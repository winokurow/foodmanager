package de.zottig.foodmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import de.zottig.foodmanager.dto.FoodDto;
import de.zottig.foodmanager.models.Food;
import de.zottig.foodmanager.repository.FoodRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

	private final FoodRepository foodRepository;
	private final ModelMapper modelMapper;

	public List<FoodDto> findAll() {
		var food = (List<Food>) foodRepository.findAll();
		return food.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
	}

	private FoodDto convertToDto(Food entity) {
		return modelMapper.map(entity, FoodDto.class);
	}
}
