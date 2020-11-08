package ui.medico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Cita;
import logica.HistorialMedico;
import logica.Paciente;
import logica.servicios.ParserBaseDeDatos;
import ui.MostrarHistorial;

import java.awt.GridLayout;
import java.awt.JobAttributes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class InfoCitaMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private Paciente paciente;
	private Cita cita;
	private HistorialMedico historial;
	
	private ParserBaseDeDatos pbd=new ParserBaseDeDatos();

	/**
	 * Create the dialog.
	 */
	public InfoCitaMedico(Paciente paciente,Cita cita) {
		this.paciente = paciente;
		this.cita=cita;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				JLabel lblNombre = new JLabel("Nombre");
				panel.add(lblNombre);
			}
			{
				JLabel lblApellido = new JLabel("Apellido");
				panel.add(lblApellido);
			}
			{
				JLabel lblHistorialMdico = new JLabel("Historial m\u00E9dico");
				panel.add(lblHistorialMdico);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			{
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
				txtNombre.setText(paciente.getNombre());
			}
			{
				txtApellido = new JTextField();
				txtApellido.setEditable(false);
				panel.add(txtApellido);
				txtApellido.setColumns(10);
				txtApellido.setText(paciente.getApellido());
			}
			{
				JButton btnVerHistorial = new JButton("Ver historial");
				btnVerHistorial.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							abrirHistorial();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				panel.add(btnVerHistorial);
				
			}
			
			{
				JButton button = new JButton("+");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abrirModificarCita();
					}
				});
				panel.add(button);
			}
			
			
			
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
			{
				Component verticalStrut = Box.createVerticalStrut(20);
				panel.add(verticalStrut);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
protected void modificarCita() throws SQLException {
		
		cita = pbd.verCita(paciente.getCodePaciente());
		
		ModificarCitaMedico mc = new ModificarCitaMedico(paciente, cita);
		mc.setLocationRelativeTo(this);
		mc.setResizable(true);
		mc.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		mc.setVisible(true);

	}
	protected void abrirHistorial() throws SQLException {
		HistorialMedico hm = pbd.verHistorial(paciente.getHistorial());
		if(hm instanceof HistorialMedico) {
//		String nombreVacuna = pbd.verNombreVacuna(hm.getCodVacuna());
//		String nombreEnfermPrevia = pbd.verNombreEnfermedadPrevias(hm.getCodEnfermPrevia());
//		String causas = pbd.verCausas(hm.getCodCausas());
		
		MostrarHistorial mh = new MostrarHistorial(hm);
		mh.setLocationRelativeTo(null);
		mh.setResizable(true);
		mh.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		mh.setVisible(true);		
		
		}
		else
			JOptionPane.showConfirmDialog(null, "No existe historial médico");
	}

	protected void abrirModificarCita() {
		try {
			cita = pbd.verCita(paciente.getCodePaciente());
			ModificarMedicosNuevoCard mc = new ModificarMedicosNuevoCard(paciente, cita);
			mc.setLocationRelativeTo(this);
			mc.setResizable(true);
			mc.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
			mc.setVisible(true);
		}  catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}
	
}
