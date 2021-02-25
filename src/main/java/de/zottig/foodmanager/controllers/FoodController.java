package de.zottig.foodmanager.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.zottig.foodmanager.controllers.service.FoodService;
import de.zottig.foodmanager.models.Food;
import de.zottig.foodmanager.models.Place;
import de.zottig.foodmanager.repository.PlaceRepository;

@RestController
@RequestMapping("/api")
public class FoodController {

	FoodService foodService;
	PlaceRepository placeRepository;
	private ModelMapper modelMapper;

	FoodController(FoodService foodService, PlaceRepository placeRepository, ModelMapper modelMapper) {
		this.foodService = foodService;
		this.placeRepository = placeRepository;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/food")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<FoodDto> getFood() {
		List<Food> places = foodService.findAll();
		return places.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
	}

	@PostMapping("/food")
	public ResponseEntity<FoodDto> insertFood(@RequestBody FoodDto dto) {
		Food food = foodService.insertFood(convertToEntity(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(food.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	private FoodDto convertToDto(Food entity) {
		FoodDto foodDto = modelMapper.map(entity, FoodDto.class);
		foodDto.setPlaceId(entity.getPlace().getId());
		foodDto.setPlaceName(entity.getPlace().getName());
		return foodDto;
	}

	private Food convertToEntity(FoodDto dto) {
		Food entity = modelMapper.map(dto, Food.class);
		Place place = placeRepository.findById(dto.getPlaceId())
				.orElseThrow(() -> new PlaceNotFoundException(dto.getPlaceId()));
		entity.setPlace(place);

		return entity;
	}
}
