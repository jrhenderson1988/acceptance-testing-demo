package com.example.acceptancetestingdemo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
  String name;
  Role role;
}
