package org.hibernate.bugs;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Child {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false)
	private Root root;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> parameterValue;

	public Child() {
	}

	public Child(Root root, String... parameters) {
		this.root = root;
		this.parameterValue = new HashMap<>();
		for (String parameter : parameters) {
			this.parameterValue.put(parameter + "_key", parameter + "_value");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public Map<String, String> getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(Map<String, String> parameterValue) {
		this.parameterValue = parameterValue;
	}
}
