package org.golanamit.rockpaperscizors;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class RockPaperScizors extends AppCompatActivity implements View.OnClickListener {

    ImageView rightIv, leftIv;
    TextView rightTv, leftTv;
    Button startRollBtn, fromScratchBtn;
    Random rand = new Random();
    int[] startPics, leftHandPics, rightHandPics;
    int leftPoints, rightPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scizors);

        init();

        setListeners();

        baseStart();
    }

    private void init() {
        rightIv = findViewById(R.id.ivRightId);
        leftIv = findViewById(R.id.ivLeftId);
        rightTv = findViewById(R.id.tvRightId);
        leftTv = findViewById(R.id.tvLeftId);
        startRollBtn = findViewById(R.id.startRollingBtnId);
        fromScratchBtn = findViewById(R.id.fromScratchBtnId);
        startPics = new int[] {
                R.drawable.rock, R.drawable.paper, R.drawable.scizors
        };
        leftHandPics = new int[] {
                R.drawable.left_hand_rock, R.drawable.left_hand_paper, R.drawable.left_hand_scizors
        };
        rightHandPics = new int[] {
                R.drawable.right_hand_rock, R.drawable.right_hand_paper, R.drawable.right_hand_scizors
        };
    }

    private void setListeners() {
        startRollBtn.setOnClickListener(this);
        fromScratchBtn.setOnClickListener(this);
    }

    private void baseStart() {
        int randPicIndex = rand.nextInt(startPics.length);
        rightIv.setImageResource(startPics[randPicIndex]);
        int tmp = randPicIndex;
        while (randPicIndex == tmp)
            randPicIndex = rand.nextInt(startPics.length);
        leftIv.setImageResource(startPics[randPicIndex]);
        leftPoints = 0; rightPoints = 0;
        leftTv.setText("");
        rightTv.setText("");
        //leftTv.setVisibility(View.INVISIBLE);
        //rightTv.setVisibility(View.INVISIBLE);
    }

    private void startRolling() {
        ItemEnum rightPicked, leftPicked;
        int randPicIndex = rand.nextInt(leftHandPics.length);
        leftIv.setImageResource(leftHandPics[randPicIndex]);
        leftPicked = ItemEnum.values()[randPicIndex];
        randPicIndex = rand.nextInt(leftHandPics.length);
        rightIv.setImageResource(rightHandPics[randPicIndex]);
        rightPicked = ItemEnum.values()[randPicIndex];
        boolean soundRnd = rand.nextBoolean();
        long sleepDelay = soundRnd ? 4000L : 2000L;
        MediaPlayer mp = soundRnd ? ((MediaPlayer.create(this, R.raw.rock_scizors_paper))) : (MediaPlayer.create(this, R.raw.even_shock));
        mp.start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("sleeping");
                try {
                    Thread.sleep(sleepDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finished sleeping");
            }
        });
        //leftTv.setVisibility(View.VISIBLE);
        //rightTv.setVisibility(View.VISIBLE);
        evaluate(leftPicked, rightPicked);
    }

    private void evaluate(ItemEnum leftPicked, ItemEnum rightPicked) {
        if(leftPicked.equals(rightPicked))
            return;
        if(leftPicked.equals(ItemEnum.ROCK)) {
            if(rightPicked.equals(ItemEnum.SCIZORS))
                leftPoints++;
            else
                rightPoints++;
        } else if(leftPicked.equals(ItemEnum.PAPER)) {
            if(rightPicked.equals(ItemEnum.ROCK))
                leftPoints++;
            else
                rightPoints++;
        } else if(leftPicked.equals(ItemEnum.SCIZORS)) {
            if(rightPicked.equals(ItemEnum.PAPER))
                leftPoints++;
            else
                rightPoints++;
        } else {
            System.err.println("SHOULD NEVER be here");
            return;
        }
        leftTv.setText(String.valueOf(leftPoints));
        rightTv.setText(String.valueOf(rightPoints));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == startRollBtn.getId()) {
            startRolling();
        } else if(view.getId() == fromScratchBtn.getId()) {
            baseStart();
        }
    }
}

enum ItemEnum {
    ROCK, PAPER, SCIZORS
}