/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sankeladder;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author SONY
 */
public class Design_Board2 {
    static int pos=0;
    static int start=0;
    static int end = 0;
    BufferedImage im_snake_head,im_snake_tail,im_ladder_head,im_ladder_tail;
    //Canvas1 c1 = new Canvas1();
    public void design_board(final int size,final int player_num) throws IOException
 //   public static void main(String args[]) throws IOException
    {
  //      final int size = 8;
        im_snake_head = ImageIO.read(new File("box_snake_design.png"));
        im_snake_tail = ImageIO.read(new File("box_snake_design_back.png"));
        im_ladder_head = ImageIO.read(new File("box_ladder_design.png"));
        im_ladder_tail = ImageIO.read(new File("box_ladder_design_back.png"));
        pos=0;
        start=0;
        end=0;
        final int []snakes = new int[(size*size)+1];
        final int []ladders = new int[(size*size)+1];
        for(int a=0;a<((size*size)+1);a++)
        {
            snakes[a]=-1;
            ladders[a]=-1;
        }
        final Objects ob1 = new Objects();
        final JFrame f = new JFrame();
        f.getContentPane().setLayout(null);
        f.setSize(1200,700);
        f.setResizable(false);
        f.setVisible(true);
        //f.setIconImage(im_snake_head);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel j1 = new JPanel();
        j1.setBounds(((12-size)*25)+50,(12-size)*25,(50*size),(50*size)+60);
        j1.setLayout(new java.awt.FlowLayout());
        //j1.setBounds(50, 0, 660, 660);
        f.add(j1);
        final Canvas1 c1 = new Canvas1();
        c1.size=size;
        j1.add(c1);
        final JTextField Message_Box = new JTextField();
        Message_Box.setBackground(Color.orange);
        Message_Box.setEditable(false);
        Message_Box.setText("Message...................................");
        //Message_Box.setForeground(Color.orange);
        Message_Box.setFont(new java.awt.Font("Times New Roman", 3, 22));
        JButton bt_snake = new JButton();
        JButton bt_ladder = new JButton();
        JButton bt_cancel = new JButton();
        JButton bt_play = new JButton();
        bt_snake.setBounds(750, 320, 100, 25);
        bt_ladder.setBounds(750, 355, 100, 25);
        bt_play.setBounds(875, 337, 100, 25);
        f.add(bt_snake);
        f.add(bt_ladder);
   //     f.add(bt_cancel);
        f.add(bt_play);
        f.add(Message_Box);
        Message_Box.setBounds(750,400 ,400, 25);
        final int []snake_ladder = new int[((size*size)+1)];
        for(int a=0;a<((size*size)+1);a++)
        {
            snake_ladder[a]=-1;
        }
        bt_snake.setText("Add Snake");
        bt_ladder.setText("Add Ladder");
        bt_cancel.setText("Cancel Last");
        bt_play.setText("Lets Play>>");
        final ImageObserver io=null;
        final MouseListener Event_c1_ladder = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet.");
                pos=0;
                System.out.println("Event_c1_ladder starts");
                Point point = e.getPoint();
                if(e.getY()<50)
                {
                    return;
                }
                int start_x = (point.x/50)+1;
                int start_y = ((point.y-50)/50);
        //        int start_x = ((point.x-)/50)+1;
          //      int start_y = ((point.y-ob1.start_y-50)/50);
                if(start_y%2==0)
                {
                    pos=size*start_y+start_x;
                }
                else
                {
                    pos=(size*(start_y+1))-start_x+1;
                }
                System.out.println(start_x+" adc  "+start_y+"    "+pos);
                if(pos>0 && pos<=(size*size))
                {
                    if(start==0 && snakes[pos]==-1 && ladders[pos]==-1)
                    {

                        int i=0;
                        for(i=0;i<((size*size)+1);i++)
                        {
                            if(snakes[i]==pos || ladders[i]==pos)
                            {
                                start=0;
                                end=0;
                                pos=0;
                                Message_Box.setText("Retry");
                                System.out.println("Retry  115");
                                break;
                            }
                        }
                        if(i==((size*size)+1))
                        {
                            Message_Box.setText("Source Selected, Select the Target");
                            start=pos;
                        }
                        else
                        {
                            start=0;
                            end=0;
                            pos=0;
                            Message_Box.setText("Retry");
                            System.out.println("Retry    130");
                        }
                    }
                    else{
                        if(start!=0)
                        {
                            if(snakes[pos]==-1 && ladders[pos]==-1 && pos>start)
                            {
                                int i=0;
                                for(i=0;i<((size*size)+1);i++)
                                {
                                    if(snakes[i]==pos || ladders[i]==pos)
                                    {
                                        break;
                                    }
                                }
                                if(i==((size*size)+1))
                                {
                                    end=pos;
                                    int prev_y_1,prev_x_1,prev_x_2,prev_y_2;
                                    prev_y_1 = (start / (size));
                                    if ((prev_y_1 % 2) == 0) {
                                        prev_x_1 = (start - ((size) * prev_y_1)) - 1;
                                        if (prev_x_1 < 0) {
                                            prev_x_1 = 0;
                                            prev_y_1--;
                                        }
                                    } else {
                                        prev_x_1 = (size) - (start - ((size) * prev_y_1));
                                        if (prev_x_1 >= (size)) {
                                            prev_x_1 = (size-1);
                                            prev_y_1--;
                                        }
                                    }
                                    c1.getGraphics().drawImage(im_ladder_head,50 * prev_x_1, (prev_y_1+1) * 50, io);
                               //     c1.getGraphics().drawImage(im_ladder_head, + (50 * prev_x_1), ob1.start_y + ((prev_y_1+1) * 50), io);
                                    prev_y_2 = (end / (size));
                                    if ((prev_y_2 % 2) == 0) {
                                        prev_x_2 = (end - ((size) * prev_y_2)) - 1;
                                        if (prev_x_2 < 0) {
                                            prev_x_2 = 0;
                                            prev_y_2--;
                                        }
                                    } else {
                                        prev_x_2 = (size) - (end - ((size) * prev_y_2));
                                        if (prev_x_2 >= (size)) {
                                            prev_x_2 = (size-1);
                                            prev_y_2--;
                                        }
                                    }
                                    c1.getGraphics().drawImage(im_ladder_tail,50 * prev_x_2, (prev_y_2+1) * 50, io);
                                    c1.getGraphics().drawLine((prev_x_1*50)+25+1, (prev_y_1+1)*50+25, (prev_x_2*50)+25+1, (prev_y_2+1)*50+25);
                                    c1.getGraphics().drawLine((prev_x_1*50)+25,((prev_y_1+1)*50)+25,(prev_x_2*50)+25,((prev_y_2+1)*50)+25);
                                    c1.getGraphics().drawLine((prev_x_1*50)+25-1, ((prev_y_1+1)*50)+25, (prev_x_2*50)+25-1, ((prev_y_2+1)*50)+25);
                                    ladders[start]=end;
                                    start=0;
                                    end=0;
                                    pos=0;
                                    Message_Box.setText("Ladder Added");
                                    System.out.println("Ladder Added");
                                }
                                else
                                {
                                    start=0;
                                    end=0;
                                    pos=0;
                                    Message_Box.setText("Retry");
                                    System.out.println("Retry    162");
                                }
                            }
                            else
                            {
                                start=0;
                                end=0;
                                pos=0;
                                Message_Box.setText("Retry");
                                System.out.println("Retry     171");
                            }
                        }
                        else
                        {
                            start=0;
                            end=0;
                            pos=0;
                            Message_Box.setText("Retry");
                            System.out.println("Retry     180");
                        }
                    }
                }
            }

