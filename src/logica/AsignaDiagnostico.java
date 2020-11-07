/**
 * 
 */
package logica;

import java.sql.Date;
import java.sql.Time;

/**
 * @author María
 *
 */
public class AsignaDiagnostico {

	
	private String nHistorial; // El número de historial del paciente a quien le hemos asignado el diagnostico
	private String nDiagnostico; // El identificador del diagnostico
	private String codMedico;
	private Date fecha;
	private Time hora;
	
	
	

	/**
	 * Constructor para la clase Asigna Diagnostico
	 * 
	 * @param nHistorial
	 * @param nDiagnostico
	 * @param codMedico
	 * @param fecha
	 * @param hora
	 */
	public AsignaDiagnostico(String nHistorial, String nDiagnostico, String codMedico, Date fecha, Time hora) {
		this.nHistorial = nHistorial;
		this.nDiagnostico = nDiagnostico;
		this.codMedico = codMedico;
		this.fecha = fecha;
		this.hora = hora;
	}	



	/**
	 * @return the nHistorial
	 */
	public String getnHistorial() {
		return nHistorial;
	}



	/**
	 * @return the nDiagnostico
	 */
	public String getnDiagnostico() {
		return nDiagnostico;
	}



	/**
	 * @return the codMedico
	 */
	public String getCodMedico() {
		return codMedico;
	}



	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}



	/**
	 * @return the hora
	 */
	public Time getHora() {
		return hora;
	}
	
	
	
	
}
