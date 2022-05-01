/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.authorship;

import domain.Authorship;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryAuthorship;

/**
 *
 * @author LORA
 */
public class AddAuthorshipSO extends AbstractSO{


    public AddAuthorshipSO() {
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Authorship)){
            throw  new Exception("Invalid authorship data!");
        } 
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Authorship)param);
    }

   
    
}
