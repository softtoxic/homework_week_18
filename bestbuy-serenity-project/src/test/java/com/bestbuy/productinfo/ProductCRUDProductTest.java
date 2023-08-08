package com.bestbuy.productinfo;

import com.bestbuy.stepsinfo.ProductSteps;
import com.bestbuy.testbase.ProductTestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static com.bestbuy.utils.TestUtils.getRandomValue;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCRUDProductTest extends ProductTestBase {
    static String name = "Water" + getRandomValue();
    static String type = "Drinks";
    static String upc = "5896" + getRandomValue();
    static double price = 1.00;
    static int shipping = 0;
    static String description = "High quality";
    static String manufacturer = "Tesco";
    static String model = "Man111AA";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("This test create product")
    @Test
    public void test001() {
        productSteps.createProduct(name, type, price, upc, description, manufacturer,
                model, shipping).statusCode(201);

    }

    @Title("Verify if the product was added successfully")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = productSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
       productId = (int) productMap.get("id");
    }

    @Title("This will update product and verify updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productSteps.updateProduct(productId,name, type, price, upc, description, manufacturer,
                model, shipping).statusCode(200);
        HashMap<String, Object> productMap = productSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
    }

    @Title("Delete product and verify the product is deleted")
    @Test
    public void test004() {
        productSteps.deleteProduct(productId).statusCode(200);
        productSteps.getProductById(productId).statusCode(404);
    }
}