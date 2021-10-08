package com.senla.ads.service;

import com.senla.ads.entity.Ad;

import java.util.List;

public interface AdService {

    Ad save(Ad ad);

    void delete(Long id);

    List<Ad> findAll();

}
