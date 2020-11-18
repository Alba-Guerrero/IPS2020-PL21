package logica.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import logica.AsignaDiagnostico;
import logica.AsignaPreinscripcion;
import logica.Paciente;
import logica.asignar.Asigna;


public class PrescripcionesDownload {
	private ParserBaseDeDatos pbd;
	private PDPageContentStream pcs;
	private int contador=0;
	private int inicio=0;
	private int fin=44;
	private PDDocument pDDocument ;
	private  PDAcroForm pDAcroForm;
	
	private String CONFIDENTIAL ="Este documento(s) se dirige exclusivamente a su(s) destinatario(s) y puede contener"
			+ " información privilegiada o confidencial. El acceso a esta información por otras personas distintas a "
			+ "las designadas no está autorizado. Si Vd. no es el destinatario indicado, queda notificado que la utilización,"
			+ " divulgación y/o copia sin autorización está prohibida en virtud de la legislación vigente";
	
	
	public PrescripcionesDownload() {
		super();
		pbd=new ParserBaseDeDatos();
	}


	public void receta(Paciente p) {
		if(p instanceof Paciente) {
	try {
		InputStream inputStream       = new FileInputStream("pr.pdf");
	
	     pDDocument = PDDocument.load(inputStream);
	   
	    
	   
	    PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
	   
        
        FileInputStream fontFile = new FileInputStream(new File("Calibri.ttf"));
        PDFont font = PDType0Font.load(pDDocument, fontFile, false);
	    
        
        List<PDField> fields = pDAcroForm.getFields();
        for (PDField field : fields)
        {
        COSDictionary dict = field.getCOSObject();
        COSString defaultAppearance = (COSString) dict
                .getDictionaryObject(COSName.DA);
        if (defaultAppearance != null)
        {
            dict.setString(COSName.DA, "/Helv 9.5 Tf 0 g");
        }
	   
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaString = sdf.format(new Date());
	    
	    PDField fecha = pDAcroForm.getField("fecha");
	    fecha.setValue(fechaString);
	    
	    PDField nhistorial = pDAcroForm.getField("nhistorial");
	    
	    nhistorial.setValue(p.getHistorial());
	    
	    PDField motivo = pDAcroForm.getField("informe");
	    motivo.setValue("A petición");

	    PDField fieldName = pDAcroForm.getField("nombre");
	    fieldName.setValue(p.getNombre());
	    PDField apellido = pDAcroForm.getField("apellido");
	    apellido.setValue(p.getApellido());
	    
	    PDField codPaciente = pDAcroForm.getField("codigo");
	    codPaciente.setValue(p.getCodePaciente());
	    
	    PDField movil = pDAcroForm.getField("movil");
	    movil.setValue(p.getMovil()+"");
	    
	    PDField email = pDAcroForm.getField("email");
	    email.setValue(p.getEmail());
	    
	    PDField historial = pDAcroForm.getField("historial");
	    historial.setValue(p.getHistorial());
	    
	   
	    PDField fechatabla = pDAcroForm.getField("fechatabla");
	    fechatabla.setAlternateFieldName("fechatabla1");
	    PDField prescripcion = pDAcroForm.getField("pres");
	    PDField cantidad = pDAcroForm.getField("cantidad");
	    PDField intervalo = pDAcroForm.getField("intervalo");
	    PDField duracion = pDAcroForm.getField("duracion");
	    PDField instrucciones = pDAcroForm.getField("instrucciones");
	    List<StringBuilder>pres=datos(p.getHistorial());
	  
	    	fechatabla.setValue(pres.get(0).toString());
	 		prescripcion.setValue(pres.get(1).toString());
	 		cantidad.setValue(pres.get(2).toString());
	 		intervalo.setValue(pres.get(3).toString());
	 		duracion.setValue(pres.get(4).toString());
	 		instrucciones.setValue(pres.get(5).toString());
	 		while(contador>45) {
	 			pres=datos(p.getHistorial());
	 			nuevaPagina(pres);
	 			contador=-44;
	 		}
	    
        
        }
       // addFooter(pDDocument);
	    pDDocument.save(p.getHistorial()+"Receta.pdf");
	    pDDocument.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}finally {
		  //pDDocument.close();
	}
	
	
	}
		
		
	}
	
	
	private void nuevaPagina(List<StringBuilder>pres) throws IOException {
		 PDPage page1 = new PDPage();
		 
		 pDDocument.addPage(page1);
		 PDAcroForm form = pDAcroForm;
		 
		 PDField fechatabla = form.getField("fechatabla");
		 
		    PDField prescripcion = form.getField("pres");
		    PDField cantidad = form.getField("cantidad");
		    PDField intervalo = form.getField("intervalo");
		    PDField duracion = form.getField("duracion");
		    PDField instrucciones = form.getField("instrucciones");
		    
		    	fechatabla.setValue(pres.get(0).toString());
		 		prescripcion.setValue(pres.get(1).toString());
		 		cantidad.setValue(pres.get(2).toString());
		 		intervalo.setValue(pres.get(3).toString());
		 		duracion.setValue(pres.get(4).toString());
		 		instrucciones.setValue(pres.get(5).toString());
		 
		
	}


