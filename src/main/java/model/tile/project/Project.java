package model.tile.project;

import model.improvement.Improvement;
import model.tile.Feature;
import model.tile.Route;

public class Project{
    private int delay;

    private Improvement currentImprovementProject;
    private Route currentRouteProject;

    private ProjectType currentProject;

    public Project() {
        this.delay = -1;
        this.currentProject = ProjectType.NONE;
    }

    public void startConstruction(Improvement improvement)
    {
        this.delay = improvement.getConstructionTime();
        this.currentProject = ProjectType.IMPROVEMENT_CONSTRUCTION;
        this.currentImprovementProject = improvement;
    }

    public void startDestruction()
    {
        this.delay = 1;
        this.currentProject = ProjectType.DESTRUCTION;
    }

    public void startFeatureRemoval(Feature feature) {
        if (feature == Feature.FOREST) this.delay = 3;
        else if (feature == Feature.JUNGLE) this.delay = 6;
        else if (feature == Feature.MARSH)  this.delay = 5;

        this.currentProject = ProjectType.FEATURE_REMOVAL;
    }

    public void startRepair() {
        this.delay = 3;
        this.currentProject = ProjectType.REPAIR;
    }

    //GETTERS
    public boolean hasProject() {
        return this.currentProject != ProjectType.NONE;
    }

    public ProjectType getFinishedProject() {
        if (delay != 0) return ProjectType.NONE;
        return currentProject;
    }
}
