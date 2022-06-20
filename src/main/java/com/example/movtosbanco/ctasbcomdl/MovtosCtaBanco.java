package com.example.movtosbanco.ctasbcomdl;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Table(name = "movtosctabanco") //OJO !!! Debe llamarse igual que en Oracle. En Oracle aparece
                                //dentro de Tables todo el nombre en mayúsculas pero puedes
                                //dejarlo aquí en minúsculas que funciona.
@Entity
@Data
public class MovtosCtaBanco {

    /*
    //Esto funciona con PostgreSql y otras BB.DD.: (autoincremento para la columna ID):

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true,nullable = false)



    ...pero no para Oracle. En Oracle funciona distinto y hay que hacerlo de otra forma.
    Para empezar, desde el mismo sitio en el que has creado la tabla con:

    CREATE TABLE movtosCtaBanco(
       id NUMBER,
       tipocta VARCHAR2(50) NOT NULL,
       fecha DATE NOT NULL,
       concepto VARCHAR2(50) NOT NULL,
       importe NUMBER(6,2),
       suministro NUMBER,
       tiposumin VARCHAR2(10) NULL,
       categoria VARCHAR2(10) NULL,
       observac VARCHAR2(30) NULL,
       comenta VARCHAR2(50) NULL );

       ... debes crear una "sequence", de esta forma: "create sequence oracle_seq;" (oracle_seq es un nombre inventado)

       Una vez hecho, JPA, desde tu programa Java, se "entera" con estas dos lineas: (ver linea 58 y siguientes)
       de cómo gestionar los "auto incrementos":

       @SequenceGenerator(name="ORACLE_SEQ",sequenceName="ORACLE_SEQ", allocationSize = 1)
       @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORACLE_SEQ")

       Otra cosa IMPORTANTE: Oracle "no se lleva bien" con los Long. Si declaro mi columna "id" como Long al
       definir la tabla en Oracle y luego en Java, al acceder por ID, me da error. De momento he declarado
       esa columna como Integer en Java y como NUMBER en Oracle. De momento lo dejo así por que me funciona
       y no necesito más... ( y si es que sí, ya me petarà !!)

      */



    @Id
    @SequenceGenerator(name="ORACLE_SEQ",sequenceName="ORACLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORACLE_SEQ")
    private Integer ID; //Normalmente, esta debería ser Long, pero a Oracle no le gustan los Long...

    private String tipocta;
    private Date fecha;
    private String concepto;
    private float importe;
    private boolean suministro;
    private String tiposumin;
    private String categoria;
    private String observac;
    private String comenta;

    public MovtosCtaBanco() { }


    public MovtosCtaBanco(int ID, String tipocta, Date fecha, String concepto, float importe,
                          boolean suministro, String tiposumin, String categoria, String observac,
                          String comenta) {
        this.ID = ID;
        this.tipocta = tipocta;
        this.fecha = fecha;
        this.concepto = concepto;
        this.importe = importe;
        this.suministro = suministro;
        this.tiposumin = tiposumin;
        this.categoria = categoria;
        this.observac = observac;
        this.comenta = comenta;
    }


}
