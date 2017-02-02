package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by Jonas on 2017-02-02.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Object[] dataset;
    private Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout item;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Dish dish = (Dish) dataset[position];
        holder.getTextView().setText( dish.getName() );
        holder.getImageView().setImageResource( dish.getImageId() );
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
