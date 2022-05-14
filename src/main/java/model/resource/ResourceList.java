package model.resource;

import java.util.HashMap;

//A class for keeping control over the resources of a civilization
public class ResourceList{
    private final HashMap<Resource, Integer> resourcesCount;

    public ResourceList() {
        this.resourcesCount = new HashMap<Resource, Integer>();
        for (Resource resource : Resource.values()) {
            this.resourcesCount.put(resource, 0);
        }
    }

    public boolean hasEnough(Resource resource, int amount) {
        //Checks if the list has a given "amount" of a model.resource
        if (resource == Resource.NO_RESOURCE)
            return true;
        return this.getResourceCount(resource) >= amount;
    }

    //SETTERS
    public void addResource(Resource resource, int amount) {
        //Adds an amount of a certain resourceType to the list

        this.resourcesCount.replace(resource, getResourceCount(resource) + amount);
    }

    public void removeResource(Resource resource, int amount) {
        //Removes an amount of a certain

        this.resourcesCount.replace(resource, getResourceCount(resource) - amount);
    }

    //GETTERS
    public int getResourceCount(Resource resource) {
        return this.resourcesCount.get(resource);
    }

}
