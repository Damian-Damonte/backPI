package com.dh.digitalbooking;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.entity.*;
import com.dh.digitalbooking.repository.*;
import com.dh.digitalbooking.service.imp.CategoriaServiceImp;
import com.dh.digitalbooking.service.imp.ProductoServiceImp;
import com.dh.digitalbooking.service.imp.PuntuacionServiceImp;
import com.dh.digitalbooking.service.imp.ReservaServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
            PuntuacionServiceImp puntuacionServiceImp,
            ReservaServiceImp reservaServiceImp
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

            Categoria hotel = categoriaServiceImp.saveCategoria(new Categoria("Hotel", "Descripcion de la categoria Hotel", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27"));
            Categoria hostel = categoriaServiceImp.saveCategoria(new Categoria("Hostel", "Descripcion de la categoria Hostel", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27"));
            Categoria departamento = categoriaServiceImp.saveCategoria(new Categoria("Departamento", "Descripcion de la categoria Departamento", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27"));
//            Categoria bedAndBreakfast = categoriaServiceImp.saveCategoria(new Categoria("Bed and breakfast", "Descripcion de la categoria Bed and breakfast", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27"));

            Country argentina = paisRepository.save(new Country("Argentina"));
            Country italia = paisRepository.save(new Country("Italia"));
            Country brasil = paisRepository.save(new Country("Brasil"));
            Country colombia = paisRepository.save(new Country("Colombia"));
            Country estadosUnidos = paisRepository.save(new Country("Estados Unidos"));
            Country espania = paisRepository.save(new Country("España"));
            Country cuba = paisRepository.save(new Country("Cuba"));
            Country japon = paisRepository.save(new Country("Japón"));

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
            Ciudad buenosaires = ciudadRepository.save(new Ciudad("Buenos Aires", argentina));
            Ciudad roma = ciudadRepository.save(new Ciudad("Roma", italia));
            Ciudad florencia = ciudadRepository.save(new Ciudad("Florencia", italia));

//            Caracteristica wifi = caracteristicaRepository.save(new Caracteristica("Wifi"));
            Caracteristica cocina = caracteristicaRepository.save(new Caracteristica("Cocina"));
            Caracteristica televisor = caracteristicaRepository.save(new Caracteristica("Televisor"));
            Caracteristica pileta = caracteristicaRepository.save(new Caracteristica("Pileta"));
            Caracteristica aptoMascotas = caracteristicaRepository.save(new Caracteristica("Apto mascotas"));
            Caracteristica parrilla = caracteristicaRepository.save(new Caracteristica("Parrilla"));
            Caracteristica lavadora = caracteristicaRepository.save(new Caracteristica("Lavadora"));
            Caracteristica banioPrivado = caracteristicaRepository.save(new Caracteristica("Baño privado"));
            Caracteristica noFumar = caracteristicaRepository.save(new Caracteristica("No fumar"));
            Caracteristica vistaCiudad = caracteristicaRepository.save(new Caracteristica("Vistas a la ciudad"));

            Caracteristica desayuno = caracteristicaRepository.save(new Caracteristica("Desayuno incluido"));
            Caracteristica calefaccion = caracteristicaRepository.save(new Caracteristica("Calefaccion"));
            Caracteristica gimnasio = caracteristicaRepository.save(new Caracteristica("Gimnasio"));
            Caracteristica recepcion24 = caracteristicaRepository.save(new Caracteristica("Recepcion 24hs"));
            Caracteristica spa = caracteristicaRepository.save(new Caracteristica("Spa"));
            Caracteristica seguridad24 = caracteristicaRepository.save(new Caracteristica("Seguridad las 24hs"));
            Caracteristica bicicletas = caracteristicaRepository.save(new Caracteristica("Alquiler de bicicletas"));
            Caracteristica conferencias = caracteristicaRepository.save(new Caracteristica("Sala de conferencias"));
            Caracteristica restaurant = caracteristicaRepository.save(new Caracteristica("Restaurant"));
            Caracteristica playa = caracteristicaRepository.save(new Caracteristica("Acceso a la playa"));
            Caracteristica infantil = caracteristicaRepository.save(new Caracteristica("Zona infantil"));
            Caracteristica bar = caracteristicaRepository.save(new Caracteristica("Bar"));
            Caracteristica cajaFuerte = caracteristicaRepository.save(new Caracteristica("Caja fuerte"));
            Caracteristica minibar = caracteristicaRepository.save(new Caracteristica("Minibar"));
            Caracteristica chimenea = caracteristicaRepository.save(new Caracteristica("Chimenea"));

            TipoPolitica normasDeLaCasa = tipoPoliticaRepository.save(new TipoPolitica("Normas de la casa"));
            TipoPolitica saludYSeguridad = tipoPoliticaRepository.save(new TipoPolitica("Salud y seguridad"));
            TipoPolitica politicaDeCancelacion = tipoPoliticaRepository.save(new TipoPolitica("Politicas de cancelacion"));

            Politica politica1 = new Politica(
                """
                            Se aplican las pautas de distanciamiento social y otras normas relacionadas con el coronavirus
                            Detector de humo
                            Depósito de seguridad
                            Camaras de seguridad
                            Matafuegos
                            """
                    , saludYSeguridad);

            Politica politica2 = new Politica(
                    "Check-out: 22:00\n"+
                            "No se permiten fiestas\n" +
                            "No fumar\n" +
                            "Check-in: 10:00",
                    normasDeLaCasa
            );

            Politica politica3 = new Politica(
                    "Agregá las fechas de tu viaje para obtener los detalles de cancelacion de esta estadía",
                    politicaDeCancelacion
            );
            Set<Politica> setPoliticas = new HashSet<>(Set.of(politica1, politica2, politica3));

            Set<Caracteristica> caracteristicas1 = new HashSet<>(Set.of(televisor, desayuno, bar, noFumar));
            Set<Caracteristica> caracteristicas2 = new HashSet<>(Set.of(cajaFuerte, parrilla, lavadora, pileta, aptoMascotas, seguridad24));
            Set<Caracteristica> caracteristicas3 = new HashSet<>(Set.of(televisor, pileta, aptoMascotas, banioPrivado, vistaCiudad, parrilla, lavadora));
            Set<Caracteristica> caracteristicas4 = new HashSet<>(Set.of(desayuno, calefaccion, gimnasio, recepcion24, spa, noFumar, bicicletas, conferencias, restaurant));
            Set<Caracteristica> caracteristicas5 = new HashSet<>(Set.of(playa, infantil, bar, cajaFuerte, seguridad24, pileta, aptoMascotas, televisor));
            Set<Caracteristica> caracteristicas6 = new HashSet<>(Set.of( desayuno, bicicletas, televisor, playa, noFumar, aptoMascotas, restaurant));


//          FOTOS 1
            Imagen foto1 = new Imagen("Imagen 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
            Imagen foto2 = new Imagen("Imagen 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
            Imagen foto3 = new Imagen("Imagen 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
            Imagen foto4 = new Imagen("Imagen 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
            Imagen foto5 = new Imagen("Imagen 5", "https://images.unsplash.com/photo-1455587734955-081b22074882?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aG90ZWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
            Imagen foto6 = new Imagen("Imagen 6", "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8aG90ZWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 1);

            Set<Imagen> setFotos= new HashSet<>(Set.of(foto1, foto2, foto3, foto4, foto5, foto6));


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

            Producto producto1 = new Producto();
            producto1.setTitulo("Hotel numero 1");
            producto1.setTituloDescripcion("Titulo descripcion hotel 1");
            producto1.setDescripcion("Esta es la descripcion del hotel numero 1 y esto es un poco de texto de relleno");
            producto1.setDireccion("Avenida Congreso 2342");
            producto1.setPrecioPorNoche(new BigDecimal("25500.00"));
            producto1.setCategoria(hotel);
            producto1.setCiudad(barcelona);
            producto1.setCaracteristicas(caracteristicas1);
            producto1.setImagenes(setFotos);
            producto1.setPoliticas(setPoliticas);
            producto1.setCoordenadas(hiltongardenc);
            productoServiceImp.saveProducto(producto1);

            UserDetailsDto userDetailsDtoAdmin = new UserDetailsDto();
            userDetailsDtoAdmin.setUserId(1L);
            userDetailsDtoAdmin.setUserRol("ADMIN");

            Puntuacion puntuacionProducto1 = new Puntuacion();
            puntuacionProducto1.setValor(4);
            puntuacionProducto1.setProducto(producto1);
            puntuacionProducto1.setUsuario(usuarioAdmin);
            puntuacionServiceImp.savePuntuacion(puntuacionProducto1, userDetailsDtoAdmin);

            Reserva reserva1 = new Reserva();
            reserva1.setCheckIn(LocalDate.of(2023, 6, 22));
            reserva1.setCheckOut(LocalDate.of(2023, 6, 25));
            reserva1.setHoraLlegada(LocalTime.now());
            reserva1.setCiudadUsuario("Buenos aires");
            reserva1.setUsuario(usuarioAdmin);
            reserva1.setProducto(producto1);
            reservaServiceImp.saveReserva(reserva1, userDetailsDtoAdmin);


        };
    }
}
