package com.example.billsplitter.model;


import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "payer_id", nullable = false)
private User payer;


@Column(name = "total_amount", nullable = false)
private BigDecimal totalAmount;


private LocalDate date;


private String reason;


private LocalDateTime createdAt;


@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
@Builder.Default
private List<BillShare> shares = new ArrayList<>();


@PrePersist
public void prePersist() {
createdAt = LocalDateTime.now();
}
}