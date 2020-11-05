package ui.medico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Causas;
import logica.Cita;
import logica.HistorialMedico;
import logica.Paciente;
import logica.Preinscripcion;
import logica.servicios.ParserBaseDeDatos;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class ModificarMedicosNuevoCard extends JDialog {

	private JPanel contentPane;
	private JPanel panel;
	private JButton button;
	private JButton button_1;
	private JPanel panelCentro;
	private JTabbedPane panelPestañas;
	private JPanel panelCausas;
	private JPanel panelEnfermedPrevia;
	private JPanel panelVacunas;

	private ParserBaseDeDatos pbd=new ParserBaseDeDatos();
	private Cita cita; // El paciente del que estamos modificando la cita
	private Preinscripcion preinscripcion; // La preinscripcion
	private List<Preinscripcion> preinscripciones;
	private Paciente paciente;

	private Causas causa;
	private List<Causas> causas;
	private JPanel panel_1;
	private JPanel panIz;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblAcudi;
	private JPanel panDe;
	private JTextField textField;
	private JTextField textField_1;
	private JRadioButton rdbtnAcudio;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_2;
	private JLabel lblNombre;
	private JButton btnNewButton;
	private JButton btnAñadirNuevo;
	private JComboBox cbCausas;

	private List<String> nombresCausas;
	private ModificarMedicosNuevoCard mm;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ModificarMedicosNuevoCard(Paciente paciente, Cita cita) throws SQLException {
		mm = this;
		this.paciente = paciente;
		this.cita = cita;
		ponerCausas();


		preinscripciones = pbd.listarPrescripciones();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.add(getButton());
			panel.add(getButton_1());
		}
		return panel;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Guardar");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
		}
		return button;
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("Cancelar");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return button_1;
	}
	private JPanel getPanelCentro() throws SQLException {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new GridLayout(0, 1, 0, 0));
			panelCentro.add(getPanel_1());
			panelCentro.add(getPanelPestañas());
		}
		return panelCentro;
	}

	private JTabbedPane getPanelPestañas() throws SQLException {
		if (panelPestañas == null) {
			panelPestañas = new JTabbedPane(JTabbedPane.TOP);
			panelPestañas.addTab("Causas", null, getPanelCausas(), null);
			panelPestañas.addTab("Enferm previas", null, getPanelEnfermedPrevia(), null);
			panelPestañas.addTab("Vacunas", null, getPanelVacunas(), null);
		}
		return panelPestañas;
	}
	private JPanel getPanelCausas() throws SQLException{
		if (panelCausas == null) {
			panelCausas = new JPanel();
			panelCausas.setLayout(new BorderLayout(0, 0));
			panelCausas.add(getPanel_2(), BorderLayout.NORTH);
		}
		return panelCausas;
	}



	private JPanel getPanelEnfermedPrevia() throws SQLException{
		if (panelEnfermedPrevia == null) {
			panelEnfermedPrevia = new JPanel();
			panelEnfermedPrevia.setLayout(new BorderLayout(0, 0));
		}
		return panelEnfermedPrevia;
	}
	private JPanel getPanelVacunas() throws SQLException {
		if (panelVacunas == null) {
			panelVacunas = new JPanel();
			panelVacunas.setLayout(new BorderLayout(0, 0));
		}
		return panelVacunas;
	}



	// LOGICA

	public Causas getCausa() {
		return causa;
	}

	public void setCausa(Causas causa) {
		this.causa = causa;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 2, 0, 0));
			panel_1.add(getPanIz_1());
			panel_1.add(getPanDe_1());
		}
		return panel_1;
	}
	private JPanel getPanIz_1() {
		if (panIz == null) {
			panIz = new JPanel();
			panIz.setLayout(new GridLayout(0, 1, 0, 0));
			panIz.add(getPanel_5());
			panIz.add(getPanel_7());
			panIz.add(getLblAcudi_1());
		}
		return panIz;
	}
	private JLabel getLabel_4() {
		if (label == null) {
			label = new JLabel("Nombre:");
		}
		return label;
	}
	private JLabel getLabel_1_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Apellidos:");
		}
		return label_1;
	}
	private JLabel getLabel_2_1() {
		if (label_2 == null) {
			label_2 = new JLabel("Hora entrada:");
		}
		return label_2;
	}
	private JLabel getLabel_3_1() {
		if (label_3 == null) {
			label_3 = new JLabel("Hora salida:");
		}
		return label_3;
	}
	private JLabel getLblAcudi_1() {
		if (lblAcudi == null) {
			lblAcudi = new JLabel("Acudi\u00F3");
		}
		return lblAcudi;
	}
	private JPanel getPanDe_1() {
		if (panDe == null) {
			panDe = new JPanel();
			panDe.setLayout(new GridLayout(0, 1, 0, 0));
			panDe.add(getPanel_6());
			panDe.add(getPanel_8());
			panDe.add(getRdbtnAcudio_1());
		}
		return panDe;
	}
	private JTextField getTextField_2() {
		if (textField == null) {
			textField = new JTextField();
			textField.setText((String) null);
			textField.setEditable(false);
			textField.setColumns(10);
		}
		return textField;
	}
	private JTextField getTextField_1_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setText((String) null);
			textField_1.setEditable(false);
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JRadioButton getRdbtnAcudio_1() {
		if (rdbtnAcudio == null) {
			rdbtnAcudio = new JRadioButton("Acudio");
		}
		return rdbtnAcudio;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.add(getLabel_4());
			panel_5.add(getTextField_2());
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.add(getLabel_1_1());
			panel_6.add(getTextField_1_1());
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.add(getLabel_2_1());
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.add(getLabel_3_1());
		}
		return panel_8;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(0, 4, 0, 0));
			panel_2.add(getLabel_4_1());
			panel_2.add(getCbCausas());
			panel_2.add(getBtnNewButton());
			panel_2.add(getBtnAñadirNuevo());
		}
		return panel_2;
	}
	private JLabel getLabel_4_1() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
		}
		return lblNombre;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Seleccionar");
		}
		return btnNewButton;
	}
	private JButton getBtnAñadirNuevo() {
		if (btnAñadirNuevo == null) {
			btnAñadirNuevo = new JButton("A\u00F1adir nueva");
			btnAñadirNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AnadirCausas ac = new AnadirCausas(mm);
					ac.setLocationRelativeTo(null);
					ac.setResizable(true);
					ac.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
					ac.setVisible(true);
				}
			});
		}
		return btnAñadirNuevo;
	}
	private JComboBox<Causas> getCbCausas() {
		if (cbCausas == null) {
			cbCausas = new JComboBox<Causas>();
			rellenarCBCausas();
		}
		return cbCausas;
	}

	public void vaciarCBCausas() {
		cbCausas.removeAllItems();
	}

	public void rellenarCBCausas() {

		for (int i = 0; i < nombresCausas.size(); i++) {
			cbCausas.insertItemAt(nombresCausas.get(i), i);
		}
	}

	protected void modificarCausas() throws SQLException {
		String causas = (String) getCbCausas().getSelectedItem();
		Time hora =  cita.gethInicio();

		java.sql.Date horas = new java.sql.Date(hora.getTime());

		Time hour = new Time(horas.getTime());

		Date fecha = (Date) cita.getDate();

		java.sql.Date sDate = new java.sql.Date(fecha.getTime());


		if(!causas.equals("")) {
			Random r = new Random();
			String codcausa = "" + r.nextInt(300);
			pbd.actualizarCausas(codcausa,causas, sDate, hour, cita.getCodMed());
		}
		dispose();
		}

	public List<String> darCausas() {
		return nombresCausas;
	}

	public void ponerCausas() {
		nombresCausas = new ArrayList<>();
		nombresCausas.add("Rotura de tobillo derecho");
		nombresCausas.add("Rotura de rodilla derecha");
	}

	public void añadirCausa(String causa) {
		nombresCausas.add(causa);
	}



}