package teamproject.taekung;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by taeku on 2016-09-11.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Main.fxml"));
        Parent root = loader.load();
        stage.setTitle("도서대여프로그램");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("taekung/img/새글쓰기.png"));
        stage.show();

        stage.setOnCloseRequest(event -> alertExit(event));
    }

    private void alertExit(WindowEvent event) {
        Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
        alt.setTitle("프로그램 종료");
        alt.setHeaderText(null);
        alt.setContentText("프로그램을 종료하시겠습니까?");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        alt.getButtonTypes().setAll(ok,no);

        String text = alt.showAndWait().get().getText();

        if(text.equals("OK")){
            Platform.exit();
        } else event.consume();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
