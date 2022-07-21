package quiz.guess;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CommonSurvival extends AppCompatActivity {

    private RewardedAd rewardedAd;
    private RewardedAd rewardedAd50;
    public InterstitialAd interstitialAd; //реклама

    Dialog dialog;
    Dialog dialog_advertising;

    MediaPlayer true_answer, false_answer, click_button;

    static String directory = "";

    static int number_directory;

    int counter_button50_50 = 1;
    int counterAd = 1; //рекла для доп жизни
    int counterAd50 = 1; //реклама для доп кнопки 50/50
    int number_i = 5; //количество картинок
    int counter = 0; // номер картинки
    int point = 0; // очки
    int mass_version[] = {R.id.version1, R.id.version2, R.id.version3, R.id.version4}; // конпки ответов
    // список картинок - начало
    static String list_image[] = {};
    // список картинок - конец

    // список названий - начало
    static int list_id[] = {};
    // список названий - конец

    Button button50;

    TextView result;
    TextView timer;
    CountDownTimer ticTac;
    long totalTime = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_time);
        //реклама начало
        loadInterstitial();
        //реклама конец

        //закритие рекламы на крестик - начало
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startTimer();
            }
        });
        //закритие рекламы на крестик - конец

        click_button = MediaPlayer.create(this, R.raw.click_button);

        //окно конец жизней - начало
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.end_heart);
        dialog.setCancelable(false);
        // окно конец жизней - конец

        //окно реклами - начало
        dialog_advertising = new Dialog(this);
        dialog_advertising.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_advertising.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_advertising.setContentView(R.layout.advertising_window);
        dialog_advertising.setCancelable(false);
        //окно реклами - конец

        final ImageView image = (ImageView)findViewById(R.id.imagen);
        final TextView points = (TextView)findViewById(R.id.points);
        final Resources res = getResources();

        true_answer = MediaPlayer.create(this, R.raw.true_answer);
        false_answer = MediaPlayer.create(this, R.raw.false_answer);

        //кнопка меню - начало
        ImageView menu = (ImageView)findViewById(R.id.main_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    click_button.start();
                    ticTac.cancel();
                    Intent intent = new Intent(CommonSurvival.this, Menu.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){

                }
            }
        });
        //кнопка меню - конец

        //загрузка реклами - начало
        loadAd();
        loadAd50();
        //загрузка реклами - конец

        //первый фильм - начало
        result = (TextView)dialog.findViewById(R.id.your_result);
        //старт отчета - начало
        timer = (TextView)findViewById(R.id.timer);
        startTimer();
        //старт отчета - конец
        final Integer[][] number_image = random(list_id.length);
        next_image(image, number_image);
        //первый фильм - конец

        //кнопка + 50/50 - начало
        final Button button_plus50_50 = (Button)findViewById(R.id.button_plus50_50);
        button_plus50_50.setAlpha(.5f);
        button_plus50_50.setClickable(false);
        button_plus50_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticTac.cancel();
                click_button.start();
                showAd50();
                button_plus50_50.setAlpha(.5f);
                button_plus50_50.setClickable(false);
            }
        });
        //кнопка + 50/50 - конец

        //кнопка 50/50 - начало
        button50 = (Button)findViewById(R.id.button50);
        final int mass50_50[] = new int[2];
        button50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                counter_button50_50--;
                for(int i = 0; i < mass_version.length; i++){
                    Button ver = (Button)findViewById(mass_version[i]);
                    if(ver.getText().equals(res.getString(list_id[number_image[counter][0]]))){
                        mass50_50[0] = i;
                    }
                }
                while (true){
                    int random_number1 = (int) (Math.random() * mass_version.length);
                    if(random_number1 != mass50_50[0]){
                        mass50_50[1] = random_number1;
                        break;
                    }
                }

                for(int i = 0; i < mass_version.length; i++){
                    Button ver = (Button)findViewById(mass_version[i]);
                    if(i != mass50_50[0] && i != mass50_50[1]){
                        ver.setClickable(false);
                        ver.setAlpha(.5f);
                        ver.setVisibility(View.INVISIBLE);
                    }
                }

                if(counter_button50_50 == 0){
                    button50.setClickable(false);
                    button50.setAlpha(0.5f);
                }
            }
        });
        //кнопка 50/50 - конец

        //обработка ответа - начало
        for(int i = 0; i < mass_version.length; i++){
            final Button ver = (Button)findViewById(mass_version[i]);
            ver.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        for(int j = 0; j < mass_version.length; j++){
                            if(mass_version[j] != ver.getId()){
                                Button button = (Button)findViewById(mass_version[j]);
                                button.setEnabled(false);
                            }
                        }
                        if(ver.getText().equals(res.getString(list_id[number_image[counter][0]]))){
                            ver.setBackgroundResource(R.drawable.style_button_true);
                            true_answer.start();
                            ticTac.cancel();
                            totalTime = 6000;
                            startTimer();

                            point++;
                        }
                        else{
                            ver.setBackgroundResource(R.drawable.style_botton_false);
                            false_answer.start();
                            ticTac.onFinish();
                        }

                    }
                    else if(event.getAction() == MotionEvent.ACTION_UP){
                        if(ver.getText().equals(res.getString(list_id[number_image[counter][0]]))){
                            points.setText(point+"");
                        }
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        ver.setBackgroundResource(R.drawable.button_press_blak_rose);

                        counter++;
                        if(counter == list_image.length*number_i){
                            counter--;
                            save_record(point);
                            save_achievement(point);
                            result.setText(point + "");
                            ticTac.cancel();
                            dialog.show();
                        }

                        //показ рекламы 12 ответов - начало
                        if(MainActivity.isClose_ads_forever() && counter%12==0 && interstitialAd.isLoaded() && MainActivity.isClose_ads()){
                            ticTac.cancel();
                            interstitialAd.show();
                            loadInterstitial();
                        }
                        //показ рекламы 12 ответов - конец

                        next_image(image, number_image);
                        for(int j = 0; j < mass_version.length; j++){
                            Button button = (Button)findViewById(mass_version[j]);
                            button.setEnabled(true);
                            button.setAlpha(1.0f);
                            button.setVisibility(View.VISIBLE);
                        }
                        if(rewardedAd50.isLoaded() && counterAd50 == 1 && !button50.isClickable()){
                            button_plus50_50.setClickable(true);
                            button_plus50_50.setAlpha(1.0f);
                        }

                    }
                    return true;
                }
            });
        }
        //обработка ответа - конец

        //кнопка повторить в диалоговом окне - начало
        Button restart = (Button)dialog.findViewById(R.id.end_heart_resum);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                Intent intent;
                switch (number_directory){
                    case 0:
                        intent = new Intent(CommonSurvival.this, Movies.class);
                        break;
                    case 1:
                        intent = new Intent(CommonSurvival.this, Series.class);
                        break;
                    case 2:
                        intent = new Intent(CommonSurvival.this, Cartoons.class);
                        break;
                    case 3:
                        intent = new Intent(CommonSurvival.this, Games.class);
                        break;
                    case 4:
                        intent = new Intent(CommonSurvival.this, Quiz.class);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + number_directory);
                }
                startActivity(intent);
                finish();
            }
        });
        //кнопка повторить в диалоговом окне - конец

        //кнопка меню в дилаговом окне - начало

        Button menu_dialog = (Button)dialog.findViewById(R.id.end_heart_menu);
        menu_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                ticTac.cancel();
                Intent intent = new Intent(CommonSurvival.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });
        //кнопка меню в дилаговом окне - конец

        // закрить окно рекламы - начало
        TextView close_advertising = (TextView)dialog_advertising.findViewById(R.id.close_window_andvertising);
        close_advertising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                dialog_advertising.dismiss();
                dialog.show();
            }
        });
        // закрить окно рекламы - конец

        //смотреть рекламу - начало
        Button show_ad = (Button)dialog_advertising.findViewById(R.id.button_andvertising);
        show_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                showAd();
            }
        });
        //смотреть рекламу - конец

        // развернуть на весь екран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // развернуть на весь екран - конец
    }

    //системная кнопка назад - начало
    @Override
    public void onBackPressed(){
        try {
            ticTac.cancel();
            Intent intent = new Intent(CommonSurvival.this, Menu.class);
            startActivity(intent); finish();
        }catch (Exception e){
        }
    }
    //системная кнопка назад - конеце
    //    картинки из папки assets - начало
    public Drawable getDrawableFromAssets(String path) {
        Drawable img = null;
        InputStream inputStream = null;
        try {
            inputStream = getApplicationContext().getAssets().open(path);
            img = Drawable.createFromStream(inputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if(inputStream!=null)
                    inputStream.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return img;
    }
    // получаем картинки из папки assets - конец

    //получаем масив рандомных чисел - начало
    public Integer[][] random(int max){
        int a = 2;
        Integer mass_number[][] = new Integer[max*number_i][a];
        Integer random_number[] = new Integer[max*number_i];

        for(int i = 0; i < number_i*max; i++){
            for(int j = 0; j < max; j++){
                for(int k = 0; k < number_i; k++){
                    mass_number[i][0] = j;
                    mass_number[i][1] = k+1;
                    random_number[i]=i;
                    i++;
                }
            }
        }

        Collections.shuffle(Arrays.asList(random_number));

        Integer new_mass_number[][] = new Integer[max*number_i][a];

        for(int i = 0; i < max*number_i; i++){
            new_mass_number[i] = mass_number[random_number[i]];
        }

        return new_mass_number;
    }
    //получаем масив рандомных чисел - конец

    //рандомное место ответа - начало
    public Integer[] random_button(int true_version, int max){
        Random randomGenerator = new Random();

        Integer num[] = new Integer[mass_version.length];
        num[0] = true_version;
        int i = 1;
        while (i < mass_version.length){
            int rand = randomGenerator.nextInt(max);
            boolean is_ok = true;
            for(int j = 0; j < i; j++){
                if(num[j] == rand){
                    is_ok = false;
                }
            }
            if(is_ok){
                num[i] = rand;
                i++;
            }
        }

        Collections.shuffle(Arrays.asList(num));

        return num;
    }
    //рандомное место ответа - конец

    //показ картинки и ответов - начало
    public void next_image(ImageView image, Integer[][] number_image){
        image.setImageDrawable(getDrawableFromAssets(directory + list_image[number_image[counter][0]] + number_image[counter][1] + ".jpg"));

        Integer number_button[] = random_button(number_image[counter][0], list_id.length);

        for(int i = 0; i < number_button.length; i++){
            Button ver = (Button)findViewById(mass_version[i]);
            ver.setText(list_id[number_button[i]]);
        }

    }
    //показ картинки и ответов - конец

    //сохраняем рекорд - начало
    public void save_record(int correct_answer){
        String s = file_read();
        String[] str_stat = s.split(" ");
        int statistic[][] = new int[5][6];

        for(int i = 0; i < statistic.length; i++){
            for(int j = 0; j < 6; j++){
                statistic[i][j] = Integer.parseInt(str_stat[i*6+j]);
            }
        }
        for(int i = 0; i <= 4; i++){
            if(statistic[number_directory][i] < correct_answer){

                int help[] = new int[4-i];

                for(int j = i+1; j <= 4; j++){
                    help[j-i-1] = statistic[number_directory][j-1];
                }

                for(int j = i+1; j <= 4; j++){
                    statistic[number_directory][j] = help[j-i-1];
                }

                statistic[number_directory][i] = correct_answer;
                break;
            }
        }
        statistic[number_directory][5] += correct_answer;

        file_write(statistic);
    }
    //сохраняем рекорд - конец

    //запись файл - начало
    public void file_write(int[][] data){
        try {
            FileOutputStream fos = openFileOutput("statistics_survival.txt", MODE_PRIVATE);
            for(int i = 0; i < data.length; i++){
                for(int j = 0; j < data[i].length; j++){
                    fos.write(String.valueOf(data[i][j]).getBytes());
                    if(j != data[i].length-1){
                        fos.write( " ".getBytes());
                    }
                }
                if(i != data.length-1){
                    fos.write( " ".getBytes());
                }
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //запись файл - конец

    //чтение файла - начало
    public String file_read() {
        String result = "";
        FileInputStream fis = null;
        try {
            fis = openFileInput("statistics_survival.txt");
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader buffered = new BufferedReader(reader);
            String lines;
            while ((lines=buffered.readLine()) != null){
                result +=(lines);
            }
        } catch (FileNotFoundException e) {
            int a[][] = {{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
            file_write(a);
            result =  "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //чтение файла - конец

    //сохраняем достижение - начало
    public void save_achievement(int correct_answer){
        int mass_ach[] = {10, 25, 50};
        int number_ach = 6, point_ach = 0;
        int achievement[] = file_read_achievement();

        for(int i = 0; i < mass_ach.length; i++){
            if(correct_answer >= mass_ach[i]){
                point_ach++;
            }
            else{
                if(achievement[number_ach] < point_ach){
                    achievement[number_ach] = point_ach;
                }
                break;
            }
        }

        file_write_achievement(achievement);
    }
    //сохраняем достижение - конец

    //запись файл с достижениями - начало
    public void file_write_achievement(int[] data){
        try {
            FileOutputStream fos = openFileOutput("achievement.txt", MODE_PRIVATE);

            for(int i = 0; i < data.length; i++){
                fos.write(String.valueOf(data[i]).getBytes());
                if(i != data.length-1){
                    fos.write( " ".getBytes());
                }
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //запись файл с достижениями - конец

    //чтение файла с достижениями - начало
    public int[] file_read_achievement() {
        String result_read[] = {};
        int result[] = new int[8];
        FileInputStream fis = null;
        try {
            fis = openFileInput("achievement.txt");
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader buffered = new BufferedReader(reader);
            String lines;
            while ((lines=buffered.readLine()) != null){
                result_read = lines.split(" ");
            }
            System.out.println(result_read.length);
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
    //чтение файла с достижениями - конец

    //установка даных для работи - начало
    public static void set_list(String list[]){
        list_image = list;
    }

    public static void set_id(int id[]){
        list_id = id;
    }

    public static void set_directory(String direct){
        directory = direct;
    }

    public static void set_number_directory(int num_direct){
        number_directory = num_direct;
    }
    //установка даных для работи - конец


    //загрзка и показ рекламы - начало
    public void loadAd(){
        this.rewardedAd = new RewardedAd(this,  "ca-app-pub-4650655413827315/2641003493");
        RewardedAdLoadCallback callback = new RewardedAdLoadCallback(){
            @Override
            public void onRewardedAdFailedToLoad(int i) {
                super.onRewardedAdFailedToLoad(i);
            }

            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();
            }
        };

        this.rewardedAd.loadAd(new AdRequest.Builder().build(), callback);
    }

    public void showAd(){
        RewardedAdCallback callback = new RewardedAdCallback() {
            @Override
            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                counterAd--;

                dialog_advertising.dismiss();
            }

            @Override
            public void onRewardedAdOpened() {
                super.onRewardedAdOpened();
            }

            @Override
            public void onRewardedAdClosed() {
                super.onRewardedAdClosed();
            }

            @Override
            public void onRewardedAdFailedToShow(int i) {
                super.onRewardedAdFailedToShow(i);
            }
        };
        this.rewardedAd.show(this, callback);
    }

    public void loadAd50(){
        this.rewardedAd50 = new RewardedAd(this,  "ca-app-pub-4650655413827315/6624620157");
        RewardedAdLoadCallback callback = new RewardedAdLoadCallback(){
            @Override
            public void onRewardedAdFailedToLoad(int i) {
                super.onRewardedAdFailedToLoad(i);
            }

            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();
            }
        };

        this.rewardedAd50.loadAd(new AdRequest.Builder().build(), callback);
    }

    public void showAd50(){
        RewardedAdCallback callback = new RewardedAdCallback() {
            @Override
            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                counter_button50_50++;
                counterAd50--;
                button50.setClickable(true);
                button50.setAlpha(1.0f);
            }

            @Override
            public void onRewardedAdOpened() {
                super.onRewardedAdOpened();
            }

            @Override
            public void onRewardedAdClosed() {
                super.onRewardedAdClosed();
                startTimer();
            }

            @Override
            public void onRewardedAdFailedToShow(int i) {
                super.onRewardedAdFailedToShow(i);
            }
        };
        this.rewardedAd50.show(this, callback);
    }

    public void loadInterstitial(){
        MobileAds.initialize(this, "ca-app-pub-4650655413827315~5806115085");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-4650655413827315/5911195905");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }
    //загрзка и показ рекламы - конец

    //отчет времени - начало
    public void startTimer(){
        ticTac = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = "0" + millisUntilFinished/60000 + ":";
                if(millisUntilFinished/1000-(millisUntilFinished/60000)*60 < 10){
                    time += "0";
                }
                time += (millisUntilFinished/1000-(millisUntilFinished/60000)*60);
                timer.setText(time);
                totalTime = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                save_record(point);
                save_achievement(point);
                result.setText(point+"");
                timer.setText("00:00");
                /*if(counterAd > 0 && rewardedAd.isLoaded()){
                    dialog_advertising.show();
                }
                else{
                    dialog.show();
                }*/
                dialog.show();
            }
        }.start();
    }
    //отчет времени - конец
}