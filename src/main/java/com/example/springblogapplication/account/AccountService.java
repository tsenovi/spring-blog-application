package com.example.springblogapplication.account;

import java.util.Optional;

public interface AccountService {

  Account save(Account account);

  Optional<Account> findByEmail(String email);
}
