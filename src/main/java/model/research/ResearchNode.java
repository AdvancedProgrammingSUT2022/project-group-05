package model.research;

import java.util.ArrayList;

import static model.research.ResearchStatus.*;

//This class represents a node in the ResearchTree
public class ResearchNode{
    private ResearchStatus status;

    private final Research research;

    private final ArrayList<ResearchNode> ancestors;
    private final ArrayList<ResearchNode> children;

    public ResearchNode(Research research) {
        setStatus(LOCKED);

        this.research = research;

        this.ancestors = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    //SETTERS
    public void addAncestor(ResearchNode researchNode) {
        if (!this.ancestors.contains(researchNode)) this.ancestors.add(researchNode);
    }

    public void addChild(ResearchNode researchNode) {
        if (!this.children.contains(researchNode)) this.children.add(researchNode);
    }

    public void setStatus(ResearchStatus status) {
        this.status = status;
    }
    //GETTERS
    public Research getResearch() {
        return this.research;
    }

    public ArrayList<ResearchNode> getAncestors() {
        return this.ancestors;
    }

    public ArrayList<ResearchNode> getChildren() {
        return this.children;
    }

    public ResearchStatus getStatus() {
        return this.status;
    }

    //DEFAULT OVERRIDES
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ResearchNode)) return false;

        ResearchNode researchNode = (ResearchNode) object;
        return this.research == researchNode.research;
    }

    @Override
    public String toString() {
        return  "name: " + this.research.toString() + "\n" +
                "status: " + this.status.toString() + "\n" +
                "prerequisites: " + this.research.getAncestors().toString() + "\n";
    }
}
