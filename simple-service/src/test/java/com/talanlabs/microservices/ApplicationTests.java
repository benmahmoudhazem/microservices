package com.talanlabs.microservices;

import com.talanlabs.microservices.rest.HelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.LocalManagementPort;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ApplicationTests {

	@LocalServerPort
	private int port = 0;

	@LocalManagementPort
	private int managementPort = 0;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo(HelloWorld.HELLO_WORLD);
		assertThat(entity.getHeaders()).doesNotContainKey("Set-Cookie");
	}


	@Test
	public void testHealth() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
				"http://localhost:" + this.managementPort + "/admin/health",
				String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("\"status\":\"UP\"");
	}

}