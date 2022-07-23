package graphics.view.popUp.overview;

import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import model.game.City;
import model.game.Civilization;

import java.util.ArrayList;

public class CityPane extends Pane{
    private City city;

    private LabelTwo name;
    private LabelTwo population;
    private LabelTwo joblessPopulation;

    private LabelTwo position;
    private LabelTwo strength;
    private LabelTwo health;

    public CityPane(City city)
    {
        this.city = city;

        this.setPrefWidth(800);
        this.setPrefHeight(150);

        this.setName();
        this.setPopulation();
        this.setJoblessPopulation();

        this.setPosition();
        this.setStrength();
        this.setHealth();
    }

    private void setName() {
        this.name = new LabelTwo(city.getName(), StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                300, 75, 200, 100, this);
    }

    private void setPopulation() {
        this.population = new LabelTwo("population: " + city.getTotalCitizenCount(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                550, 45, 100, 40, this);
    }

    private void setJoblessPopulation() {
        this.joblessPopulation = new LabelTwo("jobless: " + city.getJoblessCitizenCount(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                550, 105, 100, 40, this);
    }

    private void setPosition() {
        this.position = new LabelTwo(city.getCenter().getXPlace() + "," + city.getCenter().getYPlace(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                700, 45, 100, 40, this);
    }

    private void setStrength() {
        this.strength = new LabelTwo("strength: " + city.getDefenceStrength(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                700, 105, 100, 40, this);
    }

    private void setHealth() {
        this.health = new LabelTwo("HP:\n" + city.getHealth(), StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                75, 75, 100, 100, this);
    }

    //STATIC
    public static ArrayList<CityPane> getCityPanes(Civilization civilization) {
        ArrayList<CityPane> result = new ArrayList<>();

        for (City city : civilization.getCities()) {
            result.add(new CityPane(city));
        }

        return result;
    }
}
