package teamproject.taekung.dao;

import teamproject.taekung.VO.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-14.
 */
public class JoinDao {

    private static final String DRV = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//192.168.112.128:1521/xe";
    private static final String USR = "taekung";
    private static final String PWD = "123456";




    private static String insertManager = "insert into manager (id, pwd, email, phone, address, storename)" +
                                        " VALUES (?,?,?,?,?,?)";
    private static String selectMID = "select id from manager where id =? ";







    public static Connection openConn(){
        Connection conn = null;

        try {
            Class.forName(DRV);
            conn= DriverManager.getConnection(URL,USR,PWD);
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


    public static void joinManager(UserVO user){
        Connection conn = null;
        PreparedStatement pstmt =null;


        try {
            conn=openConn();
            pstmt = conn.prepareStatement(insertManager);

            pstmt.setString(1,user.getId());
            pstmt.setString(2,user.getPwd());
            pstmt.setString(3,user.getEmail());
            pstmt.setString(4,user.getPhone());
            pstmt.setString(5,user.getAddress());
            pstmt.setString(6,user.getStorename());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }


    public static List<UserVO> selectID(String mid){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserVO> result = new ArrayList<>();

        conn= openConn();
        try {
            pstmt=conn.prepareStatement(selectMID);
            pstmt.setString(1,mid);
            rs=pstmt.executeQuery();

            while (rs.next()){
                UserVO user = new UserVO(
                        rs.getString("id"),"","","","",""
                );
                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeConn(conn,pstmt,rs);
        }
        return result;
    }



}
