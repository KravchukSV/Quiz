package quiz.guess;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;


import java.io.*;

public class RecordsSlide extends AppCompatActivity {

    MediaPlayer click_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_slide);

        click_button = MediaPlayer.create(this, R.raw.click_button);

        //настройка вкладок - начало
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.linearLayout);
        tabSpec.setIndicator(getResources().getString(R.string.standart_mode));
        tabHost.addTab(tabSpec);
        show_statistic("statistics.txt", (View)findViewById(R.id.r_heart));

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.linearLayout2);
        tabSpec.setIndicator(getResources().getString(R.string.time_mode));
        tabHost.addTab(tabSpec);
        show_statistic("statistics_time.txt", (View)findViewById(R.id.r_time));

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.linearLayout3);
        tabSpec.setIndicator(getResources().getString(R.string.survival_mode));
        tabHost.addTab(tabSpec);
        show_statistic("statistics_survival.txt", (View)findViewById(R.id.r_survival));
        //настройка вкладок - конец

        TabWidget tw = (TabWidget)tabHost.findViewById(android.R.id.tabs);
        for(int i = 0; i < 3; i++){
            View tabView = tw.getChildTabViewAt(i);
            TextView tv = (TextView)tabView.findViewById(android.R.id.title);
            tv.setTextSize(11);
        }

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                Intent intent = new Intent(RecordsSlide.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(RecordsSlide.this, Menu.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }

    //показ статистики - начало
    public void show_statistic(String file_records, View record_d) {
        int mass_movies[] = {R.id.movies_records1, R.id.movies_records2, R.id.movies_records3, R.id.movies_records4, R.id.movies_records5, R.id.movies_records6,
                R.id.series_records1, R.id.series_records2, R.id.series_records3, R.id.series_records4, R.id.series_records5, R.id.series_records6,
                R.id.cartoons_records1, R.id.cartoons_records2, R.id.cartoons_records3, R.id.cartoons_records4, R.id.cartoons_records5, R.id.cartoons_records6,
                R.id.games_records1, R.id.games_records2, R.id.games_records3, R.id.games_records4, R.id.games_records5, R.id.games_records6,
                R.id.quiz_records1, R.id.quiz_records2, R.id.quiz_records3, R.id.quiz_records4, R.id.quiz_records5, R.id.quiz_records6};

        String stat = file_read(file_records);
        String arr_stat[] = stat.split(" ");
        for (int i = 0; i < arr_stat.length; i++) {
            TextView record = (TextView)record_d.findViewById(mass_movies[i]);
            record.setText(arr_stat[i]);
        }


    }
    //показ статистики - конец

    //даные с файла - начало
    public String file_read(String file_records){
        String result = "";
        FileInputStream fis = null;
        try {
            fis = openFileInput(file_records);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader buffered = new BufferedReader(reader);
            String lines;
            while ((lines = buffered.readLine()) != null) {
                result += (lines);
            }
        } catch (FileNotFoundException e) {
            return "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //даные с файла - конец
}
