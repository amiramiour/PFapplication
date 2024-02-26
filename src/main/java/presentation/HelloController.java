//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package presentation;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Recepie;
import repositories.RecepieRepo;

public class HelloController {
    private RecepieRepo recipeRepo = new RecepieRepo();
    @FXML
    private Label welcomeText;
    @FXML
    private Button graphButton;
    @FXML
    private Button textButton;

    public HelloController() {
        this.recipeRepo.init("C:\\Users\\CAP\\PFapplication\\src\\main\\java\\recipes.xml");
    }

    @FXML
    protected void onHelloButtonClick() {
        this.welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onGraphButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("graphMode.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Scene scene = this.graphButton.getScene();
            scene.setRoot(root);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    @FXML

    public void onTextButton(ActionEvent actionEvent) {
        System.out.println("1. Total des œufs utilisés : " + this.recipeRepo.getTotalEggsUsed());
        System.out.println("\n2. Recettes utilisant de l'huile d'olive : ");
        List<Recepie> recipesWithOliveOil = this.recipeRepo.getRecipesUsingOliveOil();
        for (Recepie recipe : recipesWithOliveOil) {
            System.out.println(recipe.getTitle());
        }

        System.out.println("\n3. Liste des titres des recettes : ");
        List<String> recipeTitles = this.recipeRepo.listRecipeTitles();
        for (String title : recipeTitles) {
            System.out.println(title);
        }

        System.out.println("\n4. Œufs par recette : ");
        List<Integer> eggsCountPerRecipe = this.recipeRepo.calculateEggsPerRecipe();
        List<Recepie> recipes = this.recipeRepo.getRecipes();
        for (int i = 0; i < eggsCountPerRecipe.size(); ++i) {
            System.out.println(recipes.get(i).getTitle() + " : " + eggsCountPerRecipe.get(i));
        }

        System.out.println("\n5. Recettes avec moins de 500 calories : ");
        List<Recepie> recipesUnder500Calories = this.recipeRepo.getRecipesWithLessThan500Calories();
        for (Recepie recipe : recipesUnder500Calories) {
            System.out.println(recipe.getTitle());
        }

        System.out.println("\n6. Total de sucre dans la recette Zuppa Inglese : " + this.recipeRepo.zuppainglesesugar());
        System.out.println("\n7. Deux premières étapes de la recette Zuppa Inglese : ");
        recipeRepo.getFirstTwoStepsOfZuppaInglese();
        System.out.println("\n8. Recettes avec plus de cinq étapes : ");
        List<Recepie> recipesWithMoreThanFiveSteps = this.recipeRepo.getRecipesWithMoreThanFiveSteps();
        for (Recepie recipe : recipesWithMoreThanFiveSteps) {
            System.out.println(recipe.getTitle());
        }

        System.out.println("\n9. Recettes sans beurre : ");
        List<Recepie> recipesWithoutButter = this.recipeRepo.getRecipesWithoutButter();
        for (Recepie recipe : recipesWithoutButter) {
            System.out.println(recipe.getTitle());
        }

        System.out.println("\n10. Recettes avec des ingrédients communs avec Zuppa Inglese : ");
        List<Recepie> recipesWithCommonIngredientsWithZuppaInglese = this.recipeRepo.getRecipesWithCommonIngredientsWithZuppaInglese();
        for (Recepie recipe : recipesWithCommonIngredientsWithZuppaInglese) {
            System.out.println(recipe.getTitle());
        }

        System.out.println("\n11. Recette la plus calorique : " + this.recipeRepo.getMostCaloricRecipe().getTitle());
        System.out.println("\n12. Unité la plus fréquente : " + this.recipeRepo.getMostFrequentUnit());
        System.out.println("\n13. Nombre d'ingrédients par recette : ");
        Map<Recepie, Integer> ingredientsCountPerRecipe = this.recipeRepo.calculateIngredientsCountPerRecipe();
        for (Map.Entry<Recepie, Integer> entry : ingredientsCountPerRecipe.entrySet()) {
            System.out.println(entry.getKey().getTitle() + " : " + entry.getValue());
        }

        System.out.println("\n14. Recette avec le plus de graisses : " + this.recipeRepo.getRecipeWithMostFat().getTitle());
        System.out.println("\n15. Ingrédient le plus utilisé : " + this.recipeRepo.getMostUsedIngredient());
        System.out.println("\n16. Recettes triées par nombre d'ingrédients : ");
        List<Recepie> recipesSortedByIngredientCount = this.recipeRepo.sortRecipesByIngredientCount();
        for (Recepie recipe : recipesSortedByIngredientCount) {
            System.out.println(recipe.getTitle());
        }

        System.out.println("\n17. Recettes par ingrédient : ");
        Map<String, List<String>> recipesByIngredient = this.recipeRepo.getRecipesByIngredient();
        for (Map.Entry<String, List<String>> entry : recipesByIngredient.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("\n18. Recettes regroupées par nombre d'étapes : ");
        Map<Integer, List<Recepie>> recipesByNumberOfSteps = this.recipeRepo.groupRecipesByNumberOfSteps();
        for (Map.Entry<Integer, List<Recepie>> entry : recipesByNumberOfSteps.entrySet()) {
            System.out.println(entry.getKey() + " étapes : ");
            for (Recepie recipe : entry.getValue()) {
                System.out.println("   - " + recipe.getTitle());
            }
        }

        System.out.println("\n19. Recette la plus facile : " + this.recipeRepo.getEasiestRecipe().getTitle());
        Stage stage = (Stage)this.textButton.getScene().getWindow();
        stage.close();
    }

}
