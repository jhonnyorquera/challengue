package com.nttdata.cuenta.controller;

import com.nttdata.cuenta.dto.PersonCustomerDto;
import com.nttdata.cuenta.repository.CustomerRepository;
import com.nttdata.cuenta.services.CustomerService;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {



  private CustomerService customerService;

  @PutMapping("/create")
  public ResponseEntity<String> saveCustomerPerson(
      @RequestBody ArrayList<PersonCustomerDto> personCustomerDto) {
    customerService.saveCustomers(personCustomerDto);
    return new ResponseEntity<>("Customer Created!", HttpStatus.CREATED);

  }

  @GetMapping("/find")
  public ResponseEntity<PersonCustomerDto> getCustomer(@RequestParam String identifier) {
    return new ResponseEntity<PersonCustomerDto>(customerService.getCustomerByIdentity(identifier),
        HttpStatus.OK);
  }

  @PostMapping("/edit")
  public ResponseEntity<String> getCustomer(@RequestBody PersonCustomerDto personCustomerDto) {
    customerService.editCustomer(personCustomerDto);
    return new ResponseEntity<>("Usuario Editado", HttpStatus.OK);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteCustomer(@RequestParam String identifier) {
    customerService.deleteCustomer(identifier);
    return new ResponseEntity<>("Customer deleted", HttpStatus.ACCEPTED);
  }


}
