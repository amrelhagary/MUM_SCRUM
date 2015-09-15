package edu.mum.mumscrum.datalayer.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "ROLE" database table.
 * 
 */
@Entity
@Table(name="\"ROLE\"")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_ID")
	private long roleId;

	private String name;

	public Role() {
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}