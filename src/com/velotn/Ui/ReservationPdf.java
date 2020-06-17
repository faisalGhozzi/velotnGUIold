package com.velotn.Ui;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import com.sun.scenario.effect.ImageData;
import com.velotn.Entite.Velo;
import com.velotn.Utils.DataBase;

import javax.swing.text.html.ImageView;


/**
 *
 * @author Bhs Nada
 */
public class ReservationPdf {
    // req qr code
    Connection c= DataBase.getInstance().getConnection();
    //     public String num(int id ) throws SQLException{
//      String query = "SELECT * from location l INNER JOIN velos v INNER JOIN produits p on v.id=p.id AND l.id_produit=v.id WHERE l.id="+id ;
//            Statement stmt;
//             stmt = c.createStatement();
//              ResultSet rs = stmt.executeQuery(query);
//
//               String num=null;
//              if(rs.next()){
//   num  =rs.getString("nom");
//     System.out.println(num);
//}
//
//
//
//     return num;
//     }
    ///////
    /// PDF
    public void Location(int id ,String date_debut , String date_fin , float prixtotal, Velo v  ) throws SQLException, FileNotFoundException {

        Document document = new Document(PageSize.A4);
        try{
            PdfWriter.getInstance(document, new FileOutputStream("Reservation"+id+".pdf"));
            document.open();

            /*Rectangle pageSize = new Rectangle(216, 720);
           pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
            */
            Paragraph p1 = new Paragraph("Reservation N°  :   "+id);
            Paragraph p2 = new Paragraph("----------------------------");
            Paragraph p3 = new Paragraph("                                    ");

            Paragraph p4 = new Paragraph("Date de début   :    "+ date_debut);
            Paragraph p5 = new Paragraph ("Date Fin    :    "+date_fin);

            Paragraph p6 = new Paragraph("Velo : " + v.getId());
            Paragraph p7 = new Paragraph("Velo Type  : " + v.getType());
            Paragraph p8 = new Paragraph("Velo Marque : " + v.getMarque());
            Paragraph p9= new Paragraph("Prix Totale   :    "+ prixtotal + " $");
            Image img = Image.getInstance(v.getImg_url());
            img.scalePercent(10f);


           // Paragraph p7 =  new Paragraph("Scanner le QR_Code pour connaitre le nom de la maison d'hôte :");
            //QR_Code
//         BarcodeQRCode my_code = new BarcodeQRCode(num(id),id,id,null);
//           Paragraph p8 = new Paragraph("------------------------------------------------------------------");
//        Image qr_image = my_code.getImage();

            document.add(p1);
            document.add(p2);
            document.add(p3);


//             document.add(qr_image);
//             document.add(p8);
            document.add(img);
            document.add(p4);
            document.add(p3);
            document.add(p5);
            document.add(p3);
            document.add(p6);
            document.add(p3);
            document.add(p7);
            document.add(p8);
            document.add(p9);
            document.add(p3);
            document.add(p3);
            document.add(img);
        }
        catch(DocumentException | MalformedURLException e){
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        document.close();
    }
}