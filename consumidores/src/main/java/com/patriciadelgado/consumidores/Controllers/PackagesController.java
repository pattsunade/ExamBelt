package com.patriciadelgado.consumidores.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.patriciadelgado.consumidores.Models.Subs;
import com.patriciadelgado.consumidores.Models.User;
import com.patriciadelgado.consumidores.Services.SubsService;
import com.patriciadelgado.consumidores.Services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PackagesController {
    private final SubsService subsService;
    private final UserService userService;

    
    public PackagesController(SubsService subsService, UserService userService) {
        this.subsService = subsService;
        this.userService = userService;
    }
    @GetMapping("/packages")
    public String packages(@ModelAttribute("paquete") Subs subss, HttpSession session, Model model) {
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        if (user.getUserRol() != 1 || user.getId() == null) {
            return "redirect:/";
        } else {
            List<User> users = userService.allUser();
            List<Subs> subs = subsService.getAll();
            model.addAttribute("users", users);
            model.addAttribute("subs", subs);
            return "dashboard.jsp";
        }
    }
    @GetMapping("/users/{idQ}")
	public String users(@ModelAttribute("user")User user, HttpSession session, Model model) {
        User user2 = userService.findUserById((Long) session.getAttribute("userId"));
        List<Subs> suscripciones = subsService.getAll();
		if (user2.getUserRol() == 1 || user2.getId() == null) {
			return "redirect:/";
		}else {
			model.addAttribute("suscripciones", suscripciones);
			model.addAttribute("user", user2);
			return "show.jsp";
		}
	}


    @PostMapping("/packages")
    public String createPackages(@Valid @ModelAttribute("paquete") Subs subss,
            BindingResult result, HttpSession session, Model model) {
       
        if (result.hasErrors()) {
            List<User> users = userService.allUser();
            List<Subs> suscripciones = subsService.getAll();
            model.addAttribute("users", users);
            model.addAttribute("suscripciones", suscripciones);
            return "dashboard.jsp";
        }else {
			User admin = userService.findUserById((Long) session.getAttribute("userId"));
			subss.setAdmin(admin);
			subss.setEstado(true);
			subsService.createSub(subss);
			return "redirect:/packages";
		}
	

    }
    @RequestMapping("/packages/{id}")
    public String show(@PathVariable("id") Long id, Model model, HttpSession session) {
        Subs subss = subsService.findSubscriptionById(id);
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        if (subss == null) {
            return "redirect:/packages";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("subs", subss);
            return "show.jsp";
        }

    }
    @GetMapping("/packages/{idS}")
    public String estado(@PathVariable("idS") Long idS, HttpSession session) {
        Subs suscripcion = subsService.findSubscriptionById(idS);
        User user = userService.findUserById((Long) session.getAttribute("userId"));
        if (user.getId() == suscripcion.getAdmin().getId()) {
            if (suscripcion.isEstado()) {
                suscripcion.setEstado(false);
                subsService.updateSub(suscripcion);
                return "redirect:/packages";
            } else {
                suscripcion.setEstado(true);
                subsService.updateSub(suscripcion);
                return "redirect:/packages";
            }
        } else {
            return "redirect:/";
        }
    }
    @GetMapping("/packages/{idS}/edit")
	public String edit(@PathVariable("idS")Long idS ,HttpSession session, Model model) {
		User user = userService.findUserById((Long) session.getAttribute("userId"));
		if (user.getId() == null || user.getUserRol() != 1) {
			return "redirect:/";
		}else {
            Subs subss = subsService.findSubscriptionById(idS);
			model.addAttribute("subss", subss);
			return "edit.jsp";
		}
	}
	
@PutMapping("/packages/{idS}/edit")
	public String editSus(@Valid @ModelAttribute("subss") Subs subss, BindingResult result,
			@PathVariable("idS")Long idS,Model model, RedirectAttributes redirectAttributes) {
        Subs sus = subsService.findSubscriptionById(idS);
		if (result.hasErrors()) {
			model.addAttribute("suscripcion", sus);
			redirectAttributes.addFlashAttribute("error", "El costo es menor a 1");
			return "redirect:/packages/"+idS+"/edit";
		}else {
			sus.setPrice(subss.getPrice());
			subsService.updateSub(sus);
			return "redirect:/packages";
		}
	}

		@GetMapping("/packages/{idS}/delete")
        public String deleteSus(@PathVariable("idS") Long idS, HttpSession session) {
            User user = userService.findUserById((Long) session.getAttribute("userId"));
            if (user.getUserRol() != 1 || user.getId() == null) {
                return "redirect:/";
            } else {
                Subs suscripcion = subsService.findSubscriptionById(idS);
                if (suscripcion.getUsers().size() > 0) {
                    return "redirect:/packages";
                } else {
                    subsService.deleteSub(suscripcion);
                    return "redirect:/packages";
                }
            }
        }
     
    @PostMapping("/users/{idQ}")
	public String switchPackage(@ModelAttribute("subss")Subs subss,HttpSession session) {
		User user = userService.findUserById((Long) session.getAttribute("userId"));
		user.setSubss(subss);
		userService.guardar(user);
		return "redirect:/users/"+user.getId();
	}
 }
	



