package org.liky.game.test;

import org.liky.game.frame.FiveChessFrame;
import org.liky.game.frame.MyChessFrame;

import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.io.IOException;

public class FiveChess {
    public static void main(String[] args) throws IOException {

       /*MyChessFrame mf = new MyChessFrame();*/
       /*JOptionPane.showMessageDialog(mf,"我的信息"); //显示一个消息对话框，主要用来提示信息
       int result = JOptionPane.showConfirmDialog(mf,"我的确认信息，现在要开始游戏吗？");//显示一个确认对话框，用户选择后，根据返回的结果进行判断
      //System.out.println("选择的结果为：  " + result);
       if (result == 0){
           JOptionPane.showMessageDialog(mf,"游戏开始");
       }
       if (result == 1){
           JOptionPane.showMessageDialog(mf,"游戏结束");
       }
       if (result == 2){
           JOptionPane.showMessageDialog(mf,"请重新选择");
       }*/
       /*String username = JOptionPane.showInputDialog("请输入您的姓名： ");//显示一个输入对话框，作用是用来保存用户输入的信息
       if (username != null) {
           System.out.println(username);
           JOptionPane.showMessageDialog(mf, "输入的姓名为： " + username);
       } else {
           JOptionPane.showMessageDialog(mf,"请重新输入用户名！");
       }*/
        FiveChessFrame ff = new FiveChessFrame();
    }
}
