package quiz.guess;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class Quiz extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);

        //список картинок - начало
        String list_image_movies[] = Movies.getList_image_movie();
        String list_image_series[] = Series.getList_image_series();
        String list_image_cartoons[] = Cartoons.getList_image_cartoons();
        int size = list_image_movies.length + list_image_series.length + list_image_cartoons.length;
        String list_image[] = new String[size];

        for(int i = 0; i < size; i++){
            if(i < list_image_movies.length){
                list_image[i] = "movies/" + list_image_movies[i];
            }
            else if(i < list_image_movies.length+list_image_series.length){
                list_image[i] = "series/" + list_image_series[i-list_image_movies.length];
            }
            else{
                list_image[i] = "cartoons/" + list_image_cartoons[i-list_image_movies.length-list_image_series.length];
            }
        }
        //список картинок - конец

        //список названий - начало
        int list_id[] = new int[size];
        int list_id_movies[] = Movies.getList_id_movies();
        int list_id_series[] = Series.getList_id_series();
        int list_id_cartoons[] = Cartoons.getList_id_cartoons();
        for(int i = 0; i < size; i++){
            if(i < list_id_movies.length){
                list_id[i] = list_id_movies[i];
            }
            else if(i < list_id_movies.length+list_id_series.length){
                list_id[i] = list_id_series[i-list_id_movies.length];
            }
            else{
                list_id[i] = list_id_cartoons[i-list_id_movies.length-list_id_series.length];
            }
        }
        //список названий - конец


        if(MainActivity.getCounterMod() == 1){
            Common.set_list(list_image);
            Common.set_directory("");
            Common.set_id(list_id);
            Common.set_number_directory(4);

            try {
                Intent intent = new Intent(Quiz.this, Common.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else if(MainActivity.getCounterMod() == 2){
            CommonTime.set_list(list_image);
            CommonTime.set_directory("");
            CommonTime.set_id(list_id);
            CommonTime.set_number_directory(4);

            try {
                Intent intent = new Intent(Quiz.this, CommonTime.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else{
            CommonSurvival.set_list(list_image);
            CommonSurvival.set_directory("");
            CommonSurvival.set_id(list_id);
            CommonSurvival.set_number_directory(4);

            try {
                Intent intent = new Intent(Quiz.this, CommonSurvival.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}