package io.github.quellatalo.puzzle.sudoku;

import io.github.quellatalo.puzzle.IPuzzle;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.TimeoutException;

public class Sudoku implements IPuzzle {
    private static final String BORDER ="+-------+-------+-------+"+System.lineSeparator();
    private static final String BORDER_C ="|";
    private int[][] matrix;
    private boolean solved;

    public Sudoku() {
        matrix = new int[9][9];
    }

    @Override
    public void solveProblem(IProblem problem) throws TimeoutException {
        solved = false;
        if(problem.isSatisfiable()){
            for (int i = 111; i < 1000; i++) {
                if (problem.model(i))
                    matrix[(i/100)-1][((i/10)%10)-1] = i%10;
            }
            solved = true;
        }
    }

    @Override
    public boolean isSolved() {
        return solved;
    }

    @Override
    public String display(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BORDER);
        byte r = 0;
        for(; r<3; r++){
            stringBuilder.append(displayRow(r));
        }
        stringBuilder.append(BORDER);
        for(; r<6; r++){
            stringBuilder.append(displayRow(r));
        }
        stringBuilder.append(BORDER);
        for(; r<9; r++){
            stringBuilder.append(displayRow(r));
        }
        stringBuilder.append(BORDER);
        return stringBuilder.toString();
    }
    private String displayRow(byte row){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BORDER_C).append(SPACE);
        byte c = 0;
        for(;c<3;c++){
            stringBuilder.append(matrix[row][c]).append(SPACE);
        }
        stringBuilder.append(BORDER_C).append(SPACE);
        for(;c<6;c++){
            stringBuilder.append(matrix[row][c]).append(SPACE);
        }
        stringBuilder.append(BORDER_C).append(SPACE);
        for(;c<9;c++){
            stringBuilder.append(matrix[row][c]).append(SPACE);
        }
        stringBuilder.append(BORDER_C).append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
