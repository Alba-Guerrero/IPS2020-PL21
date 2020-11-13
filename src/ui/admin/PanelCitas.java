package ui.admin;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import logica.servicios.ParserBaseDeDatos;
import ui.inicio.VentanaInicio;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;

import logica.Acompañante;
import logica.Cita;
import logica.Email;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

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
	private ParserBaseDeDatos pbd = new ParserBaseDeDatos();
	private VentanaInicio vi;
	private JScrollPane scrollPane;
	private DefaultListModel<Medico> modeloListaM;
	private DefaultListModel<Paciente>modeloListaPaciente;
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
	private Acompañante acompañante;
	
	List<String> salas;

	
	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public PanelCitas() throws SQLException {
		setTitle("Administrativo: citas");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 959, 739);
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
				panelArriba_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panelArriba.add(panelArriba_1);
				panelArriba_1.setLayout(new GridLayout(0, 2, 0, 0));
				panelArriba_1.add(getPanel_2());
				panelArriba_1.add(getScrollPane_1_2());
			}
			panelArriba.add(getPnMedico());
		}
		contentPanel.add(getPanelAbajo());
		getContentPane().add(getScrollPane_1_3());
		//setContactData();
	}

	

	private JPanel getPnMedico() throws SQLException {
		if (pnMedico == null) {
			pnMedico = new JPanel();
			pnMedico.setBorder(new TitledBorder(null, "M\u00E9dico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnMedico.setSize(new Dimension(219, 200));
			pnMedico.setLayout(new GridLayout(0, 2, 0, 0));
			pnMedico.add(getPanel_3());
			pnMedico.add(getScrollPane_1());
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
			panelAbajo2.add(getCbSala());
			panelAbajo2.add(getLblNewLabel());
			panelAbajo2.add(getLblNombreSala());
			panelAbajo2.add(getTxtNombreSala());
			panelAbajo2.add(getBtnFiltrar());

			

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
			lblHoraInicio.setBounds(12, 52, 66, 16);
		}
		return lblHoraInicio;
	}

	private JSpinner getSpinnerInicio() {
		timeSpinnerInicio = new JSpinner(new SpinnerDateModel());
		timeSpinnerInicio.setBounds(90, 48, 81, 24);
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
			lblHoraFin.setBounds(205, 52, 53, 16);
		}
		return lblHoraFin;
	}

	private JSpinner getTimeSpinnerF() {
		timeSpinnerFin = new JSpinner(new SpinnerDateModel());
		timeSpinnerFin.setBounds(270, 48, 81, 24);
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
			lblFecha.setBounds(387, 52, 39, 16);
		}
		return lblFecha;
	}

	private JScrollPane getScrollPane_1() throws SQLException {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setOpaque(false);
			scrollPane.setViewportView(getList_1());
		}
		return scrollPane;
	}

	private DefaultListModel<Medico> modeloListaM(List<Medico> medico) throws SQLException {
		modeloListaM = new DefaultListModel<Medico>();
		if(medico!=null) {
		List<Medico> medicos = medico;
		for (int i = 0; i < medicos.size(); i++) {
			modeloListaM.addElement(medicos.get(i));

		}
		list.setModel(modeloListaM);
		}
		if(modeloListaM.getSize()==0)
			JOptionPane.showMessageDialog(null, "No se ha encontrado ningún médico con esas características");
		return modeloListaM;
	}
	
	private DefaultListModel<Paciente> modeloListaPaciente(List<Paciente> pacientes) throws SQLException {
		modeloListaPaciente = new DefaultListModel<Paciente>();
		if(pacientes!=null) {
		List<Paciente> paciente =pacientes;
		for (int i = 0; i < paciente.size(); i++) {
			modeloListaPaciente.addElement(paciente.get(i));

		}
		listPaciente.setModel(modeloListaPaciente);
		}
	if(modeloListaPaciente.getSize()==0)
			JOptionPane.showMessageDialog(null, "No se ha encontrado ningún paciente con esas características");
		
		return modeloListaPaciente;
	}

	private JDateChooser getDateCita() {
		if (dateCita == null) {
			dateCita = new JDateChooser();
			dateCita.setBounds(438, 42, 124, 30);
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
					
					if(chckbxEsUrgente.isSelected()) {
						System.out.println("Se ha enviado un correo al medico");
					JOptionPane.showMessageDialog(null, "Se ha enviado un correo al medico");
					}
				}
			});
			chckbxEsUrgente.setBounds(602, 48, 113, 25);
		}
		return chckbxEsUrgente;
	}

	private JList<Medico> getList_1() throws SQLException {
		if (list == null) {
			list = new JList<Medico>();
			list.setBackground(Color.WHITE);
			modeloListaM(pbd.buscarMedico(""));
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

	/*
	private boolean ComboBoxPacientes() {
		return comboBox.getSelectedItem() instanceof Paciente;

	}
	*/

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
		if (JlistMedicoFill() &&ComboBoxSala() ) {
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
			
			String sala = (String) getCbSala().getSelectedItem();
			Cita c;
			try {
				c = new Cita(pacienteCita.getCodePaciente(), medicos.get(i).getCodeEmpleado(), timeInicio, timeFin, sDate, sala,
						chckbxEsUrgente.isSelected());
				pbd.crearCita(c);
				if(c.isUrgente())
					Email.enviarCorreo("roloalvarez7@gmail.com", "sbeiaolebhiewuzz", "UO266007@uniovi.es", pacienteCita, c);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos de contacto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setLayout(null);
			panel.add(getLabel_1());
			panel.add(getLabel_5());
			panel.add(getLabel_2());
			panel.add(getLabel_3());
			panel.add(getLabel_4());
			panel.add(getTxtFieldCorreoDatos());
			panel.add(getTxtFieldTelefonoDatos());
			panel.add(getTxtFieldApellidosDatos());
			panel.add(getTxtFieldNombreDatos());
			//panel.add(getTextArea_1_1(), "flowx,cell 3 5 3 7");
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
			lblNombreDatos.setBounds(25, 28, 54, 16);
		}
		return lblNombreDatos;
	}
	private JLabel getLabel_2() {
		if (lblApellidosDatos == null) {
			lblApellidosDatos = new JLabel("Apellidos:");
			lblApellidosDatos.setBounds(23, 63, 56, 16);
		}
		return lblApellidosDatos;
	}
	private JLabel getLabel_3() {
		if (lblTelefonoDatos == null) {
			lblTelefonoDatos = new JLabel("Tel\u00E9fono:");
			lblTelefonoDatos.setBounds(23, 98, 55, 16);
		}
		return lblTelefonoDatos;
	}
	private JLabel getLabel_4() {
		if (lblCorreoDatos == null) {
			lblCorreoDatos = new JLabel("Correo electr\u00F3nico:");
			lblCorreoDatos.setBounds(10, 130, 110, 16);
		}
		return lblCorreoDatos;
	}
	private JLabel getLabel_5() {
		if (lblInfoAdicionalDatos == null) {
			lblInfoAdicionalDatos = new JLabel("Informaci\u00F3n adicional:");
			lblInfoAdicionalDatos.setBounds(404, 57, 127, 16);
		}
		return lblInfoAdicionalDatos;
	}
	private JTextField getTxtFieldCorreoDatos() {
		if (txtFieldCorreoDatos == null) {
			txtFieldCorreoDatos = new JTextField();
			txtFieldCorreoDatos.setBounds(127, 127, 180, 22);
			txtFieldCorreoDatos.setEditable(false);
			txtFieldCorreoDatos.setColumns(10);
			
		}
		return txtFieldCorreoDatos;
	}
	private JTextField getTxtFieldTelefonoDatos() {
		if (txtFieldTelefonoDatos == null) {
			txtFieldTelefonoDatos = new JTextField();
			txtFieldTelefonoDatos.setBounds(127, 95, 180, 22);
			txtFieldTelefonoDatos.setEditable(false);
			txtFieldTelefonoDatos.setColumns(10);
		
		}
		return txtFieldTelefonoDatos;
	}
	private JTextField getTxtFieldApellidosDatos() {
		if (txtFieldApellidosDatos == null) {
			txtFieldApellidosDatos = new JTextField();
			txtFieldApellidosDatos.setBounds(127, 60, 180, 22);
			txtFieldApellidosDatos.setEditable(false);
			txtFieldApellidosDatos.setColumns(10);
			
		}
		return txtFieldApellidosDatos;
	}
	private JTextField getTxtFieldNombreDatos() {
		if (txtFieldNombreDatos == null) {
			txtFieldNombreDatos = new JTextField();
			txtFieldNombreDatos.setBounds(127, 25, 180, 22);
			txtFieldNombreDatos.setEditable(false);
			txtFieldNombreDatos.setColumns(10);
		}
		return txtFieldNombreDatos;
	}
	private JScrollPane getScrollPane_1_1() {
		if (scrrlPaneInfoAdicional == null) {
			scrrlPaneInfoAdicional = new JScrollPane();
			scrrlPaneInfoAdicional.setBounds(540, 28, 220, 113);
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
			btnEditarTelefonoDatos.setBounds(321, 94, 65, 25);
			btnEditarTelefonoDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtFieldTelefonoDatos.setEditable(true);
				}
			});
		}
		return btnEditarTelefonoDatos;
	}
	private JButton getBtnEditarCorreoDatos() {
		if (btnEditarCorreoDatos == null) {
			btnEditarCorreoDatos = new JButton("Editar");
			btnEditarCorreoDatos.setBounds(321, 126, 65, 25);
			btnEditarCorreoDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtFieldCorreoDatos.setEditable(true);
				}
			});
		}
		return btnEditarCorreoDatos;
	}
	
	/**
	 * Comprobamos si se han cambiado los campos de contacto
	 * @return
	 */
	
	private boolean checkCambiosInfo() {
		if(!(pacienteCita instanceof Paciente))
			return false;
		
		if(!getTxtFieldTelefonoDatos().getText().equals(""))
			return Integer.parseInt(getTxtFieldTelefonoDatos().getText()) != pacienteCita.getMovil() ||
			!(getTxtFieldCorreoDatos().getText().equals(pacienteCita.getEmail()));
		return false;
	}
	
	private void setContactData()  {
		if(acompañante instanceof Acompañante) {
			
			getTxtFieldNombreDatos().setText(acompañante.getNombre());
			getTxtFieldApellidosDatos().setText(acompañante.getApellido());
			getTxtFieldTelefonoDatos().setText(acompañante.getMovil()+"");
			getTxtFieldCorreoDatos().setText(acompañante.getEmail());
			//getTextArea_1_1().setText(acompañante.getInfo());
		}
		else {
			getTxtFieldNombreDatos().setText(pacienteCita.getNombre());
			getTxtFieldApellidosDatos().setText(pacienteCita.getApellido());
			getTxtFieldTelefonoDatos().setText(pacienteCita.getMovil()+"");
			getTxtFieldCorreoDatos().setText(pacienteCita.getEmail());
			}
	
	}
	
	
	private JButton btnActualizarDatos;
	private JPanel panel_2;
	private JPanel panel_3;
	private JComboBox cbSala;
	private JLabel lblFiltroNombre;
	private JTextField txtFieldNombreFiltro;
	private JButton btnFiltrarNombre;
	private JLabel lblApellidoFiltro;
	private JTextField txtFieldApellidoFiltro;
	private JButton btnFiltrarApellidos;
	private JLabel lblNewLabel;
	private JScrollPane scrollPanePacientes;
	private JLabel lblCodHistorial;
	private JTextField txtFieldCodHistorial;
	private JButton btnFiltrarHistorial;
	private JLabel lblNombreFiltroMedico;
	private JLabel lblApellidoMedicoFiltro;
	private JTextField txtFieldNombreMedicoFiltro;
	private JTextField txtFieldApellidoMedicoFiltro;
	private JButton btnFiltrarNombreMedico;
	private JButton btnFiltrarApellidoMedico;
	private JList<Paciente> listPaciente;
	private JLabel lblNombreSala;
	private JTextField txtNombreSala;
	private JButton btnFiltrar;
	private JScrollPane scrollPane_1;
	
	
	private JButton getBtnActualizarDatos() {
		if (btnActualizarDatos == null) {
			btnActualizarDatos = new JButton("Actualizar");
			btnActualizarDatos.setBounds(805, 126, 89, 25);
			btnActualizarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkCambiosInfo()) {
						if(pacienteCita instanceof Paciente)
							
						
							try {
								if(!getTxtFieldTelefonoDatos().getText().equals(""))
								ParserBaseDeDatos.updateDatosContacto(Integer.parseInt(getTxtFieldTelefonoDatos().getText()), getTxtFieldCorreoDatos().getText(),
										getTextArea_1_1().getText(),pacienteCita.getCodePaciente());
							} catch (NumberFormatException | SQLException e1) {
								e1.printStackTrace();
							}
						
					}
				}
			});
		}
		return btnActualizarDatos;
	}
	private JComboBox<String> getCbSala() {
		if (cbSala == null) {
			cbSala = new JComboBox<String>();
			cbSala.setBounds(90, 103, 236, 22);
			salas= rellenarSalas();
			String[] array = salas.toArray( new String[salas.size()] );
			cbSala.setModel(new DefaultComboBoxModel<String>(array));
			
			
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
		salas.add("Urología");
		salas.add("Cardiología");
		return salas;
	}
	
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new MigLayout("", "[93px][222px][82px]", "[23px][][23px][23px][]"));
			panel_2.add(getLblFiltroNombre(), "cell 0 1,alignx center,aligny center");
			panel_2.add(getTxtFieldNombreFiltro(), "cell 1 1,growx,aligny top");
			panel_2.add(getBtnFiltrarNombre(), "cell 2 1,grow");
			panel_2.add(getLblApellidoFiltro(), "cell 0 2,alignx center,aligny center");
			panel_2.add(getTxtFieldApellidoFiltro(), "cell 1 2,growx,aligny top");
			panel_2.add(getBtnFiltrarApellidos(), "cell 2 2,grow");
			panel_2.add(getLblCodHistorial(), "cell 0 3,alignx left,aligny center");
			panel_2.add(getTxtFieldCodHistorial(), "cell 1 3,growx,aligny top");
			panel_2.add(getBtnFiltrarHistorial(), "cell 2 3,grow");
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new MigLayout("", "[59px][239px][82px]", "[23px][23px][][][][][][]"));
			panel_3.add(getLblNombreFiltroMedico(), "cell 0 1,growx,aligny center");
			panel_3.add(getTextField_2(), "cell 1 1,growx,aligny top");
			panel_3.add(getBtnFiltrarNombreMedico(), "cell 2 1,grow");
			panel_3.add(getLblApellidoMedicoFiltro(), "cell 0 4,growx,aligny center");
			panel_3.add(getTextField_1_1(), "cell 1 4,growx,aligny top");
			panel_3.add(getBtnFiltrarApellidoMedico(), "cell 2 4,grow");
		}
		return panel_3;
	}
	private JLabel getLblFiltroNombre() {
		if (lblFiltroNombre == null) {
			lblFiltroNombre = new JLabel("Nombre:");
		}
		return lblFiltroNombre;
	}
	private JTextField getTxtFieldNombreFiltro() {
		if (txtFieldNombreFiltro == null) {
			txtFieldNombreFiltro = new JTextField();
			txtFieldNombreFiltro.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
						btnFiltrarNombre.setEnabled(true);
				}
			});
			txtFieldNombreFiltro.setColumns(10);
		}
		return txtFieldNombreFiltro;
	}
	private JButton getBtnFiltrarNombre() {
		if (btnFiltrarNombre == null) {
			btnFiltrarNombre = new JButton("Filtrar");
			btnFiltrarNombre.setEnabled(false);
			btnFiltrarNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						modeloListaPaciente(pbd.buscarPacienteNombre(txtFieldNombreFiltro.getText()));
						txtFieldNombreFiltro.setText("");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		}
		return btnFiltrarNombre;
	}
	private JLabel getLblApellidoFiltro() {
		if (lblApellidoFiltro == null) {
			lblApellidoFiltro = new JLabel("Apellido:");
		}
		return lblApellidoFiltro;
	}
	private JTextField getTxtFieldApellidoFiltro() {
		if (txtFieldApellidoFiltro == null) {
			txtFieldApellidoFiltro = new JTextField();
			txtFieldApellidoFiltro.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {						btnFiltrarApellidos.setEnabled(true);
					
				}
			});
			txtFieldApellidoFiltro.setColumns(10);
		}
		return txtFieldApellidoFiltro;
	}
	private JButton getBtnFiltrarApellidos() {
		if (btnFiltrarApellidos == null) {
			btnFiltrarApellidos = new JButton("Filtrar");
			btnFiltrarApellidos.setEnabled(false);
			btnFiltrarApellidos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						modeloListaPaciente(pbd.buscarPacienteApellido(txtFieldApellidoFiltro.getText()));
						txtFieldApellidoFiltro.setText("");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return btnFiltrarApellidos;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Sala:");
			lblNewLabel.setBounds(32, 107, 46, 14);
		}
		return lblNewLabel;
	}
	private JScrollPane getScrollPane_1_2() {
		if (scrollPanePacientes == null) {
			scrollPanePacientes = new JScrollPane();
			scrollPanePacientes.setViewportView(getList_1_1());
		}
		return scrollPanePacientes;
	}
	private JLabel getLblCodHistorial() {
		if (lblCodHistorial == null) {
			lblCodHistorial = new JLabel("Codigo historial:");
		}
		return lblCodHistorial;
	}
	private JTextField getTxtFieldCodHistorial() {
		if (txtFieldCodHistorial == null) {
			txtFieldCodHistorial = new JTextField();
			txtFieldCodHistorial.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {					btnFiltrarHistorial.setEnabled(true);
				}
			});
			txtFieldCodHistorial.setColumns(10);
		}
		return txtFieldCodHistorial;
	}
	private JButton getBtnFiltrarHistorial() {
		if (btnFiltrarHistorial == null) {
			btnFiltrarHistorial = new JButton("Filtrar");
			btnFiltrarHistorial.setEnabled(false);
			btnFiltrarHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						modeloListaPaciente(pbd.pacienteHistorial(txtFieldCodHistorial.getText()));
						txtFieldCodHistorial.setText("");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnFiltrarHistorial;
	}
	private JLabel getLblNombreFiltroMedico() {
		if (lblNombreFiltroMedico == null) {
			lblNombreFiltroMedico = new JLabel("Nombre:");
		}
		return lblNombreFiltroMedico;
	}
	private JLabel getLblApellidoMedicoFiltro() {
		if (lblApellidoMedicoFiltro == null) {
			lblApellidoMedicoFiltro = new JLabel("Apellido:");
		}
		return lblApellidoMedicoFiltro;
	}
	private JTextField getTextField_2() {
		if (txtFieldNombreMedicoFiltro == null) {
			txtFieldNombreMedicoFiltro = new JTextField();
			txtFieldNombreMedicoFiltro.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnFiltrarNombreMedico.setEnabled(true);
				}
			});
				
			
			
			txtFieldNombreMedicoFiltro.setColumns(10);
		}
		return txtFieldNombreMedicoFiltro;
	}
	private JTextField getTextField_1_1() {
		if (txtFieldApellidoMedicoFiltro == null) {
			txtFieldApellidoMedicoFiltro = new JTextField();
			txtFieldApellidoMedicoFiltro.setColumns(10);
			txtFieldApellidoMedicoFiltro.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnFiltrarApellidoMedico.setEnabled(true);
				}
				
			});
			
		}
		return txtFieldApellidoMedicoFiltro;
	}
	private JButton getBtnFiltrarNombreMedico() {
		if (btnFiltrarNombreMedico == null) {
			btnFiltrarNombreMedico = new JButton("Filtrar");
			btnFiltrarNombreMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txtFieldNombreMedicoFiltro.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Por favor introduce un valor");
					else {	
					
						try {
							modeloListaM(pbd.devolverMedicoNombre(txtFieldNombreMedicoFiltro.getText()));
							txtFieldNombreMedicoFiltro.setText("");
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
					
				}
				}});
			btnFiltrarNombreMedico.setEnabled(false);
		}
		return btnFiltrarNombreMedico;
	}
	private JButton getBtnFiltrarApellidoMedico() {
		if (btnFiltrarApellidoMedico == null) {
			btnFiltrarApellidoMedico = new JButton("Filtrar");
			btnFiltrarApellidoMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(txtFieldApellidoMedicoFiltro.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Por favor introduce un valor");
				else {	
				
					try {
						modeloListaM(pbd.devolverMedicoApellido(txtFieldApellidoMedicoFiltro.getText()));
						txtFieldApellidoMedicoFiltro.setText("");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				}
			});
			btnFiltrarApellidoMedico.setEnabled(false);
		}
		return btnFiltrarApellidoMedico;
	}
	private JList<Paciente> getList_1_1() {
		if (listPaciente == null) {
			listPaciente = new JList<Paciente>();
			modeloListaPaciente=new DefaultListModel<Paciente>();
			try {
				modeloListaPaciente(pbd.buscarPaciente(""));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			listPaciente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			try {
				modeloListaPaciente(pbd.buscarPaciente(""));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listPaciente.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					 pacienteCita= listPaciente.getSelectedValue();
					 try {
						acompañante = pbd.getAcompañante(pacienteCita.getCodePaciente());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					 setContactData();
				}
			
		});
		}
		return listPaciente;
	}
	private JLabel getLblNombreSala() {
		if (lblNombreSala == null) {
			lblNombreSala = new JLabel("Nombre sala");
			lblNombreSala.setBounds(399, 103, 72, 22);
		}
		return lblNombreSala;
	}
	private JTextField getTxtNombreSala() {
		if (txtNombreSala == null) {
			txtNombreSala = new JTextField();
			txtNombreSala.setBounds(483, 102, 147, 25);
			txtNombreSala.setColumns(10);
		}
		return txtNombreSala;
	}
	private JButton getBtnFiltrar() {
		if (btnFiltrar == null) {
			btnFiltrar = new JButton("Filtrar");
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txtNombreSala.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Por favor introduce un valor");
					else {	
						filtrarPorNombre(txtNombreSala.getText());
				}
				}
			});
			btnFiltrar.setHorizontalAlignment(SwingConstants.RIGHT);
			btnFiltrar.setBounds(642, 103, 66, 23);
		}
		return btnFiltrar;
	}
	
	private void filtrarPorNombre(String name) {
		for(int i=0; i < salas.size(); i++) {
			if(salas.get(i).equals(name)) {
				cbSala.setSelectedIndex(i);
			}
		}
	}
	private JScrollPane getScrollPane_1_3() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(contentPanel);
		}
		return scrollPane_1;
	}
}
