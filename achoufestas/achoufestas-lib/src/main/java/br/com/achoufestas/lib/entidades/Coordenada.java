package br.com.achoufestas.lib.entidades;

import java.io.Serializable;

public class Coordenada implements Serializable {

	private double latitude, longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
