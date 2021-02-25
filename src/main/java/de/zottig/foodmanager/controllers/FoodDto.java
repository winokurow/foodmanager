package de.zottig.foodmanager.controllers;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FoodDto {

	private Long id;

	private String name;

	private Long placeId;

	private String placeName;

	private LocalDate validDate;

	private LocalDate alarmDate;
}
