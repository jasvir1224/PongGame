package com.example.parrot.pong1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // -----------------------------------
    // ## ANDROID DEBUG VARIABLES
    // -----------------------------------

    // Android debug variables
    final static String TAG="PONG-GAME";

    // -----------------------------------
    // ## SCREEN & DRAWING SETUP VARIABLES
    // -----------------------------------
    int ballXPosition;
    int ballYPosition;
    // screen size
    int screenHeight;
    int screenWidth;

    int racketXPosition;
    int racketYPosition;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;



    // -----------------------------------
    // ## GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------

    // ----------------------------
    // ## GAME STATS - number of lives, score, etc
    // ----------------------------


    public GameEngine(Context context, int w, int h) {
        super(context);


        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;
this.ballXPosition = screenWidth/2;
this.ballYPosition = screenHeight/2;

this.racketXPosition = 500;
this.racketYPosition = 1500;
        this.printScreenInfo();

        // @TODO: Add your sprites to this section
        // This is optional. Use it to:
        //  - setup or configure your sprites
        //  - set the initial position of your sprites


        // @TODO: Any other game setup stuff goes here


    }

    // ------------------------------
    // HELPER FUNCTIONS
    // ------------------------------

    // This funciton prints the screen height & width to the screen.
    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }


    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------
String directionBallIsMoving = "right";
String directionofBall = "down";
String personTapped = "";

    // 1. Tell Android the (x,y) positions of your sprites
    public void updatePositions() {
        // @TODO: Update the position of the sprites
//        Log.d(TAG,"HEllo" );
        //calculate new position
//Right and Left
//        if(directionBallIsMoving.contentEquals("right")){
//
//            this.ballXPosition = this.ballXPosition+10;
//
//            if(this.ballXPosition > this.screenWidth){
//                directionBallIsMoving = "left";
//            }
//        } else if(directionBallIsMoving.contentEquals("left")){
//            this.ballXPosition =this.ballXPosition-10;
//
//            if(this.ballXPosition <=0){
//                directionBallIsMoving = "right";
//
//            }
//        }

        //Up and down

        if (directionofBall.contentEquals("down")){
            this.ballYPosition = this.ballYPosition + 100;
            if(this.ballYPosition >= this.screenHeight){
                directionofBall = "up";
            }

        }
        else if (directionofBall.contentEquals("up")){
            this.ballYPosition = this.ballYPosition -100;
            if(ballYPosition <= 0){
                directionofBall = "down";
            }
        }


        /* if(directionofBall.contentEquals("down")){
            this.ballYPosition = this.ballYPosition +10;

            if(this.ballYPosition > this.screenHeight){
                directionofBall = "up";
            }
        }else if(directionofBall.contentEquals("up")){
            this.ballYPosition = this.ballYPosition-10;
            if (this.ballYPosition <=0){
                directionofBall= "down";
            }
        }
*/
// racket position
         // this.racketXPosition = this.racketXPosition - 10;

        if (personTapped.contentEquals("right")){
            this.racketXPosition = this.racketXPosition + 10;

        }else if (personTapped.contentEquals("left")){
            this.racketXPosition = this.racketXPosition - 10;

        }


if(ballYPosition>  racketYPosition){
    directionofBall = "up";
}





//// this.ballXPosition = this.ballXPosition + 10;
////this.ballYPosition = this.ballYPosition -10;
//        if(this.ballXPosition >= this.screenWidth){
//            this.ballXPosition = this.ballXPosition -100;
//
//        }

        //DEBUG
      //  Log.d(TAG, "XPOS" + this.ballXPosition);


        // @TODO: Collision detection code

    }

    // 2. Tell Android to DRAW the sprites at their positions
    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------
            // Put all your drawing code in this section

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,0,0,255));
            paintbrush.setColor(Color.WHITE);


            //@TODO: Draw the sprites (rectangle, circle, etc)
//draw rectangle
            this.canvas.drawRect(this.ballXPosition,
                                 this.ballYPosition,
                                 this.ballXPosition+50,
                                 this.ballYPosition+50,paintbrush);
            paintbrush.setColor(Color.YELLOW);

            this.canvas.drawRect(this.racketXPosition,this.racketYPosition, this.racketXPosition+400,this.racketYPosition+30,paintbrush);

            //@TODO: Draw game statistics (lives, score, etc)
            paintbrush.setTextSize(60);
            canvas.drawText("Score: 25", 20, 100, paintbrush);

            //----------------
            this.holder.unlockCanvasAndPost(canvas);

            //draw racket

        }
    }

    // Sets the frame rate of the game
    public void setFPS() {
        try {
            gameThread.sleep(50);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?

        if (userAction == MotionEvent.ACTION_DOWN) {
            float fingerXPosition = event.getX();
            float fingerYPosition = event.getY();
            int middle = this.screenWidth/2;

if (fingerXPosition <= middle ){
    personTapped = "left";


}else if (fingerXPosition > middle){
    personTapped = "right";
}

            Log.d(TAG ,"Pressed" + fingerXPosition + "," + fingerYPosition);
        }
        else if (userAction == MotionEvent.ACTION_UP) {
            // user lifted their finger
        }
        return true;
    }
}