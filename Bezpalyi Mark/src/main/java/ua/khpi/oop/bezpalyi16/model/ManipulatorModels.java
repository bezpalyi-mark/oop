package ua.khpi.oop.bezpalyi16.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ua.khpi.oop.bezpalyi07.AddressBook;
import ua.khpi.oop.bezpalyi16.controller.ControlInput;
import ua.khpi.oop.bezpalyi16.view.ResultWindows;

import java.util.Collections;

public class ManipulatorModels {

    private Label sizeLabel;

    private final ControlInput controlInput = new ControlInput();

    public ManipulatorModels() {
        controlInput.loadData();
    }

    public HBox addSearchLine(final Stage s) {
        //Create nodes and adding correct spaceing
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
                AddressBook book = controlInput.getBooks().get(index - 1);
                new ResultWindows().newModalWindow(s, Collections.singletonList(controlInput.getBooks().get(index)));
            } else {
                new ResultWindows().newModalInfoWindow("Wrong index!", s);
            }
        });
        hbox.getChildren().addAll(searchButton, textField);
        return hbox;
    }

    public HBox addRemoveLine(final Stage s) {
        //Create nodes and adding correct spaceing
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
                new ResultWindows().newModalInfoWindow("Address Book deleted!", s);
            } else {
                new ResultWindows().newModalInfoWindow("Wrong index!", s);
            }
        });
        hbox.getChildren().addAll(button, textField);
        return hbox;
    }

    public HBox addRemoveAllLine(final Stage s) {
        //Create nodes and adding correct spaceing
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        Button button = new Button("Remove all address books");
        button.setPrefHeight(25);
        button.setOnAction(e -> {
            controlInput.getBooks().removeAll(controlInput.getBooks());
            sizeLabel.setText(updateSizeLabel());
            new ResultWindows().newModalInfoWindow("All books were deleted!", s);
        });
        hbox.getChildren().add(button);
        return hbox;
    }

    public HBox addShowAllLine(final Stage s) {
        //Create nodes and adding correct spaceing
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        Button button = new Button("Show all address books");
        button.setPrefHeight(25);
        button.setOnAction(e -> {
            new ResultWindows().newModalWindow(s, controlInput.getBooks());
        });
        hbox.getChildren().add(button);
        return hbox;
    }

    public Label addSizeLabelRaw() {
        sizeLabel = new Label(updateSizeLabel());
        return sizeLabel;
    }

    private String updateSizeLabel() {
        return "Container size: " + controlInput.getBooks().size();
    }
}
