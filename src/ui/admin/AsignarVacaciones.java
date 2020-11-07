package ui.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import logica.Cita;
import logica.Vacaciones;
import logica.empleados.Empleado;
import logica.servicios.ParserBaseDeDatos;

public class AsignarVacaciones extends JDialog{

	private JPanel contentPane;
	private JPanel panelSeleccionEmpleado;
	private JComboBox<Empleado> cmboBoxEmpleado;
	private JPanel panelJornada;
	private JDateChooser chooseDFin;
	private JPanel panelDia;
	private JLabel lblDiaInicio;
	private JLabel lblDiaFin;
	private JDateChooser chooseDInicio;
	private ParserBaseDeDatos pbd = new ParserBaseDeDatos();
	//private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<JToggleButton> diasSeleccionados = new ArrayList<JToggleButton>();
	private JButton btnAsignar;
	private JButton btnCancelar;
	
	List<Cita> citasBorrar;
	private String codAdmin;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AsignarVacaciones(String codAdmin) throws SQLException {
		this.codAdmin = codAdmin;
		citasBorrar = new ArrayList<Cita>();
		setTitle("Asignar Jornada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelSeleccionEmpleado());
		contentPane.add(getPanelJornada());
	}
	

	private JPanel getPanelSeleccionEmpleado() throws SQLException {
		if (panelSeleccionEmpleado == null) {
			panelSeleccionEmpleado = new JPanel();
			panelSeleccionEmpleado.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Seleccionar empleado:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelSeleccionEmpleado.setBounds(10, 11, 614, 101);
			panelSeleccionEmpleado.setLayout(null);
			panelSeleccionEmpleado.add(getCmboBoxEmpleado());
		}
		return panelSeleccionEmpleado;
	}
	private JComboBox<Empleado> getCmboBoxEmpleado() throws SQLException {
		if (cmboBoxEmpleado == null) {
			cmboBoxEmpleado = new JComboBox<Empleado>();
			cmboBoxEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						activarBoton();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			cmboBoxEmpleado.setBounds(10, 43, 482, 22);
			List<Empleado> empleados = pbd.buscarEmpleados();
			for (int i = 0; i < empleados.size(); i++) {
				cmboBoxEmpleado.insertItemAt(empleados.get(i), i);
			}
			//activarBoton();
			
		}
		return cmboBoxEmpleado;
	}
	private JPanel getPanelJornada() {
		if (panelJornada == null) {
			panelJornada = new JPanel();
			panelJornada.setBounds(0, 123, 614, 218);
			panelJornada.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Selecciona horarios:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelJornada.setLayout(null);
			panelJornada.add(getPanelDia());
			panelJornada.add(getBtnAsignar());
			panelJornada.add(getBtnCancelar());
		}
		return panelJornada;
	}
	



	private JDateChooser getchooseDFin() {
		if (chooseDFin == null) {
			chooseDFin = new JDateChooser();
//			chooseDFin.addFocusListener(new FocusAdapter() {
//				@Override
//				public void focusLost(FocusEvent e) {
//					try {
//						//activarBoton();
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			});
			
			
			chooseDFin.setBounds(364, 23, 178, 20);
			chooseDFin.setDateFormatString("yyyy-MM-dd");
			chooseDFin.setDate(new Date());
			

		}
		return chooseDFin;
	}
	private JPanel getPanelDia() {
		if (panelDia == null) {
			panelDia = new JPanel();
			panelDia.setLayout(null);
			panelDia.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Dia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelDia.setBounds(24, 26, 552, 71);
			panelDia.add(getLblDiaInicio());
			panelDia.add(getLblDiaFin());
			panelDia.add(getchooseDFin());
			panelDia.add(getChooseDInicio());
		}
		return panelDia;
	}
	private JLabel getLblDiaInicio() {
		if (lblDiaInicio == null) {
			lblDiaInicio = new JLabel("Dia inicio:");
			lblDiaInicio.setBounds(10, 29, 60, 14);
		}
		return lblDiaInicio;
	}
	private JLabel getLblDiaFin() {
		if (lblDiaFin == null) {
			lblDiaFin = new JLabel("Dia fin:");
			lblDiaFin.setBounds(302, 26, 67, 14);
		}
		return lblDiaFin;
	}
	private JDateChooser getChooseDInicio() {
		if (chooseDInicio == null) {
			chooseDInicio = new JDateChooser();
//			chooseDInicio.addFocusListener(new FocusAdapter() {
//				@Override
//				public void focusLost(FocusEvent e) {
//					try {
//						activarBoton();
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			});
			
			
			chooseDInicio.setBounds(80, 23, 169, 20);
			chooseDInicio.setDateFormatString("yyyy-MM-dd");
			chooseDInicio.setDate(new Date());
		}
		return chooseDInicio;
	}
	

	private boolean comprobarEmpleadoSelected() throws SQLException {
		return getCmboBoxEmpleado().getSelectedItem() instanceof Empleado;
		
	}
	


	private String selectDates() {
		//List<JToggleButton> dias = (List<JToggleButton>) buttonGroup.getElements();
		String cadenaDias = "";
		for(int i=0; i < diasSeleccionados.size()-1 ;i++) {
			cadenaDias += diasSeleccionados.get(i).getText() + ",";
		}
		cadenaDias += diasSeleccionados.get(diasSeleccionados.size()-1).getText();
		return cadenaDias;
	}
	
	private String getSelectedEmpleadoCodigo() throws SQLException {
		if(getCmboBoxEmpleado().getSelectedIndex()!=-1) {
			Empleado e = (Empleado) getCmboBoxEmpleado().getSelectedItem();
			return e.getCodeEmpleado();
		}
		return "";
	}
	
	private void activarBoton() throws SQLException {
		if(comprobarEmpleadoSelected()) {
			if(!getBtnAsignar().isEnabled())
				getBtnAsignar().setEnabled(true);
		}
	}
	
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(comprobarEmpleadoSelected() && comprobarFechas() <= 0) {
							System.out.print("funciona");
							if(comprobarCitas()) {
								preguntarVacaciones();
							}
							else {
								asignarVacaciones();
								dispose();
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		}
			});
			btnAsignar.setBounds(346, 111, 89, 23);
		}
		return btnAsignar;
	}
	
	protected void preguntarVacaciones() {
		ConfirmarVacaciones cv = new ConfirmarVacaciones(this);
		cv.setVisible(true);
		cv.setLocationRelativeTo(null);
		cv.setResizable(true);
	}


	protected void asignarVacaciones() throws SQLException {
		Date d1 = getChooseDInicio().getDate();
		Date d2 = getchooseDFin().getDate();
		String codEmpleado = getSelectedEmpleadoCodigo();
		Vacaciones vacaciones = new Vacaciones(codEmpleado, codAdmin, d1, d2);
				
		//pbd.asignarVacaciones(vacaciones);
	}
	
	



	
	// si sale true es que todo bien
		private boolean comprobarCitas() throws SQLException {
				boolean devolver = false;
				String codEmpleado =getSelectedEmpleadoCodigo1();
				List<Cita> citas = pbd.devolvercitasMedico(codEmpleado);
				for(int i=0; i<citas.size(); i++) {
					Date dateCita = citas.get(i).getDate();
					if(comprobarFechasCitas(dateCita)) {
						devolver = true;
						citasBorrar.add(citas.get(i));
					}
				}
			return devolver;
		}
		

		
		
	private boolean comprobarFechasCitas(Date date) {
			if(comprobarFechasCitasInicio(date) <= 0 && comprobarFechasCitasFinal(date) <= 0) {
				return true;
			}
			return false;
		}


	private int comprobarFechasCitasInicio(Date date) {
		return getChooseDInicio().getDate().compareTo(date);
	}
	
	private int comprobarFechasCitasFinal(Date date) {
		return date.compareTo(getchooseDFin().getDate());
	}	
		
	private int comprobarFechas() {
		return getChooseDInicio().getDate().compareTo(getchooseDFin().getDate());
	}


	private String getSelectedEmpleadoCodigo1() throws SQLException {
		if(getCmboBoxEmpleado().getSelectedIndex()!=-1) {
			Empleado e = (Empleado) getCmboBoxEmpleado().getSelectedItem();
			return e.getCodeEmpleado();
		}
		return "";
	}


	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(453, 111, 89, 23);
		}
		return btnCancelar;
	}
	
	public void borrarCitas() {
		
	}
}
