package io.github.quellatalo.puzzle;

import org.sat4j.specs.IProblem;
import org.sat4j.specs.TimeoutException;

public interface IPuzzle {
    String SPACE = " ";

    void solveProblem(IProblem problem) throws TimeoutException;

    boolean isSolved();

    String display();
}
