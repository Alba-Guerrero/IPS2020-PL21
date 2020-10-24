/**
 * 
 */
package logica;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Random;

import logica.servicios.ParserBaseDeDatos;

/**
 * @author L-21
 *
 */
public class Cita {

	private String CodCita;
	private String codPaciente;
	private String codMed;
	private Time hInicio;
	private Time hFin;
	private Date date;
	private String ubicacion;
	private boolean  acudio;
	private boolean urgente;
	private ParserBaseDeDatos pbd=new ParserBaseDeDatos();
	
	public Cita(String codPaciente, String codMed, Time hInicio, Time hFin,Date date, String ubicacion,
			boolean acudio, boolean urgente) throws SQLException {
		super();
		
		this.codPaciente = codPaciente;
		this.codMed = codMed;
		this.hInicio = hInicio;
		this.hFin = hFin;
		this.date=date;
		this.ubicacion = ubicacion;
		this.acudio	 = acudio;
		this.urgente = urgente;
		generateRandomCodCita();
	}
	
	public Cita(String codPaciente, String codMed, Time hInicio, Time hFin,Date date,String ubicacion, boolean selected) throws SQLException {
		this.codPaciente = codPaciente;
		this.codMed = codMed;
		this.urgente = selected;
		this.hInicio = hInicio;
		this.hFin = hFin;
		this.date=date;
		this.ubicacion=ubicacion;
		generateRandomCodCita();
	}

	public Cita (String codcita ,String codPaciente, String codMed, Time hInicio, Time hFin, java.sql.Date date,String ubicacion,boolean urgencia){
			this.CodCita=codcita;
			this.codPaciente = codPaciente;
			this.codMed = codMed;
			this.urgente = urgencia;
			this.hInicio = hInicio;
			this.hFin = hFin;
			this.date=date;
			this.ubicacion=ubicacion;
	}

	private void setCodCita(String codCita) {
		CodCita = codCita;
	}

	public void generateRandomCodCita() throws SQLException {
		String cod;
		do {
		 cod=new Random().nextInt()+"";
		}while(pbd.checkCode(cod));
		
		
		setCodCita(cod);
		
		
	}
	public String getCodPaciente() {
		return codPaciente;
	}

	public String getCodMed() {
		return codMed;
	}

	public Time gethInicio() {
		return hInicio;
	}

	public Time gethFin() {
		return hFin;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	

	public boolean isUrgente() {
		return urgente;
	}

	public String getCodCita() {
		return CodCita;
	}

	public Date getDate() {
		return date;
	}

	public boolean isAcudio() {
		return acudio;
	}

	public void setAcudio(boolean acudio) {
		this.acudio = acudio;
	}

	@Override
	public String toString(){
		return  CodCita +"Código paciente " + codPaciente + " fecha " + date ;
	}
	
	
	
	
	
}
