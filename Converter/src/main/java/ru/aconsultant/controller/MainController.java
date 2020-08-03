package ru.aconsultant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;

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

import ru.aconsultant.entity.Currency;
import ru.aconsultant.entity.Rate;
import ru.aconsultant.entity.RateData;
import ru.aconsultant.repository.CurrencyRepository;
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
	private HttpParamProcessor httpParamProcessor;
	
	@Autowired
	private Parser parser;
	
	
	@GetMapping("/")
    public String getHome(Model model/*, HttpServletRequest request, HttpServletResponse response*/) {
		
		// Parse new data if we need
		parser.parseURL("http://www.cbr.ru/scripts/XML_daily.asp");
		
		// Get current data
		List<RateData> rateDataList = rateDataRepository.findDataOnDate();
		rateDataList.add(new RateData("RUR (Российский рубль)", 1));
		model.addAttribute("rateDataList", rateDataList);
		
		
		/*List<String> list = new ArrayList<String>();
		list.add("option 1");
		list.add("option 2");
		model.addAttribute("options", list);*/
		
		return "home";
    }
	
	
	@PostMapping("/save")
	public void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    
		HashMap<String, Object> requestParameters = httpParamProcessor.getRequestParameters(request);
		String currency1 = (String) requestParameters.get("currency1");
		System.out.println("currency1: " + currency1);
	}
	
	
	@GetMapping("/login")
    public String getLogin() {
		
		return "login";
    }
	
	
}
