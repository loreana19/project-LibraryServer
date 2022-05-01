/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.librarian;

import domain.Librarian;
import java.util.ArrayList;
import java.util.List;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryLibrarian;

/**
 *
 * @author LORA
 */
public class GetAllLibrarianSO extends AbstractSO{

    List<Librarian> list;
    
    public GetAllLibrarianSO() {
    }
     
    
    
    @Override
    protected void precondition(Object param) throws Exception {
         if(param == null || !(param instanceof Librarian)){
            throw  new Exception("Invalid librarian data!");
        }  
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=(ArrayList<Librarian>)repository.getAll((Librarian)param);
    }

    public Librarian login(String username, String password)throws Exception{
           for (Librarian librarian : list) {
            if (librarian.getUsername().equals(username)) {
                if (librarian.getPassword().equals(password)) {
                    return librarian;
                }
            }
        }
        throw new Exception("Librarian does not exist!");
    }

    public List<Librarian> getList() {
        return list;
    }

   
    
    
}
