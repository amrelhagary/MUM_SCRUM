package edu.mum.mumscrum.common;

public class ConfigurationConstants {
	public interface ResponseMessage {
		public final static String ORACLE_DRIVE_CLASS_NOT_FOUND_EXCEPTION = "Class not found exception - Oracle Drive";
		public final static String SQL_EXCEPTION = "SQL Exception";
		public final static String JSON_EXCEPTION = "JSON Exception ";
		public final static String OPERATION_SUCCESSFUL = "ok";
		public final static String OPERATION_FAILED = "failed";
	}

	public enum SortingType {

		DESCENDING(1, "Descending"), ASCENDING(2, "Ascending");

		private int id;
		private String name;

		private SortingType(int id, String name) {
			this.name = name;
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
