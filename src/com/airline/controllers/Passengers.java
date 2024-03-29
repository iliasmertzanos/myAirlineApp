package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.service.FlightService;
import com.airline.service.PassengerService;

/**
 * Servlet implementation class Flights
 */
@WebServlet("/Passengers")
public class Passengers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PassengerService passengerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Passengers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Passenger> passengerList = passengerService.getPassengerList();
		request.setAttribute("passengersList", passengerList);
		
//		PrintWriter writer=response.getWriter();
//		
//		writer.println(" List of flight are ready: "+flightList.toString());
		
		RequestDispatcher view =request.getRequestDispatcher("WEB-INF/views/passengers_list.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
