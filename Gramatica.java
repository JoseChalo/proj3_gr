
import java.io.*;
import java.util.*;


public class Gramatica{

	static ArrayList<String> data = new ArrayList<>();
	static ArrayList<String> evaluar = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		try{
			if(args[1].equals("-check")){
				//String pathGr = args[0];
				//String selectorOp = args[1];
				//String nomSalida = args[2];
				//String pathCuerdas = args[3];

				FileReader lectorEv = new FileReader(args[3]);
				BufferedReader textEv = new BufferedReader(lectorEv);
	
				String linedataEv = textEv.readLine();
				while(linedataEv != null){
					evaluar.add(linedataEv);
					linedataEv = textEv.readLine();
				}

				textEv.close();			
			}

			FileReader lector = new FileReader(args[0]);
			BufferedReader text = new BufferedReader(lector);

			String linedata = text.readLine();
			while(linedata != null){
				data.add(linedata);
				linedata = text.readLine();
			}

			text.close();

			AFN afn = new AFN(data);

		} catch(Exception e){
			System.out.println("Error al leer el archivo revise las banderas ingresadas");
		}
	}
}