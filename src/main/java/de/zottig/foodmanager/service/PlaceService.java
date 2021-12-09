package de.zottig.foodmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.zottig.foodmanager.models.Place;
import de.zottig.foodmanager.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final PlaceRepository placeRepository;

	public List<Place> findAll() {
		return (List<Place>) placeRepository.findAll();
	}

	public Place insert(Place food) {
		return placeRepository.save(food);
	}

	public void deleteById(Long id) {
		placeRepository.deleteById(id);
	}
}
