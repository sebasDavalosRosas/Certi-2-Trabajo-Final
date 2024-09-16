package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.BookingEndpoints;
import entities.Booking;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en_scouse.An;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import utils.Request;

import java.util.List;

import static org.hamcrest.Matchers.not;

public class BookingSteps {
    Response response;
    @And("I perform a GET call to the booking endpoint")
    public void getBookings(){response = Request.get(BookingEndpoints.GET_BOOKINGS);}

    @And("I verify that the status code is {int}")
    public void verifyStatusCode(int statusCode){response.then().assertThat().statusCode(statusCode);}

    @And("I verify that the body does not have size {int}")
    public void verifyBodySize(int size){response.then().assertThat().body("size()", not(size));}

    @And("I perform a GET call to the booking endpoint with id {string}")
    public void getBookingById(String id){response = Request.getById(BookingEndpoints.GET_BOOKING, id);}

    @And("The message is {string}")
    public void verifyResponseMessage(String message){
        response.then().assertThat().body("message", Matchers.equalTo(message));
    }

    @And("The booking name is {string}")
    public void verifyBookingName(String bookingName){
        response.then().assertThat().body("firstname", Matchers.equalTo(bookingName));
    }
    @And("I do a log on the body")
    public void logBody(){
        response.then().log().body();
    }

    @And("I perform a POST call to the create a booking with the following data")
    public void postBooking(DataTable BookingInfo) throws JsonProcessingException {
        List<String> data = BookingInfo.transpose().asList(String.class);
        Booking booking = new Booking(data);

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        response = Request.post(BookingEndpoints.POST_BOOKING, payload);
    }
}
