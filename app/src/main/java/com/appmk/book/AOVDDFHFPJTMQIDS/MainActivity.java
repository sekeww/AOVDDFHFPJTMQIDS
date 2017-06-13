package com.appmk.book.AOVDDFHFPJTMQIDS;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.backendless.Backendless;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import static com.appmk.book.AOVDDFHFPJTMQIDS.SonsAdapter.CONTENT;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "MY_TAG";
    private EditText mSonEditText;
    private Button mFindButton;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip tabs;
    private ArrayList<String> sons;
    private String mToFindText;
    private LinearLayout activity_main;
    public static String QUERY_TEXT = "";
    private int mPosition;
    private SearchView searchView;
    private MenuItem searchMenuItem;

    public static InterstitialAd interstitial;
    private int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        activity_main = (LinearLayout) findViewById(R.id.activity_main);
//        activity_main.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View view, MotionEvent ev)
//            {
//                hideKeyboard(view);
//                return false;
//            }
//        });
        Backendless.initApp(this, Konst.APP_ID,Konst.ANDROID_KEY,"v1");
        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.ads_app_id));

        AdView mAdView = (AdView) findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().addTestDevice("27B1B3B72C8B485FEA61CFA654562346").build();
        mAdView.loadAd(adRequest);

        interstitial = new InterstitialAd(getApplicationContext());
        interstitial.setAdUnitId(getResources().getString(R.string.ads_interstitialBanner_id));

        interstitial.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        // Begin loading your interstitial.
        requestNewInterstitial();

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        QUERY_TEXT = CONTENT[mViewPager.getCurrentItem()];

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });

        // Bind the tabs to the ViewPager
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //QUERY_TEXT = CONTENT[mViewPager.getCurrentItem()];
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        SonsAdapter adapter = new SonsAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        tabs.setViewPager(mViewPager);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest1 = new AdRequest.Builder().addTestDevice("27B1B3B72C8B485FEA61CFA654562346").build();
        interstitial.loadAd(adRequest1);
    }



    protected void setupParent(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                                        return false;
                }
            });
        }
        //If a layout container, iterate over children
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupParent(innerView);
            }
        }
    }

    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                onSearchRequested();
                return true;
            case android.R.id.home:
//                Intent intent = new Intent(this, SearchableDictionary.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
