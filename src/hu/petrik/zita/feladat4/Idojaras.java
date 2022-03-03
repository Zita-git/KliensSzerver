package hu.petrik.zita.feladat4;

public class Idojaras {
    private String megye;
    private Elorejelzes mai;
    private Elorejelzes holnapi;

    public Idojaras(String sor){
        String[] adatok =sor.split("\\t+",-1);
        this.megye=adatok[0].trim();
        String e =adatok[1].trim();
        String minmax =adatok[2].trim();
        this.mai=new Elorejelzes(e,minmax);
        e =adatok[3].trim();
        minmax =adatok[4].trim();
        this.holnapi=new Elorejelzes(e,minmax);
    }

    public String getMegye() {
        return megye;
    }

    public void setMegye(String megye) {
        this.megye = megye;
    }

    public Elorejelzes getMai() {
        return mai;
    }

    public void setMai(Elorejelzes mai) {
        this.mai = mai;
    }

    public Elorejelzes getHolnapi() {
        return holnapi;
    }

    public void setHolnapi(Elorejelzes holnapi) {
        this.holnapi = holnapi;
    }

    @Override
    public String toString() {
        return "\n"+megye+":\n\tmai:\t" + mai +
                "\n\tholnapi: " + holnapi;
    }
}
