package de.zottig.foodmanager.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FoodDto {

	private Long id;

	private String name;

	private String quantity;

	private String description;

	private Long placeId;

	private String placeName;

	private LocalDate validDate;

	private LocalDate alarmDate;
}
