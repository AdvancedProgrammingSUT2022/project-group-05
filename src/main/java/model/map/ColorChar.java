package model.map;

import model.game.City;
import model.game.Civilization;
import model.tile.Feature;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

public class ColorChar {
    //CONSTANTS
    private static final int TOLEFT;
    private static final int TODOWN;
    private static final int WIDTH;
    private static final int HEIGHT;
    private static final int ZARIBMAP = 5;

    static {
        WIDTH = 4 * ZARIBMAP - 1;
        HEIGHT = 2 * ZARIBMAP;
        TODOWN = ZARIBMAP - 1;
        TOLEFT = WIDTH/2;
    }


    //RESET
    private static final String RESET = "\u001B[0m";
    //TEXT COLORS
    private static final String BLACK = "\u001B[30m";   //OCEAN
    private static final String RED = "\u001B[31m";     //DESERT
    private static final String GREEN = "\u001B[32m";   //GRASS
    private static final String YELLOW = "\u001B[33m";  //FIELD
    private static final String BLUE = "\u001B[34m";    //TUNDRA
    private static final String PURPLE = "\u001B[35m";  //MOUNTAIN
    private static final String CYAN = "\u001B[36m";    //SNOW
    private static final String WHITE = "\u001B[37m";   //HILL
    //BACKGROUND COLORS
    private static final String BLACK_BACKGROUND = "\u001B[40m";
    private static final String RED_BACKGROUND = "\u001B[41m";
    private static final String GREEN_BACKGROUND = "\u001B[42m";
    private static final String YELLOW_BACKGROUND = "\u001B[43m";
    private static final String BLUE_BACKGROUND = "\u001B[44m";
    private static final String PURPLE_BACKGROUND = "\u001B[45m";
    private static final String CYAN_BACKGROUND = "\u001B[46m";
    private static final String WHITE_BACKGROUND = "\u001B[47m";

    private String backgroundColor;
    private String textColor;
    private char text;

    public ColorChar () {
        this.backgroundColor = "";
        this.textColor = "";
        this.text = ' ';
    }

    //GETTERS

    //SETTERS
    private void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    private void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    private void setText(char text) {
        this.text = text;
    }


    //TO STRING
    public String toString () {
        return backgroundColor+textColor+text+RESET;
    }



    //CREATING PRINT PATTERN BY MAP
    public static ColorChar[][] mapConsoleOutputCreator (Civilization civilization) {
        int mapSize = Map.getInstance().getSizeOfMap();
        int printH = HEIGHT * mapSize;
        int printW = WIDTH * mapSize + (WIDTH - HEIGHT + 2) * (mapSize - 1);

        ColorChar[][] output = new ColorChar[printH][printW];
        for (int i = 0; i < printH; i++) {
            for (int j = 0; j < printW; j++) {
                output[i][j] = new ColorChar();
            }
        }

        coloringTiles(Map.getInstance(), output, civilization);
        return output;
    }

