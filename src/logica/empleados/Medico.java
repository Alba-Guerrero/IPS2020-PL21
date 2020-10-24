/**
 * 
 */
package logica.empleados;

import java.sql.Date;
import java.sql.Time;

/**
 * @author María
 *
 */
public class Medico extends Empleado{

	
	
	/**
	 * Constructor
	 * @param codeEmpleado
	 * @param pass
	 * @param hInicio
	 * @param hFin
	 * @param date
	 * @param date2
	 * @param dJornada
	 */
	public Medico(String codeEmpleado,String nombre,String apellido, String pass, Time hInicio, Time hFin, java.util.Date date, java.util.Date date2, String dJornada) {
		super(codeEmpleado, nombre,apellido,pass, hInicio, hFin, date, date2, dJornada);	
	}

	
	
	

	@Override
	public String toString() {
		return "Código de empleado: " + codeEmpleado +"		 " +"Dr/Dra " + nombre + apellido ;
	}
	
	public String getCodEmpleado() {
		return codeEmpleado;
	}
	
}
