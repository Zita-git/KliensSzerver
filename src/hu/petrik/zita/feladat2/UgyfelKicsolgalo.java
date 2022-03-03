package hu.petrik.zita.feladat2;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UgyfelKicsolgalo implements Runnable{
    Socket kapcsolat;
    public UgyfelKicsolgalo(Socket kapcsolat){
        this.kapcsolat =kapcsolat;
    }

    @Override
    public void run() {
        try{
            DataInputStream ugyfeltol =new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek =new DataOutputStream(kapcsolat.getOutputStream());
            while(true) {
                int a= ugyfeltol.readInt();
                int b= ugyfeltol.readInt();
                int menu;
                do {
                    menu= ugyfeltol.readInt();
                    switch (menu){
                        case 1: ugyfelnek.writeUTF(kerulet(a,b)); break;
                        case 2: ugyfelnek.writeUTF(terulet(a,b)); break;
                        case 3: ugyfelnek.writeUTF(negyzet(a,b)); break;
                        case 4: ugyfelnek.writeUTF(atlo(a,b)); break;
                        case 5: ugyfelnek.writeUTF("Kilépés...\n");
                    }
                }while(menu!=5);
            }

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String negyzet(int a, int b) {
        return "A téglalap négyzet-e: "+( (a==b) ? "Igen" : "Nem" )+"\n";
    }

    private String atlo(int a, int b) {
        return "A téglalap átlója: "+Math.sqrt(Math.pow(a,2)+Math.pow(b,2))+"\n";
    }

    private String terulet(int a, int b) {
        return "A téglalap területe: "+(a*b)+"\n";
    }

    private String kerulet(int a, int b) {
        return "A téglalap kerülete: "+(2*(a+b))+"\n";
    }
}

