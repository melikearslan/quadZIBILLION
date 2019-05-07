package com.quadzillion.core;

import com.quadzillion.core.levels.Level;
import com.quadzillion.core.levels.PuzzleLevel;
import com.quadzillion.core.models.MainBoard;
import com.quadzillion.core.models.PuzzleMainBoard;
import com.quadzillion.core.models.TileMatrix;
import com.quadzillion.core.move.MoveChecker;
import com.quadzillion.core.pieces.Piece;
import com.quadzillion.core.pieces.PuzzlePiece;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PuzzleGamePane extends Pane {

    private MainBoard mainBoard;
    private TileMatrix tileMatrix;
    private MoveChecker moveChecker;
    public static int moveCounter = 0;
    public static Label counter;
    private int seconds;
    private int minutes;
    private AnimationTimer timer;

    public PuzzleGamePane(PuzzleLevel level)
    {

        PuzzleMainBoard mainBoard = new PuzzleMainBoard(level.getLocs(),4, level.getForbidden());
        TileMatrix tileMatrix = new TileMatrix(mainBoard);
        MoveChecker moveChecker = new MoveChecker(tileMatrix);

        int[][] solution = level.solution;


        mainBoard.putImageToPoints(level.forbiddenUrls());


        PuzzlePiece pc3 = new PuzzlePiece(moveChecker,solution,3, level.getFileName());
        PuzzlePiece pc4 = new PuzzlePiece(moveChecker,solution,4, level.getFileName());
        PuzzlePiece pc5 = new PuzzlePiece(moveChecker,solution,5, level.getFileName());
        PuzzlePiece pc6 = new PuzzlePiece(moveChecker,solution,6, level.getFileName());
        PuzzlePiece pc7 = new PuzzlePiece(moveChecker,solution,7, level.getFileName());
        PuzzlePiece pc8 = new PuzzlePiece(moveChecker,solution,8, level.getFileName());
        PuzzlePiece pc9 = new PuzzlePiece(moveChecker,solution,9, level.getFileName());
        PuzzlePiece pc10 = new PuzzlePiece(moveChecker,solution,10, level.getFileName());
        PuzzlePiece pc11 = new PuzzlePiece(moveChecker,solution,11, level.getFileName());
        PuzzlePiece pc12 = new PuzzlePiece(moveChecker,solution,12, level.getFileName());
        PuzzlePiece pc13 = new PuzzlePiece(moveChecker,solution,13, level.getFileName());
        PuzzlePiece pc14 = new PuzzlePiece(moveChecker,solution,14, level.getFileName());


        getChildren().add(mainBoard);
        getChildren().add(pc3);
        getChildren().add(pc4);
        getChildren().add(pc5);
        getChildren().add(pc6);
        getChildren().add(pc7);
        getChildren().add(pc8);
        getChildren().add(pc9);
        getChildren().add(pc10);
        getChildren().add(pc11);
        getChildren().add(pc12);
        getChildren().add(pc13);
        getChildren().add(pc14);

        for ( Node piece : getChildren())
        {
            if( piece instanceof PuzzlePiece) {
                for (int i = 0; i < (int) ((Math.random()) * 4); i++) {
                    ((PuzzlePiece) piece).turn();
                }

                if (Math.random() < 0.5)
                    ((PuzzlePiece) piece).flip();
            }

        }

        counter = new Label();
        counter.setText("Move Count: " + moveCounter );
        counter.setLayoutX(500);
        counter.setLayoutY(50);

        getChildren().add(counter);

        //Timer
        Label lblTime = new Label("Time: 0m 0s");
        seconds = 0;
        minutes = 0;
        timer = new AnimationTimer() {

            long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime != 0) {
                    if (now > lastTime + 1_000_000_000) {
                        if(seconds < 59){
                            seconds++;
                        }
                        else{
                            seconds = 0;
                            minutes++;
                        }
                        lblTime.setText("Time: " + minutes + "m " + seconds + "s" );
                        lastTime = now;
                    }
                } else {
                    lastTime = now;
                }
            }

        };
        timer.start();
        lblTime.setLayoutX(700);
        lblTime.setLayoutY(50);
        Font font = new Font("Times New Roman",25);
        lblTime.setFont(font);
        counter.setFont(font);
        lblTime.setStyle("-fx-font-weight: bold; -fx-text-fill: #853c73;");
        counter.setStyle("-fx-font-weight: bold; -fx-text-fill: #853c73 ;");

        Label label = new Label();

        getChildren().add(lblTime);

//        setOnMouseClicked(e->{
//            if(e.getSource() instanceof PuzzleGamePane){
//                System.out.println("HIIIIIIIIIIIIIIIII");
//            }
//        });

//        getScene().getRoot().get focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//            focusState(newValue);
//        });

        setOnMouseClicked(e->{
            if( getScene().focusOwnerProperty().get() instanceof Piece){
                System.out.println("I AM A PIECEEEEEEEEEEEEEEEEEE");
            }
        });

        //requestFocus();

//        focusedProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue) {
//                System.out.println("Node 1: Mine!");
//            }
//
//        });
        /*setOnMouseClicked( e ->
        {

            for ( Node node : getChildren())
            {

                ((Piece) e.getSource()).selected = true;
                /*if( node instanceof Piece)
                {
                    if( e.getSource().equals(node))
                    {

                        System.out.println("IF");

                    }
                    else
                    {
                        ((Piece) node).selected = false;
                        System.out.println("ELSE");

                    }
                }
            }

            for ( Node node : getChildren())
            {
                if( node instanceof Piece)
                {
                    System.out.println("Selected:" + ((Piece) node).selected);
                }

            }
        }); */

    }
//    private void focusState(boolean value) {
//        if (value) {
//            System.out.println("Focus Gained");
//        }
//        else {
//            System.out.println("Focus Lost");
//        }
//    }

}
