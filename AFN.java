import java.io.*;
import java.util.*;

public class AFN {

    ArrayList<String> trans_reduced = new ArrayList<>();
    List<String> transSublist;
    String[] simNoF, simF, trans;
    String inicial;
    int numNewEstados = 1;
    ArrayList<String> analizando = new ArrayList<>();
    ArrayList<String> newestados = new ArrayList<>();



    public AFN(ArrayList<String> data, String nombreArchivo){
        this.simNoF = data.get(0).split(",");
        this.simF = data.get(1).split(",");
        this.inicial = data.get(2);
        this.transSublist = data.subList(3, data.size() - 1);
        this.trans = transSublist.toArray(new String[transSublist.size()]);


        GLDreduced();

        try{
            
            FileWriter fileWriter = new FileWriter(nombreArchivo)



        } catch(Exception e){

        }

    }

    public void GLDreduced(){
        for(int transicion = 0; transicion <= this.trans.length - 1; transicion++){
            if(this.trans[transicion].length() - 1 > 4){
                this.trans_reduced.addAll(Arrays.asList(reduced((this.trans[transicion]).length() - 3, this.trans[transicion])));

            } else {
                this.trans_reduced.add(this.trans[transicion]);
            }
        }



        System.out.println("Reducido " + this.trans_reduced.toString());
    }

    public String[] reduced(int ciclos, String regla){
 
        this.newestados.add(Character.toString(regla.charAt(0)));

        for(int letra = 3; letra < regla.length() - 1; letra++){
            if(Arrays.asList(simF).contains(Character.toString(regla.charAt(letra + 1)))){
                
                String genEstados = Integer.toString(this.numNewEstados) + this.newestados.get(0);
                this.newestados.add(genEstados);

                String newState = this.newestados.get(letra - 3) + "->" + regla.charAt(letra) + this.newestados.get(letra - 2);
                this.analizando.add(newState);

            } else{
                String newState = this.newestados.get((letra - 3)) + "->" + regla.charAt(letra) + regla.charAt(letra + 1);
                this.analizando.add(newState);
            }

            
            this.numNewEstados++;
        }

        String[] reducido = this.analizando.toArray(new String[this.analizando.size()]);
        this.analizando.clear();
        this.newestados.clear();

        System.out.println(Arrays.toString(reducido));

        return reducido;
    }

}
