package com.nttdata.cuenta.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customer")
@PrimaryKeyJoinColumn(name ="idCustomer", referencedColumnName = "idPerson")
public class Customer extends Person implements Serializable {

  public Customer() {
  }

  @Column
  private String password;

  @Column
  private boolean status;


}
