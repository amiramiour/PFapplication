package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Recepie;
import repositories.RecepieRepo;

import java.io.IOException;
import java.util.List;

public class GraphModeController {

    @FXML
    private ChoiceBox<String> questionsBox;

    @FXML
    private Button validateQuestion;

    @FXML
    private TextArea resultArea;

    String filePath = "C:\\Users\\CAP\\PFapplication\\src\\main\\java\\data\\recipes.xml";
    RecepieRepo recipeRepo;

    @FXML
    private Button backButton;

    // Méthode appelée lorsque le bouton backButton est cliqué
    @FXML
    public void onBackButtonClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button nextButton;

    // Méthode appelée lorsque le bouton backButton est cliqué
    @FXML
    public void onNextButtonClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("details-receipe.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        recipeRepo = new RecepieRepo();
        recipeRepo.init(filePath);

        // Initialiser les questions possibles dans le ChoiceBox
        List<String> questions = List.of(
                "Total des œufs utilisés",
                "Recettes utilisant de l'huile d'olive",
                "Liste des titres des recettes",
                "Œufs par recette",
                "Recettes avec moins de 500 calories",
                "Zuppa Inglese - Quantité de sucre",
                "Premières deux étapes de Zuppa Inglese",
                "Recettes avec plus de cinq étapes",
                "Recettes sans beurre",
                "Recettes avec des ingrédients communs à Zuppa Inglese",
                "Recette la plus calorique",
                "Unité la plus fréquente",
                "Nombre d'ingrédients par recette",
                "Recette avec le plus de matières grasses",
                "Ingrédient le plus utilisé",
                "Recettes triées par nombre d'ingrédients",
                "Recettes par ingrédient",
                "Recettes regroupées par nombre d'étapes",
                "Recette la plus facile"
                // Ajoutez d'autres questions si nécessaire
        );
        questionsBox.getItems().addAll(questions);
    }

