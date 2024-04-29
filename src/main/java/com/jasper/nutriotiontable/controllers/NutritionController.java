package com.jasper.nutriotiontable.controllers;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasper.nutriotiontable.services.JasperService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class NutritionController {


    @Autowired
    JasperService jasperService;

    @GetMapping("report")
    public ResponseEntity<byte[]> getNutritionReport() throws JRException{
        ByteArrayOutputStream reportStream = jasperService.generateReport();

        // Setting up the headers, this indicates for the client's browser how to handle the response
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_PDF);

        // makes the file instantly downloadable
        // responseHeader.setContentDispositionFormData("filename", "certificate.pdf");
        
        return new ResponseEntity<byte[]>(reportStream.toByteArray(), responseHeader, HttpStatus.OK);

    }


}
