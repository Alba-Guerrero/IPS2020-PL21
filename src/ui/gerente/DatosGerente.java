package ui.gerente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;

import logica.AsignaDiagnostico;
import logica.Cita;
import logica.Diagnostico;
import logica.servicios.ParserBaseDeDatos;
import ui.medico.ModeloNoEditable;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class DatosGerente extends JDialog {

	private JPanel contentPane;
	private JPanel panelDatos;
	private JPanel panelTabla;
	private JPanel panelGrafico;
	private JScrollPane scrollPane;
	private JTable table;
	private ModeloNoEditable modeloTabla;
	private ParserBaseDeDatos pbd = new ParserBaseDeDatos();
	private DefaultCategoryDataset dataset= new DefaultCategoryDataset();
	private JScrollPane scrollPaneGrafico;
	private JPanel panelFiltros;
	private JPanel panelBotones;
	private JButton btnCerrar;
	private JButton btnFiltrarPorNombre;
	private JLabel lblFechaIn;
	private JDateChooser dateChooser;
	private JLabel lblFechaIn_1;
	private JDateChooser dateChooser_1;
	private JButton btnFiltroFechas;
	private JButton btnTodas;

	/**
	 * Create the frame.
	 */
	public DatosGerente() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelFiltros(), BorderLayout.NORTH);
		contentPane.add(getPanelDatos());
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
	}

	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setLayout(new GridLayout(1, 0, 0, 0));
			panelDatos.add(getPanelTabla());
			panelDatos.add(getScrollPaneGrafico());
		}
		return panelDatos;
	}

	private JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setLayout(new GridLayout(0, 1, 0, 0));
			panelTabla.add(getScrollPane());
		}
		return panelTabla;
	}
	private JPanel getPanelGrafico() {
		if (panelGrafico == null) {
			panelGrafico = new JPanel();
		}
		añadirGrafico();
		return panelGrafico;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
			
		}
		return scrollPane;
	}
	
	private JScrollPane getScrollPaneGrafico() {
		if (scrollPaneGrafico == null) {
			scrollPaneGrafico = new JScrollPane();
			scrollPaneGrafico.setViewportView(getPanelGrafico());
		}
		return scrollPaneGrafico;
	}


	private JTable getTable() {
		if (table == null) {
			String[] nombreColumnas= {" Codigo diagnóstico ", " Nombre diagnóstico "," Nº veces "};
			modeloTabla= new ModeloNoEditable(nombreColumnas,0);
			table = new JTable(modeloTabla);
			table.getTableHeader().setReorderingAllowed(false);//Evita que se pueda mpver las columnas
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getTableHeader().setBackground(Color.LIGHT_GRAY);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
			table.setRowSorter(sorter);
			
			List<RowSorter.SortKey> sortKeys = new ArrayList<>();
			sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
			sorter.setSortKeys(sortKeys);
//			for (int i = 2; i < 2; i++) {
//				table.getColumnModel().getColumn(i).setMinWidth(0);
//				table.getColumnModel().getColumn(i).setMaxWidth(0);
//				table.getColumnModel().getColumn(i).setWidth(0);
//			}
			
			añadirFilas(false);
		}
		
		return table;
	}



	private void añadirFilas(boolean dia) {
		borrarModeloTabla();
		Object[] nuevaFila=new Object[3];
		List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		try {
			diagnosticos = pbd.listarDiagnosticos();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if(dia) {
			Date dateIn = getDateChooser().getDate();
			java.sql.Date sDateIn = new java.sql.Date(dateIn.getTime());
			
			Date dateFin = getDateChooser_1().getDate();
			java.sql.Date sDateFin = new java.sql.Date(dateFin.getTime());
			for(Diagnostico diagnostico: diagnosticos) {
				int cant = 0;
				nuevaFila[0] = diagnostico.getNumeroDiagnostico();
				nuevaFila[1]= diagnostico.getNombre();
				try {
					cant = pbd.listarDiagnosticosAsignadosPorFecha(diagnostico.getNombre(), sDateIn, sDateFin);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(diagnostico.getNombre() + cant);
				nuevaFila[2]= cant + "";
				int datoss1 = cant;
				dataset.setValue(datoss1,"", diagnostico.getNombre());
				modeloTabla.addRow(nuevaFila);
			}
			
		}
		else {
			for(Diagnostico diagnostico: diagnosticos) {
				int cant = 0;
				nuevaFila[0] = diagnostico.getNumeroDiagnostico();
				nuevaFila[1]= diagnostico.getNombre();
				try {
					cant = pbd.listarDiagnosticosAsignados(diagnostico.getNombre());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(diagnostico.getNombre() + cant);
				nuevaFila[2]= cant + "";
				int datoss1 = cant;
				dataset.setValue(datoss1,"", diagnostico.getNombre());
				modeloTabla.addRow(nuevaFila);
			}
		}
		
	}
	

	private void añadirGrafico() {
		JFreeChart chart = ChartFactory.createBarChart("Diagnósticos", "", "", dataset, PlotOrientation.VERTICAL, false, false, false);
		CategoryPlot catPlot = chart.getCategoryPlot();
		catPlot.setRangeGridlinePaint(Color.BLACK);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		panelGrafico.add(chartPanel, BorderLayout.CENTER);
		
	}

	private void borrarModeloTabla() {
		int filas=modeloTabla.getRowCount();
			for (int i = 0; i <filas; i++) {
				modeloTabla.removeRow(0);
				
			}
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
		}
		return panelFiltros;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.add(getBtnCerrar());
		}
		return panelBotones;
	}
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCerrar;
	}



	private JLabel getLblFechaIn() {
		if (lblFechaIn == null) {
			lblFechaIn = new JLabel("Fecha inicio");
		}
		return lblFechaIn;
	}
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(new Date());
		}
		return dateChooser;
	}
	private JLabel getLblFechaIn_1() {
		if (lblFechaIn_1 == null) {
			lblFechaIn_1 = new JLabel("Fecha inicio");
		}
		return lblFechaIn_1;
	}
	private JDateChooser getDateChooser_1() {
		if (dateChooser_1 == null) {
			dateChooser_1 = new JDateChooser(new Date());
		}
		return dateChooser_1;
	}
	private JButton getBtnFiltroFechas() {
		if (btnFiltroFechas == null) {
			btnFiltroFechas = new JButton("Ir");
			btnFiltroFechas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirFilas(true);
				}
			});
		}
		return btnFiltroFechas;
	}
	private JButton getBtnTodas() {
		if (btnTodas == null) {
			btnTodas = new JButton("Todas las acciones");
			btnTodas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirFilas(false);
				}
			});
		}
		return btnTodas;
	}
}
