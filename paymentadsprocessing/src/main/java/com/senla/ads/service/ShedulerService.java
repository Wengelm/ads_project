package com.senla.ads.service;

import com.senla.ads.entity.AdPaymentStatus;
import com.senla.ads.repository.AdPaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ShedulerService {

    @Autowired
    private AdPaymentRepository adPaymentRepository;

    private static final String CRON = "0 0 6,19 * * *";

    @Scheduled(cron = CRON)
    public void setPaymentStatusFalseIfDateExpired() {
        LocalDate date = LocalDate.now();
        List<AdPaymentStatus> list = adPaymentRepository.findAdPaymentStatusByUntilPaied(date);
        if (!list.isEmpty()) {
            list.forEach(entity -> {
                try {
                    if(entity.getUntilPaied().isAfter(date)) {
                        entity.setPaid(false);
                        log.info("Email have been sent. Entity paid: {}, Date: {}", entity.getUntilPaied(), date);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });
        }
    }
}
