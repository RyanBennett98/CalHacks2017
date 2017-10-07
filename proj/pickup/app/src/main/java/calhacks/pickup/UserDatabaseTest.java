package calhacks.pickup;
import org.junit.Test;
import static org.junit.Assert.*;

import ucb.junit.textui;

public class UserDatabaseTest {
    @Test
    public void testUser() {
        Database d = new Database();
        User u = new User("Ben", "Ta", d);

        assertEquals(u, d.getUser("Ben"));
    }



    public static void main (String[] args) {
            System.exit(textui.runClasses(UserDatabaseTest.class));

    }
}