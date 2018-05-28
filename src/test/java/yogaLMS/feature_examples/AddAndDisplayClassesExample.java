package yogaLMS.feature_examples;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.yogaclass.LogClass;
import yogaLMS.dto.yogaclass.YogaClass;
import yogaLMS.dto.program.Program;
import yogaLMS.dto.user.StudentUser;
import yogaLMS.dto.user.User;
import yogaLMS.service.yogaclass.LogClassService;
import yogaLMS.service.log.LogService;
import yogaLMS.service.yogaclass.YogaClassService;

import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class AddAndDisplayClassesExample {

    @Inject
    LogService logService;

    @Inject
    LogClassService logClassService;

    @Inject
    YogaClassService yogaClassService;

    @Inject
    TestHelperMethods testHelperMethods;

    /**
     * student adds class to log feature
     * precondition: student exists in system, student has log in system, student is enrolled in program "has a" program
     */

    User student;
    Log log;

    @Before
    public void setUp() throws Exception {
        // start with clean slate, delete all saved objects
        List<YogaClass> allClasses = yogaClassService.retrieveAll();
        for(YogaClass currentClass: allClasses){
            yogaClassService.delete(currentClass);
        }

        // setup some object to fulfill preconditions
        // setup training program to fulfill precondition
        Program tt1 = testHelperMethods.createTestProgram();

        // setup log to fulfill precondition
        this.log = testHelperMethods.createTestLog();

        // setup student to fulfill precondition
        this.student = testHelperMethods.createTestStudent();
        ((StudentUser)student).setLog(this.log); // downcasting example

        // create a bunch of yoga classes to simulate student already has classes in their log
        for(int i=0; i < 5; i++){
            YogaClass yogaClass = testHelperMethods.createTestYogaClassAndSave();
            LogClass logClass = new LogClass();
            logClass.setLog(log);
            logClass.setYogaClass(yogaClass);
            logClassService.create(logClass);
        }
    }

    @Test
    public void runExample() throws Exception {
        // *FEATURE 1.3.1: Student adds new yog class to their training log*
        // create class object to simulate student adding class details
        YogaClass yogaClass = testHelperMethods.createTestYogaClass();
        yogaClassService.create(yogaClass);

        System.out.println("Class info added: ");
        System.out.println("Date: " + yogaClass.getDate());
        System.out.println("Class Name: " + yogaClass.getClassName());
        System.out.println("Teacher: " + yogaClass.getTeacherName());
        System.out.println("Studio: " + yogaClass.getStudioName());
        System.out.println("Hours: " + yogaClass.getHours());

        System.out.println("New class created!"); // illustrative output

        // associate yoga class to particular student's log
        LogClass logClass = new LogClass();
        logClass.setLog(log);
        logClass.setYogaClass(yogaClass);
        logClassService.create(logClass);

        System.out.println("Class added to Log."); // illustrative output

        // *FEATURE 1.3.2 System returns training log with all classes *
        // get all yoga classes in student log
        List<YogaClass> allClasses = yogaClassService.retrieveAllByLogId(log.getId());

        // print log back to student
        System.out.println("Here is your log:");
        System.out.println();
        System.out.println("  Month  |   Count  |  Date  |  Studio  |  Teacher  ");
        System.out.println("____________________________________________________________");

        int counter = 1;
        for(YogaClass currentClass : allClasses){
            System.out.println((currentClass.getDate()).format(DateTimeFormatter.ofPattern("MMMM yyyy")) + "  |  " +
                                counter +  "  |  " +
                                (currentClass.getDate()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "  |  " +
                                currentClass.getStudioName() + "  |  " +
                                currentClass.getTeacherName());
            counter++;
        }
    }

}