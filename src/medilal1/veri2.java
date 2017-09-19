 
package medilal1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
public class veri2 
{
    Connection bagla() throws ClassNotFoundException
    {
         Connection con=null;
        try {
          
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:medikal.db");
          
 
        } catch (SQLException ex) {
            Logger.getLogger(veri2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    void tabloEkle() throws ClassNotFoundException
    {
        try {
            Connection con=bagla();
            
            Statement state=con.createStatement();
            String sqlCumle="CREATE TABLE HASTA " +
                    "(TCNO        TEXT    NOT NULL, " +                    
                    "AD           TEXT    NOT NULL, " +
                    "YAS          TEXT    NOT NULL, " +
                    "MAL          TEXT    NOT NULL,"   +
                    "C            TEXT    NOT NULL , " +
                    "TARBAS       TEXT    NOT NULL,"+
                    "TARSON       TEXT   NOT NULL,"+
                    "EK           TEXT)";
                    state.executeUpdate(sqlCumle);
                    state.close();
                    con.close();
        } catch (SQLException ex) {
            Logger.getLogger(veri2.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }
    void veriEkle(String tc,String ad,String yas,String urun,String cinsiyet,String tarihBas,String tarihSon,String ekBilgi)
    {

        try
        {
            Statement stmt=null;
            Connection con=bagla();
            try {
                stmt = con.createStatement();
                 String sql = "INSERT INTO HASTA (TCNO,AD,YAS,MAL,C,TARBAS,TARSON,EK) " +
                   "VALUES ('"+tc+"' , '"+ad+"', '"+yas+"', '"+urun+"','"+cinsiyet+"','"+tarihBas+"','"+tarihSon+"','"+ekBilgi+"')"; 
                    stmt.executeUpdate(sql);
                    stmt.close();
                    con.commit();
                    con.setAutoCommit(false);
                    con.close();
            } catch (SQLException ex) {
                Logger.getLogger(veri2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(veri2.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null,"Hasta Kaydedildi");
        
    }
     Object [][]dizi;
     String tc[];
     int veriBoy=0;
     
     
    void listele(int kontrol,String bul) throws SQLException
    { 
         
         int adet=0;
         try {
            Statement stmt=null;
            Connection con=bagla();
            stmt=con.createStatement();
           
            ResultSet rs=stmt.executeQuery("SELECT COUNT (*) AS adet FROM HASTA");
            rs.next();
            adet=rs.getInt("adet");
            dizi=new Object[adet][8];
            tc=new String[adet];
            adet=0;
            if(kontrol==1)
            {
            rs=stmt.executeQuery("SELECT * FROM HASTA");
            }
            if(kontrol==2)
            {
                rs=stmt.executeQuery("SELECT * FROM HASTA WHERE AD='"+bul+"' OR TCNO='"+bul+"' ");
            }
            if(kontrol==3)
            {
                rs=stmt.executeQuery("SELECT * FROM HASTA WHERE C='"+bul+"'");
            }
            if(kontrol==4)
            {
                rs=stmt.executeQuery("SELECT * FROM HASTA WHERE C='"+bul+"'");
            }
            if(kontrol==5)
            {
                rs=stmt.executeQuery("SELECT * FROM HASTA WHERE TARSON='"+bul+"' ");
            }
            while(rs.next())
            {
                
                dizi[adet][0]=rs.getString("TCNO");
                tc[adet]=rs.getString("TCNO");
                dizi[adet][1]=rs.getString("AD");
                dizi[adet][2]=rs.getString("YAS");
                dizi[adet][3]=rs.getString("MAL");
                dizi[adet][4]=rs.getString("C");
                dizi[adet][5]=rs.getString("TARBAS");
                dizi[adet][6]=rs.getString("TARSON");
                dizi[adet][7]=rs.getString("EK");
                adet++;
            } 
             con.setAutoCommit(false);
            stmt.close();
            con.close();
            rs.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(veri2.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    void guncelle(String tc,String ad,String yas,String urun,String tarihBas,String tarihSon,String ekBilgi)
    {
        Statement state;
      
    
       try
       {
              Connection con=bagla();
              state=con.createStatement();
              state.executeUpdate("UPDATE HASTA SET AD='"+ad+ "' , YAS='"+yas+"' , MAL='"+urun+"' , TARBAS='"+tarihBas+"' , TARSON='"+tarihSon+"' ,EK='"+ekBilgi+"' WHERE TCNO = '"+tc+"' ");
              con.setAutoCommit(false);
              state.close();
              con.close();
              JOptionPane.showMessageDialog(null, "Veri Güncellendi.");
      }
       catch(Exception e)
       {
           System.out.println(e);
       }
       
        
    }
    void silme(String silinecekSatir) throws ClassNotFoundException, SQLException
    {
            int sayac=0;
            Statement state;
            ResultSet rs;
            Connection con=bagla();
        try 
        {
            state=con.createStatement();
            rs=state.executeQuery("SELECT * FROM HASTA");
            rs=state.executeQuery("DELETE FROM HASTA WHERE TCNO='"+silinecekSatir+"'");
            state.close();
            rs.close();
            con.setAutoCommit(false);
            con.close();
        }
        
        catch (SQLException ex) {
            Logger.getLogger(veri2.class.getName()).log(Level.SEVERE, null, ex);
        }
        listele(1, null);
    }
}
