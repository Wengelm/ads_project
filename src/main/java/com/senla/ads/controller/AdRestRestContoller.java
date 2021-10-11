package com.senla.ads.controller;

import com.senla.ads.dto.AdDto;
import com.senla.ads.dto.RequestUserLogin;
import com.senla.ads.entity.Ad;
import com.senla.ads.service.AdService;
import com.senla.ads.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ad")
@Validated
public class AdRestRestContoller {

    @Autowired
    private AdService adService;
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getbyuserlogin")
    public List<AdDto> getAdsByUserLogin(@Valid @RequestBody RequestUserLogin login) {

        return adService.findAllByUserLogin(login.getLogin())
                .stream()
                .map(entity -> modelMapper.map(entity, AdDto.class))
                .collect(Collectors.toList());

    }

    @GetMapping("/get/{id}")
    public AdDto getById(@Valid @PathVariable("id") Long id) {
        Ad ad = adService.getAdById(id);
        return modelMapper.map(ad, AdDto.class);

    }


    @PostMapping("/add")
    public AdDto addNewAd(@Valid @RequestBody AdDto adDto) {

        Ad ad = modelMapper.map(adDto, Ad.class);
        ad.setUser(userService.getUserById(20L));
        adService.save(ad);
        return modelMapper.map(ad, AdDto.class);

    }

    @PostMapping("/changestatus")
    public AdDto changestatus(@Valid @RequestParam("id") Long id) {
          Ad ad = adService.comleteAd(id);
        System.out.println(ad.getStatus().getName());
        return modelMapper.map(ad, AdDto.class);

    }

    @PostMapping("/update")
    public AdDto update(@Valid @RequestBody AdDto adDto) {

        Ad ad = modelMapper.map(adDto, Ad.class);
        ad.setUser(userService.getUserById(1L));
        adService.update(ad);
        return modelMapper.map(ad, AdDto.class);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteAd(@PathVariable("id") @NotNull @Min(1) Long id) {
        adService.delete(id);
        return "successful deleted";
    }

    @GetMapping("/all")
    public List<AdDto> getAllAd() {
        return adService.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, AdDto.class))
                .collect(Collectors.toList());

    }

    @GetMapping("/category/{name}")
    public List<AdDto> getAllAdByCategory(@Valid @PathVariable("name") String name) {
        return adService.getAdByCategoryName(name)
                .stream()
                .map(entity -> modelMapper.map(entity, AdDto.class))
                .collect(Collectors.toList());

    }

    @GetMapping("/title/{name}")
    public List<AdDto> getAllAdByTitle(@Valid @PathVariable("name") String name) {
        return adService.searchAdByTitleLike(name)
                .stream()
                .map(entity -> modelMapper.map(entity, AdDto.class))
                .collect(Collectors.toList());

    }


}
