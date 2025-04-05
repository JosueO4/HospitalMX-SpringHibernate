package com.mycompany.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mycompany.app.entity.Especialidad;
import com.mycompany.app.entity.Medico;
import com.mycompany.app.repository.EspecialidadRepository;
import com.mycompany.app.repository.MedicoRepository;

@Controller
public class MedicoController {
    
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    EspecialidadRepository especialidadRepository;

    @GetMapping("/medico")
    public String getAllMedico(Model model) {

        GenericCollection<Medico> medicos = new GenericCollection<Medico>(medicoRepository.findAll());
        model.addAttribute("medicos",medicos.getItems());
        //List<Medico> medicos = medicoRepository.findAll();
        //model.addAttribute("medicos",medicos);
        return "medico";
    }

    @GetMapping("/addmedico")
    public String addMedico(){
		return "addmedico";
	}

    @PostMapping("/addmedico")
    public String AddMedicoProcess(@RequestParam int cedula ,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String correo, @RequestParam int especialidad_id){

        Optional<Especialidad> especialidad = especialidadRepository.findById(especialidad_id);
        Especialidad especialidad_info = especialidad.get();
        Medico medico = new Medico(cedula,nombre,apellido,correo,especialidad_info);
		medicoRepository.save(medico);
		return "redirect:/medico";
    }

    @GetMapping("/findmedicobyid")
    public String getMedicobyId(@RequestParam(name = "id") int id, Model model) {
        Optional<Medico> medico = medicoRepository.findById(id);
        model.addAttribute("medico", medico.orElse(null)); 
        return "findmedicobyid";
    }

    @GetMapping("/updatemedico/{id}")
    public String updateMedico(@PathVariable(value="id") int id, Model model) {
        Optional<Medico> medico = medicoRepository.findById(id);
		Medico medico_info = medico.get();
		model.addAttribute("id",id);
		model.addAttribute("medico_info",medico_info);
		return "updatemedico";
    }

    @PostMapping("/updatemedico/{id}")
	public String updateMedicoProcess(@PathVariable(value="id") Integer id,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String correo, @RequestParam int especialidad_id,Model model) {

		Optional<Medico> medico = medicoRepository.findById(id);
        Optional<Especialidad> especialidad = especialidadRepository.findById(especialidad_id);
        Medico medico_info = medico.get();
		Especialidad especialidad_info = especialidad.get();
		medico_info.setNombre(nombre);
		medico_info.setApellido(apellido);
		medico_info.setCorreo(correo);
		medico_info.setEspecialidad(especialidad_info);
        medicoRepository.save(medico_info);
		return "redirect:/medico";
	}

    @GetMapping("/deletemedico/{id}")
	public String deleteMedico(@PathVariable int id) {  
		medicoRepository.deleteById(id);
        return "redirect:/medico";
	}

    @GetMapping("/listademedicos")
    public String listarMedicos(Model model) {
        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos",medicos);
        return "listademedicos";
    }

}
