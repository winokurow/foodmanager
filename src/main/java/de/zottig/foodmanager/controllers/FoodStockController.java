package de.zottig.foodmanager.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.zottig.foodmanager.dto.FoodStockDto;
import de.zottig.foodmanager.service.FoodStockService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FoodStockController {

	private final FoodStockService foodStockService;

	@GetMapping(value = "/food-stock", produces = "appication/json")
	@PreAuthorize("hasRole('USER')")
	public List<FoodStockDto> getFoodStock() {
		return foodStockService.findAll();

	}

	@PostMapping(value = "/food-stock", produces = "appication/json")
	public FoodStockDto insertFoodStock(@RequestBody FoodStockDto stock) {
		return foodStockService.insertFoodStock(stock);
	}

	@PutMapping(value = "/food-stock", produces = "appication/json")
	public FoodStockDto updateFoodStock(@RequestBody FoodStockDto stock) {
		return foodStockService.updateFoodStock(stock);
	}

	@DeleteMapping(value = "/food-stock/{id}", produces = "appication/json")
	public void deleteFoodStock(@PathVariable Long id) {
		foodStockService.deleteById(id);
	}
}
