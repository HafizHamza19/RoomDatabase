package com.hafizhamza.roamdatabase.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.hafizhamza.roamdatabase.MainActivity;
import com.hafizhamza.roamdatabase.R;
import com.hafizhamza.roamdatabase.Room.DatabaseClient;
import com.hafizhamza.roamdatabase.Room.MyDatabase;
import com.hafizhamza.roamdatabase.Room.Student;
import com.hafizhamza.roamdatabase.SubmitRecord;

import java.util.List;
import java.util.logging.Handler;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    List<Student> studentList;
    Context context;
    public StudentAdapter(MainActivity mainActivity, List<Student> students) {
    this.context=mainActivity;
    this.studentList=students;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liststyle, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
            Student student=studentList.get(position);
            holder.fname.setText(student.getFname());
        holder.lname.setText(student.getLname());
        holder.stdclass.setText(student.getStdclass());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fname,lname,stdclass;
        public RelativeLayout layout;
        CardView cardView;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
                layout=(RelativeLayout)itemView.findViewById(R.id.listrelative);
                cardView=(CardView)itemView.findViewById(R.id.card_view);
                fname=(TextView)itemView.findViewById(R.id.fname);
            lname=(TextView)itemView.findViewById(R.id.lname);
            stdclass=(TextView)itemView.findViewById(R.id.stdclass);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, SubmitRecord.class);
                    intent.putExtra("update","1");
                    intent.putExtra("stdid",String.valueOf(studentList.get(getAdapterPosition()).getStdid()));
                    intent.putExtra("fname",studentList.get(getAdapterPosition()).getFname());
                    intent.putExtra("lname",studentList.get(getAdapterPosition()).getLname());
                    intent.putExtra("stdclass",studentList.get(getAdapterPosition()).getStdclass());
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });
            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                //this method not allowon main thread
                    //    DatabaseClient.getInstance(context).getMyDatabase().dao().deleteStu(studentList.get(getAdapterPosition()).getStdid());
                    MyDatabase database;
                    database= Room.databaseBuilder(context, MyDatabase.class,"StudentDb").allowMainThreadQueries().build();
                    database.dao().deleteStu(studentList.get(getAdapterPosition()).getStdid());
                    notifyDataSetChanged();
                    return true;
                }
            });
        }
    }
}
