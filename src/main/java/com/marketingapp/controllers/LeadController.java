package com.marketingapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.dto.LeadData;
import com.marketingapp.entities.Lead;
import com.marketingapp.services.LeadService;
import com.marketingapp.util.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private EmailService emailService;

	//Handler Methods
	
	//http://localhost:8080/createlead
	
	@RequestMapping("/createlead")
	public String viewCreateLeadForm() {
		
		return "create_lead";
	}
	
	
	//http://localhost:8080/saveLead
	@RequestMapping("/saveLead")
	public String saveLead(@ModelAttribute("lead")Lead lead,ModelMap model) {
		
//		System.out.println(lead.getId());
//		System.out.println(lead.getFirstName());
//		System.out.println(lead.getLastName());
//		System.out.println(lead.getEmail());
//		System.out.println(lead.getMobile());                      //for Backend
	
		model.addAttribute("msg","Record is saved !!");
		emailService.sendEmail(lead.getEmail(),"Welcome Email","Test");
		leadService.saveOneLead(lead);
		return "create_lead";
	}
	
	//Second way to read from data
	//http://localhost:8080/saveLead
	
//	@RequestMapping("/saveLead")
//	public String saveLead(@RequestParam("id")long id,@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastName,@RequestParam("email")String email,@RequestParam("mobile")long mobile) {
//		Lead lead=new Lead();
//		lead.setId(id);
//		lead.setFirstName(firstName);
//		lead.setLastName(lastName);                               //for frontend
//		lead.setEmail(email);
//		lead.setMobile(mobile);
//		leadService.saveOneLead(lead);
//		return "create_lead";
//		
//		
//	}
	
//	@RequestMapping("/saveLead")
//	public String saveLead(LeadData leadData) {
//		System.out.println(leadData.getId());
//		System.out.println(leadData.getFirstName());                  //for Backend
//		System.out.println(leadData.getLastName());
//		System.out.println(leadData.getEmail());
//		System.out.println(leadData.getMobile());
//		return "create_lead";  
//	}
	
//	@RequestMapping("/saveLead")
//	public String saveLead(LeadData leadData) {
//		Lead lead=new Lead();
//		lead.setId(leadData.getId());
//	    lead.setFirstName(leadData.getFirstName());
//		lead.setLastName(leadData.getLastName());                               //for frontend
//		lead.setEmail(leadData.getEmail());
//		lead.setMobile(leadData
//				.getMobile());
//		leadService.saveOneLead(lead);
//		return "create_lead";
//	}
	
	//http:localhost:8080/listall
	
	@RequestMapping("/listall") 
		public String listLeads(Model model) {
			List<Lead> leads = leadService.getAllLeads();
			model.addAttribute("leads",leads);
			
		
			return "list_leads";
		}
	
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id") long id,Model model ) {
		leadService.deleteLead(id);
		
		List<Lead> leads=leadService.getAllLeads();
		model.addAttribute("leads",leads);
		return "list_leads";
		
	}
	
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id") long id,Model model) {
		Lead lead = leadService.getLeadById(id);
		model.addAttribute("lead",lead);
		return "update_lead";
		
		
	}
	
	
	
	//http://localhost:8080/saveLead
	@RequestMapping("/updateLead")
	public String updateLead(@ModelAttribute("lead")Lead lead,ModelMap model) {
         leadService.saveOneLead(lead);
		
         List<Lead> leads=leadService.getAllLeads();
 		model.addAttribute("leads",leads);
 		return "list_leads";
 		
	}
	
	}
	

