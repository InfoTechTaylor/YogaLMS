package yogaLMS.service.workshop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yogaLMS.dao.YogaLMSPersistenceException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class LogWorkshopServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() throws YogaLMSPersistenceException {
    }

    @Test
    public void read() throws YogaLMSPersistenceException{
    }

    @Test
    public void update() throws YogaLMSPersistenceException{
    }

    @Test
    public void delete() throws YogaLMSPersistenceException{
    }

    @Test
    public void retrieveAll() throws YogaLMSPersistenceException{
    }
}