        package com.example.arusyn.eightqueens;

        import android.content.Context;
        import android.content.DialogInterface;
        import android.graphics.Color;
        import android.graphics.Point;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Display;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.GridLayout;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;


public class MainActivity extends AppCompatActivity{
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showPop("App Started");
        this.game = new Game();
//        TextView tv = (TextView) findViewById(R.id.textView);
        int i = 0;
        for(int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                ImageButton button = new ImageButton(MainActivity.this);
                GridLayout grid = (GridLayout) findViewById(R.id.chessBoard);
                if(x%2 == 0){
                    if (i % 2 == 0) {
                        button.setBackgroundColor(Color.BLACK);
                    } else {
                        button.setBackgroundColor(Color.WHITE);
                    }
                } else {
                    if (i % 2 == 0) {
                        button.setBackgroundColor(Color.WHITE);
                    } else {
                        button.setBackgroundColor(Color.BLACK);
                    }
                }
                i++;

                int coordinateId = Integer.parseInt(Integer.toString(y) + Integer.toString(x));
                System.out.println("id is: " + coordinateId);
                button.setId(coordinateId);

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int screenWidth = size.x;
                int cellSize = (screenWidth-100)/8;

                button.setMaxWidth(cellSize);
                button.setMinimumWidth(cellSize);
                button.setMaxHeight(cellSize);
                button.setMinimumHeight(cellSize);

                button.setPadding(1, 1, 1, 1);
                button.setAdjustViewBounds(true);
                button.setScaleType(ImageView.ScaleType.FIT_CENTER);

                button.setTag("1");
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String clickString = "" + v.getId();
                        int clickCol;
                        int clickRow;
                        if ((clickString.length() == 1)) {
                            clickCol = 0;
                            clickRow = Character.getNumericValue(clickString.charAt(0));
                        } else {
                            clickCol = Character.getNumericValue(clickString.charAt(0));
                            clickRow = Character.getNumericValue(clickString.charAt(1));
                        }
                        if (game.playerBoard.isAllowed(clickCol, clickRow)) {
                            ImageButton b = (ImageButton) findViewById(v.getId());
                            if (v.getTag().equals("1")) {
                                game.playerBoard.move(clickCol, clickRow);
                                b.setImageResource(R.drawable.crown);
                                v.setTag(getString(R.string.int2));
                            } else {
                                game.playerBoard.board[clickCol] = null;
                                b.setImageResource(0);
                                v.setTag(getString(R.string.int1));
                            }
                        } else {
                            showPop("Move is not allowed!");
                        }
                    }
                });
                grid.addView(button);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showPop(String s){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, s, duration);
        toast.show();
    }

    public void clickDone(View v) {
        int colToSolve = 0;
//        System.out.println("you just clicked done and lastSetColumn is: "+game.playerBoard.lastSetColumn);
//        if(game.playerBoard.lastSetColumn != 0){
//            System.out.println("going to go ahead and set colToSolve to be: "+ game.playerBoard.lastSetColumn+1);
//            colToSolve = game.playerBoard.lastSetColumn + 1;
//        }
        if(game.solve(game.playerBoard, colToSolve)){
            for(int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    int buttonId = Integer.parseInt(Integer.toString(x) + Integer.toString(y));
                    ImageButton b = (ImageButton) findViewById(buttonId);
                    if (game.playerBoard.board[x] == y) {
                        b.setImageResource(R.drawable.crown);
                    } else {
                        b.setImageResource(0);
                    }
                }
            }
        }
        else {
            showPop("No Solution!");
        }
    }

    public void clickReset(View v){
        this.recreate();
        game.playerBoard = new Board();
    }
}