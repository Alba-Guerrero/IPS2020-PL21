/**
 * 
 */
package logica.empleados;

import java.sql.Time;
import java.util.Date;

/**
 * @author María
 *
 */
public class Enfermero extends Empleado{

	
	/**
	 * Constructor
	 * @param codeEmpleado
	 * @param pass
	 * @param hInicio
	 * @param hFin
	 * @param dInicio
	 * @param dFin
	 * @param dJornada
	 */
	public Enfermero(String codeEmpleado, String pass, Time hInicio, Time hFin, Date dInicio, Date dFin, String dJornada) {
		super(codeEmpleado, pass, dJornada, dJornada, hInicio, hFin, dInicio, dFin, dJornada);	
	}

}
