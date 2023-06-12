package com.restaurant.view;

import javax.swing.*;
import java.awt.*;
//闪屏类
public class paint extends JPanel implements Runnable {//可以自动刷新Panel

    paint() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            if (x <= 380) ;
            repaint();
            try {
                Thread.sleep(70);
                i++;
                j = j - 6;
                u = u + 10;
                if (tt == 3) {
                    width = width - 20;
                }
                if (i == 4) {
                    tt++;
                    if (ifok && x > 120 + k * 20) {
                        k++;
                    }
                    if (k >= gg.length - 1) {
                        ifok = false;
                        x = x + 10;
                        i = 0;
                        j = 40;
                        u = 10;
                        dian++;
                        if (dian > 3) {
                            dian = 0;
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("线程中断");
            }
        }
    }
    int x=10;
    int i=0,j=40,u=10;
    String gg[]={"系","统","正","在","加","载","请","稍","后"};

    int k=0,tt=0;
    String shi[]={"餐","饮","管","理","系","统"};
    Font f=new Font("隶书", Font.PLAIN,18);
    boolean ifok=true;
    int width =180;
    int height=0;
    int dian=0;

    public void paintComponent(Graphics g){
        Image image;
        image=Toolkit.getDefaultToolkit().getImage("image/index.jpg");//背景图片
        g.drawImage(image,0,0,this.getWidth(),200,this);
        int r=(int)(Math.random()*255);
        int b=(int)(Math.random()*255);
        int y=(int)(Math.random()*255);
        g.setColor(new Color(253,250,250));//白色
        g.fillRect(x,210,380-x,30);
        g.setColor(new Color(253,250,250));//白色
        if(i>1){
            g.fillRect(x,235-(j+20)/2,10,j+10);
            if(j>25){
                g.setColor(new Color(r,b,y));

            }else{
                g.setColor(new Color(123,194,252));
            }
            g.fillRect(x,235-(j+20)/2,10,j);//后面跟着的短方块，10代表闪动条的宽度，j代表闪动条的高度
            g.setColor(new Color(123,194,252));
            g.drawRect(10,210,380,30);
            if(x<120){
                for (int l = 0; l < gg.length; l++) {
                    g.setColor(new Color(0,0,0));
                    g.drawString(gg[l],120+l*20,230);
                }
                for (int l = 0; l < dian; l++) {
                    g.setColor(new Color(0,0,0));
                    g.drawString("*",300+l*10,235);
                }
                g.drawString("*",300+dian*10,235);
            }else{
                //设置写字的颜色和位置
                g.setColor(new Color(23,23,230));
                g.drawString(gg[k],120+k*20,230);
                for (int l = k+1; l < gg.length; l++) {
                    g.setColor(new Color(0,0,0));
                    g.drawString(gg[l],120+l*20,230);
                }
                if(x>300+dian*10){
                    g.setColor(new Color(23,23,230));
                    for (int l = 0; l < dian; l++) {
                        g.drawString("*",300+l*10,235);

                    }
                    g.drawString("*",300+dian*10,235);
                }
                //逐字显示
                if(tt<3){
                    for (int rr = 0; rr <= tt; rr++) {
                        g.setColor(new Color(250,0,0));
                        g.drawString(shi[rr],180,60+rr*20);
                    }
                    g.drawString(shi[tt],180,60+tt*20);
                }
                /**
                 * 回头注意把8改成7试试
                 */
                if(tt>=3&&tt<8){
                    g.setColor(new Color(230,0,0));
                    for (int rr = 0; rr < 3; rr++) {
                        g.drawString(shi[rr],180,60+rr*20);
                        g.setColor(new Color(r,b,y));
                    }
                    if(tt<8){
                        for (int rr = 3; rr <= tt; rr++) {
                            g.drawString(shi[rr],150,rr*20);
                        }
                    }
                    if(tt>=7){
                        for (int rr = 3; rr < 8; rr++) {
                            g.drawString(shi[rr],150,rr*20);
                        }
                    }
                }
                if(tt>=8&&tt<13){
                    g.setColor(new Color(230,0,0));
                    for (int rr = 0; rr < 3; rr++) {
                        g.drawString(shi[rr],180,60+rr*20);
                    }
                    for (int rr = 3; rr < 8; rr++) {
                        g.drawString(shi[rr],150,rr*20);
                    }
                    g.setColor(new Color(r,b,y));
                    if(tt<13){
                        for (int rr = 8; rr < tt; rr++) {
                            g.drawString(shi[rr],120,rr*20-100);
                        }
                    }
                    if(tt>=13){
                        for (int rr = 8; rr < 13; rr++) {
                            g.drawString(shi[rr],120,rr*20-100);
                        }
                    }
                }
                if(tt>=13&&tt<18){
                    g.setColor(new Color(230,0,0));
                    for (int rr = 0; rr < 3; rr++) {
                        g.drawString(shi[rr],180,60+rr*20);
                    }
                    for (int rr = 3; rr < 8; rr++) {
                        g.drawString(shi[rr],150,rr*20);
                    }
                    for (int rr = 8; rr < 13; rr++) {
                        g.drawString(shi[rr],120,rr*20-100);
                    }
                    g.setColor(new Color(r,b,y));
                    if(tt<18){
                        for (int rr = 13; rr <= tt ; rr++) {
                            g.drawString(shi[rr],90,rr*20-200);
                        }
                    }
                    if(tt>=18){
                        tt=0;
                        for (int rr = 13; rr < 18; rr++) {
                            g.drawString(shi[rr],90,rr*20-200);
                        }
                    }
                }
                if(tt>=18&&tt<23){
                    g.setColor(new Color(230,0,0));
                    for (int rr = 0; rr < 3; rr++) {
                        g.drawString(shi[rr],180,60+rr*20);
                    }
                    for (int rr = 3; rr < 8; rr++) {
                        g.drawString(shi[rr],150,rr*20);
                    }
                    for (int rr = 8; rr < 13; rr++) {
                        g.drawString(shi[rr],120,rr*20-100);
                    }
                    for (int rr = 13; rr < 18; rr++) {
                        g.drawString(shi[rr],90,rr*20-200);
                    }
                    g.setColor(new Color(r,b,y));
                    if(tt<23){
                        for (int rr = 18; rr <= tt ; rr++) {
                            g.drawString(shi[rr],30,rr*20-300);
                        }
                    }
                    if(tt>=23){
                        tt=0;
                        for (int rr = 18; rr < 23; rr++) {
                            g.drawString(shi[rr],30,rr*20-300);
                        }
                    }
                }
                if(tt>=23&&tt<30){
                    g.setColor(new Color(230,0,0));
                    for (int rr = 0; rr < 3; rr++) {
                        g.drawString(shi[rr],180,60+rr*20);
                    }
                    for (int rr = 3; rr < 8; rr++) {
                        g.drawString(shi[rr],150,rr*20);
                    }
                    for (int rr = 8; rr < 13; rr++) {
                        g.drawString(shi[rr],120,rr*20-100);
                    }
                    for (int rr = 13; rr < 18; rr++) {
                        g.drawString(shi[rr],90,rr*20-200);
                    }
                    for (int rr = 18; rr < 23; rr++) {
                        g.drawString(shi[rr],60,rr*20-300);
                    }
                    g.setColor(new Color(r,b,y));
                    if(tt<30){
                        for (int rr = 0; rr <= tt ; rr++) {
                            g.drawString(shi[rr],30,rr*20-400);
                        }
                    }
                    if(tt>=30){
                        tt=0;
                        for (int rr = 23; rr < 30; rr++) {
                            g.drawString(shi[rr],30,rr*20-300);
                        }
                    }
                }
                if(tt>30){
                    g.setColor(new Color(230,0,0));
                    for (int rr = 0; rr < 3; rr++) {
                        g.drawString(shi[rr],180,60+rr*20);
                    }
                    for (int rr = 3; rr < 7; rr++) {
                        g.drawString(shi[rr],150,rr*20);
                    }
                    for (int rr = 7; rr < 13; rr++) {
                        g.drawString(shi[rr],120,rr*20-100);
                    }
                    for (int rr = 13; rr < 18; rr++) {
                        g.drawString(shi[rr],90,rr*20-200);
                    }
                    for (int rr = 18; rr < 23; rr++) {
                        g.drawString(shi[rr],60,rr*20-300);
                    }
                    for (int rr = 23; rr < 30; rr++) {
                        g.drawString(shi[rr],30,rr*20-400);
                    }
                }
            }
        }
    }
}
