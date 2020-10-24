package ui.inicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.admin.PanelCitas;
import ui.admin.VentanaJornada;
import ui.admin.VerCitasAdmin;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class VentanaAdministrador extends JDialog {

	private JPanel contentPane;
	private JPanel pnSur;
	private JPanel pnCentro;
	private JButton btnCancelar;
	private JButton btnJornada;
	private JPanel panel1;
	private JButton btnAsignarCitas;
	private JPanel panel2;
	private JButton btnVerCitas;
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



	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		setTitle("VentanaAdministrador");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 932, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnSur(), BorderLayout.SOUTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
	}

	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			pnSur.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) pnSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnSur.add(getBtnCancelar());
		}
		return pnSur;
	}
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(Color.WHITE);
			pnCentro.setLayout(new GridLayout(0, 5, 0, 0));
			pnCentro.add(getPanel1());
			pnCentro.add(getPanel2());
			pnCentro.add(getPanel3());
			pnCentro.add(getPanel4());
			pnCentro.add(getPanel5());
			pnCentro.add(getBtnJornada());
			pnCentro.add(getPanel6());
			pnCentro.add(getBtnAsignarCitas());
			pnCentro.add(getPanel7());
			pnCentro.add(getBtnVerCitas());
			pnCentro.add(getPanel8());
			pnCentro.add(getPanel9());
			pnCentro.add(getPanel10());
			pnCentro.add(getPanel11());
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
			btnJornada = new JButton("Asignar Jornada Laboral");
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
			panel1.setBackground(Color.WHITE);
			panel1.add(getLblSelecciona());
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
			panel2.setBackground(Color.WHITE);
		}
		return panel2;
	}
	private JButton getBtnVerCitas() {
		if (btnVerCitas == null) {
			btnVerCitas = new JButton("Ver Citas");
			btnVerCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					verCitas();
					
				}
			});
		}
		return btnVerCitas;
	}


	private JPanel getPanel3() {
		if (panel3 == null) {
			panel3 = new JPanel();
			panel3.setBackground(Color.WHITE);
		}
		return panel3;
	}
	private JPanel getPanel4() {
		if (panel4 == null) {
			panel4 = new JPanel();
			panel4.setBackground(Color.WHITE);
		}
		return panel4;
	}
	private JPanel getPanel5() {
		if (panel5 == null) {
			panel5 = new JPanel();
			panel5.setBackground(Color.WHITE);
		}
		return panel5;
	}
	private JPanel getPanel7() {
		if (panel7 == null) {
			panel7 = new JPanel();
			panel7.setBackground(Color.WHITE);
		}
		return panel7;
	}
	private JPanel getPanel9() {
		if (panel9 == null) {
			panel9 = new JPanel();
			panel9.setBackground(Color.WHITE);
		}
		return panel9;
	}
	private JPanel getPanel10() {
		if (panel10 == null) {
			panel10 = new JPanel();
			panel10.setBackground(Color.WHITE);
		}
		return panel10;
	}
	private JPanel getPanel11() {
		if (panel11 == null) {
			panel11 = new JPanel();
			panel11.setBackground(Color.WHITE);
		}
		return panel11;
	}
	private JPanel getPanel12() {
		if (panel12 == null) {
			panel12 = new JPanel();
			panel12.setBackground(Color.WHITE);
		}
		return panel12;
	}
	private JPanel getPanel6() {
		if (panel6 == null) {
			panel6 = new JPanel();
			panel6.setBackground(Color.WHITE);
		}
		return panel6;
	}
	private JPanel getPanel8() {
		if (panel8 == null) {
			panel8 = new JPanel();
			panel8.setBackground(Color.WHITE);
		}
		return panel8;
	}
	private JLabel getLblSelecciona() {
		if (lblSelecciona == null) {
			lblSelecciona = new JLabel("Seleccione lo que desea hacer:");
		}
		return lblSelecciona;
	}
	
	
	/**
	 * Método para pasar a la ventana de la jornada laboral
	 * @throws SQLException 
	 */
	protected void asignarJornadaLaboral() throws SQLException {
		
		VentanaJornada vj = new VentanaJornada();
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
		
		PanelCitas pc = new PanelCitas();
		pc.setLocationRelativeTo(null);
		pc.setResizable(true);
		pc.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		pc.setVisible(true);
		
	}
	
	
	
	/**
	 * Método para pasar a la ventana de ver citas
	 */
	protected void verCitas() {
		
		VerCitasAdmin vca = new VerCitasAdmin();
		vca.setVisible(true);
		vca.setLocationRelativeTo(this);
		
	}
}
