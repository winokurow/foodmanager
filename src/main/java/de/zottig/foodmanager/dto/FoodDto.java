package de.zottig.foodmanager.dto;

import lombok.Data;

@Data
public class FoodDto {

	private Long id;

	private String name;

	private String description;

	private String units;
}
