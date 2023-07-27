package com.nttdata.cuenta.repository;

import com.nttdata.cuenta.data.Account;
import com.nttdata.cuenta.data.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

  public Account findByNumber(String number);

  public List<Account> findByCustomer(Customer customer);


}
