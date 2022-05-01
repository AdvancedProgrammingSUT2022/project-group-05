package model.resource;

import java.util.HashMap;

//A class for keeping control over the resources of a civilization
public class ResourceList{
    private final HashMap<ResourceType, Integer> resourcesCount;

    public ResourceList() {
        this.resourcesCount = new HashMap<ResourceType, Integer>();
        for (ResourceType resourceType : ResourceType.values()) {
            this.resourcesCount.put(resourceType, 0);
        }
    }

    public boolean hasEnough(ResourceType resourceType, int amount) {
        //Checks if the list has a given "amount" of a model.resource

        return this.getResourceCount(resourceType) >= amount;
    }

    //SETTERS
    public void addResource(ResourceType resourceType, int amount) {
        //Adds an amount of a certain resourceType to the list

        this.resourcesCount.replace(resourceType, getResourceCount(resourceType) + amount);
    }

    public void removeResource(ResourceType resourceType, int amount) {
        //Removes an ammount of a certain

        this.resourcesCount.replace(resourceType, getResourceCount(resourceType) - amount);
    }

    //GETTERS
    public int getResourceCount(ResourceType resourceType) {
        return this.resourcesCount.get(resourceType);
    }

}
