package teamproject.taekung.dao;

import teamproject.taekung.VO.UserVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-16.
 */
public class MainDao {
    private static final String DRV = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//192.168.112.128:1521/xe";
    private static final String USR = "taekung";
    private static final String PWD = "123456";



    private static String login = "select id ,pwd from manager where id = ? and pwd = ?";






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

    public static List<UserVO> setLogin (String mid, String pwd){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO user = null;
        List<UserVO> result = new ArrayList<>();

        conn=openConn();
        try {
            pstmt=conn.prepareStatement(login);
            pstmt.setString(1,mid);
            pstmt.setString(2,pwd);
            rs=pstmt.executeQuery();

            while (rs.next()){
                user =new UserVO(rs.getString("id"),rs.getString("pwd"),"","","","");
                result.add(user);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,rs);
        }
        return result;
    }






}
