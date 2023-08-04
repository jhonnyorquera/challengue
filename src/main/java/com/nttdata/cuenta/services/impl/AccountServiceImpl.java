package com.nttdata.cuenta.services.impl;

import com.nttdata.cuenta.data.Account;
import com.nttdata.cuenta.data.Customer;
import com.nttdata.cuenta.data.Movement;
import com.nttdata.cuenta.dto.AccountDto;
import com.nttdata.cuenta.repository.AccountRepository;
import com.nttdata.cuenta.repository.CustomerRepository;
import com.nttdata.cuenta.repository.MovementRepository;
import com.nttdata.cuenta.services.AccountService;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  private AccountRepository accountRepository;
  private CustomerRepository customerRepository;
  private MovementRepository movementRepository;

  @Override
  public void saveAccount(ArrayList<AccountDto> accountDtos) {
    accountDtos.stream().forEach(accountDto -> {
      saveAccount(accountDto);
    });
  }

  /**
   * Save a account validating if a customer and account exist
   *
   * @param AccountDto dto for request
   */
  private void saveAccount(AccountDto accountDto) {
    Customer customer = customerRepository.findByIdentifier(accountDto.getIdentityCustomer());
    Account account = accountRepository.findByNumber(accountDto.getNumberAccount());
    if (customer.equals(null) || account.equals(null)) {
      throw new RuntimeException("exepcion");
    }
    Account accounts = accountRepository.save(mappingAccount(accountDto, customer));
    saveFirsMovement(accounts, accountDto.getInitialBalance());


  }

  private void saveFirsMovement(Account account, Double amount) {
    Movement movement = new Movement();
    movement.setAccount(account);
    movement.setAmount(amount);
    movement.setType("CREDIT");
    movement.setBalance(amount);
    movement.setDateMovement(LocalDate.now());
    movementRepository.save(movement);
  }


  private Account mappingAccount(AccountDto accountDto, Customer customer) {
    Account account = new Account();
    account.setBalance(accountDto.getInitialBalance());
    account.setCustomer(customer);
    account.setNumber(accountDto.getNumberAccount());
    account.setStatus(Boolean.TRUE);
    account.setType(accountDto.getType());
    return account;

  }

  @Override
  public AccountDto findAccount(String number) {
    return mappingAccount(accountRepository.findByNumber(number));
  }

  @Override
  public void deleteAccount(String number) {
    Account account = accountRepository.findByNumber(number);
    account.setStatus(Boolean.FALSE);
    accountRepository.save(account);
  }


  private AccountDto mappingAccount(Account account) {
    AccountDto accountDto = new AccountDto();
    accountDto.setNumberAccount(account.getNumber());
    accountDto.setType(account.getType());
    accountDto.setInitialBalance(account.getBalance());
    accountDto.setIdentityCustomer(account.getCustomer().getName());
    return accountDto;
  }

}
