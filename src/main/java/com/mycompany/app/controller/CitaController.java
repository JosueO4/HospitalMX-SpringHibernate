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


import com.mycompany.app.entity.Cita;

import com.mycompany.app.entity.Medico;
import com.mycompany.app.entity.Paciente;
import com.mycompany.app.repository.CitaRepository;

import com.mycompany.app.repository.MedicoRepository;
import com.mycompany.app.repository.PacienteRepository;

@Controller
public class CitaController extends ErrorPrints{
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    CitaRepository citaRepository;


    @GetMapping("/cita")
    public String getAllCita(Model model) {
        List<Cita> citas = citaRepository.findAll();
        model.addAttribute("citas",citas);
        return "cita";
    }

    @GetMapping("/addcita")
    public String addCita(){
		return "addcita";
	}

    @PostMapping("/addcita")
    public String AddCitaProcess(@RequestParam String fecha,@RequestParam int paciente_id,@RequestParam int medico_id){

        Optional<Medico> medico = medicoRepository.findById(medico_id);
        Medico medico_info = medico.get();
        Optional <Paciente> paciente = pacienteRepository.findById(paciente_id);
        Paciente paciente_info = paciente.get();
        Cita cita = new Cita(fecha,paciente_info,medico_info);
		citaRepository.save(cita);
		return "redirect:/cita";
    }

    @GetMapping("/findcitabyid")
    public String getCitabyId(@RequestParam(name = "id") int id, Model model) {
        if (!citaRepository.existsById(id)) {
            this.printError("No existe el id");
            return "error";
        }else{
        Optional <Cita> cita = citaRepository.findById(id);
        model.addAttribute("cita", cita.orElse(null)); 
        return "findcitabyid";

        }
    }

    @GetMapping("/updatecita/{id}")
    public String updateCita(@PathVariable(value="id") int id, Model model) {
        Optional<Cita> cita = citaRepository.findById(id);
		Cita cita_info = cita.get();
		model.addAttribute("id",id);
		model.addAttribute("cita_info",cita_info);
		return "updatecita";
    }

    @PostMapping("/updatecita/{id}")
	public String updateCitaProcess(@PathVariable int id ,@RequestParam String fecha, Model model) {

        Optional<Cita> cita = citaRepository.findById(id);
        Cita cita_info = cita.get();
		cita_info.setFecha(fecha);

        citaRepository.save(cita_info);
		return "redirect:/cita";
	}

    @GetMapping("/deletecita/{id}")
	public String deleteCita(@PathVariable int id) {  
		citaRepository.deleteById(id);
        return "redirect:/cita";
	}

    @Override
    void printError(String error) {
        System.err.println(error);
    }

    @GetMapping("/citaspormedico")
    public String citasPorMedico(){
        return "citaspormedico";
    }

    @GetMapping("/findcitaspormedico")
    public String findcitasPorMedico(@RequestParam(name = "id") int medico_id, Model model){
        List <Cita> citas = citaRepository.findCitasByMedico(medico_id);
        model.addAttribute("citas", citas);
        return "findcitaspormedico";
    }


    @GetMapping("/citaspacientes")
    public String citaspacientes(Model model){

        List <Cita> citas = citaRepository.findPacientesCitas();
        model.addAttribute("citas", citas);
        return "citaspacientes";

    }


}
