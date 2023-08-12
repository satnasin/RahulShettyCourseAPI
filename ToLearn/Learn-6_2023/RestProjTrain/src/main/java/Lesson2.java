import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class Lesson2 {

    @Test
    public void Test(){
        JsonPath js = new JsonPath(payload.mockResp());
        // Print All course titles and their respective Prices
        for (int i=0;i<js.getInt("courses.size()");i++){
            System.out.println(""+js.getString("courses["+i+"].title"));
         // String var = js.getString("courses[" + i + "].price");
            System.out.println(""+js.getString("courses["+i+"].price"));

        }

    }
}
