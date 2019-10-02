package apap.tutorial.gopud.model.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.service.MenuService;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;

    @Qualifier("restoranServiceImpl")
    @Autowired
    RestoranService restoranService;

    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value = "idRestoran") Long idRestoran, Model model){
        MenuModel menu = new MenuModel();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        menu.setRestoran(restoran);

        model.addAttribute("menu", menu);
        return "form-add-menu";
    }

    @RequestMapping(value = "menu/add", method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute MenuModel menu, Model model){
        menuService.addMenu(menu);
        model.addAttribute("nama", menu.getNama());
        return "add-menu";
    }

    //API yang digunakan untuk menuju halaman form change menu
    @RequestMapping(value="menu/change/{id}", method = RequestMethod.GET)
    public String changeMenuFormPage(@PathVariable Long id, Model model){
        //Mengambil existing data restoran
        MenuModel existingMenu = menuService.getMenuByIdMenu(id).get();
        model.addAttribute("menu", existingMenu);
        return "form-change-menu";
    }

    //API yang digunakan untuk submit form change menu
    @RequestMapping(value = "menu/change/{id}", method = RequestMethod.POST)
    public String changeMenuFormSubmit(@PathVariable Long id, @ModelAttribute MenuModel menu, Model model){
        MenuModel newMenuData = menuService.changeMenu(menu);
        model.addAttribute("menu", newMenuData);

        return "change-menu";
    }

    /*@RequestMapping(value = "menu/delete/{id}")
    public String deleteMenu(@PathVariable Long id, Model model) {
        MenuModel menu = menuService.getMenuByIdMenu(id).get();
        model.addAttribute("menu", menu);
        menuService.deleteMenu(menu);
        return "delete-menu";
    }*/
    @RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
    private String delete(@ModelAttribute RestoranModel restoran, Model model){
        for (MenuModel menu : restoran.getListMenu()){
            menuService.deleteMenu(menu);
        }
        return "delete-menu";
    }

}

    /*//nomor 3
    @RequestMapping(value = "menu/add/{idRestoran}", method = RequestMethod.POST, params = {"addRow"})
    private String addRow(@PathVariable(value="idRestoran") Long idRestoran, @ModelAttribute RestoranModel restoran, Model model) {
        MenuModel menu = new MenuModel();
        restoran.getListMenu().add(menu);
        model.addAttribute("restoran", restoran);
        model.addAttribute("idRestoran", idRestoran);
        return "form-add-menu";
    }

    @RequestMapping(value = "product/add/{idRestoran}", method = RequestMethod.POST, params = {"removeRow"})
    private String removeRow(@PathVariable(value="idRestoran") Long idRestoran, @ModelAttribute RestoranModel restoran, Model model, HttpServletRequest req) {
        Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        restoran.getListMenu().remove(rowId.intValue());
        model.addAttribute("idRestoran", idRestoran);
        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }

    @RequestMapping(value = "product/add/{idRestoran}", method = RequestMethod.POST, params={"save"})
    private String addProductSubmit(@PathVariable(value = "idRestoran") Long idRestoran, @ModelAttribute RestoranModel restoran, ModelMap model){
        RestoranModel RestoranLama = restoranService.getRestoranByIdRestoran(idRestoran);
        model.addAttribute("idRestoran", idRestoran);
        for (MenuModel menu : restoran.getListMenu()) {
            menu.setRestoran(RestoranLama);
            menuService.addMenu(menu);
        }
        model.clear();
        return "add-menu";
    }*/
