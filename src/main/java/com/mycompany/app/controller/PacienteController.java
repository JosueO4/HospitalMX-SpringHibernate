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

import com.mycompany.app.App;
import com.mycompany.app.entity.Medico;
import com.mycompany.app.entity.Paciente;
import com.mycompany.app.repository.MedicoRepository;
import com.mycompany.app.repository.PacienteRepository;


@Controller
public class PacienteController {
    
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    MedicoRepository medicoRepository;

    @GetMapping("/paciente")
    public String getAllPaciente(Model model) {
        List<Paciente> pacientes = pacienteRepository.findAll();
        model.addAttribute("pacientes",pacientes);
        return "paciente";
    }

    
    @GetMapping("/addpaciente")
    public String addPaciente(){
		return "addpaciente";
	}

    @PostMapping("/addpaciente")
    public String AddPacienteProcess(@RequestParam int cedula ,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String correo, @RequestParam int medico_id){

        Optional<Medico> medico = medicoRepository.findById(medico_id);
        Medico medico_info = medico.get();
        Paciente paciente = new Paciente(cedula,nombre,apellido,correo,medico_info);
		pacienteRepository.save(paciente);
        App.incrementarContadorPacientes();
        System.out.println(App.getPacienteCounter() + " pacientes registrados");
		return "redirect:/paciente";
    }

    @GetMapping("/findpacientebyid")
    public String getPacientebyId(@RequestParam(name = "id") int id, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        model.addAttribute("paciente", paciente.orElse(null)); 
        return "findpacientebyid";
    }

    @GetMapping("/updatepaciente/{id}")
    public String updatePaciente(@PathVariable(value="id") int id, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
		Paciente paciente_info = paciente.get();
		model.addAttribute("id",id);
		model.addAttribute("paciente_info",paciente_info);
		return "updatepaciente";
    }

    @PostMapping("/updatepaciente/{id}")
	public String updatePacienteProcess(@PathVariable(value="id") Integer id,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String correo, @RequestParam int medico_id,Model model) {

		Optional<Paciente> paciente = pacienteRepository.findById(id);
        Optional<Medico> medico = medicoRepository.findById(medico_id);
        Medico medico_info = medico.get();
		Paciente paciente_info = paciente.get();
		paciente_info.setNombre(nombre);
		paciente_info.setApellido(apellido);
		paciente_info.setCorreo(correo);
		paciente_info.setMedico(medico_info);
        pacienteRepository.save(paciente_info);
		return "redirect:/paciente";
	}

    @GetMapping("/deletepaciente/{id}")
	public String deletePaciente(@PathVariable int id) {  
		pacienteRepository.deleteById(id);
        return "redirect:/paciente";
	}
}
