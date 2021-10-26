package mk.ukim.finki.lab.web.controller;

import mk.ukim.finki.lab.model.Balloon;
import mk.ukim.finki.lab.service.BalloonService;
import mk.ukim.finki.lab.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/balloons")
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if ( error != null && !error.equals("") )
        {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("balloons", balloonService.listAll());
        return "listBalloons";
    }

    @PostMapping("/balloons/selectBalloon")
    public String selectBalloon(@RequestParam String balloon, @RequestParam String color, Model model){
        model.addAttribute("balloon", balloon);
        model.addAttribute("color", color);
        return "selectBalloonSize";
    }

    @PostMapping("/balloon/balloonOrder")
    public String balloonOrder(@RequestParam String size, @RequestParam String balloon, @RequestParam String color, Model model){
        model.addAttribute("size", size);
        model.addAttribute("balloon", balloon);
        model.addAttribute("color", color);
        return "deliveryInfo";
    }

    @PostMapping("/balloon/confirmation")
    public String balloonConfirmation(@RequestParam String clientName, @RequestParam String clientAddress, @RequestParam String balloon, @RequestParam String color, @RequestParam String size, Model model){
        model.addAttribute("clientName", clientName);
        model.addAttribute("clientAddress", clientAddress);
        model.addAttribute("balloon", balloon);
        model.addAttribute("color", color);
        model.addAttribute("size", size);
        return "confirmationInfo";
    }

    @PostMapping("/balloons/add")
    public String saveBalloon(@RequestParam String name, @RequestParam String description, @RequestParam Long manufacturerId){
        balloonService.save(name,description,manufacturerId);
        return "redirect:/balloons";
    }

    @GetMapping("/balloons/addNewBalloon")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBalloon(Model model){
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "add-balloon";
    }

    @PostMapping("/balloons/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBalloon(@PathVariable Long id){
        balloonService.removeById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/balloons/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        Balloon b = balloonService.getById(id).orElseThrow();
        model.addAttribute("balloonModel", b);
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "add-balloon";
    }


}
