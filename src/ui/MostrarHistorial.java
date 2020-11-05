package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Cita;
import logica.HistorialMedico;
import logica.Preinscripcion;
import logica.servicios.ParserBaseDeDatos;

import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MostrarHistorial extends JDialog {

	private JPanel contentPane;
	private JPanel panelBotones;
	private JTabbedPane panelPestañas;
	private JPanel panelCausas;
	private JPanel panelEnfermedPrevia;
	private JPanel panelVacunas;
	
	private DefaultListModel<Preinscripcion> modeloListaM;
	
	
	private HistorialMedico hm;
	private Cita cita;
	private ParserBaseDeDatos pbd = new ParserBaseDeDatos();
	private JScrollPane scrollPaneCausas;
	private JTextArea textAreaCausas;
	private JScrollPane scrollPaneEnfermPrevia;
	private JTextArea textAreaEnfermPrev;
	private JScrollPane scrollPaneVacunas;
	private JTextArea textAreaVacunas;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MostrarHistorial(HistorialMedico hm,Cita cita) throws SQLException {
		setTitle("Historial m\u00E9dico");
		this.hm = hm;
		this.cita = cita;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelPestañas(), BorderLayout.CENTER);
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
		}
		return panelBotones;
	}
	private JTabbedPane getPanelPestañas() throws SQLException {
		if (panelPestañas == null) {
			panelPestañas = new JTabbedPane(JTabbedPane.TOP);
			panelPestañas.addTab("Causas", null, getPanelCausas(), null);
			panelPestañas.addTab("Enferm previas", null, getPanelEnfermedPrevia(), null);
			panelPestañas.addTab("Vacunas", null, getPanelVacunas(), null);
		}
		return panelPestañas;
	}
	private JPanel getPanelCausas() throws SQLException{
		if (panelCausas == null) {
			panelCausas = new JPanel();
			panelCausas.setLayout(new GridLayout(0, 1, 0, 0));
			panelCausas.add(getScrollPaneCausas());
		}
		return panelCausas;
	}
	private JPanel getPanelEnfermedPrevia() throws SQLException{
		if (panelEnfermedPrevia == null) {
			panelEnfermedPrevia = new JPanel();
			panelEnfermedPrevia.setLayout(new BorderLayout(0, 0));
			panelEnfermedPrevia.add(getScrollPaneEnfermPrevia());
		}
		return panelEnfermedPrevia;
	}
	private JPanel getPanelVacunas() throws SQLException {
		if (panelVacunas == null) {
			panelVacunas = new JPanel();
			panelVacunas.setLayout(new BorderLayout(0, 0));
			panelVacunas.add(getScrollPaneVacunas());
		}
		return panelVacunas;
	}
	
	
	private JScrollPane getScrollPaneCausas() throws SQLException{
		if (scrollPaneCausas == null) {
			scrollPaneCausas = new JScrollPane();
			scrollPaneCausas.setViewportView(getTextAreaCausas());
		}
		return scrollPaneCausas;
	}
	
	
	public String darCausas() throws SQLException {
		String causa = "";
		List<String> causas = new ArrayList<>();
		causas = pbd.buscarCausas(hm.getCodCausas());
		for(String c: causas) {
			causa += c + "\n";
		}
		return causa;
	}
	
	private JTextArea getTextAreaCausas() throws SQLException{
		if (textAreaCausas == null) {
			textAreaCausas = new JTextArea();
			textAreaCausas.setEditable(false);
			textAreaCausas.setText(darCausas());
		}
		return textAreaCausas;
	}
	private JScrollPane getScrollPaneEnfermPrevia() throws SQLException{
		if (scrollPaneEnfermPrevia == null) {
			scrollPaneEnfermPrevia = new JScrollPane();
			scrollPaneEnfermPrevia.setViewportView(getTextAreaEnfermPrev());
		}
		return scrollPaneEnfermPrevia;
	}
	
	
	public String darEnfermPrevias() throws SQLException {
		String enfermPrev = "";
		List<String> enfermPrevs = new ArrayList<>();
		enfermPrevs = pbd.buscarEnfermPrevias(hm.getCodEnfermPrevia());
		for(String e: enfermPrevs) {
			enfermPrev += e + "\n";
		}
		return enfermPrev;
	}
	
	private JTextArea getTextAreaEnfermPrev() throws SQLException {
		if (textAreaEnfermPrev == null) {
			textAreaEnfermPrev = new JTextArea();
			textAreaEnfermPrev.setEditable(false);
			textAreaEnfermPrev.setText(darEnfermPrevias());
		}
		return textAreaEnfermPrev;
	}
	private JScrollPane getScrollPaneVacunas() throws SQLException {
		if (scrollPaneVacunas == null) {
			scrollPaneVacunas = new JScrollPane();
			scrollPaneVacunas.setViewportView(getTextAreaVacunas());
		}
		return scrollPaneVacunas;
	}
	
	public String darVacunas() throws SQLException {
		String vacunas = "";
		List<String> nombreVacunas = new ArrayList<>();
		nombreVacunas = pbd.buscarVacunas(hm.getCodVacuna());
		for(String v: nombreVacunas) {
			vacunas += v + "\n";
		}
		return vacunas;
	}
	
	private JTextArea getTextAreaVacunas() throws SQLException {
		if (textAreaVacunas == null) {
			textAreaVacunas = new JTextArea();
			textAreaVacunas.setEditable(false);
			textAreaVacunas.setText(darVacunas());
		}
		return textAreaVacunas;
	}
}
