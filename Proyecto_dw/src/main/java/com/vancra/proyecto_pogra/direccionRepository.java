package com.vancra.proyecto_pogra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.Serializable;
import java.util.List;

public interface direccionRepository extends JpaRepository<direccion,Serializable>{
	List <direccion> deleteByDireccion(String direccion);
}
