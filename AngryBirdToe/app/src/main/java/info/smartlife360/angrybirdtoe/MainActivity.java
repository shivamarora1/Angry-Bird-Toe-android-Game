package info.smartlife360.angrybirdtoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean active=true;
    public int player=0;
    int[] Clicked={2,2,2,2,2,2,2,2,2}; //to now which box is occupied
int[][] winnerPattern={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    /*function to execute when someone click Board */
    public void clicked(View view)
    {
        ImageButton startAgain=(ImageButton)findViewById(R.id.startAgain);
        ImageView image=(ImageView) view;
        int click=Integer.parseInt(image.getTag().toString());

        if(Clicked[click]==2&&active) {
            if (player == 0) {
                image.setImageResource(R.drawable.bird);
                image.animate().alphaBy(1f).setDuration(500);
                player = 1;
            } else {
                image.setImageResource(R.drawable.pig);
                image.animate().alphaBy(1f).setDuration(10).rotation(500f).setDuration(2000);
                player = 0;
            }
            Clicked[click]=player;
        }

        /* Matching Logic*/
        String winnerIs;
        for(int[] patternForWin:winnerPattern){
            if(Clicked[patternForWin[0]]==Clicked[patternForWin[1]]&&
                    Clicked[patternForWin[1]]==Clicked[patternForWin[2]]&&
                    Clicked[patternForWin[0]]!=2)
            {
                if(Clicked[patternForWin[0]]==0){
                    winnerIs="Green Piggy";
                    startAgain.setImageResource(R.drawable.winner1);
                    startAgain.setVisibility(View.VISIBLE);
                }
                                    else {
                    winnerIs = "Red Bird";

                    startAgain.setImageResource(R.drawable.winner2);
                    startAgain.setVisibility(View.VISIBLE);

                }
                active=false;
                System.out.println("Congrats "+winnerIs);
            }

        }

        /*Loop for Game Draw*/
active=false;
        for(int n:Clicked)
        {
            if(n==2)
            {
                active = true;
            }
        }
        if(!active)
        {
            startAgain.setImageResource(R.drawable.draw);
            startAgain.setVisibility(View.VISIBLE);

            System.out.println("Game is Drawn");
        }


    }

    public void startAgain(View view){
        player=0;
        for(int i=0;i<Clicked.length;i++)
        {
            Clicked[i]=2;
        }
        active=true;
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        int numberOfImages=gridLayout.getChildCount();

        for(int i=0;i<numberOfImages;i++){
            ImageView imgae=(ImageView)gridLayout.getChildAt(i);
            imgae.setImageResource(0);
        }

        ImageButton startAgain=(ImageButton)findViewById(R.id.startAgain);
        startAgain.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
