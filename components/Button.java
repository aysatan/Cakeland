package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import assets.ColorPalette;
import assets.FontFamily;

public class Button extends JButton {
	private String text;
	private boolean border = false;
	private Font font = FontFamily.btnFont1;
	private Color fontColor = ColorPalette.navy;
	private boolean transparent = false;
	
	public Button(String text) {
		this.text = text;
		setDefault();
	}
	
	public Button(String text, Font font) {
		this.text = text;
		this.font = font;
		setDefault();
	}
	
	public Button(String text, boolean border) {
		this.text = text;
		this.border = border;
		setDefault();
	}
	
	public Button(boolean transparent, String text) {
		this.text = text;
		this.transparent = transparent;
	}
	
	public Button(String text, Font font, boolean border) {
		this.text = text;
		this.font = font;
		this.border = border;
		setDefault();
	}
	
	public Button(boolean transparent, String text, Font font) {
		this.text = text;
		this.font = font;
		this.transparent = transparent;
		setDefault();
	}
	
	public Button(boolean transparent, String text, Font font, boolean border) {
		this.text = text;
		this.font = font;
		this.transparent = transparent;
		this.border = border;
		setDefault();
	}
	
	public Button(boolean transparent, String text, Font font, Color fontColor) {
		this.text = text;
		this.font = font;
		this.transparent = transparent = transparent;
		this.fontColor = fontColor;
		setDefault();
	}
	
	private void setDefault() {
		setBackground(ColorPalette.lightPink);
		setForeground(this.fontColor);
		setText(this.text);
		setFont(this.font);
		setBorderPainted(this.border);
		setOpaque(!this.transparent);
		setContentAreaFilled(!this.transparent);
	}
}
