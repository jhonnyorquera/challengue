package com.nttdata.cuenta.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Movement {

  @Id
  @GeneratedValue
  @Column
  private Integer idMovement;

  @Column
  private String type;

  @Column double amount;

  @Column double balance;

  @ManyToOne
  @JsonIgnore
  private Account account;

  @Column
  private LocalDate dateMovement;

}
