/**
 * 
 */
package logica;

import java.sql.Date;
import java.sql.Time;


/**
 * @author Mar�a
 *
 */
public class AsignaVacuna {

	
	private String codVacuna; // Lo que me identifica la vacuna
	private String codEmpleado;
	private String codHistorial;
	
	private Date date;
	private Time time;
	
	
	
	
	/**
	 * Constructor para la clase AsignaVacuna
	 * 
	 * @param codVacuna
	 * @param codEmpleado
	 * @param codHistorial
	 * @param date
	 * @param time
	 */
	public AsignaVacuna(String codVacuna, String codEmpleado, String codHistorial, Date date, Time time) {
		this.codVacuna = codVacuna;
		this.codEmpleado = codEmpleado;
		this.codHistorial = codHistorial;
		this.date = date;
		this.time = time;
	}




	/**
	 * @return the codVacuna
	 */
	public String getCodVacuna() {
		return codVacuna;
	}




	/**
	 * @return the codEmpleado
	 */
	public String getCodEmpleado() {
		return codEmpleado;
	}




	/**
	 * @return the codHistorial
	 */
	public String getCodHistorial() {
		return codHistorial;
	}




	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}




	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}
	
	
	
	
	
}