package edu.mum.mumscrum.datalayer.dao;

import java.util.List;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

import edu.mum.mumscrum.common.ConfigurationConstants.SortingType;
import edu.mum.mumscrum.datalayer.model.ProgressRecord;

public class ProgressRecordDAO {

	private static ProgressRecordDAO progressRecordDAO;
	private MUMScrumDAO mumScrumDAO;

	private ProgressRecordDAO() {
		mumScrumDAO = MUMScrumDAO.getInstance();
	}

	public static ProgressRecordDAO getInstance() {
		if (progressRecordDAO == null) {
			return new ProgressRecordDAO();
		}
		return progressRecordDAO;
	}

	public List<ProgressRecord> getAllProgressRecordsBySprintId(String id) {
		Expression expression = new ExpressionBuilder().get("sprint").get("id")
				.equal(id);
		return mumScrumDAO.getAllObjectsByExpression(ProgressRecord.class,
				expression, SortingType.ASCENDING, "id");
	}

}
