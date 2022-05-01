/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.so;

import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author LORA
 */
public abstract class AbstractSO {
    protected final Repository repository;

    public AbstractSO() {
        this.repository = new RepositoryDBGeneric();
    }
    
    public void execute(Object param)throws Exception{
     try{
        precondition(param);
        startTransaction();
        executeOperation(param);
        commitTransaction();
        System.out.println("Uspesno izvrsena operacija");
    }catch(Exception ex){
        System.out.println("Greška prilikom izvrsavanja operacije");
        rollbackTransaction();
        throw ex;
    }finally{
        disconnect();
     }
}

    protected abstract void precondition(Object param)throws Exception;

    protected abstract void executeOperation(Object param)throws Exception;

    
    private void startTransaction() throws Exception{
        ((DBRepository)repository).connect();
    }


    private void commitTransaction() throws Exception{
        ((DBRepository)repository).commit();
    }

    private void rollbackTransaction() throws Exception{
        ((DBRepository)repository).rollback();
    }

    private void disconnect()throws Exception{
        ((DBRepository)repository).disconnect();
    }

}
