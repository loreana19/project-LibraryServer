/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.author;

import domain.Author;
import java.util.ArrayList;
import java.util.List;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryAuthor;

/**
 *
 * @author LORA
 */
public class GetAllAuthorSO extends AbstractSO{
    List<Author> list;

    public GetAllAuthorSO() {
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Author)){
            throw  new Exception("Invalid author data!");
        } 
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=(ArrayList<Author>)repository.getAll((Author)param);
    }

    public List<Author> getList() {
        return list;
    }

  
    
}
