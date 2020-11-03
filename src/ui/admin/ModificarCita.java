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
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;

public class ModificarCita extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel pnMedico;
	private JPanel panelAbajo;
	private JPanel panelAbajo2;
	private JLabel lblHoraInicio;
	private JLabel lblHoraFin;
	private JLabel lblFecha;
	private ParserBaseDeDatos pbd = new ParserBaseDeDatos();
	private VentanaInicio vi;
	private JScrollPane scrollPane;
	private DefaultListModel<Medico> modeloListaM;
	private JList<Medico> list;
	private JDateChooser dateCita;
	private JScrollPane scrollPane_descripcion;
	private JTextArea textArea_descripcion;
	private JButton btnModificar;
	private JCheckBox chckbxEsUrgente;
	private ArrayList<Medico> medicos;
	private JSpinner timeSpinnerInicio;
	private JSpinner timeSpinnerFin;
	private Paciente pacienteCita;
	private JPanel panel;
	private JTextField textField;
	private JButton btnFiltrarPorNombre;
	private JButton btnFiltrarPorApellido;
	private JPanel panel_1;
	private JLabel lblEscogeLaSala;
	private JComboBox comboBox;
	private Paciente p;
	private String codcita;
	private JTextField textField_1;
	private JButton btnFiltrarPorNombre_1;


	/**
	 * Create the dialog.
	 * @param c 
	 * @param p 
	 * 
	 * @throws SQLException
	 */
	public ModificarCita(Paciente p, String codcita) throws SQLException {
		this.p=p;
		this.codcita=codcita;
		setTitle("Administrativo: Modificar cita");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 914, 554);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));
		{
			JPanel panelArriba = new JPanel();
			contentPanel.add(panelArriba);
			panelArriba.setLayout(new GridLayout(0, 1, 0, 0));
			panelArriba.add(getPanel());
			panelArriba.add(getPnMedico());
		}
		contentPanel.add(getPanelAbajo());
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
			panelAbajo.add(getPanelAbajo2());
			panelAbajo.add(getPanel_1());
		}
		return panelAbajo;
	}

	private JPanel getPanelAbajo2() {
		if (panelAbajo2 == null) {
			panelAbajo2 = new JPanel();
			panelAbajo2.setBorder(new TitledBorder(null, "Opciones de fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAbajo2.setLayout(null);
			panelAbajo2.add(getScrollPane_descripcion());
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

		return timeSpinnerFin;

	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(381, 66, 39, 16);
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

	private DefaultListModel<Medico> modeloListaM() throws SQLException {
		modeloListaM = new DefaultListModel<Medico>();
		List<Medico> medicos = pbd.buscarMedico("");
		for (int i = 0; i < medicos.size(); i++) {
			modeloListaM.addElement(medicos.get(i));

		}
		return modeloListaM;
	}
	
	
	private DefaultListModel<Medico> modeloListaNombre(String name) throws SQLException {
		modeloListaM = new DefaultListModel<Medico>();
		List<Medico> medicos = pbd.devolverMedicoNombre(name);
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
		}
		return dateCita;
	}

	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar =new JButton("Modificar cita");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {


					if(hora()) {

						if (checkMedico()) {
							UpdateCita();;
							JOptionPane.showMessageDialog(null, "Su cita se ha modificado con éxito");
							dispose();
						}
					}
				}
			});
			btnModificar.setEnabled(false);
		}
		return btnModificar;
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

						}    }
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



	private boolean hora() {
		Date dateIncio = (Date) timeSpinnerInicio.getValue();
		Time timeInicio = new Time(dateIncio.getTime());

		Date dateFin = (Date) timeSpinnerFin.getValue();
		Time timeFin = new Time(dateFin.getTime());

		if (timeFin.compareTo(timeInicio) <= 0) {
			JOptionPane.showMessageDialog(null,
					"La fecha de inicio no puede ser igual o posterior a la fecha final.Por favor,modifíquelo y vuelva a intentarlo");
			btnModificar.setEnabled(false);
			return false;
		}
		return true;
	}



	//CAMBBIARRRRRRR

	/**
	 * Metodo que crea la cita si tiene los cmapos cubiertos
	 * 
	 * @throws SQLException
	 */
	private void UpdateCita() {

		Date dateIncio = (Date) timeSpinnerInicio.getValue();
		Time timeInicio = new Time(dateIncio.getTime());

		Date dateFin = (Date) timeSpinnerFin.getValue();
		Time timeFin = new Time(dateFin.getTime());

		Date date = getDateCita().getDate();
		java.sql.Date sDate = new java.sql.Date(date.getTime());

		for (int i = 0; i < medicos.size(); i++) {

			Cita c;
			try {
				c = new Cita(p.getCodePaciente(), medicos.get(i).getCodeEmpleado(), timeInicio, timeFin, sDate,"",
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
			panel.setBorder(new TitledBorder(null, "Filtrar m\u00E9dico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(new MigLayout("", "[116px][grow][][][][][]", "[22px][][]"));
			panel.add(getTextField(), "cell 1 0,alignx left");
			panel.add(getBtnFiltrarPorNombre(), "cell 4 0");
			panel.add(getTextField_1(), "cell 1 1,grow");
			panel.add(getBtnFiltrarPorApellido(), "cell 4 1");
			panel.add(getBtnFiltrarPorNombre_1(), "cell 4 2");
		}
		return panel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(25);
		}
		return textField;
	}
	private JButton getBtnFiltrarPorNombre() {
		if (btnFiltrarPorNombre == null) {
			btnFiltrarPorNombre = new JButton("Filtrar por nombre");
			btnFiltrarPorNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(textField.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Por favor introduce un valor");
					else {	
					try {
						modeloListaNombre(textField.getText());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
				}
			});
		}
		return btnFiltrarPorNombre;
	}
	private JButton getBtnFiltrarPorApellido() {
		if (btnFiltrarPorApellido == null) {
			btnFiltrarPorApellido = new JButton("Filtrar por apellido");
		}
		return btnFiltrarPorApellido;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new MigLayout("", "[109px][grow][][][][][][][][][][][][][][][][][][][][][][][]", "[25px][][]"));
			panel_1.add(getLblEscogeLaSala(), "cell 0 1,alignx trailing");
			panel_1.add(getComboBox(), "cell 1 1 6 1,growx");
			panel_1.add(getBtnModificar(), "cell 23 2,alignx left,aligny top");
		}
		return panel_1;
	}
	private JLabel getLblEscogeLaSala() {
		if (lblEscogeLaSala == null) {
			lblEscogeLaSala = new JLabel("Escoge la sala:   ");
		}
		return lblEscogeLaSala;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
		}
		return comboBox;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(20);
		}
		return textField_1;
	}
	private JButton getBtnFiltrarPorNombre_1() {
		if (btnFiltrarPorNombre_1 == null) {
			btnFiltrarPorNombre_1 = new JButton("Filtrar por nombre y  apellido");
		}
		return btnFiltrarPorNombre_1;
	}
}
