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
				paintPlane();
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
		/*AGREGA LAS 6 ELIPSES DE CAMINO A SEGUIR, SI ES DEL MISMO COLOR PUEDE DAR CLICK Y AVANZAR
		 * DE LO CONTRARIO SE LE PRESENTA OTRO PATRÓN
		 */
		for (int i = 0; i < patrones.size(); i++) {
			app.fill(patrones.get(i).getColor(), 255, 0);
			app.ellipse((app.width/2) + 450, (app.height/2) - 300, 50, 50);
			app.fill(patrones.get(i).getColor2() + 10, 255, 0);
			app.ellipse((app.width/2) + 450, (app.height/2) - 200, 50, 50);
			app.fill(patrones.get(i).getColor3() + 20, 255, 0);
			app.ellipse((app.width/2) + 450, (app.height/2) - 100, 50, 50);
			app.fill(patrones.get(i).getColor4() + 30, 255, 0);
			app.ellipse((app.width/2) + 450, (app.height/2), 50, 50);
			app.fill(patrones.get(i).getColor5() + 40, 255, 0);
			app.ellipse((app.width/2) + 450, (app.height/2) + 100, 50, 50);
			app.fill(patrones.get(i).getColor6() + 50, 255, 0);
			app.ellipse((app.width/2) + 450, (app.height/2) + 200, 50, 50);
			app.fill(patrones.get(i).getColor7() + 60, 255, 0);
			app.ellipse((app.width/2) + 450, (app.height/2) + 300, 50, 50);
		}
	}
	
	public void paintPlane() {
		app.fill(100, 255, 0);
		app.noStroke();
		app.rect( app.width/6, (app.height / 2), 50, 50);
	}
	
	public void direccionesAvion() {
		System.out.println("Create Paths for the Plane");
		/*---------------------------
		 * PATRÓN 1, agregado de manera toche :)
		 */
		Patron bola1 = new Patron(app.random(0, 255), app.random(0, 255), app.random(0, 255), app.random(0, 255), app.random(0, 255), app.random(0, 255),app.random(0, 255));
		patrones.add(bola1);
		//-----------------------------------------------------------------------
		start = true;
	}
	
	public void click() {
		
		//VALIDACIÓN DEL CLICK CORRECTO JAIME
		
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
