package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Cita;
import logica.HistorialMedico;
import logica.servicios.ParserBaseDeDatos;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Rectangle;

public class MostrarHistorial extends JDialog {

	private JPanel contentPane;

	
	private HistorialMedico hm;
	private Cita cita;
	private ParserBaseDeDatos pbd = new ParserBaseDeDatos();
	private JTextField txtCausas;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MostrarHistorial(HistorialMedico hm,Cita cita) throws SQLException {
		setTitle("Historial m\u00E9dico");
		setBounds(new Rectangle(400, 600, 400, 400));
		
		this.hm = hm;
		this.cita=cita;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton button_1 = new JButton("Volver");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setActionCommand("Cancel");
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut);
		
		JLabel lblVacunas = new JLabel("Vacunas");
		panel_2.add(lblVacunas);
		
		JLabel lblEnfermedadesPrevias = new JLabel("Enfermedades previas");
		panel_2.add(lblEnfermedadesPrevias);
		
		JLabel lblCausas = new JLabel("Causas");
		panel_2.add(lblCausas);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_1);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_2);
		
		JComboBox cbVacunas = new JComboBox();
		cbVacunas.setEditable(false);
		
		List<String> nombreVacunas = new ArrayList<>();
		nombreVacunas = pbd.buscarEnfermPrevias(hm.getCodEnfermPrevia());
		for(int i = 0; i < nombreVacunas.size(); i++) {
			cbVacunas.insertItemAt(nombreVacunas.get(i), i);
		}
		
		
		panel_3.add(cbVacunas);
		
		JComboBox<String> cbEnfermPrevias = new JComboBox<String>();
		panel_3.add(cbEnfermPrevias);
		List<String> nombreEnfermedad = new ArrayList<>();
		nombreEnfermedad=pbd.buscarVacunas(hm.getCodVacuna());
		for(int i = 0; i < nombreEnfermedad.size(); i++) {
			cbEnfermPrevias.insertItemAt(nombreEnfermedad.get(i), i);
		}
		
		
		txtCausas = new JTextField();
		panel_3.add(txtCausas);
		txtCausas.setEditable(false);
		
		txtCausas.setColumns(10);
		String causas = pbd.verCausas(hm.getCodCausas());
		
		if(!causas.equals("")) {
			causas += " -" + cita.getDate() + " -" + cita.gethInicio();
			txtCausas.setText(causas);
		}
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_3);
		
	}

}