    //Coloring for each tile
    private static void coloringTiles(Map gameMap, ColorChar[][] input, Civilization civilization) {
        int mapSize = gameMap.getSizeOfMap();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Tile tempTile = gameMap.getTileFromMap(i, j);
                FogOfWarStates tempState = civilization.getFogOfWar()[i][j];

                if (tempState != FogOfWarStates.FOG_OF_WAR)
                coloringTileBackground(tempTile, input);

                addingTexts(tempTile, input, tempState);
            }
        }
    }

    //COLORING TILES BACKGROUNDS
    private static void coloringTileBackground (Tile tempTile, ColorChar[][] input) {
        int fromL = ColorChar.getFromLeft(tempTile);
        int fromT = ColorChar.getFromTop(tempTile);

        for (int i = fromT; i > fromT - ColorChar.HEIGHT/2; i--) {
            int level = fromT - i;
            for (int j = fromL - WIDTH/2 + level; j <= fromL + WIDTH/2 - level; j++) {
                input[i][j].setBackgroundColor(getColorForTile(tempTile));
            }
        }

        for (int i = fromT + 1; i < fromT + 1 + ColorChar.HEIGHT/2; i++) {
            int level = i - (fromT + 1);
            for (int j = fromL - WIDTH/2 + level; j <= fromL + WIDTH/2 - level; j++) {
                input[i][j].setBackgroundColor(getColorForTile(tempTile));
            }
        }

        addRiver(tempTile, input);
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    private static String getColorForTile (Tile tempTile) {
        Terrain temp = tempTile.getTerrain();
        if (temp.equals(Terrain.OCEAN)) return BLACK_BACKGROUND;
        if (temp.equals(Terrain.DESERT)) return RED_BACKGROUND;;
        if (temp.equals(Terrain.GRASS)) return GREEN_BACKGROUND;
        if (temp.equals(Terrain.FIELD)) return YELLOW_BACKGROUND;
        if (temp.equals(Terrain.TUNDRA)) return BLUE_BACKGROUND;
        if (temp.equals(Terrain.MOUNTAIN)) return PURPLE_BACKGROUND;
        if (temp.equals(Terrain.SNOW)) return CYAN_BACKGROUND;
        if (temp.equals(Terrain.HILL)) return WHITE_BACKGROUND;
        return BLACK_BACKGROUND;
    }

    //ADDING TEXT
    private static void addingTexts (Tile tempTile, ColorChar[][] input, FogOfWarStates state) {
        if (state == FogOfWarStates.FOG_OF_WAR) return;
        boolean isVisible = (state == FogOfWarStates.VISIBLE);

        Civilization civilization = tempTile.getCivilization();
        Civilian civilian = tempTile.getCivilian();
        Soldier soldier = tempTile.getSoldier();
        City city = tempTile.getCity();
        String cityName = "";
        if (city != null && city.getCenter().equals(tempTile)) cityName = city.getName();


        int fromL = ColorChar.getFromLeft(tempTile);
        int fromT = ColorChar.getFromTop(tempTile);

        addCenteredText("" + tempTile.getXPlace() ,input ,fromT - 3, fromL - 2); //xpos
        addCenteredText("" + tempTile.getYPlace() ,input ,fromT - 3, fromL + 2); //ypos

        addCenteredText(cityName, input, fromT - 2, fromL);
        if (civilization != null && isVisible)
            addCenteredText(tempTile.getCity().getCivilization().getPlayer().getNickname() ,input ,fromT - 1, fromL); //Civilization
        if (soldier != null && isVisible) {
            addCenteredText(tempTile.getSoldier().toString(), input, fromT + 1, fromL - 5); //Soldier
            addCenteredText(tempTile.getSoldier().getCivilization().getPlayer().getNickname(), input, fromT, fromL - 5);
        }
        if (civilian != null && isVisible) {
            addCenteredText(tempTile.getCivilian().toString(), input, fromT + 1, fromL + 5); //Civ
            addCenteredText(tempTile.getCivilian().getCivilization().getPlayer().getNickname(), input, fromT, fromL + 5);
        }

        addCenteredText(tempTile.getTerrain().toString() ,input ,fromT + 2, fromL); //Terrain
        addCenteredText(tempTile.getFeature().toString() ,input ,fromT + 3, fromL); //Feature
        if (isVisible) addCenteredText(tempTile.getResource().toString() ,input ,fromT + 4, fromL); //Resource
    }

    private static void addCenteredText (String text, ColorChar[][] input, int fromT, int fromL) {
        int size = text.length();

        for (int i = 0; i < size; i++) {
            input[fromT][fromL - size/2 + i].setText(text.charAt(i));
        }
    }

    private static void addRiver (Tile tempTile, ColorChar[][] input) {
        int fromL = ColorChar.getFromLeft(tempTile);
        int fromT = ColorChar.getFromTop(tempTile);

        if (tempTile.hasRiver(0)) {
            for (int i = -5; i < 6; i++) {
                input[fromT - 4][i + fromL].setBackgroundColor(BLACK_BACKGROUND);
            }
        }
        if (tempTile.hasRiver(3)) {
            for (int i = -5; i < 6; i++) {
                input[fromT + 5][i + fromL].setBackgroundColor(BLACK_BACKGROUND);
            }
        }

        if (tempTile.hasRiver(1)) {
            for (int i = 0; i < 5; i++) {
                input[fromT - 4 + i][fromL + 5 + i].setBackgroundColor(BLACK_BACKGROUND);
            }
        }
        if (tempTile.hasRiver(2)) {
            for (int i = 0; i < 5; i++) {
                input[fromT + 5 - i][fromL + 5 + i].setBackgroundColor(BLACK_BACKGROUND);
            }
        }
        if (tempTile.hasRiver(4)) {
            for (int i = 0; i < 5; i++) {
                input[fromT + 5 - i][fromL - 5 - i].setBackgroundColor(BLACK_BACKGROUND);
            }
        }
        if (tempTile.hasRiver(5)) {
            for (int i = 0; i < 5; i++) {
                input[fromT - 4 + i][fromL - 5 - i].setBackgroundColor(BLACK_BACKGROUND);
            }
        }
    }


    //TILES PLACE
    private static int getFromTop(Tile tile) {
        return tile.getFromTop() * ColorChar.ZARIBMAP + ColorChar.TODOWN;
    }
    private static int getFromLeft(Tile tile) {
        return tile.getFromLeft() * ColorChar.ZARIBMAP + ColorChar.TOLEFT;
    }
}
