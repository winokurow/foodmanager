package de.zottig.foodmanager.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.zottig.foodmanager.models.Place;
import de.zottig.foodmanager.repository.PlaceRepository;

@RestController
@RequestMapping("/api")
public class PlaceController {

	PlaceRepository repository;

	private ModelMapper modelMapper;

	PlaceController(PlaceRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/places")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<PlaceDto> userAccess() {
		List<Place> places = repository.findAll();
		return places.stream().map(placeEntity -> convertToDto(placeEntity)).collect(Collectors.toList());
	}

	private PlaceDto convertToDto(Place entity) {
		return modelMapper.map(entity, PlaceDto.class);
	}
}
