package components;

import java.awt.Color;

import javax.swing.JLabel;

import assets.ColorPalette;
import assets.FontFamily;

public class HeadingLabel4 extends JLabel{
	private String text;
	private Color color = ColorPalette.maroon;
	
	public HeadingLabel4(String text) {
		this.text = text;
		setDefault();
	}
	
	public HeadingLabel4(String text, Color color) {
		this.text = text;
		this.color = color;
		setDefault();
	}
	
	public void setDefault() {
		setText(this.text);
		setForeground(this.color);
		setFont(FontFamily.heading4);
	}
}
