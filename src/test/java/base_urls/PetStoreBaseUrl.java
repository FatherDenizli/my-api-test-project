package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class PetStoreBaseUrl {


    protected RequestSpecification spec;

    @Before //If you see @Before annotation at the top of a method, it will be executed before every test method.
    public void setUp() {


        spec = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();


    }
}