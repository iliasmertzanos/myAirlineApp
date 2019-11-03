package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PilotService
 */
@Stateless
@LocalBean
public class PilotService {
	
	@PersistenceContext(unitName="airline")
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public PilotService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPilot(Pilot pilot) {
    	entityManager.persist(pilot);
    }
    
    public void addNewPilotToFlight(Pilot pilot, String flightId) {
    	
    	entityManager.persist(pilot);
    	
    	TypedQuery <Flight> fQuery=entityManager.createNamedQuery("Flight.findById",Flight.class);
    	fQuery.setParameter("id", Integer.parseInt(flightId));
    	
    	Flight flight= fQuery.getSingleResult();
    	
    	List<Pilot> pilotsList=flight.getPilotsList();
    	
    	pilot.setFlightForPilot(flight);
    	
    	pilotsList.add(pilot);
    	
    	flight.setPilotsList(pilotsList);
    	
    	pilot.setFlightForPilot(flight);
   	
    }
}
