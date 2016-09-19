package teamproject.taekung.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import teamproject.taekung.dao.FindMemberDAO;
import teamproject.taekung.model.MemberModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by taeku on 2016-09-11.
 */
public class IndexController implements Initializable {

    @FXML
    private TableView showMember;
    @FXML private TableView showBook;
    @FXML private TableView showRent;
    @FXML private TextField memberText;
    @FXML private TextField bookText;
    @FXML private TextField lentText;
    @FXML private BorderPane main;
    @FXML private TableColumn mno;
    @FXML private TableColumn name;
    @FXML private TableColumn phone;
    @FXML private TableColumn cellphone;
    @FXML private TableColumn birthdate;
    @FXML private TableColumn email;
    @FXML private TableColumn addr;
    @FXML private Label nameLB;
    @FXML private Label phoneLB;
    @FXML private Label cellphoneLB;
    @FXML private Label addrLB;
    @FXML private Label emailLB;
    @FXML private Label birthdateLB;

    @FXML private MenuItem selectName;
    @FXML private MenuItem selectPhone;
    @FXML private SplitMenuButton memberSelect;

    private ObservableList<MemberModel> mblist = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mno.setCellValueFactory(new PropertyValueFactory<MemberModel,Integer>("mno"));
        name.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<MemberModel,String >("phone"));
        cellphone.setCellValueFactory(new PropertyValueFactory<MemberModel,String >("cellphone"));
        birthdate.setCellValueFactory(new PropertyValueFactory<MemberModel,String >("birthdate"));
        email.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("email"));
        addr.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("addr"));

        mblist = FXCollections.observableArrayList();


        List<MemberModel> ml = FindMemberDAO.listMember();

        for(MemberModel m : ml){
            mblist.add(m);
        }

        showMember.setItems(mblist);
    }


    public void exit(ActionEvent event) {
        Alert exitConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        exitConfirm.setTitle("프로그램 종료");
        exitConfirm.setHeaderText(null);
        exitConfirm.setContentText("프로그램을 종료하시겠습니까?");

        ButtonType okbtn = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType nobtn = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

        exitConfirm.getButtonTypes().setAll(okbtn,nobtn);

        Optional<ButtonType> result = exitConfirm.showAndWait();

        if(result.get()== okbtn) Platform.exit();
        else event.consume();
    }


    public void findMember(ActionEvent event) {
        showMember.setVisible(true);
        showBook.setVisible(false);
        showRent.setVisible(false);
        bookText.setText("");
        lentText.setText("");



        if(memberText.getText().equals("")) {
            List<MemberModel> ml = FindMemberDAO.listMember();


            mblist.clear();

            for (MemberModel m : ml) {
                mblist.add(m);
            }

            showMember.setItems(mblist);
        } else if (memberSelect.getText().equals("이름")){

            List<MemberModel> ml = FindMemberDAO.listMemberForName(memberText.getText());


            mblist.clear();

            for (MemberModel m : ml) {
                mblist.add(m);
            }

            showMember.setItems(mblist);


        } else if (memberSelect.getText().equals("전화번호")){


            List<MemberModel> ml = FindMemberDAO.listMemberForPhone(memberText.getText());


            mblist.clear();

            for (MemberModel m : ml) {
                mblist.add(m);
            }

            showMember.setItems(mblist);
        }


    }

    public void findBook(ActionEvent event) {
        showMember.setVisible(false);
        showBook.setVisible(true);
        showRent.setVisible(false);
        memberText.setText("");
        lentText.setText("");
    }

    public void findLent(ActionEvent event) {
        showMember.setVisible(false);
        showBook.setVisible(false);
        showRent.setVisible(true);
        memberText.setText("");
        bookText.setText("");
    }

    public void goHome(ActionEvent event) {
        showMember.setVisible(false);
        showBook.setVisible(false);
        showRent.setVisible(false);
        memberText.setText("");
        bookText.setText("");
        lentText.setText("");
    }



    public void goToMember(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/member.fxml"));
        try {
            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle("회원관리");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void goToBook(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/book.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("도서관리");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToRent(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/rent.fxml"));

        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("대여관리");

            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getScene().getWindow());
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void memberLabel(MouseEvent e) {
        int num = showMember.getSelectionModel().getSelectedIndex();

        if(e.getClickCount()==1){

            nameLB.setText(mblist.get(num).getName());
            phoneLB.setText(mblist.get(num).getPhone());
            cellphoneLB.setText(mblist.get(num).getCellphone());
            birthdateLB.setText(mblist.get(num).getBirthdate());
            addrLB.setText(mblist.get(num).getAddr());
            emailLB.setText(mblist.get(num).getEmail());
        } else if(e.getClickCount()==2){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("../view/showMemberData.fxml"));
            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                showMemberDataController smd = loader.getController();
                smd.sendData(mblist,num);

                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(main.getScene().getWindow());
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void selectOption(ActionEvent event) {
        memberSelect.setText(selectName.getText());
    }

    public void selectOption2(ActionEvent event) {
        memberSelect.setText(selectPhone.getText());
    }
}
