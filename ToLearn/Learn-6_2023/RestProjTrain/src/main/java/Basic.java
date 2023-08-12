import files.Reusablemethod;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basic {
    @Test
    public void bTest(){
        //Given - all input details
        //when - Submit the API - resource, http method
        //Then - validate the response
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"12 church Street Mumbai CST\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"English-IN\"\n" +
                        "}")
                /*.when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200);*/
                .when().post("maps/api/place/add/json")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)");


    }
                @Test
    public void bTest2(){
                    System.out.println("<<<<<<<<<<<<<<<<<<<<Test running btest2>>>>>>>>>>>>>>>>>>>>>>>>>");
                    RestAssured.baseURI="https://rahulshettyacademy.com";
                    given().log().all().queryParam("key","qaclick123")
                            .header("Content-Type","application/json")
                            .body(payload.addPlace())
                            .when().post("maps/api/place/add/json")
                            .then().log().all()
                            .assertThat().statusCode(200)
                            .body("scope",equalTo("APP"))
                            .header("Server","Apache/2.4.52 (Ubuntu)");
                }


                // TEST 3 End To End
                // Add place -> Update Place with New Address -> Get place to validate if New address is present in response.


    @Test
    public void bTest3(){

        System.out.println("<<<<<<<<<<<<<<<<<<<<Test running btest3>>>>>>>>>>>>>>>>>>>>>>>>>");

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String responsePayload = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asPrettyString();
        System.out.println("OUTPUTTING THE RESPONSE "+responsePayload);
        JsonPath js = Reusablemethod.jsonresponse(responsePayload);
        //JsonPath js = new JsonPath(responsePayload);
        String placeid = js.getString("place_id");
        System.out.println(placeid);
        // UPDATE ADDRESS

        String upaddressto = "4801 5th Ave, Brooklyn, NY 11220, United States";

        given().log().all()
                .queryParam("place_id", placeid)
                .queryParam("key", "qaclick123")
                .when().body("" + payload.updatePlace("" + placeid, "" + upaddressto))
                .put("maps/api/place/update/json")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));



        String resGivenadress = given().log().all()
                .queryParam("place_id", placeid)
                .queryParam("key", "qaclick123")
                .when().get("maps/api/place/get/json")
                .then().log().all().extract().response().asString();
        System.out.println("======================================"+resGivenadress);
        JsonPath responsegivenaddress = Reusablemethod.jsonresponse(resGivenadress);
        String valadd =responsegivenaddress.getString("address");
        Assert.assertEquals(valadd,upaddressto);


    }




}
