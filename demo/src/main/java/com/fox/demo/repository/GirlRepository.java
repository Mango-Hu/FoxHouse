package com.fox.demo.repository;


import com.fox.demo.model.Girl;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;


public interface GirlRepository extends DataTablesRepository<Girl,Long>{

    Girl findGirlById(int i);
}
