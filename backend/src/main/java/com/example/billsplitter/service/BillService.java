package com.example.billsplitter.service;


import com.example.billsplitter.dto.BillResponse;
import com.example.billsplitter.dto.CreateBillRequest;


import java.util.List;


public interface BillService {
BillResponse createBill(CreateBillRequest req);
BillResponse getBill(Long id);
List<BillResponse> listBillsByPayer(Long payerId);
}