/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.member;

import domain.Member;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryMember;

/**
 *
 * @author LORA
 */
public class AddMemberSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception{
        if(param == null || !(param instanceof Member)){
            throw  new Exception("Preduslovi za kreiranje člana biblioteke nisu ispunjeni");
        }
      
    }

    @Override
    protected void executeOperation(Object param)throws Exception{
       repository.add((Member)param);
    }

   
    
    
    
    
}
