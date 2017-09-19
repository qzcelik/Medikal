 
package medilal1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

 
public class klavye implements KeyListener
{

 
    public void keyTyped(KeyEvent e) {
        
        
        char karakter=e.getKeyChar();
        if((karakter<'0'||karakter>'9')&&karakter!='\b')
        {
            e.consume();
        }
     }

   
    public void keyPressed(KeyEvent e) {
    }

  
    public void keyReleased(KeyEvent e) {
    }
    
}
