package com.fiis.peliculas.controllers;

import com.fiis.peliculas.entities.Pelicula;
import com.fiis.peliculas.entities.Resena;
import com.fiis.peliculas.services.IResenaService;
import com.fiis.peliculas.services.PeliculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResenaController {

    @Autowired
    private IResenaService resenaService;

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/pelicula/{id}/detalle")
    public String detalle(@PathVariable Long id, Model model) {
        Pelicula pelicula = peliculaService.findById(id);

        if (pelicula == null) {
            return "redirect:/home";
        }

        model.addAttribute("pelicula", pelicula);
        model.addAttribute("resenas", resenaService.findByPeliculaId(id));
        model.addAttribute("resena", new Resena());
        model.addAttribute("titulo", "Detalle de " + pelicula.getNombre());

        return "detalle";
    }

    @PostMapping("/resena")
    public String guardar(@Valid Resena resena,
                          BindingResult br,
                          @RequestParam Long peliculaId,
                          RedirectAttributes redirectAtt) {

        Pelicula pelicula = peliculaService.findById(peliculaId);

        if (br.hasErrors()) {
            redirectAtt.addFlashAttribute("error", "Por favor complete todos los campos correctamente");
            return "redirect:/pelicula/" + peliculaId + "/detalle";
        }

        resena.setPelicula(pelicula);
        resenaService.save(resena);

        redirectAtt.addFlashAttribute("msj", "¡Reseña guardada exitosamente!");
        redirectAtt.addFlashAttribute("tipoMsj", "success");

        return "redirect:/pelicula/" + peliculaId + "/detalle";
    }
}