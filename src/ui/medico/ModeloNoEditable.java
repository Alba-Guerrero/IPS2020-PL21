package ui.medico;

import javax.swing.table.*;

public class ModeloNoEditable extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public ModeloNoEditable(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount); 
   }
	@Override
	public boolean isCellEditable(int row, int column) {
        return false;
    }
	
}
