package com.nttdata.cuenta.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.cuenta.data.Customer;
import com.nttdata.cuenta.dto.PersonCustomerDto;
import com.nttdata.cuenta.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

  @LocalServerPort
  private int port;

  private String identifier = "1720508869";

  @Mock
  private CustomerRepository customerRepository;

  @BeforeClass
  public void init() {
    Mockito.when(customerRepository.findByIdentifier("12312333")).thenReturn(getCustomer());

  }



  TestRestTemplate restTemplate = new TestRestTemplate();

  HttpHeaders headers = new HttpHeaders();

  @Test
  void getCustomerTs() {
    headers.setContentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    HttpEntity<String> entity = new HttpEntity<>((""), headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/api/nttBanco/customer/find?identifier=1720508869"), HttpMethod.GET, entity, String.class);
    HttpStatus actual = response.getStatusCode();
    assertEquals(actual, HttpStatus.OK);
  }


  @Test
  void deleteCustomer() {
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<>((""), headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/api/nttBanco/customer/delete?identifier=1720508869"), HttpMethod.DELETE, entity,
        String.class);
    HttpStatus actual = response.getStatusCode();
    assertEquals(actual, HttpStatus.ACCEPTED);

  }

  @Test
  void editCustomerPerson() throws JsonProcessingException {
    headers.setContentType(MediaType.APPLICATION_JSON);
    ObjectMapper objectMapper = new ObjectMapper();
    HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(getCustomerDto()), headers);
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/api/nttBanco/customer/edit"), HttpMethod.POST, entity,
        String.class);
    HttpStatus actual = response.getStatusCode();
    assertEquals(actual, HttpStatus.OK);
  }


  @Test
  void testGetCustomer() {
  }


  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }


  private Customer getCustomer() {
    Customer customer = new Customer();
    customer.setIdentifier(this.identifier);
    customer.setName("Jhon Doe");
    customer.setPassword("passs");
    customer.setIdPerson(12);
    customer.setTelephone("2280629");
    return customer;
  }

  private PersonCustomerDto getCustomerDto() {
    PersonCustomerDto personCustomerDto = new PersonCustomerDto();
    personCustomerDto.setIdentity(this.identifier);
    personCustomerDto.setName("Jhon Doe");
    personCustomerDto.setPassword("passs");
    personCustomerDto.setTelephone("2280629");
    return personCustomerDto;
  }
}