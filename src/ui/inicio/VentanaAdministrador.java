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

public class VentanaAdministrador extends JDialog {

	private JPanel contentPane;
	private JPanel pnSur;
	private JPanel pnCentro;
	private JButton btnCancelar;
	private JButton btnJornada;
	private JPanel panel1;
	private JButton btnAsignarCitas;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel7;
	private JPanel panel9;
	private JPanel panel10;
	private JPanel panel11;
	private JPanel panel12;
	private JPanel panel6;
	private JPanel panel8;
	private JLabel lblSelecciona;
	private JPanel panel8_1;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnNewButton;

	private String codAdmin;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btnNewButton_1;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JButton btnAadirPaciente;
	private JButton btnCrearEquipo;

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
			pnCentro.setLayout(new GridLayout(0, 11, 0, 0));
			pnCentro.add(getPanel1());
			pnCentro.add(getPanel2());
			pnCentro.add(getPanel3());
			pnCentro.add(getPanel4());
			pnCentro.add(getPanel5());
			pnCentro.add(getPanel6());
			pnCentro.add(getPanel7());
			pnCentro.add(getPanel_4());
			pnCentro.add(getPanel_5());
			pnCentro.add(getPanel8());
			pnCentro.add(getPanel_6());
			pnCentro.add(getPanel_7());
			pnCentro.add(getBtnNewButton_1_1());
			pnCentro.add(getPanel9());
			pnCentro.add(getBtnJornada());
			pnCentro.add(getPanel10());
			pnCentro.add(getBtnNewButton());
			pnCentro.add(getPanel11());
			pnCentro.add(getBtnAsignarCitas());
			pnCentro.add(getPanel8_1());
			pnCentro.add(getBtnAadirPaciente());
			pnCentro.add(getPanel());
			pnCentro.add(getPanel_1());
			pnCentro.add(getPanel_8());
			pnCentro.add(getPanel_2());
			pnCentro.add(getPanel_3());
			pnCentro.add(getPanel12());
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
			btnJornada = new JButton("Asignar jornada");
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


	private JPanel getPanel1() {
		if (panel1 == null) {
			panel1 = new JPanel();
			panel1.setBackground(SystemColor.controlHighlight);
		}
		return panel1;
	}
	private JButton getBtnAsignarCitas() {
		if (btnAsignarCitas == null) {
			btnAsignarCitas = new JButton("Asignar Citas");
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


	private JPanel getPanel2() {
		if (panel2 == null) {
			panel2 = new JPanel();
			panel2.setBackground(SystemColor.controlHighlight);
		}
		return panel2;
	}


	private JPanel getPanel3() {
		if (panel3 == null) {
			panel3 = new JPanel();
			panel3.setBackground(SystemColor.controlHighlight);
		}
		return panel3;
	}
	private JPanel getPanel4() {
		if (panel4 == null) {
			panel4 = new JPanel();
			panel4.setBackground(SystemColor.controlHighlight);
		}
		return panel4;
	}
	private JPanel getPanel5() {
		if (panel5 == null) {
			panel5 = new JPanel();
			panel5.setBackground(SystemColor.controlHighlight);
		}
		return panel5;
	}
	private JPanel getPanel7() {
		if (panel7 == null) {
			panel7 = new JPanel();
			panel7.setBackground(SystemColor.controlHighlight);
		}
		return panel7;
	}
	private JPanel getPanel9() {
		if (panel9 == null) {
			panel9 = new JPanel();
			panel9.setBackground(SystemColor.controlHighlight);
		}
		return panel9;
	}
	private JPanel getPanel10() {
		if (panel10 == null) {
			panel10 = new JPanel();
			panel10.setBackground(SystemColor.controlHighlight);
		}
		return panel10;
	}
	private JPanel getPanel11() {
		if (panel11 == null) {
			panel11 = new JPanel();
			panel11.setBackground(SystemColor.controlHighlight);
		}
		return panel11;
	}
	private JPanel getPanel12() {
		if (panel12 == null) {
			panel12 = new JPanel();
			panel12.setBackground(SystemColor.controlHighlight);
			panel12.add(getBtnCrearEquipo());
		}
		return panel12;
	}
	private JPanel getPanel6() {
		if (panel6 == null) {
			panel6 = new JPanel();
			panel6.setBackground(SystemColor.controlHighlight);
		}
		return panel6;
	}
	private JPanel getPanel8() {
		if (panel8 == null) {
			panel8 = new JPanel();
			panel8.setBackground(SystemColor.controlHighlight);
		}
		return panel8;
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
	 * M�todo para pasar a la ventana de la jornada laboral
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
	 * M�todo para pasar a la ventana de asignar citas
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
	private JPanel getPanel8_1() {
		if (panel8_1 == null) {
			panel8_1 = new JPanel();
			panel8_1.setBackground(SystemColor.controlHighlight);
		}
		return panel8_1;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(SystemColor.controlHighlight);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(SystemColor.controlHighlight);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(SystemColor.controlHighlight);
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(SystemColor.controlHighlight);
		}
		return panel_3;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Calendario citas");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					verCalendarioCitas();
				}
			});
		}
		return btnNewButton;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(SystemColor.controlHighlight);
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(SystemColor.controlHighlight);
		}
		return panel_5;
	}
	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Dar vacaciones");
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

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(SystemColor.controlHighlight);
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setBackground(SystemColor.controlHighlight);
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setBackground(SystemColor.controlHighlight);
		}
		return panel_8;
	}
	private JButton getBtnAadirPaciente() {
		if (btnAadirPaciente == null) {
			btnAadirPaciente = new JButton("A\u00F1adir Paciente");
			btnAadirPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					a�dirPaciente();
					
				}
			});
		}
		return btnAadirPaciente;
	}
	
protected void a�dirPaciente() {
		
		CrearPaciente cp= new CrearPaciente();
cp.setLocationRelativeTo(null);
		cp.setResizable(true);
		cp.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		cp.setVisible(true);
		
	}
	private JButton getBtnCrearEquipo() {
		if (btnCrearEquipo == null) {
			btnCrearEquipo = new JButton("CrearEquipo");
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
}
