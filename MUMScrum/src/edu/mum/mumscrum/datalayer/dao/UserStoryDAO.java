package edu.mum.mumscrum.datalayer.dao;

import java.util.List;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

import edu.mum.mumscrum.common.ConfigurationConstants.SortingType;
import edu.mum.mumscrum.datalayer.model.Product;
import edu.mum.mumscrum.datalayer.model.Userstory;

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

	public List<Userstory> getAllUserStorys() {
		return mumScrumDAO.getAllObjectsByExpression(Userstory.class,
				new ExpressionBuilder(), SortingType.ASCENDING, "id");
	}

	public Userstory getUserStoryById(String id) {
		Expression expression = new ExpressionBuilder().get("id").equal(id);
		return mumScrumDAO.getObjectByExpression(Userstory.class, expression);
	}

	public Userstory addUserStory(Userstory userstory) {
		return mumScrumDAO.addObject(userstory);
	}

	public Userstory updateUserStory(Userstory userstory) {
		return mumScrumDAO.updateObject(userstory);
	}

	public Userstory deleteUserStory(Userstory userstory) {
		return mumScrumDAO.deleteObject(userstory);
	}

	public List<Userstory> deleteUserStoryById(String id) {
		Expression expression = new ExpressionBuilder().get("id").equal(id);
		return mumScrumDAO.deleteAllObjectsBasedOnExpression(Userstory.class,
				expression);
	}

	public List<Userstory> getUserStoriesByProductId(String productId) {
		Expression expression = new ExpressionBuilder().get("product")
				.get("id").equal(productId);
		return mumScrumDAO.getAllObjectsByExpression(Userstory.class,
				expression);
	}

}
