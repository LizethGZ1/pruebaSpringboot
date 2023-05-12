package com.juanma.calculator;
package org.junit.jupiter.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.termometer.example.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class CalculatorApplication {
	public static void main(String[] args){
		//SpringApplication.run(CalculatorApplication.class, args);
	}
	public static void printHello() {
    System.out.println("Hello");
  }
}