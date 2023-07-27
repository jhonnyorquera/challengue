package com.nttdata.cuenta.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.sql.rowset.serial.SerialArray;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPerson;

  @Column
  private String name;

  @Column
  private String genre;

  @Column
  private Integer age;


  @Column(unique=true)
  private String identifier;

  @Column
  private String address;

  @Column
  private String telephone;


}
