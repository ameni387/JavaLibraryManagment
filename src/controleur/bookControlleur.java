/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur;
import Model.books;

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
public class bookControlleur implements CRUD<books>{
     public  MyConnexion mc = MyConnexion.getInstance(); 
    public Statement st ;

    public bookControlleur(){
        try {
      this.st = MyConnexion.getConnection().createStatement();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public boolean create(books b) {
 try {
            String sql = "INSERT INTO book_details (book_name, author, Quantity)  VALUES ('"+ b.getBook_name()+ "', '"+ b.getAuthor()+ "','"+ b.getQuantity()+"' )";
            st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }         }

    @Override
    public boolean update(int id, books b) {
 try {
            //String sql = "UPDATE book_details SET "+ "book_name = '"+ b.getBook_name()+ "',author= '"+ b.getAuthor()+"',Quantity='"+b.getQuantity();
     String sql =  "UPDATE book_details SET book_name='" + b.getBook_name()+ "', author ='" + b.getAuthor()+ "', Quantity ='" + b.getQuantity()+ "' WHERE book_id ="+id ; 


            st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }         }

    @Override
    public boolean delete(int id) {
try {
            String sql = "DELETE FROM book_details WHERE book_id ="+id;      
             st.executeUpdate(sql);
            return true ;
        } catch (SQLException ex) {            
            System.err.println(ex.getMessage());
            return false ;
         }      }

    
    
    
    @Override
    public ArrayList<books> retrieveAll() {
        try{
            String sql="SELECT * FROM book_details  ";
            ArrayList<books> arraylist = new ArrayList<>();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                books b = new books();
                b.setId(rs.getInt(1));
                b.setBook_name(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setQuantity(rs.getInt(4));
                arraylist.add(b);
            }
            return arraylist;
                    
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
    }

    }
    public Boolean AddBook(String book_name,String author,int Quantity){
        try {
        this.st = MyConnexion.getConnection().createStatement();
        String sql = "INSERT INTO book_details (book_name, author, Quantity)  VALUES ('"+book_name+ "', '"+ author + "','"+Quantity+ "' )";
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
    public ArrayList<books> retrieve(String key) {
  try {
            String sql = "SELECT * FROM book_details WHERE book_id like '%"+key+"%' OR author like '%"+key+"%'";
            ArrayList<books>  arrayList = new ArrayList<>();
            ResultSet rs = st.executeQuery(sql);            
            while(rs.next()){
                books b = new books();
                b.setId(rs.getInt(1));
                b.setBook_name(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setQuantity(rs.getInt(4));
                 
                arrayList.add(b);                
            }
            
            return arrayList;
        } catch (SQLException ex) {
            Logger.getLogger(bookControlleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    }
    
    
}
