package edu.mum.mumscrum.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.mum.mumscrum.databean.BurndownChartDataBean;
import edu.mum.mumscrum.databean.DateDataBean;
import edu.mum.mumscrum.datalayer.dao.ProgressRecordDAO;
import edu.mum.mumscrum.datalayer.model.ProgressRecord;

public class ProgressRecordService {

	private ProgressRecordDAO progressRecordDAO;

	public ProgressRecordService() {
		progressRecordDAO = ProgressRecordDAO.getInstance();
	}

	public List<BurndownChartDataBean> getBurndownChartDataBySprintId(String id) {
		List<ProgressRecord> progressRecords = progressRecordDAO
				.getAllProgressRecordsBySprintId(id);
		List<BurndownChartDataBean> burndownChartDataList = new ArrayList<BurndownChartDataBean>();
		DateDataBean dateDataBean = null;
		double actualTime = 0;
		double estimatedTime = 0;
		int prevDayVal = -1;
		int currDayVal = 0;
		int prevMonthVal = -1;
		int currMonthVal = 0;
		int prevYearVal = -1;
		int currYearVal = 0;
		for (ProgressRecord progressRecord : progressRecords) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(progressRecord.getStartTime()));
			currDayVal = cal.get(Calendar.DAY_OF_MONTH);
			currMonthVal = cal.get(Calendar.MONTH);
			currYearVal = cal.get(Calendar.YEAR);
			if (prevDayVal != currDayVal && prevMonthVal != currMonthVal
					&& prevYearVal != currYearVal) {
				if (dateDataBean != null) {
					BurndownChartDataBean burndownChartDataObject = new BurndownChartDataBean(
							dateDataBean, actualTime, estimatedTime);
					burndownChartDataList.add(burndownChartDataObject);
				}
				dateDataBean = new DateDataBean(currDayVal, currMonthVal,
						currYearVal);
				actualTime = 0;
				estimatedTime = 0;
			}
			estimatedTime += progressRecord.getUserstory().getEstTimeEff();
			actualTime += (progressRecord.getStopTime() - progressRecord
					.getStartTime()) / (60 * 60 * 1000);
		}
		return burndownChartDataList;
	}
}
