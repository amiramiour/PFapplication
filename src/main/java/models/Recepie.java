package models;

import java.util.List;

public class Recepie {
    private String id;
    private String title;
    private String date;
    private List<Ingredient> ingredients;
    private List<String> preparation;
    private String comment;
    private Nutrition nutrition;
    private String related;

    // Constructor, getters, setters


    public Recepie(String id, String title, String date, List<Ingredient> ingredients, List<String> preparation, String comment, Nutrition nutrition, String related) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.comment = comment;
        this.nutrition = nutrition;
        this.related = related;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public List<String> getPreparation() {
        return preparation;
    }

    public void setPreparation(List<String> preparation) {
        this.preparation = preparation;
    }
}
