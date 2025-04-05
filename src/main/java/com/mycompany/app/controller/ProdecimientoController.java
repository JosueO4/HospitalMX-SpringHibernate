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
import com.mycompany.app.entity.Procedimiento;
import com.mycompany.app.repository.ProcedimientoRepository;


@Controller
public class ProdecimientoController {
    
    @Autowired
    ProcedimientoRepository procedimientoRepository;

    @GetMapping("/procedimiento")
    public String getAllProcedimientos(Model model) {
        List<Procedimiento> procedimientos = procedimientoRepository.findAll();
        model.addAttribute("procedimientos",procedimientos);
        return "procedimiento";
    }

    @GetMapping("/addprocedimiento")
    public String addProcedimiento(){
		return "addprocedimiento";
	}

    @PostMapping("/addprocedimiento")
    public String AddProcedimientoProcess(@RequestParam String nombre,@RequestParam String descripcion){

        Procedimiento procedimiento =new Procedimiento(nombre,descripcion);
		procedimientoRepository.save(procedimiento);
		return "redirect:/procedimiento";
    }

    @GetMapping("/findprocedimientobyid")
    public String getProcedimientobyId(@RequestParam(name = "id") int id, Model model) {
        Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);
        model.addAttribute("procedimiento", procedimiento.orElse(null)); 
        return "findprocedimientobyid";
    }

    @GetMapping("/updateprocedimiento/{id}")
    public String updateProcedimiento(@PathVariable(value="id") int id, Model model) {
        Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);
		Procedimiento procedimiento_info = procedimiento.get();
		model.addAttribute("id",id);
		model.addAttribute("procedimiento_info",procedimiento_info);
		return "updateprocedimiento";
    }

    @PostMapping("/updateprocedimiento/{id}")
	public String updateProcedimientoProcess(@PathVariable(value="id") Integer id,@RequestParam String nombre,@RequestParam String descripcion ,Model model) {

		Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);
		Procedimiento procedimiento_info = procedimiento.get();
		procedimiento_info.setNombre(nombre);
		procedimiento_info.setDescripcion(descripcion);
		procedimientoRepository.save(procedimiento_info);
		return "redirect:/procedimiento";
	}

    @GetMapping("/deleteprocedimiento/{id}")
	public String deleteProcedimiento(@PathVariable int id) {  
		procedimientoRepository.deleteById(id);
        return "redirect:/procedimiento";
	}
}
