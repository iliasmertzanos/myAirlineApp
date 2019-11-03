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

import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PassengerService
 */
@Stateless
@LocalBean
public class PassengerService {
	
	@PersistenceContext(unitName="airline")
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public PassengerService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPassenger(Passenger singlePassenger) {
    	entityManager.persist(singlePassenger);
    }
    
    public List<Passenger> getPassengerList(){
    	
    	TypedQuery<Passenger> pQuery=entityManager.createQuery("SELECT p FROM Passenger p", Passenger.class);
    	List<Passenger> passengerList=pQuery.getResultList();
    	
    	return passengerList;
    }
    
    public void addFlightTicketToPassenger(String ticketId, String passengerId) {
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
    	queryFlight.select(rootFlight).where(builder.equal(rootFlight.get("id").as(Integer.class),ticketId));
    	Flight singleFlight=entityManager.createQuery(queryFlight).getSingleResult();
    	
    	//Associate flight with passenger 
    	
    	List<Flight> flightList=singlePassenger.getFlightsList();
    	flightList.add(singleFlight);    	
    	singlePassenger.setFlightsList(flightList);
    	
    	singleFlight.getPassengersList().add(singlePassenger);
    }
    
}
