package graphics.view.gameContents;

import controller.UnitController;
import graphics.objects.buttons.ButtonTwo;
import graphics.statics.StaticFonts;
import graphics.view.popUp.ImprovementPanel;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.RoutePanel;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;
import model.improvement.Improvement;
import model.tile.Route;
import model.tile.Tile;
import model.unit.soldier.Soldier;

import java.util.ArrayList;

//TODO add functions and closing system of this menu

public class UnitMenu extends Pane{
    private static UnitMenu instance = null;
    private static int bSize = 60;

    public static UnitMenu getInstance() {
        if (instance == null) {
            instance = new UnitMenu();
        }

        return instance;
    }

    private UnitController unitController;

    private Rectangle background;

    private ButtonTwo move;
    private ButtonTwo sleep;
    private ButtonTwo alert;
    private ButtonTwo fortify;
    private ButtonTwo recover;
    private ButtonTwo garrison;
    private ButtonTwo wake;
    private ButtonTwo cancel;
    private ButtonTwo delete;

    private ButtonTwo foundCity;

    private ButtonTwo setupRanged;
    private ButtonTwo attack;
    private ButtonTwo attackCity;
    private ButtonTwo conquerCity;

    private ButtonTwo buildImprovement;
    private ButtonTwo buildRoute;
    private ButtonTwo removeFeature;
    private ButtonTwo remove;
    private ButtonTwo repair;

    private ButtonTwo exit;

    private UnitMenu () {
        this.unitController = UnitController.getInstance();

        background = new Rectangle(bSize*10, bSize*2);
        background.setFill(new Color(0, 0.5, 0.5, 0.4));
        this.getChildren().add(background);

        move = new ButtonTwo("Move", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2, bSize, bSize, this);
        sleep = new ButtonTwo("Sleep", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2, bSize, bSize, this);
        alert = new ButtonTwo("Alert", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*2, bSize/2, bSize, bSize, this);
        fortify = new ButtonTwo("Fortify", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*3, bSize/2, bSize, bSize, this);
        recover = new ButtonTwo("Recover", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*4, bSize/2, bSize, bSize, this);
        garrison = new ButtonTwo("Garrison", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*5, bSize/2, bSize, bSize, this);
        wake = new ButtonTwo("Wake", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*6, bSize/2, bSize, bSize, this);
        cancel = new ButtonTwo("Cancel", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*7, bSize/2, bSize, bSize, this);
        delete = new ButtonTwo("Delete", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*8, bSize/2, bSize, bSize, this);
        foundCity = new ButtonTwo("FoundCity", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*9, bSize/2, bSize, bSize, this);

        setupRanged = new ButtonTwo("SetRanged", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize, bSize, bSize, this);
        attack = new ButtonTwo("Attack", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize, bSize, bSize, this);
        attackCity = new ButtonTwo("AttCity", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*2, bSize/2 + bSize, bSize, bSize, this);
        conquerCity = new ButtonTwo("Conquer", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*3, bSize/2 + bSize, bSize, bSize, this);
        buildImprovement = new ButtonTwo("BuildImp", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*4, bSize/2 + bSize, bSize, bSize, this);
        buildRoute = new ButtonTwo("BuildRoute", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*5, bSize/2 + bSize, bSize, bSize, this);
        removeFeature = new ButtonTwo("RemFeature", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*6, bSize/2 + bSize, bSize, bSize, this);
        remove = new ButtonTwo("Remove", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*7, bSize/2 + bSize, bSize, bSize, this);
        repair = new ButtonTwo("Repair", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*8, bSize/2 + bSize, bSize, bSize, this);

        exit = new ButtonTwo("EXIT", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*9, bSize/2 + bSize, bSize, bSize, this);

        //Functions

        move.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitMove(MapFX.getInstance().getSecondSelectedTile().getTile().getXPlace(), MapFX.getInstance().getSecondSelectedTile().getTile().getYPlace());
            }
        });

        sleep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitSleep();
            }
        });

        alert.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitAlert();
            }
        });

        fortify.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitFortify();
            }
        });

        recover.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitRecover();
            }
        });

        garrison.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitGarrison();
            }
        });

        wake.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitWake();
            }
        });



        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitCancel();
            }
        });

        foundCity.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitFoundCity();
            }
        });

        setupRanged.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitSetupRanged();
            }
        });

        attack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitAttack(MapFX.getInstance().getSecondSelectedTile().getTile().getXPlace(), MapFX.getInstance().getSecondSelectedTile().getTile().getYPlace());
            }
        });

        conquerCity.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.conquerCity(MapFX.getInstance().getSecondSelectedTile().getTile().getCity(), (Soldier) unitController.getUnit());
            }
        });

        Pane temp = this;

        buildImprovement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ArrayList<Improvement> improvements = new ArrayList<>();
                Tile tile = unitController.getUnit().getTile();
                Civilization civilization = unitController.getUnit().getCivilization();
                for (Improvement improvement : Improvement.values()) {
                    if (civilization.getResearchTree().isResearchDone(improvement.getNeededResearch()) &&
                        improvement.matchesFeature(tile.getFeature()) && improvement.matchesTerrain(tile.getTerrain())) {
                        improvements.add(improvement);
                    }
                }
                ImprovementPanel improvementPanel = new ImprovementPanel(improvements);
                improvementPanel.camp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildImprovement(Improvement.CAMP);
                    }
                });
                improvementPanel.farm.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildImprovement(Improvement.FARM);
                    }
                });
                improvementPanel.lumber_mill.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildImprovement(Improvement.LUMBER_MILL);
                    }
                });
                improvementPanel.mine.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildImprovement(Improvement.MINE);
                    }
                });
                improvementPanel.pasture.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildImprovement(Improvement.PASTURE);
                    }
                });
                improvementPanel.plantation.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildImprovement(Improvement.PLANTATION);
                    }
                });
                improvementPanel.quarry.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildImprovement(Improvement.QUARRY);
                    }
                });
                improvementPanel.trading_post.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildImprovement(Improvement.TRADING_POST);
                    }
                });
                new PopUp(temp, improvementPanel);
            }
        });

        buildRoute.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boolean canRoad = false ;
                boolean canRail = false;
                if (unitController.getUnit().getCivilization().getResearchTree().isResearchDone(Route.ROAD.getNeededResearch()))
                    canRoad = true;
                if (unitController.getUnit().getCivilization().getResearchTree().isResearchDone(Route.RAIL.getNeededResearch()))
                    canRail = true;
                RoutePanel routePanel = new RoutePanel(canRoad, canRail);
                routePanel.road.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildRoute(Route.ROAD);
                    }
                });
                routePanel.rail.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        unitController.unitBuildRoute(Route.RAIL);
                    }
                });
                new PopUp(temp, routePanel);
            }
        });



        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (unitController.getUnit().getTile().getFeature()) {
                    case FOREST:
                        unitController.unitRemoveForest();
                    case MARSH:
                        unitController.unitRemoveMarsh();
                    case JUNGLE:
                        unitController.unitRemoveJungle();
                }
            }
        });

        repair.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitRepair();
            }
        });

        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitDelete();
            }
        });




        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setVisible(false);
                //can add animation here
            }
        });

        //TODO other functions

        this.setLayoutX(960 - 5*bSize);
        this.setLayoutY(1060 - 2*bSize);
    }
}
