/**
 * 
 */
package logica;

/**
 * @author María
 *
 */
public class Vacuna {

	
	private String codVacuna;
	private String nombreVacuna;
	
	
	
	/**
	 * Constructor para la clase Vacuna
	 * 
	 * @param codVacuna
	 * @param codEmpleado
	 * @param nombreVacuna
	 */
	public Vacuna(String codVacuna, String nombreVacuna) {
		this.codVacuna = codVacuna;
		this.nombreVacuna = nombreVacuna;
	}



	/**
	 * @return the codVacuna
	 */
	public String getCodVacuna() {
		return codVacuna;
	}


	/**
	 * @return the nombreVacuna
	 */
	public String getNombreVacuna() {
		return nombreVacuna;
	}
}
