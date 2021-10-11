package com.senla.ads.repository;

import com.senla.ads.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query("SELECT ad FROM Ad ad LEFT JOIN FETCH ad.user WHERE ad.user.login = :login")
     List<Ad> getAdByTitle(@Param("login") String login);

    @Query("SELECT ad FROM Ad ad LEFT JOIN FETCH ad.category WHERE ad.category.name = :name")
     List<Ad> getAdByCategoryName(@Param("name") String name);

    @Query("SELECT ad FROM Ad ad  WHERE ad.title like %:title%")
     List<Ad> searchAdByTitleLike(@Param("title") String title);


}
