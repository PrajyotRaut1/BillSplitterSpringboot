package com.example.billsplitter.model;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Entity
@Table(name = "friends", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "friend_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "friend_id", nullable = false)
private User friend;


private LocalDateTime createdAt;


@PrePersist
public void prePersist() {
createdAt = LocalDateTime.now();
}
}