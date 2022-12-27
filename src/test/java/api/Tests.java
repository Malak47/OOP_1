package api;

import observer.JvmUtilities;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.lang.Thread;

public class Tests {

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    @Test
    public void test() {

        GroupAdmin groupAdmin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember("Malak");
        ConcreteMember member2 = new ConcreteMember("Lara");
        groupAdmin.register(member1);
        groupAdmin.register(member2);

        System.out.println("******************::Registering 4 members::******************");
        logger.info(JvmUtilities::jvmInfo);
        logger.info(() -> JvmUtilities.memoryStats(groupAdmin));
        logger.info(() -> JvmUtilities.memoryStats(member1));
        logger.info(() -> JvmUtilities.memoryStats(member2));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("******************::Adding String: \"HelloWorld!\"::******************");
        groupAdmin.append("HelloWorld!");
        logger.info(JvmUtilities::jvmInfo);
        logger.info(() -> JvmUtilities.memoryStats(groupAdmin));
        logger.info(() -> JvmUtilities.memoryStats(member1));
        logger.info(() -> JvmUtilities.memoryStats(member2));
    }
}
