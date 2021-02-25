package de.zottig.foodmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.zottig.foodmanager.models.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
	List<Food> findAll();
}
