package model.research;

import static model.research.ResearchStatus.*;

public class ResearchTree{
    private ResearchNode root;
    private Research currentResearch;

    public ResearchTree() {
        this.root = new ResearchNode(Research.AGRICULTURE);
        this.root.setStatus(UNLOCKED);

        for (Research research : Research.values()) {
            addResearch(research);
        }

        this.currentResearch = Research.NO_RESEARCH;
    }

    //Research time handling
    public void startResearch(Research research) {
        this.currentResearch = research;
    }

    public boolean hasResearch() {
        return this.currentResearch != Research.NO_RESEARCH;
    }

    public boolean continueResearch() {
        return true; //TODO
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
            ResearchNode node = getResearch(research);

            if (node.getStatus() != LOCKED) continue;

            for (ResearchNode ancestor : node.getAncestors()) {
                if (ancestor.getStatus() != DONE) continue mainLoop;
            }

            node.setStatus(UNLOCKED);
        }
    }

    public Boolean isResearchDone(Research research) {
        return getResearchStatus(research) == DONE;
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
        StringBuilder result = new StringBuilder("RESEARCH INFO:\n\n");

        for (Research research : Research.values()) {
            result.append(getResearch(research).toString());
            result.append("\n");
        }

        return result.toString();
    }
}
