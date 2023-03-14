package com.dh.digitalbooking;

import com.dh.digitalbooking.model.*;
import com.dh.digitalbooking.repository.*;
import com.dh.digitalbooking.service.imp.CategoriaServiceImp;
import com.dh.digitalbooking.service.imp.ProductoServiceImp;
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
			PasswordEncoder passwordEncoder
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

			Categoria hotel = categoriaServiceImp.saveCategoria(new Categoria("Hotel", "Descripcion de la categoria Hotel", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
			Categoria hostel = categoriaServiceImp.saveCategoria(new Categoria("Hostel", "Descripcion de la categoria Hostel", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80"));
			Categoria departamento = categoriaServiceImp.saveCategoria(new Categoria("Departamento", "Descripcion de la categoria Departamento", "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));
			Categoria bedAndBreakfast = categoriaServiceImp.saveCategoria(new Categoria("Bed and breakfast", "Descripcion de la categoria Bed and breakfast", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"));

			Pais argentina = paisRepository.save(new Pais("Argentina"));
			Pais chile = paisRepository.save(new Pais("Chile"));
			Pais uruguay = paisRepository.save(new Pais("Uruguay"));
			Pais brasil = paisRepository.save(new Pais("Brasil"));
			Pais colombia = paisRepository.save(new Pais("Colombia"));

			Ciudad buenosAires = ciudadRepository.save(new Ciudad("Buenos Aires", argentina));
			Ciudad marDelPlata = ciudadRepository.save(new Ciudad("Mar del Plata", argentina));
			Ciudad laPlata = ciudadRepository.save(new Ciudad("La Plata", argentina));
			Ciudad rosario = ciudadRepository.save(new Ciudad("Rosario", argentina));
			Ciudad santiago = ciudadRepository.save(new Ciudad("Santiago", chile));
			Ciudad monteVideo = ciudadRepository.save(new Ciudad("Montevide", uruguay));
			Ciudad rioDeJaneiro = ciudadRepository.save(new Ciudad("Rio de Janeiro", brasil));
			Ciudad cali = ciudadRepository.save(new Ciudad("Cali", colombia));
			Ciudad bogota = ciudadRepository.save(new Ciudad("Bogotá", colombia));
			Ciudad medellin = ciudadRepository.save(new Ciudad("Medellín", colombia));
			Ciudad cartagena = ciudadRepository.save(new Ciudad("Cartagena", colombia));
			Ciudad barranquilla = ciudadRepository.save(new Ciudad("Barranquilla", colombia));

			Caracteristica wifi = caracteristicaRepository.save(new Caracteristica("Wifi"));
			Caracteristica cocina = caracteristicaRepository.save(new Caracteristica("Cocina"));
			Caracteristica televisor = caracteristicaRepository.save(new Caracteristica("Televisor"));
			Caracteristica pileta = caracteristicaRepository.save(new Caracteristica("Pileta"));
			Caracteristica aptoMascotas = caracteristicaRepository.save(new Caracteristica("Apto mascotas"));

			TipoPolitica normasDeLaCasa = tipoPoliticaRepository.save(new TipoPolitica("Normas de la casa"));
			TipoPolitica saludYSeguridad = tipoPoliticaRepository.save(new TipoPolitica("Salud y seguridad"));
			TipoPolitica politicaDeCancelacion = tipoPoliticaRepository.save(new TipoPolitica("Politicas de cancelacion"));

			Set<Caracteristica> caracteristicas1 = new HashSet<>(Set.of(wifi, cocina, televisor));
			Set<Caracteristica> caracteristicas2 = new HashSet<>(Set.of(pileta, aptoMascotas, televisor));
			Set<Caracteristica> caracteristicas3 = new HashSet<>(Set.of(wifi, cocina, televisor, pileta, aptoMascotas));

			Imagen imagen1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen imagen2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
			Imagen imagen3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
			Imagen imagen4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
			Imagen imagen5 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");

			Set<Imagen> imagenesSet5 = new HashSet<>(Set.of(imagen1, imagen2, imagen3, imagen4, imagen5));
			Set<Imagen> imagenesSet4 = new HashSet<>(Set.of(imagen1, imagen2, imagen3, imagen4));
			Set<Imagen> imagenesSet3 = new HashSet<>(Set.of(imagen1, imagen2, imagen3));
			Set<Imagen> imagenesSet2 = new HashSet<>(Set.of(imagen1, imagen2));
			Set<Imagen> imagenesSet1 = new HashSet<>(Set.of(imagen1));

			Politica politica1 = new Politica("Check-out: 10:00", normasDeLaCasa);
			Politica politica2 = new Politica("No se permiten fiestas", normasDeLaCasa);
			Politica politica3 = new Politica("Detector de humo", saludYSeguridad);
			Politica politica4 = new Politica("Deposito de seguridad", saludYSeguridad);
			Politica politica5 = new Politica("Agregá las fechas de tu viaje para obtener los detalles de cancelación de esta estadía", politicaDeCancelacion);

			Set<Politica> politicas1 = new HashSet<>(Set.of(politica1, politica2, politica3, politica4, politica5));
			Set<Politica> politicas2 = new HashSet<>(Set.of(politica3, politica4, politica5));
			Set<Politica> politicas3 = new HashSet<>(Set.of(politica1));

			Coordenadas coordenadas = new Coordenadas(
					new BigDecimal("-34.55044"),
					new BigDecimal("-58.47996"));

			Producto hotel1 = new Producto();
			hotel1.setTitulo("Hotel 1");
			hotel1.setTituloDescripcion("Titulo de la descripcion del hotel 1");
			hotel1.setDescripcion("Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
			hotel1.setDireccion("Av Congreso 3423");
			hotel1.setPrecioPorNoche(new BigDecimal("5200.00"));
			hotel1.setCategoria(hotel);
			hotel1.setCiudad(buenosAires);
			hotel1.setCaracteristicas(caracteristicas1);
			hotel1.setImagenes(imagenesSet5);
			hotel1.setPoliticas(politicas1);
			hotel1.setCoordenadas(coordenadas);
			productoServiceImp.saveProducto(hotel1);


//			Producto hostel1 = new Producto(
//					"Hostel 1",
//					"Titulo de la descripcion del hostel 1",
//					"Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries,",
//					hostel,
//					rioDeJaneiro,
//					caracteristicas2,
//					imagenesSet4,
//					politicas2,
//					coordenadas
//			);
//
			Producto departamento1 = new Producto(
					"Departamento 1",
					"Titulo de la descripcion del departamento 1",
					"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc",
					departamento,
					cali,
					caracteristicas3,
					imagenesSet3,
					politicas3,
					coordenadas
			);
			departamento1.setTitulo("Departamento 1");
			departamento1.setTituloDescripcion("Titulo de la descripcion del departamento 1");
			departamento1.setDescripcion("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc");
			departamento1.setDireccion("Av Rivadavia 8423");
			departamento1.setPrecioPorNoche(new BigDecimal("6600.00"));
			departamento1.setCategoria(departamento);
			departamento1.setCiudad(cali);
			departamento1.setCaracteristicas(caracteristicas3);
			departamento1.setImagenes(imagenesSet3);
			departamento1.setPoliticas(politicas3);
			departamento1.setCoordenadas(coordenadas);
			productoServiceImp.saveProducto(departamento1);





//			Producto bedAndBreakfast1 = new Producto(
//					"Bed and breakfast 1",
//					"Titulo de la descripcion del Bed and breakfast 1",
//					"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc",
//					bedAndBreakfast,
//					santiago,
//					caracteristicas1,
//					imagenesSet2,
//					politicas1,
//					coordenadas
//			);
//
//			Producto bedAndBreakfast2 = new Producto(
//					"Bed and breakfast 2",
//					"Titulo de la descripcion del Bed and breakfast 2",
//					"There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc",
//					bedAndBreakfast,
//					buenosAires,
//					caracteristicas1,
//					imagenesSet1,
//					new HashSet<>(),
//					coordenadas
//			);

//			productoServiceImp.saveProducto(hostel1);
//			productoServiceImp.saveProducto(bedAndBreakfast1);
//			productoServiceImp.saveProducto(bedAndBreakfast2);
		};
	}
}
