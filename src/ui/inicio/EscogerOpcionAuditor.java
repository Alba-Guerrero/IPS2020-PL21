package ui.inicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.auditor.VerAccionesAdmin;
import ui.auditor.VerAccionesEmpleado;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class EscogerOpcionAuditor extends JDialog {

	private JPanel contentPane;
	private JButton btnAccionesAdmin;
	private JButton btnVerAccionEmpleado;
	private JPanel panel_2;
	private JButton btnCerrarSesin;
	private JLabel lblNewLabel;
	private JLabel label;


	/**
	 * Create the frame.
	 */
	public EscogerOpcionAuditor() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel_2(), BorderLayout.NORTH);
		contentPane.add(getBtnAccionesAdmin(), BorderLayout.WEST);
		contentPane.add(getBtnVerAccionEmpleado(), BorderLayout.EAST);
	}

	private JButton getBtnAccionesAdmin() {
		if (btnAccionesAdmin == null) {
			btnAccionesAdmin = new JButton("Ver acciones admin");
			btnAccionesAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirAccionesAdmin();
				}
			});
		}
		return btnAccionesAdmin;
	}
	protected void abrirAccionesAdmin() {
		VerAccionesAdmin vaa = new VerAccionesAdmin();
		vaa.setVisible(true);
		vaa.setLocationRelativeTo(null);
		vaa.setResizable(true);
		
	}

	private JButton getBtnVerAccionEmpleado() {
		if (btnVerAccionEmpleado == null) {
			btnVerAccionEmpleado = new JButton("Ver acciones empleado");
			btnVerAccionEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirAccionesEmpleado();
				}
			});
		}
		return btnVerAccionEmpleado;
	}

	protected void abrirAccionesEmpleado() {
		VerAccionesEmpleado vaa = new VerAccionesEmpleado();
		vaa.setVisible(true);
		vaa.setLocationRelativeTo(null);
		vaa.setResizable(true);
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(0, 3, 0, 0));
			panel_2.add(getLblNewLabel());
			panel_2.add(getLabel());
			panel_2.add(getBtnCerrarSesin());
		}
		return panel_2;
	}
	private JButton getBtnCerrarSesin() {
		if (btnCerrarSesin == null) {
			btnCerrarSesin = new JButton("Cerrar sesi\u00F3n");
			btnCerrarSesin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventanaInicio();
					dispose();
					
					
				}
			});
		}
		return btnCerrarSesin;
	}
	
	
	private void ventanaInicio() {
		VentanaInicio vmc =new VentanaInicio();
		vmc.setLocationRelativeTo(null);
		vmc.setVisible(true);
} 
	
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Bienvenido/a Auditor");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblNewLabel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
		}
		return label;
	}
}
