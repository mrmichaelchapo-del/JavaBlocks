import javax.swing.*;
import java.awt.*;

public class Movesteps extends JPanel {

    private static final int STEP = 4;          // pixels per frame
    private static final int DELAY_MS = 16;     // ~60 FPS
    private static final int SIZE = 40;         // moving object size

    private int x = 0;                          // current position
    private int y = 200;

    public Movesteps() {
        setPreferredSize(new Dimension(800, 400));
        setBackground(Color.WHITE);

        // Animation loop: advance one STEP, repaint, repeat.
        Timer timer = new Timer(DELAY_MS, e -> {
            x += STEP;
            if (x > getWidth()) {               // wrap around the screen
                x = -SIZE;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x2E86DE));
        g.fillRoundRect(x, y, SIZE, SIZE, 12, 12);
    }

    // Entry point for the VPL / standalone run
    public static void run() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Movesteps");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Movesteps());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        run();
    }
}