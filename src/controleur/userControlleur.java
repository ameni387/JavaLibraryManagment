/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur;

import Model.user;
import com.mysql.jdbc.log.Log;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.util.logging.Level;
import java.sql.Statement;

/**
 *
 * @author amani sghaier
 */
public class userControlleur implements CRUD<user> {
     public  MyConnexion mc = MyConnexion.getInstance(); 
    public Statement st ;

    public userControlleur(){
        try {
      this.st = MyConnexion.getConnection().createStatement();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public boolean create(user u) {
 try {
            String sql = "INSERT INTO users (Name, Password, role)  VALUES ('"+ u.getName()+ "', '"+ u.getPassword()+ "','"+ u.getRole()+"' )";
            st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }         }

    @Override
    public boolean update(int id, user u) {
 try {
            String sql = "UPDATE users SET "+ "Name = '"+ u.getName()+ "',Password= '"+ u.getPassword()+"',role='"+u.getRole();
            st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }         }

    @Override
    public boolean delete(int id) {
try {
            String sql = "DELETE FROM users WHERE id ="+id;      
             st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }      }

    
    
    
    @Override
    public ArrayList<user> retrieveAll() {
        try{
            String sql="SELECT * FROM users  ";
            ArrayList<user> arraylist = new ArrayList<>();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                user u = new user();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setPassword(rs.getString(3));
                arraylist.add(u);
            }
            return arraylist;
                    
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
    }

    }
    
    
    
    
    
    
    public boolean connect(String login,String pwd,String role){
        try {
        
       // System.out.println(login);
       // System.out.println(pwd); 
          //java.sql.Statement st = MyConnexion.getInstance().getConnection().createStatement();
            //this.st = MyConnexion.getConnection().createStatement();
        // requete select
        String sql = "SELECT * FROM users  WHERE Name = '"+login+"' AND Password = '"+ pwd +"'AND role= '"+role+ "'";
       // System.out.println(sql);
        ResultSet data= st.executeQuery(sql);
        if ( data.next()){
             
            return true ;
         } else{
             return false;
         }            
        } catch ( SQLException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return false;

        }
    }
    public Boolean Register(String Login,String pwd,String role){
        try {
        this.st = MyConnexion.getConnection().createStatement();
        String sql = "INSERT INTO users (Name, Password, role)  VALUES ('"+Login+ "', '"+ pwd + "','"+role+ "' )";
        int updateRowCount= st.executeUpdate(sql);
        if ( updateRowCount>0){
             
            return true ;
         } else{
             return false;
         }
        }catch ( SQLException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return false;

        }
            
        }

            

    @Override
    public ArrayList<user> retrieve(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

