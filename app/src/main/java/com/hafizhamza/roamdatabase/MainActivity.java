package com.hafizhamza.roamdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hafizhamza.roamdatabase.Adapter.StudentAdapter;
import com.hafizhamza.roamdatabase.Room.DatabaseClient;
import com.hafizhamza.roamdatabase.Room.MyDatabase;
import com.hafizhamza.roamdatabase.Room.Student;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
RecyclerView rv;
ImageView addnew;
    MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.rv);

        database= Room.databaseBuilder(MainActivity.this,MyDatabase.class,"StudentDb").allowMainThreadQueries().build();
new getdata().execute();
        addnew=(ImageView) findViewById(R.id.insert);
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SubmitRecord.class));
                finish();
            }
        });
    }
    public class getdata extends AsyncTask<Void,Void, List<Student>>
    {
        @Override
        protected List<Student> doInBackground(Void... voids) {
            List<Student> student=DatabaseClient.getInstance(getApplicationContext()).getMyDatabase().dao().getdata();
            return student;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            Log.d("GETDATA",students.get(0).getFname().toString());
            StudentAdapter adapter=new StudentAdapter(MainActivity.this,students);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            rv.setLayoutManager(layoutManager);
            rv.setNestedScrollingEnabled(false);
            rv.setHorizontalScrollBarEnabled(true);
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setAdapter(adapter);
        }
    }
}
