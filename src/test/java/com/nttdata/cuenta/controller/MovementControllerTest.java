package com.nttdata.cuenta.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.cuenta.data.Account;
import com.nttdata.cuenta.data.Customer;
import com.nttdata.cuenta.data.Movement;
import com.nttdata.cuenta.dto.MovementDto;
import com.nttdata.cuenta.repository.AccountRepository;
import com.nttdata.cuenta.repository.CustomerRepository;
import com.nttdata.cuenta.repository.MovementRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
class MovementControllerTest {

  @LocalServerPort
  private int port = 7633;


  @Mock
  private AccountRepository accountRepository;

  @Mock
  private MovementRepository movementRepository;

  @Mock
  private CustomerRepository customerRepository;



  private String identifier = "1234455566";
  private String nroCuenta = "878999";

  TestRestTemplate restTemplate = new TestRestTemplate();

  HttpHeaders headers = new HttpHeaders();

  @BeforeClass
  public void init() {
    Mockito.when(customerRepository.findByIdentifier(any())).thenReturn(getCustomer());
    Mockito.when(accountRepository.findByNumber(any())).thenReturn(getAccountMock());
    Mockito.when(accountRepository.save(any())).thenReturn(getAccountMock());
    Mockito.when(movementRepository.findByAccount(getAccountMock())).thenReturn(getMovements());


  }

  @Test
  void saveMovement() throws JsonProcessingException {
    headers.setContentType(MediaType.APPLICATION_JSON);
    ObjectMapper objectMapper = new ObjectMapper();
    HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(getMovementDto(50.00)), headers);
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/api/nttBanco/movement/create"), HttpMethod.POST, entity,
        String.class);
    HttpStatus actual = response.getStatusCode();
    assertEquals(actual, HttpStatus.NOT_FOUND);

  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }


  @Test
  void getReport() {
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>("", headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/api/nttBanco/movement/report?identity=1720508869&dateBegin=01012022&dateEnd=01013022"),
        HttpMethod.GET, entity, String.class);

    HttpStatus actual = response.getStatusCode();
    assertEquals(actual, HttpStatus.OK);

  }

  private Account getAccountMock() {
    Account account = new Account();
    account.setStatus(Boolean.TRUE);
    account.setBalance(100);
    account.setIdAccount(1);
    account.setNumber(this.nroCuenta);
    account.setCustomer(getCustomer());
    return account;
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

  private MovementDto getMovementDto(Double value) {
    MovementDto movementDto = new MovementDto();
    movementDto.setMovement(value);
    movementDto.setAccountNumber(this.nroCuenta);
    return movementDto;

  }

  private List<Movement> getMovements() {
    List<Movement> movements = new ArrayList<>();
    movements.add(getMovement());
    movements.add(getMovement());
    return movements;
  }

  private Movement getMovement() {
    Movement movement = new Movement();
    movement.setAccount(getAccountMock());
    movement.setAmount(200);
    movement.setType("CREDIT");
    movement.setDateMovement(LocalDate.of(2022, 0, 2));
    movement.setBalance(200);
    return movement;
  }
}