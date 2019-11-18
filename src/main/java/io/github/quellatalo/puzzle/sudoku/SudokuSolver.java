package io.github.quellatalo.puzzle.sudoku;

import io.github.quellatalo.puzzle.IPuzzleSolver;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static io.github.quellatalo.puzzle.sudoku.Sudoku.SPACE;

public class SudokuSolver implements IPuzzleSolver {
    private static String basicRules = null;
    private static int V = 9 * 9 * 9;
    private static int C = 9072;

    private Reader reader;
    private Sudoku sudoku;

    public SudokuSolver() {
        ISolver solver = SolverFactory.newDefault();
        reader = new DimacsReader(solver);
        sudoku = new Sudoku();
    }

    @Override
    public void load(String inputFile, String cnfConvertedFile) throws IOException, ParseFormatException, ContradictionException, TimeoutException {
        StringBuilder sb = new StringBuilder();
        int count = C;
        String[] lines = Files.lines(Paths.get(inputFile)).toArray(String[]::new);
        for (int r = 0; r < 9; r++) {
            char[] line = lines[r].toCharArray();
            for (int c = 0; c < 9; c++) {
                if (line[c] > '0' && line[c] <= '9') {
                    sb.append(r + 1).append(c + 1).append(line[c]).append(AND);
                    count++;
                }
            }
        }
        sb.insert(0, CNF + V + SPACE + count + System.lineSeparator());
        if (basicRules == null) {
            basicRules = new Scanner(getClass().getResourceAsStream(BASE_RULE_FILE)).useDelimiter("\\A").next();
        }
        sb.append(basicRules);
        Files.writeString(Paths.get(cnfConvertedFile), sb.toString());
        sudoku.solveProblem(reader.parseInstance(cnfConvertedFile));
    }

    @Override
    public String result() {
        return sudoku.isSolved() ? sudoku.display() : UNSAT;
    }
}
