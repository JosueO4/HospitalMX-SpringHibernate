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
import com.mycompany.app.entity.Medicamento;
import com.mycompany.app.repository.MedicamentoRepository;

@Controller
public class MedicamentoController extends ErrorPrints{


        
    @Autowired
    MedicamentoRepository medicamentoRepository;

    @GetMapping("/medicamento")
    public String getAllMedicamentos(Model model) {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        model.addAttribute("medicamentos",medicamentos);
        return "medicamento";
    }

    @GetMapping("/addmedicamento")
    public String addMedicamento(){
		return "addmedicamento";
	}

    @PostMapping("/addmedicamento")
    public String AddMedicamentoProcess(@RequestParam String nombre,@RequestParam String dosis){

        Medicamento medicamento =new Medicamento(nombre,dosis);
		medicamentoRepository.save(medicamento);
		return "redirect:/medicamento";
    }

    @GetMapping("/findmedicamentobyid")
    public String getEspecialidadbyId(@RequestParam(name = "id") int id, Model model) {
        if (!medicamentoRepository.existsById(id)) {
            this.printError("No existe el medicamento con el id: " + id);
            return "error";
        }else{
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
        model.addAttribute("medicamento", medicamento.orElse(null)); 
        return "findmedicamentobyid";

        }
  
        
        
    }

    @GetMapping("/updatemedicamento/{id}")
    public String updateMedicamento(@PathVariable(value="id") int id, Model model) {
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
		Medicamento medicamento_info = medicamento.get();
		model.addAttribute("id",id);
		model.addAttribute("medicamento_info",medicamento_info);
		return "updatemedicamento";
    
    }

    @PostMapping("/updatemedicamento/{id}")
	public String updateMedicamentoProcess(@PathVariable(value="id") Integer id,@RequestParam String nombre,@RequestParam String dosis ,Model model) {

		Optional<Medicamento> medicamento = medicamentoRepository.findById(id);
		Medicamento medicamento_info = medicamento.get();
		medicamento_info.setNombre(nombre);
		medicamento_info.setDosis(dosis);
		medicamentoRepository.save(medicamento_info);
		return "redirect:/medicamento";
	}

    @GetMapping("/deletemedicamento/{id}")
	public String deleteMedicamento(@PathVariable int id) {  
		medicamentoRepository.deleteById(id);
        return "redirect:/medicamento";
	}
    @Override
    void printError(String error) {
        System.err.println(error);
    }
    
}
