package edu.mum.mumscrum.datalayer.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.sessions.UnitOfWork;

import edu.mum.mumscrum.common.ConfigurationConstants.SortingType;

public class MUMScrumDAO {

	private static MUMScrumDAO mumScrumDAO;
	private static final String persistenceUnitName = "DataLayer";
	private static EntityManagerFactory factory;

	private MUMScrumDAO() {
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	public static MUMScrumDAO getInstance() {
		if (mumScrumDAO == null) {
			return new MUMScrumDAO();
		}
		return mumScrumDAO;
	}

	private void terminateConnection(JpaEntityManager em, UnitOfWork uow) {
		uow.commit();
		uow.release();
		em.close();
	}

	public <T> List<T> getAllObjects(Class<T> clazz) {
		List<T> clones;
		JpaEntityManager em = (JpaEntityManager) factory.createEntityManager();
		UnitOfWork uow = em.getUnitOfWork();
		clones = (List<T>) uow.readAllObjects(clazz);
		terminateConnection(em, uow);
		return clones;
	}

	public <T> List<T> getAllObjectsByExpression(Class<T> clazz,
			Expression expression, SortingType sortingType, String columnName) {
		List<T> clones;
		JpaEntityManager em = (JpaEntityManager) factory.createEntityManager();
		UnitOfWork uow = em.getUnitOfWork();
		ReadAllQuery query = new ReadAllQuery();
		query.setReferenceClass(clazz);
		if (sortingType.equals(SortingType.ASCENDING)) {
			query.addAscendingOrdering(columnName);
		} else {
			query.addDescendingOrdering(columnName);
		}
		query.setSelectionCriteria(expression);
		clones = (List<T>) uow.executeQuery(query);
		terminateConnection(em, uow);
		return clones;
	}

	public <T> T getObjectByExpression(Class<T> clazz, Expression expression) {
		T clone;
		JpaEntityManager em = (JpaEntityManager) factory.createEntityManager();
		UnitOfWork uow = em.getUnitOfWork();
		clone = (T) uow.readObject(clazz, expression);
		terminateConnection(em, uow);
		return clone;
	}

	public <T> T addObject(T t) {
		T clone;
		JpaEntityManager em = (JpaEntityManager) factory.createEntityManager();
		UnitOfWork uow = em.getUnitOfWork();
		clone = (T) uow.registerObject(t);
		terminateConnection(em, uow);
		return clone;
	}

	public <T> T updateObject(T t) {
		T clone;
		JpaEntityManager em = (JpaEntityManager) factory.createEntityManager();
		UnitOfWork uow = em.getUnitOfWork();
		clone = (T) uow.deepMergeClone(t);
		terminateConnection(em, uow);
		return clone;
	}

	public <T> T deleteObject(T t) {
		T clone;
		JpaEntityManager em = (JpaEntityManager) factory.createEntityManager();
		UnitOfWork uow = em.getUnitOfWork();
		clone = (T) uow.deleteObject(t);
		terminateConnection(em, uow);
		return clone;
	}

	public <T> void deleteAllObjects(List<T> t) {
		JpaEntityManager em = (JpaEntityManager) factory.createEntityManager();
		UnitOfWork uow = em.getUnitOfWork();
		uow.deleteAllObjects(t);
		terminateConnection(em, uow);
	}

	public <T> List<T> deleteAllObjectsBasedOnExpression(Class<T> clazz,
			Expression expression) {
		List<T> clones;
		JpaEntityManager em = (JpaEntityManager) factory.createEntityManager();
		UnitOfWork uow = em.getUnitOfWork();
		clones = uow.readAllObjects(clazz, expression);
		uow.deleteAllObjects(clones);
		terminateConnection(em, uow);
		return clones;
	}
}
