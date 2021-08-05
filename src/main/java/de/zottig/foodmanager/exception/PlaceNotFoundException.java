package de.zottig.foodmanager.exception;

public class PlaceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlaceNotFoundException(Long id) {
		super("Could not find category " + id);
	}

}
