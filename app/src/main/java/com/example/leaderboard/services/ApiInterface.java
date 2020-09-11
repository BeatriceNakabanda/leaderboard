package com.example.leaderboard.services;

import com.example.leaderboard.models.LearningLeader;
import com.example.leaderboard.models.SkillIqLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/hours")
    Call<List<LearningLeader>> getLearningLeaders();

    @GET("/api/skilliq")
    Call<List<SkillIqLeader>> getSkillIqLeaders();
}
