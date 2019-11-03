package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@NamedQuery(name= "Flight.findById", query=" SELECT f FROM Flight f where f.id=:id ")
@Entity
public class Flight implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Enumerated(EnumType.STRING) 
	private FlightDestinations flightOrigin;
	
	@Enumerated(EnumType.STRING) 
	private FlightDestinations flightDestination;
	
	private Integer price;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date flightTime;
	
	//define that property Airplane is going to be saved as a data set in the table
	@OneToOne (cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	//define the name of the column that will point to the joint Column in the Table Airplane
	@JoinColumn(name="airplane_fk")
	private Airplane airplaneDetails;
	
	//define the variable in the pilot entity that connects pilot to flight
	@OneToMany(mappedBy="flightForPilot")
	private List<Pilot> pilotsList;
	
	@ManyToMany
	@JoinTable(name = "f_p_join", joinColumns = @JoinColumn(name = "flight_fk"), inverseJoinColumns = @JoinColumn(name="passenger_fk"))
	private List<Passenger> passengersList;
	
	public Flight() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FlightDestinations getFlightOrigin() {
		return flightOrigin;
	}

	public void setFlightOrigin(FlightDestinations flightOrigin) {
		this.flightOrigin = flightOrigin;
	}

	public FlightDestinations getFlightDestination() {
		return flightDestination;
	}

	public void setFlightDestination(FlightDestinations flightDestination) {
		this.flightDestination = flightDestination;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}

	public Airplane getAirplaneDetails() {
		return airplaneDetails;
	}

	public void setAirplaneDetails(Airplane airplaneDetails) {
		this.airplaneDetails = airplaneDetails;
	}

	public List<Pilot> getPilotsList() {
		return pilotsList;
	}

	public void setPilotsList(List<Pilot> pilotsList) {
		this.pilotsList = pilotsList;
	}

	public List<Passenger> getPassengersList() {
		return passengersList;
	}

	public void setPassengersList(List<Passenger> passengersList) {
		this.passengersList = passengersList;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightOrigin=" + flightOrigin + ", flightDestination=" + flightDestination
				+ ", price=" + price + ", flightTime=" + flightTime + ", airplaneDetails=" + airplaneDetails + "]";
	}
   
	
}
