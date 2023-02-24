package com.proyectoIntegrador.sprint1;

import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.service.imp.CategoriaServiceImp;
import com.proyectoIntegrador.sprint1.service.imp.ProductoServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sprint1Application.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(
			CategoriaServiceImp categoriaServiceImp,
			ProductoServiceImp productoServiceImp
	) {
		return args -> {
			Categoria hotel = new Categoria(
					"Hotel",
					"Descripcion de la categoria Hotel",
					"http://imagen-categoria-hotel.com");
			Categoria departamento = new Categoria(
					"Departamento",
					"Descripcion de la categoria Departamento",
					"http://imagen-categoria-departamento.com");
			Categoria bedAndBreakfast = new Categoria(
					"Bed and breakfast",
					"Descripcion de la categoria Bed and breakfast",
					"http://imagen-categoria-Bed and breakfast.com");

			categoriaServiceImp.saveCategoria(hotel);
			categoriaServiceImp.saveCategoria(departamento);
			categoriaServiceImp.saveCategoria(bedAndBreakfast);

		};
	}
}
