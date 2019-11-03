package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class FlightService
 */
@Stateless
@LocalBean
public class FlightService {
    
    @PersistenceContext(unitName="airline")
    EntityManager entityManager;
    
    /**
     * Default constructor. 
     */
    public FlightService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addFlight(Flight flight, Airplane airplane) {
    	
    	entityManager.persist(flight);
    }
    
    public void addPilotToFlight(String pilotId, String flightId) {
    	
    	TypedQuery <Flight> fQuery=entityManager.createNamedQuery("Flight.findById",Flight.class);
    	fQuery.setParameter("id", Integer.parseInt(flightId));
    	
    	Flight flight= fQuery.getSingleResult();
    	
    	TypedQuery <Pilot> pQuery=entityManager.createNamedQuery("Pilot.findById",Pilot.class);
    	pQuery.setParameter("id", Integer.parseInt(pilotId));
    	
    	Pilot pilot= pQuery.getSingleResult();
    	
    	List<Pilot> pilotsList=flight.getPilotsList();
    	
    	pilot.setFlightForPilot(flight);
    	
    	pilotsList.add(pilot);
    	
    	flight.setPilotsList(pilotsList);
    	
    	
    	
    }
    
    //Difference between SELECT query and java coded query is that the java coded query is safer
    // If there is any problem in the code it want compile, whereas the SELCT query will compile but throw exceptions or give errors back
    
    public List<Flight> getFlightList(){
    	TypedQuery <Flight> fQuery=entityManager.createQuery("SELECT f FROM Flight f ",Flight.class);
    	List<Flight> flightList =fQuery.getResultList();
    	return flightList;
    }
    
    public void addPassengerToFlight(String passengerId, String flightId) {
    	//Find Passenger
    	CriteriaBuilder builder=entityManager.getCriteriaBuilder();
    	CriteriaQuery<Passenger> queryPassenger=builder.createQuery(Passenger.class);
    	
    	Root<Passenger> rootPassenger=queryPassenger.from(Passenger.class);
    	queryPassenger.select(rootPassenger).where(builder.equal(rootPassenger.get("id").as(Integer.class),passengerId));
    	Passenger singlePassenger=entityManager.createQuery(queryPassenger).getSingleResult();
    	
    	//find Flight
    	builder=entityManager.getCriteriaBuilder();
    	CriteriaQuery<Flight> queryFlight=builder.createQuery(Flight.class);
    	
    	Root<Flight> rootFlight=queryFlight.from(Flight.class);
    	queryFlight.select(rootFlight).where(builder.equal(rootFlight.get("id").as(Integer.class),flightId));
    	Flight singleFlight=entityManager.createQuery(queryFlight).getSingleResult();
    	
    	//Associate Passenger with flight
    	
    	List<Passenger> passengerList=singleFlight.getPassengersList();
    	passengerList.add(singlePassenger);    	
    	singleFlight.setPassengersList(passengerList);
    	
    	List<Flight> flightList=singlePassenger.getFlightsList();
    	flightList.add(singleFlight);
//    	singlePassenger.setFlightsList(flightList);
//    	
//List<Passenger> pList = f.getPassengers();
//		
//		pList.add(p);
//		
//		f.setPassengers(pList);
//		
//		p.getFlights().add(f);
//    	
    }

}
