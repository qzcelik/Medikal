 
package medilal1;

import java.util.logging.Level;
import java.util.logging.Logger;

 
public class Medilal1 {

   
    public static void main(String[] args)
    {
        try {
            form a=new form();
            a.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Medilal1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
