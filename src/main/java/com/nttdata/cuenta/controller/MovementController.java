package com.nttdata.cuenta.controller;

import com.nttdata.cuenta.dto.MovementDto;
import com.nttdata.cuenta.dto.ReportMovementDto;
import com.nttdata.cuenta.services.impl.MovementServiceImpl;
import com.nttdata.cuenta.services.impl.ReportServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movement")
@AllArgsConstructor
public class MovementController {

  private MovementServiceImpl movementServiceImpl;
  private ReportServiceImpl reportService;

  @PostMapping("/create")
  public void saveCustomerPerson(@RequestBody MovementDto movementDto) {
    if (movementDto.getAccountNumber().equals("") || movementDto.getAccountNumber() == null) {
      throw new RuntimeException("Account Number is required");
    }
    movementServiceImpl.saveMovement(movementDto);
  }

  @GetMapping("/report")
  public ReportMovementDto getReport(@RequestParam String identity, @RequestParam String dateBegin, String dateEnd) {
    return reportService.getReportMovements(identity, dateBegin, dateEnd);
  }


}