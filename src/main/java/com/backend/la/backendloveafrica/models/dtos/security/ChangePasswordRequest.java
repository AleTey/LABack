package com.backend.la.backendloveafrica.models.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

  private String password;
  private String newPassword;
  private String confirmNewPassword;

}
