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


import com.mycompany.app.entity.ExpedienteMedico;
import com.mycompany.app.entity.Medicamento;

import com.mycompany.app.entity.Paciente;
import com.mycompany.app.entity.Padecimiento;
import com.mycompany.app.entity.Procedimiento;

import com.mycompany.app.repository.ExpedienteMedicoRepository;
import com.mycompany.app.repository.MedicamentoRepository;

import com.mycompany.app.repository.PacienteRepository;
import com.mycompany.app.repository.PadecimientoRepository;
import com.mycompany.app.repository.ProcedimientoRepository;

@Controller
public class ExpedienteMedicoController extends ErrorPrints{
    @Autowired
    ExpedienteMedicoRepository expedienteMedicoRepository;
    @Autowired
    PadecimientoRepository padecimientoRepository;
    @Autowired
    MedicamentoRepository medicamentoRepository;
    @Autowired
    ProcedimientoRepository procedimientoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping("/expedientemedico")
    public String getAllCita(Model model) {
        List<ExpedienteMedico> expedienteMedicos = expedienteMedicoRepository.findAll();
        model.addAttribute("expedienteMedicos",expedienteMedicos);
        return "expedientemedico";
    }

    @GetMapping("/addexpedientemedico")
    public String addExpedienteMedico(){
		return "addexpedientemedico";
	}

    @PostMapping("/addexpedientemedico")
    public String AddExpedienteMedicoProcess(@RequestParam String fecha,@RequestParam int paciente_id, @RequestParam int padecimiento_id,@RequestParam int medicamento_id, @RequestParam int procedimiento_id){

        Optional<Paciente> paciente = pacienteRepository.findById(paciente_id);
        Paciente paciente_info = paciente.get();
        Optional<Padecimiento> padecimiento = padecimientoRepository.findById(padecimiento_id);
        Padecimiento padecimiento_info = padecimiento.get();
        Optional <Medicamento> medicamento = medicamentoRepository.findById(medicamento_id);
        Medicamento medicamento_info = medicamento.get();
        Optional <Procedimiento> procedimiento = procedimientoRepository.findById(procedimiento_id);
        Procedimiento procedimiento_info = procedimiento.get();
        ExpedienteMedico expedienteMedico = new ExpedienteMedico(fecha,paciente_info,padecimiento_info,medicamento_info,procedimiento_info);
		expedienteMedicoRepository.save(expedienteMedico);
		return "redirect:/expedientemedico";
    }

    @GetMapping("/findexpedientemedicobyid")
    public String getExpedienteMedicobyId(@RequestParam(name = "id") int id, Model model) {
        if (!expedienteMedicoRepository.existsById(id)) {
            this.printError("No existe el expediente medico con id: "+id);
            return "error";
        }else{
        Optional <ExpedienteMedico> expedienteMedico = expedienteMedicoRepository.findById(id);
        model.addAttribute("expedientemedico", expedienteMedico.orElse(null)); 
        return "findexpedientemedicobyid";

        }
    }

    @GetMapping("/updateexpedientemedico/{id}")
    public String updateExpedienteMedico(@PathVariable(value="id") int id, Model model) {
        Optional<ExpedienteMedico> expedienteMedico = expedienteMedicoRepository.findById(id);
		ExpedienteMedico expedienteMedico_info = expedienteMedico.get();
		model.addAttribute("id",id);
		model.addAttribute("expedienteMedico_info",expedienteMedico_info);
		return "updateexpedientemedico";
    }

    @PostMapping("/updateexpedientemedico/{id}")
	public String updateExpedienteMedicoProcess(@PathVariable int id ,@RequestParam String fecha, Model model) {

        Optional<ExpedienteMedico> expedienteMedico = expedienteMedicoRepository.findById(id);
        ExpedienteMedico expedienteMedico_info = expedienteMedico.get();
		expedienteMedico_info.setFecha(fecha);

        expedienteMedicoRepository.save(expedienteMedico_info);
		return "redirect:/expedientemedico";
	}

    @GetMapping("/deleteexpedientemedico/{id}")
	public String deleteExpedienteMedico(@PathVariable int id) {  
		expedienteMedicoRepository.deleteById(id);
        return "redirect:/expedientemedico";
	}

    @GetMapping("/expedientesmedicosporpaciente")
    public String expedientesMedicosPorPaciente(){
        return "expedientesmedicosporpaciente";
    }

    @GetMapping("/findexpedientesmedicosporpaciente")
    public String findExpedientesMedicosPorPaciente(@RequestParam(name = "id") int paciente_id, Model model){
        List <ExpedienteMedico> expedienteMedicos = expedienteMedicoRepository.findExpedienteByPaciente(paciente_id);
        model.addAttribute("expedienteMedicos", expedienteMedicos);
        return "findexpedientesmedicosporpaciente";
    }

    @Override
    void printError(String error) {
        System.err.println(error);
    }
    
}
