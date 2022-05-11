package controller;

import model.game.City;

public class CityController{
    private final City city;

    //Singleton definition
    private static CityController instance;

    private CityController(City city) {
        this.city = city;
    }

    public static CityController getInstance() {
        return instance;
    }

    public static void updateInstance(City city) {
        instance = new CityController(city);
    }
    //End of singleton definition

    //Enter methods here!!!
    //
    //
    //

    //GETTER (NOT NEEDED?)
    public City getCity() {
        return this.city;
    }
}
