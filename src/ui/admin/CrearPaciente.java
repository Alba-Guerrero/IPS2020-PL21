package ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import logica.Acompa�ante;
import logica.HistorialMedico;
import logica.Paciente;
import logica.empleados.Medico;
import logica.servicios.ParserBaseDeDatos;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.JTextPane;

public class CrearPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelPaciente;
	private JPanel panelAceptarLista;
	private JPanel panelAcompa�ante;
	private JLabel lbPNombre;
	private JTextField txtPNombre;
	private JLabel lblPApellidos;
	private JTextField txtPApellidos;
	private JLabel lbPEmail;
	private JTextField txtPEmail;
	private JTextField txtPnumero;
	private JLabel lblPTelfono;
	private JCheckBox chcbkAcompa�ante;
	private JLabel lnAnombre;
	private JTextField txtAnombre;
	private JLabel lblAApellidos;
	private JTextField txtAtelefono;
	private JLabel lblATelfono;
	private JTextField txtAApellido;
	private JButton btnAadirAcompaante;
	private JScrollPane scrollPane;
	private JPanel panelLista;
	private JPanel panelAceptar;
	private JList<Acompa�ante> list;
	private JButton btnCancelar;
	private JButton btnPaciente;
	private DefaultListModel<Acompa�ante> modeloA;
	private ParserBaseDeDatos pbd= new ParserBaseDeDatos();
	private JLabel lbAEmail;
	private JTextField txtAEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearPaciente dialog = new CrearPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearPaciente() {
		setTitle("Administrador:Crear paciente");
		setBounds(100, 100, 736, 524);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(3, 1, 0, 0));
		contentPanel.add(getPanelPaciente());
		contentPanel.add(getPanelAcompa�ante());
		contentPanel.add(getPanelAceptarLista());
	}

	private JPanel getPanelPaciente() {
		if (panelPaciente == null) {
			panelPaciente = new JPanel();
			panelPaciente.setBorder(new TitledBorder(null, "Datos del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPaciente.setLayout(null);
			panelPaciente.add(getLbPNombre());
			panelPaciente.add(getTxtPNombre());
			panelPaciente.add(getLblPApellidos());
			panelPaciente.add(getTxtPApellidos());
			panelPaciente.add(getLbPEmail());
			panelPaciente.add(getTxtPEmail());
			panelPaciente.add(getTxtPnumero());
			panelPaciente.add(getLblPTelfono());
			panelPaciente.add(getChcbkAcompa�ante());
		}
		return panelPaciente;
	}
	private JPanel getPanelAceptarLista() {
		if (panelAceptarLista == null) {
			panelAceptarLista = new JPanel();
			panelAceptarLista.setLayout(new GridLayout(0, 2, 0, 0));
			panelAceptarLista.add(getPanel_1());
			panelAceptarLista.add(getPanel_1_1());
		}
		return panelAceptarLista;
	}
	private JPanel getPanelAcompa�ante() {
		if (panelAcompa�ante == null) {
			panelAcompa�ante = new JPanel();
			panelAcompa�ante.setEnabled(false);
			panelAcompa�ante.setBorder(new TitledBorder(null, "A\u00F1adir acompa\u00F1ante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAcompa�ante.setLayout(null);
			panelAcompa�ante.add(getLnAnombre());
			panelAcompa�ante.add(getTxtAnombre());
			panelAcompa�ante.add(getLblAApellidos());
			panelAcompa�ante.add(getTxtAtelefono());
			panelAcompa�ante.add(getLblATelfono());
			panelAcompa�ante.add(getTxtAApellido());
			panelAcompa�ante.add(getBtnAadirAcompaante());
			panelAcompa�ante.add(getLbAEmail());
			panelAcompa�ante.add(getTxtAEmail());
		}
		return panelAcompa�ante;
	}
	private JLabel getLbPNombre() {
		if (lbPNombre == null) {
			lbPNombre = new JLabel("Nombre:");
			lbPNombre.setBounds(12, 30, 56, 16);
		}
		return lbPNombre;
	}
	private JTextField getTxtPNombre() {
		if (txtPNombre == null) {
			txtPNombre = new JTextField();
			txtPNombre.setBounds(69, 27, 140, 22);
			txtPNombre.setColumns(10);
		}
		return txtPNombre;
	}
	private JLabel getLblPApellidos() {
		if (lblPApellidos == null) {
			lblPApellidos = new JLabel("Apellidos:");
			lblPApellidos.setBounds(241, 30, 56, 16);
		}
		return lblPApellidos;
	}
	private JTextField getTxtPApellidos() {
		if (txtPApellidos == null) {
			txtPApellidos = new JTextField();
			txtPApellidos.setBounds(304, 27, 279, 22);
			txtPApellidos.setColumns(10);
		}
		return txtPApellidos;
	}
	private JLabel getLbPEmail() {
		if (lbPEmail == null) {
			lbPEmail = new JLabel("Email: ");
			lbPEmail.setBounds(241, 82, 56, 16);
		}
		return lbPEmail;
	}
	private JTextField getTxtPEmail() {
		if (txtPEmail == null) {
			txtPEmail = new JTextField();
			txtPEmail.setColumns(10);
			txtPEmail.setBounds(295, 79, 288, 22);
		}
		return txtPEmail;
	}
	private JTextField getTxtPnumero() {
		if (txtPnumero == null) {
			txtPnumero = new JTextField();
			txtPnumero.setColumns(10);
			txtPnumero.setBounds(69, 79, 140, 22);
		}
		return txtPnumero;
	}
	private JLabel getLblPTelfono() {
		if (lblPTelfono == null) {
			lblPTelfono = new JLabel("Tel\u00E9fono: ");
			lblPTelfono.setBounds(12, 82, 70, 16);
		}
		return lblPTelfono;
	}
	
	private JCheckBox getChcbkAcompa�ante() {
		if (chcbkAcompa�ante == null) {
			chcbkAcompa�ante = new JCheckBox("A\u00F1adir acompa\u00F1ante");
			chcbkAcompa�ante.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					boolean seleccion=chcbkAcompa�ante.isSelected();
					panelAcompa�ante.setEnabled(seleccion);
					for (Component component : panelAcompa�ante.getComponents()) {
							   component.setEnabled(seleccion); 
							}
					panelLista.setEnabled(seleccion);
					for (Component component : panelLista.getComponents()) {
						   component.setEnabled(seleccion); 
						}
					
				
				}
			});
			chcbkAcompa�ante.setBounds(20, 121, 176, 25);
		}
		return chcbkAcompa�ante;
	}
	private JLabel getLnAnombre() {
		if (lnAnombre == null) {
			lnAnombre = new JLabel("Nombre:");
			lnAnombre.setEnabled(false);
			lnAnombre.setBounds(12, 36, 50, 16);
		}
		return lnAnombre;
	}
	private JTextField getTxtAnombre() {
		if (txtAnombre == null) {
			txtAnombre = new JTextField();
			txtAnombre.setEnabled(false);
			txtAnombre.setBounds(74, 33, 140, 22);
			txtAnombre.setColumns(10);
		}
		return txtAnombre;
	}
	private JLabel getLblAApellidos() {
		if (lblAApellidos == null) {
			lblAApellidos = new JLabel("Apellidos:");
			lblAApellidos.setEnabled(false);
			lblAApellidos.setBounds(243, 36, 56, 16);
		}
		return lblAApellidos;
	}
	private JTextField getTxtAtelefono() {
		if (txtAtelefono == null) {
			txtAtelefono = new JTextField();
			txtAtelefono.setEnabled(false);
			txtAtelefono.setBounds(84, 77, 140, 22);
			txtAtelefono.setColumns(10);
		}
		return txtAtelefono;
	}
	private JLabel getLblATelfono() {
		if (lblATelfono == null) {
			lblATelfono = new JLabel("Tel\u00E9fono: ");
			lblATelfono.setEnabled(false);
			lblATelfono.setBounds(12, 80, 59, 16);
		}
		return lblATelfono;
	}
	private JTextField getTxtAApellido() {
		if (txtAApellido == null) {
			txtAApellido = new JTextField();
			txtAApellido.setEnabled(false);
			txtAApellido.setColumns(10);
			txtAApellido.setBounds(311, 33, 279, 22);
		}
		return txtAApellido;
	}
	private JButton getBtnAadirAcompaante() {
		if (btnAadirAcompaante == null) {
			btnAadirAcompaante = new JButton("A\u00F1adir acompa\u00F1ante");
			btnAadirAcompaante.setEnabled(false);
			btnAadirAcompaante.setBounds(474, 117, 166, 25);
			btnAadirAcompaante.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkCamposAcomap�ante()) {
						if(checkSoloLetras(txtAnombre.getText())||checkSoloLetras(txtAApellido.getText()))
									a�adirAcompa�ante();
						else
							JOptionPane.showConfirmDialog(null, "El nombre o apellido no puede contener caracteres distintos de letras");
					}
					else {
						JOptionPane.showConfirmDialog(null, "Por favor,rellene todos los campos obligatorios");
						lblPApellidos.setForeground(Color.RED);
						lbAEmail.setForeground(Color.RED);
						lbPNombre.setForeground(Color.RED);
					}
				}
				
			});
			
			
		}
		return btnAadirAcompaante;
	}
	
	
	private void a�adirAcompa�ante() {
		int movil=0;
	if(checkSoloNumeros(txtAtelefono.getText()))
		try {
		 movil=Integer.parseInt(txtAtelefono.getText());
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		
		Acompa�ante a= new Acompa�ante(txtAnombre.getText(),txtAApellido.getText(),movil,txtAEmail.getText());
		modeloA.addElement(a);
		list.setModel(modeloA);
		
		vaciarCampos();
			
		

		
	}
	
	private void vaciarCampos() {
		txtAApellido.setText("");
		txtAEmail.setText("");
		txtAnombre.setText("");
		txtAtelefono.setText("");
		
	}

	private boolean checkCamposAcomap�ante() {
		return !(getTxtAnombre().getText().isEmpty()||txtAApellido.getText().isEmpty()||txtAEmail.getText().isEmpty());
		
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(new Rectangle(12, 23, 304, 119));
			scrollPane.setEnabled(false);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JPanel getPanel_1() {
		if (panelLista == null) {
			panelLista = new JPanel();
			panelLista.setEnabled(false);
			panelLista.setBorder(new TitledBorder(null, "Lista de acompa\u00F1antes ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelLista.setLayout(null);
			panelLista.add(getScrollPane());
			
		}
		return panelLista;
	}
	private JPanel getPanel_1_1() {
		if (panelAceptar == null) {
			panelAceptar = new JPanel();
			panelAceptar.setLayout(null);
			panelAceptar.add(getBtnCancelar());
			panelAceptar.add(getBtnPaciente());
		}
		return panelAceptar;
	}
	private JList<Acompa�ante> getList() {
		if (list == null) {
			list = new JList<Acompa�ante>();
			modeloA=new DefaultListModel<Acompa�ante>();
			list.setModel(modeloA);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()==2) {
						
						Acompa�ante a=(Acompa�ante) list.getSelectedValue();
						int res=JOptionPane.showConfirmDialog(null, "�Esta seguro de que desea borrar este m�dico?","Confirmaci�n de eliminaci�n", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION)	
							modeloA.removeElement(a);
			}
				}
			});
			
		}
		return list;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(71, 117, 97, 25);
		}
		return btnCancelar;
	}
	private JButton getBtnPaciente() {
		if (btnPaciente == null) {
			btnPaciente = new JButton("A\u00F1adir paciente");
			btnPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(checkCamposPaciente()) {
						if(checkSoloLetras(txtPNombre.getText())||checkSoloLetras(txtPNombre.getText()))
									crearPaciente();
						else
							JOptionPane.showMessageDialog(null, "El nombre o apellido no puede contener caracteres distintos de letras");
					}
					else {
						JOptionPane.showMessageDialog(null, "Por favor,rellene todos los campos obligatorios");
						lbPNombre.setForeground(Color.RED);
						lbPEmail.setForeground(Color.RED);
						lblPApellidos.setForeground(Color.RED);
						
						
					}
						
				}

				

				
			});
			btnPaciente.setBounds(208, 117, 134, 25);
		}
		return btnPaciente;
	}
	/**
	 * Devulve true si los campos estan cubiertos
	 * @return
	 */
	private boolean checkCamposPaciente() {
		return !(txtPNombre.getText().isEmpty()||txtPApellidos.getText().isEmpty()||txtPEmail.getText().isEmpty());
		
	}
	private boolean checkSoloLetras(String cadena) {
		
		return cadena.matches("[a-zA-Z������������\\s]+");
	}
	
