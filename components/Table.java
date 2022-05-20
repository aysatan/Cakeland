package components;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import assets.ColorPalette;
import assets.FontFamily;

public class Table extends JScrollPane{
	private DefaultTableModel model;
	private JTable table = new JTable();
	
	public Table(Object[] header) {
		model = new DefaultTableModel(header, 0);
		
		setDefault();
		reset();
	}
	
	public JTable getTable() {
		return this.table;
	}
	
	public void eraseTable() {
		this.model.setRowCount(0);
		reset();
	}
	
	public void addTable(Object[] data) {
		this.model.addRow(data);
		reset();
	}
	
	public void reset() {
		this.table.setModel(model);
		getViewport().add(this.table);
	}
	
	private void setDefault() {
		this.table.getTableHeader().setBackground(ColorPalette.midPink);
		this.table.getTableHeader().setForeground(ColorPalette.maroon);
		this.table.getTableHeader().setFont(FontFamily.heading3);
		this.table.setBackground(ColorPalette.lightPink);
		this.table.setForeground(ColorPalette.maroon);
		this.table.setFont(FontFamily.tblFont);
	}
	
}
