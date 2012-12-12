package base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:context/base_test_context.xml")
public abstract class AbstractOssServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

}
