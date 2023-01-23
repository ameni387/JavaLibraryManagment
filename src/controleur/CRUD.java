/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controleur;

import java.util.ArrayList;

/**
 *
 * @author amani sghaier
 */
public interface CRUD <T> {
    boolean create (T u);
    boolean update (int id, T u);
    boolean delete (int id);
    ArrayList<T> retrieve (String keyword);
    ArrayList<T> retrieveAll ();

   
}
