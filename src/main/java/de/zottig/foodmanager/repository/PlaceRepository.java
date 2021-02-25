package de.zottig.foodmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.zottig.foodmanager.models.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
	List<Place> findAll();
}
