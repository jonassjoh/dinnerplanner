package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by Jonas on 2017-02-02.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Object[] dataset;
    private Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item;
        public Dish dish;
        public ViewHolder(LinearLayout v) {
            super(v);
            item = v;
        }
        public TextView getTextView() {
            return (TextView) item.findViewById(R.id.item_title);
        }
        public ImageView getImageView() {
            return (ImageView) item.findViewById(R.id.item_image);
        }
    }

    private Set<Dish> getSelected() {
        Set<Dish> res = new HashSet<>();
        for(Object o : dataset) {
            Dish v = (Dish) o;
            if(v.selected)
                res.add(v);
        }
        return res;
    }

    private int countSelected() {
        int sum = 0;
        Set<Dish> v = getSelected();
        for(Dish c : v)
            sum += c.getCost();
        return sum;
    }

    public MenuAdapter(Activity activity, Object[] dataset) {
        this.dataset = dataset;
        this.activity = activity;
    }

    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Dish dish = (Dish) dataset[position];
        holder.dish = dish;
        holder.getTextView().setText( dish.getName() );
        holder.getImageView().setImageResource( dish.getImageId() );

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickImage(holder, dish);
            }
        });
    }

    protected void onClickImage(final ViewHolder holder, final Dish dish) {
        if(!holder.dish.selected) {

            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.chose_menu_dialog);

            Spinner s = (Spinner) activity.findViewById(R.id.participants);

            final DinnerModel model = ((DinnerPlannerApplication) activity.getApplication()).getModel();

            //final int participants = s.getSelectedItemPosition() + 1;
            final int participants = model.getNumberOfGuests();

            ((TextView) dialog.findViewById(R.id.item_title)).setText(
                    "Cost: "+(participants * dish.getCost())+"kr\n("+dish.getCost()+"kr / person)"
            );
            ((ImageView) dialog.findViewById(R.id.item_image)).setImageResource( dish.getImageId() );

            dialog.findViewById(R.id.choose_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Mark item
                    holder.item.setBackgroundColor(Color.parseColor("#ff574a"));
                    holder.getTextView().setTextColor(Color.parseColor("#ffffff"));
                    holder.dish.selected = true;
                    TextView t = (TextView) activity.findViewById(R.id.cost);

                    t.setText( "" + (model.getTotalMenuPrice() * model.getNumberOfGuests()) + "kr" );
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
        else {
            // Mark item
            // holder.item.setBackgroundColor(Color.parseColor("#ffffff"));
            // holder.getTextView().setTextColor(Color.parseColor("#000000"));
            // holder.dish.selected = false;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
