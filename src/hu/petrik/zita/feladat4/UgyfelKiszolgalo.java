package hu.petrik.zita.feladat4;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class UgyfelKiszolgalo implements Runnable{
    Socket kapcsolat;
    public HashMap<String, Idojaras> elorejelzesek;
    public UgyfelKiszolgalo(Socket kapcsolat){
        this.kapcsolat =kapcsolat;
        elorejelzesek=new HashMap<>();
        Beolvas();
    }
    @Override
    public void run(){
        try{
            DataInputStream ugyfeltol =new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek =new DataOutputStream(kapcsolat.getOutputStream());

            while(true) {
                int menu;
                String m;
                do {
                    menu= ugyfeltol.readInt();
                    switch (menu){
                        case 1: ugyfelnek.writeUTF(kiirat()); break;
                        case 2: ugyfelnek.writeUTF(maiMind()); break;
                        case 3: ugyfelnek.writeUTF(holnapiMind()); break;


                        case 4: ugyfelnek.writeUTF(egyMind(m=ugyfeltol.readUTF())); break;
                        case 5: ugyfelnek.writeUTF(egyMai(m=ugyfeltol.readUTF())); break;
                        case 6: ugyfelnek.writeUTF(egyHolnapi(m=ugyfeltol.readUTF())); break;

                        case 7: ugyfelnek.writeUTF("Kilépés...\n");
                    }
                }while(menu!=7);
            }

        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public void Beolvas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            br.readLine();
            String  sor = br.readLine();
            while(sor!=null){
                Idojaras i = new Idojaras(sor);
                String megye =i.getMegye();
                elorejelzesek.put(megye,i);
                sor=br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String kiirat() {

        String s="";
        for (Map.Entry<String,Idojaras>entry:elorejelzesek.entrySet()){
            s+=(entry.getValue());
        }
        return s;
    }

    private String maiMind() {
        String s="";
        for (Idojaras elorejelzes: elorejelzesek.values()){
            s+=(elorejelzes.getMegye()+":\n\t"+elorejelzes.getMai()+"\n");
        }
        return s;
    }

    private String holnapiMind() {
        String s="";
        for (Idojaras elorejelzes: elorejelzesek.values()){
            s+=(elorejelzes.getMegye()+":\n\t"+elorejelzes.getHolnapi()+"\n");
        }
        return s;
    }


    private String egyMind(String m) {
        String s="ajj4";
        for (Idojaras elorejelzes : elorejelzesek.values()){
            if (elorejelzes.getMegye().equals(m)) {
                s = (elorejelzes.getMegye() +
                        ":\n\t" + elorejelzes.getMai() +
                        "\n\t" + elorejelzes.getHolnapi() + "\n");
            } else {
               s= "Nincs ilyen megye az adatok között";
            }
        }
        return s;
    }

    private String egyMai(String m) {
        String s="ajj5";
        for (Idojaras elorejelzes : elorejelzesek.values()) {
            if (elorejelzes.getMegye().equals(m)) {
                s = (elorejelzes.getMegye()+
                        ":\n\t"+elorejelzes.getMai()+"\n");
            }else {
                s =  "Nincs ilyen megye az adatok között";
            }
        }
        return s;
    }

    private String egyHolnapi(String m) {
        String s="ajj6";
        for (Idojaras elorejelzes : elorejelzesek.values()) {
            if (elorejelzes.getMegye().equals(m)) {
                s = (elorejelzes.getMegye()+
                        ":\n\t"+elorejelzes.getHolnapi()+"\n");
            }else {
                s =  "Nincs ilyen megye az adatok között";
            }
        }
        return s;
    }




}

