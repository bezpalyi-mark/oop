package ua.khpi.oop.bezpalyi16.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.khpi.oop.bezpalyi16.model.ManipulatorModels;

/**
 * Class that realize main window.
 *
 * @author Bezpalyi M.
 * @version 1.0
 **/
public class MainWindow extends Application {

    private final ManipulatorModels manipulatorModels;

    public MainWindow() {
        manipulatorModels = new ManipulatorModels();
    }

    public void go(String[] argv) {
        Application.launch(argv);
    }


    @Override
    public void start(final Stage s) {

        s.setTitle("Container Manipulator");
        AnchorPane pane = new AnchorPane();
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(
                manipulatorModels.addNewAdressBookButton(s),
                manipulatorModels.addSearchButton(s),
                manipulatorModels.addShowAllButton(s),
                manipulatorModels.addRemoveButton(s),
                manipulatorModels.addRemoveAllButton(s),
                manipulatorModels.addUpdateButton(s),
                manipulatorModels.addSizeLabelRaw());
        pane.getChildren().add(vBox1);
        Scene sc = new Scene(pane, 700, 400);
        s.setScene(sc);
        s.show();
    }
}