    @FXML
    public void onValidateQuestion(ActionEvent event) {
        String selectedQuestion = questionsBox.getValue();
        String result = "";

        // Appeler les méthodes correspondantes en fonction de la question sélectionnée
        switch (selectedQuestion) {
            case "Total des œufs utilisés":
                double totalEggsUsed = recipeRepo.getTotalEggsUsed();
                result = "Total des œufs utilisés : " + totalEggsUsed;
                break;
            case "Recettes utilisant de l'huile d'olive":
                List<Recepie> recipesWithOliveOil = recipeRepo.getRecipesUsingOliveOil();
                result = "Recettes utilisant de l'huile d'olive :\n";
                for (Recepie recipe : recipesWithOliveOil) {
                    result += recipe.getTitle() + "\n"; // Ajoutez d'autres attributs de la recette si nécessaire
                }
                break;
            case "Liste des titres des recettes":
                List<String> recipeTitles = recipeRepo.listRecipeTitles();
                result = "Liste des titres des recettes :\n";
                for (String title : recipeTitles) {
                    result += title + "\n";
                }
                break;
            // Ajoutez d'autres cas pour d'autres questions ici...
            case "Œufs par recette":
                List<Integer> eggsCountPerRecipe = recipeRepo.calculateEggsPerRecipe();
                result = "Œufs par recette :\n";
                List<Recepie> recipes = recipeRepo.getRecipes();
                for (int i = 0; i < eggsCountPerRecipe.size(); i++) {
                    result += recipes.get(i).getTitle() + " : " + eggsCountPerRecipe.get(i) + "\n";
                }
                break;
            case "Recettes avec moins de 500 calories":
                List<Recepie> recipesUnder500Calories = recipeRepo.getRecipesWithLessThan500Calories();
                result = "Recettes avec moins de 500 calories :\n";
                for (Recepie recipe : recipesUnder500Calories) {
                    result += recipe.getTitle() + "\n";
                }
                break;
            case "Zuppa Inglese - Quantité de sucre":
                double zuppaIngleseSugar = recipeRepo.zuppainglesesugar();
                result = "Zuppa Inglese - Quantité de sucre : " + zuppaIngleseSugar + " g\n";
                break;
            case "Premières deux étapes de Zuppa Inglese":
                List<String> firstTwoSteps = recipeRepo.getFirstTwoStepsOfZuppaInglese();
                result = "Premières deux étapes de Zuppa Inglese :\n";
                for (String step : firstTwoSteps) {
                    result += step + "\n";
                }
                break;

            case "Recettes avec plus de cinq étapes":
                List<Recepie> recipesWithMoreThanFiveSteps = recipeRepo.getRecipesWithMoreThanFiveSteps();
                result = "Recettes avec plus de cinq étapes :\n";
                for (Recepie recipe : recipesWithMoreThanFiveSteps) {
                    result += recipe.getTitle() + "\n";
                }
                break;
            case "Recettes sans beurre":
                List<Recepie> recipesWithoutButter = recipeRepo.getRecipesWithoutButter();
                result = "Recettes sans beurre :\n";
                for (Recepie recipe : recipesWithoutButter) {
                    result += recipe.getTitle() + "\n";
                }
                break;
            case "Recettes avec des ingrédients communs à Zuppa Inglese":
                List<Recepie> recipesWithCommonIngredientsWithZuppaInglese = recipeRepo.getRecipesWithCommonIngredientsWithZuppaInglese();
                result = "Recettes avec des ingrédients communs à Zuppa Inglese :\n";
                for (Recepie recipe : recipesWithCommonIngredientsWithZuppaInglese) {
                    result += recipe.getTitle() + "\n";
                }
                break;
            case "Recette la plus calorique":
                Recepie mostCaloricRecipe = recipeRepo.getMostCaloricRecipe();
                result = "Recette la plus calorique : " + mostCaloricRecipe.getTitle();
                break;
            case "Unité la plus fréquente":
                String mostFrequentUnit = recipeRepo.getMostFrequentUnit();
                result = "Unité la plus fréquente : " + mostFrequentUnit;
                break;
            case "Nombre d'ingrédients par recette":
                result = "Nombre d'ingrédients par recette :\n";
                for (Recepie recipe : recipeRepo.getRecipes()) {
                    result += recipe.getTitle() + " : " + recipe.getIngredients().size() + "\n";
                }
                break;
            case "Recette avec le plus de matières grasses":
                Recepie recipeWithMostFat = recipeRepo.getRecipeWithMostFat();
                result = "Recette avec le plus de matières grasses : " + recipeWithMostFat.getTitle();
                break;
            case "Ingrédient le plus utilisé":
                String mostUsedIngredient = recipeRepo.getMostUsedIngredient();
                result = "Ingrédient le plus utilisé : " + mostUsedIngredient;
                break;
            case "Recettes triées par nombre d'ingrédients":
                List<Recepie> recipesSortedByIngredientCount = recipeRepo.sortRecipesByIngredientCount();
                result = "Recettes triées par nombre d'ingrédients :\n";
                for (Recepie recipe : recipesSortedByIngredientCount) {
                    result += recipe.getTitle() + "\n";
                }
                break;
            case "Recettes par ingrédient":
                result = "Recettes par ingrédient :\n";
                for (String ingredient : recipeRepo.getRecipesByIngredient().keySet()) {
                    result += ingredient + " : " + recipeRepo.getRecipesByIngredient().get(ingredient) + "\n";
                }
                break;
            case "Recettes regroupées par nombre d'étapes":
                result = "Recettes regroupées par nombre d'étapes :\n";
                for (Integer stepCount : recipeRepo.groupRecipesByNumberOfSteps().keySet()) {
                    result += stepCount + " étapes :\n";
                    for (Recepie recipe : recipeRepo.groupRecipesByNumberOfSteps().get(stepCount)) {
                        result += "   - " + recipe.getTitle() + "\n";
                    }
                }
                break;
            case "Recette la plus facile":
                Recepie easiestRecipe = recipeRepo.getEasiestRecipe();
                result = "Recette la plus facile : " + easiestRecipe.getTitle();
                break;
        }

        // Afficher le résultat dans la zone de texte
        resultArea.setText(result);
    }

}
