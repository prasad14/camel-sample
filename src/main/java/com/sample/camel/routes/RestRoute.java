package com.sample.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.sample.camel.dtos.Inputdata;
import com.sample.camel.dtos.SampleProcessor;

@Component
public class RestRoute extends RouteBuilder{

	@Autowired
	SampleProcessor  processor;
	 
	 
	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		  .contextPath("/api") 
		  .port(8081)
		  .enableCORS(true)
		  .apiContextPath("/api-doc")
		  .apiProperty("api.title", "REST API")
		  .apiProperty("api.version", "v1")
		  .apiContextRouteId("doc-api")
		  .component("servlet")
		  .bindingMode(RestBindingMode.json);
		
		
		
		rest("/test/")
		 
		  
		  .post("/addBean")
		  .bindingMode(RestBindingMode.json)
		  .type(Inputdata.class)
		  .consumes("application/json")
		  .to("direct:postDataRoute")
		
		
		  .get("/helloWorld")
		  	.outType(String.class)
		  	.produces(MediaType.APPLICATION_JSON_VALUE)
		  	.to("direct:getResponse");
		 
		from("direct:postDataRoute")
			.routeId("direct:postDataRoute")
		  	.process( e-> {
		  		Inputdata data = e.getIn().getBody(Inputdata.class);
		  		System.out.println(data.getVal());
		  		
		  		//transfering the proccesed data to the next camel  chain
		  		e.getOut().setBody(data);
		  	})
		  	.log("to the next chained processor")
		  	.bean(processor, "testProcess");
		
		from("direct:getResponse")
			.routeId("direct:getResponse")
			.process(e -> {
				e.getOut().setBody("Hello");
			});
		
	}
	

}
