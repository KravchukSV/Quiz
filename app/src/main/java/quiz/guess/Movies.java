package quiz.guess;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;


public class Movies extends AppCompatActivity {

    // список картинок фильмов - начало
    static private String list_image_movie[] = {"12_years_a_slave", "2012-", "a_beautiful_mind", "aladdin", "alice_in_wonderland",
            "alien", "alita_battle_angel", "american_history_X", "ant_man", "ant_man_and_the_wasp", "aquaman",
            "avatar", "avengers_age_of_ultron", "avengers_endgame", "avengers_infinity_war", "back_to_the_future",
            "batman_begins", "batman_vs_superman_dawn_of_justice", "beauty_and_the_beast", "black_panther",
            "bohemian_rhapsody", "captain_america_civil_war", "captain_america_the_winter_soldier", "captain_america_the_first_avenger",
            "captain_marvel", "chappie", "charlie_and_the_chocolate_factory", "creed", "deadpool",
            "deadpool2", "die_hard", "django_unchained", "doctor_strange", "e_t_the_extra_terrestrial",
            "fantastic_beasts_and_where_to_find_them", "fight_club", "ford_vs_ferrari" ,"forrest_gump", "furious7",
            "gladiator", "goodfellas", "green_book", "guardians_of_the_galaxy", "guardians_of_the_galaxy_vol2",
            "hachi_a_dogs_tale", "harry_potter_and_the_chamber_of_secrets", "harry_potter_and_the_goblet_of_fire",
            "harry_potter_and_the_prisoner_of_azkaban", "hellboy", "i_am_legend", "inception", "independence_day",
            "indiana_jones_and_the_kingdom_of_the_crystal_skull", "inglourious_basterds", "interstellar", "iron_man",
            "iron_man2", "iron_man3", "it", "john_wick", "joker", "jumanji_welcome_to_the_jungle", "jurassic_park",
            "jurassic_world", "lock_stock_and_two_smoking", "logan", "mad_max_fury_road", "maleficent", "million_dollar_baby",
            "mission_impossible_fallout", "never_back_down", "pain_gain", "passengers", "peaceful_warrior", "pirates_of_the_Caribbean_at_worlds_end",
            "pirates_of_the_caribbean_dead_mans_chest", "pirates_of_the_caribbean_dead_men_tell_no_tales", "pirates_of_the_caribbean_on_stranger_tides",
            "psycho", "pulp_fiction", "ready_player_one", "real_steel", "requiem_for_a_dream", "reservoir_dogs", "rocky", "rogue_one_a_star_wars_story",
            "rush", "saving_private_ryan", "schindlers_list", "seven", "shutter_island", "skyfall", "snatch", "sonic_the_hedgehog", "spectre",
            "spider_man", "spider_man2", "spider_man3", "spider_man_far_from_home", "spider_man_homecoming", "star_wars_episode_I_the_phantom_menace",
            "star_wars_episode_III_revenge_of_the_sith", "star_wars_the_force_awakens", "star_wars_the_last_jedi", "star_wars_the_rise_of_skywalker",
            "step_up", "suicide_squad", "terminator2_judgment_day", "the_avengers", "the_big_lebowski", "the_dark_knight", "the_dark_knight_rises",
            "the_departed", "the_fate_of_the_furious", "the_godfather", "the_good_the_bad_and_the_ugly", "the_greatest_showman", "the_green_mile",
            "the_hitmans_bodyguard", "the_hobbit_an_unexpected_journey", "the_hobbit_the_desolation_of_smaug", "the_hunger_games_catching_fire",
            "the_incredible_hulk", "the_jungle_book", "the_lion_king", "the_lord_of_the_rings_the_return_of_the_king", "the_martian", "the_mask",
            "the_matrix", "the_pianist", "the_prestige", "the_professional", "the_revenant", "the_shawshank_redemption", "the_shining", "the_terminator",
            "the_twilight_saga_breaking_dawn_part2", "the_wolf_of_wall_street", "thor", "thor_ragnarok", "titanic", "transformers_age_of_extinction",
            "transformers_revenge_of_the_fallen", "v_for_vendetta", "venom", "warcraft", "warrior", "whiplash", "wolf_warrior2", "wonder_woman", "yes_man"};
    // список картинок фильмов - конец
    // список названий фильмов - начало
    static private int list_id_movies[] = {R.string.a12_years_a_slave, R.string.a2012, R.string.a_beautiful_mind, R.string.aladdin, R.string.alice_in_wonderland,
            R.string.alien, R.string.alita_battle_angel, R.string.american_history_X, R.string.ant_man, R.string.ant_man_and_the_wasp, R.string.aquaman,
            R.string.avatar, R.string.avengers_age_of_ultron, R.string.avengers_endgame, R.string.avengers_infinity_war, R.string.back_to_the_future,
            R.string.batman_begins, R.string.batman_vs_superman_dawn_of_justice, R.string.beauty_and_the_beast, R.string.black_panther,
            R.string.bohemian_rhapsody, R.string.captain_america_civil_war, R.string.captain_america_the_winter_soldier, R.string.captain_america_the_first_avenger,
            R.string.captain_marvel, R.string.chappie, R.string.charlie_and_the_chocolate_factory, R.string.creed, R.string.deadpool,
            R.string.deadpool2, R.string.die_hard, R.string.django_unchained, R.string.doctor_strange, R.string.e_t_the_extra_terrestrial,
            R.string.fantastic_beasts_and_where_to_find_them, R.string.fight_club, R.string.ford_vs_ferrari, R.string.forrest_gump, R.string.furious7,
            R.string.gladiator, R.string.goodfellas, R.string.green_book, R.string.guardians_of_the_galaxy, R.string.guardians_of_the_galaxy_vol2,
            R.string.hachi_a_dogs_tale, R.string.harry_potter_and_the_chamber_of_secrets, R.string.harry_potter_and_the_goblet_of_fire,
            R.string.harry_potter_and_the_prisoner_of_azkaban, R.string.hellboy, R.string.i_am_legend, R.string.inception, R.string.independence_day,
            R.string.indiana_jones_and_the_kingdom_of_the_crystal_skull, R.string.inglourious_basterds, R.string.interstellar, R.string.iron_man,
            R.string.iron_man2, R.string.iron_man3, R.string.it, R.string.john_wick, R.string.joker, R.string.jumanji_welcome_to_the_jungle, R.string.jurassic_park,
            R.string.jurassic_world, R.string.lock_stock_and_two_smoking, R.string.logan, R.string.mad_max_fury_road, R.string.maleficent, R.string.million_dollar_baby,
            R.string.mission_impossible_fallout, R.string.never_back_down, R.string.pain_gain, R.string.passengers, R.string.peaceful_warrior, R.string.pirates_of_the_Caribbean_at_worlds_end,
            R.string.pirates_of_the_caribbean_dead_mans_chest, R.string.pirates_of_the_caribbean_dead_men_tell_no_tales, R.string.pirates_of_the_caribbean_on_stranger_tides,
            R.string.psycho, R.string.pulp_fiction, R.string.ready_player_one, R.string.real_steel, R.string.requiem_for_a_dream, R.string.reservoir_dogs, R.string.rocky, R.string.rogue_one_a_star_wars_story,
            R.string.rush, R.string.saving_private_ryan, R.string.schindlers_list, R.string.seven, R.string.shutter_island, R.string.skyfall, R.string.snatch, R.string.sonic_the_hedgehog, R.string.spectre,
            R.string.spider_man, R.string.spider_man2, R.string.spider_man3, R.string.spider_man_far_from_home, R.string.spider_man_homecoming, R.string.star_wars_episode_I_the_phantom_menace,
            R.string.star_wars_episode_III_revenge_of_the_sith, R.string.star_wars_the_force_awakens, R.string.star_wars_the_last_jedi, R.string.star_wars_the_rise_of_skywalker,
            R.string.step_up, R.string.suicide_squad, R.string.terminator2_judgment_day, R.string.the_avengers, R.string.the_big_lebowski, R.string.the_dark_knight, R.string.the_dark_knight_rises,
            R.string.the_departed, R.string.the_fate_of_the_furious, R.string.the_godfather, R.string.the_good_the_bad_and_the_ugly, R.string.the_greatest_showman, R.string.the_green_mile,
            R.string.the_hitmans_bodyguard, R.string.the_hobbit_an_unexpected_journey, R.string.the_hobbit_the_desolation_of_smaug, R.string.the_hunger_games_catching_fire,
            R.string.the_incredible_hulk, R.string.the_jungle_book, R.string.the_lion_king, R.string.the_lord_of_the_rings_the_return_of_the_king, R.string.the_martian, R.string.the_mask,
            R.string.the_matrix,R.string.the_pianist, R.string.the_prestige, R.string.the_professional, R.string.the_revenant, R.string.the_shawshank_redemption, R.string.the_shining, R.string.the_terminator,
            R.string.the_twilight_saga_breaking_dawn_part2, R.string.the_wolf_of_wall_street, R.string.thor, R.string.thor_ragnarok, R.string.titanic, R.string.transformers_age_of_extinction,
            R.string.transformers_revenge_of_the_fallen, R.string.v_for_vendetta, R.string.venom, R.string.warcraft, R.string.warrior, R.string.whiplash, R.string.wolf_warrior2, R.string.wonder_woman, R.string.yes_man};
    // список названий фильмов - конец

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);

        if(MainActivity.getCounterMod() == 1){
            Common.set_list(list_image_movie);
            Common.set_id(list_id_movies);
            Common.set_directory("movies/");
            Common.set_number_directory(0);

            try {
                Intent intent = new Intent(Movies.this, Common.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else if(MainActivity.getCounterMod() == 2){
            CommonTime.set_list(list_image_movie);
            CommonTime.set_id(list_id_movies);
            CommonTime.set_directory("movies/");
            CommonTime.set_number_directory(0);

            try {
                Intent intent = new Intent(Movies.this, CommonTime.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }
        else{
            CommonSurvival.set_list(list_image_movie);
            CommonSurvival.set_id(list_id_movies);
            CommonSurvival.set_directory("movies/");
            CommonSurvival.set_number_directory(0);

            try {
                Intent intent = new Intent(Movies.this, CommonSurvival.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }
        }

        // развернуть на весь екран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // развернуть на весь екран - конец
    }

    public static String[] getList_image_movie() {
        return list_image_movie;
    }

    public static int[] getList_id_movies() {
        return list_id_movies;
    }
}