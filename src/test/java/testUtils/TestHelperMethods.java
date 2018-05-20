package testUtils;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dao.log.LogDao;
import yogaLMS.dao.workshop.WorkshopDao;
import yogaLMS.dao.yogaclass.YogaClassDao;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.program.Program;
import yogaLMS.dto.program.TT1Program;
import yogaLMS.dto.user.StudentUser;
import yogaLMS.dto.user.User;
import yogaLMS.dto.workshop.LogWorkshop;
import yogaLMS.dto.workshop.Workshop;
import yogaLMS.dto.yogaclass.YogaClass;

import javax.inject.Inject;
import java.time.LocalDate;

public class TestHelperMethods {

    private YogaClassDao yogaClassDao;
    private LogDao logDao;
    private WorkshopDao workshopDao;

    @Inject
    public TestHelperMethods(YogaClassDao yogaClassDao, LogDao logDao, WorkshopDao workshopDao){
        this.yogaClassDao = yogaClassDao;
        this.logDao = logDao;
        this.workshopDao = workshopDao;
    }

    public YogaClass createTestYogaClass(){
        YogaClass newYogaClass = new YogaClass();
        newYogaClass.setClassName("Yoga I");
        newYogaClass.setDate(LocalDate.parse("2018-05-08"));
        newYogaClass.setHours(1d);
        newYogaClass.setStudioName("YogaLife");
        newYogaClass.setTeacherName("Julie Rost");
        return newYogaClass;
    }

    public YogaClass createTestYogaClassAndSave(){
        YogaClass newYogaClass = new YogaClass();
        newYogaClass.setClassName("Yoga I");
        newYogaClass.setDate(LocalDate.parse("2018-05-08"));
        newYogaClass.setHours(1d);
        newYogaClass.setStudioName("YogaLife");
        newYogaClass.setTeacherName("Julie Rost");
        yogaClassDao.create(newYogaClass);
        return newYogaClass;
    }

    public User createTestStudent(){
        User newStudent = new StudentUser();
        newStudent.setFirstName("Taylor");
        newStudent.setLastName("Lapointe");
        newStudent.setEmailAddress("test@gmail.com");
        return newStudent;
    }

    public Program createTestProgram(){
        Program newProgram = new TT1Program();
        newProgram.setName("TT1");
        newProgram.setDescription("200 hour teacher training");
        newProgram.setNumHours(200d);
        newProgram.setStartDate(LocalDate.parse("2000-01-01"));
        newProgram.setEndDate(null); // indicating this is an active program
        return newProgram;
    }

    public Log createTestLog(){
        Log log = new Log();
        User student = createTestStudent();
        Program tt1 = createTestProgram();
//        log.setStudent(student);
        log.setProgram(tt1);
        log.setStartDate(LocalDate.parse("2018-01-01"));
        log.setEndDate(null);
        logDao.create(log);
        return log;
    }

    public Workshop createTestWorkshop(){
        Workshop workshop = new Workshop();
        workshop.setName("Art of Teaching Vinyasa");
        workshop.setTeacherName("Julie Rost");
        workshop.setNumHours(2d);
        workshop.setDate(LocalDate.parse("2018-05-10"));
        return workshop;
    }

    public LogWorkshop createTestLogWorkshop(){
        Workshop workshop = createTestWorkshop();

        try {
            workshop = workshopDao.create(workshop);
        } catch (YogaLMSPersistenceException e) {
            e.printStackTrace();
        }
        Log log = createTestLog();
        LogWorkshop logWorkshop = new LogWorkshop();
        logWorkshop.setWorkshop(workshop);
        logWorkshop.setLog(log);
        return logWorkshop;
    }

}
