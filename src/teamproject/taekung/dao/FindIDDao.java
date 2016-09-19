package teamproject.taekung.dao;

import teamproject.taekung.VO.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-16.
 */
public class FindIDDao {
    private static final String DRV = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//192.168.112.128:1521/xe";
    private static final String USR = "taekung";
    private static final String PWD = "123456";


    private static String findid = "select id from manager where EMAIL = ? and phone = ?";
    private static String findpwd = "select pwd from manager where id = ? and email = ? and phone = ?";



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





    public static List<UserVO> selectID(String e, String p){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserVO> list = new ArrayList<>();
        conn = openConn();

        try {
            pstmt = conn.prepareStatement(findid);
            pstmt.setString(1,e);
            pstmt.setString(2,p);

            rs = pstmt.executeQuery();

            while (rs.next()){
              UserVO  user = new UserVO(rs.getString("id"),"","","","","");

                list.add(user);

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }
        return list;
    }

    public static List<UserVO> selectPWD(String i, String em, String pw){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserVO> list = new ArrayList<>();
        conn = openConn();
        try {
            pstmt=conn.prepareStatement(findpwd);
            pstmt.setString(1,i);
            pstmt.setString(2,em);
            pstmt.setString(3,pw);
            rs=pstmt.executeQuery();

            while (rs.next()){
                UserVO user = new UserVO("",rs.getString("pwd"),"","","","");
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
