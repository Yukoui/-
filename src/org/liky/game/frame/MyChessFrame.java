package org.liky.game.frame;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class MyChessFrame extends JFrame implements MouseListener {
    public MyChessFrame(){
        this.setTitle("五子棋");
        this.setSize(700,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        /*System.out.println("宽度为： "+width);
        System.out.println("高度为： "+height);*/
        this.setLocation((width-200)/2,(height-100)/2);

        this.addMouseListener(this);

        this.setVisible(true);
    }

    public void paint(Graphics g){
       /* g.drawString("五子棋游戏",20,40);//绘制字符串
        g.drawOval(20,40, 40, 40);//绘制一个空心的圆形
        g.fillOval(20,40,40,40);//绘制一个实心的圆形
        g.drawLine(20,40,80,40);//绘制一条线
        g.drawRect(20,40,40,20);//绘制一个空心的矩形
        g.fillRect(80,40,40,20);//绘制一个实心的矩形*/
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("E:/file.FJ/picture/back.jpg"));//导入图片
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image,0,0,this);
        g.drawOval(20,40,40,40);
        g.setColor(Color.RED);//设置画笔的颜色
        g.fillRect(80,40,40,20);
        g.setFont(new Font("黑体",40,40));//设置绘制文字的字体
        g.drawString("五子棋游戏",20,100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {//监听鼠标点击事件的操作
        /*System.out.println("鼠标点击");
        JOptionPane.showMessageDialog(this,"鼠标点击");*/

    }

    @Override
    public void mousePressed(MouseEvent e) {//监听鼠标按下的事件
        /*System.out.println("鼠标按下");*/
        /*System.out.println("点击位置：  X --> "+ e.getX());
        System.out.println("点击位置：  Y --> "+ e.getY());*/

    }

    @Override
    public void mouseReleased(MouseEvent e) {//监听鼠标抬起的事件
        /*System.out.println("鼠标抬起");*/
    }

    @Override
    public void mouseEntered(MouseEvent e) {//监听鼠标进入事件的操作
       /* System.out.println("鼠标进入");
        JOptionPane.showMessageDialog(this,"鼠标进入");*/
    }

    @Override
    public void mouseExited(MouseEvent e) {//监听鼠标离开事件的操作
        /*System.out.println("鼠标离开");
        JOptionPane.showMessageDialog(this,"鼠标离开");*/
    }
}
