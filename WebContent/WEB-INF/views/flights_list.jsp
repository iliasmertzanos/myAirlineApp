<%@ page import="java.util.*, com.airline.models.*" %>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="resources/css/jpaStyles.css" />
<title>Flights List</title>

</head>


<body>

	<h1>List of Flights</h1>
	
	<table>
	
		<tr>
		
			<th>Id</th>
			<th>From</th>
			<th>To</th>
			<th>Time</th>
			<th>Price</th>
			<th>Airplane</th>
			<th>Seating</th>
			<th>Number of pilots</th>
			<th>Pilot names</th>
		
		
		</tr>
		
		<%
			
			List<Flight> fList = (List<Flight>) request.getAttribute("flightList");
			
			for(Integer i = 0; i < fList.size(); i++) {
	
		
		 %>
		 
		 	<tr>
		 	
		 		<td><%= fList.get(i).getId()  %></td>
		 		<td><%= fList.get(i).getFlightOrigin()  %></td>
		 		<td><%= fList.get(i).getFlightDestination()  %></td>
		 		<td><%= fList.get(i).getFlightTime()  %></td>
		 		<td><%= fList.get(i).getPrice()  %></td>
		 		<td><%= fList.get(i).getAirplaneDetails().getPlaneMake() + " " + fList.get(i).getAirplaneDetails().getModelName() %></td>
		 		<td><%= fList.get(i).getAirplaneDetails().getSeatCapacity() %></td>
		 		
		 		<td>
		 		
		 			<%
		 				
		 				if(fList.get(i).getPilotsList() != null) {
		 				
		 			
		 			 %>
		 			 	<%= fList.get(i).getPilotsList().size() %> pilots
		 			 <%
						}
						else {
								 			 
		 			  %>
		 			  
		 			  	No pilots yet
		 			  
		 			  <%
		 			  	}
		 			   %>
		 		
		 		</td>
		 		
		 		<td>
		 		
		 			<%
		 				
		 				if(fList.get(i).getPilotsList() != null) {
		 			
		 					List<Pilot> pList = (List<Pilot>) fList.get(i).getPilotsList();	
		 					
		 					for(Integer j = 0; j < pList.size(); j++) {
		 					
		 			
		 			 %>
		 			 
		 			 	<%=
		 			 		(j+1) + ") " + pList.get(j).getFirstName() + " " +  pList.get(j).getLastName()
		 			 		+ " (" + pList.get(j).getPilotRank() + ")" + "<br />"  
		 			 	 %>
		 			 
		 			 <%
		 			 		} //for
		 			 
		 			 	} //if
		 			 
		 			  %>
		 		
		 		</td>
		 		
		 	
		 	</tr>
		 	
		 	<tr>
		 		<td colspan="9">
		 			<%
		 				if(fList.get(i).getPassengersList().size() > 0) {
		 					List<Passenger> passengerList = (List<Passenger>) fList.get(i).getPassengersList();
		 					
		 					for(Integer k = 0; k < passengerList.size(); k++) {
		 			 %>
		 			 		
		 			 		<%= k+1 %>) <%= (String) passengerList.get(k).getFirstName() %> <%= (String) passengerList.get(k).getLastName() %> <br />
		 			 
		 			 <%
		 			 		} //for	
		 			 	} else {
		 			 	 
		 			  %>
		 			  	No passengers on this flight yet.
		 			  <%
		 			  	} // else
		 			   %>
		 		</td>
			</tr>
		 
		 <%
		 	}
		  %>
	
	</table>

</body>
</html>