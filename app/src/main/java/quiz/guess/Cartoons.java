package quiz.guess;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class Cartoons extends AppCompatActivity {
    // список картинок фильмов - начало
    static private String list_image_cartoons[] = {"aladdin", "angry_birds", "astro_boy", "avatar_the_last_airbender", "big_hero6", "bolt", "brave", "cars", "chicken_little",
            "chipn_dale_rescue_rangers", "cloudy_with_a_chance_of_meatballs", "coco", "despicable_me", "duck_tales", "finding_nemo", "free_birds", "frozen",
            "gravity_falls", "hercules", "hotel_transylvania", "how_to_train_your_dragon", "ice_age", "inside_out", "klaus", "kung_fu_panda", "lilo_stitch",
            "madagascar", "megamind", "moana", "monster_house", "monsters_vs_aliens", "monsters_inc", "my_neighbor_totoro", "open_season", "planet51", "puss_in_boots",
            "ratatouille", "rick_and_morty", "rio", "rise_of_the_guardians", "shark_tale", "shrek", "sinbad_legend_of_the_seven_seas", "spider_man_into_the_spider_verse",
            "tangled", "the_boss_baby", "the_croods", "the_good_dinosaur", "the_grinch", "the_incredibles", "the_iron_giant", "the_lion_king", "the_little_mermaid",
            "the_lorax", "the_princess_and_the_frog", "the_road_to_el_dorado", "the_secret_life_of_pets", "the_smurfs", "the_sponge_bob_square_pants_movie",
            "toy_story", "turbo", "up", "walle", "wreck_it_ralph", "zootopia"};
    // список картинок фильмов - конец
    // список названий фильмов - начало
    static private int list_id_cartoons[] = {R.string.aladdin, R.string.angry_birds, R.string.astro_boy, R.string.avatar_the_last_airbender, R.string.big_hero6, R.string.bolt, R.string.brave, R.string.cars, R.string.chicken_little,
            R.string.chipn_dale_rescue_rangers, R.string.cloudy_with_a_chance_of_meatballs, R.string.coco, R.string.despicable_me, R.string.duck_tales, R.string.finding_nemo, R.string.free_birds, R.string.frozen,
            R.string.gravity_falls, R.string.hercules, R.string.hotel_transylvania, R.string.how_to_train_your_dragon, R.string.ice_age, R.string.inside_out, R.string.klaus, R.string.kung_fu_panda, R.string.lilo_stitch,
            R.string.madagascar, R.string.megamind, R.string.moana, R.string.monster_house, R.string.monsters_vs_aliens, R.string.monsters_inc, R.string.my_neighbor_totoro, R.string.open_season, R.string.planet51, R.string.puss_in_boots,
            R.string.ratatouille, R.string.rick_and_morty, R.string.rio, R.string.rise_of_the_guardians, R.string.shark_tale, R.string.shrek, R.string.sinbad_legend_of_the_seven_seas, R.string.spider_man_into_the_spider_verse,
            R.string.tangled, R.string.the_boss_baby, R.string.the_croods, R.string.the_good_dinosaur, R.string.the_grinch, R.string.the_incredibles, R.string.the_iron_giant, R.string.the_lion_king, R.string.the_little_mermaid,
            R.string.the_lorax, R.string.the_princess_and_the_frog, R.string.the_road_to_el_dorado, R.string.the_secret_life_of_pets, R.string.the_smurfs, R.string.the_sponge_bob_square_pants_movie,
            R.string.toy_story, R.string.turbo, R.string.up, R.string.walle, R.string.wreck_it_ralph, R.string.zootopia};
    // список названий фильмов - конец
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);


        if(MainActivity.getCounterMod() == 1){
            Common.set_list(list_image_cartoons);
            Common.set_id(list_id_cartoons);
            Common.set_directory("cartoons/");
            Common.set_number_directory(2);

            try {
                Intent intent = new Intent(Cartoons.this, Common.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else if(MainActivity.getCounterMod() == 2){
            CommonTime.set_list(list_image_cartoons);
            CommonTime.set_id(list_id_cartoons);
            CommonTime.set_directory("cartoons/");
            CommonTime.set_number_directory(2);

            try {
                Intent intent = new Intent(Cartoons.this, CommonTime.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else{
            CommonSurvival.set_list(list_image_cartoons);
            CommonSurvival.set_id(list_id_cartoons);
            CommonSurvival.set_directory("cartoons/");
            CommonSurvival.set_number_directory(2);

            try {
                Intent intent = new Intent(Cartoons.this, CommonSurvival.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }



        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static String[] getList_image_cartoons() {
        return list_image_cartoons;
    }

    public static int[] getList_id_cartoons() {
        return list_id_cartoons;
    }

}