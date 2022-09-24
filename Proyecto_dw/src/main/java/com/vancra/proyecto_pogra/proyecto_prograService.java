package com.vancra.proyecto_pogra;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@RestController


public class proyecto_prograService {
	@Autowired
	usuarioRepository usuarioRepository;
	@Autowired
	direccionRepository direccionesRepository;
	@RequestMapping(value="/user/all",method=RequestMethod.GET)
	public List<usuario> findAll(){
		return usuarioRepository.findAll();
	}

	@RequestMapping(value="/user/save",method=RequestMethod.POST)
	public usuario saveUser(@RequestBody usuario user) {

		List<direccion> direcciones = user.getDireccion();
		user.setDireccion(null);
		usuario temp = usuarioRepository.save(user);
		for(direccion t: direcciones) {
			t.setUserId(temp.getUserId());
			direccionesRepository.save(t);
		}
		temp.setDireccion(direcciones);
		return temp;
	}

	@DeleteMapping("/user/{userId}")
	public void deleteVendedora(@PathVariable int userId) {
		usuarioRepository.deleteById(userId);
	}

	@Transactional
	@DeleteMapping("/direccion/{idDireccion}")
	public void deleteDireccion(@PathVariable int idDireccion) {
		direccionesRepository.deleteById(idDireccion);
	}

	@PutMapping("/user/{codigo}")
	public ResponseEntity<Object> updateVendedora(@RequestBody usuario user, @PathVariable int codigo) {
		List<direccion> direcciones = user.getDireccion();
		user.setDireccion(null);
		user.setUserId(codigo);
		usuario temp = usuarioRepository.save(user);
		for(direccion t: direcciones) {
			t.setUserId(temp.getUserId());
			direccionesRepository.save(t);
		}
		temp.setDireccion(direcciones);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/user/by/user/{username}")
	public usuario findByNombre(@PathVariable("username")String username){
		return usuarioRepository.findByUsername(username);
	}

	@GetMapping(value="/user/find/id/{codigo}")
	public usuario findById(@PathVariable("codigo") int codigo) {
		return usuarioRepository.findById(codigo).get();
	}

	@GetMapping("/direcciones/all")
	public List <direccion> findAllt(){
		return direccionesRepository.findAll();
	}
	
	@Autowired
	anuncioRepository anunciosRepository;
	@GetMapping(value="/anuncios/all")
	public List<anuncio> findAllA(){
		return anunciosRepository.findAll();
	}
	
	@PostMapping(value="/anuncios/save")
	public anuncio saveItem(@RequestBody anuncio anuncios) {
		return anunciosRepository.save(anuncios);
	}

	@GetMapping(value="/anuncios/find/id/{codigo}")
	public anuncio findByIda(@PathVariable("codigo") int codigo) {
		return anunciosRepository.findById(codigo).get();
	}
	@DeleteMapping("/anuncios/{idanuncios}")
	public void deleteAnuncio(@PathVariable int idanuncios) {
		anunciosRepository.deleteById(idanuncios);
	}
	@PutMapping("/anuncios/{idanuncios}")
	public ResponseEntity<Object> updateAnuncio(@RequestBody anuncio anuncio, @PathVariable int idanuncios) {
		anuncio.setIdanuncios(idanuncios);
		anunciosRepository.save(anuncio);
		return ResponseEntity.noContent().build();
	}
	
	@Autowired
	productoRepository productosRepository;

	@GetMapping(value="/productos/all")
	public List<producto> findAllPro(){
		return productosRepository.findAll();
	}
	
	@PostMapping(value="/productos/save")
	public producto saveItem(@RequestBody producto productos){
		productosRepository.save(productos);
		notificationProcessor.onNext(productos);
		return productos;
	}

	@GetMapping(value="/productos/find/id/{idproductos}")
	public producto findByIdp(@PathVariable("idproductos") int idproductos) {
		return productosRepository.findById(idproductos).get();
	}

	@DeleteMapping("/productos/{idproductos}")
	public void deleteProducto(@PathVariable int idproductos) {
		producto p=productosRepository.findById(idproductos).get();
		productosRepository.deleteById(idproductos);
		notificationProcessor.onNext(p);
	}

	@PutMapping("/productos/{idproductos}")
	public ResponseEntity<Object> updateProducto(@RequestBody producto producto, @PathVariable int idproductos) {
		producto.setIdproductos(idproductos);
		productosRepository.save(producto);
		notificationProcessor.onNext(producto);
		return ResponseEntity.noContent().build();
	}
	
	//Detalle Productos
	@Autowired
	productospedidosRepository productospedidosRepository;
	
	@GetMapping(value="/productospedidos/all")
	public List<productospedidos> findAllProp(){
		return productospedidosRepository.findAll();
	}
	
	@PostMapping(value="/productospedidos/save")
	public productospedidos saveItem(@RequestBody productospedidos productospedidos){
		return productospedidosRepository.save(productospedidos);
	}
	
	//Compras
	@Autowired
	compraRepository compraRepository;
	@GetMapping(value="/compra/all")
	public List<compra> findAllPe(){
		return compraRepository.findAll();
	}
	
	@PostMapping(value="/compra/save")
	public compra saveItem(@RequestBody compra compra){
		
		List<productospedidos> productospedidos = compra.getproductospedidos();
		compra.setProductospedidos(null);
		compra temp = compraRepository.save(compra);
		for(productospedidos p: productospedidos) {
			p.setCompraid(temp.getCompraId());
			productospedidosRepository.save(p);
		}
		temp.setProductospedidos(productospedidos);
		notificationProcessor2.onNext(temp);
		return temp;
	}
	
	@GetMapping("/compra/by/ven/{usuariouserid}")
	public List <compra> findByUserId(@PathVariable("usuariouserid")int usuariouserid){
		return compraRepository.findByusuariouserid(usuariouserid);
	}



	private EmitterProcessor<producto> notificationProcessor;
	private EmitterProcessor<compra> notificationProcessor2;

    /* -------------------------------------------------------------------------------------------------------------- */
    @PostConstruct
    private void createProcessor() {
		notificationProcessor = EmitterProcessor.create();
		notificationProcessor2= EmitterProcessor.create();
    }

	private Flux<ServerSentEvent<compra>> getPedidosSSE() {
        return notificationProcessor2
                .log().map(
                        (pe) -> {
                            System.out.println("Sending Pedido:" + pe.getCompraId());
							return ServerSentEvent.<compra>builder()
								.id(UUID.randomUUID().toString())
								.event("Pedido-update")
                                .data(pe)
                                .build();
                        }).concatWith(Flux.never());
	}

	@GetMapping(value = "/notification/sse")
    public Flux<ServerSentEvent<?>>
            getJobResultNotification() {
				return Flux.merge(
					getPedidosSSE()
				);
			}
}
