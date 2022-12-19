import api.ConcreteMember;
import api.GroupAdmin;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void test() {
        // Create a GroupAdmin object and register two ConcreteMember objects
        GroupAdmin groupAdmin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        groupAdmin.register(member1);
        groupAdmin.register(member2);

        // Print the JVM info and the memory stats for the GroupAdmin and ConcreteMember objects
        logger.info(JvmUtilities::jvmInfo);
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        logger.info(() -> JvmUtilities.memoryStats(groupAdmin));
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        logger.info(() -> JvmUtilities.memoryStats(member1));
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        logger.info(() -> JvmUtilities.memoryStats(member2));
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }
}
