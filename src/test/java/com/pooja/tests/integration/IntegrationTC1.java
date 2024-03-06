package com.pooja.tests.integration;

import com.pooja.base.BaseTest;
import com.pooja.endpoints.APIConstants;
import com.pooja.pojos.Booking;
import com.pooja.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;

public class IntegrationTC1 extends BaseTest {


    @Test(groups = "integration", priority = 1)
    @Owner("pooja")
    @Description("TC#INT1-step1. Verify that booking is created")
    public void testCreateBooking(ITestContext iTestContext){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response= RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGson()).post();
        validatableResponse=response.then().log().all();

        BookingResponse bookingResponse=payloadManager.bookingResponseJava(response.asString());
        assertThat(bookingResponse.getBookingid()).isNotNull();
        iTestContext.setAttribute("bookingId", bookingResponse.getBookingid());
        iTestContext.setAttribute("token", getToken());

    }


    @Test(groups = "integration", priority = 2)
    @Owner("pooja")
    @Description("TC#INT1-step2. Verify booking ID")
    public void verifyBookingID(ITestContext iTestContext){

        System.out.println(iTestContext.getAttribute("bookingId"));
        Assert.assertTrue(true);
    }

    @Test(groups = "integration", priority = 3)
    @Owner("pooja")
    @Description("TC#INT1-step3. Verify booking is updated")
    public void testUpdateBooking( ITestContext iTestContext){
        Integer bookingId=(Integer) iTestContext.getAttribute("bookingId");
        String token=(String) iTestContext.getAttribute("token");
        System.out.println("Booking Id generated"+bookingId);
        System.out.println("Token generated"+token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingId);
        response=RestAssured.given().spec(requestSpecification).cookie("token", token)
                .when().body(payloadManager.updatePayload()).put();
        validatableResponse=response.then().log().all();
        Booking booking=payloadManager.bookingResponsePutReq(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("Pooja");

    }

    @Test(groups = "integration", priority = 4)
    @Owner("pooja")
    @Description("TC#INT1-step4. verify booking is deleted")
    public void testDeleteBooking(ITestContext iTestContext){

        Integer bookingId = (Integer) iTestContext.getAttribute("bookingId");
        String token = (String) iTestContext.getAttribute("token");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingId).cookie("token", token);
        response=RestAssured.given().spec(requestSpecification)
                .when().delete();
                validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
    }
}
