package graphics.view.popUp;

import javafx.scene.layout.Pane;
import model.game.City;

public class CityInfo extends Pane {
    City city;

    CityInfo (City city) {
        this.city = city;
    }
}
