package de.zottig.foodmanager.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "food")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotBlank
	@Size(max = 50)
	private String name;

	@Size(max = 300)
	private String description;

	@NotBlank
	@Size(max = 300)
	private String units;

	@OneToMany(mappedBy = "food", fetch = FetchType.LAZY)
	private Set<FoodStock> foodStock;

	public Food() {
	}
}
