package teamproject.taekung.dao;

import teamproject.taekung.model.MemberModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taeku on 2016-09-17.
 */
public class MemberDAO {
    private static final String DRV = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//192.168.112.128:1521/xe";
    private static final String USR = "taekung";
    private static final String PWD = "123456";


    private static String insertMember = "insert into member (mno,name,phone,cellphone,birthdate,addr,email) " +
                                        " values (mno.nextval, ?,?,?,?,?,?)";

    private static String findMember = "select * from member ORDER BY mno DESC ";


    private static String showMemberOne = "select * from member where mno = ?";

    private static String modifyMember = "UPDATE member set name = ?, phone = ?, cellphone = ?, " +
            " birthdate = ?, addr = ? , email = ? where mno = ?";

    private static String dropMember = "delete from member where mno = ?";


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




    public static void addMember(MemberModel m){
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(insertMember);
            pstmt.setString(1,m.getName());
            pstmt.setString(2,m.getPhone());
            pstmt.setString(3,m.getCellphone());
            pstmt.setString(4,m.getBirthdate());
            pstmt.setString(5,m.getAddr());
            pstmt.setString(6,m.getEmail());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }




    public static List<MemberModel> selectMemberAll() {

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<MemberModel> ml = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(findMember);

            rs = pstmt.executeQuery();

            while (rs.next()){
                MemberModel m = new MemberModel(rs.getInt("mno"),rs.getString("name"),rs.getString("phone"),
                        rs.getString("cellphone"),rs.getString("birthdate"),rs.getString("addr"),rs.getString("email"));
                ml.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return ml;
    }




    public static MemberModel viewMember(String mno){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberModel m = null;
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(showMemberOne);
            pstmt.setString(1,mno);

            rs = pstmt.executeQuery();

            while (rs.next()){
                 m = new MemberModel(rs.getInt("mno"),rs.getString("name"),rs.getString("phone"),rs.getString("cellphone"),
                        rs.getString("birthdate"),rs.getString("addr"),rs.getString("email"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return m;
    }







    public static List<MemberModel> selectMemberName(String s, String a) {

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<MemberModel> ml = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(s);
            pstmt.setString(1,a);
            rs = pstmt.executeQuery();

            while (rs.next()){
                MemberModel m = new MemberModel(rs.getInt("mno"),rs.getString("name"),rs.getString("phone"),
                        rs.getString("cellphone"),rs.getString("birthdate"),rs.getString("addr"),rs.getString("email"));
                ml.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return ml;
    }

    public static void updateMember(MemberModel m, String num) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt  =conn.prepareStatement(modifyMember);
            pstmt.setString(1,m.getName());
            pstmt.setString(2,m.getPhone());
            pstmt.setString(3,m.getCellphone());
            pstmt.setString(4,m.getBirthdate());
            pstmt.setString(5,m.getAddr());
            pstmt.setString(6,m.getEmail());
            pstmt.setString(7,num);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }


    }

    public static List<MemberModel> selectMemberCellphone(String s, String cp) {

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<MemberModel> ml = new ArrayList<>();
        conn = openConn();
        try {
            pstmt = conn.prepareStatement(s);
            pstmt.setString(1,cp);
            rs = pstmt.executeQuery();

            while (rs.next()){
                MemberModel m = new MemberModel(rs.getInt("mno"),rs.getString("name"),rs.getString("phone"),
                        rs.getString("cellphone"),rs.getString("birthdate"),rs.getString("addr"),rs.getString("email"));
                ml.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }


        return ml;

    }

    public static void deleteMember(String s){
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = openConn();
        try {
            pstmt = conn.prepareStatement(dropMember);
            pstmt.setString(1,s);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(conn,pstmt,null);
        }

    }
}
