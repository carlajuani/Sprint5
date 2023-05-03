package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n01.model.services.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sucursals")
public class SucursalController {
    @Autowired
    ISucursalService service;

    @GetMapping("/add")
    public String showNewSucursalForm(Model model) {
        Sucursal newSucursal = new Sucursal();
        model.addAttribute("sucursal", newSucursal);
        return "new_sucursal";
    }

    @PostMapping({"/getAll","/",""})
    public String saveSucursalFromForm(@ModelAttribute("sucursal") SucursalDTO sucursalDTO) {
        service.save(sucursalDTO);
        return "redirect:/sucursals";
    }

    @GetMapping("/update/{pkSucursalID}")
    public String showEditSucursalForm(@PathVariable("pkSucursalID") int pkSucursalID, Model model) {
        model.addAttribute("sucursal", service.findById(pkSucursalID));
        return "update_sucursal";
    }

    @PostMapping("/update/{pkSucursalID}")
    public String updateSucursal(@PathVariable("pkSucursalID") int pkSucursalID, @ModelAttribute("sucursal") SucursalDTO sucursalDTO, Model model ) {
        SucursalDTO updatedSucursalDTO = service.findById(pkSucursalID);
        updatedSucursalDTO.setNomSucursal(sucursalDTO.getNomSucursal());
        updatedSucursalDTO.setPaisSucursal(sucursalDTO.getPaisSucursal());
        service.update(updatedSucursalDTO);
        return "redirect:/sucursals";
    }

    @GetMapping("/delete/{pkSucursalID}")
    public String deleteSucursal(@PathVariable("pkSucursalID") int pkSucursalID) {
        service.deleteById(pkSucursalID);
        return "redirect:/sucursals";
    }

    @GetMapping("/getOne/{pkSucursalID}")
    public String getSucursalById(@PathVariable("pkSucursalID") int pkSucursalID, Model model ) {
        model.addAttribute("sucursal", service.findById(pkSucursalID));
        return "show_sucursal";
    }

    @GetMapping({"/getAll","/",""})
    public String getAllSucursals(Model model) {
        model.addAttribute("sucursals", service.findAll());
        if (service.findAll().isEmpty()) {
            return HttpStatus.NO_CONTENT.toString();
        }
        return "sucursals";
    }


}
