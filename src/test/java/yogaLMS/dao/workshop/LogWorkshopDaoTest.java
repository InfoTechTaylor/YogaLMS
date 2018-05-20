package yogaLMS.dao.workshop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.workshop.LogWorkshop;
import yogaLMS.dto.workshop.Workshop;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class LogWorkshopDaoTest {

    @Inject
    private LogWorkshopDao logWorkshopDao;

    @Inject
    private TestHelperMethods testHelperMethods;

    @Before
    public void setUp() throws Exception {
        // start with clean slate
        List<LogWorkshop> allLogWorkshops = logWorkshopDao.retrieveAll();
        for(LogWorkshop currentLogWorkshop: allLogWorkshops){
            logWorkshopDao.delete(currentLogWorkshop);
        }
    }

    @Test
    public void testCreateLogWorkshop() throws YogaLMSPersistenceException {
        // arrange
        LogWorkshop logWorkshop = testHelperMethods.createTestLogWorkshop();

        // act
        LogWorkshop createdLogWorkshop = logWorkshopDao.create(logWorkshop);

        // assert
        assertNotNull(createdLogWorkshop.getId());
        assertEquals(logWorkshop.getLog().getId(), createdLogWorkshop.getLog().getId());
        assertEquals(logWorkshop.getWorkshop().getId(), createdLogWorkshop.getWorkshop().getId());
    }

    @Test
    public void testReadLogWorkshop() throws YogaLMSPersistenceException {
        // arrange
        LogWorkshop logWorkshop = testHelperMethods.createTestLogWorkshop();
        LogWorkshop createdLogWorkshop = logWorkshopDao.create(logWorkshop);

        // act
        LogWorkshop readLogWorkshop = logWorkshopDao.read(createdLogWorkshop.getId());

        // assert
        assertEquals(createdLogWorkshop.getId(), readLogWorkshop.getId());
        assertEquals(createdLogWorkshop.getLog().getId(), readLogWorkshop.getLog().getId());
        assertEquals(createdLogWorkshop.getWorkshop().getId(), readLogWorkshop.getWorkshop().getId());
    }

    @Test
    public void testUpdateLogWorkshop() throws YogaLMSPersistenceException {
        // arrange
        LogWorkshop logWorkshop = testHelperMethods.createTestLogWorkshop();
        LogWorkshop createdLogWorkshop = logWorkshopDao.create(logWorkshop);
        Log log = new Log();
        log.setId(2L);
        Workshop workshop = new Workshop();
        workshop.setId(2L);
        createdLogWorkshop.setLog(log);
        createdLogWorkshop.setWorkshop(workshop);

        // act
        logWorkshopDao.update(createdLogWorkshop);

        // assert
        LogWorkshop logWorkshopUpdated = logWorkshopDao.read(createdLogWorkshop.getId());
        assertEquals(createdLogWorkshop.getLog().getId(), logWorkshopUpdated.getLog().getId());
        assertEquals(createdLogWorkshop.getWorkshop().getId(), logWorkshopUpdated.getWorkshop().getId());
    }

    @Test
    public void testDeleteLogWorkshop() throws YogaLMSPersistenceException {
        // arrange
        LogWorkshop logWorkshop = testHelperMethods.createTestLogWorkshop();
        logWorkshopDao.create(logWorkshop);

        // act
        logWorkshopDao.delete(logWorkshop);

        // assert
        assertNull(logWorkshopDao.read(logWorkshop.getId()));
    }

    @Test
    public void testRetrieveAllLogWorkshops() throws YogaLMSPersistenceException {
        // arrange
        for(int i=0; i<15; i++){
            LogWorkshop logWorkshop = testHelperMethods.createTestLogWorkshop();
            logWorkshopDao.create(logWorkshop);
        }

        // act
        List<LogWorkshop> allLogWorkshops = logWorkshopDao.retrieveAll();

        // assert
        assertEquals(15, allLogWorkshops.size());
    }
}