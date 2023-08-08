package com.gorest.userinfo;

import com.gorest.stepsinfo.UserSteps;
import com.gorest.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {
static String name = "Parth"+ getRandomValue();
static String email = getRandomValue()+"@gmail.com";
static String gender = "Male";
static String status = "Active";
static int userId;
@Steps
    UserSteps userSteps;
@Title("This test will create user")
    @Test
    public void test001(){
    userSteps.createUser(name, email, gender, status).statusCode(201);
}

    @Title("Verify if the user was added successfully")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
        userId = (int) productMap.get("id");
    }
    @Title("This will update user and verify updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        userSteps.updateUser(userId, name, email, gender, status).statusCode(200);
        HashMap<String, Object> productMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
    }
    @Title("Delete user and verify the product is deleted")
    @Test
    public void test004() {
        userSteps.deleteUser(userId).statusCode(200);
        userSteps.getStoreById(userId).statusCode(404);
    }
}
