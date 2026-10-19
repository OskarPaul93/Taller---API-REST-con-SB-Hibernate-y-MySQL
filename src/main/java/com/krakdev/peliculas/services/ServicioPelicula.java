package com.krakdev.peliculas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.repository.PeliculaRepository;

@Service
public class ServicioPelicula {

    @Autowired
    private PeliculaRepository peliculaRepository;

    // Crear una pelicula
    public Pelicula crear(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    // Listar todas las peliculas
    public List<Pelicula> listar() {
        return peliculaRepository.findAll();
    }

    // Buscar una pelicula por su ID
    public Pelicula buscarPorId(Long id) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(id);

        return pelicula.orElse(null);
    }

    // Actualizar una pelicula
    public Pelicula actualizar(Long id, Pelicula peliculaActualizada) {

        Optional<Pelicula> peliculaExistente =
                peliculaRepository.findById(id);

        if (peliculaExistente.isPresent()) {
            Pelicula pelicula = peliculaExistente.get();

            pelicula.setNombre(peliculaActualizada.getNombre());
            pelicula.setDirector(peliculaActualizada.getDirector());
            pelicula.setGenero(peliculaActualizada.getGenero());
            pelicula.setDuracion(peliculaActualizada.getDuracion());
            pelicula.setDisponible(peliculaActualizada.isDisponible());

            return peliculaRepository.save(pelicula);
        }

        return null;
    }

    // Eliminar una pelicula
    public boolean eliminar(Long id) {

        if (peliculaRepository.existsById(id)) {
            peliculaRepository.deleteById(id);
            return true;
        }

        return false;
    }

    // Buscar peliculas por genero
    public List<Pelicula> buscarPorGenero(String genero) {
        return peliculaRepository.findByGenero(genero);
    }

    // Buscar peliculas por disponibilidad
    public List<Pelicula> buscarPorDisponible(boolean disponible) {
        return peliculaRepository.findByDisponible(disponible);
    }
}
