package com.dh.digitalbooking;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.model.*;
import com.dh.digitalbooking.repository.*;
import com.dh.digitalbooking.service.imp.CategoriaServiceImp;
import com.dh.digitalbooking.service.imp.ProductoServiceImp;
import com.dh.digitalbooking.service.imp.PuntuacionServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DigitalbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalbookingApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CategoriaServiceImp categoriaServiceImp,
			PaisRepository paisRepository,
			CiudadRepository ciudadRepository,
			ProductoServiceImp productoServiceImp,
			CaracteristicaRepository caracteristicaRepository,
			TipoPoliticaRepository tipoPoliticaRepository,
			RoleRepository roleRepository,
			UsuarioRepository usuarioRepository,
			PasswordEncoder passwordEncoder,
			PuntuacionServiceImp puntuacionServiceImp
	) {
		return args -> {
			Rol rolUser = roleRepository.save(new Rol("ROLE_USER"));
			Rol rolAdmin = roleRepository.save(new Rol("ROLE_ADMIN"));

			Usuario usuarioAdmin = usuarioRepository.save(new Usuario(
					"admin",
					"admin",
					"admin@gmail.com",
					passwordEncoder.encode("admin"),
					rolAdmin
			));

//			Categoria hotel = categoriaServiceImp.saveCategoria(new Categoria("Hotel", "Descripcion de la categoria Hotel", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
//			Categoria hostel = categoriaServiceImp.saveCategoria(new Categoria("Hostel", "Descripcion de la categoria Hostel", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80"));
//			Categoria departamento = categoriaServiceImp.saveCategoria(new Categoria("Departamento", "Descripcion de la categoria Departamento", "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
//			Categoria bedAndBreakfast = categoriaServiceImp.saveCategoria(new Categoria("Bed and breakfast", "Descripcion de la categoria Bed and breakfast", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
//
//			Pais argentina = paisRepository.save(new Pais("Argentina"));
//			Pais chile = paisRepository.save(new Pais("Chile"));
//			Pais uruguay = paisRepository.save(new Pais("Uruguay"));
//			Pais brasil = paisRepository.save(new Pais("Brasil"));
//			Pais colombia = paisRepository.save(new Pais("Colombia"));
//
//			Ciudad buenosAires = ciudadRepository.save(new Ciudad("Buenos Aires", argentina));
//			Ciudad marDelPlata = ciudadRepository.save(new Ciudad("Mar del Plata", argentina));
//			Ciudad laPlata = ciudadRepository.save(new Ciudad("La Plata", argentina));
//			Ciudad rosario = ciudadRepository.save(new Ciudad("Rosario", argentina));
//			Ciudad santiago = ciudadRepository.save(new Ciudad("Santiago", chile));
//			Ciudad monteVideo = ciudadRepository.save(new Ciudad("Montevide", uruguay));
//			Ciudad rioDeJaneiro = ciudadRepository.save(new Ciudad("Rio de Janeiro", brasil));
//			Ciudad cali = ciudadRepository.save(new Ciudad("Cali", colombia));
//			Ciudad bogota = ciudadRepository.save(new Ciudad("Bogotá", colombia));
//			Ciudad medellin = ciudadRepository.save(new Ciudad("Medellín", colombia));
//			Ciudad cartagena = ciudadRepository.save(new Ciudad("Cartagena", colombia));
//			Ciudad barranquilla = ciudadRepository.save(new Ciudad("Barranquilla", colombia));
//
//			Caracteristica wifi = caracteristicaRepository.save(new Caracteristica("Wifi"));
//			Caracteristica cocina = caracteristicaRepository.save(new Caracteristica("Cocina"));
//			Caracteristica televisor = caracteristicaRepository.save(new Caracteristica("Televisor"));
//			Caracteristica pileta = caracteristicaRepository.save(new Caracteristica("Pileta"));
//			Caracteristica aptoMascotas = caracteristicaRepository.save(new Caracteristica("Apto mascotas"));
//
//			TipoPolitica normasDeLaCasa = tipoPoliticaRepository.save(new TipoPolitica("Normas de la casa"));
//			TipoPolitica saludYSeguridad = tipoPoliticaRepository.save(new TipoPolitica("Salud y seguridad"));
//			TipoPolitica politicaDeCancelacion = tipoPoliticaRepository.save(new TipoPolitica("Politicas de cancelacion"));
//
//			Set<Caracteristica> caracteristicas1 = new HashSet<>(Set.of(wifi, cocina, televisor));
//			Set<Caracteristica> caracteristicas2 = new HashSet<>(Set.of(pileta, aptoMascotas, televisor));
//			Set<Caracteristica> caracteristicas3 = new HashSet<>(Set.of(wifi, cocina, televisor, pileta, aptoMascotas));
//
//			Imagen imagen1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
//			Imagen imagen2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
//			Imagen imagen3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
//			Imagen imagen4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
//			Imagen imagen5 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
//
//			Set<Imagen> imagenesSet5 = new HashSet<>(Set.of(imagen1, imagen2, imagen3, imagen4, imagen5));
//			Set<Imagen> imagenesSet4 = new HashSet<>(Set.of(imagen1, imagen2, imagen3, imagen4));
//			Set<Imagen> imagenesSet3 = new HashSet<>(Set.of(imagen1, imagen2, imagen3));
//			Set<Imagen> imagenesSet2 = new HashSet<>(Set.of(imagen1, imagen2));
//			Set<Imagen> imagenesSet1 = new HashSet<>(Set.of(imagen1));
//
//			Politica politica1 = new Politica("Check-out: 10:00", normasDeLaCasa);
//			Politica politica2 = new Politica("No se permiten fiestas", normasDeLaCasa);
//			Politica politica3 = new Politica("Detector de humo", saludYSeguridad);
//			Politica politica4 = new Politica("Deposito de seguridad", saludYSeguridad);
//			Politica politica5 = new Politica("Agregá las fechas de tu viaje para obtener los detalles de cancelación de esta estadía", politicaDeCancelacion);
//
//			Set<Politica> politicas1 = new HashSet<>(Set.of(politica1, politica2, politica3, politica4, politica5));
//			Set<Politica> politicas2 = new HashSet<>(Set.of(politica3, politica4, politica5));
//			Set<Politica> politicas3 = new HashSet<>(Set.of(politica1));
//
//			Coordenadas coordenadas = new Coordenadas(
//					new BigDecimal("-34.593179"),
//					new BigDecimal("-58.374930"));
//
//
//
//
//			for(int i = 0; i < 30; i++) {
//				Producto hotel1 = new Producto();
//				hotel1.setTitulo("Hotel " + i);
//				hotel1.setTituloDescripcion("Titulo de la descripcion del hotel " + i);
//				hotel1.setDescripcion("Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
//				hotel1.setDireccion("Av Congreso 3423");
//				hotel1.setPrecioPorNoche(new BigDecimal("5200.00"));
//				hotel1.setCategoria(hotel);
//				hotel1.setCiudad(buenosAires);
//				hotel1.setCaracteristicas(caracteristicas1);
//				hotel1.setImagenes(imagenesSet5);
//				hotel1.setPoliticas(politicas1);
//				hotel1.setCoordenadas(coordenadas);
//				productoServiceImp.saveProducto(hotel1);
//			}
//
//			for(int i = 0; i < 30; i++) {
//				Producto departamento1 = new Producto();
//				departamento1.setTitulo("Departamento " + i);
//				departamento1.setTituloDescripcion("Titulo de la descripcion del departamento 1");
//				departamento1.setDescripcion("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc");
//				departamento1.setDireccion("Av Rivadavia 8423");
//				departamento1.setPrecioPorNoche(new BigDecimal("6600.00"));
//				departamento1.setCategoria(departamento);
//				departamento1.setCiudad(cali);
//				departamento1.setCaracteristicas(caracteristicas3);
//				departamento1.setImagenes(imagenesSet3);
//				departamento1.setPoliticas(politicas3);
//				departamento1.setCoordenadas(coordenadas);
//				productoServiceImp.saveProducto(departamento1);
//			}

//			VIEJO ☝

//			NUEVO 👇

			Categoria hotel = categoriaServiceImp.saveCategoria(new Categoria("Hotel", "Descripcion de la categoria Hotel", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
			Categoria hostel = categoriaServiceImp.saveCategoria(new Categoria("Hostel", "Descripcion de la categoria Hostel", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80"));
			Categoria departamento = categoriaServiceImp.saveCategoria(new Categoria("Departamento", "Descripcion de la categoria Departamento", "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
			Categoria bedAndBreakfast = categoriaServiceImp.saveCategoria(new Categoria("Bed and breakfast", "Descripcion de la categoria Bed and breakfast", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));

			Pais argentina = paisRepository.save(new Pais("Argentina"));
			Pais italia = paisRepository.save(new Pais("Italia"));
			Pais brasil = paisRepository.save(new Pais("Brasil"));
			Pais colombia = paisRepository.save(new Pais("Colombia"));
			Pais estadosUnidos = paisRepository.save(new Pais("Estados Unidos"));
			Pais espania = paisRepository.save(new Pais("España"));
			Pais cuba = paisRepository.save(new Pais("Cuba"));
			Pais japon = paisRepository.save(new Pais("Japón"));

			Ciudad puertoIguazu = ciudadRepository.save(new Ciudad("Puerto Iguazú", argentina));
			Ciudad sanMartindelosAndes = ciudadRepository.save(new Ciudad("San Martín de los Andes", argentina));
			Ciudad elCalafate = ciudadRepository.save(new Ciudad("El Calafate", argentina));
			Ciudad neuquen = ciudadRepository.save(new Ciudad("Neuquen", argentina));
			Ciudad barcelona = ciudadRepository.save(new Ciudad("Barcelona", espania));
			Ciudad grado = ciudadRepository.save(new Ciudad("Grado", italia));
			Ciudad ushuaia = ciudadRepository.save(new Ciudad("Ushuaia", argentina));
			Ciudad buzios = ciudadRepository.save(new Ciudad("Buzios", brasil));
			Ciudad miami = ciudadRepository.save(new Ciudad("Miami", estadosUnidos));
			Ciudad santaMa = ciudadRepository.save(new Ciudad("Santa María", colombia));
			Ciudad laHabana = ciudadRepository.save(new Ciudad("La Habana", cuba));
			Ciudad newyork = ciudadRepository.save(new Ciudad("New York", estadosUnidos));
			Ciudad tokio = ciudadRepository.save(new Ciudad("Tokio", japon));
//			Ciudad barranquilla = ciudadRepository.save(new Ciudad("Barranquilla", colombia));
//			Ciudad sanPablo = ciudadRepository.save(new Ciudad("Sao Pablo", brasil));
//			Ciudad SanSalvador = ciudadRepository.save(new Ciudad("San Salvador do Bahía", brasil));
//			Ciudad Brasilia = ciudadRepository.save(new Ciudad("Brasília", brasil));

			Caracteristica wifi = caracteristicaRepository.save(new Caracteristica("Wifi"));
			Caracteristica cocina = caracteristicaRepository.save(new Caracteristica("Cocina"));
			Caracteristica televisor = caracteristicaRepository.save(new Caracteristica("Televisor"));
			Caracteristica pileta = caracteristicaRepository.save(new Caracteristica("Pileta"));
			Caracteristica aptoMascotas = caracteristicaRepository.save(new Caracteristica("Apto mascotas"));
			Caracteristica parrilla = caracteristicaRepository.save(new Caracteristica("Parrilla"));
			Caracteristica lavadora = caracteristicaRepository.save(new Caracteristica("Lavadora"));
			Caracteristica banioPrivado = caracteristicaRepository.save(new Caracteristica("Baño privado"));
			Caracteristica noFumar = caracteristicaRepository.save(new Caracteristica("No fumar"));
			Caracteristica vistaCiudad = caracteristicaRepository.save(new Caracteristica("Vistas a la ciudad"));


			TipoPolitica normasDeLaCasa = tipoPoliticaRepository.save(new TipoPolitica("Normas de la casa"));
			TipoPolitica saludYSeguridad = tipoPoliticaRepository.save(new TipoPolitica("Salud y seguridad"));
			TipoPolitica politicaDeCancelacion = tipoPoliticaRepository.save(new TipoPolitica("Politicas de cancelacion"));

			Set<Caracteristica> caracteristicas1 = new HashSet<>(Set.of(cocina, televisor, parrilla, lavadora, noFumar));
			Set<Caracteristica> caracteristicas2 = new HashSet<>(Set.of(noFumar, parrilla, lavadora, pileta, aptoMascotas, televisor, wifi));
			Set<Caracteristica> caracteristicas3 = new HashSet<>(Set.of(wifi, cocina, televisor, pileta, aptoMascotas, banioPrivado, vistaCiudad, parrilla, lavadora));

			//Imagenes generar variables que contengan las imagenes para al menos 15 productos
			//cada producto necesita 9 imagenes
			Imagen imagen1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen imagen2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen imagen3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen imagen4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen imagen5 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			//hiltongarden
			Imagen hiltongarden1 = new Imagen("Imagen 1", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/249634973.jpg?k=046dfe1736bdfcd65b43b9dea9ca6e2bd3b02d12a3f81c84b2eb4c4ed57b276e&o=&hp=1");
			Imagen hiltongarden2 = new Imagen("Imagen 2", "https://cf.bstatic.com/xdata/images/hotel/max1280x900/249632667.jpg?k=a9033b2da742903494202f719d8e0314142f3187b2aa9ee2d4796829efa43c8b&o=&hp=1");
			Imagen hiltongarden3 = new Imagen("Imagen 3", "https://cf.bstatic.com/xdata/images/hotel/max1280x900/249633069.jpg?k=e1e33c7a0886e72c4b705a41a371c5619d3210b0681f262cc6b096c4de190a7e&o=&hp=1");
			Imagen hiltongarden4 = new Imagen("Imagen 4", "https://cf.bstatic.com/xdata/images/hotel/max1280x900/313379046.jpg?k=eda588d08af2ee1f470905e22341ea6250da03a04085c1cef5d098470a675e86&o=&hp=1");
			Imagen hiltongarden5 = new Imagen("Imagen 5", "https://cf.bstatic.com/xdata/images/hotel/max1280x900/249871836.jpg?k=2f92273aa6bf31cd32a800c42b58df247b7473b5699c47ffebeacd28c4527967&o=&hp=1");

			//laBrisa
			Imagen laBrisa1 = new Imagen("Imagen 1", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/249634973.jpg?k=046dfe1736bdfcd65b43b9dea9ca6e2bd3b02d12a3f81c84b2eb4c4ed57b276e&o=&hp=1");
			Imagen laBrisa2 = new Imagen("Imagen 2", "https://cf.bstatic.com/xdata/images/hotel/max1280x900/249632667.jpg?k=a9033b2da742903494202f719d8e0314142f3187b2aa9ee2d4796829efa43c8b&o=&hp=1");
			Imagen laBrisa3 = new Imagen("Imagen 3", "https://cf.bstatic.com/xdata/images/hotel/max1280x900/208218866.jpg?k=36bc72c865018a5097012d31bee8f88e118922cec36940f51e7c1d5bbcd4b2aa&o=&hp=1");
			Imagen laBrisa4 = new Imagen("Imagen 4", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/151235405.jpg?k=2e8f210673a2e30719ed4f0587fffcbee048a136ac547781eeb524e90c37878a&o=&hp=1");
			Imagen laBrisa5 = new Imagen("Imagen 5", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/243354046.jpg?k=d3b5f54061ce9ab442bcb713c07e1307b71c5bfe66ed607b7e914cc0cbf9d9f5&o=&hp=1");

			//nuovoMiami -fotosfalsas-
			Imagen nuovoMiami1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen nuovoMiami2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen nuovoMiami3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen nuovoMiami4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen nuovoMiami5 = new Imagen("Imagen 5", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			//villadEste -fotosfalsas-
			Imagen villadEste1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen villadEste2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen villadEste3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen villadEste4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen villadEste5 = new Imagen("Imagen 5", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			//urbanyHostel -fotosfalsas-
			Imagen urbanyHostel1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen urbanyHostel2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen urbanyHostel3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen urbanyHostel4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen urbanyHostel5 = new Imagen("Imagen 5", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			//laSoberanaHosteria -fotosfalsas-
			Imagen laSoberanaHosteria1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen laSoberanaHosteria2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen laSoberanaHosteria3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen laSoberanaHosteria4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen laSoberanaHosteria5 = new Imagen("Imagen 5", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			//utaka -fotosfalsas-
			Imagen utaka1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen utaka2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen utaka3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen utaka4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen utaka5 = new Imagen("Imagen 5", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			//orillaMansa -fotosfalsas-
			Imagen orillaMansa1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen orillaMansa2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen orillaMansa3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen orillaMansa4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen orillaMansa5 = new Imagen("Imagen 5", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			//laReservaVirginLodge -fotosfalsas-
			Imagen laReservaVirginLodge1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen laReservaVirginLodge2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen laReservaVirginLodge3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen laReservaVirginLodge4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen laReservaVirginLodge5 = new Imagen("Imagen 5", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			//bonFim
			Imagen bonFim1 = new Imagen("Imagen 1", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/222720921.jpg?k=8f5bbb8cd2c9a7c489b973af5653602b5a7770480be2ef8891c640f5563ab984&o=&hp=1");
			Imagen bonFim2 = new Imagen("Imagen 2", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/394974989.jpg?k=635dce38345bbd07df803d4f094d942203093f24fa1a726223859f16c6eeaf58&o=&hp=1");
			Imagen bonFim3 = new Imagen("Imagen 3", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330908.jpg?k=e678d31e93de144047b816ca001ac469a1c6cff9985b0e944881253a0b795bdc&o=&hp=1");
			Imagen bonFim4 = new Imagen("Imagen 4", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330908.jpg?k=e678d31e93de144047b816ca001ac469a1c6cff9985b0e944881253a0b795bdc&o=&hp=1");
			Imagen bonFim5 = new Imagen("Imagen 5", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330913.jpg?k=b5c38cfd746b02090ca43f1da1d9494c3d6fc25e0c6919f524f1e56d72d53f27&o=&hp=1");

			//nacionalDeCuba -fotosfalsas-
			Imagen nacionaldeCuba1 = new Imagen("Imagen 1", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/222720921.jpg?k=8f5bbb8cd2c9a7c489b973af5653602b5a7770480be2ef8891c640f5563ab984&o=&hp=1");
			Imagen nacionaldeCuba2 = new Imagen("Imagen 2", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/394974989.jpg?k=635dce38345bbd07df803d4f094d942203093f24fa1a726223859f16c6eeaf58&o=&hp=1");
			Imagen nacionaldeCuba3 = new Imagen("Imagen 3", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330908.jpg?k=e678d31e93de144047b816ca001ac469a1c6cff9985b0e944881253a0b795bdc&o=&hp=1");
			Imagen nacionaldeCuba4 = new Imagen("Imagen 4", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330908.jpg?k=e678d31e93de144047b816ca001ac469a1c6cff9985b0e944881253a0b795bdc&o=&hp=1");
			Imagen nacionaldeCuba5 = new Imagen("Imagen 5", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330913.jpg?k=b5c38cfd746b02090ca43f1da1d9494c3d6fc25e0c6919f524f1e56d72d53f27&o=&hp=1");

			//interContinental -fotosfalsas-
			Imagen interContinental1 = new Imagen("Imagen 1", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/222720921.jpg?k=8f5bbb8cd2c9a7c489b973af5653602b5a7770480be2ef8891c640f5563ab984&o=&hp=1");
			Imagen interContinental2 = new Imagen("Imagen 2", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/394974989.jpg?k=635dce38345bbd07df803d4f094d942203093f24fa1a726223859f16c6eeaf58&o=&hp=1");
			Imagen interContinental3 = new Imagen("Imagen 3", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330908.jpg?k=e678d31e93de144047b816ca001ac469a1c6cff9985b0e944881253a0b795bdc&o=&hp=1");
			Imagen interContinental4 = new Imagen("Imagen 4", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330908.jpg?k=e678d31e93de144047b816ca001ac469a1c6cff9985b0e944881253a0b795bdc&o=&hp=1");
			Imagen interContinental5 = new Imagen("Imagen 5", "https://cf.bstatic.com/xdata/images/hotel/max1024x768/39330913.jpg?k=b5c38cfd746b02090ca43f1da1d9494c3d6fc25e0c6919f524f1e56d72d53f27&o=&hp=1");

			//Set de imagenes por producto nombrarlos i+el nombre del producto
			Set<Imagen> ihiltongarden = new HashSet<>(Set.of(hiltongarden1, hiltongarden2, hiltongarden3, hiltongarden4, hiltongarden5));
			Set<Imagen> ilaBrisa = new HashSet<>(Set.of(laBrisa1, laBrisa2, laBrisa3, laBrisa4, laBrisa5));
			Set<Imagen> inuovoMiami = new HashSet<>(Set.of(nuovoMiami1, nuovoMiami2, nuovoMiami3, nuovoMiami4, nuovoMiami5));
			Set<Imagen> ivilladEste = new HashSet<>(Set.of(villadEste1, villadEste2, villadEste3, villadEste4, villadEste5));
			Set<Imagen> iurbanyHostel = new HashSet<>(Set.of(urbanyHostel1, urbanyHostel2, urbanyHostel3, urbanyHostel4, urbanyHostel5));
			Set<Imagen> ilaSoberanaHosteria = new HashSet<>(Set.of(laSoberanaHosteria1, laSoberanaHosteria2, laSoberanaHosteria3, laSoberanaHosteria4, laSoberanaHosteria5));
			Set<Imagen> iutaka = new HashSet<>(Set.of(utaka1, utaka2, utaka3, utaka4, utaka5));
			Set<Imagen> iorillaMansa= new HashSet<>(Set.of(orillaMansa1, orillaMansa2, orillaMansa3, orillaMansa4, orillaMansa5));
			Set<Imagen> ilaReservaVirginLodge= new HashSet<>(Set.of(laReservaVirginLodge1, laReservaVirginLodge2, laReservaVirginLodge3, laReservaVirginLodge4, laReservaVirginLodge5));
			Set<Imagen> ipousadaBonfim= new HashSet<>(Set.of(bonFim1, bonFim2, bonFim3, bonFim4, bonFim5));
			Set<Imagen> inacionaldeCuba= new HashSet<>(Set.of(nacionaldeCuba1, nacionaldeCuba2, nacionaldeCuba3, nacionaldeCuba4, nacionaldeCuba5));
			Set<Imagen> iinterContinental= new HashSet<>(Set.of(interContinental1, interContinental2, interContinental3, interContinental4, interContinental5));


			Politica politica1 = new Politica("Check-out: 10:00", normasDeLaCasa);
			Politica politica2 = new Politica("No se permiten fiestas", normasDeLaCasa);
			Politica politica3 = new Politica("Detector de humo", saludYSeguridad);
			Politica politica4 = new Politica("Deposito de seguridad", saludYSeguridad);
			Politica politica5 = new Politica("Agregá las fechas de tu viaje para obtener los detalles de cancelación de esta estadía", politicaDeCancelacion);

			Set<Politica> politicas1 = new HashSet<>(Set.of(politica1, politica2, politica3, politica4, politica5));
			Set<Politica> politicas2 = new HashSet<>(Set.of(politica3, politica4, politica5));
			Set<Politica> politicas3 = new HashSet<>(Set.of(politica1));


			//Generar las variables necesarias para guardar las coordenadas de ubicación de los productos
			//Nombralas NombreHotel+c
			Coordenadas hiltongardenc = new Coordenadas(
					new BigDecimal("-38.941697761424834"),
					new BigDecimal("-68.05962964717754"));

			Coordenadas laBrisaLocaHostel = new Coordenadas(
					new BigDecimal("11.24500468403564"),
					new BigDecimal("-74.21162384247455"));

			Coordenadas nuoMiamiApartaments = new Coordenadas(
					new BigDecimal("25.734365891053194"),
					new BigDecimal("-80.23622998084356"));

			Coordenadas urbanyHostelc = new Coordenadas(
					new BigDecimal("41.407725926268164"),
					new BigDecimal("2.1863069709469762"));

			Coordenadas villadEstec = new Coordenadas(
					new BigDecimal("45.84856625461699"),
					new BigDecimal("9.085044041108125"));

			Coordenadas laSoberanaHosteriac = new Coordenadas(
					new BigDecimal("-50.31369487360898"),
					new BigDecimal("-72.31902873315147"));

			Coordenadas utakac = new Coordenadas(
					new BigDecimal("-54.809057600421816"),
					new BigDecimal("-68.32123219840852"));

			Coordenadas orillaMansac = new Coordenadas(
					new BigDecimal("-40.15945137990247"),
					new BigDecimal("-71.35977835657081"));

			Coordenadas laReservaVirginLodgec = new Coordenadas(
					new BigDecimal("-25.60489625556165"),
					new BigDecimal("-54.55083371465647"));

			Coordenadas pousadaBonfimc = new Coordenadas(
					new BigDecimal("-22.74533965556891"),
					new BigDecimal("-41.881199656985"));

			Coordenadas nacionaldeCubac = new Coordenadas(
					new BigDecimal("23.1408"),
					new BigDecimal("82.3889"));

			Coordenadas interContinentalc = new Coordenadas(
					new BigDecimal("40.7553"),
					new BigDecimal("73.9861"));

			Coordenadas shangriLac = new Coordenadas(
					new BigDecimal("35.6664"),
					new BigDecimal("139.7504"));

			Coordenadas rafflesc = new Coordenadas(
					new BigDecimal("35.6664"),
					new BigDecimal("139.7504"));

			Coordenadas leBristolc = new Coordenadas(
					new BigDecimal("25.2048"),
					new BigDecimal("139.7504"));

			Coordenadas ritzCarltonc = new Coordenadas(
					new BigDecimal("35.6664"),
					new BigDecimal("55.2708"));

			Coordenadas burjAlArabc = new Coordenadas(
					new BigDecimal("25.1412"),
					new BigDecimal("55.1852"));

			Coordenadas uniquec = new Coordenadas(
					new BigDecimal("-23.5814"),
					new BigDecimal("46.6774"));

			Coordenadas santaCaterinac = new Coordenadas(
					new BigDecimal("40.6349"),
					new BigDecimal("14.6110"));

			Coordenadas glacec = new Coordenadas(
					new BigDecimal("46.8862"),
					new BigDecimal("71.2610"));



			Producto hiltongarden = new Producto();
			hiltongarden.setTitulo("Hilton Garden Hotel");
			hiltongarden.setTituloDescripcion("Un espacio ideal para eventos empresariales");
			hiltongarden.setDescripcion("El Hilton Garden Neuquen está muy bien situado en el distrito de Mongkok, a solo 7 minutos a pie de la estación MTR de Mongkok, rodeado de populares zonas comerciales y de compras y de lugares de interés.\n" +
					"\n" +
					"El Hilton Garden Inn Hong Kong Mongkok está a 1 km del mercado nocturno de Temple Street, a 700 metros del Ladies Market y a 24 km del aeropuerto internacional de Hong Kong.\n" +
					"\n" +
					"Hay una recepción 24 horas, instalaciones para reuniones y salones de banquetes sin pilares, un espacio ideal para bodas, reuniones sociales y conferencias.\n" +
					"\n" +
					"El restaurante del establecimiento sirve una excelente selección de platos de cocina occidental y china.\n");
			hiltongarden.setDireccion("Sales Torres 98-1, Q8300ASV Neuquén");
			hiltongarden.setPrecioPorNoche(new BigDecimal("520000.00"));
			hiltongarden.setCategoria(hotel);
			hiltongarden.setCiudad(neuquen);
			hiltongarden.setCaracteristicas(caracteristicas3);
			hiltongarden.setImagenes(ihiltongarden);
			hiltongarden.setPoliticas(politicas1);
			hiltongarden.setCoordenadas(hiltongardenc);
			productoServiceImp.saveProducto(hiltongarden);

			Producto laBrisa = new Producto();
			laBrisa.setTitulo("La Brisa Loca");
			laBrisa.setTituloDescripcion("Cervezas, sol y amigos");
			laBrisa.setDescripcion("La Brisa Loca está situada en una mansión republicana de mas de 100 años que ha sido restaurada para resaltar su clásico encanto colonial caribeño. La gran mansión de techos altos, con sus dieciocho dormitorios y suites, tiene capacidad para 90 personas. Con el estilo tradicional y esquemas de color de la auténtica arquitectura del Caribe colombiano, disfrutarás y apreciarás los aspectos más destacados de la región, no solo en la ciudad colonial más antigua de América, sino también aca en tu hospedaje. En La Brisa Loca, puedes explorar y descubrir Colombia con comodidad y estilo ... pero luego debes enfrentar la tarea más grande de todas ... ¡dejar el hostal!");
			laBrisa.setDireccion("Sales Torres 98-1, Q8300ASV Neuquén");
			laBrisa.setPrecioPorNoche(new BigDecimal("36000.00"));
			laBrisa.setCategoria(hostel);
			laBrisa.setCiudad(santaMa);
			laBrisa.setCaracteristicas(caracteristicas2);
			laBrisa.setImagenes(ilaBrisa);
			laBrisa.setPoliticas(politicas2);
			laBrisa.setCoordenadas(laBrisaLocaHostel);
			productoServiceImp.saveProducto(laBrisa);

			Producto nuovoMiami = new Producto();
			nuovoMiami.setTitulo("Nuovo Miami Apartaments at Coconut Grove");
			nuovoMiami.setTituloDescripcion("Alquiler de lujo en pleno Coconut Grove");
			nuovoMiami.setDescripcion("El CoconutGrove - Luxurious Vacation Rentals in Coconut Grove ofrece sala de fitness, sauna y alojamiento con aire acondicionado y WiFi gratuita en Miami, a 600 metros del centro comercial Cocowalk. Hay aparcamiento privado.\n" +
					"\n" +
					"El apartamento dispone de TV de pantalla plana y baño privado con artículos de aseo gratuitos, secador de pelo y bañera. Hay nevera, microondas y cafetera.\n" +
					"\n" +
					"El iCoconutGrove - Luxurious Vacation Rentals in Coconut Grove alberga un restaurante que sirve cocina americana, peruana e internacional. También se pueden solicitar opciones vegetarianas.\n" +
					"\n" +
					"También hay bañera de hidromasaje.\n" +
					"\n" +
					"El ICoconutGrove - Luxurious Vacation Rentals in Coconut Grove alberga un solárium.\n" +
					"\n" +
					"El apartamento está a 4,4 km del Museo Vizcaya y a 5,7 km de la Universidad de Miami. El aeropuerto más cercano es el aeropuerto internacional de Miami, ubicado a 10 km del iCoconutGrove - Luxurious Vacation Rentals in Coconut Grove. ");
			nuovoMiami.setDireccion("2889 McFarlane Rd, Coconut Grove, Miami, FL");
			nuovoMiami.setPrecioPorNoche(new BigDecimal("360000.00"));
			nuovoMiami.setCategoria(hotel);
			nuovoMiami.setCiudad(miami);
			nuovoMiami.setCaracteristicas(caracteristicas1);
			nuovoMiami.setImagenes(inuovoMiami);
			nuovoMiami.setPoliticas(politicas1);
			nuovoMiami.setCoordenadas(nuoMiamiApartaments);
			productoServiceImp.saveProducto(nuovoMiami);

			Producto urbanyHostel  = new Producto();
			urbanyHostel.setTitulo("Urbany Hostel");
			urbanyHostel.setTituloDescripcion("La comodidad que estas buscando");
			urbanyHostel.setDescripcion("El Urbany Hostel Barcelona se encuentra en la avenida Meridiana, en Barcelona, a 300 metros de la estación de metro Clot. Este albergue grande y moderno tiene amplias áreas comunes donde puedes relajarte y conocer gente de otros países. Hay WiFi gratuita en todo el hotel y una terraza en la azotea con vistas a la ciudad y servicio de bar.\n" +
					"\n" +
					"Tanto las habitaciones privadas como las habitaciones compartidas están decoradas en estilo moderno y constan de taquillas, baño y aire acondicionado.\n" +
					"\n" +
					"El Urbany Barcelona sirve un desayuno buffet continental a diario. Además, dispone de cocina equipada con fogones, neveras y microondas. También alberga un bar cafetería y máquinas expendedoras. Además, se preparan almuerzos para llevar bajo petición.\n" +
					"\n" +
					"La recepción está abierta las 24 horas y dispone de mostrador de información turística. Se proporcionan mapas gratuitos de la ciudad.\n" +
					"\n" +
					"El Urbany Hostel está situado a 5 minutos a pie del centro comercial Glòries y a 15 minutos a pie de la Sagrada Familia de Gaudí. Asimismo, el establecimiento se halla a 15 minutos en metro o en autobús del centro de la ciudad y a 2 km de la playa.\n");
			urbanyHostel.setDireccion("Avenida Meridiana, 97, Sant Martí, 08026 Barcelona, España");
			urbanyHostel.setPrecioPorNoche(new BigDecimal("36000.00"));
			urbanyHostel.setCategoria(hostel);
			urbanyHostel.setCiudad(barcelona);
			urbanyHostel.setCaracteristicas(caracteristicas2);
			urbanyHostel.setImagenes(iurbanyHostel);
			urbanyHostel.setPoliticas(politicas1);
			urbanyHostel.setCoordenadas(urbanyHostelc);
			productoServiceImp.saveProducto(urbanyHostel);

			Producto villadEste  = new Producto();
			villadEste.setTitulo("Hotel Villa d'Este");
			villadEste.setTituloDescripcion("Cerca de la playa");
			villadEste.setDescripcion("El Hotel Villa D'Este se encuentra en Grado, a solo 400 metros del mar, y ofrece piscina al aire libre. Ofrece habitaciones con aire acondicionado, balcón y TV vía satélite.\n" +
					"\n" +
					"El hotel dispone de terraza con tumbonas. También hay un terminal con conexión a internet y WiFi gratuita en las zonas comunes.\n" +
					"\n" +
					"El almuerzo y la cena se pueden disfrutar en un hotel asociado, situado a 60 metros del Villa D'Este Hotel.");
			villadEste.setDireccion("Via Giuseppe Parini 9, 34073 Grado, Italia");
			villadEste.setPrecioPorNoche(new BigDecimal("380500.00"));
			villadEste.setCategoria(hotel);
			villadEste.setCiudad(grado);
			villadEste.setCaracteristicas(caracteristicas3);
			villadEste.setImagenes(ivilladEste);
			villadEste.setPoliticas(politicas1);
			villadEste.setCoordenadas(villadEstec);
			productoServiceImp.saveProducto(villadEste);

			Producto laSoberanaHosteria  = new Producto();
			laSoberanaHosteria.setTitulo("La Soberana Hostería");
			laSoberanaHosteria.setTituloDescripcion("Cercanía del lago Argentino");
			laSoberanaHosteria.setDescripcion("La Hostería La Soberana se encuentra en El Calafate, a 11 km del lago Argentino, y ofrece jardín y vistas a la montaña. El establecimiento se encuentra a 2,8 km de la Isla Solitaria, a 7,2 km del Museo Regional y a 7,6 km de la laguna de Nimez. Ofrece recepción 24 horas, servicio de enlace con el aeropuerto, servicio de habitaciones y WiFi gratuita.\n" +
					"\n" +
					"Las habitaciones de la posada tienen vistas a la ciudad y baño privado con bidet y artículos de aseo gratuitos. Las habitaciones de la Hostería La Soberana están equipadas con ropa de cama y toallas.\n" +
					"\n" +
					"El establecimiento sirve un desayuno buffet o continental. La Hostería La Soberana alberga un restaurante de cocina argentina. También se puede solicitar una opción sin gluten.\n" +
					"\n" +
					"La estación de autobuses de El Calafate se encuentra a 8,1 km de la posada, mientras que las ruinas de Puerto Irma están a 20 km. El aeropuerto más cercano es el aeropuerto internacional Comandante Armando Tola, ubicado a 27 km de la Hostería La Soberana.\n");
			laSoberanaHosteria.setDireccion("Calle 218 N° 347, 9405 El Calafate, Argentina");
			laSoberanaHosteria.setPrecioPorNoche(new BigDecimal("380500.00"));
			laSoberanaHosteria.setCategoria(hotel);
			laSoberanaHosteria.setCiudad(elCalafate);
			laSoberanaHosteria.setCaracteristicas(caracteristicas1);
			laSoberanaHosteria.setImagenes(ilaSoberanaHosteria);
			laSoberanaHosteria.setPoliticas(politicas1);
			laSoberanaHosteria.setCoordenadas(laSoberanaHosteriac);
			productoServiceImp.saveProducto(laSoberanaHosteria);

			Producto utaka  = new Producto();
			utaka.setTitulo("Utaka Cabañas y Apartamentos");
			utaka.setTituloDescripcion("Estudio con vistas a la montaña");
			utaka.setDescripcion("La Hostería La Soberana se encuentra en El Calafate, a 11 km del lago Argentino, y ofrece jardín y vistas a la montaña. El establecimiento se encuentra a 2,8 km de la Isla Solitaria, a 7,2 km del Museo Regional y a 7,6 km de la laguna de Nimez. Ofrece recepción 24 horas, servicio de enlace con el aeropuerto, servicio de habitaciones y WiFi gratuita.\n" +
					"\n" +
					"Las habitaciones de la posada tienen vistas a la ciudad y baño privado con bidet y artículos de aseo gratuitos. Las habitaciones de la Hostería La Soberana están equipadas con ropa de cama y toallas.\n" +
					"\n" +
					"El establecimiento sirve un desayuno buffet o continental. La Hostería La Soberana alberga un restaurante de cocina argentina. También se puede solicitar una opción sin gluten.\n" +
					"\n" +
					"La estación de autobuses de El Calafate se encuentra a 8,1 km de la posada, mientras que las ruinas de Puerto Irma están a 20 km. El aeropuerto más cercano es el aeropuerto internacional Comandante Armando Tola, ubicado a 27 km de la Hostería La Soberana.\n");
			utaka.setDireccion("Goleta Florencia 1686, 9410 Ushuaia, Argentina");
			utaka.setPrecioPorNoche(new BigDecimal("19515.00"));
			utaka.setCategoria(departamento);
			utaka.setCiudad(ushuaia);
			utaka.setCaracteristicas(caracteristicas3);
			utaka.setImagenes(iutaka);
			utaka.setPoliticas(politicas1);
			utaka.setCoordenadas(utakac);
			productoServiceImp.saveProducto(utaka);

			Producto orillaMansa  = new Producto();
			orillaMansa.setTitulo("Apart Hotel Orilla Mansa");
			orillaMansa.setTituloDescripcion("Estudio con vistas a la montaña");
			orillaMansa.setDescripcion("Está situado en la avenida principal, a solo 20 metros del lago Lacar.El Apart Hotel Orilla Mansa ofrece apartamentos independientes con conexión Wi-Fi en San Martín de los Andes. El establecimiento se encuentra a 200 metros de la terminal de autobuses y a 400 metros del centro de la ciudad.\n" +
					"\n" +
					"Los apartamentos del Apart Hotel Orilla Mansa presentan un estilo rústico, paredes con paneles de madera y una decoración detallada. Disponen de TV LED vía satélite y cocina bien equipada con horno y microondas.\n" +
					"\n" +
					"Todos los apartamentos disponen de calefacción central y una acogedora estufa de leña o salamandra en la sala de estar. El desayuno diario incluye té, café, leche, chocolate caliente, mermelada, 2 cruasanes y 3 tostadas de pan por persona.\n" +
					"\n" +
					"El Apart Hotel Orilla Mansa dispone de zona de barbacoa. El establecimiento cuenta con mostrador de información turística y guardaesquíes. En los alrededores se pueden practicar actividades como esquí, senderismo o equitación.");
			orillaMansa.setDireccion("Avenida San Martin 135, 8370 San Martín de los Andes, Argentina");
			orillaMansa.setPrecioPorNoche(new BigDecimal("24286.00"));
			orillaMansa.setCategoria(bedAndBreakfast);
			orillaMansa.setCiudad(sanMartindelosAndes);
			orillaMansa.setCaracteristicas(caracteristicas2);
			orillaMansa.setImagenes(iorillaMansa);
			orillaMansa.setPoliticas(politicas1);
			orillaMansa.setCoordenadas(orillaMansac);
			productoServiceImp.saveProducto(orillaMansa);

			Producto laReservaVirginLodge  = new Producto();
			laReservaVirginLodge.setTitulo("La Reserva Virgin Lodge");
			laReservaVirginLodge.setTituloDescripcion("Con espíritu de aventura y alma gourmet");
			laReservaVirginLodge.setDescripcion("La Reserva Virgin Lodge ofrece confort de 4 estrellas y aventuras en la jungla en el corazón de la selva Iryapú, a 10 minutos en coche de las cataratas panorámicas del Iguazú. Hay conexión WiFi gratuita en todo el establecimiento.\n" +
					"\n" +
					"La piscina al aire libre está rodeada de elegantes sillones de mimbre. El establecimiento organiza paseos por la selva todos los días.\n" +
					"\n" +
					"Las habitaciones de La Reserva Virgin Lodge presentan una decoración inspirada en la selva con ventanas amplias y una elegante arquitectura en madera. Todas disponen de aire acondicionado, TV LCD y balcones privados con vistas a la selva.\n" +
					"\n" +
					"Se sirve un desayuno gourmet con jamones finos, pan de leche recién horneado y zumos naturales. El restaurante prepara platos gourmet y la enoteca tiene una extensa carta de vinos argentinos. Por un suplemento, se organizan excursiones guiadas en bicicleta y servicios de alquiler de coches.");
			laReservaVirginLodge.setDireccion("Selva Iriapú, 3370 Puerto Iguazú, Argentina");
			laReservaVirginLodge.setPrecioPorNoche(new BigDecimal("35712.00"));
			laReservaVirginLodge.setCategoria(hotel);
			laReservaVirginLodge.setCiudad(puertoIguazu);
			laReservaVirginLodge.setCaracteristicas(caracteristicas1);
			laReservaVirginLodge.setImagenes(ilaReservaVirginLodge);
			laReservaVirginLodge.setPoliticas(politicas1);
			laReservaVirginLodge.setCoordenadas(laReservaVirginLodgec);
			productoServiceImp.saveProducto(laReservaVirginLodge);

			Producto pousadaBonfim  = new Producto();
			pousadaBonfim.setTitulo("Pousada Bonfim");
			pousadaBonfim.setTituloDescripcion("Perfume marítimo y vista a Praia dos Ossos");
			pousadaBonfim.setDescripcion(
					"La Bonfim Pousada está enfrente de la Praia dos Ossos de Búzios, a 1 km de la calle Rua das Pedras. Ofrece alojamientos de ambiente acogedor y vistas panorámicas al mar desde el solárium.\n" +
							"\n" +
							"Las habitaciones son luminosas y presentan una decoración sencilla. Disponen de aire acondicionado, TV, minibar y baño privado con ducha de agua caliente.\n" +
							"\n" +
							"Todos los días se sirve un desayuno bufé en la terraza que incluye fruta fresca, pan, embutidos y una selección de bebidas frías y calientes.\n" +
							"\n" +
							"A 1 km del establecimiento hay bares, tiendas y restaurantes. La terminal de autobuses de Búzios está a 1 km de la Bonfim Pousada y el aeropuerto de Cabo Frio, a 34 km.\n");
			pousadaBonfim.setDireccion("Rua Agripino de Souza, 166, Ossos, Búzios, CEP 28950-000, Brasil");
			pousadaBonfim.setPrecioPorNoche(new BigDecimal("133450.00"));
			pousadaBonfim.setCategoria(bedAndBreakfast);
			pousadaBonfim.setCiudad(buzios);
			pousadaBonfim.setCaracteristicas(caracteristicas3);
			pousadaBonfim.setImagenes(ipousadaBonfim);
			pousadaBonfim.setPoliticas(politicas1);
			pousadaBonfim.setCoordenadas(pousadaBonfimc);
			productoServiceImp.saveProducto(pousadaBonfim);

			Producto nacionaldeCuba  = new Producto();
			nacionaldeCuba.setTitulo("Nacional de Cuba");
			nacionaldeCuba.setTituloDescripcion("Nostalgia isleña con arquitectura colonial");
			nacionaldeCuba.setDescripcion("La arquitectura colonial española en el centro de la Habana Vieja del siglo XVI incluye el Castillo de la Fuerza Real, un fuerte y un museo marítimo. El edificio del Capitolio Nacional es un monumento icónico de la década de 1920. En la Habana Vieja también se encuentra la catedral barroca de San Cristóbal y la Plaza Vieja, cuyos edificios reflejan la dinámica mezcla arquitectónica de la ciudad.");
			nacionaldeCuba.setDireccion("Calle O Esq. 21, Vedado. La Habana, Cuba");
			nacionaldeCuba.setPrecioPorNoche(new BigDecimal("360000.00"));
			nacionaldeCuba.setCategoria(hotel);
			nacionaldeCuba.setCiudad(laHabana);
			nacionaldeCuba.setCaracteristicas(caracteristicas2);
			nacionaldeCuba.setImagenes(inacionaldeCuba);
			nacionaldeCuba.setPoliticas(politicas1);
			nacionaldeCuba.setCoordenadas(nacionaldeCubac);
			productoServiceImp.saveProducto(nacionaldeCuba);


			Producto interContinental  = new Producto();
			interContinental.setTitulo("InterContinental New York Times Square");
			interContinental.setTituloDescripcion("Ambiente acogedor con vistas a la ciudad");
			interContinental.setDescripcion("\n" +
					"\n" +
					"El InterContinental New York Times Square ocupa 36 plantas y ofrece alojamiento con ventanales y vistas al perfil urbano de Manhattan, al río Hudson y al distrito de los teatros de Broadway.\n" +
					"\n" +
					"Todas las habitaciones tienen TV de pantalla plana, amplia zona de trabajo, baño inspirado en un spa con ducha de efecto lluvia a ras de suelo, cafetera, minibar y secador de pelo.\n" +
					"\n" +
					"En la 3ª planta del Intercontinental se encuentra un gimnasio abierto las 24 horas, que ofrece vistas a 44th Street y cuenta con cintas de correr y pesas libres.\n" +
					"\n" +
					"El InterContinental está a 37 metros de una parada de subte y a 322 metros de Times Square. En los alrededores hay teatros, comercios y restaurantes.\n" +
					"\n" +
					"El Stinger Cocktail Bar & Kitchen ofrece un ambiente animado, cócteles artesanales y un menú a la carta. El ambiente acogedor y las deliciosas bebidas son el lugar perfecto para disfrutar del final de la noche.\n");
			interContinental.setDireccion("300 West 44th Street, Midtown West, Nueva York, NY");
			interContinental.setPrecioPorNoche(new BigDecimal("88113.00"));
			interContinental.setCategoria(hotel);
			interContinental.setCiudad(newyork);
			interContinental.setCaracteristicas(caracteristicas1);
			interContinental.setImagenes(iinterContinental);
			interContinental.setPoliticas(politicas1);
			interContinental.setCoordenadas(interContinentalc);
			productoServiceImp.saveProducto(interContinental);

			Producto shangriLa  = new Producto();
			shangriLa.setTitulo("Shangri-La");
			shangriLa.setTituloDescripcion("Alojate en el corazón de Tokio");
			shangriLa.setDescripcion("El Shangri-La Hotel, Tokyo es un alojamiento de lujo situado frente al Palacio Imperial y al lado de la estación de tren JR de Tokio. El hotel alberga una pileta cubierta climatizada de 20 metros, un spa, varios restaurantes y un salón en el vestíbulo.\n" +
					"\n" +
					"El Shangri-La Hotel, Tokyo se halla a 2 minutos a pie de la estación de subte de Otemachi (línea Tozai, salida B7) y a 3 minutos a pie de la estación de subte de Nihonbashi (línea Ginza, salida A3). El aeropuerto de Tokio-Haneda queda a 30 minutos en auto, mientras que el aeropuerto de Narita está a 1 hora en el tren Narita Express. A pedido previa, el personal del hotel puede recoger a los huéspedes en la estación de Tokio.\n" +
					"\n" +
					"Las habitaciones están ubicadas en las últimas 11 plantas del edificio Marunouchi Trust Tower Main. Son amplias y elegantes. Además, disponen de ventanales con vistas a la ciudad, a la bahía, al palacio imperial, a la torre Tokyo Sky Tree o al monte Fuji en días claros. Todas tienen TV de pantalla plana con canales de pago, reproductor de DVD, sistema de sonido BOSE y baño privado. Cuentan con minibar y cafetera.");
			shangriLa.setDireccion("100-8283 Tokio, Chiyoda-ku, Marunouchi Trust Tower Main, 1-8-3 Marunouchi,Japón");
			shangriLa.setPrecioPorNoche(new BigDecimal("323124.00"));
			shangriLa.setCategoria(hotel);
			shangriLa.setCiudad(tokio);
			shangriLa.setCaracteristicas(caracteristicas2);
			shangriLa.setImagenes(iinterContinental);
			shangriLa.setPoliticas(politicas1);
			shangriLa.setCoordenadas(shangriLac);
			productoServiceImp.saveProducto(shangriLa);


			UserDetailsDto userDetailsDtoAdmin = new UserDetailsDto();
			userDetailsDtoAdmin.setUserId(1L);
			userDetailsDtoAdmin.setUserRol("ADMIN");

//			puntuaciones hoteles
			Puntuacion puntuacion1 = new Puntuacion();
			puntuacion1.setValor(4);
			puntuacion1.setProducto(hiltongarden);
			puntuacion1.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion1, userDetailsDtoAdmin);

			Puntuacion puntuacion2 = new Puntuacion();
			puntuacion2.setValor(5);
			puntuacion2.setProducto(nuovoMiami);
			puntuacion2.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion2, userDetailsDtoAdmin);

			Puntuacion puntuacion3 = new Puntuacion();
			puntuacion3.setValor(1);
			puntuacion3.setProducto(villadEste);
			puntuacion3.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion3, userDetailsDtoAdmin);

			Puntuacion puntuacion4 = new Puntuacion();
			puntuacion4.setValor(2);
			puntuacion4.setProducto(laSoberanaHosteria);
			puntuacion4.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion4, userDetailsDtoAdmin);

			Puntuacion puntuacion5 = new Puntuacion();
			puntuacion5.setValor(3);
			puntuacion5.setProducto(laReservaVirginLodge);
			puntuacion5.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion5, userDetailsDtoAdmin);

			Puntuacion puntuacion6 = new Puntuacion();
			puntuacion6.setValor(3);
			puntuacion6.setProducto(nacionaldeCuba);
			puntuacion6.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion6, userDetailsDtoAdmin);

			Puntuacion puntuacion7 = new Puntuacion();
			puntuacion7.setValor(5);
			puntuacion7.setProducto(interContinental);
			puntuacion7.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion7, userDetailsDtoAdmin);

			Puntuacion puntuacion8 = new Puntuacion();
			puntuacion8.setValor(4);
			puntuacion8.setProducto(shangriLa);
			puntuacion8.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion8, userDetailsDtoAdmin);

//			puntuaciones hostel
			Puntuacion puntuacion9 = new Puntuacion();
			puntuacion9.setValor(3);
			puntuacion9.setProducto(urbanyHostel);
			puntuacion9.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion9, userDetailsDtoAdmin);

			Puntuacion puntuacion13 = new Puntuacion();
			puntuacion13.setValor(4);
			puntuacion13.setProducto(laBrisa);
			puntuacion13.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion13, userDetailsDtoAdmin);

//			putuaciones departamentos
			Puntuacion puntuacion10 = new Puntuacion();
			puntuacion10.setValor(2);
			puntuacion10.setProducto(utaka);
			puntuacion10.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion10, userDetailsDtoAdmin);

//			puntuaciones bed and breakfast
			Puntuacion puntuacion11 = new Puntuacion();
			puntuacion11.setValor(4);
			puntuacion11.setProducto(orillaMansa);
			puntuacion11.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion11, userDetailsDtoAdmin);

			Puntuacion puntuacion12 = new Puntuacion();
			puntuacion12.setValor(5);
			puntuacion12.setProducto(pousadaBonfim);
			puntuacion12.setUsuario(usuarioAdmin);
			puntuacionServiceImp.savePuntuacion(puntuacion12, userDetailsDtoAdmin);
		};
	}
}
