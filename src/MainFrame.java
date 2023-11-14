import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MainFrame {

    Boolean HumanFirst = true;
    State state ;

    Boolean withPrunning = false;

    int depth;

    static JFrame frame = new JFrame("Connect4");
//    static JPanel scores = new JPanel();
    static JPanel board = new JPanel(new GridLayout(6,7));
    static JPanel inputs = new JPanel();

    static JButton[][] cells =new JButton[6][7];

    static JButton[] input = new JButton[7];

    static JTextField textDepth = new JTextField("3");

    static JPanel jj = new JPanel();
    static JButton startAI = new JButton("start AI");

    static JCheckBox prunning = new JCheckBox();

    public MainFrame() {
        this.state = new State();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setBackground(Color.YELLOW);
                cells[i][j].setSize(100,100);

                board.add(cells[i][j]);
            }
        }

        for (int i = 0; i < input.length; i++) {
            input[i] = new JButton("row"+(i+1));
            int finalI = i;
            input[i].addActionListener(e -> {

                //call play at row finalI
                state.playturn(finalI+1);
                state.printGrid();
                guiPrint();
                this.depth = Integer.parseInt(textDepth.getText());
                this.withPrunning = prunning.isSelected();
                Connect4MiniMax game;
    //                    if (this.state.player == 1){
                game = new Connect4MiniMax(depth,this.state,!HumanFirst, Minimax.hasher);

                if(game.isTerminal(state)){
                    int score = game.estimate(state);
                    System.out.println(score);
                    if(score == 10000){
                        JOptionPane.showMessageDialog(
                                null,
                                "Player 1 wins!!!"
                        );
                    }else if(score == -10000){
                        JOptionPane.showMessageDialog(
                                null,
                                "Player 2 wins!!!"
                        );
                    }
                    return;
                }

                if(state.getDigit(state.row6, finalI+1) != 0){
                    JOptionPane.showMessageDialog(
                            null,
                            "Column full!!!!!"
                    );
                    return;
                }

                if(withPrunning){
                    System.out.println("with prunning " + game.minimaxWithPruning());
                }
                else {
                    System.out.println("without prunning "+game.minimax());
                }
                this.state = game.parentMap.get(state)!=null? game.parentMap.get(state):this.state;

                state.printGrid();
    //                    }
                //read the file and rename
                guiPrint();
        });
            inputs.add(input[i]);
        }
        startAI.addActionListener(e->{
            this.HumanFirst = false;
            this.depth = Integer.parseInt(textDepth.getText());
            this.withPrunning = prunning.isSelected();
            Connect4MiniMax game;
            game = new Connect4MiniMax(depth,this.state,!HumanFirst, Minimax.hasher);
            if(withPrunning){
                System.out.println("with prunning " + game.minimaxWithPruning());
            }
            else {
                System.out.println("without prunning "+game.minimax());
            }
            this.state = game.parentMap.get(state);
            state.printGrid();
            guiPrint();
        });
        jj.setLayout(new BoxLayout(jj,BoxLayout.Y_AXIS));
        jj.add(startAI);
        jj.add(textDepth);
        jj.add(new JLabel("with Prunning:"));
        jj.add(prunning);
        textDepth.setBackground(Color.CYAN);
        frame.setLayout(new BorderLayout());
//        frame.add(textDepth,BorderLayout.NORTH);
//        frame.add(scores,BorderLayout.NORTH);
        frame.add(board,BorderLayout.CENTER);
        frame.add(inputs,BorderLayout.SOUTH);
        frame.add(jj,BorderLayout.EAST);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    static void guiPrint(){
        File f = new File("src/connect4");
        try {
            Scanner sc = new Scanner(f);
            int r = 0;
            while(sc.hasNext()){
                String row = sc.nextLine();
                String[] cs = row.split(" ");
                for (int i = 0; i < 7; i++) {
                    if(cs[i].equals("1")){
                        cells[r][i].setIcon(new ImageIcon(ImageIO.read(new File("src/bestchipred.png")).getScaledInstance(70,70,Image.SCALE_AREA_AVERAGING)));
                    } else if (cs[i].equals("2")) {
                        cells[r][i].setIcon(new ImageIcon(ImageIO.read(new File("src/bestchipblue.png")).getScaledInstance(70,70,Image.SCALE_AREA_AVERAGING)));
                    }
                }
                r++;
            }
            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Minimax.hasher = new Connect4Hasher();
        new MainFrame();

//        while (true) {
//            Connect4MiniMax game ;
//            if(state.player==2) {
//                game = new Connect4MiniMax(7, state, true);
//                System.out.println(game.minimaxWithPruning());
//                state = game.parentMap.get(state);
//            }
//            else {
//                game = new Connect4MiniMax(7, state, false);
//                System.out.println(game.minimaxWithPruning());
//                state = game.parentMap.get(state);
//            }
//            if(game.isTerminal(state)){
//                break;
//            }
        }
    }

