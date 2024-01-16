package com.bestbuy.crudtest;

import com.bestbuy.models.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class ProductsCRUDTest extends TestBase {
    //    @BeforeClass
//    public void inIt() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 3030;
//        RestAssured.basePath = "/products";
//    }
    static String name = "Shraddha" + TestUtils.getRandomValue();
    static String type = "SoftGood";
    static int price = 4000;
    static int shipping = 3;
    static String upc = "121232353964";
    static String description = "good quality";
    static String manufacturer = "Energizer";
    static String model = "MN2400B4A";
    static String url = "Best" + TestUtils.getRandomValue() + "@gmail.com";
    static String image = "xyz.jpg";
    static int productID;

    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);
        productID = response.then().extract().path("id");
        System.out.println("ID = " + productID);
    }

    @Test
    public void test002() {
        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productID);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test003() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_siddh");
        productPojo.setType(type + "_good");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .patch("/" + productID);
        response.then().statusCode(200);
        response.prettyPrint();
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .header("Accept", "application/json")
//                .when()
//                .patch("/" +productID);
//        response.then().statusCode(200);

    }

    @Test
    public void test004() {
        given()
                .when()
                .delete("/" + productID)
                .then()
                .statusCode(200);
        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productID);
        response.then().statusCode(404);
        response.prettyPrint();


    }

}
