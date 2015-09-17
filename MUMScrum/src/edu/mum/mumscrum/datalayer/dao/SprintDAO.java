package edu.mum.mumscrum.datalayer.dao;

public class SprintDAO {
	
	private static SprintDAO sprintDAO;
	private MUMScrumDAO mumScrumDAO;

	private SprintDAO() {
		mumScrumDAO = MUMScrumDAO.getInstance();
	}

	public static SprintDAO getInstance() {
		if (sprintDAO == null) {
			return new SprintDAO();
		}
		return sprintDAO;
	}

}
