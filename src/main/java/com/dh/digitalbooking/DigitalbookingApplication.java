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
            CountryRepository countryRepository,
            CityRepository cityRepository,
            ProductoServiceImp productoServiceImp,
            AmenityRepository amenityRepository,
            PolicyTypeRepository policyTypeRepository,
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

            Country argentina = countryRepository.save(new Country("Argentina"));
            Country italia = countryRepository.save(new Country("Italia"));
            Country brasil = countryRepository.save(new Country("Brasil"));
            Country colombia = countryRepository.save(new Country("Colombia"));
            Country estadosUnidos = countryRepository.save(new Country("Estados Unidos"));
            Country espania = countryRepository.save(new Country("España"));
            Country cuba = countryRepository.save(new Country("Cuba"));
            Country japon = countryRepository.save(new Country("Japón"));

            City puertoIguazu = cityRepository.save(new City("Puerto Iguazú", argentina));
            City sanMartindelosAndes = cityRepository.save(new City("San Martín de los Andes", argentina));
            City elCalafate = cityRepository.save(new City("El Calafate", argentina));
            City neuquen = cityRepository.save(new City("Neuquen", argentina));
            City barcelona = cityRepository.save(new City("Barcelona", espania));
            City grado = cityRepository.save(new City("Grado", italia));
            City ushuaia = cityRepository.save(new City("Ushuaia", argentina));
            City buzios = cityRepository.save(new City("Buzios", brasil));
            City miami = cityRepository.save(new City("Miami", estadosUnidos));
            City santaMa = cityRepository.save(new City("Santa María", colombia));
            City laHabana = cityRepository.save(new City("La Habana", cuba));
            City newyork = cityRepository.save(new City("New York", estadosUnidos));
            City tokio = cityRepository.save(new City("Tokio", japon));
            City buenosaires = cityRepository.save(new City("Buenos Aires", argentina));
            City roma = cityRepository.save(new City("Roma", italia));
            City florencia = cityRepository.save(new City("Florencia", italia));

//            Amenity wifi = caracteristicaRepository.save(new Amenity("Wifi"));
            Amenity cocina = amenityRepository.save(new Amenity("Cocina"));
            Amenity televisor = amenityRepository.save(new Amenity("Televisor"));
            Amenity pileta = amenityRepository.save(new Amenity("Pileta"));
            Amenity aptoMascotas = amenityRepository.save(new Amenity("Apto mascotas"));
            Amenity parrilla = amenityRepository.save(new Amenity("Parrilla"));
            Amenity lavadora = amenityRepository.save(new Amenity("Lavadora"));
            Amenity banioPrivado = amenityRepository.save(new Amenity("Baño privado"));
            Amenity noFumar = amenityRepository.save(new Amenity("No fumar"));
            Amenity vistaCiudad = amenityRepository.save(new Amenity("Vistas a la city"));

            Amenity desayuno = amenityRepository.save(new Amenity("Desayuno incluido"));
            Amenity calefaccion = amenityRepository.save(new Amenity("Calefaccion"));
            Amenity gimnasio = amenityRepository.save(new Amenity("Gimnasio"));
            Amenity recepcion24 = amenityRepository.save(new Amenity("Recepcion 24hs"));
            Amenity spa = amenityRepository.save(new Amenity("Spa"));
            Amenity seguridad24 = amenityRepository.save(new Amenity("Seguridad las 24hs"));
            Amenity bicicletas = amenityRepository.save(new Amenity("Alquiler de bicicletas"));
            Amenity conferencias = amenityRepository.save(new Amenity("Sala de conferencias"));
            Amenity restaurant = amenityRepository.save(new Amenity("Restaurant"));
            Amenity playa = amenityRepository.save(new Amenity("Acceso a la playa"));
            Amenity infantil = amenityRepository.save(new Amenity("Zona infantil"));
            Amenity bar = amenityRepository.save(new Amenity("Bar"));
            Amenity cajaFuerte = amenityRepository.save(new Amenity("Caja fuerte"));
            Amenity minibar = amenityRepository.save(new Amenity("Minibar"));
            Amenity chimenea = amenityRepository.save(new Amenity("Chimenea"));

            PolicyType normasDeLaCasa = policyTypeRepository.save(new PolicyType("Normas de la casa"));
            PolicyType saludYSeguridad = policyTypeRepository.save(new PolicyType("Salud y seguridad"));
            PolicyType politicaDeCancelacion = policyTypeRepository.save(new PolicyType("Politicas de cancelacion"));

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

            Set<Amenity> caracteristicas1 = new HashSet<>(Set.of(televisor, desayuno, bar, noFumar));
            Set<Amenity> caracteristicas2 = new HashSet<>(Set.of(cajaFuerte, parrilla, lavadora, pileta, aptoMascotas, seguridad24));
            Set<Amenity> caracteristicas3 = new HashSet<>(Set.of(televisor, pileta, aptoMascotas, banioPrivado, vistaCiudad, parrilla, lavadora));
            Set<Amenity> caracteristicas4 = new HashSet<>(Set.of(desayuno, calefaccion, gimnasio, recepcion24, spa, noFumar, bicicletas, conferencias, restaurant));
            Set<Amenity> caracteristicas5 = new HashSet<>(Set.of(playa, infantil, bar, cajaFuerte, seguridad24, pileta, aptoMascotas, televisor));
            Set<Amenity> caracteristicas6 = new HashSet<>(Set.of( desayuno, bicicletas, televisor, playa, noFumar, aptoMascotas, restaurant));


//          FOTOS 1
            Image foto1 = new Image("Image 1", "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
            Image foto2 = new Image("Image 2", "https://images.unsplash.com/photo-1555854877-bab0e564b8d5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80%27");
            Image foto3 = new Image("Image 3", "https://images.unsplash.com/photo-1563298723-dcfebaa392e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1767&q=80%27");
            Image foto4 = new Image("Image 4", "https://images.unsplash.com/photo-1463620910506-d0458143143e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80%27");
            Image foto5 = new Image("Image 5", "https://images.unsplash.com/photo-1455587734955-081b22074882?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aG90ZWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
            Image foto6 = new Image("Image 6", "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8aG90ZWx8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60", 1);

            Set<Image> setFotos= new HashSet<>(Set.of(foto1, foto2, foto3, foto4, foto5, foto6));


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
