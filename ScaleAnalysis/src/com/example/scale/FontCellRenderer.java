package com.example.scale;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

class FontCellRenderer extends JPanel implements ListCellRenderer
 {
    public Component getListCellRendererComponent(JList list, Object value, int index,
       boolean isSelected, boolean cellHasFocus)
    {
       text = (String)value;
       background = isSelected ? list.getSelectionBackground() : list.getBackground();
       foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
       return this;
    }

    public void paintComponent(Graphics g)
    {
       g.setColor(background);
       g.fillRect(0, 0, getWidth(), getHeight());  //���ñ���ɫ
       g.setColor(foreground);
       g.drawString(text, 5, 20);   //���ƶ�λ�û����ı�
    }

    public Dimension getPreferredSize()
    {
       return new Dimension(35, 30);   //Cell�ĳߴ�
    }

    private String text;
    private Color background = Color.gray;
    private Color foreground = Color.BLUE; 
 }