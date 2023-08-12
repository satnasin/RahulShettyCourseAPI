package pojo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Oauth2 {

    @Test
    public static void  getCouserApi(){


   String respAcces_token =given().log().all().urlEncodingEnabled(false)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
               .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
               .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
               .queryParams("grant_type","authorization_code")
           .queryParams("code","4%2F0Adeu5BVtRYZ1tiSiC_viqhT66Y7ueR4Ws8E0U6txfSSSV6T9jQIIjdwOOVwoQHvSZvCXZw")
           .when()
               .post("https://www.googleapis.com/oauth2/v4/token")
           .asString();


        JsonPath js = new JsonPath(respAcces_token);
        String accessTokenresp =js.getString("access_token");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+accessTokenresp);



      String response =given().queryParam("access_token",accessTokenresp)
               .when().log().all()
               .get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println("final========================"+response);



    }

}
