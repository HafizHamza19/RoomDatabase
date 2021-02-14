package com.hafizhamza.roamdatabase.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {
@Insert
     void studentinsertion(Student student);
@Query("SELECT * FROM Student")
    List<Student> getdata();

    @Query("Update Student set fname = :FName,lname=:LName,stdclass=:StdClass  where stdid = :stuID")
    void updateStu(String FName,String LName,String StdClass, int stuID);

    @Query("Delete from Student where stdid = :stuID")
    void deleteStu (int stuID);

//
//@Delete
//void delete(Student student);
//@Update
//    void update(Student student);
}
