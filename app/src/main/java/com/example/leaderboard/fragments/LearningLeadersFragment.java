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
import com.example.leaderboard.adapters.LearningLeadersListAdapter;
import com.example.leaderboard.models.LearningLeader;
import com.example.leaderboard.services.ApiClient;
import com.example.leaderboard.services.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment {

    List<LearningLeader> learningLeaderList;
    RecyclerView recyclerView;
    LearningLeadersListAdapter learningLeadersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        learningLeaderList = new ArrayList<>();
        recyclerView = (RecyclerView)view.findViewById(R.id.learnersRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        learningLeadersAdapter = new LearningLeadersListAdapter(getContext(), learningLeaderList);
        recyclerView.setAdapter(learningLeadersAdapter);

        //Consume the REST Web service
        ApiInterface apiService = ApiClient.buildService(ApiInterface.class);
        Call<List<LearningLeader>> learningLeaderRequest = apiService.getLearningLeaders();

        learningLeaderRequest.enqueue(new Callback<List<LearningLeader>>() {
            @Override
            public void onResponse(Call<List<LearningLeader>> call, Response<List<LearningLeader>> response) {
                learningLeaderList = response.body();
                learningLeadersAdapter.setLearningLeaderList(learningLeaderList);
            }

            @Override
            public void onFailure(Call<List<LearningLeader>> call, Throwable t) {
                Log.i("Response check", "Failed to retrieve learners");
                Toast.makeText(view.getContext(), "Failed to retrieve ideas", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
