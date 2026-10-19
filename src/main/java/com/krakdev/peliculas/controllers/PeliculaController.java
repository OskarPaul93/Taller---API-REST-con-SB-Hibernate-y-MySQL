package com.krakdev.peliculas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.services.ServicioPelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private ServicioPelicula servicioPelicula;

    // Registrar una pelicula
    @PostMapping
    public ResponseEntity<Pelicula> crear(
            @RequestBody Pelicula pelicula) {

        Pelicula peliculaCreada = servicioPelicula.crear(pelicula);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(peliculaCreada);
    }

    // Listar todas las peliculas
    @GetMapping
    public ResponseEntity<List<Pelicula>> listar() {

        List<Pelicula> peliculas = servicioPelicula.listar();

        return ResponseEntity.ok(peliculas);
    }

    // Buscar pelicula por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> buscarPorId(
            @PathVariable Long id) {

        Pelicula pelicula = servicioPelicula.buscarPorId(id);

        if (pelicula == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pelicula);
    }

    // Actualizar pelicula
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(
            @PathVariable Long id,
            @RequestBody Pelicula pelicula) {

        Pelicula peliculaActualizada =
                servicioPelicula.actualizar(id, pelicula);

        if (peliculaActualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(peliculaActualizada);
    }

    // Eliminar pelicula
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        boolean eliminada = servicioPelicula.eliminar(id);

        if (!eliminada) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Buscar peliculas por genero
    @GetMapping("/genero")
    public ResponseEntity<List<Pelicula>> buscarPorGenero(
            @RequestParam String genero) {

        List<Pelicula> peliculas =
                servicioPelicula.buscarPorGenero(genero);

        return ResponseEntity.ok(peliculas);
    }

    // Buscar peliculas por disponibilidad
    @GetMapping("/disponible")
    public ResponseEntity<List<Pelicula>> buscarPorDisponible(
            @RequestParam boolean disponible) {

        List<Pelicula> peliculas =
                servicioPelicula.buscarPorDisponible(disponible);

        return ResponseEntity.ok(peliculas);
    }
}
