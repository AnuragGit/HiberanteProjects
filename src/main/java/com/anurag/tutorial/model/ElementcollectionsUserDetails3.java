package com.anurag.tutorial.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table (name="USERS_DETAILS_1")
public class ElementcollectionsUserDetails3 {

	@Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int    userId;
    
    @Column(name="USER_NAME") 
    private String userName;
    
    @ElementCollection(fetch=FetchType.LAZY)
    @JoinTable(name="USER_ADDRESS_1",
            joinColumns=@JoinColumn(name="USER_ID"))
    private Collection<Address> lisOfAddresses = new ArrayList<Address>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Collection<Address> getLisOfAddresses() {
		return lisOfAddresses;
	}

	public void setLisOfAddresses(Collection<Address> lisOfAddresses) {
		this.lisOfAddresses = lisOfAddresses;
	}

	@Override
	public String toString() {
		return "ElementcollectionsUserDetails2 [userId=" + userId
				+ ", userName=" + userName + ", lisOfAddresses="
				+ lisOfAddresses + "]";
	}
}
	
	