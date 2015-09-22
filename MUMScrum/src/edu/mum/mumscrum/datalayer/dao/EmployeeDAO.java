package edu.mum.mumscrum.datalayer.dao;

import java.util.List;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

import edu.mum.mumscrum.common.ConfigurationConstants.SortingType;
import edu.mum.mumscrum.datalayer.model.Employee;

public class EmployeeDAO {
	private static EmployeeDAO employeeDAO;
	private MUMScrumDAO mumScrumDAO;

	private EmployeeDAO() {
		mumScrumDAO = MUMScrumDAO.getInstance();
	}

	public static EmployeeDAO getInstance() {
		if (employeeDAO == null) {
			return new EmployeeDAO();
		}
		return employeeDAO;
	}

	public List<Employee> getAllEmployees() {
		return mumScrumDAO.getAllObjectsByExpression(Employee.class,
				new ExpressionBuilder(), SortingType.ASCENDING, "id");
	}

	public List<Employee> getAllScrumMasters(int id) {
		Expression expression = new ExpressionBuilder().get("role")
				.get("roleId").equal(id);
		return mumScrumDAO.getAllObjectsByExpression(Employee.class,
				expression, SortingType.ASCENDING, "id");
	}

	public Employee getEmployeeById(String id) {
		Expression expression = new ExpressionBuilder().get("id").equal(id);
		return mumScrumDAO.getObjectByExpression(Employee.class, expression);
	}

	public Employee getEmployeeByUsername(String username) {
		Expression expression = new ExpressionBuilder().get("username").equal(
				username);
		return mumScrumDAO.getObjectByExpression(Employee.class, expression);
	}

	public Employee addEmployee(Employee employee) {
		return mumScrumDAO.addObject(employee);
	}

	public Employee updateEmployee(Employee employee) {
		return mumScrumDAO.updateObject(employee);
	}

	public Employee deleteEmployee(Employee employee) {
		return mumScrumDAO.deleteObject(employee);
	}

	public List<Employee> deleteEmployeeById(String id) {
		Expression expression = new ExpressionBuilder().get("id").equal(id);
		return mumScrumDAO.deleteAllObjectsBasedOnExpression(Employee.class,
				expression);
	}
}