package sankeladder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Canvas1 extends Canvas
{
    BufferedImage im1;
    int size;
    int snake_image=0;
    Canvas1() throws IOException
    {
        super();
        im1 = ImageIO.read(new File("box.png"));
        System.out.println("Canvas object created!!!");
        size = 8;
    }
    @Override
  public void paint(Graphics g) {
        try {
    //        g.setFont(new java.awt.Font("ToledoLH", 1, 18));
            snake_image=0;
            System.out.println("Start of paint function!!!");
            Objects ob = new Objects();
            g.drawImage(ob.snake_ladder_top,(25*size)-125, 0, this);
            int k=1,l=1;
            String str=null;
            for(int i=1;i<(size+1);i++)
            {
                for(int j=0;j<size;j++)
                {
                    g.drawImage(im1,(50*j),(50*i), this);
                    if((k/size)%2==0)
                    {
                        if((k%size)==0)
                        {
                            l=((k/size)*size)-j;
                            str= Integer.toString(l);
                            g.drawString(str,(50*j)+12 ,(50*i)+25 );
                            k++;
                        }
                        else
                        {
                        str= Integer.toString(k);
                        g.drawString(str,(50*j)+12 ,(50*i)+25 );
                        k++;
                        }
                    }
                    else
                    {
                        if(k%(size)==0)
                        {
                            str= Integer.toString(k);
                            g.drawString(str,(50*j)+12 ,(50*i)+25 );
                            k++;
                        }
                        else
                        {
                            l=((k/(size))*(size))+((size)-j);
                            str= Integer.toString(l);
                            g.drawString(str,(50*j)+12 ,(50*i)+25 );
                            k++;
                        }
                    }
                }
            }
            System.out.println("Painting completed!!!");
        } catch (Exception ex) {
            Logger.getLogger(Canvas1.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
  }

    @Override
  public Dimension getMinimumSize() {
    return new Dimension(50, 100);
  }

    @Override
  public Dimension getPreferredSize() {
    return new Dimension(50*size,(50*size)+60);
  }

    @Override
  public Dimension getMaximumSize() {
    return new Dimension(700 , 650);
  }
}
