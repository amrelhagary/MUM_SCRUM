package edu.mum.mumscrum.datalayer.dao;

import java.util.List;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

import edu.mum.mumscrum.common.ConfigurationConstants.SortingType;
import edu.mum.mumscrum.datalayer.model.Product;

public class ProductDAO {

	private static ProductDAO productDAO;
	private MUMScrumDAO mumScrumDAO;

	private ProductDAO() {
		mumScrumDAO = MUMScrumDAO.getInstance();
	}

	public static ProductDAO getInstance() {
		if (productDAO == null) {
			return new ProductDAO();
		}
		return productDAO;
	}

	public List<Product> getAllProducts() {
		return mumScrumDAO.getAllObjectsByExpression(Product.class,
				new ExpressionBuilder(), SortingType.ASCENDING, "id");
	}

	public Product getProductById(String id) {
		Expression expression = new ExpressionBuilder().get("id").equal(id);
		return mumScrumDAO.getObjectByExpression(Product.class, expression);
	}

	public Product addProduct(Product product) {
		return mumScrumDAO.addObject(product);
	}

	public Product updateProduct(Product product) {
		return mumScrumDAO.updateObject(product);
	}

	public Product deleteProduct(Product product) {
		return mumScrumDAO.deleteObject(product);
	}

	public List<Product> deleteProductById(String id) {
		Expression expression = new ExpressionBuilder().get("id").equal(id);
		return mumScrumDAO.deleteAllObjectsBasedOnExpression(Product.class,
				expression);
	}

}
