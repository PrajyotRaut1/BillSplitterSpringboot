package com.example.billsplitter.service.impl;

import com.example.billsplitter.dto.BillResponse;
import com.example.billsplitter.dto.BillShareResponse;
import com.example.billsplitter.dto.CreateBillRequest;
import com.example.billsplitter.model.Bill;
import com.example.billsplitter.model.BillShare;
import com.example.billsplitter.model.User;
import com.example.billsplitter.repository.BillRepository;
import com.example.billsplitter.repository.UserRepository;
import com.example.billsplitter.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;

    @Override
    public BillResponse createBill(CreateBillRequest req) {
        User payer = userRepository.findById(req.getPayerId())
                .orElseThrow(() -> new IllegalArgumentException("Payer not found"));

        List<User> participants = req.getParticipantIds().stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Participant not found: " + id)))
                .collect(Collectors.toList());

        int count = participants.size();
        BigDecimal total = req.getTotalAmount();
        BigDecimal share = total.divide(new BigDecimal(count), 2, RoundingMode.DOWN);
        BigDecimal accumulated = BigDecimal.ZERO;

        Bill bill = Bill.builder()
                .payer(payer)
                .totalAmount(total)
                .date(req.getDate())
                .reason(req.getReason())
                .build();

        List<BillShare> shares = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User u = participants.get(i);
            BigDecimal s = (i == count - 1)
                    ? total.subtract(share.multiply(new BigDecimal(count - 1)))
                    : share;
            accumulated = accumulated.add(s);
            BillShare bs = BillShare.builder().bill(bill).user(u).shareAmount(s).settled(false).build();
            shares.add(bs);
        }

        bill.setShares(shares);
        Bill saved = billRepository.save(bill);
        // bill_shares will be saved via cascade

        return toResponse(saved);
    }

    @Override
    public BillResponse getBill(Long id) {
        Bill b = billRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bill not found"));
        return toResponse(b);
    }

    @Override
    public List<BillResponse> listBillsByPayer(Long payerId) {
        User payer = userRepository.findById(payerId)
                .orElseThrow(() -> new IllegalArgumentException("Payer not found"));
        List<Bill> bills = billRepository.findByPayer(payer);
        List<BillResponse> res = new ArrayList<>();
        for (Bill b : bills) res.add(toResponse(b));
        return res;
    }

    private BillResponse toResponse(Bill b) {
        BillResponse r = new BillResponse();
        r.setId(b.getId());
        r.setPayerId(b.getPayer().getId());
        r.setPayerName(b.getPayer().getName());
        r.setTotalAmount(b.getTotalAmount());
        r.setDate(b.getDate());
        r.setReason(b.getReason());
        List<BillShareResponse> sres = b.getShares().stream().map(bs -> {
            BillShareResponse sr = new BillShareResponse();
            sr.setUserId(bs.getUser().getId());
            sr.setUserName(bs.getUser().getName());
            sr.setShareAmount(bs.getShareAmount());
            sr.setSettled(bs.isSettled());
            return sr;
        }).collect(Collectors.toList());
        r.setShares(sres);
        return r;
    }
}