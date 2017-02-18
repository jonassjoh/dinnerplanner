package se.kth.csc.iprog.dinnerplanner.android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

/**
 * Created by kenneth on 2/6/17.
 */

public class OverviewActivity extends Activity {

    DinnerModel model;
    int cost;
    int participants;

    View marked;

    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_view);
        model = ((DinnerPlannerApplication) this.getApplication()).getModel();

        cost = (int) model.getTotalMenuPrice();
        participants = model.getNumberOfGuests();

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.titlebar);

        final View view = findViewById(R.id.overview_item);
        ImageView iView = (ImageView) view.findViewById(R.id.item_image);
        iView.setImageResource(R.drawable.sourdough);

        mark(view);

        final TextView tView = (TextView) view.findViewById(R.id.item_title);
        tView.setText(R.string.ingredients);

        final StringBuilder allIngredients = new StringBuilder();

        for(Ingredient i : model.getAllIngredients()) {
            allIngredients.append(i.getName()+" "+i.getQuantityString()+" "+i.getUnit()+"\n");
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewviewview) {
                showAllIngredients(allIngredients);
                mark(view);
            }
        });

        RecyclerView overview = (RecyclerView) findViewById(R.id.instructions);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        overview.setLayoutManager(linearLayoutManager);

        TextView costText = (TextView) findViewById(R.id.total_cost);
        costText.setText("" + cost * participants);

        showAllIngredients(allIngredients);

        MenuAdapter overviewAdapter = new MenuAdapter(this, model.getSelected().toArray()) {
            @Override
            protected void onClickImage(ViewHolder holder, Dish dish) {
                super.onClickImage(holder, dish);
                changeView(R.layout.instructions_view);

                mark(holder.item);

                TextView courseTitle = (TextView) findViewById(R.id.course_title);
                TextView courseType = (TextView) findViewById(R.id.course_type);
                TextView courseInst = (TextView) findViewById(R.id.inst);

                courseTitle.setText( dish.getName() );
                courseType.setText( dish.getTypeName() );
                courseInst.setText( dish.getDescription() );
            }
        };

        overview.setAdapter(overviewAdapter);
    }

    private void showAllIngredients(StringBuilder allIngredients) {
        changeView(R.layout.ingredients_view);

        TextView ing = (TextView) findViewById(R.id.ing);
        TextView part = (TextView) findViewById(R.id.part);
        part.setText("" + participants + " pers");

        ing.setText( allIngredients );
    }

    private void mark(View view) {
        if(marked != null) {
            marked.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
            for(int index=0; index<((ViewGroup)marked).getChildCount(); ++index) {
                View nextChild = ((ViewGroup)marked).getChildAt(index);
                if(nextChild instanceof TextView)
                    ((TextView) nextChild).setTextColor(Color.parseColor("#000000"));
            }
        }
        view.setBackgroundColor(Color.parseColor("#ff574a"));
        for(int index=0; index<((ViewGroup)view).getChildCount(); ++index) {
            View nextChild = ((ViewGroup)view).getChildAt(index);
            if(nextChild instanceof TextView)
                ((TextView) nextChild).setTextColor(Color.parseColor("#ffffff"));
        }
        marked = view;
    }

    private void changeView(int id) {
        View view = findViewById(R.id.ingredients_or_instructions);
        ViewGroup parent = (ViewGroup) view.getParent();
        int index = parent.indexOfChild(view);
        parent.removeView(view);
        view = getLayoutInflater().inflate(id, parent, false);
        parent.addView(view, index);
    }
}
