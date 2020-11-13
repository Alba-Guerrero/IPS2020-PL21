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

public class EscogerOpcionAuditor extends JDialog {

	private JPanel contentPane;
	private JButton btnAccionesAdmin;
	private JButton btnVerAccionEmpleado;


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
}
