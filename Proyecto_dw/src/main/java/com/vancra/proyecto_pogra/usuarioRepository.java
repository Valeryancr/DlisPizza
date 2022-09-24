package com.vancra.proyecto_pogra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.Serializable;

public interface usuarioRepository extends JpaRepository<usuario,Serializable>{
	usuario findByUsername(String username);
	
}
