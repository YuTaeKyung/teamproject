package teamproject.taekung.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import teamproject.taekung.dao.BookDAO;
import teamproject.taekung.model.BookModel;

/**
 * Created by taeku on 2016-09-19.
 */
public class showBookDataController {
    @FXML Label bno;
    @FXML Label bname;
    @FXML Label genre;
    @FXML Label author;
    @FXML Label publisher;




    public void sendData(ObservableList<BookModel> blist, int num) {

        BookModel b = (BookModel)blist.get(num);


        b = BookDAO.viewBook(String.valueOf(b.getBno()));
        bno.setText(String.valueOf(b.getBno()));
        bname.setText(b.getBname());
        genre.setText(b.getGenre());
        author.setText(b.getAuthor());
        publisher.setText(b.getPublisher());


    }
}
