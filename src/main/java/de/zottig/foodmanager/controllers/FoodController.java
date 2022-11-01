package de.zottig.foodmanager.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.zottig.foodmanager.dto.FoodDto;
import de.zottig.foodmanager.service.FoodService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@RequiredArgsConstructor
public class FoodController {

	private final FoodService foodService;

	@GetMapping(value = "/food")
	@PreAuthorize("hasRole('USER')")
	public List<FoodDto> getFood() {
		return foodService.findAll();

	}

}
