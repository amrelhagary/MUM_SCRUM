package edu.mum.mumscrum.service;

import java.util.List;

import edu.mum.mumscrum.datalayer.dao.UserStoryDAO;
import edu.mum.mumscrum.datalayer.model.Userstory;

public class UserStoryService {
	UserStoryDAO userStoryDAO;

	public UserStoryService() {
		userStoryDAO = UserStoryDAO.getInstance();
	}

	public List<Userstory> getAllUserStorys() {
		return userStoryDAO.getAllUserStorys();
	}

	public Userstory getUserStoryById(String id) {
		return userStoryDAO.getUserStoryById(id);
	}

	public Userstory addUserStory(Userstory userstory) {
		return userStoryDAO.addUserStory(userstory);
	}

	public Userstory updateUserStory(Userstory userstory) {
		return userStoryDAO.updateUserStory(userstory);
	}

	public Userstory deleteUserStory(Userstory userstory) {
		return userStoryDAO.deleteUserStory(userstory);
	}

	public List<Userstory> deleteUserStoryById(String id) {
		return userStoryDAO.deleteUserStoryById(id);
	}

}
