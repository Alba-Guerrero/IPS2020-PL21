package ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logica.Cita;
import logica.Paciente;
import logica.empleados.Empleado;
import logica.servicios.ParserBaseDeDatos;
import ui.medico.ModeloNoEditable;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.swing.JTextField;

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
	


	/**
	 * Create the frame.
	 * @param codmedico 
	 */
	public VentanaVerCita() {
		setTitle("M\u00E9dico: Ver citas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 870, 515);
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
	private JTable getTableCita() {
			if (tablacita == null) {
				String[] nombreColumnas= {"Nombre paciente "," Apellido paciente  ","Hora inicio"," Hora fin","Fecha ","Ubicación ","Nombre médico","Urgencia"};
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
				añadirFilas(false);
				
				tablacita.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						int fila=tablacita.getSelectedRow();
						if(fila!=-1) {
							btnEliminar.setEnabled(true);
							btnModificar.setEnabled(true);
								try {
									Paciente p=pbd.devolverPacientesMedico(codcitas.get(tablacita.getSelectedRow()).getCodCita());
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
	
	
	private void añadirFilas(boolean dia)  {
		borrarModeloTabla();
		Object[] nuevaFila=new Object[8];
		List<Cita> citas = new ArrayList<Cita>();
	if(dia) {
		
		Date date = getDateChooser().getDate();
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		try {
			citas = pbd.devolvercitasPorFecha(sDate);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	else {
		try {
			citas = pbd.devolverCitas();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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
			modeloTabla.addRow(nuevaFila);
			codcitas.add(c);
		}
		
		}
		
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
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
							pbd.BorrarCita(codcitas.get(tablacita.getSelectedRow()).getCodCita());
							añadirFilas(false);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						toFront();
					}
						
					}
					
					
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
						
							try {
								Paciente p=pbd.devolverPacientesMedico(codcitas.get(tablacita.getSelectedRow()).getCodCita());
								VentanaModificarCita(p,codcitas.get(tablacita.getSelectedRow()));
								
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
		
		ModificarCita mc = new ModificarCita(p,c);
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
		Object[] nuevaFila=new Object[8];
		List<Cita> citas = new ArrayList<Cita>();
		try {
			citas = pbd.devolvercitasHistorial(txtNDeHistorial.getText());
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
			modeloTabla.addRow(nuevaFila);
			codcitas.add(c);
		}
		
		}
	
	
	private void añadirFilasHistorialFecha()  {
		borrarModeloTabla();
		Object[] nuevaFila=new Object[8];
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
			modeloTabla.addRow(nuevaFila);
			codcitas.add(c);
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
}
