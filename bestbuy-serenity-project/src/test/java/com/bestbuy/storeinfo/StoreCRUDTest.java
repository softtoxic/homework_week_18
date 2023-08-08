package com.bestbuy.storeinfo;

import com.bestbuy.stepsinfo.StoreSteps;
import com.bestbuy.testbase.StoreTestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCRUDTest extends StoreTestBase {
    static String name = "Vivo";
    static String type = "Small";
    static String address = "14 High street";
    static String address2 = "Uk";
    static String city = "Surat";
    static String state = "MK";
    static String zip = "A";
    static double lat = 10.10;
    static int storeId;
@Steps
    StoreSteps storeSteps;
@Title("This test create store")
    @Test
    public void test001(){
    storeSteps.createStore(name, type, address, address2, city, state, zip, lat).statusCode(201);
    }
    @Title("This test verify store added successfully")
    @Test
    public void test002(){
        HashMap<String, Object> storeMap = storeSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));
        storeId = (int) storeMap.get("id");
    }
    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {
    type = type+"_Large";
    storeSteps.updateStore(storeId, name, type, address, address2, city, state, zip, lat).statusCode(200);
    HashMap<String, Object> storeMap = storeSteps.getStoreInfoByName(name);
    Assert.assertThat(storeMap, hasValue(name));
    }
    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004() {
        storeSteps.deleteStore(storeId).statusCode(200);
        storeSteps.getStoreById(storeId).statusCode(404);
    }
}

