package com.androiddev.roomdatabase_v2.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.roomdatabase_v2.Constants.Constants;
import com.androiddev.roomdatabase_v2.EditActivity;
import com.androiddev.roomdatabase_v2.R;
import com.androiddev.roomdatabase_v2.database.AppDatabase;
import com.androiddev.roomdatabase_v2.model.Person;

import java.util.List;

public class PersonAdaptor extends RecyclerView.Adapter<PersonAdaptor.MyViewHolder> {

    private Context context;
    private List<Person> mPersonList;

    public PersonAdaptor(Context context) {
        this.context = context;
    }

    public void setTasks(List<Person> personList){
        mPersonList = personList;
        notifyDataSetChanged();
    }
    public List<Person> getTasks() {
        return mPersonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.name.setText(mPersonList.get(position).getName());
            holder.email.setText(mPersonList.get(position).getEmail());
            holder.city.setText(mPersonList.get(position).getCity());
            holder.pinCode.setText(mPersonList.get(position).getPinCode());
            holder.number.setText(mPersonList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        if(mPersonList == null){
            return 0;
        }
        return mPersonList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,city,pinCode,number;
        ImageView editImage;
        AppDatabase mDb;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.person_name);
            email = itemView.findViewById(R.id.person_email);
            city = itemView.findViewById(R.id.person_city);
            pinCode = itemView.findViewById(R.id.person_pincode);
            number = itemView.findViewById(R.id.person_number);
            editImage =itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = mPersonList.get(getAdapterPosition()).getId();
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra(Constants.UPDATE_Person_Id,elementId);
                    context.startActivity(intent);
                }
            });

        }

    }
}
