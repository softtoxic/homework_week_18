package com.gorest.stepsinfo;

import com.gorest.constant.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;


public class UserSteps {
    String token = "d59b5e8b4c972a46bf739389f5ad7a438aa9243a771cd8eeb1093c7a9b8e9247";

    @Step("Creating user with name : {0}, email : {1}, gender : {2}, status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer d59b5e8b4c972a46bf739389f5ad7a438aa9243a771cd8eeb1093c7a9b8e9247")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post()
                .then();
    }

    @Step("Getting user information with id : {0}")
    public HashMap<String, Object> getUserInfoByName(String name) {
        String u1 = "data.findAll{it.name ='";
        String u2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(u1 + name + u2);
    }

    @Step("Getting the user information with userId: {0}")
    public ValidatableResponse getStoreById(int userId) {
        return SerenityRest.given().log().all()
                .pathParam("storeID", userId)
                .when()
                .get(EndPoints.GET_USER_BY_id)
                .then();
    }

    @Step("Update user info by userId : {0}")
    public ValidatableResponse updateUser(int userId, String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given()
                .header("Authorization", "Bearer" + token)
                .header("Content-Type", "application/json")
                .pathParam("userId", userId)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_id)
                .then();
    }

    @Step("Deleting user information with userId: {0}")
    public ValidatableResponse deleteUser(int userId) {
        return SerenityRest.given()
                .header("Authorization", "Bearer" + token)
                .header("Content-Type", "application/json")
                .pathParam("userId", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_id)
                .then();
    }
}