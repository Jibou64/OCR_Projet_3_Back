package com.example.projet3chatop.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String name;


  public JwtResponse(String accessToken, Long id, String name) {
    this.token = accessToken;
    this.id = id;
    this.name = name;

  }
}
