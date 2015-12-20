package com.branchmetrics;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jitse.example.R;
import com.jitse.example.intern.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;

/**
 * For this branch metrics example to work, the API key specified must be valid. The settings at
 * https://dashboard.branch.io/?origin=website-nav-&#/settings must be pointed to a valid S3 that
 * contains the apk, and also the package name must be declared com.jitse.example
 */
public class Main2Activity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getName();
    private static final String MONSTER_NAME = "Jia Tse";
    private static final String HELLO_WORLD = "hello world";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

        Branch branch = Branch.getInstance();

// ONLY use the line below IF you ARE NOT using automatic session management.
// Branch branch = Branch.getInstance(getApplicationContext());

        branch.initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...

                    Log.d(TAG, "Parameters are passed in from branch metrics");

                    String property = referringParams.optString("monster_name", "");
                    Log.d(TAG, "property received:" + property);
                    if (HELLO_WORLD.equals(property)) {
                        startActivity(new Intent(Main2Activity.this, Branch2Activity.class));
                    }

                } else {
                    Log.i("MyApp", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    private void click2() {
        final JSONObject monsterMetadata = new JSONObject();
        try {
            monsterMetadata.put("monster_name", MONSTER_NAME);
            monsterMetadata.put("$deeplink_path", "customer");
        } catch (JSONException ex) {
            ex.printStackTrace();
        }


        // load a URL just for display on the viewer page
        Branch.getInstance(getApplicationContext()).getContentUrl("viewer", monsterMetadata, new Branch.BranchLinkCreateListener() {
            @Override
            public void onLinkCreate(String url, BranchError error) {
                Log.d(TAG, "URL to share: " + url);
            }
        });

    }

    private void click() {
        Log.d(TAG, "creating branchUniversalObject");
        BranchUniversalObject branchUniversalObject = new BranchUniversalObject()
                .setCanonicalIdentifier("content/12345")
                .setTitle("My Content Title")
                .setContentDescription("My Content Description")
                .setContentImageUrl("https://example.com/mycontent-12345.png")
                .addContentMetadata("monster_name", HELLO_WORLD)
                .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC);

        Log.d(TAG, "creating linkProperties");
        LinkProperties linkProperties = new LinkProperties()
                .setChannel("facebook")
                .setFeature("sharing")
                .addControlParameter("$deeplink_path", "customer");

        Log.d(TAG, "generateShortUrl");
        branchUniversalObject.generateShortUrl(this, linkProperties, new Branch.BranchLinkCreateListener() {
            @Override
            public void onLinkCreate(String url, BranchError error) {
                Log.d(TAG, "onLinkCreate");
                if (error == null) {
                    Log.i("MyApp", "got my Branch link to share: " + url);
                }
            }
        });

    }
}