	private  List<StringBuilder> datos(String historial){
		List<StringBuilder> sbs= new ArrayList<StringBuilder>();
		List<AsignaPreinscripcion> pres= new ArrayList<AsignaPreinscripcion>();
		StringBuilder sbfecha=new StringBuilder();
		StringBuilder sbprescripcion=new StringBuilder();
		StringBuilder sbcantidad=new StringBuilder();
		StringBuilder sbintervalo=new StringBuilder();
		StringBuilder sbduracion=new StringBuilder();
		StringBuilder sbinstrucciones=new StringBuilder();
		
		
		try {
			pres=pbd.asignaPrescricpionesFechaHistorial(historial);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(inicio==0)
		contador=pres.size();
		
		
		 for (int i = 0; i <pres.size(); i++) {
			 System.err.println(i);
			 sbfecha.append(pres.get(i).getFecha().toString()+"\n");
			 sbprescripcion.append(pres.get(i).getCodigoPreinscripcion()+"\n");
			 sbcantidad.append(pres.get(i).getCantidad()+""+"\n");
			 sbintervalo.append(pres.get(i).getIntervalo()+""+"\n");
			 sbduracion.append(pres.get(i).getDuracion()+""+"\n");
			 sbinstrucciones.append(+i+"\n");
			 
			}
		// pres.get(i).getInstrucciones();
		   for (int i = 0; i < 44; i++) {
			   pres.remove(i);
			
		}

		 sbs.add(sbfecha);
		 sbs.add(sbprescripcion);
		 sbs.add(sbcantidad);
		 sbs.add(sbintervalo);
		 sbs.add(sbduracion);
		 sbs.add(sbinstrucciones);
		
		this.inicio=this.fin;
		this.fin=+45;
		return sbs ;
		
		
	}
	
	
	private void addFooter(final PDDocument doc) throws IOException {

		PDPageContentStream stream = null;
		try {

		PDPageTree pages = doc.getDocumentCatalog().getPages();

		for (int i = 0; i < pages.getCount(); i++) {


		PDPage page = (pages.get(i));
		stream = new PDPageContentStream(doc, page, AppendMode.APPEND, true, true);
		stream.beginText();
		stream.setFont(PDType1Font.HELVETICA, 10);
		stream.newLineAtOffset(20f, PDRectangle.A4.getLowerLeftY());
		if(i==pages.getCount()-1)
		stream.showText(CONFIDENTIAL);
		
		stream.newLineAtOffset((PDRectangle.A4.getUpperRightX() / 2),
		(PDRectangle.A4.getLowerLeftY()));
		stream.showText((i + 1) + " - " + pages.getCount());
		stream.endText();
		stream.close();

		}
		} catch (final IOException exception) {
		throw new RuntimeException(exception);
		} finally {

		if (stream != null) {
		try {
		stream.close();
		} catch (final IOException exception) {
		throw new RuntimeException(exception);
		}

		}
		}

		}
}
