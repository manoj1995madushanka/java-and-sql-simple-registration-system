/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import java.sql.*;

/**
 *
 * @author USER
 */
public class DBOperations {

    String url = "jdbc:mysql://localhost:3306/employee";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean addEmployee(EmployeeDetails em) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO employeedetails VALUES(?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setInt(1, em.getRegID());
            pst.setString(2, em.getFirstName());
            pst.setString(3, em.getLastName());
            pst.setInt(4, em.getAge());
            pst.setString(5, em.getCountry());
            pst.setString(6, em.getEmail());
            pst.setString(7, em.getUserName());
            pst.setString(8, em.getPassword());

            pst.executeUpdate();//update the db with values

            return true;

        } catch (Exception ex) {

            System.out.print(ex);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        }
    }

    int checkUserName(String username) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT username FROM employeedetails";

            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (username.equals(rs.getString(1)))//1 because we import one colum from the database
                {
                    return 0;//username already exist
                }
            }
            return 1;

        } catch (Exception e) {
            System.out.println(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.print(e);
            }

        }
    }

    int checkLogin(String username, String password) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT username,password FROM employeedetails";

            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (username.equals(rs.getString(1)) && password.equals(rs.getString(2)))
                {
                    return 1;//login success
                }
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return 2;
        }
    }
}
