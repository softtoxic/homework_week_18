package com.bestbuy.stepsinfo;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {
    @Step("Creating product by name : {0}, type : {1}, price : {2}, upc : {3},description : {4}, manufacturer : {5}, model : {6}, shipping : {7}")
    public ValidatableResponse createProduct(String name, String type, double price, String upc, String description,
                                             String manufacturer, String model, int shipping){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setShipping(shipping);
        productPojo.setModel(model);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post()
                .then();
    }
    @Step("Getting products information by name : {0}")
    public HashMap<String, Object> getProductInfoByName(String name){
        String s1 = "data.findAll{it.name ='";
        String s2 = "'}.get(0)";
        return SerenityRest.given()
                .when()
                .get()
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);
    }
    @Step("Updating product with name : {0}, type : {1}, price : {2}, upc : {3}," +
            "description : {4}, manufacturer : {5}, model : {6}, shipping : {7}, ")
    public ValidatableResponse updateProduct(int productId, String name, String type, double price, String upc, String description,
                                             String manufacturer, String model, int shipping){

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setShipping(shipping);
        productPojo.setModel(model);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("productID", productId)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Delete product by productId : {0}")
    public ValidatableResponse deleteProduct(int productId){
        return SerenityRest.given().log().all()
                .pathParam("productID", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Getting product informantio by productId : {0}")
    public ValidatableResponse getProductById(int productId){
        return SerenityRest.given()
                .pathParam("productID", productId)
                .when()
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then();
    }
}
