package yogaLMS.feature_examples;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testUtils.TestHelperMethods;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.program.Program;
import yogaLMS.dto.user.StudentUser;
import yogaLMS.dto.user.User;
import yogaLMS.dto.workshop.LogWorkshop;
import yogaLMS.dto.workshop.Workshop;
import yogaLMS.dto.yogaclass.LogClass;
import yogaLMS.dto.yogaclass.YogaClass;
import yogaLMS.service.workshop.LogWorkshopService;
import yogaLMS.service.workshop.WorkshopService;
import yogaLMS.service.yogaclass.LogClassService;
import yogaLMS.service.log.LogService;
import yogaLMS.service.yogaclass.YogaClassService;

import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback(true)
public class AddAndDisplayWorkshopsExample {

    @Inject
    LogService logService;

    @Inject
    LogClassService logClassService;

    @Inject
    LogWorkshopService logWorkshopService;

    @Inject
    TestHelperMethods testHelperMethods;

    @Inject
    WorkshopService workshopService;

    /**
     * student adds workshop to log feature & system displays log back to user
     * precondition: student exists in system, student has log in system, student is enrolled in program "has a" program
     */

    User student;
    Log log;

    @Before
    public void setUp() throws Exception {
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
            logClass.setLog(this.log);
            logClass.setYogaClass(yogaClass);
            logClassService.create(logClass);
        }

        // create a bunch of workshops to simulate student already has workshops in their log
        for(int i=0; i< 5; i++){
            Workshop workshop = testHelperMethods.createTestWorkshop();
            Workshop workshopCreated = workshopService.create(workshop);
            LogWorkshop logWorkshop = new LogWorkshop();
            logWorkshop.setLog(this.log);
            logWorkshop.setWorkshop(workshopCreated);
            logWorkshopService.create(logWorkshop);
        }
    }

    @Test
    public void runExample() throws Exception {
        // *FEATURE 1.3.1: Student adds new workshop to their training log*
        Workshop newWorkshop = testHelperMethods.createTestWorkshop();
        System.out.println("Add New Workshop: ");
        System.out.println("Name: " + newWorkshop.getName());
        System.out.println("Teacher: " + newWorkshop.getTeacherName());
        System.out.println("Date: " + newWorkshop.getDate());
        System.out.println("Hours: " + newWorkshop.getNumHours());
        System.out.println();

        workshopService.create(newWorkshop);
        System.out.println("Workshop created!");
        System.out.println();

        // *FEATURE 1.3.2 System returns training log with all workshops *
        System.out.println("STUDENT TRAINING LOG: ");
        System.out.println("_____________________________________________________________________");
        System.out.println();
        System.out.println("WORKSHOPS ATTENDED: ");
        System.out.println("Aim for four hours a month to accumulate 48 hours total.");
        System.out.println("_____________________________________________________________________");
        System.out.println("| Date    |  Workshop Title            |   Teacher        |   Hours    ");
        System.out.println("_____________________________________________________________________");

        List<Workshop> allWorkshops = workshopService.retrieveAll();

        for(Workshop currentWorkshop : allWorkshops){
            System.out.println((currentWorkshop.getDate()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "  |  " +
                    currentWorkshop.getName() + "  |  " +
                    currentWorkshop.getTeacherName() + "  |  " +
                    currentWorkshop.getNumHours() + "  | ");

        }
        System.out.println("_____________________________________________________________________");

    }

}