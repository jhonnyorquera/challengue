package com.nttdata.cuenta.services.impl;

import com.nttdata.cuenta.data.Account;
import com.nttdata.cuenta.data.Movement;
import com.nttdata.cuenta.dto.MovementDto;
import com.nttdata.cuenta.repository.AccountRepository;
import com.nttdata.cuenta.repository.MovementRepository;
import com.nttdata.cuenta.services.MovementService;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {

  private AccountRepository accountRepository;
  private MovementRepository movementRepository;


  /**
   * save a movement for type movement debit or credit
   * @param movementDto
   */
  @Override
  public void saveMovement(MovementDto movementDto) {
    Account account = accountRepository.findByNumber(movementDto.getAccountNumber());
    if (account.equals(null)  || Math.abs(movementDto.getMovement())==0) {
      throw new RuntimeException("no existe cliente o el valor del movimiento es 0");
    }
    if (Double.compare(Math.abs(movementDto.getMovement()), 0) != 0) {
      if (Double.compare(movementDto.getMovement(), 0) > 0) {
        saveCredit(account, movementDto);
      } else {
        saveDebit(account, movementDto);
      }
    }

  }

  private void saveDebit(Account account, MovementDto movementDto) {
    if (Double.compare(account.getBalance(), Math.abs(movementDto.getMovement())) >= 0) {
      saveCredit(account, movementDto);
    } else {
      throw new RuntimeException("Saldo insuficiente");
    }
  }

  private void saveCredit(Account account, MovementDto movementDto) {
    Movement mov = mapAccount(account, movementDto);
    mov.setAmount(mov.getAmount());
    mov.setBalance(account.getBalance()+movementDto.getMovement());
    movementRepository.save(mov);
    saveAccount(account, mov);

  }

  private Movement mapAccount(Account account, MovementDto movementDto) {
    Movement movement = new Movement();
    movement.setAccount(account);
    movement.setType(movementDto.getMovement() < 0 ? "DEBIT" : "CREDIT");
    movement.setAmount(movementDto.getMovement());
    movement.setDateMovement(LocalDate.now());
    return movement;
  }


  private void saveAccount(Account account, Movement movement) {
    account.setBalance(account.getBalance() + movement.getAmount());
    accountRepository.save(account);

  }
}



