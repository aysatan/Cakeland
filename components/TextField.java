package components;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

import assets.ColorPalette;
import assets.FontFamily;

public class TextField extends JTextField{
	private boolean borderBottom;
	private boolean transparent = false;
	
	public TextField() {
		setDefault();
	}
	
	public TextField(boolean borderBottom, boolean transparent) {
		this.borderBottom = borderBottom;
		this.transparent = transparent;
		setDefault();
	}
	
	private void setDefault() {
		setBackground(ColorPalette.lightPink);
		setBorder(BorderFactory.createEmptyBorder());
		setForeground(ColorPalette.navy);
		setFont(FontFamily.lblFont1);
		setOpaque(!this.transparent);
		if (this.borderBottom) {
			Border borderBottom = BorderFactory.createMatteBorder(0, 0, 2, 0, ColorPalette.navy);
			setBorder(BorderFactory.createCompoundBorder(getBorder(), borderBottom));
		} 
	}
}
