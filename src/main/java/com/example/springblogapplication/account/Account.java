package com.example.springblogapplication.account;

import com.example.springblogapplication.authorities.Authority;
import com.example.springblogapplication.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String email;

  private String password;

  private String firstName;

  private String lastName;

  @OneToMany(mappedBy = "account")
  private List<Post> posts;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "account_authority",
      joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
  private Set<Authority> authorities = new HashSet<>();

  @Override
  public String toString() {
    return "Account{" +
        "email='" + email + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", authorities=" + authorities +
        '}';
  }
}
