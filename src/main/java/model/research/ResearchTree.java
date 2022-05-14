package model.research;

import static model.research.ResearchStatus.*;

public class ResearchTree{
    private final ResearchNode root;
    private Research currentResearch;
    private int researchPoints;

    public ResearchTree() {
        this.root = new ResearchNode(Research.AGRICULTURE);
        this.root.setStatus(UNLOCKED);

        for (Research research : Research.values()) {
            addResearch(research);
        }

        this.doResearch(Research.AGRICULTURE);
        this.updateTree();

        this.currentResearch = Research.NO_RESEARCH;
        this.researchPoints = 0;
    }

    //Research time handling
    public void startResearch(Research research) {
        this.currentResearch = research;
    }

    public boolean hasResearch() {
        return this.currentResearch != Research.NO_RESEARCH;
    }

    public void continueResearch(int researchPointsIncrease) {
        this.researchPoints += researchPointsIncrease;
        if (this.researchPoints < 0) this.researchPoints = 0;

        if (!this.hasResearch()) return;
        if (this.researchPoints >= this.currentResearch.getCost()) {
            this.researchPoints -= this.currentResearch.getCost();

            this.doResearch(currentResearch);
            setCurrentResearch(Research.NO_RESEARCH);
        }
    }

    public int getResearchProgressPercentage() {
        if (!this.hasResearch()) return 0;
        if (this.researchPoints > this.currentResearch.getCost()) return 1;

        return this.researchPoints * 100 / this.currentResearch.getCost();
    }
    //GETTER
    public void setCurrentResearch(Research research) {
        this.currentResearch = research;
    }

    //SETTER
    public Research getCurrentResearch() {
        return this.currentResearch;
    }

    //Research tree changes handling
    public void doResearch(Research research) {
        ResearchNode node = getResearch(research);
        node.setStatus(DONE);

        updateTree();
    }

    private void updateTree() {
        mainLoop:
        for (Research research : Research.values()) {
            if (research == Research.NO_RESEARCH) continue;

            ResearchNode node = getResearch(research);

            if (node.getStatus() != LOCKED) continue;

            for (ResearchNode ancestor : node.getAncestors()) {
                if (ancestor.getStatus() != DONE) continue mainLoop;
            }

            node.setStatus(UNLOCKED);
        }
    }

    public Boolean isResearchDone(Research research) {
        return research == Research.NO_RESEARCH ||getResearchStatus(research) == DONE;
    }

    public Boolean isResearchLocked(Research research) {
        return getResearchStatus(research) == LOCKED;
    }

    public Boolean isResearchUnlocked(Research research) {
        return getResearchStatus(research) == UNLOCKED;
    }

    private ResearchStatus getResearchStatus(Research research) {
        return getResearch(research).getStatus();
    }

    //GETTER
    private ResearchNode getResearch(Research research) {
        return getResearchFromHead(research, root);
    }

    private ResearchNode getResearchFromHead(Research research, ResearchNode head) {
        if (head == null || head.getResearch() == research) return head;

        ResearchNode result = null;
        for (ResearchNode node : head.getChildren()) {
            result = getResearchFromHead(research, node);

            if (result != null) return result;
        }

        return null;
    }

    //SETTER
    public void addResearch(Research research) {
        ResearchNode newNode = new ResearchNode(research);

        for (Research ancestorResearch : research.getAncestors()) {
            ResearchNode ancestorNode = getResearch(ancestorResearch);

            ancestorNode.addChild(newNode);
            newNode.addAncestor(ancestorNode);
        }
    }

    //PRINT
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("RESEARCH INFO:").append("\n");
        result.append("CURRENT RESEARCH: ").append(this.getCurrentResearch().toString()).append("\n");
        result.append("CURRENT PROGRESS: ").append(this.getResearchProgressPercentage()).append("%\n\n");

        for (Research research : Research.values()) {
            if (research == Research.NO_RESEARCH) continue;
            result.append(getResearch(research).toString());
            result.append("\n");
        }

        return result.toString();
    }
}
