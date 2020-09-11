package com.example.leaderboard.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboard.R;
import com.example.leaderboard.adapters.SkillIqLeadersLeadersListAdapter;
import com.example.leaderboard.models.SkillIqLeader;
import com.example.leaderboard.services.ApiClient;
import com.example.leaderboard.services.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillsIQLeadersFragment extends Fragment {
    List<SkillIqLeader> skillIqLeaderList;
    RecyclerView recyclerView;
    SkillIqLeadersLeadersListAdapter skillIqLeadersLeadersListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_skills_iq_leaders, container, false);

        skillIqLeaderList = new ArrayList<>();
        recyclerView = (RecyclerView)view.findViewById(R.id.skillsIqLearnersRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        skillIqLeadersLeadersListAdapter = new SkillIqLeadersLeadersListAdapter(getContext(), skillIqLeaderList);
        recyclerView.setAdapter(skillIqLeadersLeadersListAdapter);

        //Consume the REST Web service
        ApiInterface apiService = ApiClient.buildService(ApiInterface.class);
        Call<List<SkillIqLeader>> iqSkillLeaderRequest = apiService.getSkillIqLeaders();

        iqSkillLeaderRequest.enqueue(new Callback<List<SkillIqLeader>>() {
            @Override
            public void onResponse(Call<List<SkillIqLeader>> call, Response<List<SkillIqLeader>> response) {
                skillIqLeaderList = response.body();
                skillIqLeadersLeadersListAdapter.setSkillIqLeaderList(skillIqLeaderList);
            }

            @Override
            public void onFailure(Call<List<SkillIqLeader>> call, Throwable t) {
                Log.i("Response check", "Failed to retrieve skill iq learners");
                Toast.makeText(view.getContext(), "Failed to retrieve ideas", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
