  
package medilal1;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class veriCek 
{
    Document doc;
    public String url="http://www.teknopor.com/urunler.html",title[]=new String[10];
    //public String dizi[]=new String[10];
    List<String>dizi=new ArrayList<>();
    List<String> linkDizi=new ArrayList<>();
   // String urll[]=new String[10];
    List<String> yeni=new ArrayList<>();
 
    public   Element content,el;
    public Elements title2;
    public  BufferedImage image[]=new BufferedImage[10];
    public void veriAl()
    {
        int k=0,l=0;
        String src,url2;
        try
        {
          doc =Jsoup.connect(url).get();
            URL url;
            Elements resim=doc.select("img.urun_resmi");
            for(Element el:resim)
            {
                 src=el.absUrl("src");
                 url=new URL(src);
                 image[k]= resize(ImageIO.read(url), 200, 200);
                 if(k==9)
                 break;
                 k++;
            }
            
           for (int i = 0; i <10; i++) 
            {
              content=doc.select("div.content_back").set(i, content); 
              dizi.add(content.text());
            }
           
          int sayac=0;
          while(doc.select("a").hasText())
           {
           if(sayac>=60)
            {
                 Element linkk=doc.select("a").get(sayac);
                 String iki=linkk.attr("abs:href");
                 linkDizi.add(iki);
                
            }
            if(sayac==79)
                break;
            sayac++;
           }
           
 
        }
        
        catch (IOException ex) 
        {
            Logger.getLogger(veriCek.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH)
    { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
    } 
    int sayac2=0;
    String linker2=null;
    
    
    
    
    
            
 
}
