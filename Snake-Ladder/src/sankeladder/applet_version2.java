package sankeladder;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class applet_version2
{
    static int i=0;
    BufferedImage im_snake,im_ladder;
    Canvas1 c1;
    final JFrame f = new JFrame();
    public void snake_and_ladder(final int size, final int player_num, final int[] snakes, final int[] ladders) throws IOException
 //   public static void main(String argsp[]) throws IOException
    {
        final int[] score = new int[player_num];
        final FileInputStream fstream;
        fstream = new FileInputStream("Player Info.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        final String []name_arr = new String[player_num];
        br.readLine();
        for(int p=0;p<player_num;p++)
        {
            name_arr[p]=br.readLine();
            score[p]=0;
        }
        im_snake = ImageIO.read(new File("box_snake.png"));
        im_ladder = ImageIO.read(new File("box_ladder.png"));
        i=0;
        System.out.println("i  "+i);
        final Objects ob1 = new Objects();
        final Objects ob2 = new Objects();
        final Objects ob3 = new Objects();
        final Objects[] ob_arr = new Objects[player_num];
        ob_arr[0]=ob1;
        if(player_num>1)
            ob_arr[1]=ob2;
        if(player_num>2)
            ob_arr[2]=ob3;
        System.out.println(ob1.toString()+"   "+ob2+"   "+ob3+"  "+ob_arr.length);
        ob2.im1 = ImageIO.read(new File("blue_coin.png"));
        ob3.im1 = ImageIO.read(new File("yellow_coin.png"));
        f.getContentPane().setLayout(new java.awt.FlowLayout());
        f.setSize(1200,700);
        f.setResizable(false);
        f.setVisible(true);
       // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel j1 = new JPanel();
        j1.setLayout(new java.awt.FlowLayout());
        j1.setBounds(100, 100, 750, 660);
        f.add(j1);
        c1 = new Canvas1();
        c1.size=size;
        j1.add(c1);
        //while(c1.canvasCompleted == 0){};
        System.out.println("Going to add snake and ladder!!!");
        
        snake_ladder_image(c1, snakes, ladders, size);
        JButton bt = new JButton();
        bt.setFont(new java.awt.Font("Snap ITC", 3, 18));
        final JTextField Message_Box = new JTextField();
        Message_Box.setBackground(Color.orange);
        Message_Box.setEditable(false);
        Message_Box.setText("Message...................................");
        Message_Box.setFont(new java.awt.Font("Snap ITC", 3, 12));
        f.add(bt);
        f.add(Message_Box);
        final int []snake_ladder = new int[((size*size)+1)];
        for(int a=0;a<((size*size)+1);a++)
        {
            snake_ladder[a]=-1;
        }
        bt.setText("ROLL");
        bt.setVisible(true);
        bt.setBackground(Color.BLUE);
        //bt.setForeground(Color.GREEN);
        System.out.println("hsdcbs");
        MouseListener Event = new MouseListener() {
        
        public void mouseClicked(MouseEvent e)
        {
                FileWriter fstream2=null;
                try {
                    int k;
                    snake_ladder_image(c1, snakes, ladders, size);
                    System.out.println("Length  " + ob_arr.length);
                    i = i % ob_arr.length;
                    System.out.println("Player  " + i);
                    ImageObserver io = null;
                    int j = 10;
                    if (ob_arr[i].pos != 0) {
                        for (j = 0; j < ob_arr.length; j++) {
                            if (j == i) {
                            } else {
                                if (ob_arr[j].pos == ob_arr[i].pos) {
                                    break;
                                    //
                                }
                            }
                        }
                    }
                    k=i;
                    i = fun(c1, size, ob_arr[i], snakes, ladders,Message_Box,score);
                    if(i==-1)
                    {
                        Message_Box.setText("Congratulations "+name_arr[k]);
                        //f.setVisible(false);
                        FileInputStream fstream1 = null;
                        try{
                        fstream1 = new FileInputStream("Highest Score.txt");
                        }catch(IOException e1){
                            FileWriter fr = new FileWriter("Highest Score.txt");
                            BufferedWriter out1 = new BufferedWriter(fr);
                            out1.close();
                            fstream1 = new FileInputStream("Highest Score.txt");
                        }
                        DataInputStream in1 = new DataInputStream(fstream1);
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
                        String str = br1.readLine();
                        String text1,text2,text3;
                        String[] split = new String[2];
                        if(str==null)
                        {
                            text1 = "CONGRATULATIONS "+name_arr[k]+" Your Score "+score[k];
                            text2 = "Previous Highest Score 0";
                            text3 = "Your Have Made A New High Score";
                            split[0] = "0";
                        }
                        else
                        {
                            split = str.split(" ", 2);
                            text1 = "CONGRATULATIONS "+name_arr[k]+" Your Score "+score[k];
                            text2 = "Previous Highest Score " + split[0] + " by "+split[1];
                            text3 = null;

                        }
                        if(Integer.parseInt(split[0])>=score[k] || Integer.parseInt(split[0])==0)
                        {
                            text3 = "Your Have Made A New High Score";
                            fstream2 = new FileWriter("Highest Score.txt");
                            BufferedWriter out = new BufferedWriter(fstream2);
                            out.write(score[k]+" "+name_arr[k]);
                            out.close();
                        }
                        else
                        {
                            text3 = "Please Try Again For A Better Score";
                        }
                        fstream2 = new FileWriter("End Of Game.txt");
                        BufferedWriter out = new BufferedWriter(fstream2);
                        out.write(text1);
                        out.newLine();
                        out.write(text2);
                        out.newLine();
                        out.write(text3);
                        out.newLine();
                        out.close();
                        End_Of_Game.end_of_game();
                        System.out.println(score[k]+" score & name  "+name_arr[k]);
                        return;

                    }
                    if (j < ob_arr.length) {
                        c1.getGraphics().drawImage(ob_arr[j].im1,(50 * ob_arr[j].start_red_x),(ob_arr[j].start_red_y * 50), io);
                    }
                    
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(applet_version2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }



        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
                try {
                    Message_Box.setText(name_arr[i%player_num]+"'s Turn");
                    if(c1.snake_image==0)
                    {
                  //      snake_ladder_image(c1, snakes, ladders, size);
                        c1.snake_image++;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(applet_version2.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        public void mouseExited(MouseEvent e) {
        }
    };
        bt.addMouseListener(Event);
        System.out.println("End of functioncall to applet_version2!!!");
        
    }

    public int fun(Canvas1 c1,int size,Objects ob1,int[] snakes, int[] ladders, JTextField Message_Box,int[] score)
    {
        Random randomGenerator = new Random();
        String str;
        int randomInt;
        int prev_x = ob1.start_red_x;
        int prev_y = ob1.start_red_y;
        int pos = ob1.pos;
        ImageObserver io = null;
        prev_x = ob1.start_red_x;
        prev_y = ob1.start_red_y;
        pos = ob1.pos;
        if (pos != 0 && pos<((size*size)+1)) {
            str = Integer.toString(pos);
            c1.getGraphics().drawImage(c1.im1, (50 * ob1.start_red_x), (50 * ob1.start_red_y), io);
            c1.getGraphics().setFont(new java.awt.Font("ToledoLH", 1, 18));
            c1.getGraphics().drawString(str, (50 * ob1.start_red_x) + 12, (50 * ob1.start_red_y) + 25);
        }
        randomInt = randomGenerator.nextInt(6) + 1;
        if(randomInt==6)
        {
            Message_Box.setText("You Got "+randomInt+"Roll Again");
        }
        else
        {
            Message_Box.setText("You Got " + randomInt);
        }
        System.out.println("RandomInt" + randomInt);
        if(pos+randomInt < ((size*size)+1))
        {
            pos += randomInt;
            prev_y = (pos / (size));
            if ((prev_y % 2) == 0) {
                prev_x = (pos - ((size) * prev_y)) - 1;
                if (prev_x < 0) {
                    prev_x = 0;
                    prev_y--;
                }
            } else {
                prev_x = (size) - (pos - ((size) * prev_y));
                if (prev_x >= (size)) {
                    prev_x = (size-1);
                    prev_y--;
                }
            }
            ob1.start_red_x = prev_x;
            ob1.start_red_y = prev_y + 1;
            ob1.pos = pos;
            c1.getGraphics().setColor(Color.red);
            c1.getGraphics().drawImage(ob1.im1, (50 * ob1.start_red_x), (ob1.start_red_y * 50), io);
            if(randomInt!=6 && snakes[pos]!=-1)
            {
                Message_Box.setText("You Got "+randomInt+" Snake at "+pos+"Got You ooo");
                prev_x = ob1.start_red_x;
                prev_y = ob1.start_red_y;
                pos = ob1.pos;

                if (pos != 0) {
                    str = Integer.toString(pos);
                    c1.getGraphics().drawImage(im_snake, (50 * ob1.start_red_x), (50 * ob1.start_red_y), io);
                //    c1.getGraphics().setFont(new java.awt.Font("ToledoLH", 1, 18));
                  //  c1.getGraphics().drawString(str,  + (50 * ob1.start_red_x) + 12,  + (50 * ob1.start_red_y) + 25);
                }
                pos=snakes[pos];
                prev_y = (pos / (size));
                if ((prev_y % 2) == 0)
                {
                    prev_x = (pos - ((size) * prev_y)) - 1;
                    if (prev_x < 0)
                    {
                        prev_x = 0;
                        prev_y--;
                    }
                }
                else
                {
                    prev_x = (size) - (pos - ((size) * prev_y));
                    if (prev_x >= (size)) {
                        prev_x = (size-1);
                        prev_y--;
                    }
                }
                ob1.start_red_x = prev_x;
                ob1.start_red_y = prev_y + 1;
                ob1.pos = pos;
                c1.getGraphics().drawImage(im_snake,(50 * ob1.start_red_x), (ob1.start_red_y * 50), io);
             //   c1.getGraphics().setColor(Color.red);
                //c1.getGraphics().fillOval( + 12 + (50 * ob1.start_red_x),  + 13 + (ob1.start_red_y * 50), 25, 25);
            }
            if(randomInt!=6 && ladders[pos]!=-1)
            {
                Message_Box.setText("You Got "+randomInt+" Ladder at "+pos+"Got You!!!");
                prev_x = ob1.start_red_x;
                prev_y = ob1.start_red_y;
                pos = ob1.pos;

                if (pos != 0) {
                    str = Integer.toString(pos);
                    c1.getGraphics().drawImage(im_ladder, (50 * ob1.start_red_x), (50 * ob1.start_red_y), io);
              //      c1.getGraphics().setFont(new java.awt.Font("ToledoLH", 1, 18));
                //    c1.getGraphics().drawString(str,  + (50 * ob1.start_red_x) + 12,  + (50 * ob1.start_red_y) + 25);
                }
                pos=ladders[pos];
                prev_y = (pos / (size));
                if ((prev_y % 2) == 0)
                {
                    prev_x = (pos - ((size) * prev_y)) - 1;
                    if (prev_x < 0)
                    {
                        prev_x = 0;
                        prev_y--;
                    }
                }
                else
                {
                    prev_x = (size) - (pos - ((size) * prev_y));
                    if (prev_x >= (size)) {
                        prev_x = (size-1);
                        prev_y--;
                    }
                }
                ob1.start_red_x = prev_x;
                ob1.start_red_y = prev_y + 1;
                ob1.pos = pos;
                c1.getGraphics().drawImage(im_ladder,(50 * ob1.start_red_x), (ob1.start_red_y * 50), io);
              //  c1.getGraphics().setColor(Color.red);
                //c1.getGraphics().fillOval( + 12 + (50 * ob1.start_red_x),  + 13 + (ob1.start_red_y * 50), 25, 25);
            }
        }
        else
            if(ob1.carry > 0)
            {
                Message_Box.setText("Out Of Board, Turn Over, Returning To Previous Position");
                pos-=ob1.carry;
                prev_y = (pos / (size));
                if ((prev_y % 2) == 0)
                {
                    prev_x = (pos - ((size) * prev_y)) - 1;
                    if (prev_x < 0)
                    {
                        prev_x = 0;
                        prev_y--;
                    }
                }
                else
                {
                    prev_x = (size) - (pos - ((size) * prev_y));
                    if (prev_x >= (size)) {
                        prev_x = (size-1);
                        prev_y--;
                    }
                }
                ob1.start_red_x = prev_x;
                ob1.start_red_y = prev_y + 1;
                ob1.pos = pos;
                ob1.carry=0;
            }
            else
            {
                c1.getGraphics().drawImage(ob1.im1, (50 * ob1.start_red_x), (ob1.start_red_y * 50), io);
                Message_Box.setText("You Got "+randomInt+" Out Of Board, Turn Over");
                score[i]+=1;
                i++;
                return i;
            }
        c1.getGraphics().drawImage(ob1.im1,(50 * ob1.start_red_x), (ob1.start_red_y * 50), io);
        System.out.println("POS" + pos);
        if(pos==(size*size))
        {
            score[i]+=1;
            return -1;
        }
        if(randomInt == 6)
        {
            ob1.carry+=6;
            return i;
        }
        score[i]+=1;
        ob1.carry=0;
        i++;
        return i;
    }
    public void snake_ladder_image(Canvas1 c1, int[] snakes, int[] ladders, int size) throws IOException
    {
        System.out.println("snake_ladder_image");
        Objects ob1 = new Objects();
        int prev_x_1,prev_y_1,prev_x_2,prev_y_2;
        ImageObserver io = null;
        for(int i=1;i<snakes.length;i++)
        {
            if(snakes[i]!=-1)
            {
                prev_y_1 = (i / (size));
                if ((prev_y_1 % 2) == 0) {
                    prev_x_1 = (i - ((size) * prev_y_1)) - 1;
                    if (prev_x_1 < 0) {
                        System.out.println("y--");
                        prev_x_1 = 0;
                        prev_y_1--;
                    }
                } else {
                    prev_x_1 = (size) - (i - ((size) * prev_y_1));
                    if (prev_x_1 >= (size)) {
                        System.out.println("y--");
                        prev_x_1 = (size-1);
                        prev_y_1--;
                    }
                }
                System.out.println("snake added "+i);
                c1.getGraphics().drawImage(im_snake,(50 * prev_x_1), ((prev_y_1+1) * 50), io);
                prev_y_2 = (snakes[i] / (size));
                if ((prev_y_2 % 2) == 0) {
                    prev_x_2 = (snakes[i] - ((size) * prev_y_2)) - 1;
                    if (prev_x_2 < 0) {
                        System.out.println("y--");
                        prev_x_2 = 0;
                        prev_y_2--;
                    }
                } else {
                    prev_x_2 = (size) - (snakes[i] - ((size) * prev_y_2));
                    if (prev_x_2 >= (size)) {
                        System.out.println("y--");
                        prev_x_2 = (size-1);
                        prev_y_2--;
                    }
                }
                c1.getGraphics().drawLine((prev_x_1*50)+25+1,((prev_y_1+1)*50)+25,(prev_x_2*50)+25+1,((prev_y_2+1)*50)+25);
                c1.getGraphics().drawLine((prev_x_1*50)+25,((prev_y_1+1)*50)+25,(prev_x_2*50)+25,((prev_y_2+1)*50)+25);
                c1.getGraphics().drawLine((prev_x_1*50)+25-1,((prev_y_1+1)*50)+25,(prev_x_2*50)+25-1,((prev_y_2+1)*50)+25);
            }
        }
        for(int i=1;i<ladders.length;i++)
        {
            if(ladders[i]!=-1)
            {
                prev_y_1 = (i / (size));
                if ((prev_y_1 % 2) == 0) {
                    prev_x_1 = (i - ((size) * prev_y_1)) - 1;
                    if (prev_x_1 < 0) {
                        System.out.println("y--");
                        prev_x_1 = 0;
                        prev_y_1--;
                    }
                } else {
                    prev_x_1 = (size) - (i - ((size) * prev_y_1));
                    if (prev_x_1 >= (size)) {
                        System.out.println("y--");
                        prev_x_1 = (size-1);
                        prev_y_1--;
                    }
                }
                System.out.println("ladder added "+i);
                c1.getGraphics().drawImage(im_ladder,(50 * prev_x_1),  + ((prev_y_1+1) * 50), io);
                prev_y_2 = (ladders[i] / (size));
                if ((prev_y_2 % 2) == 0) {
                    prev_x_2 = (ladders[i] - ((size) * prev_y_2)) - 1;
                    if (prev_x_2 < 0) {
                        System.out.println("y--");
                        prev_x_2 = 0;
                        prev_y_2--;
                    }
                } else {
                    prev_x_2 = (size) - (ladders[i] - ((size) * prev_y_2));
                    if (prev_x_2 >= (size)) {
                        System.out.println("y--");
                        prev_x_2 = (size-1);
                        prev_y_2--;
                    }
                }
                c1.getGraphics().drawLine((prev_x_1*50)+25+1,((prev_y_1+1)*50)+25,(prev_x_2*50)+25+1,((prev_y_2+1)*50)+25);
                c1.getGraphics().drawLine((prev_x_1*50)+25,((prev_y_1+1)*50)+25,(prev_x_2*50)+25,((prev_y_2+1)*50)+25);
                c1.getGraphics().drawLine((prev_x_1*50)+25-1,((prev_y_1+1)*50)+25,(prev_x_2*50)+25-1,((prev_y_2+1)*50)+25);
            }
        }
    }
}

