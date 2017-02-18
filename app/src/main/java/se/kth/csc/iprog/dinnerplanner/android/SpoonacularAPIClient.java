package se.kth.csc.iprog.dinnerplanner.android;

import com.loopj.android.http.*;

public class SpoonacularAPIClient {
    private static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/";
    private static final String API_KEY = "Qu9grxVNWpmshA4Kl9pTwyiJxVGUp1lKzrZjsnghQMkFkfA4LB";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("X-Mashape-Key",API_KEY);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
