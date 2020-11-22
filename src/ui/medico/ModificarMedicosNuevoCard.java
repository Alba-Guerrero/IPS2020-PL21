package ui.medico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logica.AccionEmpleado;
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
import logica.servicios.PrescripcionesToPDF;
import logica.servicios.Printer;
import net.sf.jasperreports.engine.JRException;
import ui.MostrarHistorial;

import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ModificarMedicosNuevoCard extends JDialog {

	private boolean listo = true;
	private boolean tablaLista = false;
	DefaultListModel modeloLista = new DefaultListModel();
	private ModeloNoEditable modeloTabla;

	
	private JPanel contentPane;
	private JPanel panelSur;
	private JButton button;
	private JButton button_1;
	private JPanel panelCentro;
	private JTabbedPane panelPestañas;
	private JPanel panelCausas;
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
	private Time horaInicioCita; // La hora que le puede asignar el medico 
	private Time horaFinCita; // La hora que le puede asignar el médico
	private JLabel lblAcudi;
	private Causas causa;
	private List<Causas> causas;
	private JPanel pnDatosHoraAcudio;
	private JPanel pnIz;
	private JLabel label;
	private JPanel pnDch;
	private JTextField txtName;
	private JPanel pnDatosPaciente;
	private JPanel pnHistorial;
	private JPanel pnHoraEntrada;
	private JPanel pnHoraSalida;
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
	private JPanel pnAcudio;
	private JRadioButton rdbtnAcudio;
	private JRadioButton rdbtnNoAcudio;
	private Component horizontalStrut;
	private JPanel pnMostrarVacunas;
	private JPanel pnVacio14;
	private JLabel lblVacuna;
	private JPanel pn3;
	private JComboBox<String> cbVacunas;
	private JButton btnAsignarVacuna;
	private JScrollPane scrollPanTabla;
	private JPanel pnDiagnosticos;
	private JPanel pn3d;
	private JPanel pnMostrarDiagnosticos;
	private JPanel pnVacio22;
	private JPanel pn4d;
	private JPanel pn5d;
	private JPanel pn6d;
	private JPanel pn7d;
	private JPanel pn8d;
	private JLabel lblDiagnosticos;
	private JComboBox<String> cbDiagnosticos;
	private JButton btnDiagnosticar;
	
	private boolean causaSeleccionada;
	private JLabel lblHoraInicio;
	private JPanel pnVacio3;
	private JPanel pnHoraInicio;
	private JPanel pnVacio;
	private JPanel pnVacio2;
	private JSpinner spinnerHInicioR;
	private JPanel pnHoraInicioF;
	private JPanel pnGuardarHInicio;
	private JPanel pnModificarHInicio;
	private JButton btnModificar;
	private JSpinner spinnerHInicioNueva;
	private JButton btnGuardarInicio;
	private JLabel lblHorasalida;
	private JPanel pnVacio5;
	private JPanel pnHoraFin;
	private JPanel pnVacio6;
	private JSpinner spinnerHFinR;
	private JPanel pnVacio7;
	private JPanel pnHoraFinF;
	private JPanel pnModificarHoraFinF;
	private JButton btnModificarFin;
	private JSpinner spinnerHFinNueva;
	private JPanel pnGuardarHFin;
	private JButton btnGuardarFin;
	private JLabel label_1;
	private JTextField txtApellido;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnMostrarHistorial;
	private JPanel pnVacio11;
	private JPanel pnVacio8;
	private JPanel pnMostrarHistorialVacunas;
	private JPanel pnVacio9;
	private JPanel pnMostrar;
	private JPanel pnVacio10;
	private JPanel pnVacio12;
	private JPanel pnSeleccionAcudio;
	private JPanel pnVacio13;
	private JPanel pnFiltrarVacunas;
	private JPanel pnBuscarVacunaAsignar;
	private JPanel pnV1;
	private JPanel pnFiltrarV;
	private JPanel pnV2;
	private JTextField txtFiltrarVacunas;
	private JButton btnFiltrarVacunas;
	private JPanel pnVacio15;
	private JPanel pnVacio16;
	private JPanel pnFiltrarDiagnosticos;
	private JPanel pnBuscarDiagnosticosAsignar;
	private JPanel pnFiltrarD;
	private JPanel pnVacio18;
	private JPanel pnVacio19;
	private JTextField txtFiltrarDiagnosticos;
	private JButton btnFiltrarDiagnosticos;
	private JPanel pnVacio20;
	private JPanel pnVacio21;
	private JButton btnCalendarioVacunas;
	private JButton btnNewButton;
	private JPanel panel_4;
	private JLabel lblNewLabel;
	private JTextField txtNombreCausa;
	private JButton btnFiltrar;
	private JButton buttonpres;
	private JButton btnprintpres;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ModificarMedicosNuevoCard(Paciente paciente, Cita cita) throws SQLException {
		setTitle("Atender Consulta");
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
		contentPane.add(getPanelSur(), BorderLayout.SOUTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
	}
	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			panelSur.setBackground(SystemColor.menu);
			panelSur.add(getButton());
			panelSur.add(getButton_1());
			panelSur.add(getButtonPrescricpcion());
			panelSur.add(getButtonPrintPres());
		}
		return panelSur;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Guardar");
			button.setFont(new Font("Tahoma", Font.PLAIN, 16));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						guardar();
						consultaAtendida();
						
						//dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return button;
	}
	
	private JButton getButtonPrescricpcion() {
		if (buttonpres == null) {
			buttonpres = new JButton("Descargar prescripción");
			buttonpres.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					descargarPrescricpiones();
				}
			});
			buttonpres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
			return buttonpres;
	}
	
	private void consultaAtendida() {
			JOptionPane.showMessageDialog(null, "Los cambios de la consulta han sido guardados");
	}
	
	private void descargarPrescricpiones() {
		PrescripcionesToPDF pres= new PrescripcionesToPDF();
		try {
			pres.createPDF(paciente);
		} catch (FileNotFoundException | JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Se ha generado la receta correctamente");
	}
	
	private JButton getButtonPrintPres() {
		if (btnprintpres == null) {
			btnprintpres = new JButton();
			btnprintpres.setIcon(new ImageIcon("C:\\Users\\Alba\\Downloads\\interface+multimedia+print+printer+icon-1320185667007730348_24.png"));
			btnprintpres.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					imprimirPrescripciones();
				}
			});
			btnprintpres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
			return btnprintpres;
	}
	
	void imprimirPrescripciones(){
		Printer printer= new Printer();
		try {
			File archivo = new File("Recetas/"+paciente.getHistorial()+"receta.pdf");
			if (!archivo.exists()) {
				descargarPrescricpiones();
			}
			printer.print("Recetas/"+paciente.getHistorial()+"receta.pdf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("Cancelar");
			button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
			panelCentro.add(getPnDatosHoraAcudio());
			panelCentro.add(getPanelPestañas());
		}
		return panelCentro;
	}

	private JTabbedPane getPanelPestañas() throws SQLException {
		if (panelPestañas == null) {
			panelPestañas = new JTabbedPane(JTabbedPane.TOP);
			panelPestañas.addTab("Causas", null, getPanelCausas(), null);
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
			panelCausas.add(getPanel_4(), BorderLayout.CENTER);
			
		}
		return panelCausas;
	}
	private JPanel getPanelVacunas() throws SQLException {
		if (panelVacunas == null) {
			panelVacunas = new JPanel();
			panelVacunas.setLayout(new GridLayout(1, 2, 0, 0));
			panelVacunas.add(getPanel_8());
			panelVacunas.add(getPanel_9());
			
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
	private JPanel getPnDatosHoraAcudio() {
		if (pnDatosHoraAcudio == null) {
			pnDatosHoraAcudio = new JPanel();
			pnDatosHoraAcudio.setLayout(new GridLayout(0, 2, 0, 0));
			pnDatosHoraAcudio.add(getPanIz_1());
			pnDatosHoraAcudio.add(getPanDe_1());
		}
		return pnDatosHoraAcudio;
	}
	private JPanel getPanIz_1() {
		if (pnIz == null) {
			pnIz = new JPanel();
			pnIz.setLayout(new GridLayout(0, 1, 0, 0));
			pnIz.add(getPnDatosPaciente());
			pnIz.add(getPnHoraEntrada());
			pnIz.add(getPnVacio11());
		}
		return pnIz;
	}
	private JLabel getLabel_4() {
		if (label == null) {
			label = new JLabel("Nombre:");
			label.setBounds(10, 21, 86, 26);
		}
		return label;
	}
	private JLabel getLblAcudi_1() {
		if (lblAcudi == null) {
			lblAcudi = new JLabel("Acudi\u00F3");
		}
		return lblAcudi;
	}
	private JPanel getPanDe_1() {
		if (pnDch == null) {
			pnDch = new JPanel();
			pnDch.setLayout(new GridLayout(0, 1, 0, 0));
			pnDch.add(getPnHistorial());
			pnDch.add(getPnHoraSalida());
			pnDch.add(getPnAcudio());
		}
		return pnDch;
	}
	private JTextField getTextField_2() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setBounds(74, 14, 203, 41);
			txtName.setText((String) null);
			txtName.setEditable(false);
			txtName.setColumns(10);
			txtName.setText(paciente.getNombre());
		}
		return txtName;
	}
	private JPanel getPnDatosPaciente() {
		if (pnDatosPaciente == null) {
			pnDatosPaciente = new JPanel();
			pnDatosPaciente.setBorder(new TitledBorder(null, "Paciente: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnDatosPaciente.setLayout(null);
			pnDatosPaciente.add(getLabel_4());
			pnDatosPaciente.add(getTextField_2());
			pnDatosPaciente.add(getLabel_1());
			pnDatosPaciente.add(getTxtApellido());
		}
		return pnDatosPaciente;
	}
	private JPanel getPnHistorial() {
		if (pnHistorial == null) {
			pnHistorial = new JPanel();
			pnHistorial.setLayout(new GridLayout(0, 2, 0, 0));
			pnHistorial.add(getPanel_5_1());
			pnHistorial.add(getPanel_5_2());
		}
		return pnHistorial;
	}
	private JPanel getPnHoraEntrada() {
		if (pnHoraEntrada == null) {
			pnHoraEntrada = new JPanel();
			pnHoraEntrada.setBorder(new TitledBorder(null, "Hora inicio:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnHoraEntrada.setLayout(new GridLayout(2, 2, 0, 0));
			pnHoraEntrada.add(getPnHoraInicio());
			pnHoraEntrada.add(getPnVacio());
			pnHoraEntrada.add(getPnHoraInicioF());
			pnHoraEntrada.add(getPanel_7_1());
		}
		return pnHoraEntrada;
	}
	private JPanel getPnHoraSalida() {
		if (pnHoraSalida == null) {
			pnHoraSalida = new JPanel();
			pnHoraSalida.setBorder(new TitledBorder(null, "Hora salida:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnHoraSalida.setLayout(new GridLayout(0, 2, 0, 0));
			pnHoraSalida.add(getPnHoraFin());
			pnHoraSalida.add(getPnVacio7());
			pnHoraSalida.add(getPnHoraFinF());
			pnHoraSalida.add(getPnGuardarHFin());
		}
		return pnHoraSalida;
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
			cbCausas.setSelectedIndex(0);
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
		
		añadirFilas(); // Añadimos a la tabla que nos muestra las preinscripciones que ya le hemos asignado
		
		limpiarPanel(); // Para ponerlo todo de 0
		
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
		String codAsignaPreinscripcion = "" + r.nextInt(800000);
		
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
		guardarInfoCita(); // Método que me guarda si acabó y si cambio la hora, la guarda también
	}







	private JPanel getPnAcudio() {
		if (pnAcudio == null) {
			pnAcudio = new JPanel();
			pnAcudio.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Acudi\u00F3: ", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnAcudio.setLayout(new GridLayout(3, 1, 0, 0));
			pnAcudio.add(getPanel_5());
			pnAcudio.add(getPanel_6());
			pnAcudio.add(getPanel_7());
		}
		return pnAcudio;
	}
	private JRadioButton getRdbtnAcudio() {
		if (rdbtnAcudio == null) {
			rdbtnAcudio = new JRadioButton("Acudio");
			rdbtnAcudio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			rdbtnAcudio.setBounds(248, 5, 67, 25);
			buttonGroup.add(rdbtnAcudio);
		}
		return rdbtnAcudio;
	}
	private JRadioButton getRdbtnNoAcudio() {
		if (rdbtnNoAcudio == null) {
			rdbtnNoAcudio = new JRadioButton("No acudio");
			rdbtnNoAcudio.setBounds(376, 5, 85, 25);
			buttonGroup.add(rdbtnNoAcudio);
		}
		return rdbtnNoAcudio;
	}
	private Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(60);
			horizontalStrut.setBounds(319, 17, 60, 12);
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
			guardarAccionCausa(causas);
	}
	
	private void guardarAccionCausa(String causas) throws SQLException {
		List<AccionEmpleado> devolverAccionesAdmin = pbd.devolverAccionesEmlpeado();
		int numeroAccion = 1;
		if(devolverAccionesAdmin.size()>0) {
			numeroAccion = devolverAccionesAdmin.size() + 1;
		}
		String naccion = "" +numeroAccion;
		
		String nombrePaciente = paciente.getNombre();
		String apellidoPaciente= paciente.getApellido();
		String codMed = cita.getCodMed();
		
		String nombre =pbd.devolverEmpleado(codMed).getNombre();
		String apellido =pbd.devolverEmpleado(codMed).getApellido();
		
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());	
		
		String mensajeAccion = "El médico " + nombre + " " +apellido  + " ha asignado al paciente " + nombrePaciente + " " 
		+ apellidoPaciente + " la siguiente causa" + causas;
				
		AccionEmpleado a = new AccionEmpleado(naccion, codMed,  fecha, hora, mensajeAccion);
		pbd.guardarAccionEmpleado(a);
		
	}
	
	public void ponerCausas() throws SQLException {
		nombresCausas = new ArrayList<>() ;
		List<String> causas = pbd.buscarNombreTodasCausas();
		for(int i =0; i< causas.size(); i++) {
			nombresCausas.add(causas.get(i));
		}
	}
	
	private JPanel getPnMostrarVacunas() {
		if (pnMostrarVacunas == null) {
			pnMostrarVacunas = new JPanel();
			pnMostrarVacunas.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			pnMostrarVacunas.add(getCbVacunas());
			pnMostrarVacunas.add(getBtnAsignarVacuna());
		}
		return pnMostrarVacunas;
	}
	private JPanel getPnVacio14() {
		if (pnVacio14 == null) {
			pnVacio14 = new JPanel();
		}
		return pnVacio14;
	}
	private JLabel getLabel_4_8() {
		if (lblVacuna == null) {
			lblVacuna = new JLabel("Vacuna:");
			lblVacuna.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblVacuna;
	}
	private JPanel getPn3() {
		if (pn3 == null) {
			pn3 = new JPanel();
			pn3.setLayout(new GridLayout(3, 1, 0, 0));
			pn3.add(getPanel_1());
			pn3.add(getPanel_3());
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
					restaurarCb();
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
			guardarAccionVacunas();
			for (AsignaVacuna av : asignaVacunasPaciente) { // Voy guardando cada una de las vacunas que le he asignado
				pbd.nuevaAsignaVacuna(av);
			}
		}
	}
	
	
	private void guardarAccionVacunas() throws SQLException {
		List<AccionEmpleado> devolverAccionesAdmin = pbd.devolverAccionesEmlpeado();
		int numeroAccion = 1;
		if(devolverAccionesAdmin.size()>0) {
			numeroAccion = devolverAccionesAdmin.size() + 1;
		}
		String naccion = "" +numeroAccion;
		
		String nombrePaciente = paciente.getNombre();
		String apellidoPaciente= paciente.getApellido();
		String codMed = cita.getCodMed();
		
		String nombre =pbd.devolverEmpleado(codMed).getNombre();
		String apellido =pbd.devolverEmpleado(codMed).getApellido();
		
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());	
		
		String mensajePreinscripciones = "";
		for(int i =0;i <asignaVacunasPaciente.size();i++ ) {
			mensajePreinscripciones += asignaVacunasPaciente.get(i).getCodVacuna() + ", ";
		}
		String mensajeAccion = "El médico " + nombre + " " +apellido  + " ha asignado al paciente " + nombrePaciente + " " 
		+ apellidoPaciente + " la siguiente vacuna" + mensajePreinscripciones;
				
		AccionEmpleado a = new AccionEmpleado(naccion, codMed,  fecha, hora, mensajeAccion);
		pbd.guardarAccionEmpleado(a);
		
	}
	/**
	 * Método para guardar las preinscripciones que se le han asignado a un paciente
	 * @throws SQLException 
	 */
	private void guardarPreinscripciones() throws SQLException {
		// Guardo las preinscripciones que le he asignado al paciente
		if (!asignaPreinscripcionesPaciente.isEmpty()) { // Que le hayamos asignado algo
			guardarAccionPreins();
			for (AsignaPreinscripcion ap : asignaPreinscripcionesPaciente) { // Voy guardando cada una de las preinscripciones que le he asignado
				pbd.nuevaAsignaPreinscripcion(ap);
				
			}
		}		
	}
	private void guardarAccionPreins() throws SQLException {
		List<AccionEmpleado> devolverAccionesAdmin = pbd.devolverAccionesEmlpeado();
		int numeroAccion = 1;
		if(devolverAccionesAdmin.size()>0) {
			numeroAccion = devolverAccionesAdmin.size() + 1;
		}
		String naccion = "" + numeroAccion;
		System.out.println("Numero acciones " + naccion);
		
		String nombrePaciente = paciente.getNombre();
		String apellidoPaciente= paciente.getApellido();
		String codMed = cita.getCodMed();
		
		String nombre =pbd.devolverEmpleado(codMed).getNombre();
		String apellido =pbd.devolverEmpleado(codMed).getApellido();
		
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());	
		
		String mensajePreinscripciones = "";
		for(int i =0;i <asignaPreinscripcionesPaciente.size();i++ ) {
			mensajePreinscripciones += asignaPreinscripcionesPaciente.get(i).getCodigoPreinscripcion() + ", ";
		}
		String mensajeAccion = "El médico " + nombre + " " +apellido  + " ha asignado al paciente " + nombrePaciente + " " 
		+ apellidoPaciente + " la siguiente preinscripción" + mensajePreinscripciones;
				
		AccionEmpleado a = new AccionEmpleado(naccion, codMed,  fecha, hora, mensajeAccion);
		//pbd.guardarAccionEmpleado(a);
		
	}
	private JPanel getPnDiagnosticos() {
		if (pnDiagnosticos == null) {
			pnDiagnosticos = new JPanel();
			pnDiagnosticos.setLayout(new GridLayout(4, 0, 2, 0));
			pnDiagnosticos = new JPanel();
			pnDiagnosticos.setLayout(new GridLayout(1, 2, 0, 0));
			pnDiagnosticos.add(getPanelDiagnosticos());
			pnDiagnosticos.add(getPanel_13());
		}
		return pnDiagnosticos;
	}
	
	private JPanel getPn3d() {
		if (pn3d == null) {
			pn3d = new JPanel();
			pn3d.setLayout(new GridLayout(3, 1, 0, 0));
			pn3d.add(getPnVacio20());
			pn3d.add(getPnVacio21());
			pn3d.add(getLabel_4_9());
		}
		return pn3d;
	}
	private JPanel getPnMostrarDiagnosticos() {
		if (pnMostrarDiagnosticos == null) {
			pnMostrarDiagnosticos = new JPanel();
			pnMostrarDiagnosticos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			pnMostrarDiagnosticos.add(getCbDiagnosticos());
			pnMostrarDiagnosticos.add(getBtnDiagnosticar());
		}
		return pnMostrarDiagnosticos;
	}
	private JPanel getPnVacio22() {
		if (pnVacio22 == null) {
			pnVacio22 = new JPanel();
		}
		return pnVacio22;
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
					restaurarCbDiagnosticos();
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
			guardarAccionDiagnosticos();
			for (AsignaDiagnostico ad : asignaDiagnosticosPaciente) { // Voy guardando cada uno de los diagnosticos
				pbd.nuevaAsignaDiagnostico(ad);
			}
		}	
	}
	
	private void guardarAccionDiagnosticos() throws SQLException {
		List<AccionEmpleado> devolverAccionesAdmin = pbd.devolverAccionesEmlpeado();
		int numeroAccion = 1;
		if(devolverAccionesAdmin.size()>0) {
			numeroAccion = devolverAccionesAdmin.size() + 1;
		}
		String naccion = "" + numeroAccion;
		
		String nombrePaciente = paciente.getNombre();
		String apellidoPaciente= paciente.getApellido();
		String codMed = cita.getCodMed();
		
		String nombre =pbd.devolverEmpleado(codMed).getNombre();
		String apellido =pbd.devolverEmpleado(codMed).getApellido();
		
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());	
		
		String mensajePreinscripciones = "";
		for(int i =0;i <asignaDiagnosticosPaciente.size();i++ ) {
			mensajePreinscripciones += asignaDiagnosticosPaciente.get(i).getnHistorial() + ", ";
		}
		String mensajeAccion = "El médico " + nombre + " " +apellido  + " ha asignado al paciente " + nombrePaciente + " " 
		+ apellidoPaciente + " la siguiente preinscripción" + mensajePreinscripciones;
				
		AccionEmpleado a = new AccionEmpleado(naccion, codMed,  fecha, hora, mensajeAccion);
		pbd.guardarAccionEmpleado(a);
		
	}

	public boolean isCausaSeleccionada() {
		return causaSeleccionada;
	}

	public void setCausaSeleccionada(boolean causaSeleccionada) {
		this.causaSeleccionada = causaSeleccionada;
	}
	
	
	/**
	 * Método para restaurar todos los valores por defecto de la preinscripcion cuando añado una
	 */
	private void limpiarPanel() {
		
		cbNombre.setSelectedIndex(0);
		spinnerCantidad.setValue(1);
		spinnerDuracion.setValue(1);
		spinnerIntervalo.setValue(0);
		textArea.setText("");
	}
	
	/**
	 * 	Método para poner el valor del cb en su valor de inicio
	 */
	protected void restaurarCb() {
		cbVacunas.setSelectedIndex(0);
		
	}
	
	
	/**
	 * Método para poner el valor del cb en su valor de inicio
	 */
	protected void restaurarCbDiagnosticos() {
		cbDiagnosticos.setSelectedIndex(0);		
	}
	
	
	private JPanel getPnHoraInicio() {
		if (pnHoraInicio == null) {
			pnHoraInicio = new JPanel();
			pnHoraInicio.setLayout(new GridLayout(0, 2, 0, 0));
			pnHoraInicio.add(getPnVacio2());
			pnHoraInicio.add(getSpinnerHInicioR());
		}
		return pnHoraInicio;
	}
	private JPanel getPnVacio() {
		if (pnVacio == null) {
			pnVacio = new JPanel();
		}
		return pnVacio;
	}
	private JPanel getPnVacio2() {
		if (pnVacio2 == null) {
			pnVacio2 = new JPanel();
		}
		return pnVacio2;
	}
	private JSpinner getSpinnerHInicioR() {
		if (spinnerHInicioR == null) {
			spinnerHInicioR = new JSpinner(new SpinnerDateModel());
						
			spinnerHInicioR.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			JSpinner.DateEditor de_timeSpinnerInicio = new JSpinner.DateEditor(spinnerHInicioR, "HH:mm");
			spinnerHInicioR.setEditor(de_timeSpinnerInicio);
			
			spinnerHInicioR.setValue(cita.gethInicio()); // Le ponemos la hora que tenía asignada del administrador
			
			return spinnerHInicioR;			
		}
		return spinnerHInicioR;
	}
	private JPanel getPnHoraInicioF() {
		if (pnHoraInicioF == null) {
			pnHoraInicioF = new JPanel();
			pnHoraInicioF.setLayout(new GridLayout(1, 0, 0, 0));
			pnHoraInicioF.add(getPnModificarHInicio());
			pnHoraInicioF.add(getSpinnerHInicioNueva());
		}
		return pnHoraInicioF;
	}
	private JPanel getPanel_7_1() {
		if (pnGuardarHInicio == null) {
			pnGuardarHInicio = new JPanel();
			pnGuardarHInicio.setLayout(new GridLayout(0, 2, 0, 0));
			pnGuardarHInicio.add(getBtnGuardarInicio());
		}
		return pnGuardarHInicio;
	}
	private JPanel getPnModificarHInicio() {
		if (pnModificarHInicio == null) {
			pnModificarHInicio = new JPanel();
			pnModificarHInicio.add(getBtnModificar());
		}
		return pnModificarHInicio;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarModificarHoraInicio();
				}
			});
		}
		return btnModificar;
	}
	
	
	/**
	 * Método que me activa para cambiar la hora de inicio
	 */
	private void activarModificarHoraInicio() {
		if (!spinnerHInicioNueva.isEnabled()) { // Primero comprobamos que no está ya enabled
			spinnerHInicioNueva.setEnabled(true);
		}	
		if (btnModificar.isEnabled()) {
			btnModificar.setEnabled(false);
		}
	}
	
	
	
	private JSpinner getSpinnerHInicioNueva() {
		if (spinnerHInicioNueva == null) {
			spinnerHInicioNueva = new JSpinner(new SpinnerDateModel());
			spinnerHInicioNueva.setEnabled(false);
			spinnerHInicioNueva.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			JSpinner.DateEditor de_timeSpinnerInicio = new JSpinner.DateEditor(spinnerHInicioNueva, "HH:mm");
			spinnerHInicioNueva.setEditor(de_timeSpinnerInicio);
			
			// La hora y fecha actuales
			Date fecha = new Date();	
			Time hora = new Time(new Date().getTime());
			
			spinnerHInicioNueva.setValue(hora); // Le ponemos la hora actual del sistema
			
			return spinnerHInicioNueva;
		}
		return spinnerHInicioNueva;
	}
	private JButton getBtnGuardarInicio() {
		if (btnGuardarInicio == null) {
			btnGuardarInicio = new JButton("Guardar");
			btnGuardarInicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guardarHoraInicioCita();
				}
			});
		}
		return btnGuardarInicio;
	}

	
	private JPanel getPnHoraFin() {
		if (pnHoraFin == null) {
			pnHoraFin = new JPanel();
			pnHoraFin.setLayout(new GridLayout(0, 2, 0, 0));
			pnHoraFin.add(getPnVacio6());
			pnHoraFin.add(getSpinnerHFinR());
		}
		return pnHoraFin;
	}
	private JPanel getPnVacio6() {
		if (pnVacio6 == null) {
			pnVacio6 = new JPanel();
		}
		return pnVacio6;
	}
	private JSpinner getSpinnerHFinR() {
		if (spinnerHFinR == null) {
			spinnerHFinR = new JSpinner(new SpinnerDateModel());
					
			spinnerHFinR.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			JSpinner.DateEditor de_timeSpinnerInicio = new JSpinner.DateEditor(spinnerHFinR, "HH:mm");
			spinnerHFinR.setEditor(de_timeSpinnerInicio);
			
			spinnerHFinR.setValue(cita.gethFin()); // Le ponemos la hora que tenía asignada del administrador
			
			return spinnerHFinR;
			
		}
		return spinnerHFinR;
	}
	private JPanel getPnVacio7() {
		if (pnVacio7 == null) {
			pnVacio7 = new JPanel();
		}
		return pnVacio7;
	}
	private JPanel getPnHoraFinF() {
		if (pnHoraFinF == null) {
			pnHoraFinF = new JPanel();
			pnHoraFinF.setLayout(new GridLayout(1, 0, 0, 0));
			pnHoraFinF.add(getPnModificarHoraFinF());
			pnHoraFinF.add(getSpinnerHFinNueva());
		}
		return pnHoraFinF;
	}
	private JPanel getPnModificarHoraFinF() {
		if (pnModificarHoraFinF == null) {
			pnModificarHoraFinF = new JPanel();
			pnModificarHoraFinF.add(getBtnModificarFin());
		}
		return pnModificarHoraFinF;
	}
	private JButton getBtnModificarFin() {
		if (btnModificarFin == null) {
			btnModificarFin = new JButton("Modificar");
			btnModificarFin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarModificarHoraFin();
				}
			});
		}
		return btnModificarFin;
	}
	
	
	/**
	 * Método para activar que pueda cambiar la hora de fin
	 */
	protected void activarModificarHoraFin() {
		
		if (!spinnerHFinNueva.isEnabled()) { // Si no estaba anteriormente enabled
			spinnerHFinNueva.setEnabled(true);
		}
		
		if (btnModificarFin.isEnabled()) {
			btnModificarFin.setEnabled(false);
		}
	}

	private JSpinner getSpinnerHFinNueva() {
		if (spinnerHFinNueva == null) {
			spinnerHFinNueva = new JSpinner(new SpinnerDateModel());
			spinnerHFinNueva.setEnabled(false);
			spinnerHFinNueva.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			JSpinner.DateEditor de_timeSpinnerInicio = new JSpinner.DateEditor(spinnerHFinNueva, "HH:mm");
			spinnerHFinNueva.setEditor(de_timeSpinnerInicio);
			
			// La hora y fecha actuales
			Date fecha = new Date();	
			Time hora = new Time(new Date().getTime());
			
			spinnerHFinNueva.setValue(hora); // Le ponemos la hora actual del sistema
			
			return spinnerHFinNueva;
		}
		return spinnerHFinNueva;
	}
	private JPanel getPnGuardarHFin() {
		if (pnGuardarHFin == null) {
			pnGuardarHFin = new JPanel();
			pnGuardarHFin.setLayout(new GridLayout(0, 2, 0, 0));
			pnGuardarHFin.add(getBtnGuardarFin());
		}
		return pnGuardarHFin;
	}
	private JButton getBtnGuardarFin() {
		if (btnGuardarFin == null) {
			btnGuardarFin = new JButton("Guardar");
			btnGuardarFin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guardarHoraFinCita();
				}
			});
		}
		return btnGuardarFin;
	}
	
	

	/**
	 * Método que me guarda localmente la hora de inicio de la cita que ha querido poner el médico
	 */
	protected void guardarHoraInicioCita() {
		if (spinnerHInicioNueva.isEnabled()) { // Si le ha dado a modificar la hora
			Date dateInicio = (Date) spinnerHInicioNueva.getValue();
			Time horaDeInicio = new Time(dateInicio.getTime());
			
			horaInicioCita = horaDeInicio;
		}
		
	}
	
	/**
	 * Método que me guarda localmente la hora de fin de la cita que ha querido poner el médico
	 */
	protected void guardarHoraFinCita() {
		if (spinnerHFinNueva.isEnabled()) { // Si le ha dado a modificar la hora
			Date dateInicio = (Date) spinnerHFinNueva.getValue();
			Time horaDeInicio = new Time(dateInicio.getTime());
			
			horaFinCita = horaDeInicio;
		}
		
	}

	
	/**
	 * Método que me guarda las información de la cita si se ha actualizado (si acudió o no y la hora de entrada y salida si la hubiese
	 * @throws SQLException 
	 */
	private void guardarInfoCita() throws SQLException {
		boolean acudio = false;
		boolean noAcudio = false;
		if(rdbtnAcudio.isSelected()) {
			acudio = true;
		}
		if(rdbtnNoAcudio.isSelected()) {
			noAcudio = true;
		}
		
		// Guardamos en la base de datos
		pbd.actualizarCita(horaInicioCita, horaFinCita, acudio, noAcudio, cita.getCodCita());
		
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Apellidos:");
			label_1.setBounds(315, 21, 68, 26);
		}
		return label_1;
	}
	private JTextField getTxtApellido() {
		if (txtApellido == null) {
			txtApellido = new JTextField();
			txtApellido.setText((String) null);
			txtApellido.setEditable(false);
			txtApellido.setColumns(10);
			txtApellido.setBounds(381, 13, 203, 41);
			txtApellido.setText(paciente.getApellido());
		}
		return txtApellido;
	}
	private JButton getBtnMostrarHistorial() {
		if (btnMostrarHistorial == null) {
			btnMostrarHistorial = new JButton("Mostrar historial");
			btnMostrarHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						mostrarHistorial();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnMostrarHistorial;
	}
	private JPanel getPnVacio11() {
		if (pnVacio11 == null) {
			pnVacio11 = new JPanel();
		}
		return pnVacio11;
	}
	private JPanel getPanel_5_1() {
		if (pnVacio8 == null) {
			pnVacio8 = new JPanel();
		}
		return pnVacio8;
	}
	private JPanel getPanel_5_2() {
		if (pnMostrarHistorialVacunas == null) {
			pnMostrarHistorialVacunas = new JPanel();
			pnMostrarHistorialVacunas.setLayout(new GridLayout(3, 1, 0, 0));
			pnMostrarHistorialVacunas.add(getPanel_5_3());
			pnMostrarHistorialVacunas.add(getPanel_5_4());
			pnMostrarHistorialVacunas.add(getPanel_5_5());
		}
		return pnMostrarHistorialVacunas;
	}
	private JPanel getPanel_5_3() {
		if (pnVacio9 == null) {
			pnVacio9 = new JPanel();
		}
		return pnVacio9;
	}
	private JPanel getPanel_5_4() {
		if (pnMostrar == null) {
			pnMostrar = new JPanel();
			pnMostrar.add(getBtnMostrarHistorial());
			pnMostrar.add(getBtnCalendarioVacunas());
		}
		return pnMostrar;
	}
	private JPanel getPanel_5_5() {
		if (pnVacio10 == null) {
			pnVacio10 = new JPanel();
		}
		return pnVacio10;
	}
	private JPanel getPanel_5() {
		if (pnVacio12 == null) {
			pnVacio12 = new JPanel();
		}
		return pnVacio12;
	}
	private JPanel getPanel_6() {
		if (pnSeleccionAcudio == null) {
			pnSeleccionAcudio = new JPanel();
			pnSeleccionAcudio.setLayout(null);
			pnSeleccionAcudio.add(getRdbtnAcudio());
			pnSeleccionAcudio.add(getHorizontalStrut());
			pnSeleccionAcudio.add(getRdbtnNoAcudio());
		}
		return pnSeleccionAcudio;
	}
	private JPanel getPanel_7() {
		if (pnVacio13 == null) {
			pnVacio13 = new JPanel();
		}
		return pnVacio13;
	}
	private JPanel getPanel_8() {
		if (pnFiltrarVacunas == null) {
			pnFiltrarVacunas = new JPanel();
			pnFiltrarVacunas.setLayout(new GridLayout(3, 0, 0, 0));
			pnFiltrarVacunas.add(getPanel_10());
			pnFiltrarVacunas.add(getPanel_11());
			pnFiltrarVacunas.add(getPanel_12());
		}
		return pnFiltrarVacunas;
	}
	private JPanel getPanel_9() {
		if (pnBuscarVacunaAsignar == null) {
			pnBuscarVacunaAsignar = new JPanel();
			pnBuscarVacunaAsignar.setLayout(new GridLayout(3, 1, 0, 0));
			pnBuscarVacunaAsignar.add(getPn3());
			pnBuscarVacunaAsignar.add(getPnMostrarVacunas());
			pnBuscarVacunaAsignar.add(getPnVacio14());
		}
		return pnBuscarVacunaAsignar;
	}
	private JPanel getPanel_10() {
		if (pnV1 == null) {
			pnV1 = new JPanel();
		}
		return pnV1;
	}
	private JPanel getPanel_11() {
		if (pnFiltrarV == null) {
			pnFiltrarV = new JPanel();
			pnFiltrarV.add(getTxtFiltrarVacunas());
			pnFiltrarV.add(getBtnFiltrarVacunas());
		}
		return pnFiltrarV;
	}
	private JPanel getPanel_12() {
		if (pnV2 == null) {
			pnV2 = new JPanel();
		}
		return pnV2;
	}
	private JTextField getTxtFiltrarVacunas() {
		if (txtFiltrarVacunas == null) {
			txtFiltrarVacunas = new JTextField();
			txtFiltrarVacunas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					activarBotonFiltrarVacunas();
				}
			});
			txtFiltrarVacunas.setToolTipText("Introduzca el nombre de la vacuna para buscarla");
			txtFiltrarVacunas.setColumns(10);
		}
		return txtFiltrarVacunas;
	}


	private JButton getBtnFiltrarVacunas() {
		if (btnFiltrarVacunas == null) {
			btnFiltrarVacunas = new JButton("Buscar");
			btnFiltrarVacunas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buscarVacuna();
				}
			});
			btnFiltrarVacunas.setEnabled(false);
		}
		return btnFiltrarVacunas;
	}
	
	
	
	/**
	 * Método que me busca si hay la vacuna que quiere el médico. 
	 * 		- Si la hay, la pone en el comboBox automáticamente
	 * 		- Si no la hay, muestra un mensaje al usuario diciendole que no existe
	 */
	protected void buscarVacuna() {
		if (!getTxtFiltrarVacunas().getText().equals("")) { // Si hay algo escrito en el campo de texto
			String buscador = getTxtFiltrarVacunas().getText().toLowerCase(); // Lo que ha buscado (lo pasamos a minuscula)
			boolean encontrada = false; // Para saber si encontró o no la vacuna buscada
			
			for(int i = 0; i < vacunas.size(); i++) {
				if (vacunas.get(i).getNombreVacuna().toLowerCase().equals(buscador)) { // Si lo que está buscando lo hay en la lista de vacunas

					cbVacunas.setSelectedIndex(i); // Lo mostramos en el cb
					encontrada = true; // la encontró

					//vacunaBuscada

				}
			}
			
			if (!encontrada) { // Si no encontró la vacuna
				JOptionPane.showMessageDialog(null, "No hemos podido encontrar su vacuna en este momento");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No ha introducido nada en el buscador");		
		}
	}

	/**
	 * Método que me activa el botón para buscar al filtrar vacunas
	 */
	protected void activarBotonFiltrarVacunas() {
		if (!btnFiltrarVacunas.isEnabled()) { // Si no estaba activado lo activamos
			btnFiltrarVacunas.setEnabled(true);
		}
	}
	private JPanel getPanel_1() {
		if (pnVacio15 == null) {
			pnVacio15 = new JPanel();
		}
		return pnVacio15;
	}
	private JPanel getPanel_3() {
		if (pnVacio16 == null) {
			pnVacio16 = new JPanel();
		}
		return pnVacio16;
	}
	private JPanel getPanelDiagnosticos() {
		if (pnFiltrarDiagnosticos == null) {
			pnFiltrarDiagnosticos = new JPanel();
			pnFiltrarDiagnosticos.setLayout(new GridLayout(3, 0, 0, 0));
			pnFiltrarDiagnosticos.add(getPanel_15());
			pnFiltrarDiagnosticos.add(getPanel_14());
			pnFiltrarDiagnosticos.add(getPanel_16());
		}
		return pnFiltrarDiagnosticos;
	}
	private JPanel getPanel_13() {
		if (pnBuscarDiagnosticosAsignar == null) {
			pnBuscarDiagnosticosAsignar = new JPanel();
			pnBuscarDiagnosticosAsignar.setLayout(new GridLayout(3, 1, 0, 0));
			pnBuscarDiagnosticosAsignar.add(getPn3d());
			pnBuscarDiagnosticosAsignar.add(getPnMostrarDiagnosticos());
			pnBuscarDiagnosticosAsignar.add(getPnVacio22());
		}
		return pnBuscarDiagnosticosAsignar;
	}
	private JPanel getPanel_14() {
		if (pnFiltrarD == null) {
			pnFiltrarD = new JPanel();
			pnFiltrarD.add(getTxtFiltrarDiagnosticos());
			pnFiltrarD.add(getBtnFiltrarDiagnosticos());
		}
		return pnFiltrarD;
	}
	private JPanel getPanel_15() {
		if (pnVacio18 == null) {
			pnVacio18 = new JPanel();
		}
		return pnVacio18;
	}
	private JPanel getPanel_16() {
		if (pnVacio19 == null) {
			pnVacio19 = new JPanel();
		}
		return pnVacio19;
	}
	private JTextField getTxtFiltrarDiagnosticos() {
		if (txtFiltrarDiagnosticos == null) {
			txtFiltrarDiagnosticos = new JTextField();
			txtFiltrarDiagnosticos.setToolTipText("Introduzca el nombre del diagn\u00F3stico para buscarlo");
			txtFiltrarDiagnosticos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					activarBotonFiltrarDiagnosticos();
				}
			});
			txtFiltrarDiagnosticos.setColumns(10);
		}
		return txtFiltrarDiagnosticos;
	}

	private JButton getBtnFiltrarDiagnosticos() {
		if (btnFiltrarDiagnosticos == null) {
			btnFiltrarDiagnosticos = new JButton("Buscar");
			btnFiltrarDiagnosticos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarDiagnostico();
				}
			});
			btnFiltrarDiagnosticos.setEnabled(false);
		}
		return btnFiltrarDiagnosticos;
	}
	
	


	/**
	 * Método para activar el buscador de los diagnosticos
	 */
	protected void activarBotonFiltrarDiagnosticos() {
		if (!btnFiltrarDiagnosticos.isEnabled()) { // Si no estaba ya activado
			btnFiltrarDiagnosticos.setEnabled(true);
		}
		
	}

	private JPanel getPnVacio20() {
		if (pnVacio20 == null) {
			pnVacio20 = new JPanel();
		}
		return pnVacio20;
	}
	private JPanel getPnVacio21() {
		if (pnVacio21 == null) {
			pnVacio21 = new JPanel();
		}
		return pnVacio21;
	}
	private JButton getBtnCalendarioVacunas() {
		if (btnCalendarioVacunas == null) {
			btnCalendarioVacunas = new JButton("Calendario vacunas");
			btnCalendarioVacunas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					verCalendarioVacunas();
				}
			});
		}
		return btnCalendarioVacunas;
	}

	/**
	 *Método para ver el calendario de vacunas del paciente
	 */
	protected void verCalendarioVacunas() {
		CalendarioVacunas cv = new CalendarioVacunas(paciente);
		cv.setLocationRelativeTo(null);
		cv.setResizable(true);
		cv.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		cv.setVisible(true);	
	}

	
	
	/**
	 * Método que me busca si hay el diagnostico que quiere el médico. 
	 * 		- Si lo hay, lo pone en el comboBox automáticamente
	 * 		- Si no lo hay, muestra un mensaje al usuario diciendole que no existe
	 */
	protected void buscarDiagnostico() {
		if (!getTxtFiltrarDiagnosticos().getText().equals("")) { // Si hay algo escrito en el campo de texto
			String buscador = getTxtFiltrarDiagnosticos().getText().toLowerCase(); // Lo que ha buscado (lo pasamos a minuscula)
			boolean encontrado = false; // Para saber si encontró o no el diagnóstico buscado
			
			for(int i = 0; i < diagnosticos.size(); i++) {
				if (diagnosticos.get(i).getNombre().toLowerCase().equals(buscador)) { // Si lo que está buscando lo hay en la lista de diagnosticos
					cbDiagnosticos.setSelectedIndex(i); // Lo mostramos en el cb
					encontrado = true; // lo encontró
				}
			}
			
			if (!encontrado) { // Si no encontró el diagnostico
				JOptionPane.showMessageDialog(null, "No hemos podido encontrar su diagnóstico en este momento");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No ha introducido nada en el buscador");		
		}
		
	}
	private void mostrarHistorial() throws SQLException {
		guardarAccionHist();
		HistorialMedico hm = pbd.HistorialCita(cita.getCodCita(),paciente.getCodePaciente());
		MostrarHistorial mh = new MostrarHistorial(hm);
		mh.setLocationRelativeTo(null);
		mh.setResizable(true);
		mh.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
		mh.setVisible(true);
	}
	
	private void guardarAccionHist() throws SQLException {
		List<AccionEmpleado> devolverAccionesAdmin = pbd.devolverAccionesEmlpeado();
		int numeroAccion = 1;
		if(devolverAccionesAdmin.size()>0) {
			numeroAccion = devolverAccionesAdmin.size() + 1;
		}
		String naccion = "" +numeroAccion;
		//String naccion = "" + (numeroAcciones.size() + 1);
		System.out.println("Numero acciones " + naccion);
		
		String nombrePaciente = paciente.getNombre();
		String apellidoPaciente= paciente.getApellido();
		String codMed = cita.getCodMed();
		
		String nombre =pbd.devolverEmpleado(codMed).getNombre();
		String apellido =pbd.devolverEmpleado(codMed).getApellido();
		
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());	
		
		
		String mensajeAccion = "El médico " + nombre + " " +apellido  + " ha visto el historial del paciente " + nombrePaciente + " " + apellidoPaciente;
		
		AccionEmpleado a = new AccionEmpleado(naccion, codMed,  fecha, hora, mensajeAccion);
		
		pbd.guardarAccionEmpleado(a);
		
	}
	
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.add(getLblNewLabel());
			panel_4.add(getTxtNombreCausa());
			panel_4.add(getBtnFiltrar());
		}
		return panel_4;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Filtrar causa");
			lblNewLabel.setBounds(11, 26, 77, 24);
		}
		return lblNewLabel;
	}
	private JTextField getTxtNombreCausa() {
		if (txtNombreCausa == null) {
			txtNombreCausa = new JTextField();
			txtNombreCausa.setBounds(98, 24, 197, 29);
			txtNombreCausa.setColumns(10);
		}
		return txtNombreCausa;
	}
	private JButton getBtnFiltrar() {
		if (btnFiltrar == null) {
			btnFiltrar = new JButton("Filtrar");
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txtNombreCausa.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Por favor introduce un valor");
					else {	
						filtrarPorNombre(txtNombreCausa.getText());
				}
				}
			});
			btnFiltrar.setBounds(316, 27, 89, 23);
		}
		return btnFiltrar;
	}
	
	private void filtrarPorNombre(String name) {
		for(int i=0; i < nombresCausas.size(); i++) {
			if(nombresCausas.get(i).equals(name)) {
				cbCausas.setSelectedIndex(i);
			}
		}
	}
	
	
	/*
	
	protected boolean isVacunado(String vacuna) {
		List<AsignaVacuna> vacunasHechas = new ArrayList<AsignaVacuna>();
		try {
			vacunasHechas = pbd.verVacunasPaciente(paciente.getHistorial());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 1; i<getTable().getColumnCount(); i++) {
			for(int j = 0; j<getTable().getRowCount(); j++) {
				for(int k = 0; k<vacunasHechas.size(); k++) {
					if(vacuna.equals(vacunasHechas.get(k).getNombreVacuna())) {
						
					}
				}
			}
		}
		
	}
	*/

	/**
	 * Clase para colorear las vacunas que ya han sido 
	 * @author roloa
	 *
	 */
	/*
	public class ColorearVacuna extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent(JTable table,
			      Object value,
			      boolean isSelected,
			      boolean hasFocus,
			      int row,
			      int column)
			   {
			      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
			      if ( isVacunado() )
			      {
			         this.setOpaque(true);
			         this.setBackground(Color.RED);
			         this.setForeground(Color.YELLOW);
			      } else {
			         // Restaurar los valores por defecto
			      }

			      return this;
			   }
	}
	*/
}