/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur;
import Model.books;
import Model.user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author amani sghaier
 */
public class studentControlleur implements CRUD<user> {
    public  MyConnexion mc = MyConnexion.getInstance(); 
    public Statement st ;

    public studentControlleur(){
        try {
      this.st = MyConnexion.getConnection().createStatement();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     @Override
    public boolean create(user u) {
 try {
            String sql = "INSERT INTO student_details (student_name, course, branche)  VALUES ('"+ u.getName()+ "', '"+ u.getCourse()+ "','"+ u.getBranche()+"' )";
            st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }         }

    @Override
    public boolean update(int id, user u) {
 try {
            String sql = "UPDATE student_details SET "
     + "student_name = '"+ u.getName()+ "',course= '"+ u.getCourse()+"',branche='"+u.getBranche();
            st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }         }

    @Override
    public boolean delete(int id) {
try {
            String sql = "DELETE FROM student_details WHERE id ="+id;      
             st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }      }
@Override
    public ArrayList<user> retrieveAll() {
        try{
            String sql="SELECT * FROM student_details  ";
            ArrayList<user> arraylist = new ArrayList<>();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                user u = new user();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setCourse(rs.getString(3));
                u.setBranche(rs.getString(4));
                arraylist.add(u);
            }
            return arraylist;
                    
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
    }

    }
      @Override
    public ArrayList<user> retrieve(String key) {
  try {
            String sql = "SELECT * FROM student_details WHERE student_id like '%"+key+"%' OR course like '%"+key+"%'";
            ArrayList<user>  arrayList = new ArrayList<>();
            ResultSet rs = st.executeQuery(sql);            
            while(rs.next()){
                user s = new user();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setCourse(rs.getString(3));
                s.setBranche(rs.getString(4));
                 
                arrayList.add(s);                
            }
            
            return arrayList;
        } catch (SQLException ex) {
            Logger.getLogger(studentControlleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    }
    
 public Boolean AddBook(String student_name,String course,String branche){
        try {
        this.st = MyConnexion.getConnection().createStatement();
        String sql = "INSERT INTO student_details (student_name, course, branch)  VALUES ('"+student_name+ "', '"+ course + "','"+branche+ "' )";
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
}
