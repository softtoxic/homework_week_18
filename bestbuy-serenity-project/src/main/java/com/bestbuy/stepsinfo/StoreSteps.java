package com.bestbuy.stepsinfo;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {
    @Step("Creating product with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city,
                                           String state, String zip, double lat) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post()
                .then();
    }
    @Step("Getting the store information with stored name: {0}")
    public HashMap<String, Object> getStoreInfoByName(String name) {
        String p1 = "data.findAll{it.name ='";
        String p2 = "'}.get(0)";

        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }
    @Step("Getting the store information with storeId: {0}")
    public ValidatableResponse getStoreById(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeId)
                .when()
                .get(EndPoints.GET_STORES_BY_ID)
                .then();

    }

    @Step("Getting the all stores information")
    public ValidatableResponse getAllStores() {
        return SerenityRest
                .given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then();
    }

    @Step("Updating store information with storeId: {0}")
    public ValidatableResponse updateStore(int storeId, String name, String type, String address, String address2,
                                           String city, String state, String zip, double lat) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        return SerenityRest.rest()
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .pathParam("storeID", storeId)
                .when()
                .body(storePojo)
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }

    @Step("Deleting product information with storeId: {0}")
    public ValidatableResponse deleteStore(int storeId) {
        return SerenityRest.given()
                .pathParam("storeID", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }
}