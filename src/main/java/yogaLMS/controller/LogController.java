package yogaLMS.controller;

import org.springframework.stereotype.Controller;
import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.yogaclass.LogClass;
import yogaLMS.dto.yogaclass.YogaClass;
import yogaLMS.dto.program.Program;
import yogaLMS.dto.program.TT2Program;
import yogaLMS.dto.user.StudentUser;
import yogaLMS.dto.user.User;
import yogaLMS.service.yogaclass.LogClassService;
import yogaLMS.service.log.LogService;
import yogaLMS.service.yogaclass.YogaClassService;

import java.time.LocalDate;

@Controller
//@RequestMapping(value="/log")
public class LogController {

    private LogClassService logClassService;
    private LogService logService;
    private YogaClassService yogaClassService;

    public LogController(LogClassService logClassService, LogService logService, YogaClassService yogaClassService){
        this.logClassService = logClassService;
        this.logService = logService;
        this.yogaClassService = yogaClassService;
    }

    public void run(){
        studentAddsClassToLog();
    }

    /**
     * student adds class to log feature
     * precondition: student exists in system, student has log in system, student is enrolled in program "has a" program
     */
    private void studentAddsClassToLog(){
        // setup program to fulfill precondition
        Program tt2 = new TT2Program();
        tt2.setName("TT2");
        tt2.setDescription("300 hour teacher training that is taken after completion of TT1 program.");
        tt2.setNumHours(300d);
        tt2.setStartDate(LocalDate.parse("2000-01-01"));
        tt2.setEndDate(LocalDate.parse("2018-01-01"));
        System.out.println("Adding class for " + tt2.getName() + " program...");

        // setup log to fulfill precondition
        Log log = new Log();
        log.setProgram(tt2);
        log.setStartDate(LocalDate.parse("2016-07-01"));
        log.setEndDate(null);
        Log createdLog = null;
        try {
            createdLog = logService.create(log);
        } catch (YogaLMSPersistenceException e) {
            e.printStackTrace();
        }

        // setup student to fulfill precondition
        User student = new StudentUser();
        student.setFirstName("Taylor");
        student.setLastName("Lapointe");
        student.setEmailAddress("test@email.com");
        ((StudentUser)student).setLog(createdLog); // downcasting
        System.out.println("for student " + student.getFirstName() + " " + student.getLastName() + " ...");

        // create class object in memory to simulate student adding class details in UI
        YogaClass yogaClass = new YogaClass();
        yogaClass.setClassName("Yoga I");
        yogaClass.setStudioName("YogaLife");
        yogaClass.setDate(LocalDate.parse("2018-05-10"));
        yogaClass.setHours(1d);
        yogaClass.setTeacherName("Julie Rost");
        // once the above is retrieved from UI, it will be passed as object to the YogaClassService
        try {
            yogaClassService.create(yogaClass);
        } catch (YogaLMSPersistenceException e) {
            e.printStackTrace();
        }

        // some illustrative output
        System.out.println("Class created: ");
        System.out.println("Class Name: " + yogaClass.getClassName());
        System.out.println("Studio: " + yogaClass.getStudioName());
        System.out.println("Date: " + yogaClass.getDate());
        System.out.println("Hours: " + yogaClass.getHours());
        System.out.println("Teacher: " + yogaClass.getTeacherName());

        // associate yoga class to particular student's log
        LogClass logClass = new LogClass();
        logClass.setLog(log);
        logClass.setYogaClass(yogaClass);
        try {
            logClassService.create(logClass);
        } catch (YogaLMSPersistenceException e) {
            e.printStackTrace();
        }

        // some illustrative output
        System.out.println("Class added to " + student.getFirstName() + " " + student.getLastName() + "'s log.");

    }

}
