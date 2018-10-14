package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichJSON = new JSONObject(json);
            JSONObject name = sandwichJSON.getJSONObject(NAME);
            String mainName = name.getString(MAIN_NAME);

            JSONArray alsoKnownAsJSONArray = name.getJSONArray(ALSO_KNOWN_AS);
            List alsoKnownAs = new ArrayList();
            populateList(alsoKnownAsJSONArray, alsoKnownAs);

            String placeOfOrigin = sandwichJSON.getString(PLACE_OF_ORIGIN);
            String description = sandwichJSON.getString(DESCRIPTION);
            String image = sandwichJSON.getString(IMAGE);

            JSONArray ingredientsJSONArray = sandwichJSON.getJSONArray(INGREDIENTS);
            List ingredients = new ArrayList();
            populateList(ingredientsJSONArray, ingredients);

            sandwich.setMainName(mainName);
            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            sandwich.setImage(image);
            sandwich.setIngredients(ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    public static void populateList(JSONArray jsonArray, List listToBePopulated){
        for(int i=0; i < jsonArray.length(); i++){
            try {
                listToBePopulated.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
