package apap.tutorial.gopud.model.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController{
	@Autowired
	private RestoranService restoranService;
	// URL mapping add
	@RequestMapping("/restoran/add")
	public String add(
			//Request Parameter untuk dipass
			@RequestParam(value = "idRestoran", required = true) String idRestoran,
			@RequestParam(value = "nama", required = true) String nama,
			@RequestParam(value = "alamat", required = true) String alamat,
			@RequestParam(value = "nomorTelepon", required = true) Integer nomorTelepon,
			Model model
			) {
		// Membuat objek RestoranModel
		RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);
		
		//Memanggil service addRestoran
		restoranService.addRestoran(restoran);
		
		//Add variabel nama restoran ke "namaResto" untuk dirender
		model.addAttribute("namaResto", nama);
		
		//Return view template
		return "add-restoran";
	}
	
	//URL mapping view
	@RequestMapping("/restoran/view")
	public String view(
			// Request Parameter untuk dipass
			@RequestParam(value = "idRestoran") String idRestoran, Model model
			) {
		
		//Mengambil objek RestoranModel yang dituju
		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
		
		//Add model restoran ke "resto" untuk dirender
		model.addAttribute("resto", restoran);
		
		// return view template
		return "view-restoran";
	}
	
    // URL mapping viewAll
    @RequestMapping("restoran/viewall")
    public String viewall(Model model){

        //Mengambil semua objek RestoranModel yang ada
        List<RestoranModel> listRestoran = restoranService.getRestoranList();

        // Add model restoran ke "resto" untuk di render
        model.addAttribute("restoList", listRestoran);

        // Return view template
        return "viewall-restoran";
    }
    
    // URL mapping id-restoran
    @RequestMapping("/restoran/view/id-restoran/{idRestoran}")
    public String viewId(@PathVariable("idRestoran") String idRestoran, Model model){
    	// Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        // Return view template
        return "view-restoran";
    }

    //URL mapping update nomor telepon
    @RequestMapping("restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTelepon}")
    public String update(@PathVariable("nomorTelepon") Integer nomorTelepon, @PathVariable("idRestoran") String idRestoran, Model model){
        // Mengambil objek RestoranModel yang dituju
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
        restoran.setNomorTelepon(nomorTelepon);

        // Add model restoran ke "resto" untuk dirender
        model.addAttribute("resto", restoran);

        // Return view template
        return "update-telepon";
    }

    // URL mapping delete resto
	@RequestMapping("restoran/delete/id/{idRestoran}")
	public String delete(@PathVariable("idRestoran") String idRestoran, Model model){
		// Mengambil objek RestoranModel yang dituju
		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
		if (idRestoran.equals(null) || idRestoran.equals("")){
			return "error";}
		else{

			// Add model restoran ke "resto" untuk dirender
			model.addAttribute("resto", restoran);

			restoranService.deleteRestoran(restoran);

			// Return view template
			return "delete";
		}

	}
    
}
