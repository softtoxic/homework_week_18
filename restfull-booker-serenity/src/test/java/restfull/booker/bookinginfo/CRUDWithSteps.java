package restfull.booker.bookinginfo;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import restfull.booker.testbase.TestBase;
import restfull.booker.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class CRUDWithSteps extends TestBase {
    static String firstName = "User" + TestUtils.getRandomValue();
    static String updateFirstName = "first" + TestUtils.getRandomValue();
    static String lastName = "last" + TestUtils.getRandomValue();
    static String additionalNeeds = "Birthday";
    static int bookingId;

    static String token;

    @Steps
    BookingSteps bookingSteps;

    @Title("This will return token")
    @Test
    public void test001() {
        token = bookingSteps.fetchToken("admin","password123");
        System.out.println("Token :" + token);
        Assert.assertNotNull(token);
    }


    @Title("This will create booking")
    @Test()
    public void test002() {
        System.out.println("====================" + token);
        bookingId = bookingSteps.creatingBooking(firstName,lastName,111, true, additionalNeeds);
        Assert.assertNotNull(bookingId);
    }


    @Title("This will update booking with firstname")
    @Test
    public void test003() {
        String updatedFirstNameResult =  bookingSteps.updatingBooking(updateFirstName,lastName,111,true,additionalNeeds, token, bookingId);
        Assert.assertEquals(updateFirstName, updatedFirstNameResult);
    }

    @Title("This will fetch booking details by booking id")
    @Test
    public void test004() {
        HashMap<String, Object> bookingMap = bookingSteps.fetchDetailsByBookingId(bookingId, token);
        Assert.assertThat(bookingMap, hasValue(updateFirstName));
        Assert.assertThat(bookingMap, hasValue(lastName));
        Assert.assertThat(bookingMap, hasValue(additionalNeeds));
    }

    @Title("This will delete booking")
    @Test
    public void test005() {
        bookingSteps.deleteBooking(bookingId,token).statusCode(201);
        bookingSteps.fetchBooking(bookingId,token).statusCode(404);
    }
}
