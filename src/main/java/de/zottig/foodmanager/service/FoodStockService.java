package de.zottig.foodmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.zottig.foodmanager.dto.FoodStockDto;
import de.zottig.foodmanager.models.FoodStock;
import de.zottig.foodmanager.repository.FoodRepository;
import de.zottig.foodmanager.repository.FoodStockRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodStockService {

	private final FoodStockRepository foodStockRepository;
	private final FoodRepository foodRepository;
	private final ModelMapper modelMapper;

	public List<FoodStockDto> findAll() {
		var foodStock = foodStockRepository.findAll();
		return foodStock.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
	}

	public FoodStockDto insertFoodStock(FoodStockDto foodStock) {

		var foodStockEntity = convertToEntity(foodStock);

		if (foodStockEntity.getFood().getId() == null) {
			var food = foodRepository.findByName(foodStockEntity.getFood().getName()).orElseGet(() -> {
				return foodRepository.save(foodStockEntity.getFood());
			});
			foodStockEntity.setFood(food);
		}
		var savedEntity = foodStockRepository.save(foodStockEntity);
		return convertToDto(savedEntity);
	}

	public FoodStockDto updateFoodStock(FoodStockDto foodStock) {

		var foodStockEntity = convertToEntity(foodStock);

		if (foodStockEntity.getFood().getId() == null) {
			var food = foodRepository.findByName(foodStockEntity.getFood().getName()).orElseGet(() -> {
				return foodRepository.save(foodStockEntity.getFood());
			});
			foodStockEntity.setFood(food);
		} else {
			foodRepository.save(foodStockEntity.getFood());
		}

		var savedEntity = foodStockRepository.save(foodStockEntity);
		return convertToDto(savedEntity);
	}

	public void deleteById(Long id) {
		foodStockRepository.deleteById(id);
	}

	private FoodStockDto convertToDto(FoodStock entity) {
		FoodStockDto foodDto = modelMapper.map(entity, FoodStockDto.class);
		return foodDto;
	}

	private FoodStock convertToEntity(FoodStockDto dto) {
		FoodStock entity = modelMapper.map(dto, FoodStock.class);
		return entity;
	}
}
