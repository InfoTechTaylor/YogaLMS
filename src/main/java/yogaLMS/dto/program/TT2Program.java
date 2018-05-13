package yogaLMS.dto.program;

public class TT2Program extends Program {

    private int numYogaClass;
    private int numMentorMeetings;

    @Override
    public String generateRequirementsList() {
        String requirements = this.getName() + " Requirements:\n " +
                "Total Hours: " + this.getNumHours() +
                "Number of Yoga Classes: " + numYogaClass +
                "Number of Mentor Meetings: " + numMentorMeetings;

        return requirements;
    }
}
