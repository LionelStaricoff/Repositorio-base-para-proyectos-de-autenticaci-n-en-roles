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
		String token = "";
		headers.set("authorization", "BEARER ".concat(token));
	}

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
}
