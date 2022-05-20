package components;

import javax.swing.JMenuBar;
import javax.swing.UIManager;

import assets.ColorPalette;

public class MenuBar extends JMenuBar{
	
	public MenuBar() {
		setDefault();
	}
	
	private void setDefault() {
		setBackground(ColorPalette.lightPink);
		UIManager.put("Menu.foreground", ColorPalette.navy);
		UIManager.put("MenuItem.background", ColorPalette.lightPink);
	}
}
