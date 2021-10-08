package com.senla.ads.service.impl;

import com.senla.ads.entity.Ad;
import com.senla.ads.repository.AdRepository;
import com.senla.ads.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    public void delete(Long id) {
        adRepository.deleteById(id);
    }

    public List<Ad> findAll() {
        return adRepository.findAll();
    }
}
