/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*;
import java.util.ArrayList;
import Model.TamuModel;

/**
 *
 * @author hulah
 */
public class DBconnection {
  private final String host = "jdbc:mysql://localhost:3306/bukutamu";
  private final String user = "root"; 
  private final String password = "";
  
  Connection connection = null;
  Statement stmt = null;
  public DBconnection() {
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          connection = DriverManager.getConnection(this.host, this.user, this.password);
      } catch (Exception exception) {
          System.out.println(exception.toString());
      }
  }
  
  
  public void insertTamu(TamuModel tamuObj) {
      try {
        stmt = connection.createStatement();
        String dataStr = "";
        dataStr += "'" + tamuObj.getNama() + "', ";
        dataStr += "'" + tamuObj.getEmail() + "', ";
        dataStr += "'" + tamuObj.getKelamin() + "', ";
        dataStr += "'" + tamuObj.getAlamat() + "', ";
        dataStr += "'" + tamuObj.getTelpon() + "', ";
        dataStr += "'" + tamuObj.getKeterangan() + "', ";
        dataStr += "'" + tamuObj.getCreatedAt() + "'";
        String sql = "INSERT INTO Tamu (nama, email, kelamin, alamat, telpon, keterangan, created_at) VALUES ("+ dataStr +")";
        
        stmt.executeUpdate(sql);
        
        stmt.close();
        connection.close();
      } catch (Exception exception) {
          System.out.println(exception.toString());
      }
  }
  
  public void editTamu(TamuModel tamuObj) {
      try {
        stmt = connection.createStatement();
        String dataStr = "";
        dataStr += "nama ='" + tamuObj.getNama() + "', ";
        dataStr += "email='" + tamuObj.getEmail() + "', ";
        dataStr += "kelamin='" + tamuObj.getKelamin() + "', ";
        dataStr += "alamat='" + tamuObj.getAlamat() + "', ";
        dataStr += "telpon='" + tamuObj.getTelpon() + "', ";
        dataStr += "keterangan='" + tamuObj.getKeterangan() + "', ";
        dataStr += "created_at='" + tamuObj.getCreatedAt() + "'";
        String sql = "UPDATE Tamu SET " + dataStr;
        sql += " WHERE id = " + tamuObj.getId();
        
        stmt.executeUpdate(sql);
        
        stmt.close();
        connection.close();
      } catch (Exception exception) {
          System.out.println(exception.toString());
      }
  }
  
  public void deleteTamu(String id) {
      try {
        stmt = connection.createStatement();
        
        String sql = "DELETE FROM Tamu where id=" + id;
        
        stmt.executeUpdate(sql);
        
        stmt.close();
        connection.close();
      } catch (Exception exception) {
          System.out.println(exception.toString());
      }
  }
  
  public ArrayList<TamuModel> listTamu() {
      ArrayList<TamuModel> tamuObjArr = new ArrayList<TamuModel>();
      try {
        stmt = connection.createStatement();
        String sql = "SELECT * FROM Tamu";
        ResultSet rs = stmt.executeQuery(sql);
//        ResultSetMetaData metadata = rs.getMetaData();
//        System.out.println(metadata.getColumnName(8));
//        System.out.println(metadata.getColumnTypeName(8));
        while(rs.next()) {
            TamuModel objTamu = new TamuModel();
            objTamu.setId(Integer.toString(rs.getInt("id")));
            objTamu.setNama(rs.getString("nama"));
            objTamu.setAlamat(rs.getString("alamat"));
            objTamu.setKelamin(rs.getString("kelamin"));
            objTamu.setEmail(rs.getString("email"));
            objTamu.setTelpon(rs.getString("telpon"));
            objTamu.setKeterangan(rs.getString("keterangan"));
            objTamu.setCreatedAt(rs.getString("created_at"));
            tamuObjArr.add(objTamu);
        }
        
        rs.close();
        stmt.close();
        connection.close();
      } catch (Exception exception) {
          System.out.println(exception.toString());
      }
      return tamuObjArr;
  }
}
