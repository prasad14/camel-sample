package com.sample.camel.dtos;

import org.apache.camel.Exchange;
import org.springframework.http.HttpStatus;

public class SampleProcessor {

	public void testProcess(Exchange exchange){
		Inputdata inputdata = exchange.getIn().getBody(Inputdata.class);
		OutPutData outData =  new OutPutData(inputdata.getId(),inputdata.getVal());
		
		
		//setting rest out put;
		exchange.getOut().setBody(outData);
		exchange.getOut().setHeader(HttpStatus.OK.name() , HttpStatus.OK);
		
	}
}
