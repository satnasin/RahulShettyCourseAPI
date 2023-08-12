import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class addPlace2 {

    @Test
    public static void  taddPlace(){

        SerializeTest st =new SerializeTest();
        st.setAccuracy(50);
        st.setAddress("129. Coles Street");
        st.setName("Legend Ornate");
        st.setLanguage("English");
        st.setPhone_number("+91-9833-33-00-11-33");
        st.setWebsite("http://google.com");
        List<String> mylist = new ArrayList<String>();
        mylist.add("shoe park");
        mylist.add("shop");
        st.setTypes(mylist);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        st.setLocation(l);



        RestAssured.baseURI = "https://rahulshettyacademy.com";
       String resp= given().log().all()
                .queryParam("key","qaclick123")
                .when().body(st)
                .post("/maps/api/place/add/json")
                .then().log().all().extract().response().asString();

        System.out.println("Reponse >>>>>"+resp);
    }

}
