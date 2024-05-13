import java.util.ArrayList;
import java.util.List;

public class OverLapShapeCanvas implements ShapeCanvas{
    private List<Shape> shapes;
    private char[][] canvas;
    private int count=0;
    public OverLapShapeCanvas(int rows,int cols)
    {
        canvas=new char[rows][cols];
        shapes=new ArrayList<>();
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                canvas[i][j] = ' ';
            }
        }
    }
    @Override
    public boolean addShape(int x, int y, char pattern, int... params) {
        boolean isAdd=true;
        if (params.length==1)
        {
            Circle circle=new Circle(new Location(x, y),pattern,params[0]);
        }
        else
        {
            switch (params[2])
            {
                case 0:
                {
                    RightTriangle triangle=new RightTriangle(new Location(x, y),pattern,params[0],params[1],Direction.LEFT_UP );
                }
                break;
                case 1:
                {
                    RightTriangle triangle=new RightTriangle(new Location(x, y),pattern,params[0],params[1],Direction.LEFT_DOWN);
                }
                break;
                case 2:
                {
                    RightTriangle triangle=new RightTriangle(new Location(x, y),pattern,params[0],params[1],Direction.RIGHT_UP);
                }
                break;
                case 3:
                {
                    RightTriangle triangle=new RightTriangle(new Location(x, y),pattern,params[0],params[1],Direction.RIGHT_DOWN);
                }
                break;
            }
        }
        return isAdd;
    }

    @Override
    public int getSpaceGridCount() {
        return count;
    }

    @Override
    public int getShapeCount() {
        return 0;
    }

    @Override
    public List<Shape> getShapesByArea() {
        return null;
    }

    @Override
    public List<Shape> getShapesByLocation() {
        return null;
    }

    @Override
    public char[][] getCanvas() {
        return new char[0][];
    }
}
