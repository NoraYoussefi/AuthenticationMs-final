
/**
 * Title:        BreadthFirstSearchEngine<p>
 * Description:  Performs a Breadth first search in a maze<p>
 * Copyright:    Copyright (c) Mark Watson, Released under Open Source Artistic License<p>
 * Company:      Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */

import java.awt.Dimension;
import java.util.*;

public class BreadthFirstSearchEngine extends AbstractSearchEngine {
    
    ArrayList<Dimension> L= new ArrayList<Dimension>(), T=new ArrayList<Dimension>();
    LinkedList<Dimension> file = new LinkedList<Dimension>();
    Dimension predecessor[][];  
        
    public BreadthFirstSearchEngine(int width, int height) {
        super(width, height);
        
        predecessor = new Dimension[width][height];  
        for (int i=0; i<width; i++) 
            for (int j=0; j<height; j++) 
                predecessor[i][j] = null;
        
        //L.add(startLoc);
        file.add(startLoc);
        T.add(goalLoc);
        
        doBFS();
        
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

    private void doBFS() {
              
        while (!file.isEmpty()) {
            
            currentLoc = file.removeFirst();
            L.add(currentLoc);           
            if(T.contains(currentLoc)){
                 System.out.println("But trouvé : (" + currentLoc.width +", " + currentLoc.height+")");
                 isSearching = false;
                 break;
             }
             else{
                
                Dimension [] connected = getPossibleMoves(currentLoc);
                for (int i=0; i<4; i++) {

                    if ( connected[i]!=null && !file.contains(connected[i]) && !L.contains(connected[i])) {
                       
                        predecessor[connected[i].width][connected[i].height] = currentLoc;
                        file.addLast(connected[i]);
                    }
                }
            }
        }
        
    }
   
}
