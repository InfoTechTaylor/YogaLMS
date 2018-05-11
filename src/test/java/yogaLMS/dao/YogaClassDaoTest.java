package yogaLMS.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dto.log.YogaClass;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
//@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class YogaClassDaoTest {

    @Inject
    private YogaClassDao classDao;

    @Inject
    private TestHelperMethods testHelperMethods;

    @Before
    public void setup(){
        // make sure each test starts with a clean slate, zero classes
        List<YogaClass> allClasses = classDao.retrieveAll();
        for(YogaClass currentYogaClass: allClasses){
            classDao.delete(currentYogaClass);
        }
    }

    @Test
    public void testCreateYogaClass() {
        // arrange test data
        YogaClass testYogaClass = new YogaClass();
        testYogaClass.setClassName("Yoga I");
        testYogaClass.setDate(LocalDate.parse("2018-05-08"));
        testYogaClass.setHours(1d);
        testYogaClass.setStudioName("YogaLife");
        testYogaClass.setTeacherName("Julie Rost");

        // act, call method to test
        YogaClass createdYogaClass = classDao.create(testYogaClass);

        // assert expected behavior
        assertNotNull(createdYogaClass.getId()); // assert an id is set on new yoga class
        assertEquals("Yoga I", createdYogaClass.getClassName());
        assertEquals(LocalDate.parse("2018-05-08"), createdYogaClass.getDate());
        assert 1d == createdYogaClass.getHours(); // assert equals on double appears to be depricated
        assertEquals("YogaLife", createdYogaClass.getStudioName());
        assertEquals("Julie Rost", createdYogaClass.getTeacherName());
    }

    @Test
    public void testReadYogaClass() {
        // arrange test data
        YogaClass testYogaClass = testHelperMethods.createTestYogaClass(); // same yoga class as above but use test methods for cleaner code
        YogaClass createdYogaClass = classDao.create(testYogaClass);

        // act, call method to test
        YogaClass readYogaClass = classDao.read(createdYogaClass.getId());

        // assert expected behavior
        assertNotNull(readYogaClass.getId()); // assert an id is set on new yoga class
        assertEquals("Yoga I", readYogaClass.getClassName());
        assertEquals(LocalDate.parse("2018-05-08"), readYogaClass.getDate());
        assert 1d == readYogaClass.getHours(); // assert equals on double appears to be depricated
        assertEquals("YogaLife", readYogaClass.getStudioName());
        assertEquals("Julie Rost", readYogaClass.getTeacherName());
    }

    @Test
    public void testUpdateYogaClass() {
        // arrange test data
        YogaClass testYogaClass = testHelperMethods.createTestYogaClass(); // same yoga class as above but use test methods for cleaner code
        YogaClass createdYogaClass = classDao.create(testYogaClass);

        // modify the class to pass to update function
        createdYogaClass.setStudioName("Bending Bodhi");
        createdYogaClass.setTeacherName("Janis Sheldon");
        createdYogaClass.setHours(1.5d);
        createdYogaClass.setDate(LocalDate.parse("2018-05-09"));
        createdYogaClass.setClassName("Yoga II");

        // act, call method to test
        classDao.update(createdYogaClass);

        // assert expected behavior
        YogaClass updatedClass = classDao.read(createdYogaClass.getId());
        assertEquals(createdYogaClass.getId(), updatedClass.getId());
        assertEquals("Yoga II", updatedClass.getClassName());
        assertEquals(LocalDate.parse("2018-05-09"), updatedClass.getDate());
        assert 1.5d == updatedClass.getHours(); // assert equals on double appears to be depricated
        assertEquals("Bending Bodhi", updatedClass.getStudioName());
        assertEquals("Janis Sheldon", updatedClass.getTeacherName());
    }

    @Test
    public void testDeleteYogaClass() {
        // arrange test data
        YogaClass testYogaClass = testHelperMethods.createTestYogaClass(); // same yoga class as above but use test methods for cleaner code
        YogaClass createdYogaClass = classDao.create(testYogaClass);

        // act, call method to test
        classDao.delete(createdYogaClass);

        // assert expected behavior
        assertNull(classDao.read(createdYogaClass.getId()));
    }

    @Test
    public void testRetrieveAllYogaClasses() {
        // arrange test data
        for(int i = 0; i < 15; i++){
            classDao.create(testHelperMethods.createTestYogaClass());
        }

        // act, call method to test
        List<YogaClass>  allYogaClasses = classDao.retrieveAll();

        // assert expected behavior
        assertEquals(15, allYogaClasses.size());
    }

    @Test
    public void testRetrieveAllClassesByStudio() {
        // arrange test data
        for(int i = 0; i < 7; i++){
            // create 7 classes with "YogaLife" as studio which is what is set in the testYogaClass helper method
            classDao.create(testHelperMethods.createTestYogaClass());
        }

        for(int i = 0; i < 3; i++){
            // create 3 classes with "Bending Bodhi" as studio
            YogaClass yogaClass = testHelperMethods.createTestYogaClass();
            yogaClass.setStudioName("Bending Bodhi");
            classDao.create(yogaClass);
        }

        // act, call method to test
        List<YogaClass> allYogaLifeClasses = classDao.retrieveAllByStudio("YogaLife");

        // assert expected behavior
        assertEquals(7, allYogaLifeClasses.size());
    }
}