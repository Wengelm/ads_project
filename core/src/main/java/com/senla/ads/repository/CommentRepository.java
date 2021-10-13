package com.senla.ads.repository;

import com.senla.ads.entity.Ad;
import com.senla.ads.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT comment FROM Comment comment LEFT JOIN FETCH comment.ad WHERE comment.ad.id = :id")
    public List<Ad> getCommentByAd(@Param("id") Long id);
}
