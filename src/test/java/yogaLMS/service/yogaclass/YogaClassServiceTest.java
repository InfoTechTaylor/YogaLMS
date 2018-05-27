package yogaLMS.service.yogaclass;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.yogaclass.LogClass;
import yogaLMS.dto.yogaclass.YogaClass;
import yogaLMS.service.yogaclass.LogClassService;
import yogaLMS.service.yogaclass.YogaClassService;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class YogaClassServiceTest {

    @Inject
    YogaClassService yogaClassService;

    @Inject
    LogClassService logClassService;

    @Inject
    private TestHelperMethods testHelperMethods;

    @Before
    public void setUp() throws Exception {
        // make sure each test starts with a clean slate, zero classes
        List<YogaClass> allYogaClasses = yogaClassService.retrieveAll();
        for(YogaClass currentYogaClass: allYogaClasses){
            yogaClassService.delete(currentYogaClass);
        }
    }

    @Test
    public void testCreateYogaClassService() throws YogaLMSPersistenceException {
        // arrange test data
        YogaClass testYogaClass = new YogaClass();
        testYogaClass.setClassName("Yoga I");
        testYogaClass.setDate(LocalDate.parse("2018-05-08"));
        testYogaClass.setHours(1d);
        testYogaClass.setStudioName("YogaLife");
        testYogaClass.setTeacherName("Julie Rost");

        // act, call method to test
        YogaClass createdYogaClass = yogaClassService.create(testYogaClass);

        // assert expected behavior
        assertNotNull(createdYogaClass.getId()); // assert an id is set on new yoga class
        assertEquals("Yoga I", createdYogaClass.getClassName());
        assertEquals(LocalDate.parse("2018-05-08"), createdYogaClass.getDate());
        assert 1d == createdYogaClass.getHours(); // assert equals on double appears to be depricated
        assertEquals("YogaLife", createdYogaClass.getStudioName());
        assertEquals("Julie Rost", createdYogaClass.getTeacherName());
    }

    @Test
    public void testReadYogaClassService() throws YogaLMSPersistenceException {
        // arrange test data
        YogaClass testYogaClass = testHelperMethods.createTestYogaClass(); // same yoga class as above but use test methods for cleaner code
        YogaClass createdYogaClass = yogaClassService.create(testYogaClass);

        // act, call method to test
        YogaClass readYogaClass = yogaClassService.read(createdYogaClass.getId());

        // assert expected behavior
        assertNotNull(readYogaClass.getId()); // assert an id is set on new yoga class
        assertEquals("Yoga I", readYogaClass.getClassName());
        assertEquals(LocalDate.parse("2018-05-08"), readYogaClass.getDate());
        assert 1d == readYogaClass.getHours(); // assert equals on double appears to be depricated
        assertEquals("YogaLife", readYogaClass.getStudioName());
        assertEquals("Julie Rost", readYogaClass.getTeacherName());
    }

    @Test
    public void TestUpdateYogaClassService() throws YogaLMSPersistenceException {
        // arrange test data
        YogaClass testYogaClass = testHelperMethods.createTestYogaClass(); // same yoga class as above but use test methods for cleaner code
        YogaClass createdYogaClass = yogaClassService.create(testYogaClass);

        // modify the class to pass to update function
        createdYogaClass.setStudioName("Bending Bodhi");
        createdYogaClass.setTeacherName("Janis Sheldon");
        createdYogaClass.setHours(1.5d);
        createdYogaClass.setDate(LocalDate.parse("2018-05-09"));
        createdYogaClass.setClassName("Yoga II");

        // act, call method to test
        yogaClassService.update(createdYogaClass);

        // assert expected behavior
        YogaClass updatedClass = yogaClassService.read(createdYogaClass.getId());
        assertEquals(createdYogaClass.getId(), updatedClass.getId());
        assertEquals("Yoga II", updatedClass.getClassName());
        assertEquals(LocalDate.parse("2018-05-09"), updatedClass.getDate());
        assert 1.5d == updatedClass.getHours(); // assert equals on double appears to be depricated
        assertEquals("Bending Bodhi", updatedClass.getStudioName());
        assertEquals("Janis Sheldon", updatedClass.getTeacherName());
    }

    @Test
    public void testDeleteYogaClassService() throws YogaLMSPersistenceException {
        // arrange test data
        YogaClass testYogaClass = testHelperMethods.createTestYogaClass(); // same yoga class as above but use test methods for cleaner code
        YogaClass createdYogaClass = yogaClassService.create(testYogaClass);

        // act, call method to test
        yogaClassService.delete(createdYogaClass);

        // assert expected behavior
        assertNull(yogaClassService.read(createdYogaClass.getId()));
    }

    @Test
    public void testRetrieveAllYogaClassService() throws YogaLMSPersistenceException {
        // arrange test data
        for(int i = 0; i < 15; i++){
            yogaClassService.create(testHelperMethods.createTestYogaClass());
        }

        // act, call method to test
        List<YogaClass>  allYogaClasses = yogaClassService.retrieveAll();

        // assert expected behavior
        assertEquals(15, allYogaClasses.size());
    }

    @Test
    public void testRetrieveAllByStudioYogaClassService() throws YogaLMSPersistenceException {
        // arrange test data
        for(int i = 0; i < 7; i++){
            // create 7 classes with "YogaLife" as studio which is what is set in the testYogaClass helper method
            yogaClassService.create(testHelperMethods.createTestYogaClass());
        }

        for(int i = 0; i < 3; i++){
            // create 3 classes with "Bending Bodhi" as studio
            YogaClass yogaClass = testHelperMethods.createTestYogaClass();
            yogaClass.setStudioName("Bending Bodhi");
            yogaClassService.create(yogaClass);
        }

        // act, call method to test
        List<YogaClass> allYogaLifeClasses = yogaClassService.retrieveAllByStudio("YogaLife");

        // assert expected behavior
        assertEquals(7, allYogaLifeClasses.size());
    }

    @Test
    public void testRetrieveAllByLogId() throws YogaLMSPersistenceException {
        // arrange test data
        Log log1 = testHelperMethods.createTestLog();
        Log log2 = testHelperMethods.createTestLog();
        for (int i=0; i<7; i++){
            YogaClass yogaClass = testHelperMethods.createTestYogaClassAndSave();
            LogClass logClass = new LogClass();
            logClass.setLog(log1);
            logClass.setYogaClass(yogaClass);
            logClassService.create(logClass);
        }

        for (int i=0; i<5; i++){
            YogaClass yogaClass = testHelperMethods.createTestYogaClassAndSave();
            LogClass logClass = new LogClass();
            logClass.setLog(log2);
            logClass.setYogaClass(yogaClass);
            logClassService.create(logClass);
        }


        // act, call method to test
        List<YogaClass> allYogaClassesByLog1 = yogaClassService.retrieveAllByLogId(log1.getId());
        List<YogaClass> allYogaClassesByLog2 = yogaClassService.retrieveAllByLogId(log2.getId());

        // assert expected behavior
        assertEquals(7, allYogaClassesByLog1.size());
        assertEquals(5, allYogaClassesByLog2.size());
    }
}