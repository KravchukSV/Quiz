package quiz.guess;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class Series extends AppCompatActivity {

    // список картинок сериалов - начало
    static private String list_image_series[] = {"agent_carter", "alf", "banshee", "black_sails", "boardwalk_empire", "breaking_bad", "castle", "charmed", "chernobyl",
            "daredevil", "defending_jacob", "desperate_housewives", "dexter", "fargo", "friends", "game_of_thrones", "gotham", "hannibal", "hercules_the_legendary_journeys",
            "house_m_d", "inhumans", "lost", "lucifer", "merlin", "misfits", "peaky_blinders", "prison_break", "rome", "scrubs", "sherlock", "smallville", "spartacus_blood_and_sand",
            "stranger_things", "supernatural", "terra_nova", "the_big_bang_theory", "the_boys", "the_flash", "the_killing", "the_man_in_the_high_castle", "the_punisher",
            "the_tudors", "the_walking_dead", "the_witcher", "the_x_files", "true_detective", "vikings", "xena_warrior_princess"};
    // список картинок сериалов - конец

    // список названий сериалов - начало
    static private int list_id_series[] = {R.string.agent_carter, R.string.alf, R.string.banshee, R.string.black_sails, R.string.boardwalk_empire, R.string.breaking_bad, R.string.castle, R.string.charmed, R.string.chernobyl,
            R.string.daredevil, R.string.defending_jacob, R.string.desperate_housewives, R.string.dexter, R.string.fargo, R.string.friends, R.string.game_of_thrones, R.string.gotham, R.string.hannibal, R.string.hercules_the_legendary_journeys,
            R.string.house_m_d, R.string.inhumans, R.string.lost, R.string.lucifer, R.string.merlin, R.string.misfits, R.string.peaky_blinders, R.string.prison_break, R.string.rome, R.string.scrubs, R.string.sherlock, R.string.smallville, R.string.spartacus_blood_and_sand,
            R.string.stranger_things, R.string.supernatural, R.string.terra_nova, R.string.the_big_bang_theory, R.string.the_boys, R.string.the_flash, R.string.the_killing, R.string.the_man_in_the_high_castle, R.string.the_punisher,
            R.string.the_tudors, R.string.the_walking_dead, R.string.the_witcher, R.string.the_x_files, R.string.true_detective, R.string.vikings, R.string.xena_warrior_princess};
    // список названий сериалов - конец

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);


        if(MainActivity.getCounterMod() == 1){
            Common.set_list(list_image_series);
            Common.set_id(list_id_series);
            Common.set_directory("series/");
            Common.set_number_directory(1);

            try {
                Intent intent = new Intent(Series.this, Common.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else if(MainActivity.getCounterMod() == 2){
            CommonTime.set_list(list_image_series);
            CommonTime.set_id(list_id_series);
            CommonTime.set_directory("series/");
            CommonTime.set_number_directory(1);

            try {
                Intent intent = new Intent(Series.this, CommonTime.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else{
            CommonSurvival.set_list(list_image_series);
            CommonSurvival.set_id(list_id_series);
            CommonSurvival.set_directory("series/");
            CommonSurvival.set_number_directory(1);

            try {
                Intent intent = new Intent(Series.this, CommonSurvival.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static String[] getList_image_series() {
        return list_image_series;
    }

    public static int[] getList_id_series() {
        return list_id_series;
    }
}