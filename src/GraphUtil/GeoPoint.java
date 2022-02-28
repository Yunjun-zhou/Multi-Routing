package GraphUtil;

public class GeoPoint {
    private Double x, y;
    private int ID;

    //设置三种构造函数
    public GeoPoint() {
        this.ID = -1;
        this.x = 0.0;
        this.y = 0.0;
    }

    public GeoPoint(int ID) {
        this.ID = ID;
        this.x = 0.0;
        this.y = 0.0;
    }

    public GeoPoint(int ID, Double x, Double y){
        this.ID = ID;
        this.x = x;
        this.y = y;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }

    public void setX(Double x){
        this.x = x;
    }

    public Double getX(){
        return this.x;
    }

    public void setY(Double y){
        this.y = y;
    }

    public Double getY(){
        return this.y;
    }
}
