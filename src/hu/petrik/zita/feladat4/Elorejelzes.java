package hu.petrik.zita.feladat4;

public class Elorejelzes {
    private String szovEl;
    private int min,max;

    public Elorejelzes(String elo,String minmax){
        this.szovEl=elo;
        String[]st=minmax.split("/");
        this.min=Integer.parseInt(st[0]);
        this.max=Integer.parseInt(st[1]);
    }

    public String getSzovEl() {
        return szovEl;
    }

    public void setSzovEl(String szovEl) {
        this.szovEl = szovEl;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return szovEl+"\tmin: "+this.min+"\t\tmax: "+this.max;
    }
}
