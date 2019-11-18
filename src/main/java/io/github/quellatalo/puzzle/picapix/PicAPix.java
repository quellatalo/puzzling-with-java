package io.github.quellatalo.puzzle.picapix;

import io.github.quellatalo.puzzle.IPuzzle;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.TimeoutException;

import java.util.BitSet;

public class PicAPix implements IPuzzle {
    private static final char MARKED = 'X';
    private final int columnCount;
    private final int rowCount;
    private final BitSet data;
    private final int total;
    private boolean solved;

    public PicAPix(int columnCount, int rowCount) {
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        total = columnCount * rowCount;
        data = new BitSet(total);
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public BitSet getData() {
        return data;
    }

    @Override
    public void solveProblem(IProblem problem) throws TimeoutException {
        solved = false;
        if (problem.isSatisfiable()) {
            for (int i = 0; i < total; i++) {
                data.set(i, problem.model(i));
            }
            solved = true;
        }
    }

    @Override
    public boolean isSolved() {
        return solved;
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        int row = 0;
        int index = 0;
        while (index < total) {
            row++;
            for (; index < row * columnCount; index++) {
                sb.append(data.get(index) ? MARKED : SPACE);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
