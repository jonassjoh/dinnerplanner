package se.kth.csc.iprog.dinnerplanner.model;

import android.graphics.Bitmap;

import java.util.HashSet;
import java.util.Set;

public class Dish {
	
	public static final int STARTER = 1;
	public static final int MAIN = 2;
	public static final int DESERT = 3;
	
	String name;
	int type; // starter (1), main (2) or desert (3)  
	String image;
	int imageId;
	String description;
	public boolean selected = false;
	String id;
	Bitmap bitmap;
	
	Set<Ingredient> ingredients = new HashSet<Ingredient>();
	
	public Dish(int imageId, String name, int type, String image, String description, String id) {
		this.name = name;
		this.type = type;
		this.image = image;
		this.imageId = imageId;
		this.description = description;
		this.id = id;

	}

	public String getTypeName() {
		int type = getType();
		if(type == STARTER) return "Starter";
		if(type == MAIN) return "Main Course";
		if(type == DESERT) return "Desert";
		return "";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		int sum = 0;
		Set<Ingredient> ingredients = getIngredients();
		for(Ingredient i : ingredients)
			sum += i.getPrice();
		return sum;
	}

	public Set<Ingredient> getIngredients(){
		return ingredients;
	}
	
	public void addIngredient(Ingredient ing){
		ingredients.add(ing);
	}
	
	public void removeIngredient(Ingredient ing){
		ingredients.remove(ing);
	}
	
	public boolean contains(String filter){
		if(name.toLowerCase().contains(filter.toLowerCase())){
			return true;
		}
		for(Ingredient i : ingredients){
			if(i.getName().toLowerCase().contains(filter.toLowerCase())){
				return true;
			}
		}
		return false;
	}

	public int getImageId() {
		return imageId;
	}

    public String getId(){
        return this.id;
    }

	public void setBitMap(Bitmap result){
		this.bitmap = result;
	}

	public Bitmap getBitMap(){
		return Bitmap.createScaledBitmap(this.bitmap, 250, 250, false);
	}
}
