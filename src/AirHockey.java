
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author keyew7019
 */
public class AirHockey extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int score1 = 0;
    int score2 = 0;
    Font scoreFont = new Font("Arial", Font.BOLD, 42);
    Rectangle[] righttop = new Rectangle[1];
    Rectangle[] rightbottom = new Rectangle[1];
    Rectangle[] bottom = new Rectangle[1];
    Rectangle[] leftbottom = new Rectangle[1];
    Rectangle[] lefttop = new Rectangle[1];
    Rectangle[] top = new Rectangle[1];
    boolean p1Up = false;
    boolean p1Down = false;
    boolean p1Left = false;
    boolean p1Right = false;
    boolean p2Up = false;
    boolean p2Down = false;
    boolean p2Left = false;
    boolean p2Right = false;
    Rectangle p1 = new Rectangle(100, 250, 75, 75);
    Rectangle p2 = new Rectangle(625, 250, 75, 75);
    Rectangle puck = new Rectangle(387, 275, 45, 45);
    double puckAngle = Math.toRadians(180);
    double puckSpeed = 1;
    
    double puckx = puck.x;
    double pucky = puck.y;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 

        // change to colour the ice
        g.setColor(Color.white);

        // draw the pipes
        g.setColor(Color.red);
        g.fillRect(780, 0, 20, HEIGHT - 390);
        g.setColor(Color.red);
        g.fillRect(780, 400, 20, HEIGHT - 240);
        g.setColor(Color.red);
        g.fillRect(0, 0, 20, HEIGHT - 390);
        g.setColor(Color.red);
        g.fillRect(0, 400, 20, HEIGHT - 240);
        g.setColor(Color.red);
        g.fillRect(0, 0, 800, 20);
        g.setColor(Color.red);
        g.fillRect(0, 580, 800, 20);
        g.setColor(Color.red);
        g.fillRect(WIDTH / 2 + 5, 0, 10, HEIGHT);



        //create the different characters
        // character 1
        g.setColor(Color.blue);
        g.fillOval(p1.x, p1.y, p1.width, p1.height);
        // character 2
        g.setColor(Color.green);
        g.fillOval(p2.x, p2.y, p2.width, p2.height);

        //create the puck
        g.setColor(Color.black);
        g.fillOval(puck.x, puck.y, puck.width, puck.height);

        //create the score for player 1
        g.setColor(Color.black);
        g.setFont(scoreFont);
        g.drawString("" + score1, (WIDTH / 2) + (WIDTH / 2) / 2, 50);

        //create the score for player 2
        g.setColor(Color.black);
        g.setFont(scoreFont);
        g.drawString("" + score2, (WIDTH / 2) / 2, 50);

        // GAME DRAWING ENDS HERE
    }

    public void reset() {
        // get the score
        score1 = score1;
        score2 = score2;

        //reset the puck
        puck.x = 387;
        puck.y = 275;
        puckx = 387;
        pucky = 275;
        puck.height = 45;
        puck.width = 45;

        //reset game if p1 or p2 goals get to 7
        if (score1 == 7 || score2 == 7) {
            score1 = 0;
            score2 = 0;
            puck.x = 387;
            puck.y = 275;
            puck.height = 45;
            puck.width = 45;
            p1.x = 100;
            p1.y = 250;
            p1.width = 75;
            p1.height = 75;
            p2.x = 625;
            p2.y = 250;
            p2.width = 75;
            p2.height = 75;
        }


    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE

            //get p1 to move
            if (p1Up) {
                p1.y = p1.y - 4;
            }
            if (p1Down) {
                p1.y = p1.y + 4;
            }
            if (p1Left) {
                p1.x = p1.x - 4;
            }
            if (p1Right) {
                p1.x = p1.x + 4;
            }
            //get p2 to move
            if (p2Up) {
                p2.y = p2.y - 4;
            }
            if (p2Down) {
                p2.y = p2.y + 4;
            }
            if (p2Left) {
                p2.x = p2.x - 4;
            }
            if (p2Right) {
                p2.x = p2.x + 4;
            }
            // check if p1 hits top or bottom of screen
            if (p1.y < 20) {
                p1.y = 20;
            } else if (p1.y > 505) {
                p1.y = 505;
            }
            // check if p1 hits the sides of the screen
            if (p1.x < 20) {
                p1.x = 20;
            } else if (p1.x > 330) {
                p1.x = 330;
            }
            // check if p2 hits top or bottom of screen
            if (p2.y < 20) {
                p2.y = 20;
            } else if (p2.y > 505) {
                p2.y = 505;
            }
            // check if p2 hits the sides of the screen
            if (p2.x > 705) {
                p2.x = 705;
            } else if (p2.x < 415) {
                p2.x = 415;
            }
            //check if puck hits the side of the boards
            if (puck.x > 735 && puck.y > 390 && puck.y < 240) {
                puck.x = 735;
                puck.y = 390;
                puck.y = 240;
            }
            if (puckx > 735 && pucky > 390 && pucky < 240) {
                puckx = 735;
                pucky = 390;
                pucky = 240;
            }
            if (puck.x > 45 && puck.y > 390 && puck.y < 240) {
                puck.x = 45;
                puck.y = 390;
                puck.y = 240;
            }
            if (puckx > 45 && pucky > 390 && pucky < 240) {
                puckx = 45;
                pucky = 390;
                pucky = 240;
            }
            if (puck.y < 20) {
                puck.y = 20;
            }
            if (pucky < 20) {
                pucky = 20;
            }
            if (puck.y > 535) {
                puck.y = 535;
            }
            if (pucky > 535) {
                pucky = 535;
            }
            // check if puck goes through the net
            if (puck.x > 800 || puck.y > 390 && puck.y < 240) {
                score2 = score2 + 1;
                reset();
            } else if (puck.x < -45 || puck.y > 390 && puck.y < 240) {
                score1 = score1 + 1;
                reset();
            }

            // check if the puck was hit
            double puckDX = puckSpeed * Math.cos(puckAngle);
            double puckDY = puckSpeed * Math.sin(puckAngle);
        
            
            puckx = puckx + puckDX;
            pucky = pucky + puckDY;

            puck.x = (int) puckx;
            puck.y = (int) pucky;
            
            double dx = (p1.x + p1.width / 2) - (puck.x + puck.width / 2);
            double dy = (p1.y + p1.width / 2) - (puck.y + puck.width / 2);
            double dx2 = (p2.x + p2.width / 2) - (puck.x + puck.width / 2);
            double dy2 = (p2.y + p2.width / 2) - (puck.y + puck.width / 2);

            // check if p1 hits the puck
            if (Math.sqrt((dx * dx) + (dy * dy)) < (puck.width / 2 + p1.width / 2)) {
                double angle = Math.atan(dy / dx);
                puckAngle = angle;
                System.out.println("stop ");
                System.out.println("Angle: " + Math.toDegrees(angle));
            }
            // check if p2 hits the puck
            if (Math.sqrt((dx2 * dx2) + (dy2 * dy2)) < (puck.width / 2 + p2.width / 2)) {
                double angle2 = Math.atan(dy2 / dx2);
                puckAngle = angle2;
                System.out.println("stop plz stop ");
                System.out.println("Angle: " + Math.toDegrees(angle2));
            }


            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        AirHockey game = new AirHockey();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            p1Up = true;
        }
        if (key == KeyEvent.VK_S) {
            p1Down = true;
        }
        if (key == KeyEvent.VK_A) {
            p1Left = true;
        }
        if (key == KeyEvent.VK_D) {
            p1Right = true;
        }
        if (key == KeyEvent.VK_UP) {
            p2Up = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            p2Down = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            p2Left = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            p2Right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            p1Up = false;
        }
        if (key == KeyEvent.VK_S) {
            p1Down = false;
        }
        if (key == KeyEvent.VK_A) {
            p1Left = false;
        }
        if (key == KeyEvent.VK_D) {
            p1Right = false;
        }
        if (key == KeyEvent.VK_UP) {
            p2Up = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            p2Down = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            p2Left = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            p2Right = false;
        }
    }
}
