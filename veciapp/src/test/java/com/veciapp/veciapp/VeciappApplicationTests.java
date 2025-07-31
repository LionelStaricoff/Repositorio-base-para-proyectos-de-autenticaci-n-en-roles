package com.veciapp.veciapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.veciapp.veciapp.Util.Util;
import com.veciapp.veciapp.dto.LoginDto;
import com.veciapp.veciapp.dto.UserResponseDto;
import jdk.jfr.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OpenLabApplicationTests {

	private TestRestTemplate testRestTemplate;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@LocalServerPort
	private int port;

	HttpHeaders headers;

	@BeforeEach
	void setUp() {
		restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
		testRestTemplate = new TestRestTemplate(restTemplateBuilder);
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		LoginDto user = new LoginDto("Lionhead@gmail.com", "Lionhead01@gmail.com");

		String json = """
                {
                    "email": "%s",
                    "password": "%s"
                }
                """.formatted(user.email(), user.password());

		System.out.println("json = " + json);
		HttpEntity<String> request = new HttpEntity<>(json, headers);

		// Asegúrate de incluir el esquema en la URL
		ResponseEntity<String> result = testRestTemplate.exchange("http://localhost:" + port + "/api/v1/user/login", HttpMethod.POST, request, String.class);

		String token = result.getHeaders().get("authorization").getFirst();
		headers.set("authorization", "BEARER ".concat(token));
	}

	/**
	 *
	 * mvn clean package
	 *  docker build -t lionelstaricoff/veciapp:v1 .
	 * docker push lionelstaricoff/veciapp:v1
	 * https://dashboard.render.com/web/srv-cugdnl0gph6c73d1a7g0/deploys/dep-d1qrg1je5dus73dugaf0
	 *  versionado docker:
	 *push lionelstaricoff/veciapp:v2 : creando usuario ok
	 *
	 * veciapp:4: login
	 */

	@Label("Agregar un nuevo usuario")
	@Test
	void createUSer() throws JsonProcessingException {
		LoginDto user = new LoginDto(UUID.randomUUID() + "Lionhead@gmail.com", "Lionhead01@gmail.com");

		String json = """
                {
                    "email": "%s",
                    "password": "%s"
                }
                """.formatted(user.email(), user.password());

		System.out.println("json = " + json);
		HttpEntity<String> request = new HttpEntity<>(json, headers);

		// Asegúrate de incluir el esquema en la URL
		ResponseEntity<UserResponseDto> result = testRestTemplate.exchange("http://localhost:" + port + "/api/v1/user", HttpMethod.POST, request, UserResponseDto.class);

		Util.toJsonPrint("result = ", result);

		assertAll(
				() -> assertEquals(HttpStatus.CREATED, result.getStatusCode()),
				() -> assertEquals(201, result.getStatusCode().value()),
				() -> assertNotNull(result.getBody())
		);
	}


	@Label("Loguear un usuario")
	@Test
	void login() throws JsonProcessingException {
		LoginDto user = new LoginDto("Lionhead@gmail.com", "Lionhead01@gmail.com");

		String json = """
                {
                    "email": "%s",
                    "password": "%s"
                }
                """.formatted(user.email(), user.password());

		System.out.println("json = " + json);
		HttpEntity<String> request = new HttpEntity<>(json, headers);

		// Asegúrate de incluir el esquema en la URL
		ResponseEntity<String> result = testRestTemplate.exchange("http://localhost:" + port + "/api/v1/user/login", HttpMethod.POST, request, String.class);

		Util.toJsonPrint("result = ", result.getHeaders());

		assertAll(
				() -> assertEquals(HttpStatus.CREATED, result.getStatusCode()),
				() -> assertEquals(201, result.getStatusCode().value()),
				() -> assertNotNull(result.getBody())
		);
	}


	@Label("test")
	@Test
	void test() throws JsonProcessingException {

		HttpEntity<String> request = new HttpEntity<>(headers);

		// Asegúrate de incluir el esquema en la URL
		ResponseEntity<String> result = testRestTemplate.exchange("http://localhost:" + port + "/api/v1/user/test", HttpMethod.GET, request, String.class);

		System.out.println("result = " + result);

		assertAll(
				() -> assertEquals(HttpStatus.OK, result.getStatusCode()),
				() -> assertEquals(200, result.getStatusCode().value()),
				() -> assertNotNull(result.getBody())
		);
	}
}
