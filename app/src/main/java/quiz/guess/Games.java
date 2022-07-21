package quiz.guess;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class Games extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);

        // список картинок фильмов - начало
        String list_image_games[] = {"assassins_creed", "batman_arkham", "bioshock", "borderlands", "bulletstorm", "call_of_duty", "civilization", "crysis",
                "dark_souls", "darksiders", "dead_space", "deadpool", "death_stranding", "deus_ex", "devil_may_cry", "dishonored", "doom", "duke_nukem_forever",
                "dying_light", "fallout", "far_cry", "gears_of_war", "god_of_war", "gothic", "grand_theft_auto", "half_life", "hitman", "horizon_zero_dawn",
                "injustice", "just_cause", "left4_dead", "limbo", "mad_max", "mafia", "mass_effect", "max_payne", "metal_gear_solid", "metro", "mortal_kombat",
                "need_for_speed", "outlast", "plants_vs_zombies", "playerunknowns_battlegrounds", "portal", "prey", "prince_of_persia", "prototype",
                "quantum_break", "rage", "red_dead_redemption", "resident_evil", "serious_sam", "silent_hill", "spider_man", "splinter_cell", "terraria",
                "the_elder_scrolls", "the_last_of_us", "the_witcher", "this_war_of_mine", "tomb_raider", "uncharted", "watch_dogs", "wolfenstein", "world_of_tanks"};
        // список картинок фильмов - конец
        // список названий фильмов - начало
        int list_id_games[] = {R.string.assassins_creed, R.string.batman_arkham,R.string.bioshock, R.string.borderlands, R.string.bulletstorm, R.string.call_of_duty, R.string.civilization, R.string.crysis,
                R.string.dark_souls, R.string.darksiders, R.string.dead_space, R.string.deadpool_g, R.string.death_stranding, R.string.deus_ex, R.string.devil_may_cry, R.string.dishonored, R.string.doom, R.string.duke_nukem_forever,
                R.string.dying_light, R.string.fallout, R.string.far_cry, R.string.gears_of_war, R.string.god_of_war, R.string.gothic, R.string.grand_theft_auto, R.string.half_life, R.string.hitman, R.string.horizon_zero_dawn,
                R.string.injustice, R.string.just_cause, R.string.left4_dead, R.string.limbo, R.string.mad_max, R.string.mafia, R.string.mass_effect, R.string.max_payne, R.string.metal_gear_solid, R.string.metro, R.string.mortal_kombat,
                R.string.need_for_speed, R.string.outlast, R.string.plants_vs_zombies, R.string.playerunknowns_battlegrounds, R.string.portal, R.string.prey, R.string.prince_of_persia, R.string.prototype,
                R.string.quantum_break, R.string.rage, R.string.red_dead_redemption, R.string.resident_evil, R.string.serious_sam, R.string.silent_hill, R.string.spider_man_g, R.string.splinter_cell, R.string.terraria,
                R.string.the_elder_scrolls, R.string.the_last_of_us, R.string.the_witcher_g, R.string.this_war_of_mine, R.string.tomb_raider, R.string.uncharted, R.string.watch_dogs, R.string.wolfenstein, R.string.world_of_tanks};
        // список названий фильмов - конец


        if(MainActivity.getCounterMod() == 1){
            Common.set_list(list_image_games);
            Common.set_id(list_id_games);
            Common.set_directory("games/");
            Common.set_number_directory(3);

            try {
                Intent intent = new Intent(Games.this, Common.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else if(MainActivity.getCounterMod() == 2){
            CommonTime.set_list(list_image_games);
            CommonTime.set_id(list_id_games);
            CommonTime.set_directory("games/");
            CommonTime.set_number_directory(3);

            try {
                Intent intent = new Intent(Games.this, CommonTime.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else{
            CommonSurvival.set_list(list_image_games);
            CommonSurvival.set_id(list_id_games);
            CommonSurvival.set_directory("games/");
            CommonSurvival.set_number_directory(3);

            try {
                Intent intent = new Intent(Games.this, CommonSurvival.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}