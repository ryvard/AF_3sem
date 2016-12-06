/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Reservation;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emmablomsterberg
 */
public class ReservationFacade {
    
    EntityManagerFactory emf;

    public ReservationFacade()
    {
        emf = Persistence.createEntityManagerFactory("PU_AFReserve");
    }
    
     public ReservationFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public boolean addReservation(Reservation reserve) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(reserve);
            em.getTransaction().commit();
            return true;
            
        } finally {
            em.close();
        }
        
    }

}
