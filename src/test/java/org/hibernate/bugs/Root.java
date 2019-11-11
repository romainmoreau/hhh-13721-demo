package org.hibernate.bugs;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Root {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "root", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<Child> childCollection;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Child> getChildCollection() {
		return childCollection;
	}

	public void setChildCollection(Collection<Child> childCollection) {
		this.childCollection = childCollection;
	}
}
