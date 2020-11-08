package ui.medico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;

public class CalendarioVacunas extends JDialog {

	private JPanel contentPane;
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private JTable tablaVacunas;
	private ModeloNoEditable modeloTabla;
	private ModeloNoEditable modeloNombres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarioVacunas frame = new CalendarioVacunas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalendarioVacunas() {
		setTitle("Calendario de vacunas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaVacunas());
		}
		return scrollPane;
	}
	private JTable getTablaVacunas() {
		if (tablaVacunas == null) {
			String[] edad = {"VACUNAS", "2 meses", "3 meses", "4 meses", "5 meses", "11 meses", "12 meses", "15 meses", "3-4 años", "6 años", "12 años", "14 años", "15-18 años"};
			String[] vacunas = {"Hepatitis B", "Difteria, tétanos y tosferina", "Poliomelitis", "Influenza", "Neumococo", "Rotavirus", "Menungococo B", "Meningococo C y ACWY",
					"Sarampión, rubeola y parotiditis", "Varicela", "Virus del papiloma humano"};
			modeloTabla = new ModeloNoEditable(edad, 11);
			tablaVacunas = new JTable(modeloTabla);
			tablaVacunas.setValueAt("HB", 0, 0);
			tablaVacunas.setValueAt("HB", 0, 2);
			tablaVacunas.setValueAt("HB", 0, 4);
			
			tablaVacunas.setValueAt("DTPa", 1, 0);
			tablaVacunas.setValueAt("DTPa", 1, 2);
			tablaVacunas.setValueAt("DTPa", 1, 4);
			tablaVacunas.setValueAt("DTPa", 1, 0);
			tablaVacunas.setValueAt("DTPa/Tdpa", 1, 8);
			tablaVacunas.setValueAt("Tdpa", 1, 9);
			tablaVacunas.setValueAt("Tdpa", 1, 10);
			
			tablaVacunas.setValueAt("VPI", 2, 0);
			tablaVacunas.setValueAt("VPI", 2, 2);
			tablaVacunas.setValueAt("VPI", 2, 4);
			tablaVacunas.setValueAt("VPI", 2, 8);
			
			tablaVacunas.setValueAt("Hib", 3, 0);
			tablaVacunas.setValueAt("Hib", 3, 2);
			tablaVacunas.setValueAt("Hib", 3, 4);
			
			tablaVacunas.setValueAt("VNC", 4, 0);
			tablaVacunas.setValueAt("VNC", 4, 2);
			tablaVacunas.setValueAt("VNC", 4, 4);
			
			tablaVacunas.setValueAt("RV", 5, 0);
			tablaVacunas.setValueAt("RV", 5, 1);
			tablaVacunas.setValueAt("RV", 5, 2);
			
			tablaVacunas.setValueAt("MenB", 6, 1);
			tablaVacunas.setValueAt("MenB", 6, 3);
			tablaVacunas.setValueAt("MenB", 6, 5);
			tablaVacunas.setValueAt("MenB", 6, 6);
			
			tablaVacunas.setValueAt("MenC", 7, 2);
			tablaVacunas.setValueAt("Men ACWY", 7, 5);
			tablaVacunas.setValueAt("Men ACWY", 7, 9);
			tablaVacunas.setValueAt("Men ACWY", 7, 10);
			tablaVacunas.setValueAt("Men ACWY", 7, 11);
			
			tablaVacunas.setValueAt("SRP", 8, 5);
			tablaVacunas.setValueAt("SRP Var /", 8, 7);
			
			tablaVacunas.setValueAt("Var", 9, 6);
			tablaVacunas.setValueAt("SRP", 9, 7);
			
			tablaVacunas.setValueAt("VPH", 10, 9);
		}
		return tablaVacunas;
	}
}
