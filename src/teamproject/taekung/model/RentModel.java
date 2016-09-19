package teamproject.taekung.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by taeku on 2016-09-14.
 */
public class RentModel {
    private SimpleIntegerProperty rno;
    private SimpleIntegerProperty mno;
    private SimpleIntegerProperty bno;
    private SimpleStringProperty regdate;
    private SimpleStringProperty duedate;


    public RentModel(int rno, int mno, int bno, String regdate, String duedate) {
        this.rno = new SimpleIntegerProperty(rno);
        this.mno = new SimpleIntegerProperty(mno);
        this.bno = new SimpleIntegerProperty(bno);
        this.regdate = new SimpleStringProperty(regdate);
        this.duedate = new SimpleStringProperty(duedate);
    }


    public int getRno() {
        return rno.get();
    }

    public void setRno(int rno) {
        this.rno.set(rno);
    }

    public int getMno() {
        return mno.get();
    }


    public void setMno(int mno) {
        this.mno.set(mno);
    }

    public int getBno() {
        return bno.get();
    }


    public void setBno(int bno) {
        this.bno.set(bno);
    }

    public String getRegdate() {
        return regdate.get();
    }

    public void setRegdate(String regdate) {
        this.regdate.set(regdate);
    }

    public String getDuedate() {
        return duedate.get();
    }


    public void setDuedate(String duedate) {
        this.duedate.set(duedate);
    }
}
