package com.example.movtosbanco.controller;

import com.example.movtosbanco.ctasbcomdl.MovtosCtaBanco;
import com.example.movtosbanco.exception.NotFoundExceptionToni;
import com.example.movtosbanco.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



//http://localhost:8085/movtosbanco/insertarmvto

/* El "id" ya se encarga hibernate de asignarle el valor correcto (autoIncremental)
{
"tipocta" : "P",
"fecha" : "2022-01-31",
"concepto" : "concepto7",
"importe" : -91.45,
"suministro" : "false",
"tiposumin" : "null",
"categoria" : "alim",
"observac" : "obser7",
"comenta" : "comenta7"
}
 */



@RestController
@RequestMapping("/movtosbanco")
public class MovtosController {

    @Autowired
    UsuarioServices usuarioServices;

    @PostMapping(value = "/insertarmvto")
    public MovtosCtaBanco postBody(@RequestBody MovtosCtaBanco mvto) {
        return this.usuarioServices.insertarFila(mvto);
    }

    //http://localhost:8085/movtosbanco/buscaporconcepto?concepto=concepto9
    @GetMapping(value = "/buscaporconcepto")
    public ArrayList<MovtosCtaBanco> listaConceptos(@RequestParam String concepto) {
        List mal = new ArrayList();
        mal = this.usuarioServices.milistaConceptos(concepto); // Hace un "...repo.findByConcepto(concepto);"

        if(mal.size() == 0) throw new RuntimeException ("No se encuentra: " + concepto );
        return (ArrayList<MovtosCtaBanco>)  mal;
    }



    //------------ codigo original ----------------------------------------------------------------------------------
    // http://localhost:8085/movtosbanco/buscaporid?id=12
    //@GetMapping(value = "/buscaporid")
    //public MovtosCtaBanco unIdSolamente(@RequestParam int id) {
    //    return this.usuarioServices.buscaPorId(id);
    // }
    //------------------------------------------------------------------------------------------------------------------


    // http://localhost:8085/movtosbanco/buscaporid?id=12
    @GetMapping(value = "/buscaporid")
    public MovtosCtaBanco unIdSolamente(@RequestParam int id) throws Exception
    {

        MovtosCtaBanco movto  = null;

        try {
            movto = this.usuarioServices.buscaPorId(id);
        }
        catch(Exception e) {
            throw new NotFoundExceptionToni("No encuentro id: " + id);
        }


        //MovtosCtaBanco movto = this.usuarioServices.buscaPorId(id).orElseThrow(() -> new NotFoundExceptionToni("No encuentro id: " + id));
        return movto;
     }













//  Desde aquí cargo los archvos con registros CSV en tabla,por ejemplo:
//  S;15/12/2021;Càrrec comercializadora regulada, gas power, s.a;-1,88;N 2021348000629496 COMERCIALIZADORA REGULADA, GAS POWER, SA

//  http://localhost:8085/movtosbanco/C:\Temporal\gas.csv
    @RequestMapping( "/{archivo}/**" )
    public String  foo( @PathVariable String archivo, HttpServletRequest request ) throws ParseException, IOException {

        //Path del archivo a cargar
        String urlTail = new AntPathMatcher().extractPathWithinPattern( "/{archivo}/**", request.getRequestURI());

        System.out.println("Espera un momento...");
        List<Object> arrayListObjetos = new ArrayList<Object>();
        arrayListObjetos = Collections.singletonList(this.usuarioServices.cargaArchivo(urlTail, (ArrayList<Object>) arrayListObjetos));

        //En mi lista de objetos, como primer objeto, tengo un ArrayList con las filas que insertaré en tabla
        List<Object> arrayListRecuperado = (ArrayList) arrayListObjetos.get(0);

        int longitudLista = arrayListRecuperado.size();

        String resultado = (String) arrayListRecuperado.get(longitudLista -1);
        if(resultado.equals("Todo correcto")) {

            for(int i = 0; i <= longitudLista - 2; i++) {

                MovtosCtaBanco mcb = (MovtosCtaBanco) arrayListRecuperado.get(i);
                usuarioServices.insertarFila(mcb);
            }
        }

        return resultado;
    }

}