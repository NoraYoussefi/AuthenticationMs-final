
package labr_code;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class interface_principale extends javax.swing.JFrame  {
    
     JPanel jPanel1 = new JPanel();
     
     private JTextArea text;
    
     
     
     public static void LireFichierText(){
         short[][] maze = new short[21][21] ;
         int sizeW=0;
         int j=0;
         int i=0;
          File fileDirs = new File("C:\\Users\\dell\\Documents\\NetBeansProjects\\app_java_labr\\src\\mazeFile\\LABY_21x21.txt");

           BufferedReader in = null;
         try {
             in = new BufferedReader(
                     new InputStreamReader(new FileInputStream(fileDirs), "utf-8"));
         } catch (FileNotFoundException ex) {
             Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
         } catch (UnsupportedEncodingException ex) {
             Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
         }

        String str;
   
        
         try {
             while ((str = in.readLine()) != null) {
              //   System.out.println(str);
              sizeW=str.length()+1;
                 
             
                  for(j=0;j<str.length();j++){
                      if(str.charAt(j)==' '){
                           maze[i][j] = 0;
                      }
                      else{
                           maze[i][j] = 1;
                      }
                      
                  }
                  
                  i++;
                  
                 
            
                 
             }  
             
             
             for(i=0;i<maze.length;i++){
                 
                 for (j=0;j<maze[i].length;j++){
                     System.out.println(maze[i][j]);
                 }
                 
                  System.out.println("\n");
             }
       
            
         
         } catch (IOException ex) {
             Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
         }
         System.out.println(sizeW);
     }
     
   
    public interface_principale()
    {
        setTitle("On affiche le bon");
        setSize(600,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
 
         
        // TEXTE
        text = new JTextArea(readFile());
         
        // AJOUT DANS LA FENETRE
        add(text,BorderLayout.CENTER);
         
        // AFFICHE
        setVisible(true);
        
        
    }
     
    
     public String readFile()
    {
         File fileDirs = new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\AI_project\\src\\mazeFile\\LABY_21x21.txt");
        // LIS LE FICHIER
        String lines = "";
        String line;
        try
        {
            // CREE LE FLUX
            BufferedReader reader = new BufferedReader( new FileReader(fileDirs) );
             
            // LIS LIGNE A LIGNE
            while( (line = reader.readLine()) != null )
            
                lines += line+"\n";
        }
        catch( Exception e )
        {
            lines = "Une erreur s'est produite durant la lecture du flux : "+e.getMessage();
        }  
       
        return lines;
    }
     
//      public static void readoFile(){
//        Scanner scan = null;
//         try {
//             scan = new Scanner(new File("C:\\Users\\Home\\Downloads\\Labyrinthes.rar (Unzipped Files)-20211130T135815Z-001\\Labyrinthes.rar (Unzipped Files)\\LABY_21x21.txt"));
//         } catch (FileNotFoundException ex) {
//             Logger.getLogger(interface_principale.class.getName()).log(Level.SEVERE, null, ex);
//         }
//   while(scan.hasNext()){
//
//   System.out.println(scan.nextLine());
//    }
//      }
//     
     
     
     public static void readTextFile1(){

        try{
            int c;
             char car=0;
             String[] arr = new String[1];
             var maList = new ArrayList<String>(Arrays.asList(arr));

            File ips = new File("C:\\Users\\dell\\Documents\\NetBeansProjects\\app_java_labr\\src\\mazeFile\\LABY_21x21.txt");
            FileReader ipsr = new FileReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
              StringBuffer ligne= new StringBuffer();
            while((c=br.read())!=-1){
                 car= (char)br.read();
                 //function  here
                // maList.add(car);
                     
                 c=c+1;
                 ligne.append(car);
                 if (car=='\n') {
                       System.out.println(ligne);
                       ligne.delete(0,ligne.length());
                     }
}
             if (ligne.length()!=0)
                  System.out.println(ligne);
                
         
            br.close();
        }  
         
        catch (Exception e){
            System.out.println(e.toString());
        }   
   
}
     
     
      public static void main(String[] args) {

      //  readoFile();
    LireFichierText();
   //  new interface_principale();
     // readTextFile1();
     
     
      }

      
    }
