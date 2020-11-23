package ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TabableView;

import logica.Accion;
import logica.Cita;
import logica.Equipo;
import logica.HistorialMedico;
import logica.Paciente;
import logica.empleados.Empleado;

import logica.servicios.ParserBaseDeDatos;
import logica.servicios.PrescripcionesToPDF;
import net.sf.jasperreports.engine.JRException;
import ui.MostrarHistorial;
import ui.medico.ModeloNoEditable;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.FlowLayout;
import javax.swing.JComboBox;

public class VentanaVerCita extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tablacita;
	private ModeloNoEditable modeloTabla;
	private ParserBaseDeDatos pbd=new ParserBaseDeDatos();
	private JPanel panel;
	private JPanel panelCita;
	private JLabel lblNewLabel;
	private JDateChooser dateChooser;
	private JButton btnIr;
	private List<Cita> codcitas= new ArrayList<Cita>();
	private JPanel panelBotones;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnTodasLasCitas;
	private JTextField txtNDeHistorial;
	private JButton irHistorial;
	private JButton btnBuscarPorFecha;
	private JButton btnVerHistorial;
	private String codAdmin;
	private JPanel panelFiltrosMedEn;
	private JLabel lblFiltrar;
	private JRadioButton rdbtnMdico;
	private JRadioButton rdbtnEnfermero;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelMedEnfer;
	private JPanel pnNombre;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JButton btnIr_1;
	private JPanel pnApellido;
	private JPanel panelVacio;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private JButton BtnApellido;
	private JPanel pnCodEmpleado;
	private JLabel lblCodempleado;
	private JTextField txtEmpleado;
	private JButton btnCodEmpleado;
	private JPanel pnNombreApellido;
	private JButton btnNombreYApellido;
	private JPanel panelEspecialidad;
	private JComboBox comboBox;
	private JPanel panelNomEspe;
	private JLabel lblEspecialidad;
	private JTextField txtEspecialidad;
	


	/**
	 * Create the frame.
	 * @param codmedico 
	 */
	public VentanaVerCita(String codAdmin) {
		this.codAdmin = codAdmin;
		setTitle("M\u00E9dico: Ver citas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1274, 516);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));
		contentPane.add(getPanel());
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableCita());
		}
		return scrollPane;
	}
	public JTable getTableCita() {
			if (tablacita == null) {
				String[] nombreColumnas= {"Nombre paciente "," Apellido paciente  ","Hora inicio"," Hora fin","Fecha ","Ubicación ","Nombre médico","Nombre equipo", "Urgencia","Codcita","CodPaciente","CodMed"};
				modeloTabla= new ModeloNoEditable(nombreColumnas,0);
				tablacita = new JTable(modeloTabla);
				tablacita.getTableHeader().setReorderingAllowed(false);//Evita que se pueda mpver las columnas
				tablacita.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tablacita.getTableHeader().setBackground(Color.LIGHT_GRAY);
				
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablacita.getModel());
				tablacita.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
				for (int i = 8; i < 11; i++) {
					tablacita.getColumnModel().getColumn(i).setMinWidth(0);
					tablacita.getColumnModel().getColumn(i).setMaxWidth(0);
					tablacita.getColumnModel().getColumn(i).setWidth(0);
				}

				añadirFilas(false);
				
				tablacita.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						int fila=tablacita.getSelectedRow();
						if(fila!=-1) {
							btnEliminar.setEnabled(true);
							btnModificar.setEnabled(true);
							btnVerHistorial.setEnabled(true);
								try {
									Paciente p=pbd.devolverPacientesMedico((String)tablacita.getValueAt(tablacita.getSelectedRow(), 9));
								} catch (SQLException e) {
									e.printStackTrace();
								}
						}
					}
				});
			
				
				
				
			
			}
			return tablacita;
		}
	
	private void borrarModeloTabla() {
		int filas=modeloTabla.getRowCount();
			for (int i = 0; i <filas; i++) {
				modeloTabla.removeRow(0);
				
			}
	}
	
	
	public void añadirFilas(boolean dia)  {
		borrarModeloTabla();
		Object[] nuevaFila=new Object[12];
		List<Cita> citas = new ArrayList<Cita>();
	if(dia) {
		Date date = getDateChooser().getDate();
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		try {
			citas = pbd.devolvercitasPorFecha(sDate);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		borrarCitasConEquipo(citas);
		try {
			List<Cita> citasConEquip = pbd.devolvercitasEquipoPorFecha(sDate);
			System.out.println("Con equipo " + citasConEquip.size());
			for(int i = 0; i<citasConEquip.size(); i++) {
				if(citasConEquip.get(i).getNumequipo()!=null)
					citas.add(citasConEquip.get(i));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	else {
		try {
			citas = pbd.devolverCitas();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		borrarCitasConEquipo(citas);
		try {
			añadirCitasConEquipo(citas);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
		for(Cita c:citas) {
			Paciente p = null;
			Empleado empleado=null;
			Equipo equipo = null;
			try {
				if(c.getCodMed()==null) {
					p = pbd.devolverPacientesEquipo(c.getCodPaciente());
					equipo = pbd.devolverEquipo(c.getNumequipo());
				}
				else {
					p = pbd.devolverPacientesMedico(c.getCodCita());
					empleado=pbd.devolverEmpleado(c.getCodMed());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			nuevaFila[0] = p.getNombre();
			nuevaFila[1]= p.getApellido();
			nuevaFila[2] = c.gethInicio();
			nuevaFila[3] =c.gethFin();
			nuevaFila[4] =c.getDate();
			nuevaFila[5] =c.getUbicacion();
			if(empleado!=null) {
				nuevaFila[6] = empleado.getNombre()+"  " +empleado.getApellido();
			}
			else {
				nuevaFila[6] = "";
			}
			
			if(equipo!=null) {
				nuevaFila[7] = equipo.getNumEquipo();
			}
			else {
				nuevaFila[7] = "";
			}
			nuevaFila[8] = c.isUrgente();
			nuevaFila[9]=c.getCodCita();
			nuevaFila[10]=c.getCodPaciente();
			nuevaFila[11]=c.getCodMed();
			modeloTabla.addRow(nuevaFila);
			}
		}
		
	
	private void borrarCitasConEquipo(List<Cita> citas) {
		for(int i = 0; i<citas.size(); i++) {
			if(citas.get(i).getCodMed()==null)
				citas.remove(citas.get(i));
		}
	}

	private void añadirCitasConEquipo(List<Cita> citas) throws SQLException {
		List<Cita> citasConEquip = pbd.devolverCitasConEquipo();
		for(int i = 0; i<citasConEquip.size(); i++) {
			if(citasConEquip.get(i).getNumequipo()!=null)
				citas.add(citasConEquip.get(i));
		}
	}
	
	private void añadirCitasConEquipoFiltroDia(List<Cita> citas, java.sql.Date sDate) throws SQLException {
		List<Cita> citasConEquip = pbd.devolvercitasEquipoPorFecha(sDate);
		for(int i = 0; i<citasConEquip.size(); i++) {
			if(citasConEquip.get(i).getNumequipo()!=null)
				citas.add(citasConEquip.get(i));
		}
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_1_1(), BorderLayout.WEST);
			panel.add(getPanelBotones(), BorderLayout.SOUTH);
			panel.add(getPanelCita(), BorderLayout.NORTH);
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JPanel getPanelCita() {
		if (panelCita == null) {
			panelCita = new JPanel();
			panelCita.add(getLblNewLabel());
			panelCita.add(getDateChooser());
			panelCita.add(getBtnIr());
			panelCita.add(getBtnTodasLasCitas());
			panelCita.add(getTxtNDeHistorial());
			panelCita.add(getIrHistorial());
			panelCita.add(getBtnBuscarPorFecha());
		}
		return panelCita;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Escoga el  d\u00EDa de la cita que desea ver");
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblNewLabel;
	}
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(new Date());
			
		}
		return dateChooser;
	}
	private JButton getBtnIr() {
		if (btnIr == null) {
			btnIr = new JButton("Ir");
			btnIr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					añadirFilas(true);
				}
			});
		}
		return btnIr;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.add(getBtnVerHistorial());
			panelBotones.add(getBtnModificar());
			panelBotones.add(getBtnEliminar());
		}
		return panelBotones;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar cita");
			btnEliminar.setEnabled(false);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila=tablacita.getSelectedRow();
					if(fila!=-1) {
					int res=JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea borrar la cita?","Mensaje de confirmación",JOptionPane.YES_NO_OPTION);
					if(res==JOptionPane.YES_OPTION) {
						try {
							pbd.BorrarCita((String)tablacita.getValueAt(tablacita.getSelectedRow(), 9));
							guardarAccionElimCita();
							añadirFilas(false);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						toFront();
					}
						
					}
					
					
				}

				private void guardarAccionElimCita() throws SQLException {
					List<Accion> devolverAccionesAdmin = pbd.devolverAccionesAdmin();
					int numeroAccion = 1;
					if(devolverAccionesAdmin.size()>0) {
						numeroAccion = devolverAccionesAdmin.size() + 1;
					}
					String naccion = "" +numeroAccion;
					
					String nombrePaciente=(String) tablacita.getValueAt(tablacita.getSelectedRow(), 0);
					String apellidoPaciente=(String) tablacita.getValueAt(tablacita.getSelectedRow(), 1);
					
					String nombreMedico=(String) tablacita.getValueAt(tablacita.getSelectedRow(), 6);
					
					Date fecha = new Date();	
					Time hora = new Time(new Date().getTime());	
					
					
					String mensajeAccion = "El aministrador " + codAdmin + " ha eliminado la cita del paciente " + nombrePaciente + " " + apellidoPaciente
							+ " con el médico " + nombreMedico;
					
					Accion a = new Accion(naccion, codAdmin,  fecha, hora, mensajeAccion);
					
					pbd.guardarAccion(a);
					
				}
			});
		}
		return btnEliminar;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar cita");
			btnModificar.setEnabled(false);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int fila=tablacita.getSelectedRow();
					if(fila!=-1) {
						Paciente p;
							try {
								if(tablacita.getValueAt(tablacita.getSelectedRow(),6).equals("")) {
									System.out.println(fila);
									p=pbd.devolverPacientesEquipo((String)tablacita.getValueAt(tablacita.getSelectedRow(),10));
								}
								else {
									p=pbd.devolverPacientesMedico((String)tablacita.getValueAt(tablacita.getSelectedRow(),9));
								}
								//System.err.println(p.getNombre() +" "+p.getApellido()+" "+p.getCodePaciente());
								Cita c=pbd.citaCod((String)tablacita.getValueAt(tablacita.getSelectedRow(),9),
										(String)tablacita.getValueAt(tablacita.getSelectedRow(),10));
								System.err.println((String)tablacita.getValueAt(tablacita.getSelectedRow(),10)+" "+c.getDate()+" "+c.gethFin());
								VentanaModificarCita(p,c);
								
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
				}
					
				
			});
		}
		return btnModificar;
	}
	
	
