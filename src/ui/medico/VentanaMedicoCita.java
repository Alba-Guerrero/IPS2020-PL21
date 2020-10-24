package ui.medico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Cita;
import logica.Paciente;
import logica.servicios.ParserBaseDeDatos;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaMedicoCita extends JDialog {

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
	private String codmedico;
	private List<String> codcitas= new ArrayList<String>();
	


	/**
	 * Create the frame.
	 * @param codmedico 
	 */
	public VentanaMedicoCita(String codmedico) {
		setTitle("M\u00E9dico: Ver citas");
		this.codmedico=codmedico;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 813, 521);
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
				String[] nombreColumnas= {"Cod paciente ","Nombre "," Apellido  ","Hora inicio"," Hora fin","Fecha ","Urgencia"};
				modeloTabla= new ModeloNoEditable(nombreColumnas,0);
				tablacita = new JTable(modeloTabla);
				tablacita.getTableHeader().setReorderingAllowed(false);//Evita que se pueda mpver las columnas
				tablacita.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tablacita.getTableHeader().setBackground(Color.LIGHT_GRAY);
				añadirFilas(false);
				
				tablacita.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						int fila=tablacita.getSelectedRow();
						if(fila!=-1) {
							
								try {
									Paciente p=pbd.devolverPacientesMedico(codcitas.get(tablacita.getSelectedRow()));
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
		Object[] nuevaFila=new Object[7];
		List<Cita> citas = new ArrayList<Cita>();
	if(dia) {

		Date date = getDateChooser().getDate();
		java.sql.Date sDate = new java.sql.Date(date.getTime());
		try {
			citas = pbd.devolvercitasMedicoPorFecha(sDate,codmedico);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	else {
		try {
			citas = pbd.devolvercitasMedico(codmedico);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
		for(Cita c:citas) {
			Paciente p = null;
			try {
				p = pbd.devolverPacientesMedico(c.getCodCita());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			nuevaFila[0] = p.getCodePaciente();
			nuevaFila[1] = p.getNombre();
			nuevaFila[2]= p.getApellido();
			nuevaFila[3] = c.gethInicio();
			nuevaFila[4] =c.gethFin();
			nuevaFila[5] =c.getDate();
			nuevaFila[6] = c.isUrgente();
			modeloTabla.addRow(nuevaFila);
			codcitas.add(c.getCodCita());
		}
		
		}
		
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
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
}
