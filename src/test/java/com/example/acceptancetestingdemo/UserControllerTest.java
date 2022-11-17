package com.example.acceptancetestingdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class UserControllerTest {

  @Test
  void get_returnsCodeMonkeyHendog_whenShowQAsIsFalse() {
    var sut = new UserController(false);

    var result = sut.get();

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(User.builder().name("Hendog").role(Role.CODE_MONKEY).build(), result.getBody());
  }

  @Test
  void get_returnsMasterfulManagerJT_whenShowQAsIsTrue() {
    var sut = new UserController(true);

    var result = sut.get();

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(User.builder().name("JT").role(Role.MASTERFUL_MANAGER).build(), result.getBody());
  }
}
