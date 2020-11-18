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
import logica.Paciente;
import logica.asignar.Asigna;


public class Historial {
	private ParserBaseDeDatos pbd;
	private PDPageContentStream pcs;
	
	private String CONFIDENTIAL ="Este documento(s) se dirige exclusivamente a su(s) destinatario(s) y puede contener"
			+ " información privilegiada o confidencial. El acceso a esta información por otras personas distintas a "
			+ "las designadas no está autorizado. Si Vd. no es el destinatario indicado, queda notificado que la utilización,"
			+ " divulgación y/o copia sin autorización está prohibida en virtud de la legislación vigente";
	
	
	public Historial() {
		super();
		pbd=new ParserBaseDeDatos();
	}


	public void escribirhistorial(Paciente p) {
		if(p instanceof Paciente) {
	try {
		InputStream inputStream       = new FileInputStream("TemplateH.pdf");
	
	    PDDocument pDDocument = PDDocument.load(inputStream);
	    PDPage page1 = pDDocument.getPage(0);
	    
	   
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
            dict.setString(COSName.DA, "/Helv 10 Tf 0 g");
        }
	   
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaString = sdf.format(new Date());
	    
	    PDField fecha = pDAcroForm.getField("fecha");
	    fecha.setValue(fechaString);
	    
	    PDField nhistorial = pDAcroForm.getField("nhistorial");
	    
	    nhistorial.setValue(p.getHistorial());
	    
	    PDField motivo = pDAcroForm.getField("motivo");
	    motivo.setValue("A petición");

	    PDField fieldName = pDAcroForm.getField("nombre");
	    fieldName.setValue(p.getNombre());
	    PDField apellido = pDAcroForm.getField("apellido");
	    apellido.setValue(p.getApellido());
	    
	    PDField codPaciente = pDAcroForm.getField("codpaciente");
	    codPaciente.setValue(p.getCodePaciente());
	    
	    PDField movil = pDAcroForm.getField("movil");
	    movil.setValue(p.getMovil()+"");
	    
	    PDField email = pDAcroForm.getField("email");
	    email.setValue(p.getEmail());
	    
	    PDField historial = pDAcroForm.getField("historial");
	    historial.setValue(p.getHistorial());
	    
	   
	    PDField tabla1 = pDAcroForm.getField("tabla1");
	    PDField tablafecha = pDAcroForm.getField("tablafecha");
	    List<StringBuilder>sbs=datosDiagnostico(p.getHistorial());
	    tabla1.setValue(sbs.get(0).toString());
	 		tablafecha.setValue(sbs.get(1).toString());
        
        }
      
	    pDDocument.save(p.getHistorial()+"2.pdf");
	    pDDocument.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}finally {
		  //pDDocument.close();
	}
	
	
	}
		
		
	}
	
	
	private  List<StringBuilder> datosDiagnostico(String historial){
		List<String> diagnosticos= new ArrayList<String>();
		StringBuilder sb= new StringBuilder();
		StringBuilder fechas= new StringBuilder();
		List<StringBuilder>sbs=new ArrayList<StringBuilder>();
		
		try {
			diagnosticos=pbd.buscarDiagnosticosAsignados(historial);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < diagnosticos.size(); i++) {
			String[]aux=diagnosticos.get(i).split("\t");
			String[]fecha=aux[1].split(" ");
			sb.append(aux[0]);
			sb.append("\n");
			fechas.append(fecha[1]);
			fechas.append("\n");
			System.err.println(sb);
		}
		
		sbs.add(sb);
		sbs.add(fechas);
		
		return sbs;
		
		
	}
	
	
	private void addFooter(final PDDocument doc, final String reportName) throws IOException {

		PDPageContentStream stream = null;
		try {

		PDPageTree pages = doc.getDocumentCatalog().getPages();

		for (int i = 0; i < pages.getCount(); i++) {


		PDPage page = (pages.get(i));
		stream = new PDPageContentStream(doc, page);
		stream.beginText();
		stream.setFont(PDType1Font.HELVETICA, 10);
		stream.newLineAtOffset(10f, PDRectangle.A4.getLowerLeftY());
		stream.showText(reportName);
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
