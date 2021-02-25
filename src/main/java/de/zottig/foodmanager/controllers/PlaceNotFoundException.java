package de.zottig.foodmanager.controllers;

public class PlaceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PlaceNotFoundException(Long id) {
		super("Could not find category " + id);
	}

}
