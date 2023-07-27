package com.nttdata.cuenta.repository;

import com.nttdata.cuenta.data.Account;
import com.nttdata.cuenta.data.Movement;
import com.nttdata.cuenta.dto.MovByCustomerAndDateDto;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer> {

  public List<Movement> findByAccount(Account account);


}
