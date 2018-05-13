package yogaLMS.dto.program;

public class TT1Program extends Program {

    private int numPoseEssays;
    private int numTTAsanaWorkshops;

    @Override
    public String generateRequirementsList() {
        String requirements = this.getName() + " Requirements:\n " +
                                "Total Hours: " + this.getNumHours() +
                                "Number of Pose Write-ups: " + numPoseEssays +
                                "Number of TT Asana Workshops: " + numTTAsanaWorkshops;

        return requirements;
    }
}
