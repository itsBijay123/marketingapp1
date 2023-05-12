package com.marketingapp.services;

import java.util.List;

import com.marketingapp.entities.Lead;

public interface LeadService {
	public void saveOneLead(Lead lead);

	public List<Lead> getAllLeads();

	public void deleteLead(long id);

	public Lead getLeadById(long id);

}
