package com.anurag.tutorial.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.anurag.tutorial.model.DemoActor;
import com.anurag.tutorial.util.QueryCollectionStore;

public class TestStorage {

    @Test
    public void testWrite() {
        // Just a write, verify id set
        DemoActor user = new DemoActor();
        QueryCollectionStore<DemoActor> QueryCollectionStore = new QueryCollectionStore<DemoActor>(user);
        QueryCollectionStore.beginTransaction();
        user.setRole("SuperUser");
        assertNull(user.getId());
        QueryCollectionStore.insert(user);
        assertNotNull(user.getId());
        QueryCollectionStore.commit();
    }

    @Test
    public void testWriteAndRead() {

        // This time write and read back
        String overRideRole = "SuperUser";

        // Write
        DemoActor DemoActor = new DemoActor();
        QueryCollectionStore<DemoActor> QueryCollectionStore = new QueryCollectionStore<DemoActor>(DemoActor);
        QueryCollectionStore.beginTransaction();
        DemoActor.setRole(overRideRole);
        assertNull(DemoActor.getId());
        QueryCollectionStore.insert(DemoActor);
        assertNotNull(DemoActor.getId());
        Long id = DemoActor.getId();
        QueryCollectionStore.commit();

        // Read and verify
        DemoActor DemoActor2  = new DemoActor();
        assertEquals(DemoActor2.getRole(), DemoActor.DEFAULT_ROLE);
        QueryCollectionStore = new QueryCollectionStore<DemoActor>(DemoActor2);
        QueryCollectionStore.beginTransaction();
        DemoActor2 = QueryCollectionStore.getById(id);
        assertEquals(DemoActor2.getRole(), overRideRole);
        QueryCollectionStore.commit();
    }


    @Test
    public void testUpdate() {

        String overRideRole = "SuperUser";
        String newOverrideRole = "GUEST";

        // Insert an DemoActor
        DemoActor DemoActor = new DemoActor();
        QueryCollectionStore<DemoActor> QueryCollectionStore = new QueryCollectionStore<DemoActor>(DemoActor);
        QueryCollectionStore.beginTransaction();
        DemoActor.setRole(overRideRole);
        assertNull(DemoActor.getId());
        QueryCollectionStore.insert(DemoActor);
        assertNotNull(DemoActor.getId());
        Long id = DemoActor.getId();
        QueryCollectionStore.commit();

        // Read it back
        DemoActor DemoActor2  = new DemoActor();
        assertEquals(DemoActor2.getRole(), DemoActor.DEFAULT_ROLE);
        QueryCollectionStore.beginTransaction();
        DemoActor2 = QueryCollectionStore.getById(id);
        assertEquals(DemoActor2.getRole(), overRideRole);

        // Update it
        DemoActor2.setRole(newOverrideRole);
        QueryCollectionStore.update(DemoActor2);
        QueryCollectionStore.commit();

        // Read it again and verify update
        QueryCollectionStore.beginTransaction();
        DemoActor DemoActor3  = QueryCollectionStore.getById(id);
        assertEquals(DemoActor3.getRole(), newOverrideRole);
        QueryCollectionStore.commit();
    }

    @Test
    public void testDelete() {

        DemoActor DemoActor = new DemoActor();
        QueryCollectionStore<DemoActor> QueryCollectionStore = new QueryCollectionStore<DemoActor>(DemoActor);

        // Write
        QueryCollectionStore.beginTransaction();
        QueryCollectionStore.insert(DemoActor);
        Long id = DemoActor.getId();
        QueryCollectionStore.commit();

        // Delete it now
        assert(DemoActor.getId() > 0);
        QueryCollectionStore.beginTransaction();
        QueryCollectionStore.delete(DemoActor);
        QueryCollectionStore.commit();

        // Now we can't read it back, as expected
        QueryCollectionStore.beginTransaction();
        DemoActor DemoActor2 = QueryCollectionStore.getById(id);
        assertNull(DemoActor2);
        QueryCollectionStore.commit();
    }
}
