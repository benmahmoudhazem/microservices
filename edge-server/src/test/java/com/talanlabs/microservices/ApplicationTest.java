package com.talanlabs.microservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.LocalManagementPort;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ApplicationTest {

    @LocalServerPort
    private int port = 0;

    @LocalManagementPort
    private int managementPort = 0;

    @Value("${management.context-path}")
    private String managementContext;

    @Test
    public void catalogLoads() {
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity("http://localhost:" + port, Map.class);
        assertThat(HttpStatus.NOT_FOUND).isEqualTo(entity.getStatusCode());
    }

    @Test
    public void adminLoads() {
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity("http://localhost:" + managementPort + managementContext + "/env", Map.class);
        assertThat(HttpStatus.OK).isEqualTo(entity.getStatusCode());
    }


}