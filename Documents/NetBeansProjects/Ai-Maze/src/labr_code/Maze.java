/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labr_code;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class Maze
{
    public enum Square
    {
        WALLS("█"),
        OPEN_SPACES(" "),
        START("o"),
        FINISH("*");
        protected String ch;
        Square(String ch)
        {
        this.ch = ch;
        }
        public String toString()
        {
            return ch;
        }
        public static Square fromChar(char ch)
        {
            if (ch == '#')
                return WALLS;
            else if (ch == '.')
                return OPEN_SPACES;
//            else if (ch == 'o')
//                return START;
//            else if (ch == '*')
//                return FINISH;
            else
                throw new IllegalArgumentException();
        }
    }
    Maze() throws FileNotFoundException
    {
    
    Scanner myScanner = new Scanner(new File("C:\\Users\\dell\\Documents\\NetBeansProjects\\app_java_labr\\src\\mazeFile\\LABY_21x21.txt"));
    final int WIDTH = myScanner.nextInt();
    final int HEIGHT = myScanner.nextInt();
    Square [] [] maze = new Square[HEIGHT] [WIDTH];
    for (int i = 0; i < HEIGHT; i++)
    {
        String s = myScanner.next();
        for(int j = 0; j <WIDTH; j++)
        {
            maze[i][j] = Maze.Square.fromChar(s.charAt(j));
            System.out.print(maze[i][j]);
        }
    }
    }
    public static void main(String[] args) throws FileNotFoundException
    {
            Maze aMaze = new Maze();
    }
}