/*package presentation;

import repositories.RecepieRepo;

import java.util.Scanner;

public class RecepiePrincipale {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\CAP\\PFapplication\\src\\main\\java\\recipes.xml";
        RecepieRepo recipeRepo = new RecepieRepo();
        recipeRepo.init(filePath);

        Scanner scanner = new Scanner(System.in);

        // Premier menu pour sélectionner le mode de présentation
        System.out.println("Sélectionnez le mode de présentation : ");
        System.out.println("1. Présentation textuelle");
        System.out.println("2. Présentation graphique");
        int presentationMode = scanner.nextInt();

        // Second menu pour illustrer les traitements possibles
        System.out.println("Sélectionnez le traitement souhaité : ");
        System.out.println("1. Afficher la liste des titres des recettes");
        System.out.println("2. Afficher le total des œufs utilisés");
        System.out.println("3. Afficher les recettes utilisant de l'huile d'olive");
        // Ajoutez d'autres options de traitement ici...
        switch (presentationMode) {
            case 1:
                // Affichage en mode texte (comme précédemment)
                System.out.println("Mode de présentation textuelle choisi.");
                int traitement = scanner.nextInt();

                switch (traitement) {
                    case 1:
                        recipeRepo.listRecipeTitles().forEach(System.out::println);
                        break;
                    case 2:
                        double totalEggsUsed = recipeRepo.getTotalEggsUsed();
                        System.out.println("Total des œufs utilisés : " + totalEggsUsed);
                        break;
                    case 3:
                        recipeRepo.getRecipesUsingOliveOil().forEach(recipe -> System.out.println("Titre : " + recipe.getTitle()));
                        break;
                    // Ajoutez d'autres cas pour d'autres traitements ici...
                    default:
                        System.out.println("Traitement non reconnu.");
                        break;
                }
                break;
            case 2:
                // Appel de l'interface graphique
                System.out.println("Mode de présentation graphique choisi.");
                // Créez une instance de l'interface graphique et affichez-la
                GraphicalInterface graphicalInterface = new GraphicalInterface("C:\\Users\\CAP\\PROJET_PR\\recipes.xml");
                graphicalInterface.display();
                break;
            default:
                System.out.println("Mode de présentation non reconnu.");
                break;
        }

    }
}*/
