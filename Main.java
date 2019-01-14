/*
*   Anthony Ronca & Danny Ross
*   CIT230 - Midterm
*   Question 2
*
*   Conversion Application  -   This application creates an interactive user interface that allows a user to enter
*   the amount and the measurement unit, then the conversion measurement unit they wish to convert
*   their initial value to
*
*   VARIABLE LIST:
*   Double startValue   -   conversion of textValue to a double data type
*   String startUnit    -   selected in first drop down menu
*   String endUnit      -   selected in second drop down menu
*
*   JAVAFX NODES:
*   ChoiceBox<String> choiceBox     -   Menu for pre-conversion unit measurements
    ChoiceBox<String> choiceBox2    -   Menu for pre-conversion unit measurements
    Label promptValue               -   Asks for double value
    Label promptTo                  -   Asks for conversion type
    TextField textValue             -   Stores user's number input
    Button done                     -   Confirms the user is finished entering data
*
*
 */

package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // Variables Used:
    private static String startUnit;    // stores pre-converted unit measurement
    private static String endUnit;      // store post-conversion unit measurement
    private static Double startValue;   // stores TextField as a double

    // Javafx nodes used:
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();         // Drop Down menu for initial unit measurement
    private ChoiceBox<String> choiceBox2 = new ChoiceBox<>();        // Drop Down menu for converted unit measurement
    private Label promptValue = new Label("Enter amount: ");    // Direction to enter a value
    private Label promptTo = new Label("to: ");                 // Prompts for conversion measurement unit
    private TextField textValue = new TextField("0.0");              // Holds a TextField for initial value, is then converted to Double
    private Button done = new Button("Done");                   // Finalizes variables and calculations

    @Override
    public void start(Stage primaryStage) throws Exception {


        // * FIRST DROP DOWN MENU

        choiceBox.setValue("Select");   // Set default to Select
        // Populate menu with available options
        choiceBox.getItems().addAll("Teaspoons","Tablespoons","Ounces","Cups",
                "Pints","Quarts","Gallons");
        // Listener that updates startUnit variable whenever an item is selected
        choiceBox.getSelectionModel().selectedItemProperty()
                .addListener( (v, oldValue, newValue) -> startUnit = newValue );


        // * SECOND DROP DOWN MENU

        // Populate menu for second drop down menu which gives final unit measurement
        choiceBox2.getItems().addAll("Teaspoons","Tablespoons",
                "Ounces","Cups","Pints","Quarts","Gallons");
        choiceBox2.setValue("Select"); // Default
        // Listener that updates endUnit variable with whatever is selected
        choiceBox2.getSelectionModel().selectedItemProperty()
                .addListener( (v2, oldValue2, newValue2) -> endUnit =  newValue2 );


        // * DONE BUTTON ACTION HANDLER

        done.setOnAction(event -> { // runs when done button is clicked
            try {
                // Store endUnit before it is abbreviated to match the formula
                String displayEndUnit = endUnit;

                // GET startValue by parsing TextField textValue
                startValue = Double.parseDouble(textValue.getText());

                // Output results
                System.out.print( startValue + " " + startUnit +" to ");

                // Plug values into conversionController for final output
                System.out.print(Part1.conversionController
                        (startValue, startUnit, endUnit));
                System.out.print(" "); // output
                System.out.println(displayEndUnit); // display final result

                //Any excetpions result in an error message.
            } catch(Exception e) {
                System.out.println("Error. Please enter a number.");
            }
        }); //done event

        primaryStage.setTitle("Conversion Application");
        VBox layout = new VBox(15); // VBox layout
        layout.setPadding(new Insets(20, 20, 20, 20));
        // Add Javafx nodes/scene
        layout.getChildren().addAll(promptValue, textValue, choiceBox, promptTo, choiceBox2, done);
        primaryStage.setScene(new Scene(layout, 600, 450));
        primaryStage.show(); // Show Javafx window

    }


    public static void main(String[] args) {
        launch(args);

    }

    } //Main



