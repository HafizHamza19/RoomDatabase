package com.hafizhamza.roamdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hafizhamza.roamdatabase.Room.DatabaseClient;
import com.hafizhamza.roamdatabase.Room.Student;

import java.util.Objects;

public class SubmitRecord extends AppCompatActivity {
    EditText fname,lname,stdclass;
    LinearLayout lo;
    Button submit;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_record);
        fname =(EditText) findViewById(R.id.fname);
        lname =(EditText) findViewById(R.id.lname);
        stdclass =(EditText) findViewById(R.id.stdclass);
        lo=(LinearLayout)findViewById(R.id.linearlayout);
        submit=(Button)findViewById(R.id.button);
        try {
            Intent intent=getIntent();
            if (Objects.equals(intent.getStringExtra("update"), "1")) {
                fname.setText(intent.getStringExtra("fname"));
                lname.setText(intent.getStringExtra("lname"));
                stdclass.setText(intent.getStringExtra("stdclass"));
                id = intent.getStringExtra("stdid");
                submit.setText("Update");
            }
        }catch (Exception e)
        {
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submit.getText().toString().equals("Update"))
                {
                    new updatedata().execute();
                }
                else {
                    new savedata().execute();
                }
                // it is also a way to insert
//        Student student=new Student(fname.getText().toString(),lname.getText().toString(),stdclass.getText().toString());
//        database.dao().studentinsertion(student);
            }
        });
    }
    public class savedata extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Student student=new Student(fname.getText().toString(),lname.getText().toString(),stdclass.getText().toString());
            DatabaseClient.getInstance(getApplicationContext()).getMyDatabase().dao().studentinsertion(student);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(SubmitRecord.this, "Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SubmitRecord.this,MainActivity.class));
            finish();

        }
    }
    public class updatedata extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Student student=new Student(fname.getText().toString(),lname.getText().toString(),stdclass.getText().toString());
            DatabaseClient.getInstance(getApplicationContext()).getMyDatabase().dao().updateStu(fname.getText().toString(),lname.getText().toString(),stdclass.getText().toString(),Integer.parseInt(id));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(SubmitRecord.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SubmitRecord.this,MainActivity.class));
            finish();

        }
    }
}
