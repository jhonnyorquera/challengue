package com.nttdata.cuenta.dto;

import com.nttdata.cuenta.data.Customer;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportMovementDto {

  private Customer customer;

  private List<AccountReportDto> accountList;


}
