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
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTree;
import javax.swing.JList;

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
	private JPanel pnPreinscripciones;
	private JPanel pnIzq;
	private JPanel pnDcha;
	private JPanel pnCentro;
	private JLabel lblNombrePreinscripcion;
	private JButton btnNueva;
	private JLabel lblNCantidad;
	private JLabel lblDuracion;
	private JLabel lblIntervalo;
	private JLabel lblInstrucciones;
	private JComboBox<String> cbNombre;
	private JPanel pnPreinscripcionNombre;
	private JButton btnSeleccionar;
	private JPanel pnVacío;
	private JSpinner spinnerCantidad;
	private JSpinner spinnerDuracion;
	private JSpinner spinnerIntervalo;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JPanel pnResumen;
	private JPanel pnBorrarPreinscripcion;
	private JButton btnBorrar;
	private JPanel pnResumenPreinscripciones;
	private JLabel lblResumenPreinscripciones;
	private JList list;
	private JScrollPane scrollPane_2;
	private JList listPreinscripciones;
	private JButton btnAadirPreinscripcin;

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
			panelPestañas.addTab("Preinscripciones", null, getPnPreinscripciones(), null);
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
			
		}
		return panelVacunas;
	}


	
	private JPanel getPnPreinscripciones() {
		if (pnPreinscripciones == null) {
			pnPreinscripciones = new JPanel();
			pnPreinscripciones.setLayout(new GridLayout(0, 3, 0, 0));
			pnPreinscripciones.add(getPnIzq());
			pnPreinscripciones.add(getPnCentro());
			pnPreinscripciones.add(getPnDcha());
			panelVacunas.setLayout(new BorderLayout(0, 0));
		}
		return pnPreinscripciones;
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



	
	private JPanel getPnIzq() {
		if (pnIzq == null) {
			pnIzq = new JPanel();
			pnIzq.setLayout(new GridLayout(7, 1, 0, 0));
			pnIzq.add(getBtnNueva());
			pnIzq.add(getLabel_4_2());
			pnIzq.add(getLabel_4_3());
			pnIzq.add(getLabel_4_4());
			pnIzq.add(getLabel_4_5());
			pnIzq.add(getLabel_4_6());
		}
		return pnIzq;
	}
	private JPanel getPnDcha() {
		if (pnDcha == null) {
			pnDcha = new JPanel();
			pnDcha.setLayout(new BorderLayout(0, 0));
			pnDcha.add(getPnResumen());
			pnDcha.add(getPnBorrarPreinscripcion());
			pnDcha.add(getPnResumen(), BorderLayout.CENTER);
			pnDcha.add(getPnBorrarPreinscripcion(), BorderLayout.SOUTH);
			pnDcha.add(getPnResumenPreinscripciones(), BorderLayout.NORTH);
		
		}
		return pnDcha;
	}
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new GridLayout(7, 1, 0, 0));
			pnCentro.add(getPnVacío());
			pnCentro.add(getPnPreinscripcionNombre());
			pnCentro.add(getSpinnerCantidad());
			pnCentro.add(getSpinnerDuracion());
			pnCentro.add(getSpinnerIntervalo());
			pnCentro.add(getScrollPane());
			pnCentro.add(getBtnAadirPreinscripcin());
