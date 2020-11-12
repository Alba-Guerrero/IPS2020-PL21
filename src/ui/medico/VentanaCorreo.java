/**
 * 
 */
package ui.medico;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import logica.Correo;
import logica.servicios.ParserBaseDeDatos;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import javax.swing.Box;

/**
 * @author Mar�a
 *
 */
public class VentanaCorreo extends JDialog{

	private ParserBaseDeDatos pbd=new ParserBaseDeDatos();
	private List<Correo> correos; // Los correos que ha recibido el m�dico
	
	private String codMedico;
	private JPanel pnIzquierda;
	private JPanel pnSur;
	private JPanel pnCentro;
	private JButton btnCancelar;
	private JPanel pnRedactar;
	private JButton btnRedactar;
	private JScrollPane scrollPRecibidos;
	private JPanel pnCorreosRecibidos;
	private JLabel lblRecibidos;
	
	
	/**
	 * Constructor para la ventana del correo del m�dico
	 * @param codmedico
	 */
	public VentanaCorreo(String codmedico) {
		
		try {
			correos = pbd.buscarCorreos(codmedico); // Cargo los correos que van dirigidos a mi m�dico de la base de datos
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("M\u00E9dico: Corrreo");
		this.codMedico = codmedico;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 946, 581);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPnIzquierda(), BorderLayout.WEST);
		getContentPane().add(getPnSur(), BorderLayout.SOUTH);
		getContentPane().add(getPnCentro(), BorderLayout.CENTER);
	}

	
	
	
	private JPanel getPnIzquierda() {
		if (pnIzquierda == null) {
			pnIzquierda = new JPanel();
			pnIzquierda.setLayout(new BorderLayout(0, 0));
			pnIzquierda.add(getPnRedactar(), BorderLayout.NORTH);
			pnIzquierda.add(getScrollPRecibidos(), BorderLayout.CENTER);
		}
		return pnIzquierda;
	}
	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnSur.add(getBtnCancelar());
		}
		return pnSur;
	}
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnCentro;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
		}
		return btnCancelar;
	}



	/**
	 * M�todo para cancelar y salir de la ventana del correo
	 */
	protected void salir() {
		dispose();
	}
	private JPanel getPnRedactar() {
		if (pnRedactar == null) {
			pnRedactar = new JPanel();
			pnRedactar.setLayout(new GridLayout(2, 1, 0, 0));
			pnRedactar.add(getBtnRedactar());
			pnRedactar.add(getLblRecibidos());
		}
		return pnRedactar;
	}
	private JButton getBtnRedactar() {
		if (btnRedactar == null) {
			btnRedactar = new JButton("Redactar");
		}
		return btnRedactar;
	}
	private JScrollPane getScrollPRecibidos() {
		if (scrollPRecibidos == null) {
			scrollPRecibidos = new JScrollPane();
			scrollPRecibidos.setViewportView(getPnCorreosRecibidos());
		}
		return scrollPRecibidos;
	}
	private JPanel getPnCorreosRecibidos() {
		if (pnCorreosRecibidos == null) {
			pnCorreosRecibidos = new JPanel();
			
			for (Correo correo : correos) {
				
				pnCorreosRecibidos.add(new PanelElementoListaCorreo(correo, this));
				//pnCorreosRecibidos.add(new JSeparator());
			}
			
			pnCorreosRecibidos.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnCorreosRecibidos;
	}
	private JLabel getLblRecibidos() {
		if (lblRecibidos == null) {
			lblRecibidos = new JLabel("Recibidos:");
		}
		return lblRecibidos;
	}




	/**
	 * M�todo que me muestra el correo que se seleccion�
	 * @param correo
	 */
	public void verCorreo(Correo correo) {
		
		Component[] componentes = pnCentro.getComponents(); // Los componentes que tiene el panel
		
		// Borramos todos los componentes que tenga
		for (Component c : componentes) {
			pnCentro.remove(c);
		}
	
		pnCentro.add(new PanelLeerCorreo(correo, this)); // A�adimos el panel para ver el correo
	
		// Lo vuelvo a repintar
		pnCentro.revalidate();
		pnCentro.repaint();
	}



	/**
	 * M�todo para limpiar el panel del centro y quede vac�o
	 */
	public void limpiarPanelCentro() {
		Component[] componentes = pnCentro.getComponents(); // Los componentes que tiene el panel
		
		// Borramos todos los componentes que tenga
		for (Component c : componentes) {
			pnCentro.remove(c);
		}
		
		
		// Lo vuelvo a repintar
		pnCentro.revalidate();
		pnCentro.repaint();
	}
}
