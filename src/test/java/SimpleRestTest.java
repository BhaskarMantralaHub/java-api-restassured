import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enums.BrowserType;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.lessThan;

public class SimpleRestTest {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    WebDriver webDriver;

    @BeforeTest
    public void beforeTest() {
        ConfigReader.load();
        requestSpecification = new RequestSpecBuilder()
                .build()
                .baseUri("https://reqres.in/api");
        responseSpecification = new ResponseSpecBuilder()
                .build().contentType(ContentType.JSON)
                .statusCode(200).time(lessThan(5L), TimeUnit.SECONDS);
    }

    @Test()
    public void test() {
//        String request = "{\n" +
//                "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"zion resident\",\n" +
//                "    \"updatedAt\": \"2023-06-07T06:02:23.838Z\"\n" +
//                "}";

//        Request request = Request
//                .builder()
//                .withJob("zion resident")
//                .withUpdatedAt("2023-06-07T06:02:23.838Z")
//                        .withName("morpheus").build();

        Request request = new GsonBuilder().create().fromJson("{\n" +
                "    \"name\": \"Bhaskar\",\n" +
                "    \"job\": \"Software\",\n" +
                "    \"updatedAt\": \"2023-06-07T07:58:37.976Z\"\n" +
                "}", Request.class);


        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("name", "Bhaskar");
        requestMap.put("job", "Software");
        requestMap.put("updatedAt", "2023-06-07T06:02:23.838Z");
        requestSpecification
                .contentType(ContentType.JSON);
        Response response = given()
                .log().all()
                .spec(requestSpecification)
                .when()
                .auth().preemptive().oauth2("")
                .body(request)

                .put("/users/2")
                .then()
                .spec(responseSpecification)
                .extract().response();

        response.prettyPrint();

//        System.out.println(response.jsonPath().get("name").toString());

//        Gson gson = new GsonBuilder().create();
//        Request apiResponse = gson.fromJson(response.jsonPath().prettify(), Request.class);
        Request apiResponse = jsonToPojo(response.jsonPath().prettify(), Request.class);
        System.out.println(apiResponse);

//        webDriver = WebDriverFactory.getDriver(BrowserType.get(ConfigReader.getProperty("BROWSER_NAME")));
//        webDriver.get("https://projectlombok.org/setup/maven");
//        System.out.println(webDriver.getTitle());
    }

    @AfterMethod
    public void quitBrowser() {
        if (webDriver != null) webDriver.quit();
    }

    public  <T> T jsonToPojo(String jsonString, Class<T> t) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, t);
    }
}