//			pnCentro.add(getTextArea());
		}
		return pnCentro;
	}
	private JLabel getLabel_4_2() {
		if (lblNombrePreinscripcion == null) {
			lblNombrePreinscripcion = new JLabel("Nombre:");
		}
		return lblNombrePreinscripcion;
	}
	private JButton getBtnNueva() {
		if (btnNueva == null) {
			btnNueva = new JButton("Crear nueva preinscripci\u00F3n");
			btnNueva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					crearPreinscripción();
				}
			});
		}
		return btnNueva;
	}
	
	/**
	 * Método para crear una nueva preinscripción que no había antes
	 */
	protected void crearPreinscripción() {
		AnadirPreinscripcion ventanaPreinscripcion = new AnadirPreinscripcion(this);
		
		ventanaPreinscripcion.setLocationRelativeTo(this);
		ventanaPreinscripcion.setResizable(true);
		ventanaPreinscripcion.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		ventanaPreinscripcion.setVisible(true);
		
	}
	
	/**
	 * Método que me permite guardar la preinscripcion
	 * @param preinscripcion the preinscripcion to set
	 */
	public void setPreinscripcion(Preinscripcion preinscripcion) {
		this.preinscripcion = preinscripcion;
	}

	private JLabel getLabel_4_3() {
		if (lblNCantidad == null) {
			lblNCantidad = new JLabel("Cantidad:");
		}
		return lblNCantidad;
	}
	private JLabel getLabel_4_4() {
		if (lblDuracion == null) {
			lblDuracion = new JLabel("Duraci\u00F3n (en d\u00EDas):");
		}
		return lblDuracion;
	}
	private JLabel getLabel_4_5() {
		if (lblIntervalo == null) {
			lblIntervalo = new JLabel("Intervalo (en horas):");
		}
		return lblIntervalo;
	}
	private JLabel getLabel_4_6() {
		if (lblInstrucciones == null) {
			lblInstrucciones = new JLabel("Instrucciones:");
		}
		return lblInstrucciones;
	}
	private JComboBox getCbNombre() {
		if (cbNombre == null) {
			cbNombre = new JComboBox();
			

			String[] nombrePreinscripciones = new String[preinscripciones.size()];
			for (int i = 0; i< preinscripciones.size(); i++) {
				nombrePreinscripciones[i] = preinscripciones.get(i).getNombre();
			}
			
			cbNombre.setModel(new DefaultComboBoxModel<String>(nombrePreinscripciones));				

			
		}
		return cbNombre;
	}
	private JPanel getPnPreinscripcionNombre() {
		if (pnPreinscripcionNombre == null) {
			pnPreinscripcionNombre = new JPanel();
			pnPreinscripcionNombre.setLayout(new GridLayout(0, 2, 0, 0));
			pnPreinscripcionNombre.add(getCbNombre());
			pnPreinscripcionNombre.add(getBtnSeleccionar());
		}
		return pnPreinscripcionNombre;
	}
	private JButton getBtnSeleccionar() {
		if (btnSeleccionar == null) {
			btnSeleccionar = new JButton("Seleccionar");
		}
		return btnSeleccionar;
	}
	private JPanel getPnVacío() {
		if (pnVacío == null) {
			pnVacío = new JPanel();
		}
		return pnVacío;
	}
	private JSpinner getSpinnerCantidad() {
		if (spinnerCantidad == null) {
			spinnerCantidad = new JSpinner();
			spinnerCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		return spinnerCantidad;
	}
	private JSpinner getSpinnerDuracion() {
		if (spinnerDuracion == null) {
			spinnerDuracion = new JSpinner();
			spinnerDuracion.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		return spinnerDuracion;
	}
	private JSpinner getSpinnerIntervalo() {
		if (spinnerIntervalo == null) {
			spinnerIntervalo = new JSpinner();
			spinnerIntervalo.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		}
		return spinnerIntervalo;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
		}
		return textArea;
	}
	private JPanel getPnResumen() {
		if (pnResumen == null) {
			pnResumen = new JPanel();
			pnResumen.setLayout(new GridLayout(0, 1, 0, 0));
			pnResumen.add(getListPreinscripciones());
			//pnResumen.add(getList());
			//pnResumen.add(getTextArea_1());
		}
		return pnResumen;
	}
	private JPanel getPnBorrarPreinscripcion() {
		if (pnBorrarPreinscripcion == null) {
			pnBorrarPreinscripcion = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBorrarPreinscripcion.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBorrarPreinscripcion.add(getBtnBorrar());
		}
		return pnBorrarPreinscripcion;
	}
	private JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar preinscripci\u00F3n");
		}
		return btnBorrar;
	}
	private JPanel getPnResumenPreinscripciones() {
		if (pnResumenPreinscripciones == null) {
			pnResumenPreinscripciones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnResumenPreinscripciones.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnResumenPreinscripciones.add(getLabel_4_7());
		}
		return pnResumenPreinscripciones;
	}
	private JLabel getLabel_4_7() {
		if (lblResumenPreinscripciones == null) {
			lblResumenPreinscripciones = new JLabel("Preinscripciones seleccionadas:");
		}
		return lblResumenPreinscripciones;
	}

	private JList getList() {
		if (list == null) {
			list = new JList();			
		}
		return list;
	}
	private JList getListPreinscripciones() {
		if (listPreinscripciones == null) {
			listPreinscripciones = new JList();
		}
		return listPreinscripciones;
	}
	private JButton getBtnAadirPreinscripcin() {
		if (btnAadirPreinscripcin == null) {
			btnAadirPreinscripcin = new JButton("A\u00F1adir preinscripci\u00F3n");
		}
		return btnAadirPreinscripcin;
	}

	/**
	 * Método para actualizar el elemento que se muestra en el comboBox de Preinscripciones
	 */
	public void actualizar() {
		
		try {
			preinscripciones = pbd.listarPrescripciones(); // Volvemos a cargar las preinscripciones de la base de datos después de haber añadido
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		cbNombre.removeAllItems(); // Vacío todo el combo box
		
//		// Actualizamos el combo box
//		for (int i = 0; i < preinscripciones.size(); i++) {
//			cbNombre.insertItemAt(preinscripciones.get(i), i);
//		}
		
		String[] nombrePreinscripciones = new String[preinscripciones.size()];
		for (int i = 0; i< preinscripciones.size(); i++) {
			nombrePreinscripciones[i] = preinscripciones.get(i).getNombre();
		}
		
		cbNombre.setModel(new DefaultComboBoxModel<String>(nombrePreinscripciones));				

		int contador = 0;
		for (Preinscripcion p : preinscripciones) {
			if (p.getNombre().equals(preinscripcion.getNombre())) {
				cbNombre.setSelectedIndex(contador);				
			}
			contador = contador + 1;
		}
		
		
	}
}