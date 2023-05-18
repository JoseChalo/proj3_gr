import java.io.*;
import java.util.*;

public class AFN {

    ArrayList<String> trans_reduced = new ArrayList<>();
    List<String> transSublist;
    String[] simNoF, simF, trans;
    String inicial;
    int numNewEstados = 1;
    char analizando;



    public AFN(ArrayList<String> data){
        this.simNoF = data.get(0).split(",");
        this.simF = data.get(1).split(",");
        this.inicial = data.get(2);
        this.transSublist = data.subList(3, data.size() - 1);
        this.trans = transSublist.toArray(new String[transSublist.size()]);


        GLDreduced();
        /*System.out.println("inicial "+inicial);
        System.out.println("finales "+Arrays.toString(this.simF));
        System.out.println("no finales "+Arrays.toString(this.simNoF));*/
        System.out.println("trans " + Arrays.toString(this.trans));
        
    }

    public void GLDreduced(){
        for(int transicion = 0; transicion <= this.trans.length - 1; transicion++){
            if(this.trans[transicion].length() - 1 > 4){
                for(int letra = 3; letra < (this.trans[transicion]).length() - 1; letra++){
                    this.analizando = this.trans[transicion].charAt(0);


                    //Buen camino solo afinar, prueba para hacerlo 'java Gramatica tests/gramaticas/kk.gld hex.txt -afn hola.txt'
                    if(Arrays.asList(simF).contains(Character.toString(this.trans[transicion].charAt(letra + 1)))){
                        this.trans_reduced.add(this.analizando + "->" + this.trans[transicion].charAt(letra) + this.numNewEstados + "_" + this.analizando);
                        this.trans_reduced.add(this.numNewEstados + "_" + this.analizando + "->" + this.trans[transicion].charAt(letra + 1) + this.trans[transicion].charAt(letra + 2));
                        this.numNewEstados++;
                    }
                }
            } else {
                this.trans_reduced.add(this.trans[transicion]);
            }
        }



        System.out.println("Reducido " + this.trans_reduced.toString());
    }
}
