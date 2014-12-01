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
     * ͨ��JScrollPane��JViewport��ʹJTextAreaӵ�б���ͼƬ,����ͨ��TexturePaint��ƽ������ɵ�,
     * Ҫ��ʵ�ֱ���,ֻ��Ҫ��дpaintComponent(Graphics g) �Ĳ��ִ��뼴��.
     * ���岽��: 1.�����ú�texture = new TexturePaint(Image img , Rectangle rect );
     * 2.��дsetView(JComponent view)����,������ͼ�����opaque(false).����view.setOpaque(false);ʹ��ͼ��������Լ�����,�Ӷ�ͻ��JViewport����
     * 2.��paintComponent(Graphics g)��ʹ��super.paintComponent(g);
     * 3.��Graphics2D��setPaint(texture);Ȼ��fillRect����;
     */
    BufferedImage fgImg , bgImg;
    TexturePaint texture;
     
    //������ͼ����
    public void setBackgroundTexture(URL url) throws IOException{
        bgImg = ImageIO.read(url);
        Rectangle rect = new Rectangle(0,0,bgImg.getWidth(),bgImg.getHeight());
        texture = new TexturePaint(bgImg,rect);
    }
     
    //����ǰ��װ��
    public void setForegroundBadge(URL url) throws IOException{
        fgImg = ImageIO.read(url);
    }
     
    //��TexturePaint�����
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(texture != null){
            Graphics2D g2 = (Graphics2D)g;
            g2.setPaint(texture);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
     
    //�Ƚ�Ҫ�Ž�������ͼ�������Ϊopaque = false;
    public void setView(JComponent view){
        view.setOpaque(false);
        super.setView(view);
    }
     
    public static void main(String[] args) throws MalformedURLException, Exception {
        // TODO Auto-generated method stub
        JFrame jf = new JFrame("��ˮӡ��ScrollPane");
         
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