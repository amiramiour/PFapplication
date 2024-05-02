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
import java.util.Scanner;

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
        this.recipeRepo.init("C:\\Users\\CAP\\PFapplication\\src\\main\\java\\data\\recipes.xml");
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


        boolean repeat = true;
        Scanner scanner;
        do {
            scanner = new Scanner(System.in);

            System.out.println("1. Total des œufs utilisés");
            System.out.println("2. Recettes utilisant de l'huile d'olive");
            System.out.println("3. Liste des titres des recettes");
            System.out.println("4. Œufs par recette");
            System.out.println("5. Recettes avec moins de 500 calories");
            System.out.println("6. Total de sucre dans la recette Zuppa Inglese");
            System.out.println("7. Deux premières étapes de la recette Zuppa Inglese");
            System.out.println("8. Recettes avec plus de cinq étapes");
            System.out.println("9. Recettes sans beurre");
            System.out.println("10. Recettes avec des ingrédients communs avec Zuppa Inglese");
            System.out.println("11. Recette la plus calorique");
            System.out.println("12. Unité la plus fréquente");
            System.out.println("13. Nombre d'ingrédients par recette");
            System.out.println("14. Recette avec le plus de graisses");
            System.out.println("15. Ingrédient le plus utilisé");
            System.out.println("16. Recettes triées par nombre d'ingrédients");
            System.out.println("17. Recettes par ingrédient");
            System.out.println("18. Recettes regroupées par nombre d'étapes");
            System.out.println("19. Recette la plus facile");
            System.out.println("Veuillez choisir le numéro de la question pour obtenir sa réponse :");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Total des œufs utilisés : " + this.recipeRepo.getTotalEggsUsed());
                    break;
                case 2:
                    System.out.println("Recettes utilisant de l'huile d'olive : ");
                    List<Recepie> recipesWithOliveOil = this.recipeRepo.getRecipesUsingOliveOil();
                    for (Recepie recipe : recipesWithOliveOil) {
                        System.out.println(recipe.getTitle());
                    }
                    break;
                case 3:
                    System.out.println("Liste des titres des recettes : ");
                    List<String> recipeTitles = this.recipeRepo.listRecipeTitles();
                    for (String title : recipeTitles) {
                        System.out.println(title);
                    }
                    break;
                case 4:
                    System.out.println("Œufs par recette : ");
                    List<Integer> eggsCountPerRecipe = this.recipeRepo.calculateEggsPerRecipe();
                    List<Recepie> recipes = this.recipeRepo.getRecipes();
                    for (int i = 0; i < eggsCountPerRecipe.size(); ++i) {
                        System.out.println(recipes.get(i).getTitle() + " : " + eggsCountPerRecipe.get(i));
                    }
                    break;
                case 5:
                    System.out.println("Recettes avec moins de 500 calories : ");
                    List<Recepie> recipesUnder500Calories = this.recipeRepo.getRecipesWithLessThan500Calories();
                    for (Recepie recipe : recipesUnder500Calories) {
                        System.out.println(recipe.getTitle());
                    }
                    break;
                case 6:
                    System.out.println("Total de sucre dans la recette Zuppa Inglese : " + this.recipeRepo.zuppainglesesugar());
                    break;
                case 7:
                    System.out.println("Deux premières étapes de la recette Zuppa Inglese : ");
                    this.recipeRepo.getFirstTwoStepsOfZuppaInglese().forEach(System.out::println);
                    break;
                case 8:
                    System.out.println("Recettes avec plus de cinq étapes : ");
                    List<Recepie> recipesWithMoreThanFiveSteps = this.recipeRepo.getRecipesWithMoreThanFiveSteps();
                    for (Recepie recipe : recipesWithMoreThanFiveSteps) {
                        System.out.println(recipe.getTitle());
                    }
                    break;
                case 9:
                    System.out.println("Recettes sans beurre : ");
                    List<Recepie> recipesWithoutButter = this.recipeRepo.getRecipesWithoutButter();
                    for (Recepie recipe : recipesWithoutButter) {
                        System.out.println(recipe.getTitle());
                    }
                    break;
                case 10:
                    System.out.println("Recettes avec des ingrédients communs avec Zuppa Inglese : ");
                    List<Recepie> recipesWithCommonIngredientsWithZuppaInglese = this.recipeRepo.getRecipesWithCommonIngredientsWithZuppaInglese();
                    for (Recepie recipe : recipesWithCommonIngredientsWithZuppaInglese) {
                        System.out.println(recipe.getTitle());
                    }
                    break;
                case 11:
                    System.out.println("Recette la plus calorique : " + this.recipeRepo.getMostCaloricRecipe().getTitle());
                    break;
                case 12:
                    System.out.println("Unité la plus fréquente : " + this.recipeRepo.getMostFrequentUnit());
                    break;
                case 13:
                    System.out.println("Nombre d'ingrédients par recette : ");
                    Map<Recepie, Integer> ingredientsCountPerRecipe = this.recipeRepo.calculateIngredientsCountPerRecipe();
                    for (Map.Entry<Recepie, Integer> entry : ingredientsCountPerRecipe.entrySet()) {
                        System.out.println(entry.getKey().getTitle() + " : " + entry.getValue());
                    }
                    break;
                case 14:
                    System.out.println("Recette avec le plus de graisses : " + this.recipeRepo.getRecipeWithMostFat().getTitle());
                    break;
                case 15:
                    System.out.println("Ingrédient le plus utilisé : " + this.recipeRepo.getMostUsedIngredient());
                    break;
                case 16:
                    System.out.println("Recettes triées par nombre d'ingrédients : ");
                    List<Recepie> recipesSortedByIngredientCount = this.recipeRepo.sortRecipesByIngredientCount();
                    for (Recepie recipe : recipesSortedByIngredientCount) {
                        System.out.println(recipe.getTitle());
                    }
                    break;
                case 17:
                    System.out.println("Recettes par ingrédient : ");
                    Map<String, List<String>> recipesByIngredient = this.recipeRepo.getRecipesByIngredient();
                    for (Map.Entry<String, List<String>> entry : recipesByIngredient.entrySet()) {
                        System.out.println(entry.getKey() + " : " + entry.getValue());
                    }
                    break;
                case 18:
                    System.out.println("Recettes regroupées par nombre d'étapes : ");
                    Map<Integer, List<Recepie>> recipesByNumberOfSteps = this.recipeRepo.groupRecipesByNumberOfSteps();
                    for (Map.Entry<Integer, List<Recepie>> entry : recipesByNumberOfSteps.entrySet()) {
                        System.out.println(entry.getKey() + " étapes : ");
                        for (Recepie recipe : entry.getValue()) {
                            System.out.println("   - " + recipe.getTitle());
                        }
                    }
                    break;
                case 19:
                    System.out.println("Recette la plus facile : " + this.recipeRepo.getEasiestRecipe().getTitle());
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
            System.out.println("Voulez-vous choisir une autre question ? (O/N)");
            String input = scanner.next();
            if (input.equalsIgnoreCase("O") || input.equalsIgnoreCase("oui") || input.equalsIgnoreCase("o")) {
                repeat = true;
            } else {
                repeat = false;
                System.out.println("Fermeture de l'application...");
                System.exit(0); // Ferme l'application
            }

        } while (repeat);
        scanner.close();


    }

}
