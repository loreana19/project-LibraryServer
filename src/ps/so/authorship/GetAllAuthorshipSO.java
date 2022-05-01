/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.authorship;

import domain.Authorship;
import java.util.ArrayList;
import java.util.List;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryAuthorship;

/**
 *
 * @author LORA
 */
public class GetAllAuthorshipSO extends AbstractSO{
    List<Authorship> list;
    
    public GetAllAuthorshipSO() {
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Authorship)){
            throw  new Exception("Invalid authorship data!");
        }     }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=(ArrayList<Authorship>)repository.getAll((Authorship)param);
    }

    public List<Authorship> getList() {
        return list;
    }

   
    
    
}