protected void VentanaModificarCita(Paciente p,Cita c) throws SQLException {
		
		ModificarCita mc = new ModificarCita(this, p,c);
		mc.setVisible(true);
		mc.setLocationRelativeTo(this);
		
	}
	private JButton getBtnTodasLasCitas() {
		if (btnTodasLasCitas == null) {
			btnTodasLasCitas = new JButton("Todas las citas");
			btnTodasLasCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					añadirFilas(false);
				}
			});
		}
		return btnTodasLasCitas;
	}
	
	
	
	
	private void añadirFilasHistorial()  {
		borrarModeloTabla();
		Object[] nuevaFila=new Object[12];
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = pbd.devolvercitasHistorial(txtNDeHistorial.getText());
			System.out.println("Todas " + citas.size());
		} catch (SQLException e) {

			e.printStackTrace();
		}
//		borrarCitasConEquipo(citas);
//		System.out.println("Sin equipos " + citas.size());
//		try {
//			añadirCitasConEquipo(citas, txtNDeHistorial.getText());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println("Con equipos " + citas.size());
		
	
		for(Cita c:citas) {
			Paciente p = null;
			Empleado empleado=null;
			Equipo equipo=null;
			try {
				if(c.getCodMed()==null) {
					p = pbd.devolverPacientesEquipo(c.getCodPaciente());
					equipo = pbd.devolverEquipo(c.getNumequipo());
				}
				else {
					p = pbd.devolverPacientesMedico(c.getCodCita());
					empleado=pbd.devolverEmpleado(c.getCodMed());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	
			nuevaFila[0] = p.getNombre();
			nuevaFila[1]= p.getApellido();
			nuevaFila[2] = c.gethInicio();
			nuevaFila[3] =c.gethFin();
			nuevaFila[4] =c.getDate();
			nuevaFila[5] =c.getUbicacion();
			if(empleado!=null) {
				nuevaFila[6] = empleado.getNombre()+"  " +empleado.getApellido();
			}
			else {
				nuevaFila[6] = "";
			}
			
			if(equipo!=null) {
				nuevaFila[7] = equipo.getNumEquipo();
			}
			else {
				nuevaFila[7] = "";
			}
			nuevaFila[8] = c.isUrgente();
			nuevaFila[9]=c.getCodCita();
			nuevaFila[10]=c.getCodPaciente();
			nuevaFila[11]=c.getCodMed();
			modeloTabla.addRow(nuevaFila);
		}
		
		}
	
	
	private void añadirCitasConEquipo(List<Cita> citas, String text) throws SQLException {
		List<Cita> citasConEquip = pbd.devolverCitasConEquipoHist(text);
		for(int i = 0; i<citasConEquip.size(); i++) {
			if(citasConEquip.get(i).getNumequipo()!=null)
				citas.add(citasConEquip.get(i));
		}
	}

	private void añadirFilasHistorialFecha()  {
		borrarModeloTabla();
		Object[] nuevaFila=new Object[11];
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = pbd.devolvercitasHistorialFechas(txtNDeHistorial.getText(),dateChooser.getDate());
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	
	
		for(Cita c:citas) {
			Paciente p = null;
			Empleado empleado=null;
			try {
				p = pbd.devolverPacientesMedico(c.getCodCita());
				empleado=pbd.devolverEmpleado(c.getCodMed());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	
			nuevaFila[0] = p.getNombre();
			nuevaFila[1]= p.getApellido();
			nuevaFila[2] = c.gethInicio();
			nuevaFila[3] =c.gethFin();
			nuevaFila[4] =c.getDate();
			nuevaFila[5] =c.getUbicacion();
			nuevaFila[6] = empleado.getNombre()+"  " +empleado.getApellido();
			nuevaFila[7] = c.isUrgente();
			nuevaFila[8]=c.getCodCita();
			nuevaFila[9]=c.getCodPaciente();
			nuevaFila[10]=c.getCodMed();
			
			modeloTabla.addRow(nuevaFila);
		}
		
		}
		
		
	
	private JTextField getTxtNDeHistorial() {
		if (txtNDeHistorial == null) {
			txtNDeHistorial = new JTextField();
			txtNDeHistorial.setText("N\u00BA de historial");
			txtNDeHistorial.setColumns(10);
			txtNDeHistorial.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtNDeHistorial.setText("");
				}
			});
			
			
			
		}
		return txtNDeHistorial;
	}
	private JButton getIrHistorial() {
		if (irHistorial == null) {
			irHistorial = new JButton("Ir");
			irHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txtNDeHistorial.getText().equals("")|| txtNDeHistorial.getText().equals("N\u00BA de historial"))
						JOptionPane.showMessageDialog(null, "Por favor, introduzca un número de historial válido");
					else
						añadirFilasHistorial();
					
					txtNDeHistorial.setText("N\u00BA de historial");
				}
			});
		}
		return irHistorial;
	}
	private JButton getBtnBuscarPorFecha() {
		if (btnBuscarPorFecha == null) {
			btnBuscarPorFecha = new JButton("Fecha e historial");
			btnBuscarPorFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(txtNDeHistorial.getText().equals("")|| txtNDeHistorial.getText().equals("N\u00BA de historial"))
						JOptionPane.showMessageDialog(null, "Por favor, introduzca un número de historial válido o fecha valida");
					else
					añadirFilasHistorialFecha();
				}
			});
		}
		return btnBuscarPorFecha;
	}
	private JButton getBtnVerHistorial() {
		if (btnVerHistorial == null) {
			btnVerHistorial = new JButton("Ver historial");
			btnVerHistorial.setEnabled(false);
			btnVerHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarHistorial();
					
				}
			});
		}
		return btnVerHistorial;
	}
	
	
	
	protected void mostrarHistorial() {
		int fila=tablacita.getSelectedRow();
		if(fila!=-1) {

			String codcita=(String) tablacita.getValueAt(tablacita.getSelectedRow(), 9);
			String codPaciente=(String) tablacita.getValueAt(tablacita.getSelectedRow(), 10);
			String codMedico=(String) tablacita.getValueAt(tablacita.getSelectedRow(), 11);
			
		try {
			PrescripcionesToPDF pdf= new PrescripcionesToPDF();
			Paciente p;
			if(tablacita.getValueAt(tablacita.getSelectedRow(),6).equals("")) {
				System.out.println(fila);
				p=pbd.devolverPacientesEquipo((String)tablacita.getValueAt(tablacita.getSelectedRow(),10));
			}
			else {
				p=pbd.devolverPacientesMedico((String)tablacita.getValueAt(tablacita.getSelectedRow(),9));
			}
			
			//PrescripcionesDownload pd= new PrescripcionesDownload();
			try {
				pdf.createPDF(p);
			} catch (FileNotFoundException | JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//pd.receta(p);
			
		//	h.escribirhistorial(p);
		
			HistorialMedico hm = pbd.HistorialCita(codcita,codPaciente);
			MostrarHistorial mh = new MostrarHistorial(hm);
			mh.setLocationRelativeTo(null);
			mh.setResizable(true);
			mh.setModal(true); // hasta que no se cierre una ventana no se puede abrir otra
			mh.setVisible(true);
			guardarAccionHist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	private void guardarAccionHist() throws SQLException {
		List<Accion> devolverAccionesAdmin = pbd.devolverAccionesAdmin();
		int numeroAccion = 1;
		if(devolverAccionesAdmin.size()>0) {
			numeroAccion = devolverAccionesAdmin.size() + 1;
		}
		String naccion = "" +numeroAccion;
		
		String nombrePaciente=(String) tablacita.getValueAt(tablacita.getSelectedRow(), 0);
		String apellidoPaciente=(String) tablacita.getValueAt(tablacita.getSelectedRow(), 1);
		
		Date fecha = new Date();	
		Time hora = new Time(new Date().getTime());	
		
		
		String mensajeAccion = "El aministrador " + codAdmin + " ha visto el historial del paciente " + nombrePaciente + " " + apellidoPaciente;
		
		Accion a = new Accion(naccion, codAdmin,  fecha, hora, mensajeAccion);
		
		pbd.guardarAccion(a);
		
	}
	private JPanel getPanel_1_1() {
		if (panelFiltrosMedEn == null) {
			panelFiltrosMedEn = new JPanel();
			panelFiltrosMedEn.setLayout(new GridLayout(8, 1, 0, 0));
			panelFiltrosMedEn.add(getPanelVacio());
			panelFiltrosMedEn.add(getPanelMedEnfer());
			panelFiltrosMedEn.add(getPnNombre());
			panelFiltrosMedEn.add(getPnApellido());
			panelFiltrosMedEn.add(getPnCodEmpleado());
			panelFiltrosMedEn.add(getPnNombreApellido());
		}
		return panelFiltrosMedEn;
	}
	private JLabel getLblFiltrar() {
		if (lblFiltrar == null) {
			lblFiltrar = new JLabel("Filtrar por:");
		}
		return lblFiltrar;
	}
	private JRadioButton getRdbtnMdico() {
		if (rdbtnMdico == null) {
			rdbtnMdico = new JRadioButton("M\u00E9dico");
			rdbtnMdico.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					crearPanelEspecialidad();
				}

			
			});
			buttonGroup.add(rdbtnMdico);
		}
		return rdbtnMdico;
	}
	
	
	
	private void crearPanelEspecialidad() {
		if(rdbtnMdico.isSelected()) {
			panelFiltrosMedEn.add(getPanelNomEspe());
			panelFiltrosMedEn.add(getPanelEspecialidad());
			
			
		}
			
		
	}
	
	
	private JRadioButton getRdbtnEnfermero() {
		if (rdbtnEnfermero == null) {
			rdbtnEnfermero = new JRadioButton("Enfermero");
			buttonGroup.add(rdbtnEnfermero);
		}
		return rdbtnEnfermero;
	}
	private JPanel getPanelMedEnfer() {
		if (panelMedEnfer == null) {
			panelMedEnfer = new JPanel();
			panelMedEnfer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelMedEnfer.add(getLblFiltrar());
			panelMedEnfer.add(getRdbtnEnfermero());
			panelMedEnfer.add(getRdbtnMdico());
		}
		return panelMedEnfer;
	}
	private JPanel getPnNombre() {
		if (pnNombre == null) {
			pnNombre = new JPanel();
			pnNombre.add(getLblNombre());
			pnNombre.add(getTxtNombre());
			pnNombre.add(getBtnIr_1());
		}
		return pnNombre;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JButton getBtnIr_1() {
		if (btnIr_1 == null) {
			btnIr_1 = new JButton("Ir");
			btnIr_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				buscarPorNombre();
					
				}

				
			});
		}
		return btnIr_1;
	}
	
	
	
	private void buscarPorNombre() {
		if(txtNombre.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Por favor introduce un valor");
		else {	
			if(rdbtnMdico.isSelected())
				añadirFilasMedico();
		}
	}
		
			public void añadirFilasMedico()  {
				borrarModeloTabla();
				Object[] nuevaFila=new Object[12];
				List<Cita> citas = new ArrayList<Cita>();
			
				try {
					citas = pbd.devolverCitas();
				} catch (SQLException e1) {
					e1.printStackTrace();
				
			}
			
				for(Cita c:citas) {
					Paciente p = null;
					Empleado empleado=null;
					
					try {
						p = pbd.devolverPacientesMedico(c.getCodCita());
							empleado=pbd.devolverEmpleado(c.getCodMed());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					nuevaFila[0] = p.getNombre();
					nuevaFila[1]= p.getApellido();
					nuevaFila[2] = c.gethInicio();
					nuevaFila[3] =c.gethFin();
					nuevaFila[4] =c.getDate();
					nuevaFila[5] =c.getUbicacion();
					nuevaFila[6] = empleado.getNombre()+"  " +empleado.getApellido();
					nuevaFila[8] = c.isUrgente();
					nuevaFila[9]=c.getCodCita();
					nuevaFila[10]=c.getCodPaciente();
					nuevaFila[11]=c.getCodMed();
					modeloTabla.addRow(nuevaFila);
					}
				}
			
			
	private JPanel getPnApellido() {
		if (pnApellido == null) {
			pnApellido = new JPanel();
			pnApellido.add(getLblApellido());
			pnApellido.add(getTxtApellido());
			pnApellido.add(getBtnApellido());
		}
		return pnApellido;
	}
	private JPanel getPanelVacio() {
		if (panelVacio == null) {
			panelVacio = new JPanel();
		}
		return panelVacio;
	}
	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido:");
		}
		return lblApellido;
	}
	private JTextField getTxtApellido() {
		if (txtApellido == null) {
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
		}
		return txtApellido;
	}
	private JButton getBtnApellido() {
		if (BtnApellido == null) {
			BtnApellido = new JButton("Ir");
		}
		return BtnApellido;
	}
	private JPanel getPnCodEmpleado() {
		if (pnCodEmpleado == null) {
			pnCodEmpleado = new JPanel();
			pnCodEmpleado.add(getLblCodempleado());
			pnCodEmpleado.add(getTxtEmpleado());
			pnCodEmpleado.add(getBtnCodEmpleado());
		}
		return pnCodEmpleado;
	}
	private JLabel getLblCodempleado() {
		if (lblCodempleado == null) {
			lblCodempleado = new JLabel("CodEmpleado:");
		}
		return lblCodempleado;
	}
	private JTextField getTxtEmpleado() {
		if (txtEmpleado == null) {
			txtEmpleado = new JTextField();
			txtEmpleado.setColumns(10);
		}
		return txtEmpleado;
	}
	private JButton getBtnCodEmpleado() {
		if (btnCodEmpleado == null) {
			btnCodEmpleado = new JButton("Ir");
		}
		return btnCodEmpleado;
	}
	private JPanel getPnNombreApellido() {
		if (pnNombreApellido == null) {
			pnNombreApellido = new JPanel();
			pnNombreApellido.add(getBtnNombreYApellido());
		}
		return pnNombreApellido;
	}
	private JButton getBtnNombreYApellido() {
		if (btnNombreYApellido == null) {
			btnNombreYApellido = new JButton("Nombre y apellido");
		}
		return btnNombreYApellido;
	}
	private JPanel getPanelEspecialidad() {
		if (panelEspecialidad == null) {
			panelEspecialidad = new JPanel();
			panelEspecialidad.setLayout(null);
			panelEspecialidad.add(getComboBox());
		}
		return panelEspecialidad;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(12, 13, 216, 22);
		}
		return comboBox;
	}
	private JPanel getPanelNomEspe() {
		if (panelNomEspe == null) {
			panelNomEspe = new JPanel();
			panelNomEspe.add(getLblEspecialidad());
			panelNomEspe.add(getTxtEspecialidad());
		}
		return panelNomEspe;
	}
	private JLabel getLblEspecialidad() {
		if (lblEspecialidad == null) {
			lblEspecialidad = new JLabel("Especialidad:");
		}
		return lblEspecialidad;
	}
	private JTextField getTxtEspecialidad() {
		if (txtEspecialidad == null) {
			txtEspecialidad = new JTextField();
			txtEspecialidad.setColumns(10);
		}
		return txtEspecialidad;
	}
}
