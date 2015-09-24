package edu.mum.mumscrum.datalayer.dao;

import java.util.List;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

import edu.mum.mumscrum.common.ConfigurationConstants.ProgressRecordStateFlag;
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

	public List<ProgressRecord> getAllFinalizedProgressRecordsBySprintId(
			String id) {
		Expression expression = new ExpressionBuilder().get("sprint").get("id")
				.equal(id);
		expression = expression.and(new ExpressionBuilder().get("flag").equal(
				ProgressRecordStateFlag.STOP));
		return mumScrumDAO.getAllObjectsByExpression(ProgressRecord.class,
				expression, SortingType.ASCENDING, "id");
	}

	public ProgressRecord getProgressRecordById(String id) {
		Expression expression = new ExpressionBuilder().get("id").equal(id);
		return mumScrumDAO.getObjectByExpression(ProgressRecord.class,
				expression);
	}

	public ProgressRecord updateStartTime(ProgressRecord progressrecord) {

		return mumScrumDAO.addObject(progressrecord);
	}

	public ProgressRecord ckeckFlagStatus(ProgressRecord pr) {

		Expression expression = new ExpressionBuilder().get("id").equal(
				pr.getId());
		ProgressRecord rec = mumScrumDAO.getObjectByExpression(
				ProgressRecord.class, expression);
		return rec;
	}

	public String setUsStatusId(long usstatusid, long usid) {
		String upsql = "UPDATE USERSTORY SET US_STATUS = " + usstatusid
				+ " where id = " + usid;
		return upsql;
	}

	public int startEndTimeEstm(ProgressRecord progressrecord, long flagid,
			long curtime) {

		long usid = progressrecord.getUserstory().getId();
		String usname = progressrecord.getUserstory().getUsName();
		long ussttus = progressrecord.getUserstory().getUsStatus();
		System.out.println(usid + " usid " + ussttus + " us status " + usname
				+ "  usname");
		String updatesql = " UPDATE PROGRESS_RECORD pr SET pr.STOP_TIME = "
				+ curtime
				+ ", pr.FLAG = 2 where  pr.USID = "
				+ usid
				+ " and pr.flag  =  1 and pr.stop_time = 0 AND PR.ID = (SELECT MAX(ID) FROM PROGRESS_RECORD pr where  pr.USID = "
				+ usid + ")";

		String insertsql = "INSERT INTO PROGRESS_RECORD( USID , START_TIME,  STOP_TIME , FLAG	, SPR_ID ) VALUES( "
				+ usid + " , " + curtime + " , " + 0 + " , " + // STOP TIME SET
																// TO 0
				1 + " , " + // FLAG SET TO START
				progressrecord.getSprint().getId() + ")";
		if (flagid == 1 && ussttus == 2) // usstatus = 2 closed
		{
			mumScrumDAO.executeNonSelectingSQLCall(setUsStatusId(1, usid));
			mumScrumDAO.executeNonSelectingSQLCall(insertsql);
			return 1;
		} else {
			mumScrumDAO.executeNonSelectingSQLCall(setUsStatusId(2, usid));
			mumScrumDAO.executeNonSelectingSQLCall(updatesql);
			return 2;
		}
	}
}
/*
 * public int startEndTimeEstm(ProgressRecord progressrecord, long flagid , long
 * curtime) {
 * 
 * long usid = progressrecord.getSprint().getId();
 * 
 * String updatesql = " UPDATE PROGRESS_RECORD pr SET pr.STOP_TIME = " + curtime
 * + ", pr.FLAG = 2 where  pr.USID = " + usid +
 * " and pr.flag  =  1 and pr.stop_time = 0 AND PR.ID = (SELECT MAX(ID) FROM PROGRESS_RECORD pr where  pr.USID = "
 * + usid + ")";
 * 
 * String insertsql =
 * "INSERT INTO PROGRESS_RECORD( USID , START_TIME,  STOP_TIME , FLAG	, SPR_ID ) VALUES( "
 * + usid +" , " + curtime + " , " + 0 + " , " + // STOP TIME SET TO 0 1 + " , "
 * + // FLAG SET TO START progressrecord.getSprint().getId() + ")"; if(flagid ==
 * 1 ) { mumScrumDAO.executeNonSelectingSQLCall(insertsql); return 1; }else {
 * mumScrumDAO.executeNonSelectingSQLCall(updatesql); return 2; } }
 */

