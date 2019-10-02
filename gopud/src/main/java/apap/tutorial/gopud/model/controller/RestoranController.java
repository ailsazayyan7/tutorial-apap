package apap.tutorial.gopud.model.controller;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController{
	@Qualifier("restoranServiceImpl")
	@Autowired
	private RestoranService restoranService;

	@Autowired
	private MenuService menuService;

	@RequestMapping("/")
	public String home() { return "home";}

	//URL mapping yang digunakan untuk mengakses halaman add restoran
	@RequestMapping(value = "/restoran/add", method = RequestMethod.GET)
	public String addRestoranFormPage(Model model){
		RestoranModel newRestoran = new RestoranModel();
		model.addAttribute("restoran", newRestoran);
		return "form-add-restoran";

	}

	//URL mapping yang digunakan untuk submit form yang telah Anda masukkan pada halaman add restoran
	@RequestMapping(value = "/restoran/add", method = RequestMethod.POST)
	public String addRestoranSubmit(@ModelAttribute RestoranModel restoran, Model model){
		restoranService.addRestoran(restoran);
		model.addAttribute("namaResto", restoran.getNama());
		return "add-restoran";
	}

	//URL mapping view
	@RequestMapping(path = "/restoran/view", method = RequestMethod.GET)
	public String view(
			// Request Parameter untuk dipass
			@RequestParam(value = "idRestoran") Long idRestoran, Model model
			) {

		//Mengambil objek RestoranModel yang dituju
		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();

		List<MenuModel> menuList = menuService.getListMenuOrderByHargaAsc(restoran.getIdRestoran());
		restoran.setListMenu(menuList);

		//Add model restoran ke "resto" untuk dirender
		model.addAttribute("resto", restoran);

		// return view template
		return "view-restoran";
	}

	//API yang digunakan untuk menuju halaman form change restoran
	@RequestMapping(value="restoran/change/{idRestoran}", method = RequestMethod.GET)
	public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model){
		//Mengambil existing data restoran
		RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
		model.addAttribute("restoran", existingRestoran);
		return "form-change-restoran";
	}

	//API yang digunakan untuk submit form change restoran
	@RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.POST)
	public String changeRestoranFormSubmit(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoran, Model model){
		RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
		model.addAttribute("restoran", newRestoranData);

		return "change-restoran";
	}
    // URL mapping viewAll
    @RequestMapping("restoran/viewall")
    public String viewall(Model model){

        //Mengambil semua objek RestoranModel yang ada
        List<RestoranModel> listRestoran = restoranService.getRestoranList();

		Collections.sort(listRestoran);
        // Add model restoran ke "resto" untuk di render
        model.addAttribute("restoList", listRestoran);


        // Return view template
        return "viewall-restoran";
    }

    // URL mapping id-restoran
    @RequestMapping("/restoran/view/id-restoran/{idRestoran}")
    public String viewId(@PathVariable("idRestoran") Long idRestoran, Model model){
    	// Mengambil objek RestoranModel yang dituju
        Optional<RestoranModel> restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);
        // Return view template
        return "view-restoran";
    }

    //URL mapping update nomor telepon
    @RequestMapping("restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTelepon}")
    public String update(@PathVariable("nomorTelepon") Integer nomorTelepon, @PathVariable("idRestoran") Long idRestoran, Model model) {
		// Mengambil objek RestoranModel yang dituju
		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
		restoran.setNomorTelepon(nomorTelepon);

		// Add model restoran ke "resto" untuk dirender
		model.addAttribute("resto", restoran);

		// Return view template
		return "update-telepon";
	}

	@RequestMapping(value = "restoran/delete/{idRestoran}")
	public String viewDeleteRestoran(
			@PathVariable(value = "idRestoran") Long idRestoran,
			Model model
	) {
		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
		List<MenuModel> listMenu = menuService.findAllMenuByIdRestoran(idRestoran);
		if (listMenu.size() == 0) {
			model.addAttribute("resto", restoran);
			restoranService.deleteRestoran(restoran);
			return "delete";
		} else {
			model.addAttribute("resto", restoran);
			return "delete-restoran-error";

		}


	}
}
