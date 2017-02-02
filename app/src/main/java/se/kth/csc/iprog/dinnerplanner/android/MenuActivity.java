package se.kth.csc.iprog.dinnerplanner.android;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

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
        setContentView(R.layout.chose_menu);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.titlebar);

        model = new DinnerModel();

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
