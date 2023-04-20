import java.awt.*;
import java.util.Scanner;

import javax.swing.*;


public class MyFrame extends JFrame{
    MyPanel panel;

    MyFrame(){
        panel = new MyPanel();
        Scanner sc = new Scanner(System.in);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(510, 600);
        this.setTitle("Jwindow");
        this.add(panel);
//        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        panel.generateMaze();
        panel.repaint();

        sc.nextInt(); 

        panel.depthFirstSearch();
        panel.repaint();

        sc.nextInt(); 
        
        panel.regenerateMap();
        panel.repaint();

        sc.nextInt(); 

        panel.breadthFirstSearch();
        panel.repaint();

    }

   
}

