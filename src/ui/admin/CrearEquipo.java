package ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Accion;
import logica.Equipo;
import logica.empleados.Medico;
import logica.servicios.ParserBaseDeDatos;

import java.awt.Dimension;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrearEquipo extends JDialog {

	private JPanel contentPane;
	private JPanel pnMedico;
	private JPanel pnDatosMedicoSeleccion;
	private JPanel pnDatosMedico;
	private JPanel pnMedicosSeleccionados;
	private JScrollPane scrollPane;
	private JPanel pnMedicosLista;
	private JScrollPane scrollPaneListaMedicos;
	private JTextField txtFieldApellidoMedicoFiltro;
	private JTextField txtFieldNombreMedicoFiltro;
	private JLabel lblNombreFiltroMedico;
	private JTextField textField;
	private JButton btnFiltrarNombreMedico;
	private JLabel lblApellidoMedicoFiltro;
	private JTextField textField_1;
	private JButton btnFiltrarApellidoMedico;
	private ParserBaseDeDatos pbd = new ParserBaseDeDatos();
	private DefaultListModel<Medico> modeloListaM;
	private JList<Medico> listMedicos;
	private ArrayList<Medico> medicos;
	private JList<Medico> list;
	private DefaultListModel<Medico> modeloMedSelec;
	private JButton btnEliminarMedico;
	private JPanel panel;
	private JButton btnCrearEquipo;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CrearEquipo() throws SQLException {
		setTitle("Crear Equipo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 650);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnMedico(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
	}

	private JPanel getPnMedico() throws SQLException {
		if (pnMedico == null) {
			pnMedico = new JPanel();
			pnMedico.setSize(new Dimension(219, 200));
			pnMedico.setBorder(new TitledBorder(null, "M\u00E9dico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnMedico.setLayout(new GridLayout(0, 2, 0, 0));
			pnMedico.add(getPnDatosMedicoSeleccion());
			pnMedico.add(getPnMedicosLista());
		}
		return pnMedico;
	}
	private JPanel getPnDatosMedicoSeleccion() {
		if (pnDatosMedicoSeleccion == null) {
			pnDatosMedicoSeleccion = new JPanel();
			pnDatosMedicoSeleccion.setLayout(new GridLayout(2, 0, 0, 0));
			pnDatosMedicoSeleccion.add(getPnDatosMedico());
			pnDatosMedicoSeleccion.add(getPnMedicosSeleccionados());
		}
		return pnDatosMedicoSeleccion;
	}
	private JPanel getPnDatosMedico() {
		if (pnDatosMedico == null) {
			pnDatosMedico = new JPanel();
			pnDatosMedico.setLayout(new MigLayout("", "[152px][152px][152px]", "[17px][17px][17px][17px]"));
			pnDatosMedico.add(getLblNombreFiltroMedico(), "flowx,cell 0 1,grow");
			pnDatosMedico.add(getTextField_2(), "cell 1 1,grow");
			pnDatosMedico.add(getBtnFiltrarNombreMedico(), "cell 2 1,grow");
			pnDatosMedico.add(getLblApellidoMedicoFiltro(), "cell 0 3,grow");
			pnDatosMedico.add(getTextField_1_1(), "cell 1 3,grow");
			pnDatosMedico.add(getBtnFiltrarApellidoMedico(), "cell 2 3,grow");
		}
		return pnDatosMedico;
	}

	
	private JPanel getPnMedicosSeleccionados() {
		if (pnMedicosSeleccionados == null) {
			pnMedicosSeleccionados = new JPanel();
			pnMedicosSeleccionados.setLayout(null);
			pnMedicosSeleccionados.setBorder(new TitledBorder(null, "M\u00E9dicos seleccionados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnMedicosSeleccionados.add(getScrollPane());
			pnMedicosSeleccionados.add(getBtnEliminarMedico());
		}
		return pnMedicosSeleccionados;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(new Rectangle(12, 23, 476, 72));
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JPanel getPnMedicosLista() throws SQLException {
		if (pnMedicosLista == null) {
			pnMedicosLista = new JPanel();
			pnMedicosLista.setLayout(null);
			pnMedicosLista.setBorder(new TitledBorder(null, "Seleccione los m\u00E9dicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnMedicosLista.add(getScrollPaneListaMedicos());
		}
		return pnMedicosLista;
	}
	private JScrollPane getScrollPaneListaMedicos() throws SQLException {
		if (scrollPaneListaMedicos == null) {
			scrollPaneListaMedicos = new JScrollPane();
			scrollPaneListaMedicos.setOpaque(false);
			scrollPaneListaMedicos.setBounds(21, 23, 453, 178);
			scrollPaneListaMedicos.setViewportView(getList_1());
		}
		return scrollPaneListaMedicos;
	}
	
	private JList<Medico> getList_1() throws SQLException {
		if (listMedicos == null) {
			listMedicos = new JList<Medico>();
			listMedicos.setBackground(Color.WHITE);
			modeloListaM(pbd.buscarMedico(""));
			listMedicos.setModel(modeloListaM);
			medicos = new ArrayList<Medico>();
			listMedicos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					
					@SuppressWarnings("deprecation")
					Object[] selectedValues = listMedicos.getSelectedValues();
					if (selectedValues.length >= 0) {
						for (int i = 0; i < selectedValues.length; i++) {
							medicos.add((Medico) selectedValues[i]);
						}
						modeloListaSeleccionados(medicos);
					camposCubiertos();
					System.out.println(medicos.size());
					}}
			});
			
		}

		return listMedicos;
	}
	
	private JList<Medico> getList() {
		if (list == null) {
			list = new JList<Medico>();
			modeloMedSelec= new DefaultListModel<Medico>();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()==2) {
						
							Medico med=(Medico) list.getSelectedValue();
							int res=JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea borrar este médico?","Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
							if(res==JOptionPane.YES_OPTION)	
								modeloMedSelec.removeElement(med);
				}
					}
			});
		
		}
		return list;
	}
	

	
	private void  modeloListaSeleccionados(List<Medico>medicos){
		if(medicos!=null) {
		
		 
		for (int i = 0; i < medicos.size(); i++) {
			if(!modeloMedSelec.contains(medicos.get(i)))
			modeloMedSelec.addElement(medicos.get(i));
			
		}
		list.setModel(modeloMedSelec);
		
		}
	
	}
	
	private JLabel getLblNombreFiltroMedico() {
		if (lblNombreFiltroMedico == null) {
			lblNombreFiltroMedico = new JLabel("Nombre:");
		}
		return lblNombreFiltroMedico;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	
	private DefaultListModel<Medico> modeloListaM(List<Medico> medico) throws SQLException {
		modeloListaM = new DefaultListModel<Medico>();
		if(medico!=null) {
		List<Medico> medicos = medico;
		for (int i = 0; i < medicos.size(); i++) {
			modeloListaM.addElement(medicos.get(i));

		}
		listMedicos.setModel(modeloListaM);
		}
		if(modeloListaM.getSize()==0)
			JOptionPane.showMessageDialog(null, "No se ha encontrado ningún médico con esas características");
		return modeloListaM;
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
	private JLabel getLblApellidoMedicoFiltro() {
		if (lblApellidoMedicoFiltro == null) {
			lblApellidoMedicoFiltro = new JLabel("Apellido:");
		}
		return lblApellidoMedicoFiltro;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		return textField_1;
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
	private JButton getBtnEliminarMedico() {
		if (btnEliminarMedico == null) {
			btnEliminarMedico = new JButton("Eliminar m\u00E9dico");
			btnEliminarMedico.setBounds(12, 116, 125, 23);
		}
		return btnEliminarMedico;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getButton_1());
		}
		return panel;
	}
	private JButton getButton_1() {
		if (btnCrearEquipo == null) {
			btnCrearEquipo = new JButton("Crear equipo");
			btnCrearEquipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						crearEquipo();
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnCrearEquipo;
	}

	protected void crearEquipo() throws SQLException {
		List<Equipo> devolverEquipos = pbd.calcularEquipos();
		int numeroEquipos = devolverEquipos.size() + 1;
		 String numEquipo = "Equipo " +numeroEquipos ;
		 pbd.nuevoEquipo(numEquipo);
		 for(int i =0;i< medicos.size() ; i++) {
			 Random r = new Random();
			 String codequipo = "" + r.nextInt(1000);
			 String codEmpleado = medicos.get(i).getCodeEmpleado();
			 pbd.asignarEquipo(codequipo, numEquipo, codEmpleado);
		 }
	}
	
	private boolean camposCubiertos() {
		if (JlistMedicoFill()) {
			btnCrearEquipo.setEnabled(true);
			return true;

		} else
			btnCrearEquipo.setEnabled(false);

		return false;

	}
	
	private boolean JlistMedicoFill() {
		if (medicos == null)
			return false;
		return medicos.size() > 0;
	}
}
