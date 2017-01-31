package se.kth.csc.iprog.dinnerplanner.android.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class ExampleView {

	View view;
    DinnerModel dinnermodel;
	int clickerino = 0;
	public ExampleView(View view) {

		// store in the class the reference to the Android View
		this.view = view;
        dinnermodel = new DinnerModel();
		final TextView example = (TextView) view.findViewById(R.id.example_text);

		final Button button = (Button) view.findViewById(R.id.button);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				example.setVisibility(View.VISIBLE);
				clickerino ++;

				if(clickerino % 2 == 0) {
					example.setVisibility(View.INVISIBLE);
				}
			}
		});





		// Setup the rest of the view layout
	}


}
