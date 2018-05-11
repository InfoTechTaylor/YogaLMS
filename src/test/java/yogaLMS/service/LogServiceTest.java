package yogaLMS.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.program.Program;
import yogaLMS.dto.user.User;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class LogServiceTest {

    @Inject
    LogService logService;

    @Inject
    TestHelperMethods testHelperMethods;

    @Before
    public void setUp() throws Exception {
        // make sure each test starts with a clean slate, zero logs
        List<Log> allLogs = logService.retrieveAll();
        for(Log currentLog : allLogs){
            logService.delete(currentLog);
        }
    }

    @Test
    public void testCreateLog() {
        // arrange test data
        User student = testHelperMethods.createTestStudent();
        Program tt1 = testHelperMethods.createTestProgram();
        Log log = new Log();
//        log.setStudent(student);
        log.setProgram(tt1);
        log.setStartDate(LocalDate.parse("2018-01-01"));
        log.setEndDate(null); // indicating log is current, student has not completed program

        // act, call method to test
        Log createdLog = logService.create(log);

        // assert expected behavior
        assertNotNull(createdLog.getId()); // assert an id was assigned
//        assertEquals(student.getFirstName(), log.getStudent().getFirstName());
//        assertEquals(student.getLastName(), log.getStudent().getLastName());
//        assertEquals(student.getEmailAddress(), log.getStudent().getEmailAddress());
        assertEquals(tt1.getName(), log.getProgram().getName());
        assertEquals(tt1.getDescription(), log.getProgram().getDescription());
        assert tt1.getNumHours() == log.getProgram().getNumHours();
        assertEquals(tt1.getStartDate(), log.getProgram().getStartDate());
        assertEquals(tt1.getEndDate(), log.getProgram().getEndDate());
    }

    @Test
    public void testReadLog() {
        // arrange test data
        User student = testHelperMethods.createTestStudent();
        Program tt1 = testHelperMethods.createTestProgram();
        Log log = new Log();
//        log.setStudent(student);
        log.setProgram(tt1);
        log.setStartDate(LocalDate.parse("2018-01-01"));
        log.setEndDate(null); // indicating log is current, student has not completed program
        Log createdLog = logService.create(log);

        // act, call method to test
        Log readLog = logService.read(createdLog.getId());

        // assert expected behavior
        assertEquals(createdLog.getId(), readLog.getId());
//        assertEquals(student.getFirstName(), readLog.getStudent().getFirstName());
//        assertEquals(student.getLastName(), readLog.getStudent().getLastName());
//        assertEquals(student.getEmailAddress(), readLog.getStudent().getEmailAddress());
        assertEquals(tt1.getName(), readLog.getProgram().getName());
        assertEquals(tt1.getDescription(), readLog.getProgram().getDescription());
        assert tt1.getNumHours() == readLog.getProgram().getNumHours();
        assertEquals(tt1.getStartDate(), readLog.getProgram().getStartDate());
        assertEquals(tt1.getEndDate(), readLog.getProgram().getEndDate());
    }

    @Test
    public void testUpdateLog() {
        // arrange test data
        User student = testHelperMethods.createTestStudent();
        Program tt1 = testHelperMethods.createTestProgram();
        Log log = new Log();
//        log.setStudent(student);
        log.setProgram(tt1);
        log.setStartDate(LocalDate.parse("2018-01-01"));
        log.setEndDate(null); // indicating log is current, student has not completed program
        Log createdLog = logService.create(log);

        // make changes to log
        createdLog.setEndDate(LocalDate.parse("2018-05-01"));

        // act, call method to test
        logService.update(createdLog);

        // assert expected behavior
        Log updatedLog = logService.read(createdLog.getId());
        assertEquals(LocalDate.parse("2018-05-01"), updatedLog.getEndDate());
    }

    @Test
    public void testDeleteLog() {
        // arrange test data
        User student = testHelperMethods.createTestStudent();
        Program tt1 = testHelperMethods.createTestProgram();
        Log log = new Log();
//        log.setStudent(student);
        log.setProgram(tt1);
        log.setStartDate(LocalDate.parse("2018-01-01"));
        log.setEndDate(null); // indicating log is current, student has not completed program
        Log createdLog = logService.create(log);

        // act, call method to test
        logService.delete(createdLog);

        // assert expected behavior
        assertEquals(null, logService.read(createdLog.getId()));
    }

    @Test
    public void testRetrieveAllLogs() {
        // arrange test data
        for(int i=0; i < 15; i++){
            User student = testHelperMethods.createTestStudent();
            Program tt1 = testHelperMethods.createTestProgram();
            Log log = new Log();
//            log.setStudent(student);
            log.setProgram(tt1);
            log.setStartDate(LocalDate.parse("2018-01-01"));
            log.setEndDate(null); // indicating log is current, student has not completed program
            logService.create(log);
        }

        // act, call method to test
        List<Log> allLogs = logService.retrieveAll();

        // assert expected behavior
        assertEquals(15, allLogs.size());
    }
}