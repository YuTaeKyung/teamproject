package teamproject.taekung.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import teamproject.taekung.dao.MainDao;

import java.util.List;

/**
 * Created by taeku on 2016-09-11.
 */
public class MainController {
    @FXML BorderPane main;
    @FXML TextField uid;
    @FXML PasswordField pwd;
    public void exitProgram(ActionEvent event) {
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

    public void loginAlert() throws Exception{

        List user = MainDao.setLogin(uid.getText(),pwd.getText());




/*

        if (uid.getText().equals("")){
            alert("아이디를 입력하세요");
        } else if(pwd.getText().equals("")){
            alert("비밀번호를 입력하세요");
        } else if(user.size()!=0){
            okLogin();
        } else alert("아이디와 비밀번호가 일치하지 않습니다");
*/



        okLogin();

    }

    public void alert(String s){
        Alert alt = new Alert(Alert.AlertType.WARNING);
        alt.setTitle("아이디/패스워드 입력 오류!!");
        alt.setHeaderText(null);
        alt.setContentText(s);
        alt.showAndWait();
    }

    public void okLogin()throws Exception{
        Alert alt = new Alert(Alert.AlertType.INFORMATION);
        alt.setTitle("로그인 성공 ");
        alt.setHeaderText(null);
        alt.setContentText("로그인 되었습니다! ");
       alt.showAndWait();




        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Index.fxml"));
        Parent root =loader.load();


        Stage stage = (Stage)main.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("도서대여프로그램");
        stage.setResizable(false);
        stage.show();

    }




    public void loginProgram(ActionEvent event)throws Exception{
        loginAlert();


    }

    public void joinProgram(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Join.fxml"));
        Parent root = (Parent)loader.load();

        Stage stage = new Stage();
        stage.setTitle("회원가입");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(main.getScene().getWindow());
        stage.show();

    }

    public void findInfo(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/FindID.fxml"));
        Parent root = loader.load();

        Stage stage= new Stage();
        stage.setTitle("아이디/비번 찾기");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(main.getScene().getWindow());
        stage.show();


    }

    public void alertLogin(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("로그인 메세지");
        alert.setHeaderText(null);
        alert.setContentText("로그인 후 이용하실 수 있습니다!!");
        alert.showAndWait();
    }

    public void showInfo(ActionEvent event) throws Exception{


    }
}
