package io.github.quellatalo.puzzle;

import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

import java.io.IOException;

public interface IPuzzleSolver {
    String UNSAT = "Unsatisfiable";
    String BASE_RULE_FILE = "sudoku.txt";
    String AND = " 0 ";
    String CNF = "p cnf ";
    int V = 9 * 9 * 9;
    int C = 9072;

    String result();
    void load(String input, String output) throws IOException, ParseFormatException, ContradictionException, TimeoutException;
}
