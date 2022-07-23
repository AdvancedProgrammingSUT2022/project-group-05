package graphics.view.gameContents;

import client.ClientManager;
import controller.UnitController;
import graphics.objects.buttons.ButtonTwo;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.popUp.*;
import graphics.view.popUp.Error;
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

    private LabelOne name;

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

    public void setName(String name) {
        this.name.setText(name);
    }

    private UnitMenu () {

        UnitMenu.this.update();
        int fromTop = (int) ClientManager.getInstance().getMainStage().getHeight() / 2;

        this.setVisible(false);

        background = new Rectangle(bSize*2, bSize*10);
        background.setFill(new Color(0, 0.5, 0.5, 0.4));
        this.getChildren().add(background);

        name = new LabelOne("nothing", StaticFonts.segoeLoad(15), Pos.CENTER,
                bSize/2, -bSize/2, bSize, bSize, this);
        move = new ButtonTwo("Move", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2, bSize, bSize, this);
        sleep = new ButtonTwo("Sleep", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize, bSize, bSize, this);
        alert = new ButtonTwo("Alert", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize*2, bSize, bSize, this);
        fortify = new ButtonTwo("Fortify", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize*3, bSize, bSize, this);
        recover = new ButtonTwo("Recover", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize*4, bSize, bSize, this);
        garrison = new ButtonTwo("Garrison", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize*5, bSize, bSize, this);
        wake = new ButtonTwo("Wake", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize*6, bSize, bSize, this);
        cancel = new ButtonTwo("Cancel", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize*7, bSize, bSize, this);
        delete = new ButtonTwo("Delete", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize*8, bSize, bSize, this);
        foundCity = new ButtonTwo("FoundCity", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize*9, bSize, bSize, this);

        setupRanged = new ButtonTwo("SetRanged", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2, bSize, bSize, this);
        attack = new ButtonTwo("Attack", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize, bSize, bSize, this);
        attackCity = new ButtonTwo("AttCity", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize*2, bSize, bSize, this);
        conquerCity = new ButtonTwo("Conquer", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize*3, bSize, bSize, this);
        buildImprovement = new ButtonTwo("BuildImp", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize*4, bSize, bSize, this);
        buildRoute = new ButtonTwo("BuildRoute", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize*5, bSize, bSize, this);
        removeFeature = new ButtonTwo("RemFeature", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize*6, bSize, bSize, this);
        remove = new ButtonTwo("Remove", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize*7, bSize, bSize, this);
        repair = new ButtonTwo("Repair", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize*8, bSize, bSize, this);

        exit = new ButtonTwo("EXIT", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize*9, bSize, bSize, this);

        //Functions

        move.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                if (MapFX.getInstance().getSecondSelectedTile() == null) {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Error("error: no target selected"));
                    return;
                }
                String response = unitController.unitMove(MapFX.getInstance().getSecondSelectedTile().getTile().getXPlace(), MapFX.getInstance().getSecondSelectedTile().getTile().getYPlace());
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        sleep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitSleep();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        alert.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitAlert();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        fortify.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitFortify();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        recover.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitRecover();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        garrison.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitGarrison();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        wake.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitWake();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitCancel();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        foundCity.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitFoundCity();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        setupRanged.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitSetupRanged();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        attack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                if (MapFX.getInstance().getSecondSelectedTile() == null) {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Error("error: no target selected"));
                    return;
                }
                String response = unitController.unitAttack(MapFX.getInstance().getSecondSelectedTile().getTile().getXPlace(), MapFX.getInstance().getSecondSelectedTile().getTile().getYPlace());
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        attackCity.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                if (MapFX.getInstance().getSecondSelectedTile() == null) {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Error("error: no target selected"));
                    return;
                }
                String response = unitController.unitAttack(MapFX.getInstance().getSecondSelectedTile().getTile().getXPlace(), MapFX.getInstance().getSecondSelectedTile().getTile().getYPlace());
                if (response.startsWith("error")) {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        conquerCity.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                if (MapFX.getInstance().getSecondSelectedTile() == null) {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Error("error: no target selected"));
                    return;
                }
                String response = unitController.conquerCity(MapFX.getInstance().getSecondSelectedTile().getTile().getCity(), (Soldier) unitController.getUnit());
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        Pane temp = this;

        buildImprovement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
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
                        String response = unitController.unitBuildImprovement(Improvement.CAMP);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }

                    }
                });
                improvementPanel.farm.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildImprovement(Improvement.FARM);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                improvementPanel.lumberMill.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildImprovement(Improvement.LUMBER_MILL);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                improvementPanel.mine.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildImprovement(Improvement.MINE);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                improvementPanel.pasture.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildImprovement(Improvement.PASTURE);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                improvementPanel.plantation.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildImprovement(Improvement.PLANTATION);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                improvementPanel.quarry.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildImprovement(Improvement.QUARRY);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                improvementPanel.tradingPost.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildImprovement(Improvement.TRADING_POST);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                new PopUp(temp, improvementPanel);
            }
        });

        buildRoute.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                RoutePanel routePanel = new RoutePanel();
                routePanel.road.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildRoute(Route.ROAD);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                routePanel.rail.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String response = unitController.unitBuildRoute(Route.RAIL);
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                    }
                });
                new PopUp(temp, routePanel);
            }
        });



        removeFeature.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response;
                switch (unitController.getUnit().getTile().getFeature()) {
                    case FOREST:
                        response = unitController.unitRemoveForest();
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                        break;
                    case MARSH:
                        response = unitController.unitRemoveMarsh();
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                        break;
                    case JUNGLE:
                        response = unitController.unitRemoveJungle();
                        if (response.startsWith("error")) {
                            new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                        } else {
                            new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                        }
                        break;
                    default:
                        new PopUp((Pane) UnitMenu.this.getParent(), new Error("error: no removable feature found"));
                        break;
                }
            }
        });

        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PopUp((Pane) UnitMenu.this.getParent(), new Successful("removed"));
            }
        });

        repair.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitRepair();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });

        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.this.update();
                String response = unitController.unitDelete();
                if (response.startsWith("error")) {
                    new PopUp((Pane)UnitMenu.this.getParent(), new Error(response));
                } else {
                    new PopUp((Pane) UnitMenu.this.getParent(), new Successful(response));
                }
            }
        });


        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UnitMenu.getInstance().setVisible(false);
                System.out.println(UnitMenu.getInstance().isVisible());
            }
        });

        //TODO other functions

        this.setLayoutX(2*bSize);
        this.setLayoutY(fromTop - 5*bSize);
    }
    
    public void update() {
        unitController = UnitController.getInstance();
    }
}
