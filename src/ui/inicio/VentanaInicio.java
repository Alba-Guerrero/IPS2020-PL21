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

import logica.LecturaExcel;
import logica.servicios.ParserBaseDeDatos;
import ui.admin.PanelCitas;
import ui.auditor.VerAccionesAdmin;
import ui.medico.VentanaMedicoCita;
import ui.medico.VerCitasMedico;


public class VentanaInicio extends JFrame {
	/**
	 * 
	 */
	private static LecturaExcel lecturaExcel = new LecturaExcel();

	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JButton btnMedico;
	private JButton btnAdmin;
	private JLabel lblIniciaSesin;
	private JLabel lblIntroduceElCodigo;
	private JTextField textField;
	private static ParserBaseDeDatos pbd=new ParserBaseDeDatos();
	private JButton btnAuditor;
	private JButton btnGerente;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			if (!pbd.diagnosticosCargados()) { // Si no están cargados los diagnósticos se cargan
				lecturaExcel.cargarDiagnosticosCie10(); // Cargamos el cie10 de diagnosticos
				System.out.println("Se han cargado con éxito los diagnósticos del cie10");
			}
			else if (pbd.diagnosticosCargados()) { // Si ya estaban cargados te avisa
				System.out.println("Los diagnósticos del 10 ya estaban cargados");
			}
			
			
			
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
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		setTitle("Inicio de sesi\u00F3n");
		setBounds(100, 100, 951, 502);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel_1());
		getContentPane().add(getBtnMedico());
		getContentPane().add(getBtnAdmin());
		getContentPane().add(getLblIniciaSesin());
		getContentPane().add(getLblIntroduceElCodigo());
		getContentPane().add(getTextField());
		getContentPane().add(getBtnAuditor());
		getContentPane().add(getBtnGerente());
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBounds(0, 0, 10, 10);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0};
			gbl_panel_1.rowHeights = new int[]{0};
			gbl_panel_1.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
		}
		return panel_1;
	}
	private JButton getBtnMedico() {
		if (btnMedico == null) {
			btnMedico = new JButton("M\u00E9dico");
			btnMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						if(pbd.buscarMedicoCod(getTextField().getText())) {
						dispose();

						verCitas();
						}
						else {
							getTextField().setText("");
							JOptionPane.showMessageDialog(null, "No se ha encontrado ningun Médico con ese codigo.Por favor vuelva a intentarlo");
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
			btnMedico.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnMedico.setBounds(534, 220, 229, 77);
		}
		return btnMedico;
	}
	
	
	
	/**
	 * Método para pasar a la ventana de ver citas
	 */
	protected void verCitas() {
		VentanaMedicoCita vmc =new VentanaMedicoCita(textField.getText());
		vmc.setLocationRelativeTo(null);
		vmc.setResizable(true);
		vmc.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		vmc.setVisible(true);
		
	
		
	}
	
	private void panelMedico() {
		VentanaMedico vm = new VentanaMedico(getTextField().getText());
		vm.setLocationRelativeTo(null);
		vm.setResizable(true);
		vm.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		vm.setVisible(true);
	}
	private JButton getBtnAdmin() {
		if (btnAdmin == null) {
			btnAdmin = new JButton("Administrador");
			btnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(pbd.buscarAdministrativo(getTextField().getText())) {
						dispose();
						panelCita();
						}
						else 
							JOptionPane.showMessageDialog(null, "No se ha encontrado ningun administrativo con ese codigo.Por favor vuelva a intentarlo");
					} catch (HeadlessException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAdmin.setBounds(145, 220, 243, 79);
		}
		return btnAdmin;
	}
	
	
	private void panelCita() {
		String codAdmin = getTextField().getText();
		VentanaAdministrador va;

			va = new VentanaAdministrador(codAdmin);
			va.setVisible(true);
			va.setLocationRelativeTo(null);
			va.setResizable(true);
			va.setModal(true);
		
		
	}
	private JLabel getLblIniciaSesin() {
		if (lblIniciaSesin == null) {
			lblIniciaSesin = new JLabel("Inicia sesi\u00F3n:");
			lblIniciaSesin.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblIniciaSesin.setBounds(97, 58, 226, 48);
		}
		return lblIniciaSesin;
	}
	private JLabel getLblIntroduceElCodigo() {
		if (lblIntroduceElCodigo == null) {
			lblIntroduceElCodigo = new JLabel("Introduce el codigo:");
			lblIntroduceElCodigo.setBounds(111, 132, 114, 16);
		}
		return lblIntroduceElCodigo;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(237, 129, 229, 22);
			textField.setColumns(10);
			
		}
		return textField;
	}
	private JButton getBtnAuditor() {
		if (btnAuditor == null) {
			btnAuditor = new JButton("Auditor");
			btnAuditor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirAuditor();
				}
			});
			btnAuditor.setBounds(722, 378, 90, 28);
		}
		return btnAuditor;
	}

	protected void abrirAuditor() {
		EscogerOpcionAuditor vaa = new EscogerOpcionAuditor();
		vaa.setVisible(true);
		vaa.setLocationRelativeTo(null);
		vaa.setResizable(true);
	}
	private JButton getBtnGerente() {
		if (btnGerente == null) {
			btnGerente = new JButton("Gerente");
			btnGerente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnGerente.setBounds(606, 378, 90, 28);
		}
		return btnGerente;
	}
}
