package com.mycompany.app.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.mycompany.app.entity.Especialidad;
import com.mycompany.app.repository.EspecialidadRepository;

@Controller
public class EspecialidadController {
    
    @Autowired
    EspecialidadRepository especialidadRepository;

    @GetMapping("/especialidad")
    public String getAllEspecialidades(Model model) {
        List<Especialidad> especialidades = especialidadRepository.findAll();
        model.addAttribute("especialidades",especialidades);
        return "especialidad";
    }

    @GetMapping("/addespecialidad")
    public String addPadecimiento(){
		return "addespecialidad";
	}

    @PostMapping("/addespecialidad")
    public String AddEspecialidadProcess(@RequestParam String nombre,@RequestParam String descripcion){

        Especialidad especialidad =new Especialidad(nombre,descripcion);
		especialidadRepository.save(especialidad);
		return "redirect:/especialidad";
    }

    @GetMapping("/findespecialidadbyid")
    public String getEspecialidadbyId(@RequestParam(name = "id") int id, Model model) {
        if (!especialidadRepository.existsById(id)) {
            System.err.println("No existe el id");
            return "error";
        }else{
        Optional<Especialidad> especialidad = especialidadRepository.findById(id);
        model.addAttribute("especialidad", especialidad.orElse(null)); 
        return "findespecialidadbyid";

        }
  
        
        
    }

    @GetMapping("/updateespecialidad/{id}")
    public String updateEspecialidad(@PathVariable(value="id") int id, Model model) {
        Optional<Especialidad> especialidad = especialidadRepository.findById(id);
		Especialidad especialidad_info = especialidad.get();
		model.addAttribute("id",id);
		model.addAttribute("especialidad_info",especialidad_info);
		return "updateespecialidad";
    }

    @PostMapping("/updateespecialidad/{id}")
	public String updateEspecialidadProcess(@PathVariable(value="id") Integer id,@RequestParam String nombre,@RequestParam String descripcion ,Model model) {

		Optional<Especialidad> especialidad = especialidadRepository.findById(id);
		Especialidad especialidad_info = especialidad.get();
		especialidad_info.setNombre(nombre);
		especialidad_info.setDescripcion(descripcion);
		especialidadRepository.save(especialidad_info);
		return "redirect:/especialidad";
	}

    @GetMapping("/deleteespecialidad/{id}")
	public String deleteEspecialidad(@PathVariable int id) {  
		especialidadRepository.deleteById(id);
        return "redirect:/especialidad";
	}
}
