package com.example.springblogapplication.user;

import com.example.springblogapplication.account.Account;
import com.example.springblogapplication.account.AccountService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyUserDetailsService implements UserDetailsService {

  private final AccountService accountService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Account> optionalAccount = accountService.findByEmail(email);
    if (!optionalAccount.isPresent()) {
      throw new UsernameNotFoundException("Account not found");
    }

    Account account = optionalAccount.get();
    List<GrantedAuthority> grantedAuthorities = account
        .getAuthorities()
        .stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName()))
        .collect(Collectors.toList());

    return new User(account.getEmail(), account.getPassword(), grantedAuthorities);
  }
}
