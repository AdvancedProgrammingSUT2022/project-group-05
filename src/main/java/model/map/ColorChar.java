package model.map;

import model.tile.Tile;

public class ColorChar {
    //CONSTANTS
    private static final int TOLEFT = 20;
    private static final int TODOWN = 20;
    private static final int WIDTH;
    private static final int HEIGHT;
    private static final int ZARIBMAP = 5;

    static {
        WIDTH = 4 * ZARIBMAP - 1;
        HEIGHT = 2 * ZARIBMAP;
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
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public void setText(char text) {
        this.text = text;
    }


    //TO STRING
    public String toString () {
        return backgroundColor+textColor+text+RESET;
    }



    //CREATING PRINT PATTERN BY MAP
    public static ColorChar[][] mapConsoleOutputCreator (Map gameMap) {
        int mapSize = gameMap.getSizeOfMap();
        int printH = ColorChar.ZARIBMAP * (mapSize-1) + 2 * ColorChar.TODOWN;
        int printW = ColorChar.ZARIBMAP * 3 * (mapSize-1) + 2 * ColorChar.TOLEFT;
        ColorChar[][] output = new ColorChar[printH][printW];
        coloringTiles(gameMap, output);
        return output;
    }

    //Coloring for each tile
    private static void coloringTiles(Map gameMap, ColorChar[][] input) {
        int mapSize = gameMap.getSizeOfMap();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Tile tempTile = gameMap.getTileFromMap(i, j);
                coloringTileBackground(tempTile, input);
                addingTexts(tempTile, input);
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
    }
    private static String getColorForTile (Tile tempTile) {
        //TODO .. get tile feature String and change it to colors above
        return "HELLO";
    }

    //ADDING TEXT
    private static void addingTexts (Tile tempTile, ColorChar[][] input) {
        int fromL = ColorChar.getFromLeft(tempTile);
        int fromT = ColorChar.getFromTop(tempTile);
        addCenteredText("" + tempTile.getXPlace() ,input ,fromT - 3, fromL - 2); //xpos
        addCenteredText("" + tempTile.getYPlace() ,input ,fromT - 3, fromL + 2); //ypos
        addCenteredText(tempTile.getCity().getCivilization().getPlayer().getNickname() ,input ,fromT - 1, fromL); //Civilization
        //TODO can change later
        addCenteredText(tempTile.getSoldier().toString() ,input ,fromT + 1, fromL - 5); //Soldier
        addCenteredText(tempTile.getSoldier().getCivilization().getPlayer().getNickname(),input ,fromT - 1, fromL - 5);
        addCenteredText(tempTile.getCivilian().toString() ,input ,fromT + 1, fromL + 5); //Civ
        addCenteredText(tempTile.getCivilian().getCivilization().getPlayer().getNickname(),input ,fromT - 1, fromL + 5);
        addCenteredText(tempTile.getFeature().toString() ,input ,fromT + 3, fromL); //Feature
        addCenteredText(tempTile.getResource().toString() ,input ,fromT + 4, fromL); //Resource
    }
    private static void addCenteredText (String text, ColorChar[][] input, int fromT, int fromL) {
        int size = text.length();

        for (int i = 0; i < size; i++) {
            input[fromT][fromL - size/2 + i].setText(text.charAt(i));
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
