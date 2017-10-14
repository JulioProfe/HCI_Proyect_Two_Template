package velprocesamiento;

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
	private int correct;
	
	public LogicaVp(PApplet _app, Table _table) {
		app = _app;
		table = _table;
		start = false;
		gameOver = false;
		dataSaved = false;
		correct = 0;
		ui = new Ui(app);
	}
	
	public void paint() {
		if (start != true) {
			app.text("Pruebas Velocidad de procesamiento", app.width/2, app.height/2);
		} else {
			if(start == true && gameOver == false)
				
				//pintarPrueba
				
			if (gameOver == true) {
				ui.paint();
				if (ui.getDoneHere() == true) {
					saveData();
				}
			}
		}
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
