package com.example.springBootProfile;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoads() {
        Integer prodPort = devApp.getMappedPort(8080);
        Integer devPort = devApp.getMappedPort(8080);

        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + prodPort, String.class);
        System.out.println(forEntityProd.getBody());
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + devPort, String.class);
        System.out.println(forEntityDev.getBody());

        assertEquals(forEntityProd.getBody(), forEntityDev.getBody());
    }

}
