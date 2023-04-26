import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.lang.Math;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.awt.GraphicsDevice;

public class MyPanel extends JPanel{
    
    Scanner sc = new Scanner(System.in);

    int pathLength, expand;

    int mazeArr2[][] = {
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        { 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
        { 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        { 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
        { 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
        { 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
        { 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
        { 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        { 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
        { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1},
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };


    

    int rcount = 15;
    int offset = 500/rcount;

    int mazeArr[][] = new int[rcount][rcount];

    Square squareArr[][] = new Square[rcount][rcount];
    Stack<Square> squareStack = new Stack<Square>();

    Queue<Square> squareQueue = new LinkedList<Square>();

    MyPanel(){
        this.setPreferredSize(new Dimension(500, 500));
        
        for (int i = 0; i < rcount; i++) {
            for (int j = 0; j < rcount; j++) {
                squareArr[i][j] = new Square();          
            }
        }
    }


    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        for (int i = 0; i < rcount; i++) {
            for (int j = 0; j < rcount; j++) {
                squareArr[i][j].drawSqr(g2d, i*offset, j*offset, offset);
            }
        }
    }

    
    public void generateMap(){
        
        int max = rcount-1;
        int min = 0;

        for (int i = 0; i < 25; i++) {

            int r_x, r_y;

            do {
                r_x = (int)(Math.random()*(max-min+1)+min); 
                r_y = (int)(Math.random()*(max-min+1)+min); 
               
            } while (r_x == 0 && r_y == 0);

            Square curr = squareArr[r_x][r_y];
            curr.setColor(Color.black);
            
        }
    }

    public void generateMaze(){
        
        // for initialization of pathArr
                
        for (int i = 0; i < rcount; i++) {
            for (int j = 0; j < rcount; j++) {
                mazeArr[i][j] = mazeArr2[j][i];
            }
        }

        squareArr[1][1].setColor(Color.cyan);
        squareArr[1][13].setColor(Color.cyan);

        for (int i = 0; i < rcount; i++) {
            for (int j = 0; j < rcount; j++) {

                if (mazeArr[i][j] == 1)
                    squareArr[i][j].setColor(Color.black);
            }
        }
    }

    // Depth First Search
    public void depthFirstSearch(){
        Square curr = squareArr[1][1];
        boolean isReached = false;

        int i,j;


        squareStack.push(curr);

        while (!isReached && !squareStack.isEmpty()) {
            i = curr.getX()/offset;
            j = curr.getY()/offset;
            
            curr.setColor(Color.orange);


            try {
                this.repaint();
                Thread.sleep(400);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            
//          System.out.println(i + ", " + j);
//            sc.next();

            if(curr == squareArr[1][13]){
                isReached = true;
            }


            if(!squareArr[i+1][j].isVisited() && squareArr[i+1][j].getColor() != Color.black){
                curr.setVisited();
                expand++;
                curr = squareArr[i+1][j];
                squareStack.push(curr);

            }

            else if(!squareArr[i][j+1].isVisited() && squareArr[i][j+1].getColor() != Color.black){

                curr.setVisited();
                expand++;
                curr = squareArr[i][j+1];
                squareStack.push(curr);
            }

            else if(!squareArr[i-1][j].isVisited() && squareArr[i-1][j].getColor() != Color.black){
                curr.setVisited();
                expand++;
                curr = squareArr[i-1][j];
                squareStack.push(curr);
            }

            else if(!squareArr[i][j-1].isVisited() && squareArr[i][j-1].getColor() != Color.black){
                curr.setVisited();
                expand++;
                curr = squareArr[i][j-1];
                squareStack.push(curr);
            }

            // backtrack
            else{
                curr.setVisited();
                expand++;
                squareStack.pop();

                if(!squareStack.isEmpty())
                    curr = squareStack.peek();

            }

        }


        while (!squareStack.isEmpty()) {
            Square pathBlock = squareStack.peek();
            pathBlock.setColor(Color.green);
            pathLength++;

            squareStack.pop();
        }
        squareArr[1][13].setColor(Color.green);
        pathLength++;
        
        System.out.println("DFS:");
        System.out.println("Expansion:" + expand);
        System.out.println("pathLength:" + pathLength);

    } 

    // Clear map again
    public void regenerateMap(){
        for (int i = 0; i < rcount; i++) {
            for (int j = 0; j < rcount; j++) {

                if(squareArr[i][j].getColor() != Color.black && squareArr[i][j].isVisited()) squareArr[i][j].setUnvisited();
            }
            
        }

    }


    // breadth first Search
    public void breadthFirstSearch(){
        Square curr = squareArr[1][1];
        boolean isReached = false;

        int i,j;

        squareQueue.add(curr);
        
        expand = 0;

        while (!isReached) {
            try {
                this.repaint();
                Thread.sleep(200);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }

            curr = squareQueue.remove();

            pathLength = 0;
            
            curr.setVisited();
            expand++;


            if(curr == squareArr[1][13]){
                isReached = true;
            }

            i = curr.getX()/offset;
            j = curr.getY()/offset;

            
            Square temp ;


            // right node
            if(!squareArr[i+1][j].isVisited() && squareArr[i+1][j].getColor() != Color.black){
                temp = squareArr[i+1][j];
                squareQueue.add(temp);
                
                if(temp.from == null)
                    temp.setFrom(curr);
                temp.setColor(Color.orange);

            }

            // down node
            if(!squareArr[i][j+1].isVisited() && squareArr[i][j+1].getColor() != Color.black){
                temp = squareArr[i][j+1];
                squareQueue.add(temp);

                if(temp.from == null)
                    temp.setFrom(curr);

                temp.setColor(Color.orange);
            }

            // left node
            if(!squareArr[i-1][j].isVisited() && squareArr[i-1][j].getColor() != Color.black){
                temp = squareArr[i-1][j];
                squareQueue.add(temp);


                if(temp.from == null)
                    temp.setFrom(curr);

                temp.setColor(Color.orange);
            }

            // up node
            if(!squareArr[i][j-1].isVisited() && squareArr[i][j-1].getColor() != Color.black){
                temp = squareArr[i][j-1];
                squareQueue.add(temp);

                if(temp.from == null)
                    temp.setFrom(curr);

                temp.setColor(Color.orange);
            }

        }


        this.repaint();

        curr = squareArr[1][13];
        while (curr != squareArr[1][1]) {
            curr.setColor(Color.green); 
            pathLength++;
            curr = curr.from;
        }
        squareArr[1][1].setColor(Color.green);
        pathLength++;

        System.out.println("BFS:");
        System.out.println("Expansion:" + expand);
        System.out.println("pathLength:" + pathLength);
    } 
}


