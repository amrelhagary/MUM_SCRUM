package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.datalayer.dao.SprintDAO;
import edu.mum.mumscrum.datalayer.model.Release;
import edu.mum.mumscrum.datalayer.model.Sprint;
import oracle.net.aso.s;

public class SprintService {

	SprintDAO sprintdao;

	public SprintService() {
	 sprintdao = SprintDAO.getInstance();
	}
	public List<Sprint> getAllSprints()
	{
		return sprintdao.getAllSprintes();
	}
	
	public Sprint getSprintById(String id){
		return sprintdao.getSprintById(id);
	}
	
	public Sprint addSprint(Sprint sprint) {
		return sprintdao.addSprint(sprint);
	}
	
	public Sprint updateSprint(Sprint sprint) {
		return sprintdao.updateSprint(sprint);
	}
	public Sprint deleteSprint(Sprint sprint) {
		return sprintdao.deleteSprint(sprint);
	}
	public List<Sprint> deleteSprintById(String id) {
		return sprintdao.deleteSprintById(id);
	}
	public int setSprintIdNull(String id) {
		return sprintdao.setReleasIdNull(id , " userStory" , " progress_record ");
	}
	
}
