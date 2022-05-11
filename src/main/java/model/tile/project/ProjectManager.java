package model.tile.project;

import model.improvement.Improvement;
import model.tile.Feature;
import model.tile.Route;

public class ProjectManager{
    private int delay;

    private Improvement improvementProject;
    private Route routeProject;

    private ProjectType projectType;

    public ProjectManager() {
        this.delay = -1;
        this.projectType = ProjectType.NONE;
    }

    public void startImprovementConstruction(Improvement improvement) {
        this.delay = improvement.getConstructionTime();
        this.projectType = ProjectType.IMPROVEMENT_CONSTRUCTION;
        this.improvementProject = improvement;
    }

    public void startRouteConstruction(Route route) {
        this.delay = 3;
        this.projectType = ProjectType.ROUTE_CONSTRUCTION;
        this.routeProject = route;
    }

    public void startImprovementRemoval() {
        this.delay = 1;
        this.projectType = ProjectType.IMPROVEMENT_REMOVAL;
    }

    public void startRouteRemoval() {
        this.delay = 1;
        this.projectType = ProjectType.ROUTE_REMOVAL;
    }

    public void startFeatureRemoval(Feature feature) {
        if (feature == Feature.FOREST) this.delay = 3;
        else if (feature == Feature.JUNGLE) this.delay = 6;
        else if (feature == Feature.MARSH)  this.delay = 5;

        this.projectType = ProjectType.FEATURE_REMOVAL;
    }

    public void startRepair() {
        this.delay = 3;
        this.projectType = ProjectType.REPAIR;
    }

    public void continueProject() {
        if (this.hasProject()) this.delay -= 1;
    }

    public void closeProject() {
        this.delay = -1;
        this.projectType = ProjectType.NONE;

        this.routeProject = null;
        this.improvementProject = null;
    }

    //GETTERS
    public boolean hasProject() {
        return this.projectType != ProjectType.NONE;
    }

    public boolean hasFinishedProject() {
        return this.delay == 0 && this.projectType != ProjectType.NONE;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public Improvement getImprovementProject() {
        return this.improvementProject;
    }

    public Route getRouteProject() {
        return this.routeProject;
    }
}
