package com.java.shop.services;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.java.shop.domain.Customer;

@Path("/customers")
public class CustomerResource {

	private Map<Integer, Customer> customerBD = new ConcurrentHashMap<>();
	private AtomicInteger idCounter = new AtomicInteger();
	
	@POST
	@Consumes("application/xml")
	public Response createCustomer(InputStream is) {
		Customer customer = readCustomer(is);
		customer.setId(idCounter.incrementAndGet());
		customerBD.put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("customers" + customer.getId())).build();
	}
	
	@GET
	@Path("id")
	@Produces("application/xml")
	public StreamingOutput getCustomers(@PathParam("id") int id) {
		
	}
	 
}
