package velprocesamiento;

import processing.core.PApplet;
import processing.core.PVector;

public class Patron {

	private int contador;
	private int r, g, b;
	private PApplet app;
	private PVector pos;
	private PVector vel;
	private PVector ace;
	private float topS = 5f;

	public Patron(PApplet app, int x, int y) {
		this.app = app;
		contador = (int) app.random(6);
		pos = new PVector(x, y);
		vel = new PVector(0, 0);
		ace = new PVector(0, 0.01f);
		topS = 3;
		colores();
	}

	public void pintar() {
		app.fill(r, g, b);
		app.ellipse(pos.x, pos.y, 20, 20);
		app.noFill();
	}

	public void mover() {
		vel.add(ace);
		vel.limit(topS);
		pos.add(vel);
	}

	public void right() {
		vel.add(new PVector(2, 0));
	}

	public void left() {
		vel.add(new PVector(2, 0));
	}

	private void colores() {
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

	public int getX() {
		return (int) pos.x;
	}

	public int getY() {
		return (int) pos.y;
	}

	public int getSpeed() {
		return (int) topS;
	}

	public void setSpeed(float speed) {
		this.topS = speed;
	}
}
