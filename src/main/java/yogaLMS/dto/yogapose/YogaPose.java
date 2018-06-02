package yogaLMS.dto.yogapose;

import yogaLMS.dto.Dto;

import java.io.Serializable;
import java.util.List;

public class YogaPose extends Dto implements Serializable {

    private Long id;
    private String name;
    private List<PoseCategory> categories;
    private List<Chakra> chakras;
    private List<TargetArea> targetAreas;
    private List<String> archetypes;
    private List<String> attitudes;
    private List<String> intention;
    private List<String> posturalAlignments;
    private List<String> breathingTechniques;
    private List<String> mentalFocuses;
    private List<String> lockAndSeals;
    private List<String> psychologicalBlocks;
    private List<String> emotionalTransformations;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<PoseCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<PoseCategory> categories) {
        this.categories = categories;
    }

    public List<Chakra> getChakras() {
        return chakras;
    }

    public void setChakras(List<Chakra> chakras) {
        this.chakras = chakras;
    }



    public List<TargetArea> getTargetAreas() {
        return targetAreas;
    }

    public void setTargetAreas(List<TargetArea> targetAreas) {
        this.targetAreas = targetAreas;
    }

    public List<String> getArchetypes() {
        return archetypes;
    }

    public void setArchetypes(List<String> archetypes) {
        this.archetypes = archetypes;
    }

    public List<String> getAttitudes() {
        return attitudes;
    }

    public void setAttitudes(List<String> attitudes) {
        this.attitudes = attitudes;
    }

    public List<String> getIntention() {
        return intention;
    }

    public void setIntention(List<String> intention) {
        this.intention = intention;
    }

    public List<String> getPosturalAlignments() {
        return posturalAlignments;
    }

    public void setPosturalAlignments(List<String> posturalAlignments) {
        this.posturalAlignments = posturalAlignments;
    }

    public List<String> getBreathingTechniques() {
        return breathingTechniques;
    }

    public void setBreathingTechniques(List<String> breathingTechniques) {
        this.breathingTechniques = breathingTechniques;
    }

    public List<String> getMentalFocuses() {
        return mentalFocuses;
    }

    public void setMentalFocuses(List<String> mentalFocuses) {
        this.mentalFocuses = mentalFocuses;
    }

    public List<String> getLockAndSeals() {
        return lockAndSeals;
    }

    public void setLockAndSeals(List<String> lockAndSeals) {
        this.lockAndSeals = lockAndSeals;
    }

    public List<String> getPsychologicalBlocks() {
        return psychologicalBlocks;
    }

    public void setPsychologicalBlocks(List<String> psychologicalBlocks) {
        this.psychologicalBlocks = psychologicalBlocks;
    }

    public List<String> getEmotionalTransformations() {
        return emotionalTransformations;
    }

    public void setEmotionalTransformations(List<String> emotionalTransformations) {
        this.emotionalTransformations = emotionalTransformations;
    }
}
