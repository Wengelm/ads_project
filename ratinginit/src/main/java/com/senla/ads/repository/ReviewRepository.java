package com.senla.ads.repository;

import com.senla.ads.entity.Review;
import com.senla.ads.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT rw FROM Review rw  WHERE rw.onUser.id = :id")
    public List<Review> getByReviewerUser(@Param("id") Long id);
}
