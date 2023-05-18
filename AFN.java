import java.io.*;
import java.util.*;

public class AFN {

    List<String> transSublist;
    String[] simNoF, simF, trans;
    String inicial;



    public AFN(ArrayList<String> data){
        this.simNoF = data.get(0).split(",");
        this.simF = data.get(1).split(",");
        this.inicial = data.get(2);
        this.transSublist = data.subList(3, data.size() - 1);
        this.trans = transSublist.toArray(new String[transSublist.size()]);


        /*System.out.println("inicial "+inicial);
        System.out.println("finales "+Arrays.toString(this.simF));
        System.out.println("no finales "+Arrays.toString(this.simNoF));
        System.out.println("trans "+Arrays.toString(this.trans));*/
        
    }
}
