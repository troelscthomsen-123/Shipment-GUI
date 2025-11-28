package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //my url to connect jdbc & sql
        String url = "jdbc:sqlite:C:/Users/Troel/test1.db";

        Model shipModel = new Model(url);

        Controller control = new Controller(shipModel);

        View view = new View(control);
        control.settingView(view);
        primaryStage.setTitle("Shipment GUI");
        // The size of the GUI and setting the display
        primaryStage.setScene(new Scene(view.asParent(), 600, 475));
        // This part shows the GUi on the screen
        primaryStage.show();

    }

}
