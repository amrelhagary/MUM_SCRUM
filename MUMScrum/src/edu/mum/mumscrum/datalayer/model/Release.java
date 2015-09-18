package edu.mum.mumscrum.datalayer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the "RELEASE" database table.
 * 
 */
@Entity
@Table(name = "\"RELEASE\"")
@NamedQuery(name = "Release.findAll", query = "SELECT r FROM Release r")
public class Release implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RELEASE_SEQ", sequenceName = "RELEASE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELEASE_SEQ")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "RELS_DATE")
	private Date relsDate;

	@Column(name = "RELS_DESC")
	private String relsDesc;

	@Column(name = "RELS_NAME")
	private String relsName;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRD_ID")
	private Product product;

	public Release() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getRelsDate() {
		return this.relsDate;
	}

	public void setRelsDate(Date relsDate) {
		this.relsDate = relsDate;
	}

	public String getRelsDesc() {
		return this.relsDesc;
	}

	public void setRelsDesc(String relsDesc) {
		this.relsDesc = relsDesc;
	}

	public String getRelsName() {
		return this.relsName;
	}

	public void setRelsName(String relsName) {
		this.relsName = relsName;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}