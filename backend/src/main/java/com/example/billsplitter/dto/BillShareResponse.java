package com.example.billsplitter.dto;


import lombok.Data;


import java.math.BigDecimal;


@Data
public class BillShareResponse {
private Long userId;
private String userName;
private BigDecimal shareAmount;
private boolean settled;
}