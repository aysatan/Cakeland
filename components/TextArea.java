package components;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import assets.ColorPalette;
import assets.FontFamily;

public final class TextArea extends JTextArea {

	public TextArea(int rows, int columns) {
		setRows(rows);
		setColumns(columns);
		setDefault();
	}
	
	private void setDefault() {
		setBackground(ColorPalette.lightPink);
		setBorder(BorderFactory.createEmptyBorder());
		setForeground(ColorPalette.navy);
		setFont(FontFamily.lblFont1);
	}

}
