package com.example.vijaygarg.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int val[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int turn = 0;
    boolean gameactive = true;
    int arr[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.pic1);
        img2 = findViewById(R.id.pic2);
        img3 = findViewById(R.id.pic3);
        img4 = findViewById(R.id.pic4);
        img5 = findViewById(R.id.pic5);
        img6 = findViewById(R.id.pic6);
        img7 = findViewById(R.id.pic7);
        img8 = findViewById(R.id.pic8);
        img9 = findViewById(R.id.pic9);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);
        img9.setOnClickListener(this);
        Button btn = findViewById(R.id.playagain);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView card = findViewById(R.id.cardview);
                card.setVisibility(View.GONE);
                gameactive=true;
                turn = 0;
                for (int i = 0; i < val.length; i++) {
                    val[i] = 2;
                }
                GridLayout grid = findViewById(R.id.gridlayout);
                for (int i = 0; i < grid.getChildCount(); i++) {
                    ((ImageView) grid.getChildAt(i)).setImageResource(0);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        ImageView img = (ImageView) view;

        int tagval = Integer.parseInt(img.getTag().toString());
        if (val[tagval - 1] == 2 && gameactive) {
            img.setTranslationY(-1000f);
            img.setTranslationX(-1000f);
            val[tagval-1]=turn;
            if (turn == 0) {

                img.setImageResource(R.drawable.red);
                turn = 1;
            } else if (turn == 1) {

                img.setImageResource(R.drawable.yellow);
                turn = 0;
            }
            img.animate().translationYBy(1000f).translationXBy(1000f).setDuration(500);
            boolean result=false;
            for (int arr1[] : arr) {
                if (val[arr1[0]] == val[arr1[1]] && val[arr1[1]] == val[arr1[2]] && val[arr1[0]] != 2) {
                    result=true;
                    CardView card = findViewById(R.id.cardview);
                    card.setVisibility(View.VISIBLE);
                    if (turn == 0) {
                        ((TextView) card.findViewById(R.id.winner)).setText("YELLOW WINS");
                    } else {
                        ((TextView) card.findViewById(R.id.winner)).setText("RED WINS");
                    }
                    gameactive = false;
                }
            }
            if(result==false) {
                boolean gameover = true;
                for (int i = 0; i < val.length; i++) {
                    if (val[i] == 2) {
                        gameover = false;
                        break;
                    }
                }
                if (gameover) {
                    CardView card = findViewById(R.id.cardview);
                    card.setVisibility(View.VISIBLE);
                    ((TextView) card.findViewById(R.id.winner)).setText("Match Draw ");
                }
            }

        }

    }
}
