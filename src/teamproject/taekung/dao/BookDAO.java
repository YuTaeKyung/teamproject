package teamproject.taekung.dao;

import teamproject.taekung.model.BookModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-19.
 */
public class BookDAO {
    private static final String DRV = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//192.168.112.128:1521/xe";
    private static final String USR = "taekung";
    private static final String PWD = "123456";



    private static String selectAll ="select * from book order by bno desc";

    private static String insertBook = "insert into book (bno,bname,genre,author,publisher) values (?,?,?,?,?)";

    private static String dropBook = "delete from book where bno = ? ";

    private static String updateBook = "update book set bno=?, bname=?, genre = ? , author = ?, publisher = ? where" +
            " bno = ?";

    private static String showBookOne = "select * from book where bno = ?";

    public static Connection openConn(){
        Connection conn = null;

        try {
            Class.forName(DRV);
            conn= DriverManager.getConnection(URL, USR, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }



    public static void closeConn(Connection c, PreparedStatement p, ResultSet r){
        if(r !=null) try{r.close(); r=null;} catch (Exception ex){}
        if(p !=null) try{p.close(); p=null;} catch (Exception ex){}
        if(c !=null) try{c.close(); c=null;} catch (Exception ex){}
    }


    public static List<BookModel> selectBookAll(){
        Connection conn =null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BookModel> blist = new ArrayList<>();

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(selectAll);
           rs = pstmt.executeQuery();


            while (rs.next()){
                BookModel b = new BookModel(rs.getInt("bno"),rs.getString("bname"),rs.getString("genre"),rs.getString("author"),
                                    rs.getString("publisher"));

                blist.add(b);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }
        return blist;

    }


    public static void addBook(BookModel b){
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(insertBook);
            pstmt.setInt(1, b.getBno());
            pstmt.setString(2,b.getBname());
            pstmt.setString(3,b.getGenre());
            pstmt.setString(4,b.getAuthor());
            pstmt.setString(5,b.getPublisher());

            pstmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }

    public static void deleteMember(String s) {

        Connection conn =null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(dropBook);

            pstmt.setString(1,s);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }

    public static void updateBook(BookModel bm, String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(updateBook);
            pstmt.setInt(1,bm.getBno());
            pstmt.setString(2,bm.getBname());
            pstmt.setString(3,bm.getGenre());
            pstmt.setString(4,bm.getAuthor());
            pstmt.setString(5,bm.getPublisher());
            pstmt.setString(6,s);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }
    }

    public static List<BookModel> selectBookName(String s, String a) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<BookModel> bl = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(s);
            pstmt.setString(1,a);
            rs = pstmt.executeQuery();

            while (rs.next()){
                BookModel b = new BookModel(rs.getInt("bno"),rs.getString("bname"),rs.getString("genre"),
                        rs.getString("author"),rs.getString("publisher"));
                bl.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return bl;
    }

    public static List<BookModel> selectBookBno(String s, String a) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<BookModel> bl = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(s);
            pstmt.setString(1,a);
            rs = pstmt.executeQuery();

            while (rs.next()){
                BookModel b = new BookModel(rs.getInt("bno"),rs.getString("bname"),rs.getString("genre"),
                        rs.getString("author"),rs.getString("publisher"));
                bl.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return bl;

    }

    public static BookModel viewBook(String s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BookModel b = null;
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(showBookOne);
            pstmt.setString(1,s);

            rs = pstmt.executeQuery();

            while (rs.next()){
                b = new BookModel(rs.getInt("bno"),rs.getString("bname"),rs.getString("genre"),
                        rs.getString("author"),rs.getString("publisher"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return b;
    }
}
