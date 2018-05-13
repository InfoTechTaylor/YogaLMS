package yogaLMS.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.log.LogClass;
import yogaLMS.dto.log.YogaClass;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class LogClassDaoTest {

    @Inject
    LogClassDao logClassDao;

    @Inject
    TestHelperMethods testHelperMethods;

    @Before
    public void setUp() throws Exception {
        // make sure each test starts with a clean slate, zero logClasses
        List<LogClass> allLogClasses = logClassDao.retrieveAll();
        for(LogClass currentLogClass : allLogClasses){
            logClassDao.delete(currentLogClass);
        }
    }

    @Test
    public void testCreateLogClass() {
        // arrange test data
        Log log = testHelperMethods.createTestLog();
        YogaClass yogaClass = testHelperMethods.createTestYogaClassAndSave();
        LogClass logClass = new LogClass();
        logClass.setLog(log);
        logClass.setYogaClass(yogaClass);

        // act, call method to test
        LogClass createdLogClass = logClassDao.create(logClass);

        // assert expected behavior
        assertNotNull(createdLogClass.getId());
        assertEquals(log.getStartDate(), createdLogClass.getLog().getStartDate());
        assertEquals(log.getEndDate(), createdLogClass.getLog().getEndDate());
        assertEquals(log.getProgram().getId(), createdLogClass.getLog().getProgram().getId());
//        assertEquals(log.getStudent().getId(), createdLogClass.getLog().getStudent().getId());
        assertEquals(log.getId(), createdLogClass.getLog().getId());
        assertEquals(yogaClass.getId(), createdLogClass.getYogaClass().getId());
        assertEquals(yogaClass.getClassName(), createdLogClass.getYogaClass().getClassName());
        assertEquals(yogaClass.getStudioName(), createdLogClass.getYogaClass().getStudioName());
        assertEquals(yogaClass.getTeacherName(), createdLogClass.getYogaClass().getTeacherName());
        assertEquals(yogaClass.getDate(), createdLogClass.getYogaClass().getDate());
        assert yogaClass.getHours() == createdLogClass.getYogaClass().getHours();

    }

    @Test
    public void testReadLogClass() {
        // arrange test data
        Log log = testHelperMethods.createTestLog();
        YogaClass yogaClass = testHelperMethods.createTestYogaClassAndSave();
        LogClass logClass = new LogClass();
        logClass.setLog(log);
        logClass.setYogaClass(yogaClass);
        LogClass createdLogClass = logClassDao.create(logClass);

        // act, call method to test
        LogClass readLogClass = logClassDao.read(createdLogClass.getId());

        // assert expected behavior
        assertEquals(createdLogClass.getId(), readLogClass.getId());
        assertEquals(log.getId(), readLogClass.getLog().getId());

    }

    @Test
    public void testUpdateLogClass() {
        // arrange test data
        Log log = testHelperMethods.createTestLog();
        YogaClass yogaClass = testHelperMethods.createTestYogaClassAndSave();
        LogClass logClass = new LogClass();
        logClass.setLog(log);
        logClass.setYogaClass(yogaClass);
        LogClass createdLogClass = logClassDao.create(logClass);

        // modify LogClass
        YogaClass newYogaClass = testHelperMethods.createTestYogaClassAndSave();
        Log newLog = testHelperMethods.createTestLog();
        createdLogClass.setYogaClass(newYogaClass);
        createdLogClass.setLog(newLog);

        // act, call method to test
        logClassDao.update(createdLogClass);

        // assert expected behavior
        LogClass readLogClass = logClassDao.read(createdLogClass.getId());
        assertEquals(createdLogClass.getId(), readLogClass.getId());
        assertEquals(newYogaClass.getId(), readLogClass.getYogaClass().getId());
        assertEquals(newLog.getId(), readLogClass.getLog().getId());
    }

    @Test
    public void testDeleteLogClass() {
        // arrange test data
        Log log = testHelperMethods.createTestLog();
        YogaClass yogaClass = testHelperMethods.createTestYogaClassAndSave();
        LogClass logClass = new LogClass();
        logClass.setLog(log);
        logClass.setYogaClass(yogaClass);
        LogClass createdLogClass = logClassDao.create(logClass);

        // act, call method to test
        logClassDao.delete(createdLogClass);

        // assert expected behavior
        assertNull(logClassDao.read(createdLogClass.getId()));
    }

    @Test
    public void testRetrieveAllLogClasses() {
        // arrange test data
        for(int i=0; i < 15; i++){
            Log log = testHelperMethods.createTestLog();
            YogaClass yogaClass = testHelperMethods.createTestYogaClassAndSave();
            LogClass logClass = new LogClass();
            logClass.setLog(log);
            logClass.setYogaClass(yogaClass);
            logClassDao.create(logClass);
        }

        // act, call method to test
        List<LogClass> allLogClasses = logClassDao.retrieveAll();

        // assert expected behavior
        assertEquals(15, allLogClasses.size());
    }
}