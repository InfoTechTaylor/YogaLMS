package yogaLMS.dto.program;

public class CYTProgram extends Program {

    private int numYogaTherapySessions;
    private int numSeminars;

    @Override
    public String generateRequirementsList() {
        String requirements = this.getName() + " Requirements:\n " +
                "Total Hours: " + this.getNumHours() +
                "Number of Yoga Therapy Sessions: " + numYogaTherapySessions +
                "Number of Seminars: " + numSeminars;

        return requirements;
    }
}
