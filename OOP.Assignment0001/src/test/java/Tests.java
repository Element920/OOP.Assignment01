import observer.ConcreteMember;
import observer.GroupAdmin;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(JvmUtilities::jvmInfo);
    }

    @Test
    void GroupAdmin(){
        GroupAdmin groupAdmin1 = new GroupAdmin();
        assertEquals(groupAdmin1.getCurrentData(),"");
        GroupAdmin groupAdmin2 = new GroupAdmin("Tests");
        assertEquals(groupAdmin2.getCurrentData(),"Tests");
    }
    @Test
    void register() {
        GroupAdmin groupAdmin = new GroupAdmin();

        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();
        ConcreteMember member4 = new ConcreteMember();

        /**
         * checking if a member that is already signed in
         * if he does then do nothing
         */
        groupAdmin.register(member1);
        groupAdmin.register(member1);
        groupAdmin.register(member1);
        assertEquals(1,groupAdmin.getMembers().size());

        groupAdmin.register(member2);
        groupAdmin.register(member2);
        assertEquals(2,groupAdmin.getMembers().size());
        groupAdmin.register(member3);
        groupAdmin.register(member4);
        assertEquals(4,groupAdmin.getMembers().size());
        logger.info(()->"Before actions on UndoableStringBuilder: "+JvmUtilities.objectTotalSize(groupAdmin));

        groupAdmin.append("Tests");
        groupAdmin.append("The");
        groupAdmin.append("Register");
        groupAdmin.append("Method");

        assertEquals("Tests The Register Method ", member1.toString());

        groupAdmin.undo();
        groupAdmin.undo();
        groupAdmin.undo();
        assertEquals("Tests ",member1.getCurrentData());
        groupAdmin.delete(3,4);
        assertEquals("Tess ",member2.getCurrentData());
        groupAdmin.insert(3,"t");
        assertEquals("Tests ",member2.getCurrentData());

        assertEquals("Tests ",member3.getCurrentData());
        logger.info(()->"After actions on UndoableStringBuilder: "+JvmUtilities.objectTotalSize(groupAdmin));
    }
    @Test
    void unregister() {
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();
        ConcreteMember member4 = new ConcreteMember();
        ConcreteMember member5 = new ConcreteMember();
        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.register(member1);
        groupAdmin.register(member2);
        groupAdmin.register(member3);
        groupAdmin.register(member4);
        groupAdmin.append("Testing");
        assertEquals("Tests",member1.getCurrentData());
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));
        groupAdmin.unregister(member1);
        groupAdmin.unregister(member2);
        groupAdmin.unregister(member3);
        groupAdmin.unregister(member4);
        groupAdmin.unregister(member5);
        assertEquals(0,groupAdmin.getMembers().size());
        logger.info(()->JvmUtilities.objectTotalSize(groupAdmin));
        assertNull(member1.getCurrentData());
        assertNull(member2.getCurrentData());
        assertNull(member3.getCurrentData());
        assertNull(member4.getCurrentData());
        assertNull(member5.getCurrentData());
    }
}