package com.example.movtosbanco.repo;

import com.example.movtosbanco.ctasbcomdl.MovtosCtaBanco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioMovtos extends JpaRepository<MovtosCtaBanco, Integer> {

    public abstract ArrayList<MovtosCtaBanco> findByTiposumin(String tiposumin);


    ArrayList<MovtosCtaBanco> findByConcepto(String concepto);
}


