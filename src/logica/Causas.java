package logica;

import java.sql.Date;
import java.sql.Time;

public class Causas {
	
	private String codHistorial;
	private String codCausas;
	private String nombreCausas;
	private Date fecha;
	private Time hora;
	
	
	
	
	
	public Causas(String codHistorial, String codCausas, String nombreCausas, Date fecha, Time hora) {
		this.codHistorial = codHistorial;
		this.codCausas = codCausas;
		this.nombreCausas = nombreCausas;
		this.fecha = fecha;
		this.hora = hora;
	}
	public String getCodHistorial() {
		return codHistorial;
	}
	public void setCodHistorial(String codHistorial) {
		this.codHistorial = codHistorial;
	}
	public String getCodCausas() {
		return codCausas;
	}
	public void setCodCausas(String codCausas) {
		this.codCausas = codCausas;
	}
	public String getNombreVacuna() {
		return nombreCausas;
	}
	public void setNombreCausas(String nombreCausas) {
		this.nombreCausas = nombreCausas;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	
	
}
