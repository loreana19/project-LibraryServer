/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ps.view.ActiveUsersForm;

/**
 *
 * @author LORA
 */
public class ThreadRefresh extends Thread{
    ActiveUsersForm auf;

    public ThreadRefresh(ActiveUsersForm auf) {
        this.auf = auf;
    }

    @Override
    public void run() {
        while(true){
            try {
                auf.prepareView();
                System.out.println("Refreshed");
                sleep(5000);
            } catch (IOException ex) {
                Logger.getLogger(ThreadRefresh.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRefresh.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    
    
}
