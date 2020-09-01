package life;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class GameDisplay extends javax.swing.JFrame implements Runnable{
    private JPanel upperPanel;
    private JPanel lowerPanel;
    private JToggleButton startStopButton;
    private JLabel generationLabel;
    private JLabel aliveLabel;
    private JPanel [][] panelMatrix;
    private JSlider speedSlider;
    private int speed = 1;
    private JLabel slideLabel;
    private boolean start=true;
    public GameDisplay(Universe universe) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init(universe);
        setTitle("Game of Life");
        update(universe);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        upperPanel.setVisible(true);
        lowerPanel.setVisible(true);
        this.getContentPane().add(upperPanel);
        this.getContentPane().add(lowerPanel);
        pack();
    }

    private void init(Universe universe) {
        upperPanel = new JPanel();
        upperPanel.setLayout( new GridLayout(3,2));

        generationLabel = new JLabel();
        aliveLabel = new JLabel();
        generationLabel.setHorizontalAlignment(SwingConstants.NORTH_EAST);
        generationLabel.setText("Generation #"+universe.generation);
        generationLabel.setName("genLabel");
        aliveLabel.setHorizontalAlignment(SwingConstants.NORTH_EAST);
        aliveLabel.setName("aliveLabel");
        aliveLabel.setText("Alive: "+universe.alive);



        startStopButton = new JToggleButton("Pause");
        startStopButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                start = ! start;
                String text = start? "Pause":"Resume";
                startStopButton.setText(text);
            }
        });


        slideLabel = new JLabel();
        speedSlider = new JSlider(1, 10,1);
        slideLabel.setText("Time Interval: "+speed);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                int value = speedSlider.getValue();
                slideLabel.setText("Time Interval: "+value);
                speed = value;
            }
        });

        upperPanel.add(generationLabel);
        upperPanel.add(aliveLabel);
        upperPanel.add(speedSlider);
        upperPanel.add(slideLabel);
        upperPanel.add(startStopButton);





        lowerPanel = new JPanel();
        panelMatrix = new JPanel [universe.size][universe.size];
        GridLayout lowerPanelLayout = new GridLayout(universe.size,universe.size);
        lowerPanel.setLayout(lowerPanelLayout);
        for (int r=0; r < universe.size; r++) {
            for (int c = 0; c < universe.size; c++) {
                panelMatrix[r][c] = new JPanel();
                JPanel rect = this.panelMatrix[r][c];
                if (universe.states[r][c] == 1) {rect.setBackground(Color.black);}
                else {rect.setBackground(Color.white);}
                rect.setBorder(BorderFactory.createLineBorder(Color.black));
                lowerPanel.add(rect);
                rect.setVisible(true);
            }
        }
    }



    public void update(Universe universe){
        generationLabel.setText("Generation #"+Integer.toString(universe.generation));
        aliveLabel.setText("Alive: "+Integer.toString(universe.alive));
        for (int r=0; r < universe.size; r++){
            for (int c=0; c < universe.size; c++){
                JPanel rect = panelMatrix[r][c];
                if (universe.states[r][c] == 1) {rect.setBackground(Color.black);}
                else {rect.setBackground(Color.white);}
            }
        }

    }

    public boolean isStarted(){
        return start;
    }

    public int getSpeed(){
        return speed * 1000;
    }

    @Override
    public void run() {
        setVisible(true);
    }
}
