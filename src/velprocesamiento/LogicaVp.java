package velprocesamiento;

import java.util.ArrayList;
import java.util.LinkedList;

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
	private LinkedList<Patron> patrones;
	private ArrayList<Bandera> banderas;
	private Cronometro crono;
	private boolean isReleased;
	private Patron first;
	
	
	public LogicaVp(PApplet _app, Table _table) {
		app = _app;
		table = _table;
		start = false;
		gameOver = false;
		dataSaved = false;
		correct = 0;
		ui = new Ui(app);
		patrones = new LinkedList<Patron>();
		banderas = new ArrayList<Bandera>();
		// createPaths();
		// direccionesAvion();
		databaseFlags();
		ruta = 0;
		crono = new Cronometro(app);
		crono.setCorriendo(true);
		first = new Patron(app, app.width/2, -50);
		patrones.add(first);
	}

	public void paint() {
		if (start != true) {
			app.text("Pruebas Velocidad de procesamiento", app.width / 2, app.height / 2);
		} else {
			if (start == true && gameOver == false) {
				// paintFlags();
				firstGame();
				app.textSize(20);
				app.fill(0);
				app.text("puntaje: " + correct, 150, 150);
				app.noFill();
			}

			System.out.println("tiempo: " + crono.getSec());
			if (gameOver == true) {
				ui.paint();
				if (ui.getDoneHere() == true) {
					saveData();
				}
			}
		}
	}
	
	

	/*
	 * private void validacion(int mX, int mY) { app.loadPixels(); for (int x =
	 * 0; x < app.width; x++) { for (int y = 0; y < app.height; y++) { int
	 * mouseP = mX + mY * app.width; // PIXEL QUE CORREXPONDE A LA // POS DEL
	 * MOUSE // VALORES DE COLOR DEL PIXEL DEL ARRAY int r = colorPlane.getR();
	 * int g = colorPlane.getG(); int b = colorPlane.getB();
	 * 
	 * // VALORES DE COLOR DEL PIXEL QUE CORRESPONDE A LA POS DEL MOUSE int rM =
	 * (int) app.red(app.pixels[mouseP]); int gM = (int)
	 * app.green(app.pixels[mouseP]); int bM = (int)
	 * app.blue(app.pixels[mouseP]); // VALIDACIÓN DE QUE LOS COLORES SEAN LOS
	 * MISMOS
	 * 
	 * if ((r == rM) && (g == gM) && (b == bM)) { restartPath(); correct += 1;
	 * isReleased = false; return; } } } app.updatePixels(); }
	 * 
	 * private void restartPath() { patrones.removeAll(patrones); createPaths();
	 * }
	 * 
	 * private void createPaths() { for (int i = 0; i < 7; i++) { Patron pTemp =
	 * new Patron(app); patrones.add(pTemp); } }
	 * 
	 * public void paintPaths() {
	 * 
	 * 
	 * app.fill(patrones.get(0).getR(), patrones.get(0).getG(),
	 * patrones.get(0).getB()); app.ellipse((app.width / 2) + 450, (app.height /
	 * 2) - 300, 50, 50);
	 * 
	 * app.fill(patrones.get(1).getR(), patrones.get(1).getG(),
	 * patrones.get(1).getB()); app.ellipse((app.width / 2) + 450, (app.height /
	 * 2) - 200, 50, 50);
	 * 
	 * app.fill(patrones.get(2).getR(), patrones.get(2).getG(),
	 * patrones.get(2).getB()); app.ellipse((app.width / 2) + 450, (app.height /
	 * 2) - 100, 50, 50);
	 * 
	 * app.fill(patrones.get(3).getR(), patrones.get(3).getG(),
	 * patrones.get(3).getB()); app.ellipse((app.width / 2) + 450, (app.height /
	 * 2), 50, 50);
	 * 
	 * app.fill(patrones.get(4).getR(), patrones.get(4).getG(),
	 * patrones.get(4).getB()); app.ellipse((app.width / 2) + 450, (app.height /
	 * 2) + 100, 50, 50);
	 * 
	 * app.fill(patrones.get(5).getR(), patrones.get(5).getG(),
	 * patrones.get(5).getB()); app.ellipse((app.width / 2) + 450, (app.height /
	 * 2) + 200, 50, 50);
	 * 
	 * app.fill(patrones.get(6).getR(), patrones.get(6).getG(),
	 * patrones.get(6).getB());
	 * app.ellipse((app.width/2)+450,(app.height/2)+300,50,50);
	 * 
	 * }
	 * 
	 * public void paintPlane() { app.fill(colorPlane.getR(), colorPlane.getG(),
	 * colorPlane.getB()); app.noStroke(); app.rect(app.width / 6, (app.height /
	 * 2), 50, 50); app.noFill(); app.stroke(0); }
	 * 
	 * public void direccionesAvion() {
	 * System.out.println("Create Paths for the Plane");
	 * 
	 * Patron avioncito = new Patron(app); patrones.add(avioncito); //
	 * -----------------------------------------------------------------------
	 * start = true; }
	 */

	
	private void firstGame(){
		for (int i = 0; i < patrones.size(); i++) {
			patrones.get(i).mover();
			patrones.get(i).pintar();
			Patron pTemp= patrones.getLast();
			if (pTemp.getY()>=350) {
				patrones.add(new Patron(app, app.width/2, -50));
			}
		}
		
	}
	private void paintFlags() {
		/*
		 * AGREGA LAS BANDERAS, TODAS DE UN MISMO COLOR Y UNA DE COLOR DISTINTO
		 */
		for (int i = 0; i < banderas.size(); i++) {
			app.noStroke();
			app.fill(banderas.get(i).getColor(), 255, 0);
			app.rect((app.width / 2) - 400, (app.height / 2), 100, 50);
			app.fill(banderas.get(i).getColor(), 255, 0);
			app.rect((app.width / 2) - 200, (app.height / 2), 100, 50);
			app.fill(banderas.get(i).getColor(), 255, 0);
			app.rect((app.width / 2), (app.height / 2), 100, 50);
			app.fill(banderas.get(i).getColor(), 255, 0);
			app.rect((app.width / 2) + 200, (app.height / 2), 100, 50);
			app.fill(0, banderas.get(i).getColor(), banderas.get(i).getColor2());
			app.rect((app.width / 2) + 400, (app.height / 2), 100, 50);
		}
	}
	
	public void key(){
		if(app.key == PApplet.RIGHT){
			patrones.getLast().right();
		}
		
		if (app.key == PApplet.LEFT) {
			patrones.getLast().left();
		}
	}

	public void databaseFlags() {
		/*
		 * AGREGAR MÁS CICLOS DE BANDERAS PARA MOSTRARLE A VERGARA TAMBIÉN FALTA
		 * AGREGAR OPCIONES PARA QUE EL USUARIO ESCOJA LA BANDERA QUE PERTENECE
		 * O NO AL CICLO MOSTRADO
		 */
		Bandera flagBase = new Bandera(app.random(0, 255), app.random(0, 255));

		banderas.add(flagBase);

		// CONDICIÓN DE INICIO
		start = true;
	}

	public void click() {

		// VALIDACIÓN DEL CLICK CORRECTO
		if (isReleased == true) {
		}
	}

	public void mReleased() {
		isReleased = true;
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
