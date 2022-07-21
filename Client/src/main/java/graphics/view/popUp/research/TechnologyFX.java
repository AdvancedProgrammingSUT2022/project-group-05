package graphics.view.popUp.research;

import controller.CivilizationController;
import controller.GameMenuController;
import graphics.objects.buttons.DisableButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.game.Civilization;
import model.research.Research;
import model.research.ResearchNode;
import model.research.ResearchStatus;

public class TechnologyFX extends Pane {
    TechnologyTreeMap technologyTreeMap;
    DisableButtonOne buttonOne;

    ResearchNode researchNode;


    public TechnologyFX (ResearchNode researchNode, int X, int Y, TechnologyTreeMap technologyTreeMap) {
        this.technologyTreeMap = technologyTreeMap;
        this.technologyTreeMap.getChildren().add(this);

        this.setLayoutX(250*X - 250);
        this.setLayoutY(200*Y - 100);

        this.researchNode = researchNode;
        this.buttonOne = new DisableButtonOne(researchNode.getResearch().toString(), StaticFonts.segoeLoad(15), Pos.CENTER,
                100, 50, 200, 40, this);

        this.setEnable();
        this.setColor();

        this.setFunction();

    }

    public boolean isEnable() {
        return buttonOne.isEnable();
    }

    private void setColor() {
        switch (researchNode.getStatus()) {
            case LOCKED:
                this.buttonOne.setColor(Color.GRAY);
                break;
            case UNLOCKED:
                this.buttonOne.setColor(Color.GREEN);
                break;
            case DONE:
                this.buttonOne.setColor(Color.GOLD);
                break;
            default:
                break;
        }
    }

    private void setEnable () {
        buttonOne.setEnable(this.researchNode.getStatus().equals(ResearchStatus.UNLOCKED));
    }

    private void setFunction() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if (!TechnologyFX.this.isEnable()) return;

                Research research = TechnologyFX.this.researchNode.getResearch();
                Civilization civilization = TechnologyFX.this.technologyTreeMap.getCivilization();

                civilization.getResearchTree().setCurrentResearch(research);
                new PopUp((Pane) TechnologyFX.this.getParent().getParent().getParent().getParent().getParent().getParent(), new Successful("started doing " + research.toString()));
            }
        });
    }

    public double getLineFromX () {
        return getLayoutX() + 100;
    }

    public double getLineFromY () {
        return getLayoutY() + 80;
    }

    public double getLineToX () {
        return getLayoutX() + 100;
    }

    public double getLineToY () {
        return getLayoutY() + 20;
    }
}
