package se.kth.csc.iprog.dinnerplanner.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cz.msebera.android.httpclient.Header;
import se.kth.csc.iprog.dinnerplanner.android.AsyncData;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.android.SpoonacularAPIClient;

public class DinnerModel implements IDinnerModel{


    float totalCost = 0;
	int numOfGuests = 1;
	Set<Dish> dishes = new HashSet<Dish>();
	/**
	 * TODO: For Lab2 you need to implement the IDinnerModel interface.
	 * When you do this you will have all the needed fields and methods
	 * for the dinner planner (number of guests, selected dishes, etc.). 
	 */
	
	
	/**
	 * The constructor of the overall model. Set the default values here
	 */
	public DinnerModel(){

        /*

        //Adding some example data, you can add more
		Dish dish1 = new Dish(R.drawable.toast, "French toast",Dish.STARTER,"toast.jpg","In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
		Ingredient dish1ing1 = new Ingredient("eggs",0.5,"",1);
		Ingredient dish1ing2 = new Ingredient("milk",30,"ml",6);
		Ingredient dish1ing3 = new Ingredient("brown sugar",7,"g",1);
		Ingredient dish1ing4 = new Ingredient("ground nutmeg",0.5,"g",12);
		Ingredient dish1ing5 = new Ingredient("white bread",2,"slices",2);
		dish1.addIngredient(dish1ing1);
		dish1.addIngredient(dish1ing2);
		dish1.addIngredient(dish1ing3);
		dish1.addIngredient(dish1ing4);
		dish1.addIngredient(dish1ing5);
		dishes.add(dish1);

		dishes.add( makeDish("Frank's toast", Dish.DESERT) );
		dishes.add( makeDish("Frank's toast", Dish.MAIN) );
		dishes.add( makeDish("Frank's toast", Dish.STARTER) );
		dishes.add( makeDish("Frank's toast2", Dish.DESERT) );
		dishes.add( makeDish("Frank's toast2", Dish.MAIN) );
		dishes.add( makeDish("Frank's toast2", Dish.STARTER) );
		dishes.add( makeDish("Frank's toast3", Dish.DESERT) );
		dishes.add( makeDish("Frank's toast3", Dish.MAIN) );
		dishes.add( makeDish("Frank's toast3", Dish.STARTER) );
		dishes.add( makeDish("Frank's toast4", Dish.DESERT) );
		dishes.add( makeDish("Frank's toast4", Dish.MAIN) );
		dishes.add( makeDish("Frank's toast4", Dish.STARTER) );
		dishes.add( makeDish("Frank's toast5", Dish.DESERT) );
		dishes.add( makeDish("Frank's toast5", Dish.MAIN) );
		dishes.add( makeDish("Frank's toast5", Dish.STARTER) );
		dishes.add( makeDish("Frank's toast6", Dish.DESERT) );
		dishes.add( makeDish("Frank's toast6", Dish.MAIN) );
		dishes.add( makeDish("Frank's toast6", Dish.STARTER) );

		Dish dish2 = new Dish(R.drawable.meatballs, "Meat balls",Dish.STARTER,"meatballs.jpg","Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		Ingredient dish2ing1 = new Ingredient("extra lean ground beef",115,"g",20);
		Ingredient dish2ing2 = new Ingredient("sea salt",0.7,"g",3);
		Ingredient dish2ing3 = new Ingredient("small onion, diced",0.25,"",2);
		Ingredient dish2ing4 = new Ingredient("garlic salt",0.6,"g",3);
		Ingredient dish2ing5 = new Ingredient("Italian seasoning",0.3,"g",3);
		Ingredient dish2ing6 = new Ingredient("dried oregano",0.3,"g",3);
		Ingredient dish2ing7 = new Ingredient("crushed red pepper flakes",0.6,"g",3);
		Ingredient dish2ing8 = new Ingredient("Worcestershire sauce",16,"ml",7);
		Ingredient dish2ing9 = new Ingredient("milk",20,"ml",4);
		Ingredient dish2ing10 = new Ingredient("grated Parmesan cheese",5,"g",8);
		Ingredient dish2ing11 = new Ingredient("seasoned bread crumbs",115,"g",4);
		dish2.addIngredient(dish2ing1);
		dish2.addIngredient(dish2ing2);
		dish2.addIngredient(dish2ing3);
		dish2.addIngredient(dish2ing4);
		dish2.addIngredient(dish2ing5);
		dish2.addIngredient(dish2ing6);
		dish2.addIngredient(dish2ing7);
		dish2.addIngredient(dish2ing8);
		dish2.addIngredient(dish2ing9);
		dish2.addIngredient(dish2ing10);
		dish2.addIngredient(dish2ing11);
		dishes.add(dish2);
		*/
	}

	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishes(){
		return dishes;
	}
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishesOfType(int type){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type){
				result.add(d);
			}
		}
		return result;
	}
	
	/**
	 * Returns the set of dishes of specific type, that contain filter in their name
	 * or name of any ingredient. 
	 */
	public Set<Dish> filterDishesOfType(int type, String filter){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type && d.contains(filter)){
				result.add(d);
			}
		}
		return result;
	}

	/**
	 * Returns the selected item
     * @return
     */
	public Set<Dish> getSelected(){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.selected){
				result.add(d);
			}
		}
		return result;
	}


	@Override
	public int getNumberOfGuests() {
		return numOfGuests;
	}

	@Override
	public void setNumberOfGuests(int numberOfGuests) {
        numOfGuests = numberOfGuests;
	}

	@Override
	public Dish getSelectedDish(int type) {
		return null;
	}

	@Override
	public Set<Dish> getFullMenu() {
		return dishes;
	}

	@Override
	public Set<Ingredient> getAllIngredients() {
        Set<Ingredient> ingredients = new HashSet<Ingredient>();
		Set<Ingredient> in = new HashSet<>();

		for(Dish d : dishes) {
			if(d.selected)
			for(Ingredient i : d.getIngredients()) {
				boolean has = false;
				for(Ingredient k : in) {
					if(k.getName().equals(i.getName())) {
						k.setQuantity( k.getQuantity() + i.getQuantity() );
						has = true;
						break;
					}
				}
				if(!has) {
					in.add(i);
				}
			}
		}

        for(Dish d : dishes) {
            for(Ingredient i : d.getIngredients()) {
                ingredients.add(i);
            }
        }

        return in;
	}

	@Override
	public float getTotalMenuPrice() {
		Set<Dish> selected = getSelected();
		int sum = 0;
		for (Dish d : selected) {
			sum += d.getCost();
		}
		return sum;
	}

	@Override
	public void addDishToMenu(Dish dish) {
        dishes.add(dish);
	}

	@Override
	public void removeDishFromMenu(Dish dish) {
        dishes.remove(dish);
	}

    public void getInstructions(final Dish dish, final AsyncData callback){
        try {
            SpoonacularAPIClient.get("recipes/"+dish.getId()+"/analyzedInstructions", null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONObject instruction = response.getJSONObject(0);
                        JSONArray steps = instruction.getJSONArray("steps");
                        for(int i = 0; i < steps.length(); i++){
                            String step = steps.getJSONObject(i).getString("step");
                            dish.setDescription(""+dish.getDescription()+step+"\n");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    callback.onData();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    System.out.println(errorResponse);
                }
            });
        }catch (Exception e) {

        }

    }

    public void getIngredients(final Dish dish, final AsyncData callback){
        try {
            SpoonacularAPIClient.get("recipes/"+dish.getId()+"/information", null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray ingredients = response.getJSONArray("extendedIngredients");
                        for(int i = 0; i < ingredients.length(); i++){
                            String name = ingredients.getJSONObject(i).getString("name");
                            double amount = Double.parseDouble(ingredients.getJSONObject(i).getString("amount"));
                            String unit = ingredients.getJSONObject(i).getString("unit");
                            dish.addIngredient(new Ingredient(name, amount, unit, amount));
                        }
                        callback.onData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    System.out.println(errorResponse);
                }
            });
        }catch (Exception e) {

        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        Dish dish;
        AsyncData data;

        public DownloadImageTask(Dish dish, AsyncData data) {
            this.dish = dish;
            this.data = data;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            dish.setBitMap(result);
            dishes.add(dish);
            data.onData();
        }
    }

    public void searchDish(final String type, final AsyncData data){
        try {
            SpoonacularAPIClient.get("recipes/search?type="+type, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    System.out.println("> " + response);
                    try {
                        String imgBase = response.getString("baseUri");
                        JSONArray arr = response.getJSONArray("results");
                        for (int i=0; i<arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject( i );

                            Dish dish = null;

                            if(type=="appetizer"){
                                dish = new Dish(R.drawable.toast, obj.getString("title"), Dish.STARTER, imgBase+obj.get("image"), "intructions here", obj.getString("id"));
                            }
                            else if(type=="main course"){
                                dish = new Dish(R.drawable.toast, obj.getString("title"), Dish.MAIN, imgBase+obj.get("image"), "intructions here", obj.getString("id"));
                            }
                            else if(type=="dessert"){
                                dish = new Dish(R.drawable.toast, obj.getString("title"), Dish.DESERT, imgBase+obj.get("image"), "intructions here", obj.getString("id"));
                            }
                            new DownloadImageTask(dish, data)
                                    .execute(dish.getImage());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    System.out.println(errorResponse);
                }
            });
        }catch (Exception e) {

        }
    }

    public void helpSearchDish(final AsyncData data){
        searchDish("appetizer", new AsyncData() {
            @Override
            public void onData() {
                searchDish("main course", new AsyncData() {
                    @Override
                    public void onData() {
                        searchDish("dessert", new AsyncData() {
                            @Override
                            public void onData() {
                                data.onData();
                            }
                        });
                    }
                });
            }
        });
    }

}
