package com.senla.ads.service.impl;

import com.senla.ads.entity.*;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.repository.AdRepository;
import com.senla.ads.repository.CategoryRepository;
import com.senla.ads.service.AdService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Ad save(Ad ad) {
        ad.setStatus(new Status(AdStatus.NEW));
        ad.setCreatedDate(LocalDate.now());
        AdPaymentStatus adPaymentStatus = new AdPaymentStatus();
        adPaymentStatus.setPaid(false);
        adPaymentStatus.setUntilPaied(LocalDate.now());
        Category category = categoryRepository.getByName(ad.getCategory().getName());
        if (category == null) {
            log.info("Entity not found");
            throw new MyEntityNotFoundException(ad.getCategory().getId());

        }
        ad.setCategory(category);
        ad.setAdPaymentStatus(adPaymentStatus);
        log.info("Saved new AD: Ad{}", ad);
        return adRepository.saveAndFlush(ad);
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN') and" +
            " authentication.principal.username.equals(#r.byUser.login)")
    public Ad update(Ad ad) {
        Ad a = adRepository.findById(ad.getId())
                .orElseThrow(() -> {
                    log.info("Entity not found");
                    throw new MyEntityNotFoundException(ad.getId());
                });
        ;
        a.setDescription(ad.getDescription());
        a.setTitle(ad.getDescription());
        log.info("Updated AD: Ad{}", a);
        return adRepository.save(ad);
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Ad getAdById(Long id) {
        return adRepository.findById(id).orElseThrow();
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void delete(Long id) {
        try {
            adRepository.deleteById(id);
            log.info("Deleted AD with id: id{}", id);
        } catch (HibernateError error) {
            log.error(error.getMessage());
        }

    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Ad> findAllByUserLogin(String login) {
        try {
            return adRepository.getAdByTitle(login);
        } catch (NullPointerException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Ad> findAll() {
        try {

            List<Ad> adList = adRepository.findAll();

            adList.sort(Comparator.comparing((Ad ad) -> ad.getAdPaymentStatus().isPaid())
                    .thenComparingDouble((Ad ad) -> ad.getUser().getRating()).reversed());
            log.info("Finded all ads");
            return adList;
        } catch (NullPointerException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Ad> getAdByCategoryName(String name) {
        try {
            List<Ad> adList = adRepository.getAdByCategoryName(name);
            adList.sort(Comparator.comparing((Ad ad) -> ad.getAdPaymentStatus().isPaid())
                    .thenComparingDouble((Ad ad) -> ad.getUser().getRating()).reversed());
            return adList;
        } catch (NullPointerException e) {
            log.error(e.getMessage());
        }
        return null;

    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Ad> searchAdByTitleLike(String name) {
        try {
            List<Ad> adList = adRepository.searchAdByTitleLike(name);
            adList.sort((a, b) -> Double.compare(b.getUser().getRating(), a.getUser().getRating()));
            return adList;
        } catch (NullPointerException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Ad comleteAd(Long id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        ad.getStatus().setName(AdStatus.COMPLETED);
        adRepository.save(ad);
        return ad;
    }
}
