/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.ParseException;
import Database.DBconnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hulah
 */
public class TamuModel {

    private String id, nama, email, kelamin, alamat, telpon, keterangan, created_at;

    public TamuModel() {

    }

    public TamuModel(Boolean create) {
        this.created_at = this.getNowTimeStamp();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCreatedAt() {
        return this.created_at;
    }

    public String getCreatedAt(Boolean table) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String outputDate = "";

        if (this.created_at != null) {
            try {
                Date date = input.parse(this.created_at);
                outputDate = output.format(date);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }

        return outputDate;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public void save() {
        DBconnection db = new DBconnection();
        db.insertTamu(this);
    }

    public void update() {
        DBconnection db = new DBconnection();
        db.editTamu(this);
    }

    public void delete() {
        DBconnection db = new DBconnection();
        db.deleteTamu(this.getId());
    }

    private String getNowTimeStamp() {
        SimpleDateFormat formatTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return formatTimeStamp.format(now);
    }

    public Boolean validate() {
        if (this.nama.isEmpty()) {
            if (this.email.isEmpty()) {
                if (this.kelamin.isEmpty()) {
                    if (this.alamat.isEmpty()) {
                        if (this.telpon.isEmpty()) {
                            if (this.keterangan.isEmpty()) {
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return true;
    }

    public Boolean validate(Boolean isEdit) {
        if (this.nama.isEmpty()) {
            if (this.email.isEmpty()) {
                if (this.kelamin.isEmpty()) {
                    if (this.alamat.isEmpty()) {
                        if (this.telpon.isEmpty()) {
                            if (this.keterangan.isEmpty()) {
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        if (isEdit) {
            if (this.getId().isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
