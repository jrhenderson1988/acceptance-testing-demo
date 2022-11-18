package com.example.acceptancetestingdemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(
    properties = {"app.show-qas=true"},
    webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerWithShowQAsEnabledIT {
  private final BasicJsonTester json = new BasicJsonTester(getClass());
  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void gettingUserShouldReturnJT() {
    var response =
        restTemplate.getForEntity("http://localhost:%d/test".formatted(port), String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotBlank();
    var body = response.getBody();
    assertThat(json.from(body)).extractingJsonPathStringValue("@.name").isEqualTo("JT");
    assertThat(json.from(body))
        .extractingJsonPathStringValue("@.role")
        .isEqualTo("MASTERFUL_MANAGER");
  }
}
