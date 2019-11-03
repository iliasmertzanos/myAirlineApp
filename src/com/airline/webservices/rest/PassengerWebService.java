package com.airline.webservices.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.service.PassengerService;

@Path("/passengers")
public class PassengerWebService {
	
	@PersistenceContext(unitName="airline")
	EntityManager entityManager;
	
	@EJB
	PassengerService passengerService;
	
	@Context
	UriInfo purInfo;
	
	public PassengerWebService() {}
	
	//access method through get call
		@GET
		//define the type of responce, here the list will be converted into xml type
		@Produces(MediaType.APPLICATION_XML )
		public List<Passenger> getFlights(){
			
			List<Passenger> passengersList=passengerService.getPassengerList();
			
			
			return null;
		}
	
}
