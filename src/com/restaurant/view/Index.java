package com.restaurant.view;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class Index extends JWindow implements Runnable {
    Thread t;
    paint p;

    public static void main(String[] args) {
        Index index = new Index();
        Thread t = new Thread(index);
        t.start();
    }


    public Index() {
        //创建p
        p = new paint();
        this.add(p);
        this.setSize(400, 250);
        /**
         * 确定JWindow的初始位置
         */
//        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
//        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
//        this.setLocation(width/2-200,height/2-150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            //等待闪屏效果结束，再跳转用户登录界面
            try {
                Thread.sleep(5 * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.dispose();
            //跳转到登录页面
            System.out.println("闪屏效果结束");
            //跳转登录界面
            new UserLogin();
            break;
        }
    }
}

