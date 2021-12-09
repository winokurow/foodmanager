package de.zottig.foodmanager.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FoodStockDto {

	private Long id;

	private String name;

	private Float quantity;

	private PlaceDto place;

	private FoodDto food;

	private LocalDate validDate;

	private LocalDate alarmDate;
}
