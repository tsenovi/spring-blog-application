package com.example.springblogapplication.post;

import com.example.springblogapplication.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@Table
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String title;

  @Column(columnDefinition = "TEXT")
  private String body;

  private LocalDateTime createdAt;

  @NotNull
  @ManyToOne()
  @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
  private Account account;
}
