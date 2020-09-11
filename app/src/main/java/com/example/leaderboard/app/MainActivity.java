package com.example.leaderboard.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.leaderboard.R;
import com.example.leaderboard.databinding.ActivityMainBinding;
import com.example.leaderboard.fragments.LearningLeadersFragment;
import com.example.leaderboard.fragments.SkillsIQLeadersFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    //Tab titles
    private String[] titles = new String[]{"Learning Leaders", "Skill IQ Leaders"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            init();

            submitProject();
    }
    private void init(){

        //Adding custom toolbar
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));

        //Attaching tab mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(titles[position]);
                    }
                }).attach();


    }
    public void submitProject() {
        TextView submitButton = (TextView) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(intent);
            }
        });

    }

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity){
            super(fragmentActivity);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new LearningLeadersFragment();
                case 1:
                    return new SkillsIQLeadersFragment();
            }
            return new LearningLeadersFragment();
        }
    }
}