package medilal1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import medilal1.veri2;
import java.util.Date;
import javax.print.DocFlavor;

public class raporHatirlat {

    public void raporGetir() throws SQLException, ClassNotFoundException {
        ResultSet rs;
        int adet=0;
        Object veriDizi[][];
        Connection bag = null;
        veri2 baglan = new veri2();
        bag = baglan.bagla();
        Statement state = bag.createStatement();
        rs = state.executeQuery("SELECT COUNT (*) AS adet FROM HASTA");
        adet=rs.getInt("adet");
        rs = state.executeQuery("SELECT*FROM HASTA");
        Date sunakiTarih = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.M.yyyy");
        String tarihSon = null, gun = null, yil = null;
        String ay = null;
        String sGun = null, sAy = null, sYil = null;
        int gunSay=0;
        veriDizi=new Object[adet][9];
        adet=0;
        raporForm raporGel = new raporForm();
        try 
        {
            while (rs.next()) {
                tarihSon = rs.getString("TARSON");

                gun = tarihSon.substring(0, 2);
                yil = tarihSon.substring(7, 11);
                ay = String.valueOf(tarihDuzenle(tarihSon.substring(3, 6)));
               
                sGun = format.format(sunakiTarih).substring(0, 2);
                sAy = format.format(sunakiTarih).substring(3, 5);
                sYil = format.format(sunakiTarih).substring(6, 10);
                
                if (ay.equals(sAy) && yil.equals(sYil))
                {
                    gunSay=Integer.valueOf(gun) - Integer.valueOf(sGun);
                    if (gunSay <= 3)
                    {
                        veriDizi[adet][0]=rs.getString("TCNO");
                        veriDizi[adet][1]=rs.getString("AD");
                        veriDizi[adet][2]=rs.getString("YAS");
                        veriDizi[adet][3]=rs.getString("MAL");
                        veriDizi[adet][4]= rs.getString("C");
                        veriDizi[adet][5]=rs.getString("TARBAS");
                        veriDizi[adet][6]=rs.getString("TARSON");
                        veriDizi[adet][7]=String.valueOf(gunSay);
                        
                        raporGel.setVisible(true);
                        raporGel.hastaSet(veriDizi);
                        adet++;
                    }

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(raporHatirlat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    int tarihDuzenle(String secim) {
        int tarihDeger = 0;
        switch (secim) {
            case "Ock":
                tarihDeger = 1;
                break;
            case "Şub":
                tarihDeger = 2;
                break;
            case "mar":
                tarihDeger = 3;
                break;
            case "Nis":
                tarihDeger = 4;
                break;
            case "may":
                tarihDeger = 5;
                break;
            case "Haz":
                tarihDeger = 6;
                break;
            case "tem":
                tarihDeger = 7;
                break;
            case "Agu":
                tarihDeger = 8;
                break;
            case "Eyl":
                tarihDeger = 9;
                break;
            case "Eki":
                tarihDeger = 10;
                break;
            case "Kas":
                tarihDeger = 11;
                break;
            case "Ara":
                tarihDeger = 12;
                break;
        }
        return tarihDeger;
    }
}
