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

    final int foodIncrease;
    final int goldIncrease;
    final int productionIncrease;

    Resource(ResourceType resourceType, //Technology neededTech,
             int foodIncrease, int goldIncrease, int productionIncrease) {
        this.resourceType = resourceType;
        this.foodIncrease = foodIncrease;
        this.goldIncrease = goldIncrease;
        this.productionIncrease = productionIncrease;
    }

    //checks if resource can appear in given terrain
    public boolean matchesTerrain(Terrain terrain) {
        //TODO... Complete matching terrains and features
        return true;
    }
}
