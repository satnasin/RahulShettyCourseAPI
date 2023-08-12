package files;

import io.restassured.path.json.JsonPath;

public class Reusablemethod {
    public static JsonPath jsonresponse(String body){
        JsonPath js = new JsonPath(body);
        return js;
    }

}
