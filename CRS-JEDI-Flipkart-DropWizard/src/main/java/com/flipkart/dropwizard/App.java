package com.flipkart.dropwizard;


import com.flipkart.controller.StudentRestApi;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


import com.flipkart.controller.LoginRestApi;


public class App extends Application<Configuration> {
 
	@Override
    public void initialize(Bootstrap<Configuration> b) {
    }
 
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        
        e.jersey().register(new StudentRestApi());  
        e.jersey().register(new LoginRestApi());
    }
 
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}