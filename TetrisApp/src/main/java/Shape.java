
import java.util.Random;

public enum Shape {
    I(new int[][] { {0,1}, {1,1}, {2,1}, {3,1} }),
    T(new int[][] { {1,0}, {0,1}, {1,1}, {2,1} }),
    S(new int[][] { {1,0}, {2,0}, {0,1}, {1,1} }),
    Z(new int[][] { {0,0}, {1,0}, {1,1}, {2,1} }),
    L(new int[][] { {0,0}, {0,1}, {1,1}, {2,1} }),
    O(new int[][] { {0,0}, {0,1}, {1,0}, {1,1} }),
    J(new int[][] { {2,0}, {0,1}, {1,1}, {2,1} });
    
    int[][] coords;
    
    Shape(int[][] coords) {
        this.coords = coords;
    }
    
    public static Shape getRandomShape() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
    
}
