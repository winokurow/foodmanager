package de.zottig.foodmanager.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "food_stock")
public class FoodStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "food_id", nullable = false)
	private Food food;

	@ManyToOne
	@JoinColumn(name = "place_id", nullable = false)
	private Place place;

	private Float quantity;

	@NotNull
	private LocalDate validDate;

	private LocalDate alarmDate;

	public FoodStock() {
	}
}
