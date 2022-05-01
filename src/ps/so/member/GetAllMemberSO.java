/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.member;

import domain.Member;
import java.util.ArrayList;
import java.util.List;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryMember;

/**
 *
 * @author LORA
 */
public class GetAllMemberSO extends AbstractSO{
    List<Member> list;
    
    public GetAllMemberSO() {
    }

    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Member)){
            throw  new Exception("Invalid member data!");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=(ArrayList<Member>)repository.getAll((Member)param);
    }

    public List<Member> getList() {
        return list;
    }

  
    

    
    
}
