package model.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TestResourceList{
    ResourceList resourceList;

    @BeforeEach
    public void setUp() {
        resourceList = new ResourceList();
    }

    @Test
    public void hasNoResourceExceptionTest() {
        Assertions.assertTrue(resourceList.hasEnough(Resource.NO_RESOURCE, 100));
    }

    @Test
    public void addTest() {
        resourceList.addResource(Resource.HORSE, 1);
        resourceList.addResource(Resource.COAL, 1);

        Assertions.assertTrue(resourceList.hasEnough(Resource.HORSE, 1));
        Assertions.assertTrue(resourceList.hasEnough(Resource.COAL, 1));
    }

    @Test
    public void removeTest() {
        resourceList.addResource(Resource.HORSE, 2);
        resourceList.removeResource(Resource.HORSE, 1);

        Assertions.assertFalse(resourceList.hasEnough(Resource.HORSE, 2));
    }

    @Test
    public void getResourceTest() {
        resourceList.addResource(Resource.COAL, 3);

        Assertions.assertEquals(3, resourceList.getResourceCount(Resource.COAL));
    }
}
