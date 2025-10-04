package com.example.billsplitter.dto;


import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
public class BillResponse {
private Long id;
private Long payerId;
private String payerName;
private BigDecimal totalAmount;
private LocalDate date;
private String reason;
private List<BillShareResponse> shares;
}