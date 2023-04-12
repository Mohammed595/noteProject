package com.example.noteproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    RealmResults <Note> notesList ;

    public MyAdapter(Context context, RealmResults<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemview,parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get(position);

        holder.titleoutput.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        String creatT = DateFormat.getDateTimeInstance().format(note.createdTime);
        holder.creatTimeoutput.setText(creatT);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu menu = new PopupMenu(context, view);
                menu.getMenu().add("D");

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("D")){

                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "DOne Dlelete", Toast.LENGTH_LONG).show();
                        }
                        menu.show();
                        return true;
                    }
                });


                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    // Iner class
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleoutput ;
        TextView description ;
        TextView creatTimeoutput ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleoutput = itemView.findViewById(R.id.titleoutput);
            description = itemView.findViewById(R.id.descriptionoutput);
            creatTimeoutput = itemView.findViewById(R.id.createdTimeoutput);

        }
    }
}
