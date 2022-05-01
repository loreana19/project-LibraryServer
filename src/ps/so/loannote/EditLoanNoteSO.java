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
public class EditLoanNoteSO extends AbstractSO{

    
    @Override
    protected void precondition(Object param) throws Exception {
         if (param == null || !(param instanceof LoanNote) || ((LoanNote) param).getListOfLoanNoteItems() == null) {
            throw new Exception("Invalid loanNote data!");
        }    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((LoanNote)param);
        LoanNoteItem item=new LoanNoteItem();
        item.setLoanNoteID(((LoanNote)param).getLoanNote());
        
        List<LoanNoteItem> listDatabase=(ArrayList<LoanNoteItem>)repository.getAll(item);
        List<LoanNoteItem> forUpdate=new ArrayList<>();
        List<LoanNoteItem> forDelete=new ArrayList<>();
        List<LoanNoteItem> forInsert=new ArrayList<>();
        List<LoanNoteItem> lista=((LoanNote)param).getListOfLoanNoteItems();
        
          for (LoanNoteItem itemLN : ((LoanNote) param).getListOfLoanNoteItems()) {
            for (LoanNoteItem itemDB : listDatabase) {
                if (itemLN.getLoanNoteItemID().equals(itemDB.getLoanNoteItemID()) && (!itemLN.equals(itemDB))) {
                    forUpdate.add(itemLN);
                }
            }
            if (itemLN.getLoanNoteItemID() == 0l) {
                forInsert.add(itemLN);
            }
        }
     
        for (LoanNoteItem itemdb : listDatabase) {
            boolean has = false;
            for (LoanNoteItem itemLN : ((LoanNote)param).getListOfLoanNoteItems()) {
                if (itemdb.getLoanNoteItemID().equals(itemLN.getLoanNoteItemID())) {
                    has = true;
                    break;
                }

            }
            if (has == false) {
                forDelete.add(itemdb);
            }
        }
        /* for (LoanNoteItem itemLN : ((LoanNote)param).getListOfLoanNoteItems()) {
                if (itemLN.getLoanNoteItemID()!=0l) {
                    forDelete.add(itemLN);
                }

            }*/
        System.out.println("DELETE:" + forDelete.size());
        System.out.println("UPDATE:" + forUpdate.size());
        System.out.println("ADD: " + forInsert.size());
        if (forInsert.size() > 0) {
            for (LoanNoteItem itemLN : forInsert) {
               itemLN.setLoanNoteID(((LoanNote)param).getLoanNote());
                repository.add(itemLN);
            }
        }
        if (forDelete.size() > 0) {
            for (LoanNoteItem itemLN : forDelete) {
                repository.delete(itemLN);
            }
        }
        if (forUpdate.size() > 0) {
            for (LoanNoteItem itemLN : forUpdate) {
                repository.edit(itemLN);
            }
        }

        
    }

    
    
}
