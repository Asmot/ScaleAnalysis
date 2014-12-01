package com.example.scale;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BGJPanel extends JPanel {

	ImageIcon im_bg = new ImageIcon("bg.jpg");
	Image im = Toolkit.getDefaultToolkit().getImage("bg.jpg");
	
	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		arg0.drawImage(im, 0, 0, null);
	}

}
