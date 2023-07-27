package com.nttdata.cuenta.repository;

import com.nttdata.cuenta.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  public Customer findByIdentifier(String identity);

}
