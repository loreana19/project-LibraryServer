/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.book;

import domain.Book;
import java.util.ArrayList;
import java.util.List;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryBook;

/**
 *
 * @author LORA
 */
public class GetAllBookSO extends AbstractSO{
    List<Book> list;

    public GetAllBookSO() {
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Book)){
            throw  new Exception("Invalid book data!");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=(ArrayList<Book>)repository.getAll((Book)param);  
    }

    public List<Book> getList() {
        return list;
    }

   
    
    
    
}
