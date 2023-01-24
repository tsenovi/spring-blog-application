package com.example.springblogapplication.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {

  private final AccountRepository accountRepository;

  public Account save(Account account) {
    return accountRepository.save(account);
  }
}
