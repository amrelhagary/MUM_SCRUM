package edu.mum.mumscrum.datalayer.dao;

public class ReleaseDAO {
	
	private static ReleaseDAO releaseDAO;
	private MUMScrumDAO mumScrumDAO;

	private ReleaseDAO() {
		mumScrumDAO = MUMScrumDAO.getInstance();
	}

	public static ReleaseDAO getInstance() {
		if (releaseDAO == null) {
			return new ReleaseDAO();
		}
		return releaseDAO;
	}

}
