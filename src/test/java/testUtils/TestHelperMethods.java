package testUtils;

import yogaLMS.dao.YogaLMSPersistenceException;
import yogaLMS.dao.log.LogDao;
import yogaLMS.dao.workshop.WorkshopDao;
import yogaLMS.dao.yogaclass.YogaClassDao;
import yogaLMS.dto.log.Log;
import yogaLMS.dto.program.Program;
import yogaLMS.dto.user.StudentUser;
import yogaLMS.dto.user.User;
import yogaLMS.dto.workshop.LogWorkshop;
import yogaLMS.dto.workshop.Workshop;
import yogaLMS.dto.yogaclass.YogaClass;
import yogaLMS.dto.yogapose.Chakra;
import yogaLMS.dto.yogapose.YogaPose;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public YogaClass createTestYogaClassAndSave() throws YogaLMSPersistenceException {
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
        newStudent.setEmailAddress("taylor@gmail.com");
        return newStudent;
    }
    public User createTestFaculty(){
        User newStudent = new StudentUser();
        newStudent.setFirstName("Julie");
        newStudent.setLastName("Rost");
        newStudent.setEmailAddress("julie@gmail.com");
        return newStudent;
    }

    public Program createTestProgram(){
        Program newProgram = new Program();
        newProgram.setId(1L);
        newProgram.setName("TT1");
        newProgram.setDescription("200 hour teacher training");
        newProgram.setNumHours(200d);
        newProgram.setStartDate(LocalDate.parse("2000-01-01"));
        newProgram.setEndDate(null); // indicating this is an active program
        return newProgram;
    }

    public Program createTestProgramAndSave(){
        Program newProgram = new Program();
        newProgram.setId(1L);
        newProgram.setName("TT1");
        newProgram.setDescription("200 hour teacher training");
        newProgram.setNumHours(200d);
        newProgram.setStartDate(LocalDate.parse("2000-01-01"));
        newProgram.setEndDate(null); // indicating this is an active program
        return newProgram;
    }

    public Log createTestLog() throws YogaLMSPersistenceException {
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

    public LogWorkshop createTestLogWorkshop() throws YogaLMSPersistenceException {
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

    public YogaPose createTestYogaPose(){
        YogaPose yogaPose = new YogaPose();
        yogaPose.setName("Vrikshasana 'Tree Pose'");
        List<String> intentions = new ArrayList<>();
        intentions.add("Personal intention based on individual growth");
        yogaPose.setIntention(intentions);
        List<String> attitudes = new ArrayList<>();
        attitudes.add("Giving");
        attitudes.add("The abundance of a tree");
        yogaPose.setAttitudes(attitudes);
        List<String> archetypes = new ArrayList<>();
        archetypes.add("Deeply rooted while reaching for the sky");
        yogaPose.setArchetypes(archetypes);
        List<String> posturalAlignments = new ArrayList<>();
        posturalAlignments.add("Rest foot on ankle, calf, or inner thigh");
        posturalAlignments.add("Lifted knee turns out to the side");
        posturalAlignments.add("Press foot into leg and leg into foot");
        posturalAlignments.add("Lengthen spine upward, relax shoulders");
        posturalAlignments.add("Upper arms close to ears");
        yogaPose.setPosturalAlignments(posturalAlignments);
        List<String> breathings = new ArrayList<>();
        breathings.add("Natural deep breathing");
        yogaPose.setBreathingTechniques(breathings);
        List<String> mentalFocuses = new ArrayList<>();
        mentalFocuses.add("Root - one foot beneath the floor");
        mentalFocuses.add("Heart - feeling the giving nature of tree");
        yogaPose.setMentalFocuses(mentalFocuses);
        List<Chakra> chakras = new ArrayList<>();
        chakras.add(Chakra.FIRST);
        chakras.add(Chakra.FOURTH);
        yogaPose.setChakras(chakras);
        List<String> locksAndSeals = new ArrayList<>();
        locksAndSeals.add("Prayer mudra, hands above head or hands at heart");
        locksAndSeals.add("Root lock");
        yogaPose.setLockAndSeals(locksAndSeals);
        List<String> psychBlocks = new ArrayList<>();
        psychBlocks.add("Challenges attachment to balance");
        psychBlocks.add("Aversion to imbalance");
        psychBlocks.add("Fear of falling");
        yogaPose.setPsychologicalBlocks(psychBlocks);
        List<String> emotionalTransformations = new ArrayList<>();
        emotionalTransformations.add("Worry vs. Faith");
        yogaPose.setEmotionalTransformations(emotionalTransformations);

        return yogaPose;
    }

}
