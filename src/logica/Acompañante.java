package logica;

public class Acompañante {

	private String codAcompañante;
	private String codPaciente;
	private String nombre;
	private String apellido;
	private int movil;
	private String email;
	
	
	public Acompañante(String codAcompañante, String codPaciente, String nombre, String apellido, int movil, String email) {
		super();
		this.codAcompañante = codAcompañante;
		this.codPaciente = codPaciente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.movil = movil;
		this.email=email;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCodAcompañante() {
		return codAcompañante;
	}


	public void setCodAcompañante(String codAcompañante) {
		this.codAcompañante = codAcompañante;
	}


	public String getCodPaciente() {
		return codPaciente;
	}


	public void setCodPaciente(String codPaciente) {
		this.codPaciente = codPaciente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getMovil() {
		return movil;
	}


	public void setMovil(int movil) {
		this.movil = movil;
	}
	
	
}
