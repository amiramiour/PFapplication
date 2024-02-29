package presentation;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import repositories.RecepieRepo;

import java.io.IOException;

public class DetailsReceipeController {

    @FXML
    private ChoiceBox<String> recipeTitlesBox;

    @FXML
    private Button showDetailsButton;

    @FXML
    private TextArea recipeDetailsArea;

    private RecepieRepo recepieRepo;

    public void initialize() {
        // Initialize RecepieRepo
        recepieRepo = new RecepieRepo();
        recepieRepo.init("C:\\Users\\CAP\\PFapplication\\src\\main\\java\\recipes.xml");

        // Populate ChoiceBox with recipe titles
        recipeTitlesBox.setItems(FXCollections.observableArrayList(recepieRepo.listRecipeTitles()));
    }


    @FXML
    public void onShowDetailsClicked(ActionEvent event) {
        String selectedTitle = recipeTitlesBox.getValue();
        String recipeDetails = recepieRepo.getRecipeDetails(selectedTitle);
        recipeDetailsArea.setText(recipeDetails);
    }

    @FXML
    private Button backButton;

    @FXML
    public void onBackButtonClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graphMode.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
