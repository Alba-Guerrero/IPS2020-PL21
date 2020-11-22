package ui.inicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.servicios.ParserBaseDeDatos;
import ui.admin.AsignarVacaciones;
import ui.admin.CrearEquipo;
import ui.admin.CrearPaciente;
import ui.admin.PanelCitas;
import ui.admin.VentanaJornada;
import ui.admin.VentanaVerCita;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

public class VentanaAdministrador extends JDialog {

	private JPanel contentPane;
	private JPanel pnSur;
	private JPanel pnCentro;
	private JButton btnCancelar;
	private JButton btnJornada;
	private JButton btnAsignarCitas;
	private JPanel panelPaciente;
	private JLabel lblSelecciona;
	private JButton btnNewButton;

	private String codAdmin;
	private JButton btnNewButton_1;
	private JButton btnAadirPaciente;
	private JButton btnCrearEquipo;
	private JPanel panelEmpleados;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblOpcionesDePacientes;
	private JLabel lblOpcionesDeEmpleados;

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador(String codAdmin) {
		this.codAdmin = codAdmin;
		setTitle("VentanaAdministrador");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1225, 667);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnSur(), BorderLayout.SOUTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
		contentPane.add(getLblSelecciona(), BorderLayout.NORTH);
	}

	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			pnSur.setBackground(SystemColor.controlHighlight);
			FlowLayout flowLayout = (FlowLayout) pnSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnSur.add(getBtnCancelar());
		}
		return pnSur;
	}
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(SystemColor.controlHighlight);
			pnCentro.setLayout(new GridLayout(1, 2, 0, 0));
			pnCentro.add(getPanelPaciente());
			pnCentro.add(getPanel_9());
		}
		return pnCentro;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					dispose();
					
				}
			});
		}
		return btnCancelar;
	}
	private JButton getBtnJornada() {
		if (btnJornada == null) {
			btnJornada = new JButton("Asignar jornadas laborales");
			btnJornada.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnJornada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						asignarJornadaLaboral();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		}
		return btnJornada;
	}
	private JButton getBtnAsignarCitas() {
		if (btnAsignarCitas == null) {
			btnAsignarCitas = new JButton("Asignar Citas");
			btnAsignarCitas.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnAsignarCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						asignarCitas();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		return btnAsignarCitas;
	}
	private JPanel getPanelPaciente() {
		if (panelPaciente == null) {
			panelPaciente = new JPanel();
			panelPaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panelPaciente.setBackground(SystemColor.controlHighlight);
			panelPaciente.setLayout(new GridLayout(8, 1, 0, 0));
			panelPaciente.add(getPanel_10());
			panelPaciente.add(getBtnAadirPaciente());
			panelPaciente.add(getBtnAsignarCitas());
			panelPaciente.add(getBtnNewButton());
		}
		return panelPaciente;
	}
	private JLabel getLblSelecciona() {
		if (lblSelecciona == null) {
			lblSelecciona = new JLabel("Seleccione lo que desea hacer:");
			lblSelecciona.setBackground(Color.WHITE);
			lblSelecciona.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblSelecciona;
	}
	
	
	/**
	 * Método para pasar a la ventana de la jornada laboral
	 * @throws SQLException 
	 */
	protected void asignarJornadaLaboral() throws SQLException {
		
		VentanaJornada vj = new VentanaJornada(codAdmin);
		vj.setLocationRelativeTo(null);
		vj.setResizable(true);
		vj.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		vj.setVisible(true);
		
	}
	
	
	/**
	 * Método para pasar a la ventana de asignar citas
	 * @throws SQLException 
	 */
	protected void asignarCitas() throws SQLException {
		
		PanelCitas pc = new PanelCitas(codAdmin);
		pc.setLocationRelativeTo(null);
		pc.setResizable(true);
		pc.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		pc.setVisible(true);
		
	}
	
	
	

	
protected void verCalendarioCitas() {
		
		VentanaVerCita vca = new VentanaVerCita(codAdmin);
		vca.setVisible(true);
		vca.setLocationRelativeTo(this);
		
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Calendario citas");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					verCalendarioCitas();
				}
			});
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Dar vacaciones");
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						abrirVacaciones();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnNewButton_1;
	}
	protected void abrirVacaciones() throws SQLException {
		AsignarVacaciones av = new AsignarVacaciones(codAdmin);
		av.setVisible(true);
		av.setLocationRelativeTo(null);
		av.setResizable(true);
		av.setModal(true);
	}
	private JButton getBtnAadirPaciente() {
		if (btnAadirPaciente == null) {
			btnAadirPaciente = new JButton("A\u00F1adir Paciente");
			btnAadirPaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnAadirPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añdirPaciente();
					
				}
			});
		}
		return btnAadirPaciente;
	}
	
protected void añdirPaciente() {
		
		CrearPaciente cp= new CrearPaciente();
cp.setLocationRelativeTo(null);
		cp.setResizable(true);
		cp.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		cp.setVisible(true);
		
	}
	private JButton getBtnCrearEquipo() {
		if (btnCrearEquipo == null) {
			btnCrearEquipo = new JButton("Crear Equipo");
			btnCrearEquipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnCrearEquipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						abrirCrearEquipo();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnCrearEquipo;
	}
	
	private void abrirCrearEquipo() throws SQLException {
		CrearEquipo cp= new CrearEquipo();
		cp.setLocationRelativeTo(null);
		cp.setResizable(true);
		cp.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		cp.setVisible(true);
	}
	private JPanel getPanel_9() {
		if (panelEmpleados == null) {
			panelEmpleados = new JPanel();
			panelEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panelEmpleados.setBackground(SystemColor.controlHighlight);
			panelEmpleados.setLayout(new GridLayout(8, 1, 0, 0));
			panelEmpleados.add(getPanel_1_1());
			panelEmpleados.add(getBtnCrearEquipo());
			panelEmpleados.add(getBtnJornada());
			panelEmpleados.add(getBtnNewButton_1_1());
		}
	
		return panelEmpleados;
	}
	private JPanel getPanel_10() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(SystemColor.controlHighlight);
			panel.setLayout(null);
			panel.add(getLblOpcionesDePacientes());
		}
		return panel;
	}
	private JPanel getPanel_1_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(SystemColor.controlHighlight);
			panel_1.setLayout(null);
			panel_1.add(getLblOpcionesDeEmpleados());
		}
		return panel_1;
	}
	private JLabel getLblOpcionesDePacientes() {
		if (lblOpcionesDePacientes == null) {
			lblOpcionesDePacientes = new JLabel("Opciones de pacientes");
			lblOpcionesDePacientes.setForeground(SystemColor.windowBorder);
			lblOpcionesDePacientes.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblOpcionesDePacientes.setBounds(159, 13, 252, 43);
		}
		return lblOpcionesDePacientes;
	}
	private JLabel getLblOpcionesDeEmpleados() {
		if (lblOpcionesDeEmpleados == null) {
			lblOpcionesDeEmpleados = new JLabel("Opciones de empleados");
			lblOpcionesDeEmpleados.setBounds(166, 13, 250, 43);
			lblOpcionesDeEmpleados.setForeground(SystemColor.windowBorder);
			lblOpcionesDeEmpleados.setFont(new Font("Tahoma", Font.BOLD, 19));
		}
		return lblOpcionesDeEmpleados;
	}
}
