/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import display.Display;
import players.HumanPlayer;
import players.RandomPlayer;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Iuri
 */
public class Jogar {

    static int X = 1, O = -1, validar;
    public static Display displayOtello;
    public static String vencedor;

    public static void imprime(String string) {
        if (!Display.ENABLE && Torneio.JOGO == 2) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) throws Exception {
        Casa casa = null;
        AbstractJogo jogo = null;
        AbstractPlayer player = null;
        AbstractPlayer player2 = null;
        List<Jogada> jogadas;

        if (Display.ENABLE && Torneio.JOGO == 2) {
            displayOtello = new Display();
        }
        System.out.println("args.length " + args.length);
        switch (args.length) {
            case 0:
                jogo = new JogoOthello();
                //TODO: Define a classe utilizada pelo player 1:
                player = new players.RandomPlayer(2);
                player.setMarcaTabuleiro(X);
                player.setMarcaTabuleiroOponente(O);
                player.setJogo(jogo);
                //TODO: Define a classe utilizada pelo player 2:
                if (displayOtello != null) {
                    player2 = new players.HumanPlayerDisplay(2);
                } else {
                    player2 = new players.HumanPlayer(2);
                }
                player2.setMarcaTabuleiro(O);
                player2.setMarcaTabuleiroOponente(X);
                player2.setJogo(jogo);
                break;
            case 1:
                jogo = new JogoOthello();
                //TODO: Define a classe utilizada pelo player 1:
                player = (AbstractPlayer) Class.forName(args[0]).getConstructor(int.class).newInstance(2);
                player.setMarcaTabuleiro(X);
                player.setMarcaTabuleiroOponente(O);
                player.setJogo(jogo);
                //TODO: Define a classe utilizada pelo player 2:
                if (displayOtello != null && Torneio.JOGO == 2) {
                    player2 = new players.HumanPlayerDisplay(2);
                } else {
                    player2 = new players.HumanPlayer(2);
                }
                player2.setMarcaTabuleiro(O);
                player2.setMarcaTabuleiroOponente(X);
                player2.setJogo(jogo);
                break;
            case 2:
                jogo = new JogoOthello();
                //TODO: Define a classe utilizada pelo player 1:
                player = (AbstractPlayer) Class.forName(args[0]).getConstructor(int.class).newInstance(2);
                player.setMarcaTabuleiro(X);
                player.setMarcaTabuleiroOponente(O);
                player.setJogo(jogo);
                //TODO: Define a classe utilizada pelo player 2:
                player2 = (AbstractPlayer) Class.forName(args[1]).getConstructor(int.class).newInstance(2);
                player2.setMarcaTabuleiro(O);
                player2.setMarcaTabuleiroOponente(X);
                player2.setJogo(jogo);
                break;
            default:
                break;
        }
        if (Display.ENABLE && Torneio.JOGO == 2) {
            displayOtello.setPlayersName(player, player2);
        }

        Tabuleiro t = new Tabuleiro(jogo);
        if (Display.ENABLE && Torneio.JOGO == 2) {
            displayOtello.atualizarDisplay(t.getTabuleiro());
        }
        t.imprimir();

        while (true) {
            int resultado = 0;
//            jogada player 1
            if (Display.ENABLE && Torneio.JOGO == 2) {
                displayOtello.setPlayerTurn(1);
            }
            if (Display.ENABLE && Torneio.JOGO == 2) {
                displayOtello.jogadasValidas(jogo.getJogadasValidas(t.getTabuleiro(), 1), player);
            }
            jogadas = jogo.getJogadasValidas(t.getTabuleiro(), player.getMinhaMarcaTabuleiro());
            if (jogadas.size() > 0) {
                casa = player.jogar(t.getTabuleiro());
                validar = jogo.validar_jogada(t.getTabuleiro(), casa, player);
            } else {
                validar = jogo.validar_jogada(t.getTabuleiro(), new Casa(-1, -1), player);
            }
            if (validar == 0) {
                t.setTabuleiro(jogo.efetuar_jogada(t.getTabuleiro(), casa, player));
                if (Display.ENABLE && Torneio.JOGO == 2) {
                    displayOtello.atualizarDisplay(t.getTabuleiro(), casa, player);
                }
            } else if (validar == -1 || validar == -2) {
                imprime("player1 WO: Jogada em casa invalida");
                break;
            }

            imprime("Jogada do player1");
            t.imprimir();

//            verifica se o player 1 venceu
            resultado = jogo.teste_terminal(t.getTabuleiro(), player.getMarcaTabuleiro());
            if (resultado != 0) {
                if (resultado == 1) {
                    if (Display.ENABLE && Torneio.JOGO == 2) {
                        displayOtello.setGanhador(1);
                    }
                    vencedor = player.getClass().toString();
                    imprime("Player 1 Ganhou");
                } else {
                    if (Display.ENABLE && Torneio.JOGO == 2) {
                        displayOtello.setGanhador(0);
                    }
                    vencedor = "empate";
                    imprime("Empate");
                }
                break;
            }

//            jogada player 2
            if (Display.ENABLE && Torneio.JOGO == 2) {
                displayOtello.setPlayerTurn(-1);
            }
            if (Display.ENABLE && Torneio.JOGO == 2) {
                displayOtello.jogadasValidas(jogo.getJogadasValidas(t.getTabuleiro(), -1), player2);
            }
            jogadas = jogo.getJogadasValidas(t.getTabuleiro(), player2.getMinhaMarcaTabuleiro());
            if (jogadas.size() > 0) {
                casa = player2.jogar(t.getTabuleiro());
                validar = jogo.validar_jogada(t.getTabuleiro(), casa, player2);
            } else {
                validar = jogo.validar_jogada(t.getTabuleiro(), new Casa(-1, -1), player2);
            }
            if (validar == 0) {
                t.setTabuleiro(jogo.efetuar_jogada(t.getTabuleiro(), casa, player2));
                if (Display.ENABLE && Torneio.JOGO == 2) {
                    displayOtello.atualizarDisplay(t.getTabuleiro(), casa, player2);
                }
            } else if (validar == -1 || validar == -2) {
                imprime("player2 WO: Jogada em casa invalida");
                break;
            }
            imprime("Jogada do player2");
            t.imprimir();

//            verifica se o player 2 venceu
            resultado = jogo.teste_terminal(t.getTabuleiro(), player2.getMarcaTabuleiro());
            if (resultado != 0) {
                if (resultado == 1) {
                    if (Display.ENABLE && Torneio.JOGO == 2) {
                        displayOtello.setGanhador(-1);
                    }
                    vencedor = player2.getClass().toString();
                    imprime("Player 2 ganhou");
                } else {
                    if (Display.ENABLE && Torneio.JOGO == 2) {
                        displayOtello.setGanhador(0);
                    }
                    vencedor = "empate";
                    imprime("Empate");
                }
                break;
            }

        }

    }

    public static JFrame getGameRecordFrame() {
        return displayOtello.getGameRecordFrame();
    }

    public static void showGameRecord() {
        displayOtello.showGameRecord();
    }

    public static void closeDisplay() {
        displayOtello.closeDisplay();
    }
}
