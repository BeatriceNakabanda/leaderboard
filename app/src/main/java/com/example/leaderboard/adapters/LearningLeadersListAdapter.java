package com.example.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.leaderboard.R;
import com.example.leaderboard.models.LearningLeader;

import java.util.List;

/*
    display list of learners
 */
public class LearningLeadersListAdapter extends RecyclerView.Adapter<LearningLeadersListAdapter.MyViewHolder>{
    Context context;
    List<LearningLeader> learningLeaderList;

    public LearningLeadersListAdapter(Context context, List<LearningLeader> learningLeaderList) {
        this.context = context;
        this.learningLeaderList = learningLeaderList;
    }

    public void setLearningLeaderList(List<LearningLeader> learningLeaderList){
        this.learningLeaderList = learningLeaderList;
        notifyDataSetChanged();
    }
    @Override
    public LearningLeadersListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView learnerName;
        ImageView learnerBadge;
        TextView learnerDetails;

        public MyViewHolder(View itemView) {
            super(itemView);
            learnerName = (TextView)itemView.findViewById(R.id.learnerNameTextView);
            learnerDetails = (TextView)itemView.findViewById(R.id.learnerDetailsTextView);
            learnerBadge = (ImageView)itemView.findViewById(R.id.topLearnerImage);
        }
    }

    @Override
    public void onBindViewHolder(LearningLeadersListAdapter.MyViewHolder holder, int position) {
        String learnerInfo = learningLeaderList.get(position).getHours() + " learning hours, "
                + learningLeaderList.get(position).getCountry();
        holder.learnerName.setText(learningLeaderList.get(position).getName().toString());
        holder.learnerDetails.setText(learnerInfo);
        Glide.with(context).load(learningLeaderList.get(position).getBadgeUrl())
                .apply(RequestOptions.centerCropTransform()).into(holder.learnerBadge);
    }

    @Override
    public int getItemCount() {
        if(learningLeaderList != null){
            return learningLeaderList.size();
        }
        return 0;
    }
}