package com.example.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.leaderboard.R;
import com.example.leaderboard.models.SkillIqLeader;

import java.util.List;

public class SkillIqLeadersLeadersListAdapter extends RecyclerView.Adapter<SkillIqLeadersLeadersListAdapter.MyViewHolder> {
    Context context;
    List<SkillIqLeader> skillIqLeaderList;

    public SkillIqLeadersLeadersListAdapter(Context context, List<SkillIqLeader> skillIqLeaderList) {
        this.context = context;
        skillIqLeaderList = skillIqLeaderList;
    }
    public void setSkillIqLeaderList(List<SkillIqLeader> skillIqLeaderList){
        this.skillIqLeaderList = skillIqLeaderList;
        notifyDataSetChanged();
    }

    @Override
    public SkillIqLeadersLeadersListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iq_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SkillIqLeadersLeadersListAdapter.MyViewHolder holder, int position) {
        String learnerInfo = skillIqLeaderList.get(position).getScore()+ " skill IQ Score, "
                + skillIqLeaderList.get(position).getCountry();
        holder.learnerName.setText(skillIqLeaderList.get(position).getName());
        holder.learnerDetails.setText(learnerInfo);
        Glide.with(context).load(skillIqLeaderList.get(position).getBadgeUrl())
                .apply(RequestOptions.centerCropTransform()).into(holder.learnerBadge);

    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView learnerName;
        ImageView learnerBadge;
        TextView learnerDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerName = itemView.findViewById(R.id.iqLeaderNameTextView);
            learnerDetails = itemView.findViewById(R.id.iqDetailsTextView);
            learnerBadge = itemView.findViewById(R.id.topiqLearnerImage);
        }
    }

    @Override
    public int getItemCount() {
        if(skillIqLeaderList != null){
            return skillIqLeaderList.size();
        }
        return 0;
    }
}
