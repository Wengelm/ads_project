package com.senla.ads.repository;

import com.senla.ads.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AdRepository extends JpaRepository<Ad, Long> {
}
