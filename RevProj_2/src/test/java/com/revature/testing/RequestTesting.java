package com.revature.testing;

import com.revature.models.Request;
import com.revature.repositories.RequestRepoHbImpl;
import com.revature.services.RequestServiceImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RequestTesting {

    RequestRepoHbImpl rr = new RequestRepoHbImpl();
    RequestServiceImpl rs = new RequestServiceImpl(rr);

    BigDecimal rmbValue = BigDecimal.valueOf(5.00);
    Long requestTime = Long.valueOf(5000000);
    Long sDeadline = Long.valueOf(100000000);
    Long dhDeadline = Long.valueOf(100000000);

    Request req10 = new Request(1, 10, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req11 = new Request(2, 11, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);

    Request req20 = new Request(3, 20, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req21 = new Request(4, 21, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req22 = new Request(5, 22, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req23 = new Request(6, 23, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);

    Request req30 = new Request(7, 30, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req31 = new Request(8, 31, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req32 = new Request(9, 32, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req33 = new Request(10, 33, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req34 = new Request(11, 34, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req35 = new Request(12, 35, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req36 = new Request(13, 36, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req37 = new Request(14, 37, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);

    Request req40 = new Request(15, 40, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req41 = new Request(16, 41, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req42 = new Request(17, 42, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req43 = new Request(18, 43, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);

    Request req50 = new Request(19, 50, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request req60 = new Request(20, 60, 5, 1, 1, rmbValue, 5000000, 100000000, 100000000);

    Request reqExtraOne = new Request(21, 10, 4, 1, 1, rmbValue, 5000000, 100000000, 100000000);
    Request reqExtraTwo = new Request(22, 10, 3, 1, 1, rmbValue, 5000000, 100000000, 100000000);

    //Repo

    @Test
    public void getRequestTest() {
        Request returnedRequest = rs.getRequest(1);
        assertEquals(returnedRequest, req10);
    }

    @Test
    public void getAttRequestsByRequesterIdTest() {
        List<Request> expectedRequests = Arrays.asList(req10, req11, req20, req21, req22, req23, req30, req31, req32, req33, req34, req35, req36, req37, req40, req41, req42, req43, req50, req60);
        List<Request> returnedRequests = rs.getAllRequestsByRequesterId(5);
        assertEquals(expectedRequests, returnedRequests);
    }

    @Test
    public void getAttnRequestsRequesterTest() {
        List<Request> expectedRequests = Arrays.asList(req11, req21, req23, req31, req33, req35, req37, req40);
        List<Request> returnedRequests = rs.getAttnRequestsRequester(5);
        assertEquals(expectedRequests, returnedRequests);
    }

    @Test
    public void getAttnRequestsSupervisorTest() {
        List<Request> expectedRequests = Arrays.asList(req10, req11, req22, req23, req32, req33, req36, req37, req43);
        List<Request> returnedRequests = rs.getAttnRequestsSupervisor(5);
        assertEquals(expectedRequests, returnedRequests);
    }

    @Test
    public void getAttnRequestsDeptHeadTest() {
        List<Request> expectedRequests = Arrays.asList(req20, req21, req22, req23, req34, req35, req36, req37);
        List<Request> returnedRequests = rs.getAttnRequestsDeptHead(5);
        assertEquals(expectedRequests, returnedRequests);
    }

    @Test
    public void getAttnRequestsBenCoTest() {
        List<Request> expectedRequests = Arrays.asList(req30, req31, req32, req33, req34, req35, req36, req37, req41, req42);
        List<Request> returnedRequests = rs.getAttnRequestsBenCo();
        assertEquals(expectedRequests, returnedRequests);
    }
//

    @Test
    @Order(2)
    public void addRequestTest() {
        Request addedRequest = new Request(21, 10, 1, 1, 1, rmbValue, requestTime, sDeadline, dhDeadline);
        Request returnedRequest = rs.addRequest(addedRequest);
        assertEquals(addedRequest, returnedRequest);
    }

    @Test
    @Order(3)
    public void updateRequestTest() {
        Request updatedRequest = new Request(21, 50, 1, 1, 1, rmbValue, requestTime, sDeadline, dhDeadline);
        Request returnedRequest = rr.updateRequest(updatedRequest);
        assertEquals(updatedRequest, returnedRequest);
    }

    @Test
    @Order(5)
    public void deleteRequestTest() {
        Request expectedRequest = new Request(21, 60, 1, 1, 1, rmbValue, requestTime, sDeadline, dhDeadline);
        Request deletedRequest = rr.deleteRequest(21);
        assertEquals(expectedRequest, deletedRequest);
    }

    @Test
    @Order(1)
    public void getAllRequestsTest() {
        List<Request> expectedRequests = Arrays.asList(req10, req11, req20, req21, req22, req23, req30, req31, req32, req33, req34, req35, req36, req37, req40, req41, req42, req43, req50, req60, reqExtraOne, reqExtraTwo);
        List<Request> returnedRequests = rr.getAllRequests();
        assertEquals(expectedRequests, returnedRequests);
    }

    //Service

    @Test
    @Order(4)
    public void alterRequestStatusCodeTest(){
        Request expectedRequest = new Request(21, 60, 1, 1, 1, rmbValue, requestTime, sDeadline, dhDeadline);
        Request alteredRequest = rs.alterRequestStatusCode(21, 60);
        assertEquals(expectedRequest, alteredRequest);
    }
}
