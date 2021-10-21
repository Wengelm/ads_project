package com.senla.ads.repository;

import com.senla.ads.entity.AdPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdPaymentRepository extends JpaRepository<AdPaymentStatus, Long> {

    List<AdPaymentStatus> findAdPaymentStatusByUntilPaied(LocalDate date);
}
