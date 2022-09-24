package com.vancra.proyecto_pogra;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface compraRepository extends JpaRepository<compra,Serializable>{
	List <compra> findByusuariouserid(int usuariouserid);
}
