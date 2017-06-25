import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class PirateShellGame extends JPanel implements ActionListener{
/**
    *
    */

    private static final long serialVersionUID = 1L;
    final boolean shouldWeightX=true;
    final static boolean RIGHT_TO_LEFT =false;
    
    //Variables
    static boolean Win = false;
    static int NumCups = 0;
    static int TotalMoney = 200;
    static int BetAmmount = 0;
    static int DifficultyNum = 0;
    static Random Gen = new Random();
    static boolean Choice = false;
    static int RandomCup = 0;
    static int ChosenCup  = 0;
    static String HighScoreStr = "";
    static int HighScore = 0;
    static boolean Failed = false;

    //Load highscore from file HighScore.txt, make sure to save a file with a value of zero.
    static String FileName = "HighScore";
    static String line = null;
    static JLabel text;
    static JLabel lbl;
    static JFrame frame;
    static JLabel lblInfo ;
    static JLabel lblMessage;
    static JLabel lblFiller;
    static JLabel text1;

    // cups variables
    JButton ecup0 ;
    JButton ecup1;
    JButton ncup0;
    JButton ncup1;
    JButton ncup2;
    JButton hcup0;
    JButton hcup1;
    JButton hcup2;
    JButton hcup3;

    //define pictures
    ImageIcon icon = new ImageIcon(PirateShellGame.class.getResource("cup.jpg"));
    ImageIcon intro = new ImageIcon(PirateShellGame.class.getResource("intro.jpg"));
    ImageIcon winner = new ImageIcon(PirateShellGame.class.getResource("winner.jpg"));
    ImageIcon loser = new ImageIcon(PirateShellGame.class.getResource("loser.jpg"));
    ImageIcon cone = new ImageIcon(PirateShellGame.class.getResource("one.jpg"));
    ImageIcon cfive = new ImageIcon(PirateShellGame.class.getResource("five.jpg"));
    ImageIcon cten = new ImageIcon(PirateShellGame.class.getResource("twentyfive.jpg"));
    ImageIcon cAllIn = new ImageIcon(PirateShellGame.class.getResource("AllIn.jpg"));
    
    public void actionPerformed(ActionEvent e){
        if ("exit".equals(e.getActionCommand())){
            System.exit(0);
        }
        else if ("Play".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
              new PirateShellGame().ShowPlay();
        }
        else if ("done".equals(e.getActionCommand()))
        {
        	
        	if (BetAmmount<1)
        	{
        		JOptionPane.showMessageDialog(null,"You cannot bet less than $1");
        	}
        	else 
        	{
        		frame.setVisible(false);
                new PirateShellGame().ShowDifficulty();
        	}
        	
        }
        else if ("help".equals(e.getActionCommand()))
        {
            JOptionPane.showMessageDialog(frame,"The object of the game is to guess in which cup the ball is",
                                                "Help",JOptionPane.PLAIN_MESSAGE);
        }
        else if ("normal".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            DifficultyNum = 2;
            NumCups = 3;
            boolean[] Cups = new boolean[NumCups];
            HideBall(Cups);
        }
        else if ("easy".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            DifficultyNum = 1;
            NumCups = 2;
            boolean[] Cups = new boolean[NumCups];
            HideBall(Cups);
        }
        else if ("hard".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            DifficultyNum = 3;
            NumCups= 4;
            boolean[] Cups = new boolean[NumCups];
            HideBall(Cups);
        }
        else if ("AllIn".equals(e.getActionCommand()))
        {
            BetAmmount = BetAmmount +TotalMoney;
            if (BetAmmount>TotalMoney){
                BetAmmount = BetAmmount-TotalMoney;
                JOptionPane.showMessageDialog(null, "You cannot bet more money than you have");
            }
            text.setText("  Yer bet: "+BetAmmount);
        }
        else if ("ten".equals(e.getActionCommand()))
        {
            BetAmmount = BetAmmount +25;
            if (BetAmmount>TotalMoney){
                BetAmmount = BetAmmount-25;
                JOptionPane.showMessageDialog(null, "You cannot bet more money than you have");
            }
            text.setText("  Yer bet: "+BetAmmount);
        }
        else if ("five".equals(e.getActionCommand()))
        {
            BetAmmount = BetAmmount +5;
            if (BetAmmount>TotalMoney){
                BetAmmount = BetAmmount-5;
                JOptionPane.showMessageDialog(null, "You cannot bet more money than you have");
            }
            text.setText("  Yer bet: "+BetAmmount);
        }
        else if ("one".equals(e.getActionCommand()))
        {
            BetAmmount = BetAmmount +1;
            if (BetAmmount>TotalMoney){
                BetAmmount = BetAmmount-1;
                JOptionPane.showMessageDialog(null, "You cannot bet more money than you have");
            }
            text.setText("  Yer bet: "+BetAmmount);
        }
        else if ("ecup0".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            ChosenCup = 0;
            CheckWinner();
        }
        else if ("ecup1".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            ChosenCup = 1;
            CheckWinner();
        }
        else if ("ncup0".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            ChosenCup = 0;
            CheckWinner();
        }
        else if ("ncup1".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            ChosenCup = 1;
            CheckWinner();
        }
        else if ("ncup2".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            ChosenCup = 2;
            CheckWinner();
        }
        else if ("hcup0".equals(e.getActionCommand())){
        	frame.setVisible(false);
            ChosenCup = 0;
            CheckWinner();
        }
        else if ("hcup1".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            ChosenCup = 1;
            CheckWinner();
        }
        else if ("hcup2".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            ChosenCup = 2;
            CheckWinner();
        }
        else if ("hcup3".equals(e.getActionCommand()))
        {
        	frame.setVisible(false);
            ChosenCup = 3;
            CheckWinner();
        }
    }
    public void Menu(Container pane) throws IOException {
        JButton exitButton;
        JButton helpButton;
        JButton PlayButton;
        if(RIGHT_TO_LEFT){
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        lblInfo = new JLabel("    Welcome to the Pirate Game");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        pane.add(lblInfo, c);

        lblMessage = new JLabel("Yarr! Ahoy matey, do ye wanna win easy doubloons? ");
        c.gridwidth=5;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=3;
        c.gridy=1;
        pane.add(lblMessage, c);

        lblMessage = new JLabel(intro);
        c.gridwidth=3;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        pane.add(lblMessage, c);
        
        text1 = new JLabel("Your HighScore: "+ HighScore);
        c.gridwidth=8;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridy = 0;
        pane.add(text1, c);

        lblFiller = new JLabel(" ");
        c.gridwidth=8;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=2;
        pane.add(lblFiller, c);

        helpButton = new JButton("Help");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=3;
        c.gridy=6;
        helpButton.setActionCommand("help");
        helpButton.addActionListener(this);
        pane.add(helpButton, c);

        PlayButton = new JButton("Play");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=4;
        c.gridy=6;
        PlayButton.setActionCommand("Play");
        PlayButton.addActionListener(this);
        pane.add(PlayButton, c);

        exitButton = new JButton("Exit");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=5;
        c.gridy=6;
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(this );
        pane.add(exitButton, c);
       
    }
    private void ShowMenu(){
        frame = new JFrame("Shell Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Menu(frame.getContentPane());
        }
        catch (IOException e) {
            System.out.println("Files not found.");
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);
    }
    public void Play(Container pane)throws IOException {
        JButton ten;
        JButton five;
        JButton one;
        JButton AllIn;
        JButton done;

        if(RIGHT_TO_LEFT){
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        lbl = new JLabel("  Select yer wager scallywag! ");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        pane.add(lbl, c);

        lblFiller = new JLabel(" ");
        c.gridwidth=8;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=2;
        pane.add(lblFiller, c);

        text = new JLabel ("  Yer bet: "+BetAmmount);
        c.gridwidth=8;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        pane.add(text, c);

        text1 = new JLabel ("  Yer gold: "+TotalMoney);
        c.gridwidth=8;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=4;
        pane.add(text1, c);

        AllIn = new JButton(cAllIn);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=6;
        c.gridy=6;
        AllIn.setActionCommand("AllIn");
        AllIn.addActionListener(this);
        pane.add(AllIn, c);
        
        ten = new JButton(cten);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=3;
        c.gridy=6;
        ten.setActionCommand("ten");
        ten.addActionListener(this);
        pane.add(ten, c);

        five = new JButton(cfive);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=4;
        c.gridy=6;
        five.setActionCommand("five");
        five .addActionListener(this);
        pane.add(five, c);

        one= new JButton(cone);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=5;
        c.gridy=6;
        one.setActionCommand("one");
        one.addActionListener(this );
        pane.add(one, c);

        done = new JButton("Ready!");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=6;
        done.setActionCommand("done");
        done.addActionListener(this);
        pane.add(done, c);
    }
    public void ShowPlay(){
        frame = new JFrame("Bet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Play(frame.getContentPane());
        } catch (IOException e) {
            System.out.println("Files not found.");
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);

    }
    public void Difficulty(Container pane)throws IOException {
        JButton easy;
        JButton normal;
        JButton hard;

        if(RIGHT_TO_LEFT){
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        lblMessage= new JLabel("Select difficulty: ");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        pane.add(lblMessage, c);

        
        lblMessage = new JLabel(loser);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        pane.add(lblMessage, c);

        easy = new JButton("Easy: 2 cups");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=3;
        easy.setActionCommand("easy");
        easy.addActionListener(this);
        pane.add(easy, c);

        normal = new JButton("Normal: 3 cups");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=2;
        c.gridy=3;
        normal.setActionCommand("normal");
        normal.addActionListener(this);
        pane.add(normal, c);

        hard = new JButton("Hard: 4 cups");
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=3;
        c.gridy=3;
        hard.setActionCommand("hard");
        hard.addActionListener(this );
        pane.add(hard, c);
        
      }
    public void ShowDifficulty () {
        frame = new JFrame("Difficulty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Difficulty(frame.getContentPane());
        } catch (IOException e) {
            System.out.println("Files not found.");
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);
    }
    public void Picker() {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                ShowMenu();
            }
        });
    }
    public void Easy(Container pane) throws IOException {
        if(RIGHT_TO_LEFT){
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lbl = new JLabel("Select the cup where the ball is in" );
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        pane.add(lbl, c);

        lblFiller = new JLabel(" ");
        c.gridwidth=8;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=2;
        pane.add(lblFiller, c);

        ecup0 = new JButton (icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.VERTICAL;
        c.gridx=0;
        c.gridy=6;
        ecup0.setActionCommand("ecup0");
        ecup0.addActionListener(this);
        pane.add(ecup0, c);

        ecup1 = new JButton(icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=6;
        ecup1.setActionCommand("ecup1");
        ecup1.addActionListener(this);
        pane.add(ecup1, c);
        
    }
    public void ShowEasy() {
        frame = new JFrame("Easy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Easy(frame.getContentPane());
        } catch (IOException e) {
            System.out.println("Files not found.");
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);
    }
    public void Normal (Container pane)throws IOException {
        if(RIGHT_TO_LEFT){
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lbl = new JLabel("Select the cup where the ball is in" );
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        pane.add(lbl, c);

        lblFiller = new JLabel(" ");
        c.gridwidth=8;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=2;
        pane.add(lblFiller, c);

        ncup0 = new JButton(icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=6;
        ncup0.setActionCommand("ncup0");
        ncup0.addActionListener(this);
        pane.add(ncup0, c);

        ncup1 = new JButton(icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=3;
        c.gridy=6;
        ncup1.setActionCommand("ncup1");
        ncup1.addActionListener(this);
        pane.add(ncup1, c);

        ncup2 = new JButton(icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=2;
        c.gridy=6;
        ncup2.setActionCommand("ncup2");
        ncup2.addActionListener(this);
        pane.add(ncup2, c);
        
    }
    public void ShowNormal () {
        frame = new JFrame("Normal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Normal(frame.getContentPane());
        } catch (IOException e) {
            System.out.println("Files not found.");
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);
    }
    public void Hard (Container pane)throws IOException{
        if(RIGHT_TO_LEFT){
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lbl = new JLabel("Select the cup where the ball is in" );
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        pane.add(lbl, c);

        lblFiller = new JLabel(" ");
        c.gridwidth=8;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=2;
        pane.add(lblFiller, c);

        hcup0 = new JButton(icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=6;
        hcup0.setActionCommand("hcup0");
        hcup0.addActionListener(this);
        pane.add(hcup0, c);

        hcup1 = new JButton(icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=6;
        hcup1.setActionCommand("hcup1");
        hcup1.addActionListener(this);
        pane.add(hcup1, c);

        hcup2 = new JButton(icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=2;
        c.gridy=6;
        hcup2.setActionCommand("hcup2");
        hcup2.addActionListener(this);
        pane.add(hcup2, c);

        hcup3 = new JButton(icon);
        c.gridwidth=1;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=3;
        c.gridy=6;
        hcup3.setActionCommand("hcup3");
        hcup3.addActionListener(this);
        pane.add(hcup3, c);
        
    }
    public void ShowHard () {
        frame = new JFrame("Hard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Hard(frame.getContentPane());
        } catch (IOException e) {
            System.out.println("Files not found.");
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);
    }
    protected ImageIcon createImageIcon(String path) {
          java.net.URL imgURL = PirateShellGame.class.getResource(path);
          if (imgURL != null) {
              return new ImageIcon(imgURL);
          } else {
              System.err.println("Couldn't find file: " + path);
              return null;
          }
    }
    public static void main (String[] args)throws IOException{
        try {
            FileReader filereader = new FileReader(FileName);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            while((line = bufferedreader.readLine()) != null) {
                HighScoreStr = (line);
            }
            bufferedreader.close();
        }
        //Stop from crashing in 2 areas
        //1. IOException and other exception from crashing after not finding or being able to read the file
        //2. Later when comparing the total score to the high score a  value is needed or else a crash is imminent.
        catch(FileNotFoundException ex){
            System.out.println("Unable to open file "+ FileName +".txt");
            HighScore = 0;
            Failed = true;
        }
        catch(IOException ex){
            System.out.println("Error Reading file "+ FileName +".txt");
            HighScore = 0;
            Failed = true;
        }
        if (Failed==false){
            HighScore = Integer.parseInt(HighScoreStr);
        }
        new PirateShellGame().Picker();
    }
    public boolean[] HideBall(boolean[] Cups){
        RandomCup = Gen.nextInt(NumCups);
      //Cheating: System.out.println(RandomCup);
        for (int x = 0; x<NumCups-1; x++){
            if (x == RandomCup){
                Cups[x] = true;
            }
            else {
                Cups[x] = false;
            }
        }
            if (DifficultyNum == 1){
                new PirateShellGame().ShowEasy();
            }
            else if (DifficultyNum == 2){
                new PirateShellGame().ShowNormal();
            }
            else if (DifficultyNum == 3){
                new PirateShellGame().ShowHard();
            }
        return Cups;
    }
    public void CheckWinner(){
        if (RandomCup == ChosenCup){
            Winner();
        }
        else
        {
            Loser();
        }
    }
    public void Winner(){
        String choice ="";
        choice = JOptionPane.showInputDialog(null, "Yarr! You thief! Would  ye like t'try again? Y/N");
        if (choice.equalsIgnoreCase("Y")) 
        {
            Choice = true;
        }
        TotalMoney = TotalMoney + (BetAmmount*DifficultyNum);
        //do you want to play again?
        if (Choice == false)
        {
            if (TotalMoney>HighScore)
            {
                //Write a file, or override the previous highscore file with  the new highscore
                //And save it to Bin? maybe...?
                try 
                {
                    FileWriter High = new FileWriter(FileName);
                    BufferedWriter bufferedwriter = new BufferedWriter(High);
                    bufferedwriter.write(""+ TotalMoney);
                    bufferedwriter.close();
                }
                //prevent crashing if possible.
                catch(IOException ex)
                {
                    System.out.println("Error saving Highscore");
                    System.out.println(" under HighScore.txt");
                }
            }
            System.exit(0);
        }
        else
        {
            BetAmmount = 0;
            text.setText("Your bet: "+BetAmmount);
            new PirateShellGame().ShowPlay();
            //Return to betting Screen.
        }
        Choice = false;
    }
    public void Loser(){
        String choice ="";
        choice = JOptionPane.showInputDialog(null, "Ha-ha I got yer doubloons bucko! Would ye like t'try again? Y/N");
        if (choice.equalsIgnoreCase("Y")) {
            Choice = true;
        
        //Some more crap about being a pirate and how you lost.
        TotalMoney = TotalMoney-BetAmmount;
        if (TotalMoney<1){
            //Show you lose
            JOptionPane.showMessageDialog(null, "I'm sorry matey. No doubloons, no game. \nIt's over!");
            System.exit(0);
        }
        }
        if (Choice == false){
            System.exit(0);
        }
        else {
            BetAmmount = 0;
            text.setText("Your bet: "+BetAmmount);
            new PirateShellGame().ShowPlay();
            //Return to betting Screen.

        }
        Choice = false;
    }

}