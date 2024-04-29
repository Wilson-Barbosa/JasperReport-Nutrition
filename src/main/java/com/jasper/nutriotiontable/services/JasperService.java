package com.jasper.nutriotiontable.services;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jasper.nutriotiontable.models.Nutrition;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;


@Service
public class JasperService {

    // Path to the .jrxml template used by Jasper to generate a report
    private String filePath = "src/main/resources/templates/nutriotionreport.jrxml";

    public ByteArrayOutputStream generateReport() throws JRException{

        // Creating a few Nutrition Objects to serve as mock data 
		Nutrition iron = new Nutrition("Iron", 20, 40, "mg");
		Nutrition protein = new Nutrition("protein", 120, 500, "g");
		Nutrition minerals = new Nutrition("minerals", 80, 110, "mg");
		Nutrition fat = new Nutrition("fat", 200, 300, "g");
		Nutrition sugar = new Nutrition("sugar", 80, 110, "g");
		Nutrition sodium = new Nutrition("sodium", 50, 80, "mg");
		Nutrition carbs = new Nutrition("carbs", 175, 200, "g");
		Nutrition calcium = new Nutrition("calcium", 70, 80, "g");
		Nutrition fiber = new Nutrition("fiber", 80, 110, "g");

		// Adding the items to the list
		List<Nutrition> nutritionList = new ArrayList<>();
		nutritionList.add(iron);
		nutritionList.add(protein);
		nutritionList.add(minerals);
		nutritionList.add(fat);
		nutritionList.add(sugar);
		nutritionList.add(sodium);
		nutritionList.add(carbs);
		nutritionList.add(calcium);

		nutritionList.add(fiber);

		// Now to populate the DataSourceSet from Jasper with my List of Nutrients
		JRBeanCollectionDataSource nutrutionDataSource = new JRBeanCollectionDataSource(nutritionList);


		// creating some mock data to fill it
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("firstName", "Wilson");
		parameters.put("lastName", "Barbosa");
		parameters.put("dbo", "21/06/1989");
		parameters.put("age", 34);
		parameters.put("nutriotionDataSet", nutrutionDataSource);

		// Compiles the template to a JasperReport object
		JasperReport compiledReport = JasperCompileManager.compileReport(filePath);

		// Fills the report with information
		JasperPrint print = JasperFillManager.fillReport(compiledReport, parameters, new JREmptyDataSource());

		// Now to generate a ByteOutputStream that will serve the pdf as a response
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        // Setting up configuration of my pdf document
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCompressed(true);
        

        // Exports the file as a .pdf
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setConfiguration(configuration);
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.exportReport();


        return byteArrayOutputStream;


    }

}
