package com.anurag.tutorial.model;

/**
 * @author Anurag
 * 
 *   Concept:-
 *   Here @Entity means it telling hibernate this class treat as entity and need to save it the database.
     and @ID means it telling hibernate this property treat as primary key of the table.
     these are two minimum required annotation we have use for saving the object in the database.
 */
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails {
	@Id
	private Long userId;
	private String userName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + "]";
	}
	
	
}
