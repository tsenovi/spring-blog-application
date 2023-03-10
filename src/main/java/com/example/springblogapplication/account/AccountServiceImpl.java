package com.example.springblogapplication.account;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public Account save(Account account) {
    account.setPassword(passwordEncoder.encode(account.getPassword()));
    return accountRepository.save(account);
  }

  @Override
  public Optional<Account> findByEmail(String email) {
    return accountRepository.findOneByEmail(email);
  }
}
