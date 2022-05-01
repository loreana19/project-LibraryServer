/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so.loannote;

import domain.LoanNote;
import domain.LoanNoteItem;
import ps.so.AbstractSO;
import repository.db.DBRepository;
import repository.db.impl.RepositoryLN;

/**
 *
 * @author LORA
 */
public class AddLoanNoteSO extends AbstractSO{


    public AddLoanNoteSO() {
    }
    
    
    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof LoanNote) || ((LoanNote)param).getListOfLoanNoteItems() == null)
            throw new Exception("Invalid loannota data!");    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         repository.add((LoanNote)param);

            for (LoanNoteItem item : ((LoanNote)param).getListOfLoanNoteItems()) {
                item.setLoanNoteID(((LoanNote)param).getLoanNote());
                repository.add(item);

            }
    }
    
}
