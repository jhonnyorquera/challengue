package com.nttdata.cuenta.dto;

import com.nttdata.cuenta.data.Account;
import com.nttdata.cuenta.data.Movement;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountReportDto {

  private Account account;
  private List<Movement> movement;

}
