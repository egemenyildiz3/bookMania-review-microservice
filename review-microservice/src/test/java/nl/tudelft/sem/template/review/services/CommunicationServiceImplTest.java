package nl.tudelft.sem.template.review.services;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommunicationServiceImplTest {

    @Autowired
    CommunicationServiceImpl service = new CommunicationServiceImpl();

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(); // No-args constructor defaults to port 8080
        wireMockServer.start();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void getResponseTest() {
        // Configure a stub for a GET request

        wireMockServer.stubFor(get(urlEqualTo("/check/role/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"role\":\"Admin\"}")));
        boolean book = service.getResponse("http://localhost:8080", 1L,true, true);
        assertTrue(book);
        // Verify that the expected request was made
        wireMockServer.verify(getRequestedFor(urlEqualTo("/check/role/1")));
    }

    @Test
    void isAdminTest() {
        // Configure a stub for a GET request

        wireMockServer.stubFor(get(urlEqualTo("/check/role/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"role\":\"Admin\"}")));
        boolean admin = service.isAdmin(1L);
        assertTrue(admin);
        // Verify that the expected request was made
        wireMockServer.verify(getRequestedFor(urlEqualTo("/check/role/1")));
    }
    @Test
    void isNotAdminTest() {
        // Configure a stub for a GET request


        wireMockServer.stubFor(get(urlEqualTo("/check/role/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"role\":\"Author\"}")));
        boolean admin = service.isAdmin(1L);
        assertFalse(admin);
        // Verify that the expected request was made
        wireMockServer.verify(getRequestedFor(urlEqualTo("/check/role/1")));
    }
    @Test
    void NotExistsUserTest() {
        // Configure a stub for a GET request

        wireMockServer.stubFor(get(urlEqualTo("/check/role/1"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\":\"User account not found\"}")));
        boolean user = service.existsUser(1L);
        assertFalse(user);
        // Verify that the expected request was made
        wireMockServer.verify(getRequestedFor(urlEqualTo("/check/role/1")));
    }
    @Test
    void ExistsUserTest() {
        // Configure a stub for a GET request

        wireMockServer.stubFor(get(urlEqualTo("/check/role/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"role\":\"User\"}")));
        boolean user = service.existsUser(1L);
        assertTrue(user);
        // Verify that the expected request was made
        wireMockServer.verify(getRequestedFor(urlEqualTo("/check/role/1")));
    }

    @Test
    void ExistsBookTest() {
        // Configure a stub for a GET request
        WireMockServer wireMockServer1 = new WireMockServer(8081); // No-args constructor defaults to port 8080
        wireMockServer1.start();
        WireMock.configureFor(8081);

        wireMockServer1.stubFor(get(urlEqualTo("/book/getById/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"genre\":\"horror\"}")));
        boolean user = service.existsBook(1L);
        assertTrue(user);
        // Verify that the expected request was made
        wireMockServer1.verify(getRequestedFor(urlEqualTo("/book/getById/1")));
        wireMockServer1.stop();

    }
    @Test
    void NotExistsBookTest() {
        // Configure a stub for a GET request
        WireMockServer wireMockServer1 = new WireMockServer(8081); // No-args constructor defaults to port 8080
        wireMockServer1.start();
        WireMock.configureFor(8081);

        wireMockServer1.stubFor(get(urlEqualTo("/book/getById/1"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody("cannot find book")));
        boolean user = service.existsBook(1L);
        assertFalse(user);
        // Verify that the expected request was made
        wireMockServer1.verify(getRequestedFor(urlEqualTo("/book/getById/1")));
        wireMockServer1.stop();
    }
}