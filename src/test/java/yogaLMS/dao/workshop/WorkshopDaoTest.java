package yogaLMS.dao.workshop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.workshop.Workshop;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class WorkshopDaoTest {

    @Inject
    private WorkshopDao workshopDao;

    @Inject
    private TestHelperMethods testHelperMethods;

    @Before
    public void setUp() throws Exception {
        // start each test with clean slate
        List<Workshop> allWorkshops = workshopDao.retrieveAll();
        for(Workshop currentWorkshop : allWorkshops){
            workshopDao.delete(currentWorkshop);
        }
    }

    @Test
    public void testCreateWorkshop() throws YogaLMSPersistenceException {
        // arrange
        Workshop workshop = testHelperMethods.createTestWorkshop();

        // act
        Workshop workshopCreated = workshopDao.create(workshop);

        // assert
        assertNotNull(workshopCreated.getId());
        assertEquals(workshop.getName(), workshopCreated.getName());
        assertEquals(workshop.getTeacherName(), workshopCreated.getTeacherName());
        assert workshop.getNumHours() == workshopCreated.getNumHours();
        assertEquals(workshop.getDate(), workshopCreated.getDate());
    }

    @Test
    public void testReadWorkshop() throws YogaLMSPersistenceException {
        // arrange
        Workshop workshop = testHelperMethods.createTestWorkshop();
        Workshop workshopCreated = workshopDao.create(workshop);

        // act
        Workshop workshopRead = workshopDao.read(workshopCreated.getId());

        // assert
        assertEquals(workshopCreated.getId(), workshopRead.getId());
        assertEquals(workshop.getName(), workshopCreated.getName());
        assertEquals(workshop.getTeacherName(), workshopCreated.getTeacherName());
        assert workshop.getNumHours() == workshopCreated.getNumHours();
        assertEquals(workshop.getDate(), workshopCreated.getDate());
    }

    @Test
    public void testUpdateWorkshop() throws YogaLMSPersistenceException {
        // arrange
        Workshop workshop = testHelperMethods.createTestWorkshop();
        Workshop workshopCreated = workshopDao.create(workshop);
        workshopCreated.setName("Exploring Asana");
        workshopCreated.setTeacherName("Brian Serven");
        workshopCreated.setDate(LocalDate.parse("2018-06-22"));
        workshopCreated.setNumHours(1.5d);

        // act
        workshopDao.update(workshopCreated);

        // assert
        Workshop updatedWorkshop = workshopDao.read(workshopCreated.getId());
        assertEquals("Exploring Asana", updatedWorkshop.getName());
        assertEquals("Brian Serven", updatedWorkshop.getTeacherName());
        assertEquals(LocalDate.parse("2018-06-22"), updatedWorkshop.getDate());
        assert 1.5d == updatedWorkshop.getNumHours();
    }

    @Test
    public void testDeleteWorkshop() throws YogaLMSPersistenceException {
        // arrange
        Workshop workshop = testHelperMethods.createTestWorkshop();
        Workshop workshopCreated = workshopDao.create(workshop);

        // act
        workshopDao.delete(workshopCreated);

        // assert
        assertNull(workshopDao.read(workshopCreated.getId()));
    }

    @Test
    public void testRetrieveAllWorkshop() throws YogaLMSPersistenceException {
        // arrange
        for(int i=0; i<15; i++){
            Workshop workshop = testHelperMethods.createTestWorkshop();
            workshopDao.create(workshop);
        }

        // act
        List<Workshop> allWorkshops = workshopDao.retrieveAll();

        // assert
        assertEquals(15, allWorkshops.size());
    }
}