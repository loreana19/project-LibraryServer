/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.book;

import domain.Book;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryBook;

/**
 *
 * @author LORA
 */
public class DeleteBookSO extends AbstractSO{

  
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Book)){
            throw  new Exception("Invalid book data!");
        } 
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Book)param);
    }

   
    
    
}
