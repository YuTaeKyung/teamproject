package teamproject.taekung.controller;

import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import teamproject.taekung.dao.MemberDAO;
import teamproject.taekung.model.MemberModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-13.
 */
public class MemberController implements Initializable {
    @FXML
    BorderPane main;
    @FXML
    TextField mno;
    @FXML TextField name;
    @FXML TextField phone;
    @FXML TextField cellphone;
    @FXML TextField birthdate;
    @FXML TextField addr;
    @FXML TextField email;
    @FXML
    TableView memberTable;
    @FXML TableColumn mnoTC;
    @FXML TableColumn nameTC;
    @FXML TableColumn phoneTC;
    @FXML TableColumn cellphoneTC;
    @FXML TableColumn birthdateTC;
    @FXML TableColumn addrTC;
    @FXML TableColumn emailTC;


    private ObservableList<MemberModel> mblist=null;
    List<MemberModel> ml = null;



    private static String findMember = "select * from member";
    private static String findMemberName = "select * from member where name = ? ";
    private static String findMemberCellphone = "select * from member where cellphone = ?";





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mnoTC.setCellValueFactory(new PropertyValueFactory<MemberModel,Integer>("mno"));
        nameTC.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("name"));
        phoneTC.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("phone"));
        cellphoneTC.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("cellphone"));
        birthdateTC.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("birthdate"));
        addrTC.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("addr"));
        emailTC.setCellValueFactory(new PropertyValueFactory<MemberModel,String>("email"));

        mblist= FXCollections.observableArrayList();

        List<MemberModel> ml = MemberDAO.selectMemberAll();

        for(MemberModel m : ml){
            mblist.add(m);
        }

        memberTable.setItems(mblist);
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

    public void goToRent(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/rent.fxml"));
        try {
            Parent root =loader.load();
            Stage stage =(Stage)main.getScene().getWindow();
            stage.setTitle("대여관리");
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

    public void insertMember(ActionEvent event) {



        MemberModel mm = new MemberModel(0,name.getText(),phone.getText(),cellphone.getText(),birthdate.getText(),addr.getText(),email.getText());
        MemberDAO.addMember(mm);




        mblist.clear();

        for(MemberModel m : MemberDAO.selectMemberAll()){
            mblist.add(m);
        }




    }

    public void selectMember(ActionEvent event) {



        if(name.getText().equals("")&&phone.getText().equals("")&&cellphone.getText().equals("")&&
                birthdate.getText().equals("")&&email.getText().equals("")){
             ml = MemberDAO.selectMemberAll();
        }
        else if(name.getText().length()!=0) {ml = MemberDAO.selectMemberName(findMemberName, name.getText());}
        else if(cellphone.getText().length()>0){ml = MemberDAO.selectMemberCellphone(findMemberCellphone,cellphone.getText());}

        mblist.clear();

        for(MemberModel m : ml){
            mblist.add(m);
        }

        memberTable.setItems(mblist);

    }

    public void updateMember(ActionEvent event) {

        MemberModel mm = new MemberModel(0,name.getText(),phone.getText(),cellphone.getText(),birthdate.getText(),addr.getText(),email.getText());
        MemberDAO.updateMember(mm,mno.getText());


        mblist.clear();

        for(MemberModel m : MemberDAO.selectMemberAll()) {
            mblist.add(m);
        }
    }

    public void deleteMember(ActionEvent event) {

        MemberDAO.deleteMember(mno.getText());

        mblist.clear();

        for(MemberModel m : MemberDAO.selectMemberAll()) {
            mblist.add(m);
        }
    }


    public void showData(MouseEvent event) {
        int num = memberTable.getSelectionModel().getSelectedIndex();

        if(event.getClickCount()==2){


            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/showMemberData.fxml"));
            try {
                Parent root = loader.load();

                showMemberDataController smd = loader.getController();
                smd.sendData(mblist,num);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(event.getClickCount()==1){
            mno.setText(String.valueOf(mblist.get(num).getMno()));
            name.setText(mblist.get(num).getName());
            phone.setText(mblist.get(num).getPhone());
            cellphone.setText(mblist.get(num).getCellphone());
            birthdate.setText(mblist.get(num).getBirthdate().substring(0,10));
            addr.setText(mblist.get(num).getAddr());
            email.setText(mblist.get(num).getEmail());
        }
    }

    public void reset(ActionEvent event) {
        mno.setText("");
        name.setText("");
        phone.setText("");
        cellphone.setText("");
        birthdate.setText("");
        addr.setText("");
        email.setText("");
    }
}
