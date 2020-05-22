package ua.khpi.oop.bezpalyi16.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi16.controller.ControlInput;

import java.util.List;

/**
 * Class that generate result windows.
 *
 * @author Bezpalyi M.
 * @version 1.0
 **/
public class ResultWindows {

    /**
     * Table structure to store view address books.
     */
    private final TableView<AddressBook> table = new TableView<>();

    /**
     * Method create new modal window with table which store result.
     *
     * @param stage        root stage.
     * @param addressBooks list with output address books.
     */
    public void newModalWindow(Stage stage, List<AddressBook> addressBooks) {
        ObservableList<AddressBook> data =
                FXCollections.observableArrayList(addressBooks);

        TableColumn<AddressBook, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        TableColumn<AddressBook, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        TableColumn<AddressBook, String> birthDayCol = new TableColumn<>("Date of birth");
        birthDayCol.setMinWidth(270);
        birthDayCol.setCellValueFactory(
                new PropertyValueFactory<>("dateOfBirth"));

        TableColumn<AddressBook, String> addressCol = new TableColumn<>("Address");
        addressCol.setMinWidth(270);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        TableColumn<AddressBook, String> editCol = new TableColumn<>("Last edit");
        editCol.setMinWidth(230);
        editCol.setCellValueFactory(
                new PropertyValueFactory<>("editDateAndTime"));

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
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(stage);
        newWindow.setX(620);
        newWindow.setY(400);
        newWindow.show();
    }

    /**
     * Method create modal window to print info.
     *
     * @param message message to print.
     * @param stage   root stage.
     */
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

    /**
     * Method create new modal window with form to add new address book.
     *
     * @param stage root stage.
     * @param input controller for check input data and add new address book to list.
     */
    public void newAddAddressBookForm(Stage stage, ControlInput input) {
        VBox vBox = new VBox();
        vBox.setSpacing(5);

        TextField fieldFN = new TextField();
        fieldFN.setPrefWidth(175);
        fieldFN.setPromptText("David");
        Label labelFN = new Label("  First name:       ");
        HBox hBoxFN = new HBox();
        hBoxFN.setSpacing(5);
        hBoxFN.getChildren().addAll(labelFN, fieldFN);

        TextField fieldSN = new TextField();
        fieldSN.setPrefWidth(175);
        fieldSN.setPromptText("Heidi");
        Label labelSN = new Label("  Second name:  ");
        HBox hBoxSN = new HBox();
        hBoxSN.setSpacing(5);
        hBoxSN.getChildren().addAll(labelSN, fieldSN);

        TextField fieldLN = new TextField();
        fieldLN.setPrefWidth(175);
        fieldLN.setPromptText("Olsen");
        Label labelLN = new Label("  Last name:       ");
        HBox hBoxLN = new HBox();
        hBoxLN.setSpacing(5);
        hBoxLN.getChildren().addAll(labelLN, fieldLN);

        TextField fieldDate = new TextField();
        fieldDate.setPrefWidth(210);
        fieldDate.setPromptText("2000-10-20");
        Label labelDate = new Label("  Birthday:          ");
        HBox hBoxDate = new HBox();
        hBoxDate.setSpacing(5);
        hBoxDate.getChildren().addAll(labelDate, fieldDate);

        TextField fieldAddress = new TextField();
        fieldAddress.setPrefWidth(210);
        fieldAddress.setPromptText("[any format]");
        Label labelAddress = new Label("  Address:           ");
        HBox hBoxAddress = new HBox();
        hBoxAddress.setSpacing(5);
        hBoxAddress.getChildren().addAll(labelAddress, fieldAddress);

        TextField fieldPhone = new TextField();
        fieldPhone.setPrefWidth(210);
        fieldPhone.setPromptText("0996887364");
        Label labelPhone = new Label("  Phone:              ");
        HBox hBoxPhone = new HBox();
        hBoxPhone.setSpacing(5);
        hBoxPhone.getChildren().addAll(labelPhone, fieldPhone);

        Button button = new Button("Submit");
        button.setPrefHeight(25);
        button.setOnAction(e -> {
            String added;
            added = input.addNewAddressBook(fieldFN.getText(), fieldLN.getText(), fieldSN.getText(),
                    fieldDate.getText(), fieldAddress.getText(), fieldPhone.getText());
            if (added == null) {
                new ResultWindows().newModalInfoWindow("Address book successfully added", stage);
            } else {
                new ResultWindows().newModalInfoWindow("Error, invalid data: " + added, stage);
            }
        });
        HBox hBoxButton = new HBox();
        Label empty = new Label("                             ");
        hBoxButton.getChildren().addAll(empty, button);
        Label label = new Label("After adding click 'Update...' button");
        label.setFont(new Font("Courier New", 15));
        vBox.getChildren().addAll(hBoxFN, hBoxSN, hBoxLN, hBoxDate, hBoxAddress, hBoxPhone, hBoxButton, label);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(vBox);

        Scene secondScene = new Scene(secondaryLayout, 500, 260);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Adding new Address Book");
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
