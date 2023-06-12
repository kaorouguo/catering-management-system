package com.restaurant.view.CanCheInfo;

import com.restaurant.model.Model;
import com.restaurant.tools.MyTools;
import com.restaurant.view.Window1;
import com.restaurant.view.Windows2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Canche extends JPanel implements ActionListener{
    JLabel jlb;
    JTextField jtf;
    JButton jb_query;
    JPanel p1,p2;
    JTable jtable;
    Model mm;
    JScrollPane jsp;

//    public static void main(String[] args) {
//        Window1 window1=new Window1();
//        new CustomerInfo(window1);
//    }

    public Canche(Window1 window1){
        jlb=new JLabel("请输入待查询顾客所提交的餐车菜单编号");
        jlb.setFont(MyTools.f3);
        jtf=new JTextField(20);
        jb_query=new JButton("查询");
        jb_query.setFont(MyTools.f1);
        jb_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户希望查询：");
                //因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
                String name = jtf.getText().trim();//把文本框中的内容赋给name
                String id = jtf.getText().trim();//把文本框中的内容赋给id
                mm=new Model();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.init("select * from 餐车 where id LIKE'"+id+"%' and 1=?",params);//模糊查询

                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        p1=new JPanel();
        p1.add(jlb);
        p1.add(jtf);
        p1.add(jb_query);

        jtable=new JTable();
        mm=new Model();
        String [] params={"1"};
        mm.query("select * from 餐车 where 1=?",params);
        jtable=new JTable(mm);
        jtable.setSize(500,500);
        jsp=new JScrollPane(jtable);
        p2=new JPanel();
        p2.add(jsp);

        this.setLayout(new BorderLayout());
        //把p1加入总的JPanel
        this.add(p1,"North");
        this.add(p2,"Center");
        this.setBackground(Color.pink);
        this.setVisible(true);
    }

    public Canche(Windows2 window1){
        jlb=new JLabel("请输入待查询顾客提交的菜单的编号");
        jlb.setFont(MyTools.f3);
        jtf=new JTextField(20);
        jb_query=new JButton("查询");
        jb_query.setFont(MyTools.f1);
        jb_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户希望查询：");
                //因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
                String name = jtf.getText().trim();//把文本框中的内容赋给name
                String id = jtf.getText().trim();//把文本框中的内容赋给id
                mm=new Model();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.init("select * from 餐车 where id LIKE'"+id+"%' and 1=?",params);//模糊查询

                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        p1=new JPanel();
        p1.add(jlb);
        p1.add(jtf);
        p1.add(jb_query);

        jtable=new JTable();
        mm=new Model();
        String [] params={"1"};
        mm.query("select * from 餐车 where 1=?",params);
        jtable=new JTable(mm);
        jtable.setSize(500,500);
        jsp=new JScrollPane(jtable);
        p2=new JPanel();
        p2.add(jsp);

        this.setLayout(new BorderLayout());
        //把p1加入总的JPanel
        this.add(p1,"North");
        this.add(p2,"Center");
        this.setBackground(Color.pink);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
