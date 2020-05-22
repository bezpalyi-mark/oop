package ua.khpi.oop.bezpalyi16.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ua.khpi.oop.bezpalyi16.controller.ControlInput;
import ua.khpi.oop.bezpalyi16.view.ResultWindows;

import java.util.Collections;

/**
 * Class - model of application.
 *
 * @author Bezpalyi M.
 * @version 1.0
 */
public class ManipulatorModels {

    /**
     * Label for size.
     */
    private Label sizeLabel;

    /**
     * Controller.
     */
    private final ControlInput controlInput = new ControlInput();

    public ManipulatorModels() {
        controlInput.loadData();
    }

    /**
     * Button for update size information.
     *
     * @param stage root stage.
     * @return horizontal box with button.
     */
    public HBox addUpdateButton(final Stage stage) {
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        Button button = new Button("Update...");
        button.setPrefHeight(25);
        button.setOnAction(e -> {
            sizeLabel.setText(updateSizeLabel());
        });
        hbox.getChildren().add(button);
        return hbox;
    }

    /**
     * Button for add new address book.
     * Call for new modal window with input.
     *
     * @param stage root stage.
     * @return horizontal box with button.
     */
    public HBox addNewAddressBookButton(final Stage stage) {
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        Button button = new Button("Add new address book");
        button.setPrefHeight(25);
        button.setOnAction(e -> {
            new ResultWindows().newAddAddressBookForm(stage, controlInput);

            sizeLabel.setText(updateSizeLabel());
        });
        hbox.getChildren().add(button);
        return hbox;
    }

    /**
     * Button and text field for search by index.
     * Call for new modal window with result.
     *
     * @param stage root stage.
     * @return horizontal box with button and text field.
     */
    public HBox addSearchButton(final Stage stage) {
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        TextField textField = new TextField();
        textField.setPrefWidth(93);
        textField.setPromptText("enter index");

        Button searchButton = new Button("Search by index");
        searchButton.setPrefHeight(25);
        searchButton.setOnAction(e -> {
            int index = controlInput.getIndex(textField.getText());
            if (index != -1) {
                new ResultWindows().newModalWindow(stage, Collections.singletonList(controlInput.getBooks().get(index - 1)));
            } else {
                new ResultWindows().newModalInfoWindow("Wrong index!", stage);
            }
        });
        hbox.getChildren().addAll(searchButton, textField);
        return hbox;
    }

    /**
     * Button for remove address book by index.
     * Call modal result window.
     *
     * @param stage root stage.
     * @return horizontal box with button and text field.
     */
    public HBox addRemoveButton(final Stage stage) {
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        TextField textField = new TextField();
        textField.setPrefWidth(93);
        textField.setPromptText("enter index");

        Button button = new Button("Remove by index");
        button.setPrefHeight(25);
        button.setOnAction(e -> {
            int index = controlInput.getIndex(textField.getText());
            if (index != -1) {
                controlInput.getBooks().remove(index - 1);
                sizeLabel.setText(updateSizeLabel());
                new ResultWindows().newModalInfoWindow("Address Book deleted!", stage);
            } else {
                new ResultWindows().newModalInfoWindow("Wrong index!", stage);
            }
        });
        hbox.getChildren().addAll(button, textField);
        return hbox;
    }

    /**
     * Button for remove all address books.
     *
     * @param stage root stage.
     * @return horizontal box with button.
     */
    public HBox addRemoveAllButton(final Stage stage) {
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        Button button = new Button("Remove all address books");
        button.setPrefHeight(25);
        button.setOnAction(e -> {
            controlInput.getBooks().removeAll(controlInput.getBooks());
            sizeLabel.setText(updateSizeLabel());
            new ResultWindows().newModalInfoWindow("All books were deleted!", stage);
        });
        hbox.getChildren().add(button);
        return hbox;
    }

    /**
     * Button for show all address book.
     *
     * @param stage root stage.
     * @return horizontal box with button.
     */
    public HBox addShowAllButton(final Stage stage) {
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        Button button = new Button("Show all address books");
        button.setPrefHeight(25);
        button.setOnAction(e -> new ResultWindows().newModalWindow(stage, controlInput.getBooks()));
        hbox.getChildren().add(button);
        return hbox;
    }

    /**
     * Method for get size label.
     *
     * @return label with size of container.
     */
    public Label addSizeLabelRaw() {
        sizeLabel = new Label(updateSizeLabel());
        return sizeLabel;
    }

    /**
     * Method get updated size.
     *
     * @return string with updated size.
     */
    private String updateSizeLabel() {
        return "Container size: " + controlInput.getBooks().size();
    }
}
