package teamproject.taekung.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import teamproject.taekung.dao.RentDAO;
import teamproject.taekung.model.RentModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-13.
 */
public class RentController implements Initializable {
    @FXML
    BorderPane main;
    @FXML TextField rno;
    @FXML TextField mno;
    @FXML TextField bno;
    @FXML TextField regdate;
    @FXML TextField duedate;
    @FXML TableView rentTable;
    @FXML TableColumn rnoTC;
    @FXML TableColumn mnoTC;
    @FXML TableColumn bnoTC;
    @FXML TableColumn regdateTC;
    @FXML TableColumn duedateTC;

    private ObservableList<RentModel> rlist = null;
    List<RentModel> rl= null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }



    public void goToMember(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/member.fxml"));
        try {
            Parent root =loader.load();
            Stage stage =(Stage)main.getScene().getWindow();
            stage.setTitle("회원관리");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToBook(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/book.fxml"));
        try {
            Parent root =loader.load();
            Stage stage =(Stage)main.getScene().getWindow();
            stage.setTitle("도서관리");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitModal(ActionEvent event) {
        Stage stage =(Stage)main.getScene().getWindow();
        stage.close();

    }

    public void selectRent(ActionEvent event) {

    }

    public void updateRent(ActionEvent event) {
    }

    public void addRent(ActionEvent event) {
        RentModel r = new RentModel(0,Integer.parseInt(mno.getText()),Integer.parseInt(bno.getText()),"","");
        RentDAO.addRent(r);
    }

    public void deleteRent(ActionEvent event) {
    }

    public void reset(ActionEvent event) {
    }

    public void showRent(MouseEvent event) {
    }


}
