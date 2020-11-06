/**
 * 
 */
package logica;

/**
 * @author María
 *
 */
public class AsignaDiagnostico {

	
	private String nHistorial; // El número de historial del paciente a quien le hemos asignado el diagnostico
	private String nDiagnostico; // El identificador del diagnostico
	
	
	
	/**
	 * Constructor para la clase AsignaDiagnóstico
	 * 
	 * @param nHistorial
	 * @param nDiagnostico
	 */
	public AsignaDiagnostico(String nHistorial, String nDiagnostico) {
		this.nHistorial = nHistorial;
		this.nDiagnostico = nDiagnostico;
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
	
	
	
	
}
