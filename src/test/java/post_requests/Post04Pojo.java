package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerOkuAppBaseUrl {

/*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                     },
                 "additionalneeds": "Breakfast with white tea"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

       @Test
     public void post04(){
       spec.pathParams("first","booking");

       //Send the expected data
           BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2021-09-21","2021-12-21");
           BookingPojo expectedData=new BookingPojo("Ali", "Can", 999,true,bookingDatesPojo,"Breakfast"  );
           System.out.println("expectedData = "+expectedData);


           //Send the request and get the response
           Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
           response.prettyPrint();

           //Do Assertion
           BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
           System.out.println("actualData = " + actualData);

           assertEquals(200,response.statusCode());
           assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
           assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
           assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
           assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
           assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

           //1st Way: Recommended
           assertEquals(bookingDatesPojo.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
           assertEquals(bookingDatesPojo.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());

           //2nd Way: Not Recommended
           assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
           assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());



























       }













}
