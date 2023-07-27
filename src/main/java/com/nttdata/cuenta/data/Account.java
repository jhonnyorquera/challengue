package com.nttdata.cuenta.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {

  @Id
  @Column
  @GeneratedValue
  private Integer idAccount;

  @Column(unique = true)
  private String number;

  @Column
  private String type;

  @Column
  private double balance;

  @Column
  private boolean status;

  @ManyToOne
  @JsonIgnore
  private Customer customer;


}
