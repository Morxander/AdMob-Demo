package mal.helloadmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Init the button
        button = (Button) findViewById(R.id.button);
        // Getting the Banner Ad
        requestNewBanner();
        // Getting the Interstitial Ad
        requestNewInterstitial();
        // Button OnClick
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the Ad is loaded show it
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    goToNextActivity();
                }
            }
        });
    }

    // Go to the next Activity
    private void goToNextActivity() {
        startActivity(new Intent(this,NextActivity.class));
    }

    // Getting the Banner Ad
    private void requestNewBanner(){
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    // Getting the Interstitial Ad
    private void requestNewInterstitial() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                Toast.makeText(getApplicationContext(),"Ad Closed",Toast.LENGTH_LONG).show();
                goToNextActivity();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Toast.makeText(getApplicationContext(),"Ad Loaded",Toast.LENGTH_LONG).show();
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }
}
