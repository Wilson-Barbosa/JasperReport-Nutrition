package com.jasper.nutriotiontable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jasper.nutriotiontable.models.Nutrition;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SpringBootApplication
public class NutriotionTableApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(NutriotionTableApplication.class, args);

		// file path to my report template
		String filePath = "src/main/resources/templates/nutriotionreport.jrxml";

		// Creating a few Nutrition Objects to serve as mock data in my database
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
		parameters.put("firstName", "Lucilene");
		parameters.put("lastName", "Barbosa");
		parameters.put("dbo", "21/06/1989");
		parameters.put("age", 34);
		parameters.put("nutriotionDataSet", nutrutionDataSource);

		// compiles the template to a JasperReport object
		JasperReport report = JasperCompileManager.compileReport(filePath);

		// fills the report with information
		JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

		// outputs the filled report to a pdf file
		JasperExportManager.exportReportToPdfFile(print, "src/main/resources/static/finalreport.pdf");

		System.out.println("Report Generated successfully!");

	}

}
