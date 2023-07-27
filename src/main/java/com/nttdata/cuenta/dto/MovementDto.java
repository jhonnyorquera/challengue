package com.nttdata.cuenta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovementDto {

  public MovementDto() {

  }

  private String accountNumber;
  private Double movement;
}
