/*package presentation;

import repositories.RecepieRepo;

import javax.swing.*;
import java.awt.*;

public class GraphicalInterface {
    // Champ pour stocker l'instance de RecepieRepo
    private RecepieRepo recipeRepo;

    // Constructeur
    public GraphicalInterface(String filePath) {
        // Initialisation de l'instance de RecepieRepo
        recipeRepo = new RecepieRepo();
        recipeRepo.init(filePath);
    }

    // Méthode display non statique
    public void display() {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Interface graphique");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Augmentez la taille pour une meilleure lisibilité

        // Création d'une zone de texte pour afficher les résultats
        JTextArea resultsArea = new JTextArea();
        resultsArea.setEditable(false); // Rendre la zone de texte non modifiable

        // Ajout de la zone de texte à un JScrollPane pour permettre le défilement si nécessaire
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        frame.getContentPane().add(scrollPane);

        // Création d'un bouton pour afficher les titres des recettes
        JButton titlesButton = new JButton("Afficher les titres des recettes");
        titlesButton.addActionListener(e -> {
            StringBuilder titles = new StringBuilder();
            recipeRepo.listRecipeTitles().forEach(title -> titles.append(title).append("\n"));
            resultsArea.setText(titles.toString()); // Afficher les titres dans la zone de texte
        });

        // Création d'un bouton pour afficher le total des œufs utilisés
        JButton eggsButton = new JButton("Afficher le total des œufs utilisés");
        eggsButton.addActionListener(e -> {
            double totalEggsUsed = recipeRepo.getTotalEggsUsed();
            resultsArea.setText("Total des œufs utilisés : " + totalEggsUsed);
        });
        // Création d'un bouton pour afficher les recettes utilisant de l'huile d'olive
     /*   JButton oliveOilButton = new JButton("Les recettes utilisant l'huile d'olive");
        oliveOilButton.addActionListener(e -> {
            StringBuilder oliveOilRecipes = new StringBuilder();
            recipeRepo.getRecipesUsingOliveOil().forEach(recipe -> {
                oliveOilRecipes.append("Titre : ").append(recipe.getTitle()).append("\n");
            });
            resultsArea.setText(oliveOilRecipes.toString()); // Afficher les recettes dans la zone de texte
        });


        // Création d'un panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(titlesButton);
        buttonPanel.add(eggsButton);
       // buttonPanel.add(oliveOilButton); // Ajouter le bouton pour les recettes à l'huile d'olive

        // Ajout du panneau de boutons à la fenêtre
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Affichage de la fenêtre
        frame.setVisible(true);
    }
}
*/