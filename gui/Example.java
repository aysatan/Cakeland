package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.TextField;
import database.ViewAllMenuQuery;
import model.Cart;

	public class Example {
		public static void main(String[] args) {
			new ViewAllMenuQuery().addToCart(new Cart("U7498", "CON8164", 3));
		}
	}
	


