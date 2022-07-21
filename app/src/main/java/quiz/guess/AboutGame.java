package quiz.guess;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

public class AboutGame extends AppCompatActivity {

    MediaPlayer click_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);

        show_achievement();

        click_button = MediaPlayer.create(this, R.raw.click_button);

        Button back_about_game = (Button)findViewById(R.id.back_about_game);
        back_about_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                Intent intent = new Intent(AboutGame.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(AboutGame.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein, R.anim.slideout);
            finish();
        }catch (Exception e){
        }
    }
    //системная кнопка назад - конец

    void show_achievement(){

        String point_mass [][] = {{"20", "50", "100"}, {"20", "50", "100"}, {"20", "50", "100"}, {"20", "50", "100"},
                {"20", "50", "100"}, {"20", "50", "100"}, {"10", "25", "50"}, {"10", "25", "50"}};

        int pointID[] = {R.id.movie_point, R.id.series_point, R.id.cartoons_point, R.id.games_point, R.id.quiz_point,
                R.id.trickster_point, R.id.madman_point, R.id.lucky_point};

        int starsID[][] = {{R.id.movie_star1, R.id.movie_star2, R.id.movie_star3},{R.id.series_star1, R.id.series_star2, R.id.series_star3},
                {R.id.cartoons_star1, R.id.cartoons_star2, R.id.cartoons_star3}, {R.id.games_star1, R.id.games_star2, R.id.games_star3},
                {R.id.quiz_star1, R.id.quiz_star2, R.id.quiz_star3}, {R.id.trickster_star1, R.id.trickster_star2, R.id.trickster_star3},
                {R.id.madman_star1, R.id.madman_star2, R.id.madman_star3}, {R.id.lucky_star1, R.id.lucky_star2, R.id.lucky_star3}};


        int ach[] = file_read();

        for(int i = 0; i < ach.length; i++){
            for(int j = 0; j < ach[i]; j++){
                ImageView star = (ImageView)findViewById(starsID[i][j]);
                star.setImageDrawable(getDrawable(R.drawable.star));
            }
            if(ach[i] < 3){
                TextView point = (TextView)findViewById(pointID[i]);
                point.setText(point_mass[i][ach[i]]);
            }
            else{
                TextView point = (TextView)findViewById(pointID[i]);
                point.setText(point_mass[i][ach[i]-1]);
            }
        }
    }
    //чтение файла - начало
    public int[] file_read() {
        String result_read[] = {};
        int result[] = new int[8];
        FileInputStream fis = null;
        try {
            fis = openFileInput("achievement.txt");
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader buffered = new BufferedReader(reader);
            String lines;
            while ((lines=buffered.readLine()) != null){
                System.out.println(lines.length());
                result_read = lines.split(" ");
            }
            for(int i = 0; i < result_read.length; i++){
                result[i] = Integer.parseInt(result_read[i]);
            }
        } catch (FileNotFoundException e) {
            result = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //чтение файла - конец
}