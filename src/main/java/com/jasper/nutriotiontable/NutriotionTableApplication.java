package com.jasper.nutriotiontable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
public class NutriotionTableApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(NutriotionTableApplication.class, args);
	}

}
