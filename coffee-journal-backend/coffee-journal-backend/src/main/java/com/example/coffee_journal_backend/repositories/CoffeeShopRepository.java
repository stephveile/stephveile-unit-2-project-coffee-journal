package com.example.coffee_journal_backend.repositories;

import com.example.coffee_journal_backend.models.CoffeeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeShopRepository extends JpaRepository<CoffeeShop, Integer> {
}
