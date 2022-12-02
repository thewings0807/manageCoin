package com.demo.coin.repository;

import com.demo.coin.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {
    Optional<Coin> findByCode(String code);
}
