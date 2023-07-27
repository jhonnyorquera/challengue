package com.nttdata.cuenta.services;

import com.nttdata.cuenta.dto.ReportMovementDto;

public interface ReportService {


  ReportMovementDto getReportMovements(String identity, String dateBegin, String dateEnd);
}
