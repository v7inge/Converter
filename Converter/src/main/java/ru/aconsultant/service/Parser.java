package ru.aconsultant.service;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ru.aconsultant.entity.Currency;
import ru.aconsultant.entity.Rate;
import ru.aconsultant.repository.CurrencyRepository;
import ru.aconsultant.repository.RateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

@Component
public class Parser {

	
	@Autowired
    private CurrencyRepository currencyRepository;
	
	@Autowired
    private RateRepository rateRepository;
	
	
	public void parseURL(String url) {
		
		// Check if we need to load anything
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		
		List<Rate> tempRateList = rateRepository.findAllByDate(calendar);
	    if (tempRateList.size() == 0) {
	    
			// Connect and get XML
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			Document doc = null;
			try {
				doc = db.parse(new URL(url).openStream());
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
			
			// Prepare lists
			List<Currency> currencyList = new ArrayList<Currency>();
			List<Rate> rateList = new ArrayList<Rate>();
			
			// Parse date - not used
			/*Element root = doc.getDocumentElement();
			String dateText = root.getAttribute("Date");
			
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			try {
				calendar.setTime(sdf.parse(dateText));
			} catch (ParseException e) {
				System.err.println("Error parsing date: " + dateText);
				e.printStackTrace();
				calendar.setTimeInMillis(System.currentTimeMillis());
			}*/
			
			// Parse currency
			NodeList nList = doc.getElementsByTagName("Valute");
			
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		    	
		        Node nNode = nList.item(temp);
		        
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		        	
		            Element eElement = (Element) nNode;
		            
		            Currency c = new Currency(eElement.getAttribute("ID"), 
		            		getNestedAttribute(eElement, "NumCode"),
		            		getNestedAttribute(eElement, "CharCode"),
		            		getNestedAttribute(eElement, "Name"),
		            		toFloat(getNestedAttribute(eElement, "Value")));
		            
		            Rate r = new Rate(c, toFloat(getNestedAttribute(eElement, "Value")), calendar);
		            
		            currencyList.add(c);
		            rateList.add(r);
		        }
		    }
		    
		    currencyRepository.saveAll(currencyList);
		    rateRepository.saveAll(rateList);
	    }
	    
	    /*List<Rate> tempRateList = rateRepository.findAllByDate(calendar);
	    if (tempRateList.size() < rateList.size()) {
	    	rateRepository.saveAll(rateList);
	    }*/
	}
	
	
	private String getNestedAttribute(Element eElement, String name) {
		
		return eElement.getElementsByTagName(name).item(0).getTextContent();
	}
	
	
	private float toFloat(String st) {
		
		return Float.parseFloat(st.replace(",", "."));
	}
	
	
}
