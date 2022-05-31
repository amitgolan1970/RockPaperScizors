package org.golanamit.rockpaperscizors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button goMainPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setListeners();

        goForward();
    }

    private void init() {
        goMainPageBtn = findViewById(R.id.goToGameActivityId);
    }

    private void setListeners() {
        goMainPageBtn.setOnClickListener(this);
    }

    private void goForward() {
        Intent intent = new Intent(this, RockPaperScizors.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == goMainPageBtn.getId()) {
            goForward();
        }
    }
}