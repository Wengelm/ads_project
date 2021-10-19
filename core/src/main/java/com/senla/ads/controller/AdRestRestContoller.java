package com.senla.ads.controller;

import com.senla.ads.dto.AdDto;
import com.senla.ads.dto.request.UserLoginRequest;
import com.senla.ads.entity.Ad;
import com.senla.ads.service.AdService;
import com.senla.ads.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ad")
@SecurityRequirement(name = "bearerAuth")
public class AdRestRestContoller {

    @Autowired
    private AdService adService;
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getbyuserlogin")
    public List<AdDto> getAdsByUserLogin(@Valid @RequestBody UserLoginRequest login) {

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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Ad ad = modelMapper.map(adDto, Ad.class);
        ad.setUser(userService.getUserByLogin(userDetails.getUsername()));
        adService.save(ad);
        return modelMapper.map(ad, AdDto.class);

    }

    @PostMapping("/changestatus")
    public AdDto changestatus(@Valid @RequestParam("id") Long id) {
        Ad ad = adService.comleteAd(id);
        return modelMapper.map(ad, AdDto.class);

    }

    @PostMapping("/update")
    public AdDto update(@Valid @RequestBody AdDto adDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Ad ad = modelMapper.map(adDto, Ad.class);
        ad.setUser(userService.getUserByLogin(userDetails.getUsername()));
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
