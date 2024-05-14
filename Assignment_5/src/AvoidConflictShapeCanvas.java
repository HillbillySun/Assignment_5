import java.util.ArrayList;
import java.util.List;

public class AvoidConflictShapeCanvas implements ShapeCanvas {
    private List<Shape> shapes;
    private char[][] canvas;

    private int count = 0;

    public AvoidConflictShapeCanvas(int rows, int cols) {
        canvas = new char[rows][cols];
        shapes = new ArrayList<>();
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                canvas[i][j] = ' ';
            }
        }
    }

    @Override
    public boolean addShape(int x, int y, char pattern, int... params) {
        boolean isAdd = false;
        if (x < canvas.length && y < canvas[0].length && x >= 0 && y >= 0) {
            isAdd = true;
            if (params.length == 1) {
                Circle circle = new Circle(new Location(x, y), pattern, params[0]);
                int[] center = new int[2];
                center[0] = x + circle.getRadius();
                center[1] = y + circle.getRadius();
                if ( center[1] > canvas[0].length || center[0] > canvas.length || circle.getRadius() > FourMin(center[0], center[1], canvas.length - center[0], canvas[0].length - center[1])) {
                    isAdd = false;
                } else {
                    int RowT = circle.location.getX();
                    int ColT = circle.location.getY();
                    FillLoop:
                    for (int i = 0; i < circle.getGrids().length; i++) {
                        for (int j = 0; j < circle.getGrids()[0].length; j++) {
                            if (canvas[i + RowT][j + ColT] == ' ' && circle.getGrids()[i][j]==pattern) {
                                canvas[i + RowT][j + ColT] = pattern;
                            } else if (canvas[i+RowT][j+ColT]!=' ' && circle.getGrids()[i][j]==pattern){
                                isAdd = false;
                                break FillLoop;
                            }
                        }
                    }
                    if (!isAdd) {
                        this.deleteAdd(pattern);
                    } else {
                        shapes.add(circle);
                    }
                }
            } else {
                RightTriangle rightTriangle;
                if (params[2] == 0) {
                    rightTriangle = new RightTriangle(new Location(x, y), pattern, params[0], params[1], Direction.LEFT_UP);
                    if (canvas.length - rightTriangle.location.getX() < rightTriangle.getHeight() || canvas[0].length - rightTriangle.location.getY() < rightTriangle.getWidth()) {
                        int rowT = rightTriangle.location.getX();
                        int colT = rightTriangle.location.getY();
                        FillLoop:
                        for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                            for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                                if (canvas[i + rowT][j + colT] == ' ' && rightTriangle.getGrids()[i][j] == pattern) {
                                    canvas[i + rowT][j + colT] = rightTriangle.getGrids()[i][j];
                                } else if (rightTriangle.getGrids()[i][j] == pattern && canvas[i + rowT][j + colT] != ' ') {
                                    isAdd = false;
                                    break FillLoop;
                                }
                            }
                        }
                        if (isAdd) {
                            shapes.add(rightTriangle);
                        } else {
                            this.deleteAdd(pattern);
                        }
                    }
                } else if (params[2] == 1) {
                    rightTriangle = new RightTriangle(new Location(x, y), pattern, params[0], params[1], Direction.LEFT_DOWN);
                    int[] Spoint = new int[2];
                    Spoint[0] = rightTriangle.getHeight() + rightTriangle.location.getX();
                    Spoint[1] = rightTriangle.location.getY();
                    if (Spoint[0] > canvas.length || rightTriangle.getWidth() > canvas[0].length - Spoint[1]) {
                        isAdd = false;
                    } else {
                        int rowT = rightTriangle.location.getX();
                        int colT = rightTriangle.location.getY();
                        FillLoop:
                        for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                            for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                                if (canvas[i + rowT][j + colT] == ' ' && rightTriangle.getGrids()[i][j] == pattern) {
                                    canvas[i + rowT][j + colT] = rightTriangle.getGrids()[i][j];
                                } else if (rightTriangle.getGrids()[i][j] == pattern && canvas[i + rowT][j + colT] != ' ') {
                                    isAdd = false;
                                    break FillLoop;
                                }
                            }
                        }
                        if (isAdd) {
                            shapes.add(rightTriangle);
                        } else {
                            this.deleteAdd(pattern);
                        }
                    }
                } else if (params[2] == 2) {
                    rightTriangle = new RightTriangle(new Location(x, y), pattern, params[0], params[1], Direction.RIGHT_UP);
                    int[] Spoint = new int[2];
                    Spoint[0] = rightTriangle.location.getX();
                    Spoint[1] = rightTriangle.location.getY() + rightTriangle.getWidth();
                    if (Spoint[1] > canvas[0].length || rightTriangle.getHeight() > canvas.length - Spoint[0]) {
                        isAdd = false;
                    } else {
                        int rowT = rightTriangle.location.getX();
                        int colT = rightTriangle.location.getY();
                        FillLoop:
                        for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                            for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                                if (canvas[i + rowT][j + colT] == ' ' && rightTriangle.getGrids()[i][j] == pattern) {
                                    canvas[i + rowT][j + colT] = rightTriangle.getGrids()[i][j];
                                } else if (rightTriangle.getGrids()[i][j] == pattern && canvas[i + rowT][j + colT] != ' ') {
                                    isAdd = false;
                                    break FillLoop;
                                }
                            }
                        }
                        if (isAdd) {
                            shapes.add(rightTriangle);
                        } else {
                            this.deleteAdd(pattern);
                        }
                    }
                } else if (params[2] == 3) {
                    rightTriangle = new RightTriangle(new Location(x, y), pattern, params[0], params[1], Direction.RIGHT_DOWN);
                    int[] Spoint = new int[2];
                    Spoint[0] = rightTriangle.getHeight() + rightTriangle.location.getX();
                    Spoint[1] = rightTriangle.getWidth() + rightTriangle.location.getY();
                    if (Spoint[0] > canvas.length || Spoint[1] > canvas[0].length) {
                        isAdd = false;
                    } else {
                        int rowT = rightTriangle.location.getX();
                        int colT = rightTriangle.location.getY();
                        FillLoop:
                        for (int i = 0; i < rightTriangle.getGrids().length; i++) {
                            for (int j = 0; j < rightTriangle.getGrids()[0].length; j++) {
                                if (canvas[i + rowT][j + colT] == ' ' && rightTriangle.getGrids()[i][j] == pattern) {
                                    canvas[i + rowT][j + colT] = rightTriangle.getGrids()[i][j];
                                } else if (rightTriangle.getGrids()[i][j] == pattern && canvas[i + rowT][j + colT] != ' ') {
                                    isAdd = false;
                                    break FillLoop;
                                }
                            }
                        }
                        if (isAdd) {
                            shapes.add(rightTriangle);
                        } else {
                            this.deleteAdd(pattern);
                        }
                    }
                }
            }
            if (isAdd) {
                count++;
            }
        }
        return isAdd;
    }

    @Override
    public int getSpaceGridCount() {
        int count = 0;
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                if (canvas[i][j] == ' ') {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int getShapeCount() {
        return count;
    }

    @Override
    public List<Shape> getShapesByArea() {
        for (int i = 0; i < shapes.size() - 1; i++) {
            for (int j = i; j < shapes.size() - i - 1; j++) {
                if (shapes.get(j + 1).area() < shapes.get(j).area()) {
                    Shape temp = shapes.get(j + 1);
                    shapes.set(j + 1, shapes.get(j));
                    shapes.set(j, temp);
                } else if (shapes.get(j + 1).area() == shapes.get(j).area() && shapes.get(j + 1).pattern < shapes.get(j).pattern) {
                    Shape temp = shapes.get(j + 1);
                    shapes.set(j + 1, shapes.get(j));
                    shapes.set(j, temp);
                }
            }
        }
        return shapes;
    }

    @Override
    public List<Shape> getShapesByLocation() {
        for (int i = 0; i < shapes.size(); i++) {
            for (int j = i; j < shapes.size() - i - 1; j++) {
                if (shapes.get(j + 1).location.getX() < shapes.get(j).location.getX()) {
                    Shape temp = shapes.get(j + 1);
                    shapes.set(j + 1, shapes.get(j));
                    shapes.set(j, temp);
                } else if (shapes.get(j + 1).location.getX() == shapes.get(j).location.getX() && shapes.get(j + 1).location.getY() < shapes.get(j).location.getY()) {
                    Shape temp = shapes.get(j + 1);
                    shapes.set(j + 1, shapes.get(j));
                    shapes.set(j, temp);
                } else if (shapes.get(j + 1).location.getX() == shapes.get(j).location.getX() && shapes.get(j + 1).location.getY() == shapes.get(j).location.getY() && shapes.get(j + 1).pattern < shapes.get(j).pattern) {
                    Shape temp = shapes.get(j + 1);
                    shapes.set(j + 1, shapes.get(j));
                    shapes.set(j, temp);
                }
            }
        }
        return shapes;
    }

    @Override
    public char[][] getCanvas() {
        return canvas;
    }

    public static int FourMin(int u, int v, int w, int x) {
        int min1 = Math.min(u, v);
        int min2 = Math.min(w, x);
        return Math.min(min1, min2);
    }

    public void deleteAdd(char pattern) {
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                if (canvas[i][j] == pattern) {
                    canvas[i][j] = ' ';
                }
            }
        }
    }
}