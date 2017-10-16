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
	private ArrayList<Bandera> banderas;
	
	public LogicaVp(PApplet _app, Table _table) {
		app = _app;
		table = _table;
		start = false;
		gameOver = false;
		dataSaved = false;
		correct = 0;
		ui = new Ui(app);
		patrones = new ArrayList<Patron>();
		banderas = new ArrayList<Bandera>();
		direccionesAvion();
		databaseFlags();
		ruta = 0;
	}
	
	public void paint() {
		if (start != true) {
			app.text("Pruebas Velocidad de procesamiento", app.width/2, app.height/2);
		} else {
			if(start == true && gameOver == false)
				paintFlags();
				/*paintPlane();
				paintPaths();*/
			if (gameOver == true) {
				ui.paint();
				if (ui.getDoneHere() == true) {
					saveData();
				}
			}
		}
	}
	
	public void paintPaths() {
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
	
	public void paintFlags() {
		/*
		 * AGREGA LAS BANDERAS, TODAS DE UN MISMO COLOR Y 
		 * UNA DE COLOR DISTINTO
		 */
		for (int i = 0; i < banderas.size(); i++) {
			app.noStroke();
			app.fill(banderas.get(i).getColor(), 255, 0);
			app.rect((app.width/2) - 400, (app.height/2), 100, 50);
			app.fill(banderas.get(i).getColor(), 255, 0);
			app.rect((app.width/2) - 200, (app.height/2), 100, 50);
			app.fill(banderas.get(i).getColor(), 255, 0);
			app.rect((app.width/2), (app.height/2), 100, 50);
			app.fill(banderas.get(i).getColor(), 255, 0);
			app.rect((app.width/2) + 200, (app.height/2), 100, 50);
			app.fill(0, banderas.get(i).getColor(), banderas.get(i).getColor2());
			app.rect((app.width/2) + 400, (app.height/2), 100, 50);
		}
	}
	
	public void databaseFlags() {
		/*
		 * AGREGAR MÁS CICLOS DE BANDERAS PARA MOSTRARLE A VERGARA
		 * TAMBIÉN FALTA AGREGAR OPCIONES PARA QUE EL USUARIO ESCOJA LA BANDERA
		 * QUE PERTENECE O NO AL CICLO MOSTRADO
		 */
		Bandera flagBase = new Bandera(app.random(0,255), app.random(0,255));
		
		banderas.add(flagBase);
		
		//CONDICIÓN DE INICIO
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
