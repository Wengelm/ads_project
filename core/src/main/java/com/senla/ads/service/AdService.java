package com.senla.ads.service;

import com.senla.ads.entity.Ad;

import java.util.List;

public interface AdService {

    Ad save(Ad ad);

    Ad update(Ad ad);

    Ad getAdById(Long id);

    void delete(Long id);

    List<Ad> findAllByUserLogin(String login);

    List<Ad> findAll();

    List<Ad> getAdByCategoryName( String name);

    List<Ad> searchAdByTitleLike(String name);

    Ad comleteAd(Long id);


}
