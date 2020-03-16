package org.liky.game.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.*;

public class FiveChessFrame extends JFrame implements MouseListener,Runnable {
    //取得屏幕的宽度
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    //取得屏幕的高度
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    //背景图片
    BufferedImage bgImage = null;
    //保存棋子的坐标
    int x = 0;
    int y = 0;
    //保存之前下过的全部棋子的坐标
    // 其中数据内容 0:表示这个点并没有棋子  1；表示这个点是黑子  2：表示这个点是白子
    int[][] allChess = new int [19][19];
    //标识当前是黑棋还是白棋来下下一步棋
    boolean isBlack = true ;
    //标识当前游戏是否可以继续
    boolean canPlay = true ;
    //保存显示的提示信息
    String message = "黑方先行";
    //保存最多拥有多少时间（秒）
    int maxTime = 0;
    //做倒计时的线程类
    Thread t = new Thread(this);
    //保存黑方与白方的剩余时间
    int blackTime = 0;
    int whiteTime = 0;
    //保存双方剩余时间的显示信息
    String blackMessage = "无限制";
    String whiteMessage = "无限制";



    public FiveChessFrame() throws IOException {
        //设置标题
        this.setTitle("五子棋");
        //设置窗体大小
        this.setSize(500,500);
        //设置窗体出现位置
        this.setLocation((width - 500)/2,(height - 500)/2);
        //将窗体设置为大小不可改变
        this.setResizable(false);
        //将窗体的关闭方式设为默认关闭后程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //为窗体加入监听器
        this.addMouseListener(this);
        //将窗体显示出来
        this.setVisible(true);

        t.start();
        t.suspend();

        //刷新屏幕，防止游戏开始时出现无法显示的情况
        this.repaint();

        try {
            bgImage = ImageIO.read(new File("E:/file.FJ/picture/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

       public void paint(Graphics g){
        //双缓冲技术防止屏幕闪烁
           BufferedImage bi = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
           Graphics g2 = bi.createGraphics();



        //绘制背景
        g2.drawImage(bgImage,0,20,this);
        //输出标题信息
        g2.setColor(Color.BLACK);//设置画笔的颜色
        g2.setFont(new Font("黑体",Font.BOLD,20));
        g2.drawString("游戏信息："+ message,100,60);
        //输出时间信息
        g2.setFont(new Font("宋体",0,12));
        g2.drawString("黑方时间："+blackMessage,45,465);
        g2.drawString("白方时间："+whiteMessage,250,465);


        //绘制棋盘
           for (int i=0;i < 19;i++){
                g2.drawLine(15,62 + 20 * i,375,62 + 20 * i);
                g2.drawLine(15 + 20 * i,62,15 + 20 * i,422);
           }
              //标注点位
           g2.fillOval(73,120,4,4);
           g2.fillOval(73,360,4,4);
           g2.fillOval(313,120,4,4);
           g2.fillOval(313,360,4,4);
           g2.fillOval(193,120,4,4);
           g2.fillOval(193,360,4,4);
           g2.fillOval(73,240,4,4);
           g2.fillOval(313,240,4,4);
           g2.fillOval(193,240,4,4);

           //绘制棋子
           /*x = (x - 15) / 20 * 20 + 15;*/
           /*y = (y - 62) / 20 * 20 + 62;*/
           /*//黑子*/
           /*g.fillOval(x - 7,y - 7,14,14);*/
           /*//白子*/
           /*g.setColor(Color.WHITE);*/
           /*g.fillOval(x - 7, y - 7, 14,14);*/
           /*g.setColor(Color.BLACK);*/
           /*g.drawOval(x-7,y-7,14,14);*/

           for (int i = 0; i < 19; i++){
               for(int j = 0; j < 19; j++){
                   if (allChess[i][j] == 1){
                       //下黑子
                       int tempX = i * 20 + 15;
                       int tempY = j * 20 + 62;
                       g2.fillOval(tempX - 7,tempY - 7,14,14);
                   }
                   if (allChess[i][j] == 2) {
                       //下白子
                       int tempX = i * 20 + 15;
                       int tempY = j * 20 + 62;
                       g2.setColor(Color.WHITE);
                       g2.fillOval(tempX - 7, tempY - 7, 14,14);
                       g2.setColor(Color.BLACK);
                       g2.drawOval(tempX - 7,tempY - 7,14,14);


                   }
               }
           }
        g.drawImage(bi,0,0,this);

       }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
            /*System.out.println("X:"+e.getX());*/
            /*System.out.println("Y:"+e.getY());    打印点击处的坐标*/
            if (canPlay == true) {
                x = e.getX();
                y = e.getY();
                if (x >= 15 && x <= 375 && y >= 62 && y <= 422) {
                    //保存棋子位置
                    x = (x - 15) / 20;
                    y = (y - 62) / 20;
                    if (allChess[x][y] == 0) {
                        //判断当前要下的是什么颜色的棋子
                        if (isBlack == true) {
                            allChess[x][y] = 1;
                            isBlack = false;
                            message = "轮到白方";
                        } else {
                            allChess[x][y] = 2;
                            isBlack = true;
                            message = "轮到黑方";
                        }
                        //判断这个棋子是否和其他的棋子连成5点，即判断游戏是否结束
                        boolean winFlag = this.checkWIN();
                        if (winFlag == true) {

                            JOptionPane.showMessageDialog(this, "游戏结束,"
                                    + (allChess[x][y] == 1 ? "黑方" : "白方") + "获胜！");
                            canPlay = false; //一方获胜后游戏结束
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "当前位置已经有棋子，请重新落子！ ");
                    }
                    this.repaint();       //表示重新执行一次paint()的方法
                }
            }
            //System.out.println((e.getX() + "--" + e.getY())); 输出鼠标点击位置的坐标
            // 点击 开始游戏 按钮
            if (e.getX() >= 385 && e.getX() <= 485 && e.getY() >= 60 && e.getY() <= 100){
                int result = JOptionPane.showConfirmDialog(this,"是否重新开始游戏？");
                if(result == 0){
                    //现在重新开始游戏
                    //重新开始所要做的操作：1）把棋盘清空，allChess这个数组中全部数据归零
                    //2}将游戏信息： 的显示改回到开始位置
                    //3)将下一步下棋的改为黑方
                    for(int i = 0; i < 19; i++){
                        for(int j = 0; j < 19; j++){
                            allChess[i][j] = 0;
                        }
                    }
                    //另一种方式 allChess = new int [19][19];
                    message = "黑方先行";
                    isBlack = true;
                    blackTime = maxTime;
                    whiteTime = maxTime;
                    if (maxTime > 0) {
                        blackMessage = maxTime / 3600 + ":"
                                + ( maxTime / 60 - maxTime / 3600 * 60) + ":"
                                + ( maxTime - maxTime / 60 * 60);
                        whiteMessage = maxTime / 3600 + ":"
                                + ( maxTime / 60 - maxTime / 3600 * 60) + ":"
                                + ( maxTime - maxTime / 60 * 60);
                        t.resume();
                    } else {
                        blackMessage = "无限制";
                        whiteMessage = "无限制";
                    }
                    this.repaint();//调用paint重新绘制棋盘 棋盘清空
                }
            }
            //点击 游戏设置 按钮
            if (e.getX() >= 385 && e.getX() <= 485 && e.getY() >= 120 && e.getY() <= 160){
                String input = JOptionPane.showInputDialog("请输入游戏的最大时间(单位：分钟)，如果输入0，表示没有时间限制：");
                try {
                    maxTime = Integer.parseInt(input) * 60;
                    if(maxTime < 0){
                        JOptionPane.showMessageDialog(this,"请输入正确信息，不允许输入负数！");
                    }
                    if(maxTime == 0){
                       int result = JOptionPane.showConfirmDialog(this,"设置完成,是否重新开始游戏？");
                        if(result == 0){
                            //现在重新开始游戏
                            //重新开始所要做的操作：1）把棋盘清空，allChess这个数组中全部数据归零2}将游戏信息： 的显示改回到开始位置3)将下一步下棋的改为黑方
                            for(int i = 0; i < 19; i++){
                                for(int j = 0; j < 19; j++){
                                    allChess[i][j] = 0;
                                }
                            }
                            //另一种方式 allChess = new int [19][19];
                            message = "黑方先行";
                            isBlack = true;
                            blackTime = maxTime;
                            whiteTime = maxTime;
                            blackMessage = "无限制";
                            whiteMessage = "无限制";
                            this.repaint();//调用paint重新绘制棋盘 棋盘清空
                        }
                    }
                    if(maxTime > 0){
                        int result = JOptionPane.showConfirmDialog(this,"设置完成,是否重新开始游戏？");
                        if(result == 0){
                            //现在重新开始游戏
                            //重新开始所要做的操作：1）把棋盘清空，allChess这个数组中全部数据归零2}将游戏信息： 的显示改回到开始位置3)将下一步下棋的改为黑方
                            for(int i = 0; i < 19; i++){
                                for(int j = 0; j < 19; j++){
                                    allChess[i][j] = 0;
                                }
                            }
                            //另一种方式 allChess = new int [19][19];
                            message = "黑方先行";
                            isBlack = true;
                            blackTime = maxTime;
                            whiteTime = maxTime;
                            blackMessage = maxTime / 3600 + ":"
                                    + ( maxTime / 60 - maxTime / 3600 * 60) + ":"
                                    + ( maxTime - maxTime / 60 * 60);
                            whiteMessage = maxTime / 3600 + ":"
                                    + ( maxTime / 60 - maxTime / 3600 * 60) + ":"
                                    + ( maxTime - maxTime / 60 * 60);
                            t.resume();
                            this.repaint();//调用paint重新绘制棋盘 棋盘清空
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,"请正确输入信息！");
                }
            }
            //点击 游戏说明 按钮
            if (e.getX() >= 385 && e.getX() <= 485 && e.getY() >= 180 && e.getY() <= 220){
                JOptionPane.showMessageDialog(this, "这是一个五子棋游戏程序，黑白双方轮流下棋，当某一方连到五子棋的时候，游戏结束。");
            }
            //点击 认输 按钮
           if (e.getX() >= 385 && e.getX() <= 485 && e.getY() >= 275 && e.getY() <= 315){
               int result = JOptionPane.showConfirmDialog(this,"是否确认认输？");
               if (result == 0){
                   if(isBlack){
                       JOptionPane.showMessageDialog(this,"黑方已经认输，游戏结束！");
                   }else{
                       JOptionPane.showMessageDialog(this,"白方已经认输，游戏结束！");
                   }
                   canPlay = false;
               }
           }
            //点击 关于 按钮
           if (e.getX() >= 385 && e.getX() <= 485 && e.getY() >= 330 && e.getY() <= 370){
              JOptionPane.showMessageDialog(this, "本游戏由樊鲸鱼制作");
           }
           //点击 退出 按钮
           if (e.getX() >= 385 && e.getX() <= 485 && e.getY() >= 385 && e.getY() <= 425){
              JOptionPane.showMessageDialog(this, "游戏结束");
              System.exit(0);
           }

    }

    private boolean checkWIN(){       //每下一颗棋子就对它进行横向判断是否已连成五点
        boolean flag = false;  //false代表未结束
        //保存共有相同颜色多少棋子相连
        int count = 1;
        //判断横向是否有五个棋子相连，特点 纵坐标是相同的 即allChess[x][y]中y值是相同的
        int color = allChess[x][y];
        /*if (color == allChess[x+1][y]) {*/
        /*    count++;*/
        /*    if (color == allChess[x+2][y]){*/
        /*        count++;*/
        /*        if (color == allChess[x+3][y]){*/
        /*            ....*/
        /*        }*/
        /*    }*/
        /*}         这样判断不够优化  */
        //通过循环来做棋子相连的判断
        //横向的判断
        /*int i = 1;
        while(color == allChess[x+i][y]){
            count++;
            i++;
        }     //此处color的值已与进入方法时初始的color的值不一样了
        i = 1;
        while(color == allChess[x-i][y]){
            count++;
            i++;
        }
        if (count >=5){
            flag = true;
        }
        //纵向的判断
        int i2 = 1;
        int count2 = 1;
        while(color == allChess[x][y+i2]){
            count2 ++;
            i2 ++;
        }     //此处color的值已与进入方法时初始的color的值不一样了
        i2 = 1;
        while(color == allChess[x][y-i2]){
            count2 ++;
            i2 ++;
        }
        if (count2 >=5) {
            flag = true;
        }
        //斜方向的判断（右上+左下）
        int i3 = 1;
        int count3 = 1;
        while(color == allChess[x+i3][y-i3]){
            count3 ++;
            i3 ++;
        }     //此处color的值已与进入方法时初始的color的值不一样了
        i3 = 1;
        while(color == allChess[x-i3][y+i3]){
            count3 ++;
            i3 ++;
        }
        if (count3 >=5) {
            flag = true;
        }
        //斜方向的判断（右下+左上）
        int i4 = 1;
        int count4 = 1;
        while(color == allChess[x+i4][y+i4]){
            count4 ++;
            i4 ++;
        }     //此处color的值已与进入方法时初始的color的值不一样了
        i4 = 1;
        while(color == allChess[x-i4][y-i4]){
            count4 ++;
            i4 ++;
        }
        if (count4 >=5) {
            flag = true;
        }*/
        //判断横向
        count = this.checkCount(1,0,color);
        if(count >= 5){
            flag = true;
        } else {
            //判断纵向
            count = this.checkCount(0,1,color);
            if (count >= 5){
                flag = true;
            }else{
                //判断右上 左下
                count = this.checkCount(1,-1,color);
                if (count >= 5){
                    flag = true;
                }else {
                    //判断右下 左上
                    count = this.checkCount(1,1,color);
                    if (count >= 5){
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }
    //判断棋子连接的数量
    private int checkCount(int xChange,int yChange,int color){
        int count = 1;
        int tempX = xChange;
        int tempY = yChange;
        while(x + xChange >= 0 && x + xChange <= 18
                && y + yChange >= 0 && y + yChange <= 18
                && color == allChess[x + xChange][y + yChange]){
            count ++;
            if (xChange != 0 )
                xChange++;
            if (yChange != 0) {
                if (yChange > 0)
                    yChange++;
                else{
                    yChange--;
                }
            }
        }
        xChange = tempX;
        yChange = tempY;
        while(x - xChange >= 0 && x - xChange <= 18
                && y - yChange >= 0 && y - yChange <= 18
                && color == allChess[x - xChange][y - yChange]){
            count ++;
            if (xChange != 0 )
                xChange++;
            if (yChange != 0) {
                if (yChange > 0)
                    yChange++;
                else{
                    yChange--;
                }
            }
        }
        return count;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void run() {
        //判断是否有时间限制
        if(maxTime > 0){
            while (true){
                if (isBlack){
                    blackTime--;
                    if (blackTime == 0){
                        JOptionPane.showMessageDialog(this,"黑方超时，游戏结束！");
                    }
                }else{
                    whiteTime--;
                    if (whiteTime == 0){
                        JOptionPane.showMessageDialog(this,"白方超时，游戏结束！");
                    }
                }
                blackMessage = blackTime / 3600 + ":"
                        + ( blackTime / 60 - blackTime / 3600 * 60) + ":"
                        + ( blackTime - blackTime / 60 * 60);
                whiteMessage = whiteTime / 3600 + ":"
                        + ( whiteTime / 60 - whiteTime / 3600 * 60) + ":"
                        + ( whiteTime - whiteTime / 60 * 60);
                this.repaint();
                try {
                    Thread.sleep(1000);//睡眠1000ms 也就是休息1s 等于在1s 1s的倒数
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
