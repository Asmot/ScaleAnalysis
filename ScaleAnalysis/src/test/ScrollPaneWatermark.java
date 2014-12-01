package test;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
 
 
public class ScrollPaneWatermark extends JViewport{
 
    /**
     * @param args
     * 通过JScrollPane和JViewport来使JTextArea拥有背景图片,可以通过TexturePaint的平铺来完成的,
     * 要想实现背景,只需要重写paintComponent(Graphics g) 的部分代码即可.
     * 具体步骤: 1.先设置好texture = new TexturePaint(Image img , Rectangle rect );
     * 2.重写setView(JComponent view)方法,先设视图组件的opaque(false).即设view.setOpaque(false);使视图组件不画自己背景,从而突出JViewport背景
     * 2.在paintComponent(Graphics g)中使用super.paintComponent(g);
     * 3.用Graphics2D来setPaint(texture);然后fillRect背景;
     */
    BufferedImage fgImg , bgImg;
    TexturePaint texture;
     
    //设置视图背景
    public void setBackgroundTexture(URL url) throws IOException{
        bgImg = ImageIO.read(url);
        Rectangle rect = new Rectangle(0,0,bgImg.getWidth(),bgImg.getHeight());
        texture = new TexturePaint(bgImg,rect);
    }
     
    //设置前置装饰
    public void setForegroundBadge(URL url) throws IOException{
        fgImg = ImageIO.read(url);
    }
     
    //用TexturePaint画组件
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(texture != null){
            Graphics2D g2 = (Graphics2D)g;
            g2.setPaint(texture);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
     
    //先将要放进来的视图组件设置为opaque = false;
    public void setView(JComponent view){
        view.setOpaque(false);
        super.setView(view);
    }
     
    public static void main(String[] args) throws MalformedURLException, Exception {
        // TODO Auto-generated method stub
        JFrame jf = new JFrame("带水印的ScrollPane");
         
        JTextArea jta = new JTextArea();
        jta.setText("this is a test  http://www.baidu.com");
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
         
        ScrollPaneWatermark watermark = new ScrollPaneWatermark();
        watermark.setBackgroundTexture(new File("bg.jpg").toURI().toURL());
        watermark.setView(jta);
         
        JScrollPane jsp = new JScrollPane();
        jsp.setViewport(watermark);
         
        jf.getContentPane().add(jsp);
        jf.pack();
        jf.setVisible(true);
         
    }
 
}