package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.datalayer.dao.SprintDAO;
import edu.mum.mumscrum.datalayer.model.Release;
import edu.mum.mumscrum.datalayer.model.Sprint;

public class SprintService {

	SprintDAO sprintdao;

	public SprintService() {
	 sprintdao = SprintDAO.getInstance();
	}
	public List<Sprint> getAllSprints()
	{
		return sprintdao.getAllSprintes();
	}
	
	public Sprint getSprintById(String id)
	{
		return sprintdao.getSprintById(id);
		
	}
}
