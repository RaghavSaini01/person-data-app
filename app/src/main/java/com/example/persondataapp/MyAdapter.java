package com.example.persondataapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Person> dataModelArrayList;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, age, school;

        public MyViewHolder(View itemView) {
            super(itemView);

            name =  itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            school = itemView.findViewById(R.id.school);
        }

    }

    public MyAdapter(Context ctx, ArrayList<Person> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycleview, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {

        holder.name.setText(dataModelArrayList.get(position).getName());
        holder.age.setText(dataModelArrayList.get(position).getAge());
        holder.school.setText(dataModelArrayList.get(position).getSchool());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}

