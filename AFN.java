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
    ArrayList<String> Keynewestados = new ArrayList<>();
    ArrayList<String> todoslosestados = new ArrayList<>();
    ArrayList<String> estadosAgrupados = new ArrayList<>();
    ArrayList<String> reglaTransFinal = new ArrayList<>();



    public AFN(ArrayList<String> data, String nombreArchivo){
        this.simNoF = data.get(0).split(",");
        this.simF = data.get(1).split(",");
        this.inicial = data.get(2);
        this.transSublist = data.subList(3, data.size());
        this.trans = transSublist.toArray(new String[transSublist.size()]);




        GLDreduced(data.get(1));

        //Try y catch cuando se inice la escritura del archivo
        try{
            
            FileWriter fileWriter = new FileWriter(nombreArchivo);



        } catch(Exception e){

        }

    }

    public void GLDreduced(String noF){
        //verificacion por estado para reduccion
        for(int transicion = 0; transicion <= this.trans.length - 1; transicion++){
            if(this.trans[transicion].length() - 1 > 4){
                this.trans_reduced.addAll(Arrays.asList(reduced((this.trans[transicion]).length() - 3, this.trans[transicion])));

            } else if((Arrays.asList(this.simF).contains(Character.toString((this.trans[transicion]).charAt(this.trans[transicion].length()-1)))) && (Arrays.asList(this.simF).contains(Character.toString((this.trans[transicion]).charAt(this.trans[transicion].length()-2))))){
                this.trans_reduced.addAll(Arrays.asList(reduced((this.trans[transicion]).length() - 3, this.trans[transicion])));
            } else {
                String restrito = Character.toString(this.trans[transicion].charAt(0)) + this.trans[transicion].substring(1, this.trans[transicion].length());
                this.trans_reduced.add(restrito);

                if(!(this.Keynewestados.contains(Character.toString(this.trans[transicion].charAt(0))))){
                    this.Keynewestados.add(Character.toString(this.trans[transicion].charAt(0)));
                }
            }
        }

        /*
        
        COdigo fallido
        
        String estado_analizando = this.trans_reduced.get(0).substring(0, 3);
        String agrupado = "";
        for(int estado = 0; estado < this.trans_reduced.size(); estado++){
            if(estado_analizando.equals((this.trans_reduced.get(estado)).substring(0, 3))){
                agrupado = agrupado + (this.trans_reduced.get(estado)).substring(4, this.trans_reduced.get(estado).length()) + ";";

            } else {
                this.estadosAgrupados.add(agrupado.substring(0, agrupado.length() - 1));
                estado_analizando = this.trans_reduced.get(estado).substring(0, 3);
                agrupado = "";
                agrupado = agrupado + (this.trans_reduced.get(estado)).substring(4, this.trans_reduced.get(estado).length()) + ";";
            }
            
        }

        this.estadosAgrupados.add(agrupado.substring(0, agrupado.length() - 1));*/


        //hashmap para poder llamar desde el estado como W y remplazarlo a 1 por ejemplo
        HashMap<String,String> reglaTransiciones = new HashMap<>();
        for(int posiciones = 1; posiciones <= this.Keynewestados.size(); posiciones++){
            reglaTransiciones.put(this.Keynewestados.get(posiciones - 1), String.valueOf(posiciones));
        }

        //Lamda en donde se pasa asi mismo ya que hay pocos casos de lamda en los ejemplos; para inicar pruebas puede funcionar pero luuego toca cambiar
        String transLamda = "";
        for(int i = 0; i <= reglaTransiciones.size() + 1; i++){
            transLamda = transLamda + i + ",";
        }


        this.reglaTransFinal.add(transLamda.substring(0, transLamda.length()));


        //intente colocar numeros en ves de estados pero al parecer falla en la validacion
        for(int iteracion = 0; iteracion < this.trans_reduced.size(); iteracion++){
            for(int inteEstados = 0; inteEstados < this.todoslosestados.size(); inteEstados++){
                String estadoanalizando = this.todoslosestados.get(inteEstados);

                if(this.trans_reduced.get(iteracion).contains(estadoanalizando)){
                    String nuevoEstado = this.trans_reduced.get(iteracion).replace(estadoanalizando, reglaTransiciones.get(estadoanalizando));
                    this.trans_reduced.set(iteracion, nuevoEstado);
                    iteracion = 0;
                }
            }
        }

        

        //Prints de prueba sin importancia

        System.out.println("Estado con numero para transicion " + reglaTransiciones.toString());
        System.out.println("estados " + this.Keynewestados.toString());
        //System.out.println("kk " + this.reglaTransFinal.toString());
        System.out.println("trans_reduced " + this.trans_reduced.toString());
    }

    public String[] reduced(int ciclos, String regla){

        this.newestados.add(Character.toString(regla.charAt(0)));

        for(int letra = 3; letra < regla.length() - 1; letra++){
            //Funcion en donde reduce las tenciciones a GLD en donde verifica cada letra en busqueda del padron (F)(F)(noF)  F= simbolo final y noF= simbolo no final
            if((Arrays.asList(this.simF).contains(Character.toString(regla.charAt(regla.length()-1)))) && (Arrays.asList(this.simF).contains(Character.toString(regla.charAt(regla.length()-2))))){
                String genEstados = Integer.toString(this.numNewEstados) + this.newestados.get(0);
                this.newestados.add(genEstados);

                String newState = this.newestados.get(letra - 3) + "->" + regla.charAt(letra) + this.newestados.get(letra - 2);
                this.analizando.add(newState);

                this.numNewEstados++;

                String newState2= this.newestados.get((letra - 2)) + "->" + regla.charAt(letra);
                this.analizando.add(newState2);

                
            } else{
                //otro caso de solo (F)(F) F= simbolo final
                if(Arrays.asList(simF).contains(Character.toString(regla.charAt(letra + 1)))){
                    
                    String genEstados = Integer.toString(this.numNewEstados) + this.newestados.get(0);
                    this.newestados.add(genEstados);

                    String newState = this.newestados.get(letra - 3) + "->" + regla.charAt(letra) + this.newestados.get(letra - 2);
                    this.analizando.add(newState);

                } else{
                    String newState = this.newestados.get((letra - 3)) + "->" + regla.charAt(letra) + regla.charAt(letra + 1);
                    this.analizando.add(newState);
                }
            }

            this.numNewEstados++;
        }

        String[] reducido = this.analizando.toArray(new String[this.analizando.size()]);
        this.Keynewestados.addAll(this.newestados);
        this.analizando.clear();
        this.newestados.clear();

        return reducido;
    }

}
