package ui.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class AñadirEmpleado extends JDialog {

	private JPanel contentPane;
	private JPanel panelDatos;
	private JLabel lblNombre;
	private JTextField txtFieldNombre;
	private JLabel lblApellidos;
	private JTextField txtFieldApellidos;
	private JLabel lblDni;
	private JTextField txtFieldDni;
	private JLabel lblCorreo;
	private JTextField txtFieldCorreo;
	private JPanel panelProfesion;
	private JRadioButton rdbtnMedico;
	private JRadioButton rdbtnEnfermero;
	private JLabel lblEspecialidad;
	private JComboBox cmboxEspecialidad;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnContinuar;
	private JButton btnCancelar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirEmpleado frame = new AñadirEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AñadirEmpleado() {
		setTitle("A\u00F1adir empleado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 730, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelDatos());
		contentPane.add(getPanelProfesion());
		contentPane.add(getBtnContinuar());
		contentPane.add(getBtnCancelar());
	}
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Datos personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatos.setBounds(10, 11, 694, 250);
			panelDatos.setLayout(new MigLayout("", "[58px][10px][39px][10px][121px][43px][58px][10px][240px]", "[20px][20px][20px][][20px][20px][][][][]"));
			panelDatos.add(getLblNombre(), "cell 0 0,growx,aligny center");
			panelDatos.add(getTxtFieldNombre(), "cell 2 0 3 1,growx,aligny top");
			panelDatos.add(getLblApellidos(), "cell 6 0,growx,aligny center");
			panelDatos.add(getTxtFieldApellidos(), "cell 8 0,growx,aligny top");
			panelDatos.add(getLblDni(), "cell 0 1,growx,aligny center");
			panelDatos.add(getTxtFieldDni(), "cell 2 1 3 1,alignx left,aligny top");
			panelDatos.add(getLblCorreo(), "cell 0 6,growx,aligny center");
			panelDatos.add(getTxtFieldCorreo(), "cell 2 6,alignx left,aligny top");
			panelDatos.add(getLblNewLabel(), "cell 0 7,growx,aligny center");
			panelDatos.add(getPasswordField(), "cell 2 7 3 1,growx,aligny top");
			panelDatos.add(getLblNewLabel_1(), "cell 0 8,growx,aligny center");
			panelDatos.add(getPasswordField_1(), "cell 2 8 3 1,growx,aligny top");
		}
		return panelDatos;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
		}
		return lblNombre;
	}
	private JTextField getTxtFieldNombre() {
		if (txtFieldNombre == null) {
			txtFieldNombre = new JTextField();
			txtFieldNombre.setColumns(10);
		}
		return txtFieldNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
		}
		return lblApellidos;
	}
	private JTextField getTxtFieldApellidos() {
		if (txtFieldApellidos == null) {
			txtFieldApellidos = new JTextField();
			txtFieldApellidos.setColumns(10);
		}
		return txtFieldApellidos;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
		}
		return lblDni;
	}
	private JTextField getTxtFieldDni() {
		if (txtFieldDni == null) {
			txtFieldDni = new JTextField();
			txtFieldDni.setColumns(10);
		}
		return txtFieldDni;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo elect\u00F3nico:");
		}
		return lblCorreo;
	}
	private JTextField getTxtFieldCorreo() {
		if (txtFieldCorreo == null) {
			txtFieldCorreo = new JTextField();
			txtFieldCorreo.setColumns(10);
		}
		return txtFieldCorreo;
	}
	private JPanel getPanelProfesion() {
		if (panelProfesion == null) {
			panelProfesion = new JPanel();
			panelProfesion.setBorder(new TitledBorder(null, "Profesi\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelProfesion.setBounds(10, 272, 694, 104);
			panelProfesion.setLayout(null);
			panelProfesion.add(getRdbtnMedico());
			panelProfesion.add(getRdbtnEnfermero());
			panelProfesion.add(getLblEspecialidad());
			panelProfesion.add(getCmboxEspecialidad());
		}
		return panelProfesion;
	}
	private JRadioButton getRdbtnMedico() {
		if (rdbtnMedico == null) {
			rdbtnMedico = new JRadioButton("M\u00E9dico");
			buttonGroup.add(rdbtnMedico);
			rdbtnMedico.setBounds(35, 44, 109, 23);
		}
		return rdbtnMedico;
	}
	private JRadioButton getRdbtnEnfermero() {
		if (rdbtnEnfermero == null) {
			rdbtnEnfermero = new JRadioButton("Enfermero");
			buttonGroup.add(rdbtnEnfermero);
			rdbtnEnfermero.setBounds(146, 44, 109, 23);
		}
		return rdbtnEnfermero;
	}
	private JLabel getLblEspecialidad() {
		if (lblEspecialidad == null) {
			lblEspecialidad = new JLabel("Especialidad:");
			lblEspecialidad.setBounds(289, 48, 84, 14);
		}
		return lblEspecialidad;
	}
	private JComboBox getCmboxEspecialidad() {
		if (cmboxEspecialidad == null) {
			cmboxEspecialidad = new JComboBox();
			cmboxEspecialidad.setBounds(383, 44, 141, 22);
		}
		return cmboxEspecialidad;
	}
	private JButton getBtnContinuar() {
		if (btnContinuar == null) {
			btnContinuar = new JButton("Continuar");
			btnContinuar.setBounds(601, 387, 89, 23);
		}
		return btnContinuar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(502, 387, 89, 23);
		}
		return btnCancelar;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Contrase\u00F1a:");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Repetir contrase\u00F1a:");
		}
		return lblNewLabel_1;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
		}
		return passwordField;
	}
	private JPasswordField getPasswordField_1() {
		if (passwordField_1 == null) {
			passwordField_1 = new JPasswordField();
		}
		return passwordField_1;
	}
}
