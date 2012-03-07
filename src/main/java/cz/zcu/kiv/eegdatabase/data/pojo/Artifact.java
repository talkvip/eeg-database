package cz.zcu.kiv.eegdatabase.data.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: stebjan
 * Date: 20.2.12
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class Artifact implements Serializable {

    private int artifactId;
    private String compensation;
    private String rejectCondition;
    private Set<Experiment> experiments = new HashSet<Experiment>(0);

    public Artifact() {
    }

    public Artifact(int artifactId, String compensation, String rejectCondition, Set<Experiment> experiments) {
        this.artifactId = artifactId;
        this.compensation = compensation;
        this.rejectCondition = rejectCondition;
        this.experiments = experiments;
    }

    public int getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(int artifactId) {
        this.artifactId = artifactId;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public String getRejectCondition() {
        return rejectCondition;
    }

    public void setRejectCondition(String rejectCondition) {
        this.rejectCondition = rejectCondition;
    }

    public Set<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(Set<Experiment> experiments) {
        this.experiments = experiments;
    }
}

