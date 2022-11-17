package com.example.acceptancetestingdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UserController {
  private final boolean showQAs;

  public UserController(@Value("${app.show-qas}") boolean showQAs) {
    this.showQAs = showQAs;
  }

  @GetMapping
  public ResponseEntity<User> get() {
    return ResponseEntity.ok(
        User.builder()
            .name(showQAs ? "JT" : "Hendog")
            .role(showQAs ? Role.MASTERFUL_MANAGER : Role.CODE_MONKEY)
            .build());
  }
}
