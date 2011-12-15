package cz.zcu.kiv.eegdatabase.logic.controller.list.visualimpairment;

/**
 * @author Jindra
 */
public class AddVisualImpairmentCommand {

    private int visualImpairmentId;
    private String description;
    private int researchGroupId;
    private String researchGroupTitle;

    public int getVisualImpairmentId() {
        return visualImpairmentId;
    }

    public void setVisualImpairmentId(int visualImpairmentId) {
        this.visualImpairmentId = visualImpairmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResearchGroupId() {
        return researchGroupId;
    }

    public void setResearchGroupId(int researchGroupId) {
        this.researchGroupId = researchGroupId;
    }

    public String getResearchGroupTitle() {
        return researchGroupTitle;
    }

    public void setResearchGroupTitle(String researchGroupTitle) {
        this.researchGroupTitle = researchGroupTitle;
    }
}