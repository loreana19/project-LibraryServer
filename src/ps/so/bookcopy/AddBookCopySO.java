/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.bookcopy;

import domain.BookCopy;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryBookCopy;

/**
 *
 * @author LORA
 */
public class AddBookCopySO extends AbstractSO{

    public AddBookCopySO() {
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
         if(param == null || !(param instanceof BookCopy)){
            throw  new Exception("Invalid bookcopy data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((BookCopy)param);
    }

   
    
    
}
