package com.example.billsplitter.controller;


import com.example.billsplitter.dto.BillResponse;
import com.example.billsplitter.dto.CreateBillRequest;
import com.example.billsplitter.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {
private final BillService billService;


@PostMapping
public ResponseEntity<BillResponse> createBill(@Valid @RequestBody CreateBillRequest req) {
BillResponse res = billService.createBill(req);
return ResponseEntity.ok(res);
}


@GetMapping("/{id}")
public ResponseEntity<BillResponse> getBill(@PathVariable Long id) {
return ResponseEntity.ok(billService.getBill(id));
}


@GetMapping("/by-payer/{payerId}")
public ResponseEntity<List<BillResponse>> listByPayer(@PathVariable Long payerId) {
return ResponseEntity.ok(billService.listBillsByPayer(payerId));
}
}