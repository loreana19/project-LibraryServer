/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.genre;

import domain.BookGenre;
import java.util.ArrayList;
import java.util.List;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryGenre;

/**
 *
 * @author LORA
 */
public class GetAllGenreSO extends AbstractSO{
    List<BookGenre> list;

    public GetAllGenreSO() {
    }
    
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof BookGenre)){
            throw  new Exception("Invalid bookgenres data!");
        }     }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=(ArrayList<BookGenre>)repository.getAll((BookGenre)param);
    }

    public List<BookGenre> getList() {
        return list;
    }

   
    
    
}
