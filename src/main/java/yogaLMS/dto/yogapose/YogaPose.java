package yogaLMS.dto.yogapose;

import yogaLMS.dto.Dto;

import java.util.List;

public class YogaPose extends Dto {

    private Long id;
    private String name;
    private DifficultyLevel level;
    private List<PoseCategory> categories;
    private List<String> variations;
    private List<Chakra> chakras;
    private List<String> cautions;
    private List<TargetArea> targetArea;
    private List<String> archetype;
    private List<String> attitude;
    private List<String> intention;
    private List<String> posturalAlignment;
    private List<String> breathing;
    private List<String> mentalFocus;
    private List<String> lockAndSeals;
    private List<String> psychologicalBlock;
    private List<String> emotionalTransformation;

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

    public DifficultyLevel getLevel() {
        return level;
    }

    public void setLevel(DifficultyLevel level) {
        this.level = level;
    }

    public List<PoseCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<PoseCategory> categories) {
        this.categories = categories;
    }

    public List<String> getVariations() {
        return variations;
    }

    public void setVariations(List<String> variations) {
        this.variations = variations;
    }

    public List<Chakra> getChakras() {
        return chakras;
    }

    public void setChakras(List<Chakra> chakras) {
        this.chakras = chakras;
    }

    public List<String> getCautions() {
        return cautions;
    }

    public void setCautions(List<String> cautions) {
        this.cautions = cautions;
    }

    public List<TargetArea> getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(List<TargetArea> targetArea) {
        this.targetArea = targetArea;
    }

    public List<String> getArchetype() {
        return archetype;
    }

    public void setArchetype(List<String> archetype) {
        this.archetype = archetype;
    }

    public List<String> getAttitude() {
        return attitude;
    }

    public void setAttitude(List<String> attitude) {
        this.attitude = attitude;
    }

    public List<String> getIntention() {
        return intention;
    }

    public void setIntention(List<String> intention) {
        this.intention = intention;
    }

    public List<String> getPosturalAlignment() {
        return posturalAlignment;
    }

    public void setPosturalAlignment(List<String> posturalAlignment) {
        this.posturalAlignment = posturalAlignment;
    }

    public List<String> getBreathing() {
        return breathing;
    }

    public void setBreathing(List<String> breathing) {
        this.breathing = breathing;
    }

    public List<String> getMentalFocus() {
        return mentalFocus;
    }

    public void setMentalFocus(List<String> mentalFocus) {
        this.mentalFocus = mentalFocus;
    }

    public List<String> getLockAndSeals() {
        return lockAndSeals;
    }

    public void setLockAndSeals(List<String> lockAndSeals) {
        this.lockAndSeals = lockAndSeals;
    }

    public List<String> getPsychologicalBlock() {
        return psychologicalBlock;
    }

    public void setPsychologicalBlock(List<String> psychologicalBlock) {
        this.psychologicalBlock = psychologicalBlock;
    }

    public List<String> getEmotionalTransformation() {
        return emotionalTransformation;
    }

    public void setEmotionalTransformation(List<String> emotionalTransformation) {
        this.emotionalTransformation = emotionalTransformation;
    }
}
