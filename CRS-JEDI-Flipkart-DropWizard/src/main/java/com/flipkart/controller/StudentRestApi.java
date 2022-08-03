package com.flipkart.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.flipkart.Exceptions.CourseNotFoundException;
import com.flipkart.Exceptions.GradeCardNotPublishedException;
import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentCourseChoice;
import com.flipkart.bean.StudentResponse;
import com.flipkart.dao.CatalogDaoImpl;
import com.flipkart.dao.CatalogDaoInterface;
import com.flipkart.dao.CourseDaoImpl;
import com.flipkart.dao.CourseDaoInterface;
import com.flipkart.dao.GradeCardDaoImpl;
import com.flipkart.dao.GradeCardDaoInterface;
import com.flipkart.dao.NotificationDaoImpl;
import com.flipkart.dao.NotificationInterface;
import com.flipkart.dao.PaymentDaoImpl;
import com.flipkart.dao.PaymentDaoInterface;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.dao.StudentDaoInterafce;
import com.flipkart.dao.UserDaoImpl;
import com.flipkart.dao.UserDaoInterface;
import com.flipkart.service.StudentImpl;
import com.flipkart.service.UtilityService;

import javafx.util.Pair;

@Path("/StudentApi")
public class StudentRestApi {

	@GET
	@Path("/viewStudentDetails/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewStudentDetails(@PathParam("studentId") String studentId) {
		Student data;
		StudentResponse response = new StudentResponse();
		try {
			StudentDaoInterafce studentDaoImplementation = new StudentDaoImpl();
			data = studentDaoImplementation.viewStudentDetails(studentId);
			response.setName(data.getName());
			response.setContactNo(data.getContactNo());
			response.setGender(data.getGender());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/displayGradeCard/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayGradeCard(@PathParam("studentId") String studentId) throws GradeCardNotPublishedException {
		GradeCardDaoInterface gradeCardDaoImplementation = new GradeCardDaoImpl();
		GradeCard gradeCard = gradeCardDaoImplementation.viewGradeCard(studentId, 2);

		try {
			if (gradeCard.isPublished())
				return Response.status(Status.OK).entity(gradeCard).build();
			else
				throw new GradeCardNotPublishedException();
		} catch (GradeCardNotPublishedException e) {
			e.printStackTrace();
			return Response.status(Status.FORBIDDEN).entity(e.getMessage()).build();
		}

	}

	@GET
	@Path("/getPaymentStatus/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPaymentStatus(@PathParam("studentId") String studentId) {
		PaymentDaoInterface paymentDaoImpl = new PaymentDaoImpl();
		boolean paymentStatus = paymentDaoImpl.getPaymentStatus(studentId);
		if (paymentStatus) {
			return Response.status(Status.OK).entity("Payment is done").build();
		}
		return Response.status(Status.OK).entity("Payment is pending").build();
	}

	@GET
	@Path("/catalog")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayCourseCatalog() {
		CatalogDaoInterface catalogDaoImplementation = new CatalogDaoImpl();
		ArrayList<Course> clist = catalogDaoImplementation.viewCatalog(2);
		return Response.status(Status.OK).entity(clist).build();

	}

	@PUT
	@Path("/makePayment/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePaymentSuccessful(@PathParam("studentId") String studentId) {
		PaymentDaoInterface paymentDaoImpl = new PaymentDaoImpl();
		UserDaoInterface user = new UserDaoImpl();
		if (!user.checkIDAvailable(studentId)) {
			System.out.println("Invalid Student ID. Payment failed");
			return Response.status(Status.FORBIDDEN).entity("Invalid Student Id. Payment. Payment failed").build();
		}
		paymentDaoImpl.makePayment(studentId);
		return Response.status(Status.OK).entity("Payment successful").build();

	}

	@GET
	@Path("/studentRegistered/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response isStudentRegistered(@PathParam("studentId") String studentId) {
		StudentDaoInterafce studentDaoImplementation = new StudentDaoImpl();
		int result = studentDaoImplementation.checkRegStatus(studentId);
		return Response.status(Status.OK).entity(result).build();

	}

	public ArrayList<Course> viewStudentCourseChoice(String studentID) {
		StudentDaoInterafce student = new StudentDaoImpl();
		ArrayList<Course> studChoice = student.viewSelectedCourses(studentID);
		return studChoice;
	}
	

	@POST
	@Path("/registerCourses/{c1}/{c2}/{c3}/{c4}/{c5}/{c6}/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectCourses(@PathParam("c1") String c1,
			@PathParam("c2") String c2,
			@PathParam("c3") String c3,
			@PathParam("c4") String c4,
			@PathParam("c5") String c5,
			@PathParam("c6") String c6,
			@NotNull
			@PathParam("studentId") String studentId) {

		ArrayList<Pair<String, Integer>> selectedCourses = new ArrayList<Pair<String, Integer>>();
		CatalogDaoInterface catalogDaoImplementation = new CatalogDaoImpl();
				
				ArrayList<Course> courseCatalog = catalogDaoImplementation.viewCatalog(2);
				Map<String,Course> courseList = new HashMap<>();
				for(Course c: courseCatalog)
					courseList.put(c.getCourseId(), c);
				
				if(!courseList.containsKey(c1) || !courseList.containsKey(c2) || !courseList.containsKey(c3) || !courseList.containsKey(c4) 
						|| !courseList.containsKey(c5) || !courseList.containsKey(c6)) {
					 return Response.status(Status.BAD_REQUEST).entity("Course not found or not available").build();
				}
				selectedCourses.add(new Pair<>(c1, 1));
				selectedCourses.add(new Pair<>(c2, 1));
				selectedCourses.add(new Pair<>(c3, 1));
				selectedCourses.add(new Pair<>(c4, 1));
				selectedCourses.add(new Pair<>(c5, 2));
				selectedCourses.add(new Pair<>(c6, 2));
			 

		StudentCourseChoice studentCourseChoice = new StudentCourseChoice();
		studentCourseChoice.setStudentId(studentId);
		studentCourseChoice.setCourses(selectedCourses);
		StudentDaoInterafce studentDaoImplementation = new StudentDaoImpl();
		studentDaoImplementation.storeStudentCourseChoice(studentCourseChoice);
		return Response.status(201).entity("Registration Successfull").build();
	}

}
