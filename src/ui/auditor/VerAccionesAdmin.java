package ui.auditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import logica.Accion;
import logica.Cita;
import logica.Paciente;
import logica.empleados.Empleado;
import logica.servicios.ParserBaseDeDatos;
import ui.medico.ModeloNoEditable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerAccionesAdmin extends JFrame {

	private JPanel contentPane;
	private JPanel panelBotones;
	private JPanel panelFiltros;
	private JScrollPane scrollPane;
	private JTable tablaCita;
	private ModeloNoEditable modeloTabla;
	private JLabel lblFechaIn;
	private JDateChooser dateChooser;
	private ParserBaseDeDatos pbd=new ParserBaseDeDatos();
	private List<Cita> codcitas= new ArrayList<Cita>();
	private List<Accion> accionesAdmin = new ArrayList<Accion>();
	private JButton btnNewButton;
	private JDateChooser dateChooser_1;
	private JLabel lblFechaIn_1;
	private JButton btnFiltroFechas;
	private JButton btnTodas;
	private JLabel lblNombre;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;


	/**
	 * Create the frame.
	 */
	public VerAccionesAdmin() {
		setTitle("Registro de acciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
		contentPane.add(getPanelFiltros(), BorderLayout.NORTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.add(getBtnNewButton());
		}
		return panelBotones;
	}
	private JPanel getPanelFiltros() {
		if (panelFiltros == null) {
			panelFiltros = new JPanel();
			panelFiltros.add(getLblFechaIn());
			panelFiltros.add(getDateChooser());
			panelFiltros.add(getLblFechaIn_1());
			panelFiltros.add(getDateChooser_1());
			panelFiltros.add(getBtnFiltroFechas());
			panelFiltros.add(getBtnTodas());
			panelFiltros.add(getLblNombre());
			panelFiltros.add(getBtnNewButton_1());
			panelFiltros.add(getBtnNewButton_2());
		}
		return panelFiltros;
	}
	
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(new Date());
			
		}
		return dateChooser;
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaCita());
		}
		return scrollPane;
	}
	private JTable getTablaCita() {
		if (tablaCita == null) {
			String[] nombreColumnas= {"Numero acci�n", "Nombre usuario","Fecha", "Hora","Acci�n "};
			modeloTabla= new ModeloNoEditable(nombreColumnas,0);
			tablaCita = new JTable(modeloTabla);
			tablaCita.getTableHeader().setReorderingAllowed(false);//Evita que se pueda mpver las columnas
			tablaCita.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaCita.getTableHeader().setBackground(Color.LIGHT_GRAY);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaCita.getModel());
			tablaCita.setRowSorter(sorter);
			
			List<RowSorter.SortKey> sortKeys = new ArrayList<>();
			sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
			sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
			sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
			sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
			sorter.setSortKeys(sortKeys);
			
			a�adirFilas(false);
			tablaCita.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					int fila=tablaCita.getSelectedRow();
					if(fila!=-1) {
						String mensaje = (String) tablaCita.getValueAt(tablaCita.getSelectedRow(), 3);
						JOptionPane.showMessageDialog(null,
								 mensaje);
					}
				}
			});
			
		}
		return tablaCita;
	}
	
	public void a�adirFilas(boolean dia)  {
		borrarModeloTabla();
		Object[] nuevaFila=new Object[11];
		List<Accion> citas = new ArrayList<Accion>();
	if(dia) {
		
		Date date = getDateChooser().getDate();
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		try {
			citas = pbd.devolverAccionesAdmin();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	else {
		try {
			accionesAdmin = pbd.devolverAccionesAdmin();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
		for(Accion a:accionesAdmin) {
			nuevaFila[0]= a.getNaccion();
			nuevaFila[1]= a.getCodaccion();
			nuevaFila[2] = a.getDate();
			nuevaFila[3] =a.getHora();
			nuevaFila[4] =a.getMensajeAccion();
			modeloTabla.addRow(nuevaFila);
		
		}
	}
	
	private void borrarModeloTabla() {
		int filas=modeloTabla.getRowCount();
			for (int i = 0; i <filas; i++) {
				modeloTabla.removeRow(0);
				
			}
	}
	private JLabel getLblFechaIn() {
		if (lblFechaIn == null) {
			lblFechaIn = new JLabel("Fecha inicio");
		}
		return lblFechaIn;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Cerrar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnNewButton;
	}
	private JDateChooser getDateChooser_1() {
		if (dateChooser_1 == null) {
			dateChooser_1 = new JDateChooser((Date) null);
		}
		return dateChooser_1;
	}
	private JLabel getLblFechaIn_1() {
		if (lblFechaIn_1 == null) {
			lblFechaIn_1 = new JLabel("Fecha inicio");
		}
		return lblFechaIn_1;
	}
	private JButton getBtnFiltroFechas() {
		if (btnFiltroFechas == null) {
			btnFiltroFechas = new JButton("Ir");
		}
		return btnFiltroFechas;
	}
	private JButton getBtnTodas() {
		if (btnTodas == null) {
			btnTodas = new JButton("Todas las acciones");
		}
		return btnTodas;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre de usuario");
		}
		return lblNombre;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Ir");
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("Fecha y nombre");
		}
		return btnNewButton_2;
	}
}
