package io.github.quellatalo.puzzle.sudoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.github.quellatalo.puzzle.sudoku.Sudoku.SPACE;
import static io.github.quellatalo.puzzle.sudoku.SudokuSolver.AND;
import static io.github.quellatalo.puzzle.sudoku.SudokuSolver.BASE_RULE_FILE;

public class SudokuBasicRules {

        private static final String NOT = " -";
        public static String basicRules() throws IOException {
            String rs;
            Path baseRulePath = Paths.get(BASE_RULE_FILE);
            if (Files.exists(baseRulePath)) {
                rs = Files.readString(baseRulePath);
            } else {
                StringBuilder sb = new StringBuilder();
                int i, j, k, l;
                // cell clauses - contain
                for (i = 1; i <= 9; i++) {
                    for (j = 1; j <= 9; j++) {
                        for (k = 1; k <= 9; k++) {
                            sb.append(i).append(j).append(k).append(SPACE);
                        }
                        sb.append(AND);
                    }
                }

                // cell clauses - not repeat
                for (i = 1; i <= 9; i++) {
                    for (j = 1; j <= 9; j++) {
                        for (k = 1; k <= 8; k++) {
                            for (l = k + 1; l <= 9; l++) {
                                sb.append(NOT).append(i).append(j).append(k);
                                sb.append(NOT).append(i).append(j).append(l);
                                sb.append(AND);
                            }
                        }
                    }
                }

                // row clauses - contain
                for (i = 1; i <= 9; i++) {
                    for (j = 1; j <= 9; j++) {
                        for (k = 1; k <= 9; k++) {
                            sb.append(i).append(k).append(j).append(SPACE);
                        }
                        sb.append(AND);
                    }
                }

                // row clauses - not repeat
                for (i = 1; i <= 9; i++) {
                    for (j = 1; j <= 9; j++) {
                        for (k = 1; k <= 8; k++) {
                            for (l = k + 1; l <= 9; l++) {
                                sb.append(NOT).append(i).append(k).append(j);
                                sb.append(NOT).append(i).append(l).append(j).append(AND);
                            }
                        }
                    }
                }

                // column clauses - contain
                for (i = 1; i <= 9; i++) {
                    for (j = 1; j <= 9; j++) {
                        for (k = 1; k <= 9; k++) {
                            sb.append(k).append(i).append(j).append(SPACE);
                        }
                        sb.append(AND);
                    }
                }

                // column clauses - not repeat
                for (i = 1; i <= 9; i++) {
                    for (j = 1; j <= 9; j++) {
                        for (k = 1; k <= 8; k++) {
                            for (l = k + 1; l <= 9; l++) {
                                sb.append(NOT).append(k).append(i).append(j);
                                sb.append(NOT).append(l).append(i).append(j);
                                sb.append(AND);
                            }
                        }

                    }
                }

                // block clauses
                for (i = 1; i <= 9; i++) {
                    // top left block
                    for (j = 1; j <= 3; j++) {
                        for (k = 1; k <= 3; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                    // top block
                    for (j = 1; j <= 3; j++) {
                        for (k = 4; k <= 6; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                    // top right block
                    for (j = 1; j <= 3; j++) {
                        for (k = 7; k <= 9; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                    // left block
                    for (j = 4; j <= 6; j++) {
                        for (k = 1; k <= 3; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                    // center block
                    for (j = 4; j <= 6; j++) {
                        for (k = 4; k <= 6; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                    // right block
                    for (j = 4; j <= 6; j++) {
                        for (k = 7; k <= 9; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                    // bottom left block
                    for (j = 7; j <= 9; j++) {
                        for (k = 1; k <= 3; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                    // bottom block
                    for (j = 7; j <= 9; j++) {
                        for (k = 4; k <= 6; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                    // bottom right block
                    for (j = 7; j <= 9; j++) {
                        for (k = 7; k <= 9; k++) {
                            sb.append(j).append(k).append(i).append(SPACE);
                        }
                    }
                    sb.append(AND);
                }
                rs = sb.toString();
                try {
                    Files.writeString(baseRulePath,rs);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return rs;
        }

}
