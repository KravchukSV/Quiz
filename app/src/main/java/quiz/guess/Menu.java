package quiz.guess;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Menu extends AppCompatActivity {

    private AdView adView;

    MediaPlayer click_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        if(MainActivity.isClose_ads_forever() && MainActivity.isClose_ads()){
            MobileAds.initialize(this, "ca-app-pub-4650655413827315~5806115085");
            adView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        click_button = MediaPlayer.create(this, R.raw.click_button);

        //кнопка фильмы - начало
        Button movies = (Button)findViewById(R.id.movies);

        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    click_button.start();
                    Intent intent = new Intent(Menu.this, Movies.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //кнопка фильмы - конец

        //кнопка сериалы - начало
        Button series = (Button)findViewById(R.id.series);

        series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    click_button.start();
                    Intent intent = new Intent(Menu.this, Series.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //кнопка сериали - конец

        //кнопка мультфильмы - начало
        Button cartoons = (Button)findViewById(R.id.cartoons);

        cartoons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    click_button.start();
                    Intent intent = new Intent(Menu.this, Cartoons.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //кнопка мультфильми - конец

        //кнопка игры - начало
        Button games = (Button)findViewById(R.id.games);

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                try {
                    Intent intent = new Intent(Menu.this, Games.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //кнопка игры - конец

        //кнопка викторина - начало
        Button quiz = (Button)findViewById(R.id.quiz);

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                try {
                    Intent intent = new Intent(Menu.this, Quiz.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //кнопка викторина - конец

        //кнопка рекорды - начало
        Button records = (Button)findViewById(R.id.records);
        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                Intent intent = new Intent(Menu.this, RecordsSlide.class);
                startActivity(intent);
                finish();
            }
        });
        //кнопка рекорды - конец
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Menu.this, MainActivity.class);
            startActivity(intent); finish();
        }catch (Exception e){
        }
    }
}