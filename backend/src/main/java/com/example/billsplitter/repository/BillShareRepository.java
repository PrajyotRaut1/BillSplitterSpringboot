package com.example.billsplitter.repository;


import com.example.billsplitter.model.BillShare;
import com.example.billsplitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface BillShareRepository extends JpaRepository<BillShare, Long> {
List<BillShare> findByUser(User user);
}