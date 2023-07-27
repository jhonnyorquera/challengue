package com.nttdata.cuenta.services.impl;

import com.nttdata.cuenta.data.Customer;
import com.nttdata.cuenta.dto.PersonCustomerDto;
import com.nttdata.cuenta.repository.CustomerRepository;
import com.nttdata.cuenta.services.CustomerService;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  /**
   * Save a customer
   *
   * @param personCustomerDtos dto request
   */
  @Override
  public void saveCustomers(ArrayList<PersonCustomerDto> personCustomerDtos) {
    personCustomerDtos.stream().forEach(personCustomerDto -> {
      if (personCustomerDto.getName() == null || personCustomerDto.getName().isEmpty()) {
        throw new RuntimeException("Nombre requerido");
      }
      try {
        customerRepository.save(createCustomer(personCustomerDto));
      } catch (RuntimeException runtimeException) {
        throw new RuntimeException("Identity duplicado");
      }
    });


  }

  @Override
  public void deleteCustomer(String identifer){
    Customer customer= customerRepository.findByIdentifier(identifer);
    customer.setStatus(Boolean.FALSE);
    customerRepository.save(customer);
  }


  /**
   * @param personCustomerDto dto for construct a client
   * @return customer for persist in database
   */
  private Customer createCustomer(PersonCustomerDto personCustomerDto) {
    Customer customer = new Customer();
    customer.setIdentifier(personCustomerDto.getIdentity());
    customer.setName(personCustomerDto.getName());
    customer.setStatus(Boolean.TRUE);
    customer.setAddress(personCustomerDto.getAddress());
    customer.setAge(personCustomerDto.getAge());
    customer.setPassword(personCustomerDto.getPassword());
    customer.setGenre(personCustomerDto.getGenre());
    customer.setTelephone(personCustomerDto.getTelephone());
    return customer;
  }

  @Override
  public PersonCustomerDto getCustomerByIdentity(String identity) {

    return mappingPerson(this.customerRepository.findByIdentifier(identity));
  }

  @Override
  public void editCustomer(PersonCustomerDto personCustomerDto) {
    Customer customer = this.customerRepository.findByIdentifier(personCustomerDto.getIdentity());
    if (customer.equals(null)) {
      throw new RuntimeException("Usuario no Existe");
    }
    this.customerRepository.save(mappingCustomer(customer, personCustomerDto));

  }


  private PersonCustomerDto mappingPerson(Customer customer) {
    PersonCustomerDto personCustomerDto = new PersonCustomerDto();
    personCustomerDto.setAddress(customer.getAddress());
    personCustomerDto.setGenre(customer.getGenre());
    personCustomerDto.setAge(customer.getAge());
    personCustomerDto.setPassword(customer.getPassword());
    personCustomerDto.setName(customer.getName());
    personCustomerDto.setTelephone(customer.getTelephone());
    personCustomerDto.setIdentity(customer.getIdentifier());
    return personCustomerDto;

  }

  private Customer mappingCustomer(Customer customer, PersonCustomerDto personCustomerDto) {
    customer.setTelephone(personCustomerDto.getTelephone());
    customer.setGenre(personCustomerDto.getGenre());
    customer.setName(personCustomerDto.getName());
    customer.setAge(personCustomerDto.getAge());
    customer.setAddress(personCustomerDto.getAddress());
    customer.setPassword(personCustomerDto.getPassword());
    return customer;
  }


}
