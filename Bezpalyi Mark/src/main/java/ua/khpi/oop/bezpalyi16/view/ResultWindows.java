package ua.khpi.oop.bezpalyi16.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.khpi.oop.bezpalyi07.AddressBook;

import java.util.List;

/**
 * Class that generate result window.
 *
 * @author Bezpalyi M.
 * @version 1.0
 **/
public class ResultWindows {
    private final TableView<AddressBook> table = new TableView<>();

    public void newModalWindow(Stage stage, List<AddressBook> addressBooks) {
        ObservableList<AddressBook> data =
                FXCollections.observableArrayList(addressBooks);

        TableColumn<AddressBook, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<AddressBook, String>("firstName"));

        TableColumn<AddressBook, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<AddressBook, String>("lastName"));

        TableColumn<AddressBook, String> birthDayCol = new TableColumn<>("Date of birth");
        birthDayCol.setMinWidth(270);
        birthDayCol.setCellValueFactory(
                new PropertyValueFactory<AddressBook, String>("dateOfBirth"));

        TableColumn<AddressBook, String> addressCol = new TableColumn<>("Address");
        addressCol.setMinWidth(270);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<AddressBook, String>("address"));

        TableColumn<AddressBook, String> editCol = new TableColumn<>("Last edit");
        editCol.setMinWidth(230);
        editCol.setCellValueFactory(
                new PropertyValueFactory<AddressBook, String>("editDateAndTime"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, birthDayCol, addressCol, editCol);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);

        HBox hBox = new HBox();
        ScrollPane scrollPane = new ScrollPane(hBox);
        scrollPane.setFitToHeight(true);
        hBox.getChildren().add(label);
        BorderPane secondaryLayout = new BorderPane(scrollPane);
        secondaryLayout.setPadding(new Insets(15));
        secondaryLayout.setBottom(table);

        Scene secondScene = new Scene(secondaryLayout, 1000, 460);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Result");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(stage);

        // Set position of second window, related to primary window.
        newWindow.setX(620);
        newWindow.setY(400);

        newWindow.show();
    }

    public void newModalInfoWindow(String message, Stage stage) {
        Label secondLabel = new Label(message);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 400, 60);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle(message);
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(stage);

        // Set position of second window, related to primary window.
        newWindow.setX(620);
        newWindow.setY(400);

        newWindow.show();
    }
}
