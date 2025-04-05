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
import com.mycompany.app.entity.Agenda;
import com.mycompany.app.entity.Paciente;
import com.mycompany.app.repository.AgendaRepository;
import com.mycompany.app.repository.CitaRepository;

import com.mycompany.app.repository.PacienteRepository;

@Controller
public class AgendaController extends ErrorPrints{
    
    @Autowired
    AgendaRepository agendaRepository;
    @Autowired
    CitaRepository citaRepository;
    @Autowired
    PacienteRepository pacienteRepository;


    @GetMapping("/agenda")
    public String getAllAgenda(Model model) {
        List<Agenda> agendas = agendaRepository.findAll();
        model.addAttribute("agendas",agendas);
        return "agenda";
    }

    @GetMapping("/addagenda")
    public String addAgenda(){
		return "addagenda";
	}

    @PostMapping("/addagenda")
    public String addAgendaProcess(@RequestParam int paciente_id, @RequestParam int cita_id){

        Optional<Paciente> paciente = pacienteRepository.findById(paciente_id);
        Paciente paciente_info = paciente.get();
        Optional <Cita> cita = citaRepository.findById(cita_id);
        Cita cita_info = cita.get();
        Agenda agenda = new Agenda(paciente_info,cita_info);
		agendaRepository.save(agenda);
		return "redirect:/agenda";
    }

    @GetMapping("/findagendabyid")
    public String getAgendabyId(@RequestParam(name = "id") int id, Model model) {
        if (!agendaRepository.existsById(id)) {
            this.printError("No existe la agenda con id: " + id);
            return "error";
        }
        else {
            Optional <Agenda> agenda = agendaRepository.findById(id);
            model.addAttribute("agenda", agenda.orElse(null)); 
            return "findagendabyid";

        }
    }

    @GetMapping("/deleteagenda/{id}")
	public String deleteAgenda(@PathVariable int id) {  
		agendaRepository.deleteById(id);
        return "redirect:/agenda";
	}

    
    @GetMapping("/agendasporpaciente")
    public String agendaPorPaciente(){
        return "agendasporpaciente";
    }

    @GetMapping("/findagendaporpaciente")
    public String findAgendasPorPaciente(@RequestParam(name = "id") int paciente_id, Model model){
        List <Agenda> agendas = agendaRepository.findAgendaByPaciente(paciente_id);
        model.addAttribute("agendas", agendas);
        return "findagendaporpaciente";
    }



    @Override
    void printError(String error) {
       System.err.println(error);
    }

    
}
