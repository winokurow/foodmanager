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

import de.zottig.foodmanager.controllers.service.PlaceService;
import de.zottig.foodmanager.dto.PlaceDto;
import de.zottig.foodmanager.models.Place;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlaceController {

	PlaceService placeService;

	private ModelMapper modelMapper;

	@GetMapping("/places")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<PlaceDto> getPlaces() {
		List<Place> places = placeService.findAll();
		return places.stream().map(placeEntity -> convertToDto(placeEntity)).collect(Collectors.toList());
	}

	@PostMapping("/places")
	public ResponseEntity<PlaceDto> insertPlace(@RequestBody PlaceDto dto) {
		Place place = placeService.insert(convertToEntity(dto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(place.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	private PlaceDto convertToDto(Place entity) {
		return modelMapper.map(entity, PlaceDto.class);
	}

	private Place convertToEntity(PlaceDto dto) {
		return modelMapper.map(dto, Place.class);
	}
}
