package components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import assets.ColorPalette;
import assets.FontFamily;

public class Frame extends JFrame {
	private int x = 700;
	private int y = 500;
	private String title;
	
	public Frame(String title) {
		this.title = title;
		setDefault();
	}
	
	public Frame(int x, int y, String title) {
		this.x = x;
		this.y = y;
		this.title = title;
		setDefault();
	}
	
	public Frame(char dimensionType, int dimension, String title) {
		switch (dimensionType) {
		case 'x':
			this.x = dimension;
			break;
		case 'y':
			this.y = dimension;
			break;
		}
		this.title = title;
		setDefault();
	}
	
	public void Window() {
		setTitle(this.title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(this.x, this.y);
		setLocationRelativeTo(null);
		getContentPane().setBackground(ColorPalette.pink);
		setVisible(true);
	}
	
	private void setDefault() {
		UIManager.put("Label.font", FontFamily.lblFont1);
		UIManager.put("Label.foreground", ColorPalette.navy);
	}
	
}
