/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sankeladder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author SONY
 */
public class Objects {
    BufferedImage im1,snake_ladder_top;
 //   int start_x,start_y,start_red_x,start_red_y,pos,abc,carry;//,prev_red_x,prev_red_y;
    int carry,pos,start_red_x,start_red_y;
    Objects() throws IOException
    {
    //    im1 = ImageIO.read(new File("red_coin.png"));
        snake_ladder_top=ImageIO.read(new File("snake_and_ladder_top.png"));
   //     start_x = 0;
     //   start_y = 0;
        start_red_x=0;
        start_red_y=0;
       // abc=0;
        carry=0;
      //  prev_red_x=0;
        //prev_red_y=0;
        pos=0;
    }

}