private boolean checkSoloNumeros(String cadena) {
		
		return cadena.matches("[0-9]+");
	}
	private void crearPaciente() {
		HistorialMedico h= new HistorialMedico();
		int movil=0;
	if(checkSoloNumeros(txtPnumero.getText()))
		try {
		 movil=Integer.parseInt(txtPnumero.getText());
		}catch (NumberFormatException e) {
		
		}
		
		Paciente p=new Paciente(txtPNombre.getText(),txtPApellidos.getText(),movil,getTxtPEmail().getText(),h.getHistorial());
		try {
			pbd.crearHistorial(h.getHistorial());
			pbd.crearPaciente(p);
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		crearAcompa�ante(p);
		
		JOptionPane.showMessageDialog(null, "Se ha a�adido correctamente el paciente "+ p.getNombre()+" "+ p.getApellido()+
				" \ncon n�mero de historial "+p.getHistorial() );
		dispose();
		
	}
	private void crearAcompa�ante(Paciente p) {
		Object[] obj= modeloA.toArray();
		
		List<Acompa�ante>ap=new ArrayList<Acompa�ante>();
		for (int i = 0; i < obj.length; i++) {
			ap.add((Acompa�ante)obj[i]);
			
			
		}
		
		for (int i = 0; i < ap.size(); i++) {
			ap.get(i).setCodPaciente(p.getCodePaciente());
			
			try {
				pbd.crearAcompa�ante(ap.get(i));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	private JLabel getLbAEmail() {
		if (lbAEmail == null) {
			lbAEmail = new JLabel("Email:");
			lbAEmail.setEnabled(false);
			lbAEmail.setBounds(257, 80, 56, 16);
		}
		return lbAEmail;
	}
	private JTextField getTxtAEmail() {
		if (txtAEmail == null) {
			txtAEmail = new JTextField();
			txtAEmail.setEnabled(false);
			txtAEmail.setBounds(311, 77, 279, 22);
			txtAEmail.setColumns(10);
		}
		return txtAEmail;
	}
}
