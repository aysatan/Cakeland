package components;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import assets.ColorPalette;
import assets.FontFamily;

public final class PasswordField extends JPasswordField {
	private boolean borderBottom;
	private boolean transparent = false;
	
	public PasswordField() {
		setDefault();
	}
	
	public PasswordField(boolean borderBottom, boolean transparent) {
		this.borderBottom = borderBottom;
		this.transparent = transparent;
		setDefault();
	}
	
	private void setDefault() {
		setBackground(ColorPalette.lightPink);
		setForeground(ColorPalette.navy);
		setBorder(BorderFactory.createEmptyBorder());
		setFont(FontFamily.lblFont1);
		setOpaque(!this.transparent);
		if (this.borderBottom) {
			setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorPalette.navy));
		}
	}
}
