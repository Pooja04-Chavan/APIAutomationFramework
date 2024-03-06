package com.pooja.tests.crud;

import com.pooja.base.BaseTest;
import com.pooja.endpoints.APIConstants;
import com.pooja.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.requestSpecification;
import static org.assertj.core.api.Assertions.*;
public class CreateBooking extends BaseTest {

    @Test
    @Owner("pooja")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#1 - Verify that the booking is created")
    public void testCreateBooking() {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGson()).post();

        validatableResponse=response.then().log().all();

        BookingResponse bookingResponse=payloadManager.bookingResponseJava(response.asString());

        validatableResponse.statusCode(200);

        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotEmpty();

        //assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo();

        assertActions.verifyStatusCode(response);
    }

    @Test
    @Owner("pooja")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#2 - Verify that the booking not created when payload is missing")
    public void testCreateBookingnegative(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response= RestAssured.given().spec(requestSpecification)
                .when().body("{ }").post();
        validatableResponse=response.then().log().all();

        assertActions.verifyStatusCodeInvalidReq(response);


    }
}