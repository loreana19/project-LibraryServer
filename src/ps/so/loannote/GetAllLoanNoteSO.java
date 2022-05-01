/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.loannote;

import domain.LoanNote;
import domain.LoanNoteItem;
import java.util.ArrayList;
import java.util.List;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryLN;

/**
 *
 * @author LORA
 */
public class GetAllLoanNoteSO extends AbstractSO{
    List<LoanNote> list;
    
    public GetAllLoanNoteSO() {
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof LoanNote))
            throw new Exception("Invalid loannota data!"); 
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list=(ArrayList<LoanNote>)repository.getAll((LoanNote)param);
          for (LoanNote ln : list) {
            LoanNoteItem items = new LoanNoteItem();
            items.setLoanNoteID(ln.getLoanNote());
            ArrayList<LoanNoteItem> LNitems = (ArrayList<LoanNoteItem>) repository.getAll(items);
            ln.setListOfLoanNoteItems(LNitems);
           
        }
    }
    

    public List<LoanNote> getList() {
        return list;
    }

   
    
}
