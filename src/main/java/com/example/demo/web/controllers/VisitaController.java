package com.example.demo.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.VisitaService;

@RestController
@RequestMapping("/visitas")
public class VisitaController {

    private VisitaService visitaService;

}
