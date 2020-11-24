package logica;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import logica.servicios.ParserBaseDeDatos;




/**
 * 
 */

/**
 * @author María
 *
 */
public class LecturaExcel {
	
	private static ParserBaseDeDatos pbd=new ParserBaseDeDatos();

	public static void cargarDiagnosticosCie10() throws SQLException{
		try {
			//String rutaArchivo = "input/hojanueva.xlsx";
			FileInputStream fis = new FileInputStream("lib/diagnosticoscie10.xlsx"); // Tenemos nuestro archivo
			
			XSSFWorkbook book = new XSSFWorkbook(fis); // Creamos el libro
			
			XSSFSheet sheet =  book.getSheet("Hoja1"); // Ponemos la hoja que queremos leer
			int rows = sheet.getLastRowNum(); // Cogemos el número total de filas que tieene la hoja
			
			Row row; 
			for (int i = 0; i <= rows; i++) { // Recorremos todas las filas
				row = sheet.getRow(i); // Con la fila en la que estamos iterando...
				
				Cell cell1 = row.getCell(0);
				String codigo = "";
				if (!cell1.getStringCellValue().equals("")) {
					codigo = cell1.getStringCellValue() + "\t";
				}
				Cell cell2 = row.getCell(1);
				String diagnostico = "";
				if (!cell2.getStringCellValue().equals("")) {
					diagnostico = cell2.getStringCellValue() + "\t";
				}		
				
				// Tendremos que crear el diagnóstico
				Diagnostico d = new Diagnostico(codigo, diagnostico); // Creamos el objeto diagnóstico con sus valores		
				pbd.nuevoDiagnostico(d);
				
				
				System.out.println(codigo + diagnostico);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
