package models;

public class Nutrition {
    private int calories;
    private String fat;
    private String carbohydrates;
    private String protein;
    private String alcohol;

    public Nutrition(int calories, String fat, String carbohydrates, String protein,String alcohol) {
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.alcohol=alcohol;
    }

    // Getters and setters

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }


}
