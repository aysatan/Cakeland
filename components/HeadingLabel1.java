package components;

import javax.swing.JLabel;

import assets.ColorPalette;
import assets.FontFamily;

public class HeadingLabel1 extends JLabel{
	private String text;
	
	public HeadingLabel1(String text) {
		this.text = text;
		setDefault();
	}
	
	public void setDefault() {
		setText(this.text);
		setForeground(ColorPalette.maroon);
		setFont(FontFamily.heading1);
	}
}
