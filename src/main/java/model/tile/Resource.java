package model.tile;

import static model.tile.ResourceType.*;

public enum Resource {
    //TODO... Add all resources
    BANANA(BONUS, 1, 0, 0),

    COTTON(LUXURIOUS, 0, 2, 0),

    COAL(STRATEGIC, 0, 0, 1),

    NO_RESOURCE(NONE, 0, 0, 0);


    final ResourceType resourceType;
    //final Technology neededTech;

    final int food;
    final int gold;
    final int production;

    Resource(ResourceType resourceType, //Technology neededTech,
             int food, int gold, int production) {
        this.resourceType = resourceType;
        this.food = food;
        this.gold = gold;
        this.production = production;
    }

    //checks if resource can appear in given terrain
    public boolean matchesTerrain(Terrain terrain) {
        //TODO... Complete matching terrains and features
        return true;
    }
}
