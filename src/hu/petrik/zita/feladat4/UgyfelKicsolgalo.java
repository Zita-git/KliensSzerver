package hu.petrik.zita.feladat4;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UgyfelKicsolgalo implements Runnable{
    Socket kapcsolat;
    boolean vane =false;

    String valasztott;
    String sz = "Nincs ilyen megye az adatok között";
    public HashMap<String, Idojaras> elorejelzesek;
    public UgyfelKicsolgalo(Socket kapcsolat){
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
                    if (menu == 4 || menu == 5 || menu == 6) {
                        m=ugyfeltol.readUTF();
                    }
                    switch (menu){
                        case 1: ugyfelnek.writeUTF(kiirat()); break;
                        case 2: ugyfelnek.writeUTF(maiMind()); break;
                        case 3: ugyfelnek.writeUTF(holnapiMind()); break;

                        case 4: ugyfelnek.writeUTF(egyMind(m));break;
                        case 5: ugyfelnek.writeUTF(egyMai(m)); break;
                        case 6: ugyfelnek.writeUTF(egyHolnapi(m)); break;

                        case 7: ugyfelnek.writeUTF("Kilépés...\n");
                    }
                }while(menu!=7);
            }

        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private String egyHolnapi(String m) {
        for (Idojaras elorejelzes : elorejelzesek.values()) {
            if (elorejelzes.getMegye().equals(m)) {
                valasztott=(elorejelzes.getMegye()+
                        ":\n\t"+elorejelzes.getHolnapi()+;
                return sz = valasztott;
            }else {
                return sz = "Nincs ilyen megye az adatok között";
            }
        }
    }
    private String egyMai(String m) {
        for (Idojaras elorejelzes : elorejelzesek.values()) {
            if (elorejelzes.getMegye().equals(m)) {
                valasztott=(elorejelzes.getMegye()+
                        ":\n\t"+elorejelzes.getMai()+;
                return sz = valasztott;
            }else {
                return sz = "Nincs ilyen megye az adatok között";
            }
        }
    }

    private String egyMind(String m) {
        for (Idojaras elorejelzes : elorejelzesek.values()) {
            if (elorejelzes.getMegye().equals(m)) {
                valasztott=(elorejelzes.getMegye()+
                        ":\n\t"+elorejelzes.getMai()+
                        "\n\t"+elorejelzes.getHolnapi()+"\n");
                return sz = valasztott;
            }else {
                return sz = "Nincs ilyen megye az adatok között";
            }
        }
    }



    private String holnapiMind() {
        String s="";
        for (Idojaras elorejelzes: elorejelzesek.values()){
            s+=(elorejelzes.getMegye()+":\n\t"+elorejelzes.getHolnapi()+"\n");
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

    private String kiirat() {

        String s="";
        for (Map.Entry<String,Idojaras>entry:elorejelzesek.entrySet()){
            s+=(entry.getValue());
        }
        return s;
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
}

