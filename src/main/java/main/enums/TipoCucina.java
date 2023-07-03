package main.enums;

import lombok.Getter;

@Getter
public enum TipoCucina {
	cucina_italiana("Cucina italiana"),
	cucina_indiana("Cucina indiana"),
	cucina_di_pesce("Cucina di pesce"),
	steak_house("Stak house"),
	cucina_vegana("Cucina vegana"),
	cucina_vegetariana("Cucina vegetariana"),
	cucina_libanese("Cucina libanese");

	private final String label;

	TipoCucina(String label) {
		this.label = label;
	}

}
