package com.packtpublishing.tddjava.ch03tictactoe;

import com.packtpublishing.tddjava.ch03tictactoe.mongo.TickTackToeBean;
import com.packtpublishing.tddjava.ch03tictactoe.mongo.TickTackToeCollection;

import java.net.UnknownHostException;

public class TicTacToe {

    // TODO: Add to book
    private int turn = 0;
    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    private char lastPlayer = '\0';
    private static final int SIZE = 3;
    // TODO: Add to book
    private TickTackToeCollection ticTacToeCollection;
    // TODO: Add to book
    protected TickTackToeCollection getTicTacToeCollection() {
        return ticTacToeCollection;
    }

    // TODO: Add to book
    public TicTacToe() throws UnknownHostException {
        new TicTacToe(new TickTackToeCollection());
    }
    // TODO: Add to book
    protected TicTacToe(TickTackToeCollection collection) {
        ticTacToeCollection = collection;
        ticTacToeCollection.drop();
    }

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
//        setBox(x, y, lastPlayer);
        // TODO: Add to book
//        setBox(new TickTackToeBean(1, x, y, lastPlayer));
        // TODO: Add to book
        setBox(new TickTackToeBean(++turn, x, y, lastPlayer));
        if (isWin(x, y)) {
            return lastPlayer + " is the winner";
        } else if (isDraw()) {
            return "The result is draw";
        } else {
            return "No winner";
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }

    // TODO: Add refactoring to TickTackToeBean argument
    private void setBox(TickTackToeBean bean) {
        if (board[bean.getX() - 1][bean.getY() - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[bean.getX() - 1][bean.getY() - 1] = lastPlayer;
            // TODO: Add to book
            getTicTacToeCollection().saveMove(bean);
        }
    }

    private boolean isWin(int x, int y) {
        int playerTotal = lastPlayer * SIZE;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical = diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
        }
        if (horizontal == playerTotal
                || vertical == playerTotal
                || diagonal1 == playerTotal
                || diagonal2 == playerTotal) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

}
