package com.example.demo.repository;

import com.example.demo.entity.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BidRepository extends JpaRepository<BidEntity, Integer> {

    @Query("SELECT u FROM BidEntity u WHERE u.itemId = :itemId")
    List<BidEntity> findByItemID(int itemId);

    @Query("SELECT u FROM BidEntity u WHERE u.itemId = :itemId AND u.username = :username")
    List<BidEntity> findByItemIdAndUserName(int itemId, String username);
}
