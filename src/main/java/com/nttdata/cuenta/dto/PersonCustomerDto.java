package com.nttdata.cuenta.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonCustomerDto implements Serializable {

  private String name;
  private String address;
  private String telephone;
  private String password;
  private String identity;
  private Integer age;
  private String genre;

  public PersonCustomerDto() {

  }
}
