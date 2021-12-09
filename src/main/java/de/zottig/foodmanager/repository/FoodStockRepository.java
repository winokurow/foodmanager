package de.zottig.foodmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.zottig.foodmanager.models.FoodStock;

public interface FoodStockRepository extends JpaRepository<FoodStock, Long> {
	List<FoodStock> findAll();
}
