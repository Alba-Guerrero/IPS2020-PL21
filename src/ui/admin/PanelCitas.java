package ui.admin;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import logica.servicios.ParserBaseDeDatos;
import ui.inicio.VentanaInicio;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;

import logica.Cita;
import logica.Paciente;
import logica.empleados.Medico;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class PanelCitas extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel pnMedico;
	private JPanel panelAbajo;
	private JPanel panelAbajo1;
	private JPanel panelAbajo2;
	private JLabel lblHoraInicio;
	private JLabel lblHoraFin;
	private JLabel lblFecha;
	private JComboBox<Paciente> comboBox;
	private ParserBaseDeDatos pbd = new ParserBaseDeDatos();
	private VentanaInicio vi;
	private JScrollPane scrollPane;
	private DefaultListModel<Medico> modeloListaM;
	private JList<Medico> list;
	private JDateChooser dateCita;
	private JScrollPane scrollPane_descripcion;
	private JTextArea textArea_descripcion;
	private JButton btnCrearCita;
	private JCheckBox chckbxEsUrgente;
	private ArrayList<Medico> medicos;
	private JSpinner timeSpinnerInicio;
	private JSpinner timeSpinnerFin;
	private JPanel panel;
	private JLabel lblNombreDatos;
	private JLabel lblApellidosDatos;
	private JLabel lblTelefonoDatos;
	private JLabel lblCorreoDatos;
	private JLabel lblInfoAdicionalDatos;
	private JTextField txtFieldCorreoDatos;
	private JTextField txtFieldTelefonoDatos;
	private JTextField txtFieldApellidosDatos;
	private JTextField txtFieldNombreDatos;
	private JScrollPane scrrlPaneInfoAdicional;
	private JTextArea txtAreaInfoAdicionalDatos;
	private JButton btnEditarTelefonoDatos;
	private JButton btnEditarCorreoDatos;
	private Paciente pacienteCita;

	
	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public PanelCitas() throws SQLException {
		setTitle("Administrativo: citas");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 938, 739);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));
		{
			JPanel panelArriba = new JPanel();
			contentPanel.add(panelArriba);
			panelArriba.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JPanel panelArriba_1 = new JPanel();
				panelArriba.add(panelArriba_1);
				panelArriba_1.setLayout(new GridLayout(0, 2, 0, 0));
				panelArriba_1.add(getPanel_2());
				panelArriba_1.add(getPanel_1());
			}
			panelArriba.add(getPanel_3());
			panelArriba.add(getPnMedico());
		}
		contentPanel.add(getPanelAbajo());
		setContactData();
	}

	

	private JPanel getPnMedico() throws SQLException {
		if (pnMedico == null) {
			pnMedico = new JPanel();
			pnMedico.setBorder(new TitledBorder(null, "M\u00E9dico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnMedico.setSize(new Dimension(219, 200));
			pnMedico.setLayout(new BorderLayout(0, 0));
			pnMedico.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return pnMedico;
	}

	private JPanel getPanelAbajo() {
		if (panelAbajo == null) {
			panelAbajo = new JPanel();
			panelAbajo.setLayout(new GridLayout(0, 1, 0, 0));
			panelAbajo.add(getPanelAbajo1());
			panelAbajo.add(getPanelAbajo2());
		}
		return panelAbajo;
	}

	private JPanel getPanelAbajo1() {
		if (panelAbajo1 == null) {
			panelAbajo1 = new JPanel();
			panelAbajo1.setLayout(new BorderLayout(0, 0));
			panelAbajo1.add(getPanel(), BorderLayout.CENTER);
		}
		return panelAbajo1;
	}

	private JPanel getPanelAbajo2() {
		if (panelAbajo2 == null) {
			panelAbajo2 = new JPanel();
			panelAbajo2.setBorder(new TitledBorder(null, "Opciones de fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAbajo2.setLayout(null);
			panelAbajo2.add(getScrollPane_descripcion());
			panelAbajo2.add(getBtnCrearCita());
			panelAbajo2.add(getLblHoraInicio());

			panelAbajo2.add(getSpinnerInicio());

			panelAbajo2.add(getLblHoraFin());

			panelAbajo2.add(getTimeSpinnerF());

			panelAbajo2.add(getLblFecha());
			panelAbajo2.add(getDateCita());
			panelAbajo2.add(getChckbxEsUrgente());

			

		}
		return panelAbajo2;
	}

	private JScrollPane getScrollPane_descripcion() {
		if (scrollPane_descripcion == null) {
			scrollPane_descripcion = new JScrollPane();
			scrollPane_descripcion.setBounds(0, 81, 409, -81);
			scrollPane_descripcion.setViewportView(getTextArea_1());
		}
		return scrollPane_descripcion;
	}

	private JTextArea getTextArea_1() {
		if (textArea_descripcion == null) {
			textArea_descripcion = new JTextArea();
			textArea_descripcion.setEditable(false);
			textArea_descripcion.setLineWrap(true);
			textArea_descripcion.setWrapStyleWord(true);
		}
		return textArea_descripcion;
	}

	private JLabel getLblHoraInicio() {
		if (lblHoraInicio == null) {
			lblHoraInicio = new JLabel("Hora Inicio:");
			lblHoraInicio.setBounds(12, 66, 66, 16);
		}
		return lblHoraInicio;
	}

	private JSpinner getSpinnerInicio() {
		timeSpinnerInicio = new JSpinner(new SpinnerDateModel());
		timeSpinnerInicio.setBounds(97, 62, 81, 24);
		timeSpinnerInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JSpinner.DateEditor de_timeSpinnerInicio = new JSpinner.DateEditor(timeSpinnerInicio, "HH:mm");
		timeSpinnerInicio.setEditor(de_timeSpinnerInicio);
		timeSpinnerInicio.setValue(new Date());
		timeSpinnerInicio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(camposCubiertos())
					btnCrearCita.setEnabled(true);
			}

		});
		return timeSpinnerInicio;
	}

	private JLabel getLblHoraFin() {
		if (lblHoraFin == null) {
			lblHoraFin = new JLabel("Hora Fin:");
			lblHoraFin.setBounds(203, 66, 53, 16);
		}
		return lblHoraFin;
	}

	private JSpinner getTimeSpinnerF() {
		timeSpinnerFin = new JSpinner(new SpinnerDateModel());
		timeSpinnerFin.setBounds(267, 62, 81, 24);
		timeSpinnerFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JSpinner.DateEditor de_timeSpinnerFin = new JSpinner.DateEditor(timeSpinnerFin, "HH:mm");
		timeSpinnerFin.setEditor(de_timeSpinnerFin);
		timeSpinnerFin.setValue(new Date());
		timeSpinnerFin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(camposCubiertos())
					btnCrearCita.setEnabled(true);
			}
		});

		return timeSpinnerFin;

	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(381, 66, 39, 16);
		}
		return lblFecha;
	}

	private JComboBox<Paciente> getComboBox()  {
		if (comboBox == null) {
			comboBox = new JComboBox<Paciente>();
			comboBox.setBounds(37, 47, 344, 22);
			comboBox.setEditable(false);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setContactData();
				}
					
			});
			
			List<Paciente> pacientes=new ArrayList<>();
			try {
				pacientes = pbd.buscarPaciente("");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			for (int i = 0; i < pacientes.size(); i++) {
				comboBox.insertItemAt(pacientes.get(i), i);
			}
		}
		return comboBox;
	}

	private JScrollPane getScrollPane_1() throws SQLException {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setOpaque(false);
			scrollPane.setViewportView(getList_1());
		}
		return scrollPane;
	}

	private DefaultListModel<Medico> modeloListaM() throws SQLException {
		modeloListaM = new DefaultListModel<Medico>();
		List<Medico> medicos = pbd.buscarMedico("");
		for (int i = 0; i < medicos.size(); i++) {
			modeloListaM.addElement(medicos.get(i));

		}
		return modeloListaM;
	}

	private JDateChooser getDateCita() {
		if (dateCita == null) {
			dateCita = new JDateChooser();
			dateCita.setBounds(432, 60, 117, 22);
			dateCita.setDateFormatString("yyyy-MM-dd");
			dateCita.setDate(new Date());
			dateCita.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					if (dateCita.getDate() != null) {
						camposCubiertos();
					}
				}
			});

		}
		return dateCita;
	}

	private JButton getBtnCrearCita() {
		if (btnCrearCita == null) {
			btnCrearCita =new JButton("Crear cita");
			btnCrearCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if (!camposCubiertos())
						JOptionPane.showMessageDialog(null,"Por favor,revise todos los campos han sido rellenados correctamente");
					else
						if(hora()) {

							if (checkMedico()) {
								crearCita();
								JOptionPane.showMessageDialog(null, "Su cita se ha generado con exito");
								dispose();
							}
						}
				}
			});
			btnCrearCita.setEnabled(false);
			btnCrearCita.setBounds(779, 128, 97, 25);
		}
		return btnCrearCita;
	}

	private boolean checkMedico() {
		Date dateIncio = (Date) timeSpinnerInicio.getValue();
		Time timeInicio = new Time(dateIncio.getTime());
		
		Date dateFin = (Date) timeSpinnerFin.getValue();
		Time timeFin = new Time(dateFin.getTime());
		boolean cita = false;
		int jornadares = -1;
		int citares = -1;
		boolean jornada = false;

		for (int i = 0; i < medicos.size(); i++) {

			try {
				jornada = pbd.checkMedicoJornada(medicos.get(i).getCodeEmpleado(), timeInicio, timeFin);
			} catch (SQLException e1) {
	
				e1.printStackTrace();
			}
			if (!jornada) {
				jornadares = JOptionPane.showConfirmDialog(null,
						"No hay médico disponible en esa jornada laboral,¿Desea crear la cita igualmente? ",
						"Advertencia: Creación cita", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			}
			if (jornadares == 1)
				return false;
			try {

				cita = pbd.checkMedicoCita(medicos.get(i).getCodeEmpleado(), timeInicio, timeFin);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (cita) {
				citares = JOptionPane.showConfirmDialog(null,
						"No hay cita disponible en el hueco seleccionada, ¿Desea crear la cita igualmente? ",
						"Advertencia: Creación cita", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			}
			if (citares == 1)
				return false;

		}
		return true;

	}

	private JCheckBox getChckbxEsUrgente() {
		if (chckbxEsUrgente == null) {
			chckbxEsUrgente = new JCheckBox("Es urgente");
			chckbxEsUrgente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(chckbxEsUrgente.isEnabled()) {
						System.out.println("Se ha enviado un correo al medico");
					JOptionPane.showMessageDialog(null, "Se ha enviado un correo al medico");
					}
				}
			});
			chckbxEsUrgente.setBounds(587, 57, 113, 25);
		}
		return chckbxEsUrgente;
	}

	private JList<Medico> getList_1() throws SQLException {
		if (list == null) {
			modeloListaM();
			list = new JList<Medico>();
			list.setOpaque(false);
			list.setModel(modeloListaM);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					@SuppressWarnings("deprecation")
					Object[] selectedValues = list.getSelectedValues();
					if (selectedValues.length >= 0) {
						for (int i = 0; i < selectedValues.length; i++) {
							medicos = new ArrayList<Medico>();
							medicos.add((Medico) selectedValues[i]);

						}

					}

					camposCubiertos();

				}

			});
			
		}

		return list;
	}

	private boolean JlistMedicoFill() {
		if (medicos == null)
			return false;
		return medicos.size() > 0;
	}

	private boolean ComboBoxPacientes() {
		return comboBox.getSelectedItem() instanceof Paciente;

	}

	private boolean hora() {
		Date dateIncio = (Date) timeSpinnerInicio.getValue();
		Time timeInicio = new Time(dateIncio.getTime());

		Date dateFin = (Date) timeSpinnerFin.getValue();
		Time timeFin = new Time(dateFin.getTime());

		if (timeFin.compareTo(timeInicio) <= 0) {
			JOptionPane.showMessageDialog(null,
					"La fecha de inicio no puede ser igual o posterior a la fecha final.Por favor,modifíquelo y vuelva a intentarlo");
			btnCrearCita.setEnabled(false);
			return false;
		}
		return true;
	}

	
	
	private boolean ComboBoxSala() {
		return getCbSala().getSelectedItem() != null;

	}
	/**
	 * Metodo que comprueba que todos los cmapos obligatosio estan cubiertos y pone
	 * a enabled el boton de cita si asi es
	 * 
	 * @return
	 * @throws SQLException
	 */
	private boolean camposCubiertos() {
		if (JlistMedicoFill() && ComboBoxPacientes() &&ComboBoxSala() ) {
			btnCrearCita.setEnabled(true);
			return true;

		} else
			btnCrearCita.setEnabled(false);

		return false;

	}

	/**
	 * Metodo que crea la cita si tiene los cmapos cubiertos
	 * 
	 * @throws SQLException
	 */
	private void crearCita() {

		Date dateIncio = (Date) timeSpinnerInicio.getValue();
		Time timeInicio = new Time(dateIncio.getTime());

		Date dateFin = (Date) timeSpinnerFin.getValue();
		Time timeFin = new Time(dateFin.getTime());

		Date date = getDateCita().getDate();
		java.sql.Date sDate = new java.sql.Date(date.getTime());

		for (int i = 0; i < medicos.size(); i++) {
			Paciente p = (Paciente) comboBox.getSelectedItem();
			String sala = (String) getCbSala().getSelectedItem();
			Cita c;
			try {
				c = new Cita(p.getCodePaciente(), medicos.get(i).getCodeEmpleado(), timeInicio, timeFin, sDate, sala,
						chckbxEsUrgente.isSelected());
				pbd.crearCita(c);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Datos del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(null);
			panel.add(getLabel_1());
			panel.add(getLabel_2());
			panel.add(getLabel_3());
			panel.add(getLabel_4());
			panel.add(getLabel_5());
			panel.add(getTxtFieldCorreoDatos());
			panel.add(getTxtFieldTelefonoDatos());
			panel.add(getTxtFieldApellidosDatos());
			panel.add(getTxtFieldNombreDatos());
			panel.add(getScrollPane_1_1());
			panel.add(getBtnEditarTelefonoDatos());
			panel.add(getBtnEditarCorreoDatos());
			panel.add(getBtnActualizarDatos());
		}
		return panel;
	}
	private JLabel getLabel_1() {
		if (lblNombreDatos == null) {
			lblNombreDatos = new JLabel("Nombre: ");
			lblNombreDatos.setBounds(20, 30, 61, 14);
		}
		return lblNombreDatos;
	}
	private JLabel getLabel_2() {
		if (lblApellidosDatos == null) {
			lblApellidosDatos = new JLabel("Apellidos:");
			lblApellidosDatos.setBounds(20, 61, 67, 14);
		}
		return lblApellidosDatos;
	}
	private JLabel getLabel_3() {
		if (lblTelefonoDatos == null) {
			lblTelefonoDatos = new JLabel("Tel\u00E9fono:");
			lblTelefonoDatos.setBounds(20, 92, 67, 14);
		}
		return lblTelefonoDatos;
	}
	private JLabel getLabel_4() {
		if (lblCorreoDatos == null) {
			lblCorreoDatos = new JLabel("Correo electr\u00F3nico:");
			lblCorreoDatos.setBounds(20, 123, 110, 14);
		}
		return lblCorreoDatos;
	}
	private JLabel getLabel_5() {
		if (lblInfoAdicionalDatos == null) {
			lblInfoAdicionalDatos = new JLabel("Informaci\u00F3n adicional:");
			lblInfoAdicionalDatos.setBounds(441, 30, 132, 14);
		}
		return lblInfoAdicionalDatos;
	}
	private JTextField getTxtFieldCorreoDatos() {
		if (txtFieldCorreoDatos == null) {
			txtFieldCorreoDatos = new JTextField();
			txtFieldCorreoDatos.setEditable(false);
			txtFieldCorreoDatos.setBounds(140, 120, 190, 20);
			txtFieldCorreoDatos.setColumns(10);
		}
		return txtFieldCorreoDatos;
	}
	private JTextField getTxtFieldTelefonoDatos() {
		if (txtFieldTelefonoDatos == null) {
			txtFieldTelefonoDatos = new JTextField();
			txtFieldTelefonoDatos.setEditable(false);
			txtFieldTelefonoDatos.setBounds(140, 89, 190, 20);
			txtFieldTelefonoDatos.setColumns(10);
			
		}
		return txtFieldTelefonoDatos;
	}
	private JTextField getTxtFieldApellidosDatos() {
		if (txtFieldApellidosDatos == null) {
			txtFieldApellidosDatos = new JTextField();
			txtFieldApellidosDatos.setEditable(false);
			txtFieldApellidosDatos.setBounds(140, 58, 190, 20);
			txtFieldApellidosDatos.setColumns(10);
		}
		return txtFieldApellidosDatos;
	}
	private JTextField getTxtFieldNombreDatos() {
		if (txtFieldNombreDatos == null) {
			txtFieldNombreDatos = new JTextField();
			txtFieldNombreDatos.setEditable(false);
			txtFieldNombreDatos.setBounds(140, 27, 190, 20);
			txtFieldNombreDatos.setColumns(10);
			//txtFieldNombreDatos.setText(((Paciente)comboBox.getSelectedItem()).getNombre());
		}
		return txtFieldNombreDatos;
	}
	private JScrollPane getScrollPane_1_1() {
		if (scrrlPaneInfoAdicional == null) {
			scrrlPaneInfoAdicional = new JScrollPane();
			scrrlPaneInfoAdicional.setBounds(571, 24, 190, 112);
			scrrlPaneInfoAdicional.setViewportView(getTextArea_1_1());
		}
		return scrrlPaneInfoAdicional;
	}
	private JTextArea getTextArea_1_1() {
		if (txtAreaInfoAdicionalDatos == null) {
			txtAreaInfoAdicionalDatos = new JTextArea();
			txtAreaInfoAdicionalDatos.setLineWrap(true);
			txtAreaInfoAdicionalDatos.setWrapStyleWord(true);
		}
		return txtAreaInfoAdicionalDatos;
	}
	private JButton getBtnEditarTelefonoDatos() {
		if (btnEditarTelefonoDatos == null) {
			btnEditarTelefonoDatos = new JButton("Editar");
			btnEditarTelefonoDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtFieldTelefonoDatos.setEditable(true);
				}
			});
			btnEditarTelefonoDatos.setBounds(340, 88, 74, 23);
		}
		return btnEditarTelefonoDatos;
	}
	private JButton getBtnEditarCorreoDatos() {
		if (btnEditarCorreoDatos == null) {
			btnEditarCorreoDatos = new JButton("Editar");
			btnEditarCorreoDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtFieldCorreoDatos.setEditable(true);
				}
			});
			btnEditarCorreoDatos.setBounds(340, 119, 74, 23);
		}
		return btnEditarCorreoDatos;
	}
	
	/**
	 * Comprobamos si se han cambiado los campos de contacto
	 * @return
	 */
	private boolean checkCambiosInfo() {
		Paciente p = null;
		if(comboBox.getSelectedItem() instanceof Paciente)
			p = (Paciente) comboBox.getSelectedItem();
		
		return Integer.parseInt(getTxtFieldTelefonoDatos().getText()) != p.getMovil() || !(getTxtFieldCorreoDatos().getText().equals(p.getEmail()));
		
	}
	
	private void setContactData()  {
		if(getComboBox().getSelectedIndex()!=-1) {
			if(comboBox.getSelectedItem() instanceof Paciente)
				pacienteCita = (Paciente) comboBox.getSelectedItem();
			getTxtFieldNombreDatos().setText(pacienteCita.getNombre());
			getTxtFieldApellidosDatos().setText(pacienteCita.getApellido());
			getTxtFieldTelefonoDatos().setText(pacienteCita.getMovil()+"");
			getTxtFieldCorreoDatos().setText(pacienteCita.getEmail());
			getTextArea_1_1().setText(pacienteCita.getInfo());
		}
	
	}
	
	private JButton btnActualizarDatos;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JComboBox cbSala;
	
	
	private JButton getBtnActualizarDatos() {
		if (btnActualizarDatos == null) {
			btnActualizarDatos = new JButton("Actualizar");
			btnActualizarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkCambiosInfo()) {
						Paciente p = null;
						if(comboBox.getSelectedItem() instanceof Paciente)
							p = (Paciente) comboBox.getSelectedItem();
						
							try {
								ParserBaseDeDatos.updateDatosContacto(Integer.parseInt(getTxtFieldTelefonoDatos().getText()), getTxtFieldCorreoDatos().getText(), getTextArea_1_1().getText(),p.getCodePaciente());
							} catch (NumberFormatException | SQLException e1) {
								e1.printStackTrace();
							}
						
					}
				}
			});
			btnActualizarDatos.setBounds(800, 135, 100, 23);
		}
		return btnActualizarDatos;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Introduce la sala", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setLayout(null);
			panel_1.add(getCbSala());
		}
		return panel_1;
	}
	private JComboBox<String> getCbSala() {
		if (cbSala == null) {
			List<String> salas= rellenarSalas();
			cbSala = new JComboBox<String>();
			cbSala.setBounds(54, 47, 236, 22);
			for (int i = 0; i < salas.size(); i++) {
				cbSala.insertItemAt(salas.get(i), i);
			}
		}
		return cbSala;
	}
	
	private List<String> rellenarSalas() {
		List<String> salas = new ArrayList<String>();
		for(int i = 1; i < 4; i++) {
			salas.add("Quirófano " + i);
			salas.add("Sala de rayos " + i);
			salas.add("Pediatría " + i);
		}

		salas.add("Digestivo");
		salas.add("Oncología");
		salas.add("Uroología");
		salas.add("Cardiología");
		return salas;
	}
	
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setLayout(null);
			panel_2.add(getComboBox());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
		}
		return panel_3;
	}
}
