package com.example.billsplitter.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
public class CreateBillRequest {
@NotNull
private BigDecimal totalAmount;


@NotNull
private Long payerId;


@NotEmpty
private List<Long> participantIds;


private LocalDate date;
private String reason;
}