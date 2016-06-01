package display;

import players.HumanPlayerDisplay;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import jogo.AbstractPlayer;
import jogo.Casa;
import jogo.Jogada;

/**
 *
 * @author Marcelo Paglione
 */
public class Display extends JFrame {

    private ButtonCasa[][] buttons;
    private ButtonCasa[][] buttonsGameRecord;
    private GridLayout gridLayout;
    private static String path;
    private static final int BUTTON_SIZE = 50;
    private static int row, col;
    private static boolean click = false;
    private JLabel playerVez, player1Name, player2Name, winner;
    public static JLabel player1Score, player2Score;
    public static AbstractPlayer player1class, player2class,currentPlayer;
    private int[][] gameRecord;
    private int gameRecordCounter = 0;
    public static boolean showGameRecord = false;

    private JFrame frame;
    private JPanel panel;

    public static boolean ENABLE = true;
    public static int TIME = 500;
    public static Icon blue, red, blue_red, red_blue, blue_winner, red_winner, blue_player, red_player;

    /**
     * Informa para o Display quem serão os jogadores desta partida
     *
     * @param player1
     * @param player2
     */
    public void setPlayersName(AbstractPlayer player1, AbstractPlayer player2) {
        this.player1class = player1;
        this.player2class = player2;
        player1Name.setText(player1.getClass().getSimpleName());
        player2Name.setText(player2.getClass().getSimpleName());
    }

    /**
     * Inicializa todas as variáveis, o painel lateral e o painel principal
     */
    public Display() {
        initComponents();
        setTitle("jOthelloT");
        getContentPane().setBackground(Color.BLACK);
        String separator = File.separator;
        path = System.getProperty("user.dir") + separator + "src" + separator + "Display" + separator + "images" + separator;
        blue_red = new ImageIcon(path + "blueRed.gif");
        red_blue = new ImageIcon(path + "redBlue.gif");
        red = new ImageIcon(path + "red.png");
        red_player = new ImageIcon(path + "redPlayer.gif");
        red_winner = new ImageIcon(path + "redWinner.gif");
        blue = new ImageIcon(path + "blue.png");
        blue_player = new ImageIcon(path + "bluePlayer.gif");
        blue_winner = new ImageIcon(path + "blueWinner.gif");
        click = false;
        row = -1;
        col = -1;

        setMenuLateral();
        buttons = new ButtonCasa[8][8];
        setTabuleiro(tabuleiro, buttons);
        setBordasPretas();

        gameRecord = new int[buttons.length][buttons[0].length];
        gameRecord[buttons.length / 2][buttons.length / 2] = -1;
        gameRecord[buttons.length / 2 - 1][buttons.length / 2] = -1;
        gameRecord[buttons.length / 2][buttons.length / 2 - 1] = -1;
        gameRecord[buttons.length / 2 - 1][buttons.length / 2 - 1] = -1;
        panel = new JPanel();
        buttonsGameRecord = new ButtonCasa[8][8];
        setTabuleiro(panel, buttonsGameRecord);
        setRecordGameTab();
        frame = new JFrame("Game Record");
        frame.add(panel);
        //frame.setVisible(true);
        frame.pack();
        setResizable(false);

    }

    private synchronized void set() {
        while (click) {
            try {
                wait();
            } catch (InterruptedException exception) {
            }
        }
        click = true;
        notify();
    }

    /**
     * Retorna a jogada escolhida pelo HumanPlayerDisplay
     *
     * @return
     */
    public synchronized Casa get() {
        while (!click) {
            try {
                wait();
            } catch (InterruptedException exception) {

            }
        }
        click = false;
        notify();
        return new Casa(row, col);
    }

