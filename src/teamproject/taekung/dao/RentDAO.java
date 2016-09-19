package teamproject.taekung.dao;

import teamproject.taekung.model.RentModel;

import java.sql.*;

/**
 * Created by taeku on 2016-09-19.
 */
public class RentDAO {
    private static final String DRV = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//192.168.112.128:1521/xe";
    private static final String USR = "taekung";
    private static final String PWD = "123456";



    private static String insertRent = "insert into rent (rno,mno,bno) VALUES (rno.nextval,?,?)";



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


    public static void addRent(RentModel r){
        Connection conn =null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(insertRent);
            pstmt.setInt(1, r.getMno());
            pstmt.setInt(2,r.getBno());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }
    }
}
