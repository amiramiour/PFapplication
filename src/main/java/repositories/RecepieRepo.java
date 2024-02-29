package repositories;

import models.Ingredient;
import models.Nutrition;
import models.Recepie;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecepieRepo {

    public List<Recepie> recipes;

    public RecepieRepo() {
        this.recipes = new ArrayList<>();
    }

    public List<Recepie> getRecipes() {
        return recipes;
    }

    public void init(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList recipeNodes = doc.getElementsByTagName("rcp:recipe");
            for (int i = 0; i < recipeNodes.getLength(); i++) {
                Element recipeElement = (Element) recipeNodes.item(i);

                String id = recipeElement.getAttribute("id");
                String title = getTagValue(recipeElement, "rcp:title");
                String date = getTagValue(recipeElement, "rcp:date");
                List<Ingredient> ingredients = parseIngredients(recipeElement.getElementsByTagName("rcp:ingredient"));
                List<String> preparation = parsePreparation(recipeElement.getElementsByTagName("rcp:preparation"));
                String comment = getTagValue(recipeElement, "rcp:comment");
                Nutrition nutrition = parseNutrition(recipeElement.getElementsByTagName("rcp:nutrition"));
                String related = getTagValue(recipeElement, "rcp:related");

                Recepie recipe = new Recepie(id, title, date, ingredients, preparation, comment, nutrition, related);
                recipes.add(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> parsePreparation(NodeList preparationNodes) {
        List<String> preparationSteps = new ArrayList<>();
        for (int i = 0; i < preparationNodes.getLength(); i++) {
            Element preparationElement = (Element) preparationNodes.item(i);
            NodeList stepNodes = preparationElement.getElementsByTagName("rcp:step");
            for (int j = 0; j < stepNodes.getLength(); j++) {
                Element stepElement = (Element) stepNodes.item(j);
                String step = stepElement.getTextContent().trim();
                preparationSteps.add(step);
            }
        }
        return preparationSteps;
    }

    private String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node.hasChildNodes()) {
                return node.getFirstChild().getNodeValue();
            }
        }
        return null;
    }


    private List<Ingredient> parseIngredients(NodeList ingredientNodes) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientNodes.getLength(); i++) {
            Element ingredientElement = (Element) ingredientNodes.item(i);
            String name = ingredientElement.getAttribute("name");
            String amountStr = ingredientElement.getAttribute("amount");
            double amount;
            if (amountStr.equals("*")) {
                amount = 0.0; // Valeur par défaut
            } else {
                try {
                    amount = Double.parseDouble(amountStr);
                } catch (NumberFormatException e) {
                    amount = 0.0; // Valeur par défaut en cas d'erreur de conversion
                }
            }
            String unit = ingredientElement.getAttribute("unit");
            Ingredient ingredient = new Ingredient(name, amount, unit);
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    private Nutrition parseNutrition(NodeList nutritionNodes) {
        Element nutritionElement = (Element) nutritionNodes.item(0);
        int calories = Integer.parseInt(nutritionElement.getAttribute("calories"));
        String fat = nutritionElement.getAttribute("fat");
        String carbohydrates = nutritionElement.getAttribute("carbohydrates");
        String protein = nutritionElement.getAttribute("protein");
        String alcohol = nutritionElement.getAttribute("alcohol");
        return new Nutrition(calories, fat, carbohydrates, protein, alcohol);
    }
    ///////4
    public List<String> listRecipeTitles() {
        return recipes.stream()
                .map(Recepie::getTitle)
                .collect(Collectors.toList());
    }
    ///////////5
    public double getTotalEggsUsed() {
        return recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .filter(ingredient -> ingredient.getName().toLowerCase().contains("egg"))
                .mapToDouble(Ingredient::getAmount)
                .sum();
    }
    ///////////6
    public List<Recepie> getRecipesUsingOliveOil() {
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients()
                        .stream()
                        .anyMatch(ingredient -> ingredient.getName().equalsIgnoreCase("olive oil")))
                .collect(Collectors.toList());
    }
    /////////////7
    public List<Integer> calculateEggsPerRecipe() {
        List<Integer> eggsPerRecipe = new ArrayList<>();
        for (Recepie recipe : recipes) {
            int eggsCount = (int) recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getName().toLowerCase().contains("egg"))
                    .mapToDouble(Ingredient::getAmount)
                    .sum();
            eggsPerRecipe.add(eggsCount);
        }
        return eggsPerRecipe;
    }
    ///////////8
    public List<Recepie> getRecipesWithLessThan500Calories() {
        return recipes.stream()
                .filter(recipe -> recipe.getNutrition().getCalories() < 500)
                .collect(Collectors.toList());
    }
    ////////9
    public double zuppainglesesugar(){
        return recipes.stream()
                .filter(n->n.getTitle().equals("Zuppa Inglese"))
                .flatMap(recipe -> recipe.getIngredients().stream())
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase("sugar"))
                .mapToDouble(Ingredient::getAmount)
                .sum();
    }
    //////////10
    public List<String> getFirstTwoStepsOfZuppaInglese() {
        List<String> firstTwoSteps = new ArrayList<>();

        // Rechercher la recette "Zuppa Inglese" dans la liste des recettes
        recipes.stream()
                .filter(recipe -> recipe.getTitle().equalsIgnoreCase("Zuppa Inglese"))
                .findFirst()
                .ifPresent(recipe -> {
                    // Trouvé la recette "Zuppa Inglese", maintenant ajouter les deux premières étapes à la liste
                    recipe.getPreparation().stream()
                            .limit(2)
                            .forEachOrdered(step -> firstTwoSteps.add(step));
                });

        return firstTwoSteps;
    }

    //////////11
    public List<Recepie> getRecipesWithMoreThanFiveSteps() {
        return recipes.stream()
                .filter(recipe -> recipe.getPreparation().size() > 5)
                .collect(Collectors.toList());
    }
    ///////////12
    public List<Recepie> getRecipesWithoutButter() {
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients()
                        .stream()
                        .noneMatch(ingredient -> ingredient.getName().equalsIgnoreCase("butter")))
                .collect(Collectors.toList());
    }
    ///////////13
    public List<Recepie> getRecipesWithCommonIngredientsWithZuppaInglese() {
        List<String> ingredientsZuppaInglese = recipes.stream()
                .filter(recipe -> recipe.getTitle().equals("Zuppa Inglese"))
                .flatMap(recipe -> recipe.getIngredients().stream())
                .map(Ingredient::getName)
                .collect(Collectors.toList());

        // Filtrer les recettes ayant les mêmes ingrédients que la recette Zuppa Inglese
        return recipes.stream()
                .filter(recipe -> recipe != null && !recipe.getTitle().equalsIgnoreCase("Zuppa Inglese")) // Exclure la recette Zuppa Inglese elle-même
                .filter(recipe -> recipe.getIngredients().stream()
                        .map(Ingredient::getName)
                        .anyMatch(ingredientsZuppaInglese::contains))
                .collect(Collectors.toList());
    }
    /////////14
    public Recepie getMostCaloricRecipe() {
        return recipes.stream()
                .max(Comparator.comparingDouble(recipe -> recipe.getNutrition().getCalories()))
                .orElse(null);
    }
    /////////15
    public String getMostFrequentUnit() {
        Map<String, Long> uniteCounts = recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .map(Ingredient::getUnit)
                .filter(unit -> unit != null && !unit.isEmpty()) // Exclure les chaînes vides

                .collect(Collectors.groupingBy(unit -> unit, Collectors.counting()));

        return uniteCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Aucune unité trouvée");
    }
    /////////16
    public Map<Recepie, Integer> calculateIngredientsCountPerRecipe() {
        return recipes.stream()
                .collect(Collectors.toMap(
                        recipe -> recipe,
                        recipe -> recipe.getIngredients().size()
                ));
    }
    //////////17
    public Recepie getRecipeWithMostFat() {
        return recipes.stream()
                .max(Comparator.comparing(r->r.getNutrition().getFat()))
                .orElseThrow(() -> new RuntimeException("Aucune recette trouvée"));
    }
    ///////////18
    public String getMostUsedIngredient() {
        Map<String, Long> ingredientUsageMap = recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream())
                .collect(Collectors.groupingBy(Ingredient::getName, Collectors.counting()));

        return ingredientUsageMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    //////////19
    public List<Recepie> sortRecipesByIngredientCount() {
        return recipes.stream()
                .sorted(Comparator.comparingInt(recipe -> recipe.getIngredients().size()))
                .collect(Collectors.toList());
    }
    ////////20
    public Map<String, List<String>> getRecipesByIngredient() {
        return recipes.stream()
                .flatMap(recipe -> recipe.getIngredients().stream()
                        .map(Ingredient::getName)
                        .distinct() // Supprime les doublons d'ingrédients
                        .map(ingredient -> Map.entry(ingredient, recipe.getTitle())))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }
    /////////21
    public Map<Integer, List<Recepie>> groupRecipesByNumberOfSteps() {
        return recipes.stream()
                // Regrouper les recettes par nombre d'étapes
                .collect(Collectors.groupingBy(recipe -> recipe.getPreparation().size()));
    }
    //////////22
    public Recepie getEasiestRecipe() {
        return recipes.stream()
                .min(Comparator.comparingInt(recipe -> recipe.getPreparation().size()))
                .orElse(null);
    }
    /////details d'une recette
    public String getRecipeDetails(String title) {
        // Recherche de la recette par titre
        List<Recepie> filteredRecipes = recipes.stream()
                .filter(recipe -> recipe.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());

        // Si la recette est trouvée, récupérez ses détails sous forme de chaîne
        if (!filteredRecipes.isEmpty()) {
            Recepie recipe = filteredRecipes.get(0);
            StringBuilder detailsBuilder = new StringBuilder();
            detailsBuilder.append("Title: ").append(recipe.getTitle()).append("\n");
            detailsBuilder.append("Ingredients:\n");
            for (Ingredient ingredient : recipe.getIngredients()) {
                detailsBuilder.append("- ").append(ingredient.getName())
                        .append(": ").append(ingredient.getAmount())
                        .append(" ").append(ingredient.getUnit()).append("\n");
            }
            detailsBuilder.append("\nPreparation:\n");
            int stepNumber = 1;
            for (String step : recipe.getPreparation()) {
                detailsBuilder.append(stepNumber).append(". ").append(step).append("\n");
                stepNumber++;
            }
            // Ajoutez d'autres détails si nécessaire
            return detailsBuilder.toString();
        } else {
            // Si la recette n'est pas trouvée, retournez une chaîne indiquant qu'elle n'existe pas
            return "Recipe not found.";
        }
    }









}
