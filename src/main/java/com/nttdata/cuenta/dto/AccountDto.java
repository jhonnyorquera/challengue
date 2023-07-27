package com.nttdata.cuenta.dto;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto implements Serializable {

  private String numberAccount;
  private String type;
  private double initialBalance;
  private String identityCustomer;

  public AccountDto() {

  }
}
