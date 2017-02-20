package se.kth.csc.iprog.dinnerplanner.android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class MenuActivity extends Activity {

    DinnerModel model;

    public MenuActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ((DinnerPlannerApplication) this.getApplication()).getModel();

        setContentView(R.layout.chose_menu);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.titlebar);

        final Spinner participants = (Spinner) findViewById(R.id.participants);
        Integer[] guests = new Integer[] { 1,2,3,4,5,6,7,8,9,10,11 };
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, guests);
        participants.setAdapter(adapter);
        participants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TextView costView = (TextView) findViewById(R.id.cost);
                model.setNumberOfGuests(i+1);
                costView.setText(model.getTotalMenuPrice() * model.getNumberOfGuests() + "kr");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final RecyclerView starters = (RecyclerView) findViewById(R.id.starters);
        final RecyclerView mainCourses = (RecyclerView) findViewById(R.id.main_courses);
        final RecyclerView desserts = (RecyclerView) findViewById(R.id.desserts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        starters.setLayoutManager(linearLayoutManager);
        mainCourses.setLayoutManager(linearLayoutManager2);
        desserts.setLayoutManager(linearLayoutManager3);

        final MenuAdapter startersAdapter = new MenuAdapter(this, get());
        MenuAdapter mainCoursesAdapter = new MenuAdapter(this, get());
        MenuAdapter desertsAdapter = new MenuAdapter(this, get());
        starters.setAdapter(startersAdapter);
        mainCourses.setAdapter(mainCoursesAdapter);
        desserts.setAdapter(desertsAdapter);

        model.setAdapters(startersAdapter, mainCoursesAdapter, desertsAdapter);

        model.helpSearchDish(new AsyncData() {
            @Override
            public void onData() {
            }
        });

        Button create = (Button) findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, OverviewActivity.class);
                startActivity(intent);
            }
        });
    }

    private Object[] get() {
        Set<Dish> result = new HashSet<Dish>();
        return result.toArray();
    }

    private Object[] getStarters() {
        return model.getDishesOfType(Dish.STARTER).toArray();
    }
    private Object[] getMainCourses() {
        return model.getDishesOfType(Dish.MAIN).toArray();
    }
    private Object[] getDeserts() {
        return model.getDishesOfType(Dish.DESERT).toArray();
    }
}
