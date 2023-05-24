//package com.example.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DataPublisherServiceImpl 
//{
//	public static final String topic ="movie-topic";
//	
//	@Autowired
//	private KafkaTemplate<String, String> temp;
//
//	public KafkaTemplate<String, String> getTemp() {
//		return temp;
//	}
//
//	public void setTemp(String message)
//	{
//		this.temp.send(topic, message);
//	}
//
//	public static String getTopic() {
//		return topic;
//	}
//	
//	
//
//}