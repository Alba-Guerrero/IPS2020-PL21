/**
 * 
 */
package logica;

/**
 * @author María
 *
 */
public class HistorialMedico {

	
	
	private String historial;

	private String codVacuna;
	private String codEnfermPrevia;
	private String codCausas;
	private String codPreinscripcion;
	
	
	/**
	 * Constructor
	 * @param historial
	 */
	public HistorialMedico(String historial) {
		this.historial = historial;
	}
	
	public HistorialMedico(String historial, String codVacuna, String codEnfermPrevia) {
		this.historial = historial;
		this.codVacuna = codVacuna;
		this.codEnfermPrevia = codEnfermPrevia;
	}




	public HistorialMedico(String historial, String codVacuna, String codEnfermPrevia, String codCausas,
			String codPreinscripcion) {
		this.historial = historial;
		this.codVacuna = codVacuna;
		this.codEnfermPrevia = codEnfermPrevia;
		this.codCausas = codCausas;
		this.codPreinscripcion = codPreinscripcion;
	}






	public String getCodVacuna() {
		return codVacuna;
	}






	public void setCodVacuna(String codVacuna) {
		this.codVacuna = codVacuna;
	}






	public String getCodEnfermPrevia() {
		return codEnfermPrevia;
	}






	public void setCodEnfermPrevia(String codEnfermPrevia) {
		this.codEnfermPrevia = codEnfermPrevia;
	}






	public String getCodCausas() {
		return codCausas;
	}






	public void setCodCausas(String codCausas) {
		this.codCausas = codCausas;
	}






	public String getCodPreinscripcion() {
		return codPreinscripcion;
	}






	public void setCodPreinscripcion(String codPreinscripcion) {
		this.codPreinscripcion = codPreinscripcion;
	}






	public String getHistorial() {
		return historial;
	}




	public void setHistorial(String historial) {
		this.historial = historial;
	}	
	
	
}
