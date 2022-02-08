
/**
 * Title:        DepthFirstSearchEngine<p>
 * Description:  Performs a depth first search in a maze<p>
 * Copyright:    Copyright (c) Mark Watson, Released under Open Source Artistic License<p>
 * Company:      Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */

import java.awt.Dimension;
import java.util.*;

public class DepthFirstSearchEngine extends AbstractSearchEngine {
    // $$1
    ArrayList<Dimension> L= new ArrayList<Dimension>(), T=new ArrayList<Dimension>();
    Stack<Dimension>  pile=new Stack<Dimension>();
    Dimension predecessor[][] = new Dimension[maze.getWidth()][maze.getHeight()];
    
            
    public DepthFirstSearchEngine(int width, int height) {
        super(width, height);
        
        // $$2
        for (int i=0; i<width; i++) 
        for (int j=0; j<height; j++) 
            predecessor[i][j] = null;

        pile.push(startLoc);
        T.add(goalLoc);
        
        // Algorithme de recherche en profondeur 
        doDFS();
        
        // now calculate the shortest path from the predecessor array:
        maxDepth = 0;
        if (!isSearching) {
            searchPath[maxDepth++] = goalLoc;
            for (int i=0; i<100; i++) {
                searchPath[maxDepth] = predecessor[searchPath[maxDepth - 1].width][searchPath[maxDepth - 1].height];
                maxDepth++;
                if (equals(searchPath[maxDepth - 1], startLoc))  break;  // back to starting node
            }
        }
    
    }

    private void doDFS() {
       
        while(!pile.isEmpty()){
             currentLoc = pile.pop();
             L.add(currentLoc);
         
             if(T.contains(currentLoc)){
                 System.out.println("But trouvé : (" + currentLoc.width +", " + currentLoc.height+")");
                 isSearching = false;
                 break;
             }
             else{
                 Dimension [] connected = getPossibleMoves(currentLoc);
                 for(int i=0;i<connected.length;i++){
                     
                 if( connected[i]!=null && !pile.contains(connected[i]) && !L.contains(connected[i])){
                    pile.push(connected[i]);
                    predecessor[connected[i].width][connected[i].height] = currentLoc;
                 }
                         
                 }
                
             }
        }
      
    }
}
