package com.anurag.tutorial.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.anurag.tutorial.model.UserDetails;
import com.anurag.tutorial.util.QueryCollectionStore;

public class TestUserDetails {

	    @Test
	    public void testWrite() {
	        // Just a write, verify id set
	        UserDetails user = new UserDetails();
	        QueryCollectionStore<UserDetails> QueryCollectionStore = new QueryCollectionStore<UserDetails>(user);
	        QueryCollectionStore.beginTransaction();
	        assertNull(user.getUserId());
	        user.setUserId(1l);
	        user.setUserName("Anurag");	        
	        QueryCollectionStore.insert(user);
	        assertNotNull(user.getUserId());
	        QueryCollectionStore.commit();
	    }
}
