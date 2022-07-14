package model.game;

import java.util.ArrayList;

public enum CityName {
    KARBALA,
    BUDAPEST,
    BALKH,
    TEHRAN,
    KARAJ,
    SAMARGHAND,
    BOKHARA,
    TEXAS,
    BEIJING,
    SHEKARABAD,
    ALIABAD,
    KHANAK,
    HAMEDAN,
    GHAZVIN,
    ZANJAN,
    FLORANCE,
    MILAN,
    BARCELONA,
    TOKYO,
    KYOTO,
    DELHI,
    WONDERLAND,
    MOTHERLAND,
    MOSCOW,
    BERLIN,
    PARIS,
    KIEV,
    ZORABAD,
    RAJABAD,
    AMSTERDAM,
    GANDOR,
    STOCKHOLM,
    ATLANTIS,
    ENDCITY,
    NETHER;

    public static ArrayList<String> getCityNames() {
        ArrayList<String> cityNames = new ArrayList<>();
        for (CityName name : CityName.values()) {
            cityNames.add(name.name());
        }
        return cityNames;
    }

}
