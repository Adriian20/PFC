package com.example.demo.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VisitaDTO;
import com.example.demo.service.VisitaService;

@RestController
@RequestMapping("/visits")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @GetMapping("/allVisits")
    public List<VisitaDTO> listarVisitas() {
        List<VisitaDTO> visitas = visitaService.getAll();
        return visitas;
    }

    @GetMapping("/visit/{id}")
    public ResponseEntity<VisitaDTO> visitasId(@PathVariable Long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        } else {
            VisitaDTO visitaDTO = new VisitaDTO();
            visitaDTO.setId(id);
            Optional<VisitaDTO> opt = Optional.ofNullable(visitaService.findById(visitaDTO));
            if (opt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok(opt.get());
            }
        }
    }
}
