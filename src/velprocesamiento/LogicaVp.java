package velprocesamiento;

import java.util.ArrayList;

import lenguaje.Ui;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class LogicaVp {
	
	private PApplet app;
	private Ui ui;
	private boolean start, gameOver, dataSaved;
	private Table table;
	public String tipoInteligencia = "Velocidad Procesamiento";
	private int correct, ruta;
	private ArrayList<Patron> patrones;
	
	public LogicaVp(PApplet _app, Table _table) {
		app = _app;
		table = _table;
		start = false;
		gameOver = false;
		dataSaved = false;
		correct = 0;
		ui = new Ui(app);
		patrones = new ArrayList<Patron>();
		direccionesAvion();
		ruta = 0;
	}
	
	public void paint() {
		if (start != true) {
			app.text("Pruebas Velocidad de procesamiento", app.width/2, app.height/2);
		} else {
			if(start == true && gameOver == false)
				paintPaths(ruta);
			if (gameOver == true) {
				ui.paint();
				if (ui.getDoneHere() == true) {
					saveData();
				}
			}
		}
	}
	
	public void paintPaths(int _bola) {
		app.fill(patrones.get(_bola).getColor());
		app.ellipse(20, 20, 100, 500);
	}
	
	public void direccionesAvion() {
		System.out.println("Create Paths for the Plane");
		Patron bola1 = new Patron(app.random(0, 255));
		Patron bola2 = new Patron(app.random(0, 255));
		Patron bola3 = new Patron(app.random(0, 255));
		Patron bola4 = new Patron(app.random(0, 255));
		Patron bola5 = new Patron(app.random(0, 255));
		
		patrones.add(bola1);
		patrones.add(bola2);
		patrones.add(bola3);
		patrones.add(bola4);
		patrones.add(bola5);
		
		start = true;
	}
	
	public void click() {
		
	}
	
	public void saveData() {
		TableRow newRow = table.addRow();
		newRow.setString("Tipo", tipoInteligencia);
		newRow.setInt("Puntaje", correct);
		newRow.setInt("Autopuntaje", ui.getAutoScore());
		newRow.setInt("Posicion", ui.getPosition());
		System.out.println("Saving CSV");
		app.saveTable(table, "data/new.csv");
		dataSaved = true;
	}
	
	public boolean getDataSaved() {
		return dataSaved;
	}
}
