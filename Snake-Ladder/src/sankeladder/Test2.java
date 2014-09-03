/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sankeladder;

/**
 *
 * @author SONY
 */
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Test2 extends JFrame{
  private static final long serialVersionUID = 1L;

  //public static BufferedImage image;

 /* public Test2(BufferedImage image, String filename, int i, int j) {
    try {
      image = ImageIO.read(new File(filename));
    } catch (IOException ie) {
      ie.printStackTrace();
    }
  }

/*  public void paint(Graphics g) {
    g.drawImage(image, 25, 25, null);
 * Panel panel = new ShowImage(args[0]);
  }
*/
  static public void main(String args[]) throws Exception {
    //BufferedImage image = ;
    try {
    BufferedImage image = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
    BufferedImage im1 = ImageIO.read(new File("box.png"));
   // JFrame frame = new Test2(image,"box.png",25,25);
    JFrame frame = new JFrame();
    frame.setLayout(new GridLayout());
    ImageObserver im = new ImageObserver() {

            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        
    Graphics g = image.createGraphics();
    if(g != null)
    {
        g.setClip(0,0,image.getWidth(),image.getHeight());
        g.setPaintMode();
        g.setColor(Color.red);
        g.drawRect(10, 10, 20, 20);
        g.drawImage(im1, 100, 100, im);
    }
    else
    {
        System.out.println("vjgk");
    }

    //g = image.getGraphics();
    //g.drawImage(image, 100, 100, im);
    //frame.getContentPane().add(panel);
    ImageIcon II = new ImageIcon(image);
    System.out.println(II.getIconHeight()+"   "+II.getIconWidth());
    JLabel j = new JLabel(II);

   // j.setLocation(50, 74);
    //j.setBounds(50, 50, 25, 25);
    
    //j.prepareImage(image, im);
    //j.paint(g);
    frame.setSize(400, 400);
    frame.add(j);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// frame.prepareImage(image, 100, 100, im);
    //frame.paint(g);
    //g.drawImage(image, 50, 50, im);
    //frame.pack();
    frame.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

 /* static public void main(String args[]) throws Exception {
      JFrame frame = new Test2();
      BufferedImage image = ImageIO.read(new File("box.png"));
      System.out.println(image.getTileHeight());
      Graphics g = image.createGraphics();
      g.drawImage(image, 0, 0, null);
      //frame.add(frame);
      //System.out.println(image.getPropertyNames());
      ImageIcon II = new ImageIcon(image);

  }
}
*/