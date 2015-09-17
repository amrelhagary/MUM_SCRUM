package edu.mum.mumscrum.datalayer.dao;

public class UserStoryDAO {

	private static UserStoryDAO userStoryDAO;
	private MUMScrumDAO mumScrumDAO;

	private UserStoryDAO() {
		mumScrumDAO = MUMScrumDAO.getInstance();
	}

	public static UserStoryDAO getInstance() {
		if (userStoryDAO == null) {
			return new UserStoryDAO();
		}
		return userStoryDAO;
	}

}
