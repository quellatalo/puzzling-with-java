package io.github.quellatalo.puzzle;

import io.github.quellatalo.puzzle.sudoku.SudokuSolver;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

import java.io.IOException;

public class PuzzleMain {
    public static void main(String[] args) {
        IPuzzleSolver sudokuSolver = new SudokuSolver();
        try {
            sudokuSolver.load("input.txt","cnf.txt");
            System.out.println(sudokuSolver.result());
        } catch (IOException | ParseFormatException | ContradictionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
