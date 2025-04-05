package com.mycompany.app.controller;
import com.mycompany.app.entity.Padecimiento;
import com.mycompany.app.repository.PadecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
public class PadecimientoController {

    @Autowired
    PadecimientoRepository padecimientoRepository;

    @GetMapping("/padecimiento")
    public String getAllPadecimientos(Model model) {
        List<Padecimiento> padecimientos = padecimientoRepository.findAll();
        model.addAttribute("padecimientos",padecimientos);
        return "padecimiento";
    }

    @GetMapping("/addpadecimiento")
    public String addPadecimiento(){
		return "addpadecimiento";
	}

    @PostMapping("/addpadecimiento")
    public String AddPadecimientoProcess(@RequestParam String nombre,@RequestParam String sintomas,@RequestParam String descripcion){

        Padecimiento padecimiento =new Padecimiento(nombre,sintomas,descripcion);
		padecimientoRepository.save(padecimiento);
		return "redirect:/padecimiento";
    }

    @GetMapping("/findpadecimientobyid")
    public String getPadecimientobyId(@RequestParam(name = "id") int id, Model model) {
        Optional<Padecimiento> padecimiento = padecimientoRepository.findById(id);
        model.addAttribute("padecimiento", padecimiento.orElse(null)); 
        return "findpadecimientobyid";
    }

    @GetMapping("/updatepadecimiento/{id}")
    public String updatePadecimiento(@PathVariable(value="id") int id, Model model) {
        Optional<Padecimiento> padecimiento = padecimientoRepository.findById(id);
		Padecimiento padecimiento_info = padecimiento.get();
		model.addAttribute("id",id);
		model.addAttribute("padecimiento_info",padecimiento_info);
		return "updatepadecimiento";
    }

    @PostMapping("/updatepadecimiento/{id}")
	public String updatePadecimientoProcess(@PathVariable(value="id") Integer id,@RequestParam String nombre,@RequestParam String sintomas,@RequestParam String descripcion ,Model model) {

		Optional<Padecimiento> padecimiento = padecimientoRepository.findById(id);
		Padecimiento padecimiento_info = padecimiento.get();
		padecimiento_info.setNombre(nombre);
		padecimiento_info.setSintomas(sintomas);
		padecimiento_info.setDescripcion(descripcion);
		padecimientoRepository.save(padecimiento_info);
		return "redirect:/padecimiento";
	}

    @GetMapping("/deletepadecimiento/{id}")
	public String deletePadecimiento(@PathVariable int id) {  
		padecimientoRepository.deleteById(id);
        return "redirect:/padecimiento";
	}
}
