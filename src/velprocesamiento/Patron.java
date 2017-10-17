package velprocesamiento;

import processing.core.PApplet;

public class Patron {

	private int contador;
	private int r, g, b;
	private PApplet app;

	public Patron(PApplet app) {
		this.app = app;
		contador = (int) app.random(6);

		switch (contador) {
		case 0: // RED
			r = 255;
			g = 0;
			b = 0;

			break;

		case 1: // GREEN
			r = 0;
			g = 255;
			b = 0;

			break;

		case 2: // BLUE
			r = 0;
			g = 0;
			b = 255;

			break;

		case 3: // YELLOW
			r = 255;
			g = 255;
			b = 0;

			break;

		case 4: // ORANGE
			r = 255;
			g = 170;
			b = 0;

			break;

		case 5: // PURPLE
			r = 255;
			g = 0;
			b = 255;

			break;

		}
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

}
