package com.consulta.clientes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.consulta.clientes.model.Customer;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@GetMapping
	
	 public ResponseEntity<?> getCustomer(@RequestParam String documentType, @RequestParam String documentNumber) {
        if (!documentType.matches("^[CP]$")) {
            return new ResponseEntity<>("Invalid document type", HttpStatus.BAD_REQUEST);
        }

        if ("C".equals(documentType) && "23445322".equals(documentNumber)) {
            Customer customer = new Customer("Juan", "Carlos", "Perez", "Lopez", "555-1234", "Calle Falsa 123", "Bogotá");
            return new ResponseEntity<>(customer, HttpStatus.OK);
            
        } else if ("P".equals(documentType)&& "12345678".equals(documentNumber)){
        	Customer customer = new Customer("Nicolas", "Sebastian", "Ayala", "Cubides", "321-9876", "carrera 147 # 102 47", "Bogotá");
        	return new ResponseEntity<>(customer, HttpStatus.OK);        	
        	
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}	

