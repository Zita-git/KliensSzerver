package hu.petrik.zita.feladat4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {

    public static void main(String[] args) {
        try{
            Socket kapcsolat = new Socket("localhost",8080);
            DataInputStream szervertol= new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernek= new DataOutputStream(kapcsolat.getOutputStream());
            Scanner sc =new Scanner(System.in);
            while(true){
                int menu;
                String m;
                do {
                    System.out.println("\n\nMelyik menüpontot válassza:\n");
                    System.out.println("\t1: Minden adat kiírása" +
                            "\n\t2: Mai adatok kiírása" +
                            "\n\t3: Holnapi adatok kiírása" +
                            "\n\t4: Kiválasztott megye kiírása" +
                            "\n\t5: Kiválasztott megye mai adatainak kiírása" +
                            "\n\t6: Kiválasztott megye holnapi adatainak kiírása" +
                            "\n\t7: Kilépés");
                    menu=Integer.parseInt(sc.nextLine());

                    szervernek.writeInt(menu);
                    szervernek.flush();
                    if (menu == 4 || menu == 5 || menu == 6) {
                        System.out.println("Kérem a nevét, hogy melyik megyét keresed: ");
                        m = sc.nextLine();

                        szervernek.writeUTF(m);
                        szervernek.flush();
                    }


                    System.out.println(szervertol.readUTF());
                }while (menu != 7);
            }
        }catch(IOException ex){
            System.out.println(ex);
        }

    }
}
