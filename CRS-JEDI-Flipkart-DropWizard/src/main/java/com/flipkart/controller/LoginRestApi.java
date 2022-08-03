package com.flipkart.controller;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.flipkart.bean.User;
import com.flipkart.service.AuthorizationService;

@Path("/LoginApi")
public class LoginRestApi {
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorize(@QueryParam("userId") @NotNull String userId, @QueryParam("password") @NotNull String password) {
		AuthorizationService auth = new AuthorizationService();
		User loginUser = auth.authorize(userId, password);
		if(loginUser.getRole() == -1)
		{
			return Response.status(Status.FORBIDDEN).entity("Invalid UserName or Password").build();
		}
		return Response.status(Status.OK).entity("Welcome "+loginUser.getName() + "\t"+ LocalDateTime.now()).build();
	}
}
