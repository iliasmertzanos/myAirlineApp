package com.airline.webservices.rest;

import javax.ws.rs.ApplicationPath;

//the class works as a gate to the external requests coming in
@ApplicationPath("/airlineservices/rest")
public class RestApplicationConfig extends javax.ws.rs.core.Application{

}
