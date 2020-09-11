package com.example.leaderboard.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.leaderboard.R;
import com.example.leaderboard.services.ApiInterface;
import com.example.leaderboard.services.SubmitProjectApiClient;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {
    private TextInputLayout mFirstName;
    private TextInputLayout mLastName;
    private TextInputLayout mEmail;
    private TextInputLayout mGithublink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        mFirstName = findViewById(R.id.firstName);
        mLastName = findViewById(R.id.lastName);
        mEmail = findViewById(R.id.emailAddress);
        mGithublink = findViewById(R.id.githubLink);
    }

    public void submitProject(String firstName, String lastName, String email, String link) {

        ApiInterface apiService = SubmitProjectApiClient.buildService(ApiInterface.class);
        Call<Void> submitProject = apiService.submitForm(firstName, lastName, email, link);

        submitProject.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    new AlertDialog.Builder(SubmitActivity.this)
                            .setTitle("Failed to Submit Project")
                            .setMessage(response.message() + "\n" + response.code())
                            .create()
                            .show()
                    ;
                }

                new AlertDialog.Builder(SubmitActivity.this)
                        .setTitle("Success ")
                        .setMessage("Project Submitted Successfully")
                        .create()
                        .show()
                ;

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Response check", "Failed to submit");
                new AlertDialog.Builder(SubmitActivity.this)
                        .setTitle("Failed to Submit Project")
                        .setMessage(t.getMessage())
                        .create()
                        .show()
                ;
            }
        });
    }
    public void submitProject(View view) {
        final String firstName = mFirstName.getEditText().getText().toString().trim();
        final String lastName = mLastName.getEditText().getText().toString().trim();
        final String email = mEmail.getEditText().getText().toString().trim();
        final String link = mGithublink.getEditText().getText().toString().trim();

        if (validateInputs(firstName, mFirstName) && validateInputs(lastName, mLastName)
                && validateInputs(email, mEmail) && validateInputs(link, mGithublink)) {

            new AlertDialog.Builder(this)
                    .setTitle("Are you Sure ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            submitProject(firstName, lastName, email, link);
                        }
                    })
                    .create()
                    .show()
            ;
        }
    }

    private boolean validateInputs(String value, TextInputLayout view) {
        if (value.isEmpty()) {
            view.setError("Field must not be empty");
            view.requestFocus();
            return false;
        } else {
            view.setError(null);
            return true;
        }
    }
}
