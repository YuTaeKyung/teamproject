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
import teamproject.taekung.dao.BookDAO;
import teamproject.taekung.model.BookModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-13.
 */
public class BookController implements Initializable {
    @FXML BorderPane main;
    @FXML TextField bno;
    @FXML TextField bname;
    @FXML TextField genre;
    @FXML TextField author;
    @FXML TextField publisher;
    @FXML TableView bookTable;
    @FXML TableColumn bnoTC;
    @FXML TableColumn bnameTC;
    @FXML TableColumn genreTC;
    @FXML TableColumn authorTC;
    @FXML TableColumn publisherTC;

    private ObservableList<BookModel> blist=null;
    List<BookModel> bl = null;


    private static String findbookname = "select * from book where bname = ? ";
    private static String findBno = "select * from book where bno = ? ";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bnoTC.setCellValueFactory(new PropertyValueFactory<BookModel,Integer>("bno"));
        bnameTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("bname"));
        genreTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("genre"));
        authorTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("author"));
        publisherTC.setCellValueFactory(new PropertyValueFactory<BookModel,String>("publisher"));

        blist= FXCollections.observableArrayList();

        List<BookModel> bl = BookDAO.selectBookAll();

        for(BookModel b : bl){

            blist.add(b);
        }

        bookTable.setItems(blist);

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

    public void selectBook(ActionEvent event) {
        if(bno.getText().equals("")&&bname.getText().equals("")&&genre.getText().equals("")&&
                author.getText().equals("")&&publisher.getText().equals("")){
            bl = BookDAO.selectBookAll();
        }
        else if(bname.getText().length()!=0) {bl = BookDAO.selectBookName(findbookname, bname.getText());}
        else if(bno.getText().length()>0){bl = BookDAO.selectBookBno(findBno, bno.getText());
        }

        blist.clear();

        for(BookModel b : bl){
            blist.add(b);
        }

        bookTable.setItems(blist);
    }

    public void updateBook(ActionEvent event) {
        BookModel bm = new BookModel(Integer.parseInt(bno.getText()),bname.getText(),genre.getText(),author.getText(),publisher.getText());
        BookDAO.updateBook(bm,bno.getText());

        blist.clear();
        for(BookModel b : BookDAO.selectBookAll()){
            blist.add(b);
        }
    }

    public void addBook(ActionEvent event) {

        BookModel bm = new BookModel(Integer.parseInt(bno.getText()),bname.getText(),genre.getText(),author.getText(),publisher.getText());
        BookDAO.addBook(bm);

        blist.clear();

        for(BookModel b : BookDAO.selectBookAll()){

            blist.add(b);
        }


    }

    public void deleteBook(ActionEvent event) {

        BookDAO.deleteMember(bno.getText());

        blist.clear();

        for(BookModel b : BookDAO.selectBookAll()) {
            blist.add(b);
        }
    }


    public void reset(ActionEvent event) {
        bno.setText("");
        bname.setText("");
        genre.setText("");
        author.setText("");
        publisher.setText("");
    }

    public void showBook(MouseEvent e) {
        int num = bookTable.getSelectionModel().getSelectedIndex();
        if(e.getClickCount()==2){


            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/showBookData.fxml"));
            try {
                Parent root = loader.load();

                showBookDataController sbd = loader.getController();
                sbd.sendData(blist,num);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if(e.getClickCount()==1){
            bno.setText(String.valueOf(blist.get(num).getBno()));
            bname.setText(blist.get(num).getBname());
            genre.setText(blist.get(num).getGenre());
            author.setText(blist.get(num).getAuthor());
            publisher.setText(blist.get(num).getPublisher());

        }
    }
}
