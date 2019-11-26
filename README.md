# myAirlineApp
------------
## This service handles an app for managing aviation processes like inserting new passengers and airplanes as well as assigning pilots to flights

## Tech
It is based on JAVA EE using GlassFish web server.

## App description

The main entities that are being managed from this app are 
- `Airplane`
- `Flight`
- `Passenger`
- `Pilot`

and are in the package `com.airline.models`.

The service offers a lot of endpoints so that different entities can be manipulated and assigned to each other. (see package `com.airline.controllers` and `com.airline.webservices.rest`)

All of them are accessible over the user interfaces in the package `WebContent/WEB-INF/views`.

There is also a service layer in the package `com.airline.service` that handles the access to the database. 
Inserts, updates, deletes and reads objects from the database.