    private void setTabuleiro(JPanel tabuleiro, ButtonCasa[][] buttons) {

        gridLayout = new GridLayout(buttons.length + 1, buttons[0].length + 1, 0, 0);
        tabuleiro.setLayout(gridLayout);
        tabuleiro.setBackground(new Color(41, 110, 55));//verde

        JButton button = new JButton();
        button.setBackground(new Color(2, 23, 7));//verde mais escuro
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        button.setEnabled(false);
        tabuleiro.add(button);
        for (int i = 0; i < buttons.length; i++) {
            if (i == 0) {
                for (int j = 0; j < buttons[i].length; j++) {
                    button = new JButton((char) (j + 97) + "");
                    button.setEnabled(false);
                    button.setForeground(Color.yellow);
                    button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
                    button.setBackground(new Color(4, 51, 15));
                    tabuleiro.add(button);
                }
            }
            button = new JButton((i + 1) + "");
            button.setEnabled(false);
            button.setForeground(new Color(255, 255, 255));
            button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
            button.setBackground(new Color(4, 51, 15));
            tabuleiro.add(button);
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new ButtonCasa();
                buttons[i][j].setCasa(new Casa(i, j));
                buttons[i][j].getButton().setContentAreaFilled(false);
                buttons[i][j].getButton().setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
                addMouseEvent(buttons[i][j]);
                tabuleiro.add(buttons[i][j].getButton());
            }
        }
    }

    private void setMenuLateral() {
        gridLayout = new GridLayout(8, 1, 0, 0);
        menuLateral.setLayout(gridLayout);
        menuLateral.setBackground(Color.BLACK);

        playerVez = new JLabel("Turn");
        playerVez.setFont(new Font("Verdana", 0, 20));
        playerVez.setForeground(new Color(255, 255, 255));

        player1Name = new JLabel();
        player1Name.setFont(new Font("Verdana", 0, 20));
        player1Name.setForeground(new Color(255, 255, 255));

        player1Score = new JLabel();
        player1Score.setIcon(blue);
        player1Score.setFont(new Font("Verdana", 0, 35));
        player1Score.setForeground(new Color(255, 255, 255));

        player2Name = new JLabel();
        player2Name.setFont(new Font("Verdana", 0, 20));
        player2Name.setForeground(new Color(255, 255, 255));

        player2Score = new JLabel();
        player2Score.setIcon(red);
        player2Score.setFont(new Font("Verdana", 0, 35));
        player2Score.setForeground(new Color(255, 255, 255));

        winner = new JLabel("Vencedor");
        winner.setFont(new Font("Verdana", 0, 20));
        winner.setForeground(new Color(255, 255, 255));
        winner.setVisible(false);

        menuLateral.add(playerVez);
        menuLateral.add(player1Name);
        menuLateral.add(player1Score);
        menuLateral.add(new JLabel());
        menuLateral.add(player2Name);
        menuLateral.add(player2Score);
        menuLateral.add(new JLabel());
        menuLateral.add(winner);
    }

    private void setPlacar(int player1, int player2) {
        this.player1Score.setText(String.valueOf(player1));
        this.player2Score.setText(String.valueOf(player2));
    }

    /**
     * Display informa quem venceu o jogo no menu lateral
     *
     * @param ganhador
     */
    public void setGanhador(int ganhador) {
        switch (ganhador) {
            case 1:
                winner.setIcon(blue_winner);
                break;
            case -1:
                winner.setIcon(red_winner);
                break;
            default:
                winner.setText("Empate");
                break;
        }
        winner.setVisible(true);
    }

    /**
     * Alterna o icone que representa a vez do jogador no menu lateral
     *
     * @param jogador
     */
    public void setPlayerTurn(int jogador) {
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
        if (jogador == 1) {
            playerVez.setIcon(blue_player);
        } else if (jogador == -1) {
            playerVez.setIcon(red_player);
        }
    }

    private void addMouseEvent(ButtonCasa button) {
        button.getButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = button.getCasa().getLinha();
                col = button.getCasa().getColuna();
                if (button.getMouseListener()) {
                    set();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.getMouseListener()) {
                    button.getButton().setBorder(BorderFactory.createLineBorder(new Color(255, 255, 0)));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (button.getMouseListener()) {
                    button.getButton().setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
                }
            }
        });
    }

    /**
     * Atualiza o display com as jogadas validas.
     *
     * 1 - Troca todas as bordas internas do tabuleiro para a cor preta e o
     * cursor para o tipo DEFAULT_CURSOR
     *
     * 2 - Troca as bordas que forem jogadas validas para a cor branca e o
     * cursos do mouse para o tipo HAND_CURSOR
     *
     * @see setBordasPretas()
     * @param jogadasValidas
     */
    public void jogadasValidas(List<Jogada> jogadasValidas,AbstractPlayer player) {
        setBordasPretas();
        for (Jogada jogo : jogadasValidas) {
            int i = jogo.getCasa().getLinha();
            int j = jogo.getCasa().getColuna();
            buttons[i][j].getButton().setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
            if (player instanceof HumanPlayerDisplay ) {
                buttons[i][j].getButton().setCursor(new Cursor(Cursor.HAND_CURSOR));
                buttons[i][j].setMouseListener(true);
            }
        }
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void setBordasPretas() {
        for (ButtonCasa[] jButtons : buttons) {
            for (ButtonCasa button : jButtons) {
                button.getButton().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
                button.getButton().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                button.setMouseListener(false);
            }
        }
    }

    /**
     * 1 - Atualiza todas as posicoes do display com os icones azul ou vermelho.
     * Caso o tabuleiro ja possua um icone, ou seja ja possui uma peca do jogo,
     * realiza a transicao entre a cor atual da peca para a segunda opcao de cor
     *
     * 2 - Confere a pontuacao de cada jogador e atualiza no display
     *
     * 3 - Confere o tamanho fisico dos objetos internos e expande os externos
     * para acomodar todos os objetos da cena
     *
     * 4 - Centraliza o Display na tela do usuario
     *
     * 5 - Mostra o Display
     *
     * @see setPlacar(int player1, int player2)
     * @param tabuleiro
     */
    public void atualizarDisplay(int[][] tabuleiro) {
        int p1 = 0, p2 = 0;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (tabuleiro[i][j] == 1) {
                    if (buttons[i][j].getButton().getIcon() == null) {
                        buttons[i][j].getButton().setIcon(blue);
                    } else if (String.valueOf(buttons[i][j].getButton().getIcon()).equals(String.valueOf(red))) {
                        RedBlue transition = new RedBlue(buttons[i][j].getButton());
                        new Thread(transition).start();
                    }
                    p1++;
                } else if (tabuleiro[i][j] == -1) {
                    if (buttons[i][j].getButton().getIcon() == null) {
                        buttons[i][j].getButton().setIcon(red);
                    } else if (String.valueOf(buttons[i][j].getButton().getIcon()).equals(String.valueOf(blue))) {
                        BlueRed transition = new BlueRed(buttons[i][j].getButton());
                        new Thread(transition).start();
                    }
                    p2++;
                }
            }
        }
        setPlacar(p1, p2);
        pack();
        if (showGameRecord) {
            if (!frame.isVisible()) {
                showGameRecord();
            }
        }
        if (!isVisible()) {
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    public int[][] getGameRecor() {
        return gameRecord;
    }

    public JLabel getPlayer1Score() {
        return player1Score;
    }

    public JLabel getPlayer2Score() {
        return player2Score;
    }

    public AbstractPlayer getPlayer1class() {
        return player1class;
    }

    public AbstractPlayer getPlayer2class() {
        return player2class;
    }

    /**
     * Mostrar a interface do Game Record da atual jogada
     *
     * Interface é atualizada a cada iteração dos jogadores
     */
    public void showGameRecord() {
        if (!frame.isVisible()) {
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    frame.setVisible(false);
                }
            });
        }
    }

    /**
     * Atualiza o Display principal e o frame Game Record
     *
     * @param tabuleiro
     * @param casa
     * @param player
     */
    public void atualizarDisplay(int[][] tabuleiro, Casa casa, AbstractPlayer player) {
        currentPlayer = player;
        atualizarDisplay(tabuleiro);
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setMouseListener(false);
            }
        }
        gameRecordCounter++;
        if (gameRecordCounter <= 60) {
            try {
                if (player.getClass() == player1class.getClass()) {
                    gameRecord[casa.getLinha()][casa.getColuna()] = gameRecordCounter;
                    buttonsGameRecord[casa.getLinha()][casa.getColuna()].getButton().setIcon(blue);

                } else {
                    gameRecord[casa.getLinha()][casa.getColuna()] = gameRecordCounter;
                    buttonsGameRecord[casa.getLinha()][casa.getColuna()].getButton().setIcon(red);
                }
                buttonsGameRecord[casa.getLinha()][casa.getColuna()].getButton().setText(gameRecordCounter + "");
                buttonsGameRecord[casa.getLinha()][casa.getColuna()].getButton().setContentAreaFilled(false);
                buttonsGameRecord[casa.getLinha()][casa.getColuna()].getButton().setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
                buttonsGameRecord[casa.getLinha()][casa.getColuna()].getButton().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
                buttonsGameRecord[casa.getLinha()][casa.getColuna()].getButton().setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                buttonsGameRecord[casa.getLinha()][casa.getColuna()].getButton().setPreferredSize(new Dimension((int) (BUTTON_SIZE * 0.5), (int) (BUTTON_SIZE * 0.5)));
            } catch (Exception e) {

            }
        }
    }
    

    private void setRecordGameTab() {
        buttonsGameRecord[buttons.length / 2][buttons.length / 2].getButton().setIcon(red);
        buttonsGameRecord[buttons.length / 2 - 1][buttons.length / 2].getButton().setIcon(blue);
        buttonsGameRecord[buttons.length / 2][buttons.length / 2 - 1].getButton().setIcon(blue);
        buttonsGameRecord[buttons.length / 2 - 1][buttons.length / 2 - 1].getButton().setIcon(red);

        buttonsGameRecord[buttons.length / 2][buttons.length / 2].getButton().setText("");
        buttonsGameRecord[buttons.length / 2 - 1][buttons.length / 2].getButton().setText("");
        buttonsGameRecord[buttons.length / 2][buttons.length / 2 - 1].getButton().setText("");
        buttonsGameRecord[buttons.length / 2 - 1][buttons.length / 2 - 1].getButton().setText("");

        buttonsGameRecord[buttons.length / 2][buttons.length / 2].getButton().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        buttonsGameRecord[buttons.length / 2 - 1][buttons.length / 2].getButton().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        buttonsGameRecord[buttons.length / 2][buttons.length / 2 - 1].getButton().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        buttonsGameRecord[buttons.length / 2 - 1][buttons.length / 2 - 1].getButton().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        for (int i = 0; i < gameRecord.length; i++) {
            for (int j = 0; j < gameRecord[i].length; j++) {
                buttonsGameRecord[i][j].getButton().setContentAreaFilled(false);
                buttonsGameRecord[i][j].getButton().setFont(new java.awt.Font("Lucida Grande", 1, 18));
                buttonsGameRecord[i][j].getButton().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
                buttonsGameRecord[i][j].getButton().setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                buttonsGameRecord[i][j].getButton().setPreferredSize(new Dimension((int) (BUTTON_SIZE * 0.8), (int) (BUTTON_SIZE * 0.8)));
                buttonsGameRecord[i][j].setMouseListener(false);
            }
        }
    }

    public void closeDisplay() {
        frame.dispose();
        gameRecord = null;
        this.dispose();
    }

    public JFrame getGameRecordFrame() {
        return frame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuLateral = new javax.swing.JPanel();
        tabuleiro = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout menuLateralLayout = new javax.swing.GroupLayout(menuLateral);
        menuLateral.setLayout(menuLateralLayout);
        menuLateralLayout.setHorizontalGroup(
            menuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );
        menuLateralLayout.setVerticalGroup(
            menuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tabuleiroLayout = new javax.swing.GroupLayout(tabuleiro);
        tabuleiro.setLayout(tabuleiroLayout);
        tabuleiroLayout.setHorizontalGroup(
            tabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        tabuleiroLayout.setVerticalGroup(
            tabuleiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setOpaque(false);

        jMenu1.setText("Game Record");

        jMenuItem1.setText("Show");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabuleiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabuleiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        showGameRecord();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel menuLateral;
    private javax.swing.JPanel tabuleiro;
    // End of variables declaration//GEN-END:variables

}
