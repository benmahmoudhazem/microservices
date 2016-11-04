package com.talanlabs.microservices;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestRestTemplate restTemplate;

    @Ignore
    @Test
    public void homePage() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
     //   assertThat(entity.getBody()).isEqualTo(HomeController.HELLO_WORLD);
        assertThat(entity.getHeaders()).containsKeys("Set-Cookie");
        entity.getHeaders().get("Set-Cookie").contains("XSRF-TOKEN");
    }

    @Test
    public void adminLoads() {
        ResponseEntity<Map> entity = restTemplate.getForEntity("http://localhost:" + managementPort + managementContext + "/env", Map.class);
        assertThat(HttpStatus.OK).isEqualTo(entity.getStatusCode());
    }


}