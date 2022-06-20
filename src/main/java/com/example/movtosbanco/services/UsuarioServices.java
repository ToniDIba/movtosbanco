package com.example.movtosbanco.services;

import com.example.movtosbanco.ctasbcomdl.MovtosCtaBanco;
import com.example.movtosbanco.repo.RepositorioMovtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;


@Service
public class UsuarioServices {

    public static boolean swTodoOk;

    @Autowired
    RepositorioMovtos repo;

    public MovtosCtaBanco insertarFila(MovtosCtaBanco mcb) { return this.repo.save(mcb); }

    public ArrayList<MovtosCtaBanco> milistaConceptos(String concepto) {
        return this.repo.findByConcepto(concepto);
    }

    public MovtosCtaBanco buscaPorId(int id) {
        return this.repo.findById(id).get();
    }

    public ArrayList<Object> cargaArchivo(String ruta, ArrayList<Object> listaObjectos) throws ParseException, IOException {

        listaObjectos = comprobarArchivoExiste(ruta, listaObjectos);
        if (!swTodoOk) return listaObjectos; //Oops! error, me vuelvo.

        //En cada fila del ArrayList devuelto, irá una fila que luego insertaré en tabla
        listaObjectos = leerArchivo(ruta, listaObjectos);
        listaObjectos.add("Todo correcto");
        return listaObjectos;

    }

    private ArrayList<Object> leerArchivo(String ruta, ArrayList<Object> listaObjectos) throws IOException {

        Path path = Paths.get(ruta);

        try
        {
            File file=new File(String.valueOf(path));
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String lineaLeida;
            while((lineaLeida=br.readLine())!=null)
            {
                System.out.println("linea: " + lineaLeida);
                listaObjectos = trataLinea(lineaLeida, listaObjectos);
            }

            fr.close();
        }
        catch(IOException | ParseException e)
        {
            e.printStackTrace();
        }

        return listaObjectos;

    }




    private ArrayList<Object> trataLinea(String algo, ArrayList<Object> listaObjectos) throws ParseException {

        //cada columna viene separada por ";", con "split" pongo cada una de ellas en una celda del array
        String[] arrOfStr = algo.split(";", 5);

        MovtosCtaBanco mcb = new MovtosCtaBanco();
        String tipoSuministro = "null";


        mcb.setTipocta(arrOfStr[0]);

        String sDate1 = arrOfStr[1];
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        mcb.setFecha(date1);

        mcb.setConcepto(arrOfStr[2]);

        String result = arrOfStr[3].replaceAll(",", ".");
        mcb.setImporte(Float.parseFloat(result));

        mcb.setSuministro(false);
        mcb.setCategoria("null");

        String concepto = arrOfStr[2].toLowerCase();
        String aux = null;

        if(concepto.contains("aigua")) { aux = "aigua";  mcb.setCategoria("sum"); mcb.setTiposumin(aux); mcb.setSuministro(true); }
        if(concepto.contains("jazz"))  { aux = "jazz";   mcb.setCategoria("sum"); mcb.setTiposumin(aux); mcb.setSuministro(true); }
        if(concepto.contains("endes")) { aux = "endesa"; mcb.setCategoria("sum"); mcb.setTiposumin(aux); mcb.setSuministro(true); }
        if(concepto.contains("gas"))   { aux = "gas";    mcb.setCategoria("sum"); mcb.setTiposumin(aux); mcb.setSuministro(true); }

        if(concepto.contains("mercadona")) { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("aldi"))      { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("market"))    { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("rostisser")) { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("sirena"))    { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("estefania")) { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("nocuinis"))  { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("guissona"))  { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("teikit"))    { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("kebab"))     { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("que bo que")){ mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("serlluis"))  { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("onpreu"))    { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("brasa"))     { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("supermerc")) { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }

        if(concepto.contains("armacia"))   { mcb.setCategoria("med"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("adeslas"))   { mcb.setCategoria("med"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("cia efect")) { mcb.setCategoria("col"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("colegio"))   { mcb.setCategoria("col"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("supermerc")) { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }
        if(concepto.contains("supermerc")) { mcb.setCategoria("ali"); mcb.setTiposumin("null"); mcb.setSuministro(false); }

        mcb.setObservac("null");
        mcb.setComenta("null");

        listaObjectos.add(mcb); //guardo en el array un objeto "MovtosCtaBanco" que luego insertaré en tabla

        return listaObjectos;


    }




    private ArrayList<Object> comprobarArchivoExiste(String ruta, ArrayList<Object> listaObjectos) {

        swTodoOk = true;

        if (!new File(ruta).exists()) {
            listaObjectos.add("No encuentro el archivo que quieres abrir: " + ruta);
            swTodoOk = false;
        }

        return listaObjectos;

    }


}
