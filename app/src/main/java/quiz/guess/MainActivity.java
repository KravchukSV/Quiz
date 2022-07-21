package quiz.guess;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.billingclient.api.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    Dialog dialog;

    MediaPlayer click_button;

    private RewardedAd rewardedAd; //временное отключение реклами
    static private boolean close_ads = true;
    static private boolean close_ads_forever = true;

    //даные для покупки - начало
    private BillingClient mBillingClient;
    private Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();
    private String mSkuId = "help1";
    //даные для покупки конец

    static private int counterMod = 1;
    static private int massMod[] = {R.string.standart_mode, R.string.time_mode, R.string.survival_mode};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterMod = 1;
        close_ads_forever = true;
        loadAd();
        initBilling();

        click_button = MediaPlayer.create(this, R.raw.click_button);

        //кнопка старт - начало
        Button start = (Button)findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    click_button.start();
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        //кнопка страрт - конец

        //кнопка выбор режима - начало
        final Button mode = (Button)findViewById(R.id.mode);
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counterMod < 3){
                    counterMod++;
                }
                else{
                    counterMod = 1;
                }
                System.out.println(counterMod);
                System.out.println(massMod[counterMod-1]);
                mode.setText(massMod[counterMod-1]);
            }
        });
        //кнопка выбор режма - конец

        //кнопка об игре - начало
        Button about_game = (Button)findViewById(R.id.about_game);
        about_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                Intent intent = new Intent(MainActivity.this, AboutGame.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slidein, R.anim.slideout);
                finish();
            }
        });
        // кнопка об игре - конец

        //блокировка реклами - начало
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок
        dialog.setContentView(R.layout.block_ads);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачний фон диалогового окна
        dialog.setCancelable(false);

        ImageView close_ads = (ImageView)findViewById(R.id.close_ads);
        close_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        //кнопка закритея диалогового окна - начало
        TextView button_close_block_ads = (TextView)dialog.findViewById(R.id.button_close_block_ads);
        button_close_block_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_button.start();
                dialog.dismiss();
            }
        });
        //кнопка закритея диалогового окна - конец

        //временное отключение - начало
        Button temporary_disabling_ads = (Button)dialog.findViewById(R.id.temporary_disabling_ads);
        temporary_disabling_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAd();
            }
        });
        //временное отключение - конец

        //поддержка розработчика - начало
        Button developer_support = (Button)dialog.findViewById(R.id.developer_support);
        developer_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBilling(mSkuId);
            }
        });
        //поддержка розработчика - начало

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void loadAd(){
        this.rewardedAd = new RewardedAd(this,  "ca-app-pub-4650655413827315/6350071802");
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
                close_ads = false;
                dialog.dismiss();
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

    public static boolean isClose_ads() {
        return close_ads;
    }

    public static boolean isClose_ads_forever() {
        return close_ads_forever;
    }

    public void initBilling(){
        mBillingClient = BillingClient.newBuilder(this).setListener(new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
                if(responseCode == BillingClient.BillingResponse.OK && purchases != null){

                }
            }
        }).build();

        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    //здесь мы можем запросить информацию о товарах и покупках
                    querySkuDetails(); //запрос о товарах
                    List<Purchase> purchasesList = queryPurchases(); //запрос о покупках

                    //если товар уже куплен, предоставить его пользователю
                    for (int i = 0; i < purchasesList.size(); i++) {
                        String purchaseId = purchasesList.get(i).getSku();
                        if(TextUtils.equals(mSkuId, purchaseId)) {
                            payComplete();
                        }
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }

    //запрос информации о товарах - начало
    private void querySkuDetails() {
        SkuDetailsParams.Builder skuDetailsParamsBuilder = SkuDetailsParams.newBuilder();
        List<String> skuList = new ArrayList<>();
        skuList.add(mSkuId);
        skuDetailsParamsBuilder.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        mBillingClient.querySkuDetailsAsync(skuDetailsParamsBuilder.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                if (responseCode == 0) {
                    for (SkuDetails skuDetails : skuDetailsList) {
                        mSkuDetailsMap.put(skuDetails.getSku(), skuDetails);
                    }
                }
            }
        });
    }
    //запрос информации о товарах - конец

    //запрос на покупку - начало
    public void launchBilling(String skuId) {
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(mSkuDetailsMap.get(skuId))
                .build();
        mBillingClient.launchBillingFlow(this, billingFlowParams);
    }
    //запрос на покупку - конец

    //результат покупки - начало
    public void payComplete(){
        close_ads_forever = false;
        ImageView close_ads = (ImageView)findViewById(R.id.close_ads);
        close_ads.setVisibility(View.INVISIBLE);
    }
    //результат покупки - конец

    //информация о покупки - начало
    private List<Purchase> queryPurchases() {
        Purchase.PurchasesResult purchasesResult = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
        return purchasesResult.getPurchasesList();
    }
    //информация о покупки - конец


    public static int getCounterMod() {
        return counterMod;
    }
}