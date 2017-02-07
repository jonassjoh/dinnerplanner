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
import android.widget.Toast;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class MenuActivity extends Activity {

    DinnerModel model;
    int previous = 1;

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
                String costString = costView.getText().toString();
                int totCost = Integer.parseInt(costString.subSequence(0, costString.length() - 2).toString());
                totCost /= previous;
                totCost *= (i+1);
                costView.setText(totCost+"kr");
                previous = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RecyclerView starters = (RecyclerView) findViewById(R.id.starters);
        RecyclerView mainCourses = (RecyclerView) findViewById(R.id.main_courses);
        RecyclerView desserts = (RecyclerView) findViewById(R.id.desserts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        starters.setLayoutManager(linearLayoutManager);
        mainCourses.setLayoutManager(linearLayoutManager2);
        desserts.setLayoutManager(linearLayoutManager3);

        MenuAdapter startersAdapter = new MenuAdapter(this, getStarters());
        MenuAdapter mainCoursesAdapter = new MenuAdapter(this, getMainCourses());
        MenuAdapter desertsAdapter = new MenuAdapter(this, getDeserts());
        starters.setAdapter(startersAdapter);
        mainCourses.setAdapter(mainCoursesAdapter);
        desserts.setAdapter(desertsAdapter);

        Button create = (Button) findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, OverviewActivity.class);
                TextView costView = (TextView) findViewById(R.id.cost);
                String costString = costView.getText().toString();

                intent.putExtra("cost", costString);
                intent.putExtra("participants", previous);

                startActivity(intent);
            }
        });
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
    private Object[] getSelected() {
        return model.getSelected().toArray();
    }
}
