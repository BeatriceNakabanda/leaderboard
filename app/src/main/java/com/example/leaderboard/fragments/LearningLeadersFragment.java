package com.example.leaderboard.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.leaderboard.R;

public class LearningLeadersFragment extends Fragment {

    public LearningLeadersFragment(){
        Log.i("Fragment check", "LearningLeadersFragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false);
    }
}
