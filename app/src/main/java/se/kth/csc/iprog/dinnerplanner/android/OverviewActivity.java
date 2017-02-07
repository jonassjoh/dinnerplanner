package se.kth.csc.iprog.dinnerplanner.android;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kenneth on 2/6/17.
 */

public class OverviewActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_view);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.titlebar);

        View view = findViewById(R.id.overview_item);
        ImageView iView = (ImageView) view.findViewById(R.id.item_image);
        iView.setImageResource(R.drawable.sourdough);

        TextView tView = (TextView) view.findViewById(R.id.item_title);
        tView.setText(R.string.ingredients);

        RecyclerView overview = (RecyclerView) findViewById(R.id.overview_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        overview.setLayoutManager(linearLayoutManager);

        //MenuAdapter overviewAdapter = new MenuAdapter(this, getStarters());

        //overview.setAdapter(startersAdapter);


    }
}
