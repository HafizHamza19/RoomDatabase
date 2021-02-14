package com.hafizhamza.roamdatabase.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Student implements Serializable {
    @PrimaryKey (autoGenerate = true)
    int stdid;
    @ColumnInfo(name = "fname")
    String fname;
    @ColumnInfo(name = "lname")
    String lname;
    @ColumnInfo(name = "stdclass")
    String stdclass;

    public Student(String fname, String lname, String stdclass) {
        this.fname = fname;
        this.lname = lname;
        this.stdclass = stdclass;
    }

    public int getStdid() {
        return stdid;
    }

    public void setStdid(int stdid) {
        this.stdid = stdid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStdclass() {
        return stdclass;
    }

    public void setStdclass(String stdclass) {
        this.stdclass = stdclass;
    }
}
