package model.research;

public enum ResearchStatus{
    LOCKED,   //Its prerequisite hasn't been done
    UNLOCKED, //Its prerequisite has been done. Now it is available for researching
    DONE      //It has been done
}
