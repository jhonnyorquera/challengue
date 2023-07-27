package com.nttdata.cuenta.services;

import com.nttdata.cuenta.dto.PersonCustomerDto;
import java.util.ArrayList;

public interface CustomerService {

  void saveCustomers(ArrayList<PersonCustomerDto> personCustomerDtos);

  PersonCustomerDto getCustomerByIdentity(String identity);

  void editCustomer(PersonCustomerDto personCustomerDto);

  void deleteCustomer(String identifer);
}
