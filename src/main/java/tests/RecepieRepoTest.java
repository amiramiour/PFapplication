package tests;

import models.Recepie;
import repositories.RecepieRepo;

import java.util.List;
import java.util.Map;

public class RecepieRepoTest {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\CAP\\PFapplication\\src\\main\\java\\recipes.xml";

        // Création d'une instance de RecipeRepo
        RecepieRepo recipeRepo = new RecepieRepo();
        // Initialisation de RecipeRepo avec le fichier XML
        recipeRepo.init(filePath);

        // Appel de la méthode à tester
        List<String> recipeTitles = recipeRepo.listRecipeTitles();

        // Affichage des titres des recettes
        for (String title : recipeTitles) {
            System.out.println(title);
        }

        // Tester calculateTotalEggsUsed()
        double totalEggsUsed = recipeRepo.getTotalEggsUsed();
        // Convertir le total des œufs utilisés en entier
        int totalEggsUsedInt = (int) totalEggsUsed;
        // Affichage du nombre total d'œufs utilisés
        System.out.println("\nTotal eggs used: " + totalEggsUsedInt);

        // Affichage des recettes utilisant de l'huile d'olive
        List<Recepie> recipesUsingOliveOil = recipeRepo.getRecipesUsingOliveOil();
        System.out.println("\nRecettes utilisant de l'huile d'olive :");
        recipesUsingOliveOil.forEach(recipe -> {
            System.out.println("Titre : " + recipe.getTitle());
        });

        // Affichage du nombre d'œufs par recette
        List<Integer> eggsPerRecipe = recipeRepo.calculateEggsPerRecipe();
        for (int i = 0; i < eggsPerRecipe.size(); i++) {
            System.out.println("Recette \"" + recipeTitles.get(i) + "\": " + eggsPerRecipe.get(i) + " œufs");
        }

        // Affichage des recettes avec moins de 500 calories
        List<Recepie> recipesWithLessThan500Calories = recipeRepo.getRecipesWithLessThan500Calories();
        System.out.println("Recettes avec moins de 500 calories :");
        for (Recepie recipe : recipesWithLessThan500Calories) {
            System.out.println("Titre : " + recipe.getTitle());
        }
        //////////
        System.out.println("recette Inglese qt sucre: " + recipeRepo.zuppainglesesugar() +" cup");


        // Affichage des deux premières étapes de Zuppa Inglese
       // recipeRepo.printFirstTwoStepsOfZuppaInglese();

        // Affichage des recettes avec plus de cinq étapes
        System.out.println("Recettes avec plus de cinq étapes :");
        for (Recepie recipe : recipeRepo.getRecipesWithMoreThanFiveSteps()) {
            System.out.println("Titre : " + recipe.getTitle());
        }

        // Affichage des recettes sans beurre
        System.out.println("Recettes sans beurre :");
        for (Recepie recipe : recipeRepo.getRecipesWithoutButter()) {
            System.out.println("Titre : " + recipe.getTitle());
        }
        List<Recepie> recipesWithCommonIngredients = recipeRepo.getRecipesWithCommonIngredientsWithZuppaInglese();

        // Affichage des recettes ayant des ingrédients en commun avec Zuppa Inglese
        System.out.println("Recettes avec des ingrédients en commun avec Zuppa Inglese :");
        recipesWithCommonIngredients.forEach(recipe -> System.out.println("Titre : " + recipe.getTitle()));
        //////
        Recepie mostCaloricRecipe = recipeRepo.getMostCaloricRecipe();
        System.out.println("La recette la plus calorique : " + mostCaloricRecipe.getTitle());

        // Test getMostFrequentUnit
        String mostFrequentUnit = recipeRepo.getMostFrequentUnit();
        System.out.println("Unité la plus fréquente : " + mostFrequentUnit);

        // Test calculateIngredientsCountPerRecipe
        Map<Recepie, Integer> ingredientsCountPerRecipe = recipeRepo.calculateIngredientsCountPerRecipe();

        // Affichage du nombre d'ingrédients par recette
        System.out.println("Nombre d'ingrédients par recette :");
        ingredientsCountPerRecipe.forEach((recipe, count) -> {
            System.out.println("Recette \"" + recipe.getTitle() + "\" : " + count);
        });
        // Test getRecipeWithMostFat
        Recepie recipeWithMostFat = recipeRepo.getRecipeWithMostFat();
      System.out.println("Recette avec le plus de gras : " + recipeWithMostFat.getTitle());

        // Test getMostUsedIngredient
        String mostUsedIngredient = recipeRepo.getMostUsedIngredient();
        System.out.println("Ingrédient le plus utilisé : " + mostUsedIngredient);

        // Test sortRecipesByIngredientCount
        List<Recepie> recipesSortedByIngredientCount = recipeRepo.sortRecipesByIngredientCount();
        System.out.println("Recettes triées par nombre d'ingrédients :");
        recipesSortedByIngredientCount.forEach(recipe -> System.out.println(recipe.getTitle() + " : " + recipe.getIngredients().size()));

        // Test getRecipesByIngredient
        Map<String, List<String>> recipesByIngredient = recipeRepo.getRecipesByIngredient();
        System.out.println("Recettes par ingrédient :");
        recipesByIngredient.forEach((ingredient, recipes) -> {
            System.out.println(ingredient + " : ");
            recipes.forEach(recipe -> System.out.println("    " + recipe));
        });

        // Test calculateRecipeDistributionByStep
        Map<Integer, List<Recepie>> recipesBySteps = recipeRepo.groupRecipesByNumberOfSteps();

        // Affichage du résultat
        recipesBySteps.forEach((steps, recipes) -> {
            System.out.println("Nombre d'étapes : " + steps);
            recipes.forEach(recipe -> System.out.println("Recette : " + recipe.getTitle()));
            System.out.println(); // Ligne vide pour séparer les groupes
        });

        // Test getEasiestRecipe
        Recepie easiestRecipe = recipeRepo.getEasiestRecipe();
        System.out.println("Recette la plus facile : " + easiestRecipe.getTitle());

    }
}
