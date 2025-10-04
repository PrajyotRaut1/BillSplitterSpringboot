package com.example.billsplitter.model;


import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "bill_shares")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillShare {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "bill_id", nullable = false)
private Bill bill;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;


@Column(name = "share_amount", nullable = false)
private BigDecimal shareAmount;


@Builder.Default
private boolean settled = false;


private LocalDateTime createdAt;


@PrePersist
public void prePersist() {
createdAt = LocalDateTime.now();
}
}