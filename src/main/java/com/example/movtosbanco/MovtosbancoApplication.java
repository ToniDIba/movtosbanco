package com.example.movtosbanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*

Lee un par de archivos CSV con los movimientos bancarios para insertarlos en una tabla de Oracle.
Luego permite buscar datos por varias columnas (que devuelve en un arraylist )

 */



@SpringBootApplication
public class MovtosbancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovtosbancoApplication.class, args);
	}

}
