package ui.medico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logica.AsignaDiagnostico;
import logica.AsignaPreinscripcion;
import logica.AsignaVacuna;
import logica.Causas;
import logica.Cita;
import logica.Diagnostico;
import logica.HistorialMedico;
import logica.Paciente;
import logica.Preinscripcion;
import logica.Vacuna;
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
import java.text.SimpleDateFormat;
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
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ModificarMedicosNuevoCard extends JDialog {

	private boolean listo = true;
	private boolean tablaLista = false;
	DefaultListModel modeloLista = new DefaultListModel();
	private ModeloNoEditable modeloTabla;

	
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
	private List<Preinscripcion> preinscripciones; // Las preinscripciones que tenemos en la base de datos
	private List<Vacuna> vacunas; // Las bacuans que tenemos en la base de datos
	private List<Diagnostico> diagnosticos; // Los diagnosticos que tenemos en la base de datos
	private List<Preinscripcion> preinscripcionesPaciente = new ArrayList<Preinscripcion>();
	private List<AsignaPreinscripcion> asignaPreinscripcionesPaciente = new ArrayList<AsignaPreinscripcion>();
	private List<AsignaVacuna> asignaVacunasPaciente = new ArrayList<AsignaVacuna>();
	private List <AsignaDiagnostico> asignaDiagnosticosPaciente = new ArrayList<AsignaDiagnostico>();
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
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_2;
	private JLabel lblNombre;
	private JButton btnSeleccionar;
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
	private JButton btnAadirPreinscripcin;
	private JTable table;
	private JPanel panel_3;
	private JRadioButton rdbtnAcudio;
	private JRadioButton rdbtnNoAcudio;
	private Component horizontalStrut;
	private JPanel pn1;
	private JPanel pn2;
	private JPanel pn4;
	private JPanel pn5;
	private JPanel pn6;
	private JPanel pn7;
	private JPanel pn8;
	private JLabel lblVacuna;
	private JPanel pn3;
	private JComboBox<String> cbVacunas;
	private JButton btnAsignarVacuna;
	private JScrollPane scrollPanTabla;
	private JPanel pnDiagnosticos;
	private JPanel pn1d;
	private JPanel pn2d;
	private JPanel pn3d;
	private JPanel pn4d;
	private JPanel pn5d;
	private JPanel pn6d;
	private JPanel pn7d;
	private JPanel pn8d;
	private JLabel lblDiagnosticos;
	private JComboBox<String> cbDiagnosticos;
	private JButton btnDiagnosticar;
	
	private boolean causaSeleccionada;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ModificarMedicosNuevoCard(Paciente paciente, Cita cita) throws SQLException {
		mm = this;
		this.paciente = paciente;
		this.cita = cita;
		ponerCausas();
		causaSeleccionada = false;


		preinscripciones = pbd.listarPrescripciones();
		vacunas = pbd.listarVacunas();
		diagnosticos = pbd.listarDiagnosticos();

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1486, 691);
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
					try {
						guardar();
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
			panelPestañas.addTab("Diagnosticos", null, getPnDiagnosticos(), null);
			
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
			panelVacunas.setLayout(new GridLayout(4, 0, 2, 0));
			panelVacunas.add(getPn1());
			panelVacunas.add(getPn2());
			panelVacunas.add(getPn3());
			panelVacunas.add(getPn4());
			panelVacunas.add(getPn5());
			panelVacunas.add(getPn6());
			panelVacunas.add(getPn7());
			panelVacunas.add(getPn8());
			
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
			panDe.add(getPanel_3());
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
			panel_2.add(getBtnSeleccionar());
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
	private JButton getBtnSeleccionar() {
		if (btnSeleccionar == null) {
			btnSeleccionar = new JButton("Seleccionar");
			btnSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setCausaSeleccionada(true);
				}
			});
		}
		return btnSeleccionar;
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


	public List<String> darCausas() {
		return nombresCausas;
	}


	public void añadirCausa(String causa) {
		nombresCausas.add(causa);
	}



	
	private JPanel getPnIzq() {
		if (pnIzq == null) {
			pnIzq = new JPanel();
			pnIzq.setLayout(new GridLayout(7, 1, 0, 0));
			pnIzq.add(getPnVacío());
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
			pnCentro.add(getBtnNueva());
			pnCentro.add(getCbNombre());
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
			cbNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cambiarEnabled();
				}

			});
			

			String[] nombrePreinscripciones = new String[preinscripciones.size()];
			for (int i = 0; i< preinscripciones.size(); i++) {
				nombrePreinscripciones[i] = preinscripciones.get(i).getNombre();
			}
			
			cbNombre.setModel(new DefaultComboBoxModel<String>(nombrePreinscripciones));				

			
		}
		return cbNombre;
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
			
			if(comprobarSiEsMedicamento()) { // Si la preinscripción es un medicamento
				if(!spinnerCantidad.isEnabled()) { // Si no estaba enabled
					spinnerCantidad.setEnabled(true);
				}
			}
			else if(comprobarSiEsMedicamento() == false) {
				if (spinnerCantidad.isEnabled()) {
					spinnerCantidad.setEnabled(false);
				}
			}
			
		}
		return spinnerCantidad;
	}


	private JSpinner getSpinnerDuracion() {
		if (spinnerDuracion == null) {
			spinnerDuracion = new JSpinner();
			spinnerDuracion.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			
			if(comprobarSiEsMedicamento()) { // Si la preinscripción es un medicamento
				if(!spinnerDuracion.isEnabled()) { // Si no estaba enabled
					spinnerDuracion.setEnabled(true);
				}
			}
			else if(comprobarSiEsMedicamento() == false) {
				if(spinnerDuracion.isEnabled()) {
					spinnerDuracion.setEnabled(false);
				}
			}
		}
		return spinnerDuracion;
	}
	private JSpinner getSpinnerIntervalo() {
		if (spinnerIntervalo == null) {
			spinnerIntervalo = new JSpinner();
			spinnerIntervalo.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			
			if(comprobarSiEsMedicamento()) { // Si la preinscripción es un medicamento
				if(!spinnerIntervalo.isEnabled()) { // Si no estaba enabled
					spinnerIntervalo.setEnabled(true);
				}
			}
			else if(comprobarSiEsMedicamento() == false) {
				if (spinnerIntervalo.isEnabled()) {
					spinnerIntervalo.setEnabled(false);	
				}
			}
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
			//pnResumen.add(getTable());
			pnResumen.add(getScrollPanTabla());
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
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila=table.getSelectedRow();
					
					if (fila != -1) {
						int res = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea borrar la preinscripción?","Mensaje de confirmación",JOptionPane.YES_NO_OPTION);
						if(res == JOptionPane.YES_OPTION) {	
								
								preinscripcionesPaciente.remove(table.getSelectedRow());
								asignaPreinscripcionesPaciente.remove(table.getSelectedRow());
								
								añadirFilas();
						
						}						
					}		
				}
			});
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
	private JButton getBtnAadirPreinscripcin() {
		if (btnAadirPreinscripcin == null) {
			btnAadirPreinscripcin = new JButton("A\u00F1adir preinscripci\u00F3n");
			btnAadirPreinscripcin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					anadirPreinscripcionPaciente();

				}
			});
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
		
		listo = false;
		cbNombre.removeAllItems(); // Vacío todo el combo box
		
		// Cargo otra vez las preinscripciones en el cb
		String[] nombrePreinscripciones = new String[preinscripciones.size()];
		for (int i = 0; i< preinscripciones.size(); i++) {
			nombrePreinscripciones[i] = preinscripciones.get(i).getNombre();
		}
		
		cbNombre.setModel(new DefaultComboBoxModel<String>(nombrePreinscripciones));				

		listo = true;
		
		int contador = 0;
		for (Preinscripcion p : preinscripciones) {
			if (p.getNombre().equals(preinscripcion.getNombre())) {
				cbNombre.setSelectedIndex(contador);
				comprobarSiEsMedicamento(); // Comprobamos si es un medicamento para cambiar los enabled de los spinner
			}
			contador = contador + 1;
		}
	}	
		/**
		 * Método que me comprueba si la preinscripción que hay seleccionada en el comboBox es de tipo medicamento o no
		 */
	private boolean comprobarSiEsMedicamento() {
		
		int indiceSeleciconado = getCbNombre().getSelectedIndex();
		Preinscripcion p = null;
			

		int contador = 0;
		for (Preinscripcion pre : preinscripciones) {
			if (indiceSeleciconado == contador) {
				p = pre;
			}
			contador = contador + 1;
		}
		if(p != null) {
			if (p.isMedicamento()) { // Si era medicamento
				return true;
			}
		}
		return false; // Si no era medicamento
	}
	
	
	/**
	 * Método que para cuando cambio el comboBox me compruebe si tiene que cambiar el enabled de los spinner por ser/no un medicamento lo que
	 * tiene seleccionado
	 */
	private void cambiarEnabled() {
		if (listo) { // Para que no casque mientras está cambiando la ventana
			boolean isMedicamento = comprobarSiEsMedicamento(); // Compruebo si es un medicamento
			
			
			
			if(isMedicamento) { // Si la preinscripción es un medicamento
				if(!spinnerCantidad.isEnabled()) { // Si no estaba enabled
					spinnerCantidad.setEnabled(true);
				}
				if(!spinnerDuracion.isEnabled()) { 
					spinnerDuracion.setEnabled(true);
				}
				if(!spinnerIntervalo.isEnabled()) { 
					spinnerIntervalo.setEnabled(true);
				}
				
			}
			else if(isMedicamento == false) {
				if (spinnerCantidad.isEnabled()) {
					spinnerCantidad.setEnabled(false);
				}
				if (spinnerDuracion.isEnabled()) {
					spinnerDuracion.setEnabled(false);
				}
				if (spinnerIntervalo.isEnabled()) {
					spinnerIntervalo.setEnabled(false);
				}
			}			
		}
		
		
	}
	
	
	/**
	 * Método para añadir la preinscripcion al paciente
	 */
	private void anadirPreinscripcionPaciente() {
						
		int indiceSeleciconado = getCbNombre().getSelectedIndex(); // Lo que está seleccionado en el comboBox
		Preinscripcion p = null; // La preinscripcion
			

		// Buscamos la preinscripcion que hay seleccionada en el comboBox
		int contador = 0;
		for (Preinscripcion pre : preinscripciones) {
			if (indiceSeleciconado == contador) {
				p = pre;
			}
			contador = contador + 1;
		}
		
		preinscripcionesPaciente.add(p); // Añadimos la preinscripcion
		crearAsignaPreinscripcion(p);
				
		if (tablaLista == false) { // Para que no casque al pintar la tabla de las preinscripciones
			tablaLista = true;
		}
		
		añadirFilas();
	}
	
	
	/**
	 * Método que me crea un asigna preinscripcion y me lo añade a la lista de preinscripciones que va a tener el paciente
	 * @param p
	 */
	private void crearAsignaPreinscripcion(Preinscripcion p) {
		
		int indiceSeleciconado = getCbNombre().getSelectedIndex();
			

		int contador = 0;
		for (Preinscripcion pre : preinscripciones) {
			if (indiceSeleciconado == contador) {
				preinscripcion = pre;
			}
			contador = contador + 1;
		}
		
		
		
		// El codigo de la preinscripcion
		Random r = new Random();
		String codAsignaPreinscripcion = "" + r.nextInt(800);
		
		// El código del historial del paciente
		String codigoHistorial = paciente.getHistorial();
		String codigoPreinscripcion = preinscripcion.getNombre();
		
		// El codigo del empleado (medico)
		String codempleado = cita.getCodMed();
		//String codempleado = "1a";
		
		
		// Si es medicamento o no
		boolean medicamento = preinscripcion.isMedicamento();

		// Las dosis (SOLO SI ES MEDICAMENTO, sino se queda a 0 por defecto)
		int cantidad = 0;
		int intervalo = 0;
		int duracion = 0;
		
		if (medicamento) {
			cantidad = (int) getSpinnerCantidad().getValue();
			duracion= (int) getSpinnerDuracion().getValue();
			intervalo = (int)getSpinnerIntervalo().getValue();	
		}
		
		// Las instrucciones para tomar la preinscripcion
		String instrucciones = getTextArea().getText();	

		// Fecha y hora actuales del sistema
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());
		
		AsignaPreinscripcion ap = new AsignaPreinscripcion(codAsignaPreinscripcion, codigoHistorial, codempleado, codigoPreinscripcion, cantidad, 
									intervalo, duracion, instrucciones, fecha, hora);
		
		
		
		
		asignaPreinscripcionesPaciente.add(ap); // Añado la asignacion de la preinscripcion a una lista que tengo
	}

	
	private JTable getTable() {
		if (table == null) {
			
			String[] nombreColumnas= {"Nombre","Medicamento","Cantidad","Duracion","Intervalo"};
			modeloTabla= new ModeloNoEditable(nombreColumnas,0);
			table = new JTable(modeloTabla);
			table.getTableHeader().setReorderingAllowed(false);//Evita que se pueda mpver las columnas
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getTableHeader().setBackground(Color.LIGHT_GRAY);
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
			table.setRowSorter(sorter);
			
			List<RowSorter.SortKey> sortKeys = new ArrayList<>();
			sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
			sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
			
			sorter.setSortKeys(sortKeys);
			
			añadirFilas();
		}
		return table;
	}

	/**
	 * Método para añadir filas a la tabla de las preinscripciones
	 * @param b
	 */
	private void añadirFilas() {
		borrarModeloTabla(); // Borramos todo antes de volver a pintar
	
		Object[] nuevaFila=new Object[5]; // 5 son las columnas
				
		
		if (tablaLista) {
			for (AsignaPreinscripcion a : asignaPreinscripcionesPaciente) {
				nuevaFila[0] = a.getCodigoPreinscripcion(); // El nombre de la preinscripcion
				
				boolean medicamento = false;
				for (Preinscripcion p : preinscripcionesPaciente) {
					if (p.getNombre().equals(a.getCodigoPreinscripcion())) { // Si es la preinscripcion
						
						if (p.isMedicamento()) { // Si la preinscripcion es un medicamento
							nuevaFila[1] = "Si";
							medicamento = true;
						}
						else if (!p.isMedicamento()) { // Si NO es un medicamento
							nuevaFila[1] = "No";
						}	 
					}
				}
				
				if (medicamento) { // Si es un medicamento le ponemos las demás características
					nuevaFila[2] = "" + a.getCantidad();
					nuevaFila[3] = "" + a.getDuracion();
					nuevaFila[4] = "" + a.getIntervalo();
				}
				else if (!medicamento){ // Si no es un medicamento
					nuevaFila[2] = "-";
					nuevaFila[3] = "-";
					nuevaFila[4] = "-";
				}
				
				
				modeloTabla.addRow(nuevaFila); // Añado la fila
				
			}
			
			
		}
		
	}

	
	/**
	 * Método para borrar todas las filas de tabla
	 */
	private void borrarModeloTabla() {
		int filas = modeloTabla.getRowCount();
		for (int i = 0; i < filas; i++) {
			modeloTabla.removeRow(0);			
		}		
	}
	
	
	
	/**
	 * Método que me guarda lo que he modificado de la cita
	 * @throws SQLException 
	 */
	private void guardar() throws SQLException {
		guardarPreinscripciones();
		if(isCausaSeleccionada()) {
			guardarCausas();
		}
		guardarVacunas();
		guardarDiagnosticos();
	}





	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getRdbtnAcudio());
			panel_3.add(getHorizontalStrut());
			panel_3.add(getRdbtnNoAcudio());
		}
		return panel_3;
	}
	private JRadioButton getRdbtnAcudio() {
		if (rdbtnAcudio == null) {
			rdbtnAcudio = new JRadioButton("Acudio");
		}
		return rdbtnAcudio;
	}
	private JRadioButton getRdbtnNoAcudio() {
		if (rdbtnNoAcudio == null) {
			rdbtnNoAcudio = new JRadioButton("No acudio");
		}
		return rdbtnNoAcudio;
	}
	private Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(60);
		}
		return horizontalStrut;
	}
	
	protected void guardarCausas() throws SQLException {
			String causas = getCbCausas().getSelectedItem().toString();
			String nHistorial = "" + mm.getPaciente().getHistorial();
			Time hora =  cita.gethInicio();
			
			java.sql.Date horas = new java.sql.Date(hora.getTime());
			
			Time hour = new Time(horas.getTime());
			
			Date fecha = (Date) cita.getDate();
			
			java.sql.Date sDate = new java.sql.Date(fecha.getTime());
			

			if(!causas.equals("")) {
				Random r = new Random();
				String codcausa = "" + r.nextInt(300);
				pbd.actualizarAsignaCausa(codcausa,causas, nHistorial, sDate, hour, cita.getCodMed());
			}
		
		
	}
	
	public void ponerCausas() throws SQLException {
		nombresCausas = new ArrayList<>() ;
		List<String> causas = pbd.buscarNombreTodasCausas();
		for(int i =0; i< causas.size(); i++) {
			nombresCausas.add(causas.get(i));
		}
	}
	
	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
		}
		return pn1;
	}
	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
		}
		return pn2;
	}
	private JPanel getPn4() {
		if (pn4 == null) {
			pn4 = new JPanel();
			pn4.setLayout(new GridLayout(0, 2, 0, 0));
			pn4.add(getCbVacunas());
		}
		return pn4;
	}
	private JPanel getPn5() {
		if (pn5 == null) {
			pn5 = new JPanel();
		}
		return pn5;
	}
	private JPanel getPn6() {
		if (pn6 == null) {
			pn6 = new JPanel();
		}
		return pn6;
	}
	private JPanel getPn7() {
		if (pn7 == null) {
			pn7 = new JPanel();
		}
		return pn7;
	}
	private JPanel getPn8() {
		if (pn8 == null) {
			pn8 = new JPanel();
			pn8.add(getBtnAsignarVacuna());
		}
		return pn8;
	}
	private JLabel getLabel_4_8() {
		if (lblVacuna == null) {
			lblVacuna = new JLabel("Vacuna:");
			lblVacuna.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblVacuna;
	}
	private JPanel getPn3() {
		if (pn3 == null) {
			pn3 = new JPanel();
			pn3.add(getLabel_4_8());
		}
		return pn3;
	}
	private JComboBox<String> getCbVacunas() {
		if (cbVacunas == null) {
			cbVacunas = new JComboBox();
			
			String[] nombreVacunas = new String[vacunas.size()];
			for (int i = 0; i < vacunas.size(); i++) {
				nombreVacunas[i] = vacunas.get(i).getNombreVacuna();
			}
			
			cbVacunas.setModel(new DefaultComboBoxModel<String>(nombreVacunas));	
		}
		
		return cbVacunas;
	}
	private JButton getBtnAsignarVacuna() {
		if (btnAsignarVacuna == null) {
			btnAsignarVacuna = new JButton("Asignar vacuna");
			btnAsignarVacuna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anadirVacuna();
				}


			});
		}
		return btnAsignarVacuna;
	}
	private JScrollPane getScrollPanTabla() {
		if (scrollPanTabla == null) {
			scrollPanTabla = new JScrollPane();
			
			scrollPanTabla.setViewportView(getTable());
		}
		return scrollPanTabla;
	}
	
	
	
	/**
	 * Método para añadir la vacuna temporalmente al paciente
	 */
	private void anadirVacuna() {
		int indice = cbVacunas.getSelectedIndex(); // el índice que hay seleccionado en el cb
		Vacuna vacuna = null;
		
		// Buscamos la vacuna que hay seleccionada en el cb
		int contador = 0;
		for(Vacuna v : vacunas) {
			if (indice == contador) {
				vacuna = v;
			}
			contador = contador + 1;
		}
		
		Random r = new Random();
		String codVacuna = "" + r.nextInt(999);
		
		String nombreVacuna = vacuna.getNombreVacuna();
		String codHistorial = paciente.getHistorial();
		String codEmpleado = cita.getCodMed();		
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());		
		
		
		AsignaVacuna av = new AsignaVacuna(codVacuna, nombreVacuna, codHistorial, codEmpleado, fecha, hora);
		
		asignaVacunasPaciente.add(av);
	}
	
	
	/**
	 * Método para asignar definitivamente las vacunas al paciente
	 * @throws SQLException 
	 */
	private void guardarVacunas() throws SQLException {
		
		if (!asignaVacunasPaciente.isEmpty()) { // Que le hayamos asignado alguna vacuna
			for (AsignaVacuna av : asignaVacunasPaciente) { // Voy guardando cada una de las vacunas que le he asignado
				pbd.nuevaAsignaVacuna(av);
			}
		}
	}
	
	
	/**
	 * Método para guardar las preinscripciones que se le han asignado a un paciente
	 * @throws SQLException 
	 */
	private void guardarPreinscripciones() throws SQLException {
		// Guardo las preinscripciones que le he asignado al paciente
		if (!asignaPreinscripcionesPaciente.isEmpty()) { // Que le hayamos asignado algo
			for (AsignaPreinscripcion ap : asignaPreinscripcionesPaciente) { // Voy guardando cada una de las preinscripciones que le he asignado
				pbd.nuevaAsignaPreinscripcion(ap);
			}
		}		
	}
	private JPanel getPnDiagnosticos() {
		if (pnDiagnosticos == null) {
			pnDiagnosticos = new JPanel();
			pnDiagnosticos.setLayout(new GridLayout(4, 0, 2, 0));
			pnDiagnosticos.add(getPn1d());
			pnDiagnosticos.add(getPn2d());
			pnDiagnosticos.add(getPn3d());
			pnDiagnosticos.add(getPn4d());
			pnDiagnosticos.add(getPn5d());
			pnDiagnosticos.add(getPn6d());
			pnDiagnosticos.add(getPn7d());
			pnDiagnosticos.add(getPn8d());
		}
		return pnDiagnosticos;
	}
	private JPanel getPn1d() {
		if (pn1d == null) {
			pn1d = new JPanel();
		}
		return pn1d;
	}
	private JPanel getPn2d() {
		if (pn2d == null) {
			pn2d = new JPanel();
		}
		return pn2d;
	}
	private JPanel getPn3d() {
		if (pn3d == null) {
			pn3d = new JPanel();
			pn3d.add(getLabel_4_9());
		}
		return pn3d;
	}
	private JPanel getPn4d() {
		if (pn4d == null) {
			pn4d = new JPanel();
			pn4d.setLayout(new GridLayout(0, 2, 0, 0));
			pn4d.add(getCbDiagnosticos());
		}
		return pn4d;
	}
	private JPanel getPn5d() {
		if (pn5d == null) {
			pn5d = new JPanel();
		}
		return pn5d;
	}
	private JPanel getPn6d() {
		if (pn6d == null) {
			pn6d = new JPanel();
		}
		return pn6d;
	}
	private JPanel getPn7d() {
		if (pn7d == null) {
			pn7d = new JPanel();
		}
		return pn7d;
	}
	private JPanel getPn8d() {
		if (pn8d == null) {
			pn8d = new JPanel();
			pn8d.add(getBtnDiagnosticar());
		}
		return pn8d;
	}
	private JLabel getLabel_4_9() {
		if (lblDiagnosticos == null) {
			lblDiagnosticos = new JLabel("Diagnosticos:");
		}
		return lblDiagnosticos;
	}
	private JComboBox<String> getCbDiagnosticos() {
		if (cbDiagnosticos == null) {
			cbDiagnosticos = new JComboBox();
			
			String[] nombreDiagnosticos = new String[diagnosticos.size()];
			for (int i = 0; i < diagnosticos.size(); i++) {
				nombreDiagnosticos[i] = diagnosticos.get(i).getNombre();
			}
			
			cbDiagnosticos.setModel(new DefaultComboBoxModel<String>(nombreDiagnosticos));
		}
		return cbDiagnosticos;
	}
	private JButton getBtnDiagnosticar() {
		if (btnDiagnosticar == null) {
			btnDiagnosticar = new JButton("Diagnosticar");
			btnDiagnosticar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anadirDiagnostico();
				}
			});
		}
		return btnDiagnosticar;
	}

	
	/**
	 * Método para asignar un diagnóstico nuevo a un paciente
	 */
	protected void anadirDiagnostico() {
		int indice = cbDiagnosticos.getSelectedIndex(); // el índice que hay seleccionado en el cb
		Diagnostico diagnostico = null;
		
		// Buscamos la vacuna que hay seleccionada en el cb
		int contador = 0;
		for(Diagnostico d : diagnosticos) {
			if (indice == contador) {
				diagnostico = d;
			}
			contador = contador + 1;
		}
		
		
		
		

		
		Random r = new Random();
		String codAsigDiagnostico = "" + r.nextInt(999); // El código es aleatorio
		
		String nombreDiagnostico = diagnostico.getNombre();
		String nHistorial = paciente.getHistorial(); // El número de historial del paciente a quien le hemos asignado el diagnostico
		String nDiagnostico = diagnostico.getNumeroDiagnostico(); // El identificador del diagnostico
		String codMedico = cita.getCodMed();
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());
		AsignaDiagnostico ad = new AsignaDiagnostico(codAsigDiagnostico, nombreDiagnostico, nDiagnostico, nHistorial, codMedico, fecha, hora);

		asignaDiagnosticosPaciente.add(ad);
		
	}
	
	
	
	
	
	/**
	 * Método para guardar definitivamente todos los diagnósticos que se le han asignado al paciente
	 * @throws SQLException 
	 */
	private void guardarDiagnosticos() throws SQLException {
		if (!asignaDiagnosticosPaciente.isEmpty()) { // Que le hayamos asignado algun diagnostico
			for (AsignaDiagnostico ad : asignaDiagnosticosPaciente) { // Voy guardando cada uno de los diagnosticos
				pbd.nuevaAsignaDiagnostico(ad);
			}
		}	
	}

	public boolean isCausaSeleccionada() {
		return causaSeleccionada;
	}

	public void setCausaSeleccionada(boolean causaSeleccionada) {
		this.causaSeleccionada = causaSeleccionada;
	}
	
	
	
}