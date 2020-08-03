package ru.aconsultant.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.aconsultant.entity.Currency;
import ru.aconsultant.entity.Event;
import ru.aconsultant.entity.Rate;
import ru.aconsultant.entity.RateData;
import ru.aconsultant.repository.CurrencyRepository;
import ru.aconsultant.repository.EventRepository;
import ru.aconsultant.repository.RateDataRepository;
import ru.aconsultant.repository.RateRepository;
import ru.aconsultant.service.Parser;
import ru.aconsultant.service.HttpParamProcessor;

@Controller
public class MainController {

	@Autowired
    private CurrencyRepository currencyRepository;
	
	@Autowired
    private RateRepository rateRepository;
	
	@Autowired
    private RateDataRepository rateDataRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	private HttpParamProcessor httpParamProcessor;
	
	@Autowired
	private Parser parser;
	
	
	@GetMapping("/")
    public String getHome(Model model, HttpServletRequest request) {
		
		// Parse new data if we need
		parser.parseURL("http://www.cbr.ru/scripts/XML_daily.asp");
		
		// Get current data
		List<RateData> rateDataList = rateDataRepository.findDataOnDate();
		rateDataList.add(new RateData("RUR (Российский рубль)", 1));
		model.addAttribute("rateDataList", rateDataList);
		
		// Get history
		List<Event> eventList = eventRepository.findAll();
		model.addAttribute("eventList", eventList);
		
		// Store rates
		HttpSession session = request.getSession(true);
	    session.setAttribute("data", rateDataList);
		
		return "home";
    }
	
	
	@PostMapping("/save")
	public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    
		HashMap<String, Object> requestParameters = httpParamProcessor.getRequestParameters(request);
		String name1 = (String) requestParameters.get("currency1");
		String name2 = (String) requestParameters.get("currency2");
		float sum = Float.parseFloat((String) requestParameters.get("sum"));
		float result = Float.parseFloat((String) requestParameters.get("result"));
		List<RateData> rateDataList = (List<RateData>) request.getSession(true).getAttribute("data");
		
		String id1 = "", id2 = "";
		for (RateData rd : rateDataList) {
			
			if (rd.getName().equals(name1)) {
				id1 = rd.getCurrencyId();
			}
			if (rd.getName().equals(name2)) {
				id2 = rd.getCurrencyId();
			}
		}
		
		Event event = new Event(id1, id2, sum, result);
		eventRepository.save(event);
	}
	
	
	@GetMapping("/login")
    public String getLogin() {
		
		return "login";
    }
	
	
	private void setSessionAttribute(HttpServletRequest request, String name, String value) {
		
		HttpSession session = request.getSession(true);
	    session.setAttribute(name, value);
	}
	
}
