package com.airline.webservices.rest;

import java.util.List;

import javax.ejb.EJB;
//import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Flight;
import com.airline.service.FlightService;

import javax.ws.rs.core.MediaType;


@Path("/flights")
public class FlightWebService {
	
	@PersistenceContext(unitName="airline")
	EntityManager entityManager;
	
	@EJB
	FlightService flightService;
	
	@Context
	UriInfo furInfo;
	
	public FlightWebService() {}
	
	//access method through get call
	@GET
	//define the type of responce, here the list will be converted into json type
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights(){
		
		List<Flight> flightsList=flightService.getFlightList();
		
		
		return flightsList;
	}
	
}
