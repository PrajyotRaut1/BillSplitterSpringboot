package com.example.billsplitter.repository;


import com.example.billsplitter.model.Bill;
import com.example.billsplitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
List<Bill> findByPayer(User payer);
}