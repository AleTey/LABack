package com.backend.la.backendloveafrica.models.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

  private String backendMessage;

  private String message;

  private String url;

  private String method;

  private int httpCode;

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime timestamp;
}
