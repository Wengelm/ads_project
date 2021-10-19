package com.senla.ads.service.impl;

import com.senla.ads.entity.Ad;
import com.senla.ads.entity.AdStatus;
import com.senla.ads.entity.Category;
import com.senla.ads.entity.Status;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.repository.AdRepository;
import com.senla.ads.repository.CategoryRepository;
import com.senla.ads.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Ad save(Ad ad) {
        ad.setStatus(new Status(AdStatus.NEW));
        ad.setCreatedDate(LocalDate.now());
        Category category = categoryRepository.getByName(ad.getCategory().getName());
        if (category == null) {
            throw new MyEntityNotFoundException(ad.getCategory().getId());
        }
        ad.setCategory(category);
        return adRepository.saveAndFlush(ad);
    }

    @Override
    public Ad update(Ad ad) {
        Ad a = adRepository.findById(ad.getId())
                .orElseThrow(() -> new MyEntityNotFoundException(ad.getId()));;
        a.setDescription(ad.getDescription());
        a.setTitle(ad.getDescription());
        return adRepository.save(ad);
    }

    @Override
    public Ad getAdById(Long id) {
        return adRepository.findById(id).orElseThrow();
    }


    public void delete(Long id) {
        adRepository.deleteById(id);
    }

    @Override
    public List<Ad> findAllByUserLogin(String login) {
        return adRepository.getAdByTitle(login);
    }

    public List<Ad> findAll() {
        List<Ad> adList = adRepository.findAll();
        adList.sort((a, b) -> Double.compare(b.getUser().getRating(), a.getUser().getRating()));
        return adList;
    }

    @Override
    public List<Ad> getAdByCategoryName(String name) {
        List<Ad> adList= adRepository.getAdByCategoryName(name);
        adList.sort((a, b) -> Double.compare(b.getUser().getRating(), a.getUser().getRating()));
        return adList ;
    }

    @Override
    public List<Ad> searchAdByTitleLike(String name) {
        List<Ad> adList= adRepository.searchAdByTitleLike(name);
        adList.sort((a, b) -> Double.compare(b.getUser().getRating(), a.getUser().getRating()));
        return adList;
    }

    @Override
    public Ad comleteAd(Long id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        ad.getStatus().setName(AdStatus.COMPLETED);
        adRepository.save(ad);
        return ad;
    }
}
