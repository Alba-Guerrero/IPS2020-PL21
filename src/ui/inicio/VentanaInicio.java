package ui.inicio;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;

import logica.servicios.ParserBaseDeDatos;
import ui.admin.PanelCitas;
import ui.auditor.VerAccionesAdmin;
import ui.medico.VentanaMedicoCita;
import ui.medico.VerCitasMedico;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class VentanaInicio extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ParserBaseDeDatos pbd=new ParserBaseDeDatos();
	private JPanel panel_2;
	private JLabel lblHospitalPsquitricoUniversidad;
	private JLabel lblNewLabel;
	private JLabel lblCdigoDeEmpleado;
	private JTextField txtCode;
	private JLabel lblContrasea;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_1;
	private JButton btnIniciarSesin;
	private JButton btnCancelar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaInicio frame = new VentanaInicio();
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaInicio() {
		setResizable(false);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		setTitle("Inicio de sesi\u00F3n");
		setBounds(100, 100, 1002, 566);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel_2_1());
	}
	
	
	
	/**
	 * M�todo para pasar a la ventana de ver citas
	 */
	protected void verCitas() {
		VentanaMedicoCita vmc =new VentanaMedicoCita(txtCode.getText());
		vmc.setLocationRelativeTo(null);
		vmc.setResizable(true);
		vmc.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		vmc.setVisible(true);
		
	
		
	}
	
	
	
	private void panelCita() {
		String codAdmin = txtCode.getText();
		VentanaAdministrador va;

			va = new VentanaAdministrador(codAdmin);
			va.setVisible(true);
			va.setLocationRelativeTo(null);
			va.setResizable(true);
			va.setModal(true);
		
		
	}
	private JPanel getPanel_2_1() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBounds(257, 17, 433, 472);
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setLayout(null);
			panel_2.add(getLblHospitalPsquitricoUniversidad());
			panel_2.add(getLblNewLabel());
			panel_2.add(getLblCdigoDeEmpleado());
			panel_2.add(getTextField_1());
			panel_2.add(getLblContrasea());
			panel_2.add(getPasswordField());
			panel_2.add(getLblNewLabel_1());
			panel_2.add(getBtnIniciarSesin());
			panel_2.add(getBtnCancelar());
		}
		return panel_2;
	}
	private JLabel getLblHospitalPsquitricoUniversidad() {
		if (lblHospitalPsquitricoUniversidad == null) {
			lblHospitalPsquitricoUniversidad = new JLabel("Hospital psiqui\u00E1trico \r\n");
			lblHospitalPsquitricoUniversidad.setFont(new Font("SansSerif", Font.BOLD, 28));
			lblHospitalPsquitricoUniversidad.setBounds(60, 6, 304, 77);
		}
		return lblHospitalPsquitricoUniversidad;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Universidad de Oviedo");
			lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
			lblNewLabel.setBounds(60, 68, 304, 21);
		}
		return lblNewLabel;
	}
	private JLabel getLblCdigoDeEmpleado() {
		if (lblCdigoDeEmpleado == null) {
			lblCdigoDeEmpleado = new JLabel("C\u00F3digo de empleado");
			lblCdigoDeEmpleado.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblCdigoDeEmpleado.setBounds(27, 289, 148, 24);
		}
		return lblCdigoDeEmpleado;
	}
	private JTextField getTextField_1() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(175, 288, 206, 28);
			txtCode.setColumns(10);
		}
		return txtCode;
	}
	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lblContrasea.setBounds(57, 333, 118, 16);
		}
		return lblContrasea;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(175, 325, 206, 28);
		}
		return passwordField;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			ImageIcon icon =new ImageIcon("C:/Users/Alba/git/IPS2020-PL21/resources/logop.jpg");
			lblNewLabel_1 = new JLabel("",icon,JLabel.CENTER);
			lblNewLabel_1.setBounds(146, 113, 140, 143);
		}
		return lblNewLabel_1;
	}
	private JButton getBtnIniciarSesin() {
		if (btnIniciarSesin == null) {
			btnIniciarSesin = new JButton("Iniciar sesi\u00F3n");
			btnIniciarSesin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					compruebaUsuario();
				}

				
				
				
				
			});
			btnIniciarSesin.setBounds(112, 417, 118, 31);
		}
		return btnIniciarSesin;
	}
	protected void abrirAuditor() {
		EscogerOpcionAuditor vaa = new EscogerOpcionAuditor();
		vaa.setVisible(true);
		vaa.setLocationRelativeTo(null);
		vaa.setResizable(true);
	}
	private void compruebaUsuario() {
		try {
			if(pbd.buscarMedicoCod(txtCode.getText())) {
				dispose();
				verCitas();}
			
			
			else if(pbd.buscarAdministrativo(txtCode.getText())) {
				dispose();
				panelCita();
			}
			
			else if("Auditor".equals(txtCode.getText())) {
				dispose();
				abrirAuditor();
			}
			else {
				txtCode.setText("");
				JOptionPane.showMessageDialog(null, "No se ha encontrado ningun Empleado con esos datos.Por favor vuelva a intentarlo");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(258, 417, 106, 31);
		}
		return btnCancelar;
	}
}
