package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
        //response.log().all(); //print logs in console
    }
//    1. Extract the limit
    @Test
    public void test001(){
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }


//2. Extract the total
@Test
public void test002(){
    int total = response.extract().path("total");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of Total is : " + total);
    System.out.println("------------------End of Test---------------------------");
}

//3. Extract the name of 5th store
@Test
public void test003() {
    String name = response.extract().path("data[4].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The name of 5th store is : " + name);
    System.out.println("------------------End of Test---------------------------");
}

//4. Extract the names of all the store
@Test
public void test004() {
    List<String> listOfStoreNames = response.extract().path("data.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The name of all store  is : " + listOfStoreNames);
    System.out.println("------------------End of Test---------------------------");
}

//5. Extract the storeId of all the store
@Test
public void test005() {
    List<Integer> listOfStoreIds = response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The all store id's are : " + listOfStoreIds);
    System.out.println("------------------End of Test---------------------------");
}

//6. Print the size of the data list
@Test
public void test006() {
    List<Integer> listOfdata = response.extract().path("data");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The size of data list is : " + listOfdata.size());
    System.out.println("------------------End of Test---------------------------");
}

//7. Get all the value of the store where store name = St Cloud

    @Test
    public void test007() {
       List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name ==  'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for store where store name is 'St Cloud' are : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

//8. Get the address of the store where store name = Rochester
@Test
public void test008() {
    List<String> storeAddress  = response.extract().path("data.findAll{it.name ==  'Rochester'}.address");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get the address of the store where store name: " + storeAddress);
    System.out.println("------------------End of Test---------------------------");
}

//9. Get all the services of 8th store
@Test
public void test009() {
    List<HashMap<String,?>> listOfservices  = response.extract().path("data[7].services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println(" Get all the services of 8th store are: " + listOfservices);
    System.out.println("------------------End of Test---------------------------");
}
//10. Get storeservices of the store where service name = Windows Store
@Test
public void test010() {
    List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get storeservices of the store where service name: " + storeServices);
    System.out.println("------------------End of Test---------------------------");
}

//11. Get all the storeId of all the store
@Test
public void test011() {
    List<Integer> allStoreId = response.extract().path("data.services.storeservices.storeId");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the storeId of all the store are : " + allStoreId);
    System.out.println("------------------End of Test---------------------------");
}

//12. Get id of all the store
@Test
public void test012() {
    List<Integer> listId = response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get id of all the store are : " + listId);
    System.out.println("------------------End of Test---------------------------");
}

//13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String>storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the store are : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

//14. Find the Total number of services for the store where store name = Rochester
@Test
public void test014() {
    List<HashMap<String,?>> totalServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Total number of services for the store where store name is Rochester : "+ totalServices);
    System.out.println("------------------End of Test---------------------------");
}

//15. Find the createdAt for all services whose name = “Windows Store”

    @Test
    public void test015() {
        List <String> allServices = response.extract().path("data.services*.findAll{it.name =='Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all services whose name =“Windows Store” : "+ allServices);
        System.out.println("------------------End of Test---------------------------");
    }
//            16. Find the name of all services Where store name = “Fargo”

    @Test
    public void test016() {
       List<HashMap<String, ?>> allServicesName = response.extract().path("data.findAll{it.name =='Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the name of all services Where store name = “Fargo”  : "+ allServicesName);
        System.out.println("------------------End of Test---------------------------");
    }

    //            17. Find the zip of all the store
    @Test
    public void test017() {
        List<Integer> allZip = response.extract().path("data.Zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store  : "+ allZip);
        System.out.println("------------------End of Test---------------------------");
    }


//18. Find the zip of store name = Roseville
@Test
public void test018() {
    List<Integer> allZip = response.extract().path("data.findAll{it.name =='Roseville'}.zip");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the zip of all the store  : "+ allZip);
    System.out.println("------------------End of Test---------------------------");
}
//19. Find the storeservices details of the service name = Magnolia Home Theater
@Test
public void test019() {
    List<HashMap<String,?>> allStoreServices =response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the storeservices details of the service name = Magnolia Home Theater  : "+ allStoreServices);
    System.out.println("------------------End of Test---------------------------");
}


//20. Find the lat of all the stores
@Test
public void test020() {
    List<HashMap<String,?>> lat =response.extract().path("data.lat");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the lat of all the stores : "+ lat);
    System.out.println("------------------End of Test---------------------------");
}

}
