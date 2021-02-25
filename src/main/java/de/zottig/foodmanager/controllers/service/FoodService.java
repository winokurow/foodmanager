package de.zottig.foodmanager.controllers.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.zottig.foodmanager.models.Food;
import de.zottig.foodmanager.repository.FoodRepository;

@Service
public class FoodService {

	private FoodRepository foodRepository;

	public FoodService(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}

	public List<Food> findAll() {
		return (List<Food>) foodRepository.findAll();
	}

	public Food insertFood(Food food) {
		return foodRepository.save(food);
	}

	public void deleteById(String id) {
		foodRepository.deleteById(id);
	}
}
