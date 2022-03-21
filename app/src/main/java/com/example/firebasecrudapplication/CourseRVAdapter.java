package com.example.firebasecrudapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {
    private ArrayList<CourseRVModal> courseRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private CourseClickInterface courseClickInterface;

    public CourseRVAdapter(ArrayList<CourseRVModal> courseRVModalArrayList, Context context, int lastPos, CourseClickInterface courseClickInterface) {
        this.courseRVModalArrayList = courseRVModalArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    public CourseRVAdapter(ArrayList<CourseRVModal> courseRVModalArrayList, MainActivity mainActivity, MainActivity mainActivity1) {
        
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CourseRVModal courseRVModal = courseRVModalArrayList.get(position);
        holder.courseNameTV.setText(courseRVModal.getCourseName());
        holder.coursePriceTV.setText("Rs, "+courseRVModal.getCoursePrice());
        Picasso.get().load(courseRVModal.getCourseImg()).into(holder.courseIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseClickInterface.onCourseClick(position);
            }
        });

    }
    private void setAnimation(View itemView, int position){
        if (position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(itemView.getContext(), android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
//        return courseRVModalArrayList.size();
        try{ return courseRVModalArrayList.size();}
        catch (Exception e){
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseNameTV, coursePriceTV;
        private ImageView courseIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            coursePriceTV = itemView.findViewById(R.id.idTVPrice);
            courseIV = itemView.findViewById(R.id.idIVCourse);


        }
    }
    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}