            public void mousePressed(MouseEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseReleased(MouseEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseEntered(MouseEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseExited(MouseEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        final MouseListener Event_c1_snake = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                //        throw new UnsupportedOperationException("Not supported yet.");
                pos=0;
                System.out.println("Event_c1_snake starts");
                Point point = e.getPoint();
                if(e.getY()<50)
                {
                    return;
                }
                int start_x = ((point.x)/50)+1;
                int start_y = ((point.y-50)/50);
                System.out.println(e.getY());
                if(start_y%2==0)
                {
                    pos=size*start_y+start_x;
                }
                else
                {
                    pos=(size*(start_y+1))-start_x+1;
                }
                System.out.println(start_x+" adc  "+start_y+"    "+pos);
                if(pos>0 && pos<(size*size))
                {
                    if(start==0 && snakes[pos]==-1 && ladders[pos]==-1)
                    {
                        int i=0;
                        for(i=0;i<((size*size)+1);i++)
                        {
                            if(snakes[i]==pos || ladders[i]==pos)
                            {
                                break;
                            }
                        }
                        if(i==((size*size)+1))
                        {
                            Message_Box.setText("Source Selected, Select the Target");
                            start=pos;
                        }
                        else
                        {
                            start=0;
                            end=0;
                            pos=0;
                            Message_Box.setText("Retry");
                            System.out.println("Retry    243");
                        }
                    }
                    else{
                        if(start!=0)
                        {
                            if(snakes[pos]==-1 && ladders[pos]==-1 && pos<start)
                            {
                                int i=0;
                                for(i=0;i<((size*size)+1);i++)
                                {
                                    if(snakes[i]==pos || ladders[i]==pos)
                                    {
                                        break;
                                    }
                                }
                                if(i==((size*size)+1))
                                {
                                    end=pos;
                                    int prev_y_1,prev_x_1,prev_x_2,prev_y_2;
                                    prev_y_1 = (start / (size));
                                    if ((prev_y_1 % 2) == 0) {
                                        prev_x_1 = (start - ((size) * prev_y_1)) - 1;
                                        if (prev_x_1 < 0) {
                                            prev_x_1 = 0;
                                            prev_y_1--;
                                        }
                                    } else {
                                        prev_x_1 = (size) - (start - ((size) * prev_y_1));
                                        if (prev_x_1 >= (size)) {
                                            prev_x_1 = (size-1);
                                            prev_y_1--;
                                        }
                                    }
                                    c1.getGraphics().drawImage(im_snake_head,(50 * prev_x_1), ((prev_y_1+1) * 50), io);
                                    prev_y_2 = (end / (size));
                                    if ((prev_y_2 % 2) == 0) {
                                        prev_x_2 = (end - ((size) * prev_y_2)) - 1;
                                        if (prev_x_2 < 0) {
                                            prev_x_2 = 0;
                                            prev_y_2--;
                                        }
                                    } else {
                                        prev_x_2 = (size) - (end - ((size) * prev_y_2));
                                        if (prev_x_2 >= (size)) {
                                            prev_x_2 = (size-1);
                                            prev_y_2--;
                                        }
                                    }
                                    c1.getGraphics().drawImage(im_snake_tail, + (50 * prev_x_2), ((prev_y_2+1) * 50), io);
                                    c1.getGraphics().drawLine((prev_x_1*50)+25+1, ((prev_y_1+1)*50)+25, (prev_x_2*50)+25+1, ((prev_y_2+1)*50)+25);
                                    c1.getGraphics().drawLine((prev_x_1*50)+25, ((prev_y_1+1)*50)+25, (prev_x_2*50)+25, ((prev_y_2+1)*50)+25);
                                    c1.getGraphics().drawLine((prev_x_1*50)+25-1, ((prev_y_1+1)*50)+25, (prev_x_2*50)+25-1, ((prev_y_2+1)*50)+25);
                                    snakes[start]=end;
                                    start=0;
                                    end=0;
                                    pos=0;
                                    Message_Box.setText("Snake Added");
                                    System.out.println("Snake Added");
                                }
                                else
                                {
                                    start=0;
                                    end=0;
                                    pos=0;
                                    Message_Box.setText("Retry");
                                    System.out.println("Retry     275");
                                }
                            }
                            else
                            {
                                start=0;
                                end=0;
                                pos=0;
                                Message_Box.setText("Retry");
                                System.out.println("Retry     284");
                            }
                        }
                        else
                        {
                            start=0;
                            end=0;
                            pos=0;
                            Message_Box.setText("Retry");
                            System.out.println("Retry     293");
                        }
                    }
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener Event_bt_snake = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                System.out.println("bt_snake");
                pos=0;
                c1.removeMouseListener(Event_c1_snake);
                c1.removeMouseListener(Event_c1_ladder);
                c1.addMouseListener(Event_c1_snake);
                Message_Box.setText("Ready To Add Snake");
                System.out.println("312  "+pos);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener Event_bt_ladder = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            System.out.println("bt_ladder");
                pos=0;
                c1.removeMouseListener(Event_c1_ladder);
                c1.removeMouseListener(Event_c1_snake);
                c1.addMouseListener(Event_c1_ladder);
                Message_Box.setText("Ready To Add Ladder");
                System.out.println("336  "+pos);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener Event_bt_cancel = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                print_snake(snakes,ladders,size);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };
        MouseListener Event_bt_play = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                try {
                    applet_version3 av = new applet_version3();
                    av.snake_and_ladder(size, player_num, snakes, ladders);
                    f.setVisible(false);
               //     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               //     f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                } catch (Exception ex) {
                    Logger.getLogger(Design_Board2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                Message_Box.setText("Click To Start The Game");
            }

            public void mouseExited(MouseEvent e) {
                Message_Box.setText("");
            }
        };
        bt_snake.addMouseListener(Event_bt_snake);
        bt_ladder.addMouseListener(Event_bt_ladder);
        bt_cancel.addMouseListener(Event_bt_cancel);
        bt_play.addMouseListener(Event_bt_play);
        System.out.println("end");
    }

    public static void print_snake(int[] snakes,int[] ladders, int size)
    {
        System.out.println("Snakes");
        for(int i=0;i<((size*size)+1);i++)
        {
            System.out.println(i+" -> "+snakes[i]);
        }
        System.out.println("Ladders");
        for(int i=0;i<((size*size)+1);i++)
        {
            System.out.println(i+" -> "+ladders[i]);
        }
    }
}
