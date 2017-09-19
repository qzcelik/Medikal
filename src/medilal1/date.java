 
package medilal1;

import java.text.DateFormat;
import java.util.Date;
import javax.xml.crypto.Data;

 
public class date
{
    String tarihGetir(Date tarih)
    {
        if(tarih.toString()!=null)
        {
        DateFormat df=DateFormat.getDateInstance();//tarihi formatlamak
        String t=df.format(tarih);
        return t;
        }
      else
            return null;
    }
}
