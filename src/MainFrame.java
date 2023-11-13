import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MainFrame {
    static JFrame frame = new JFrame("Connect4");
    static JPanel scores = new JPanel();
    static JPanel board = new JPanel(new GridLayout(6,7));
    static JPanel inputs = new JPanel();

    static JButton[][] cells =new JButton[6][7];

    static JButton[] input = new JButton[7];

    public MainFrame() {
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
//                    new State().playturn(finalI);
                //read the file and rename
                printGrid();
            });
            inputs.add(input[i]);
        }
        frame.setLayout(new BorderLayout());
        frame.add(scores,BorderLayout.NORTH);
        frame.add(board,BorderLayout.CENTER);
        frame.add(inputs,BorderLayout.SOUTH);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    static void printGrid(){
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
        new MainFrame();
    }
}
