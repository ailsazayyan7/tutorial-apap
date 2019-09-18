
package apap.tutorial.gopud.service;
import java.util.*;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.stereotype.Service;

@Service
public class RestoranInMemoryService implements RestoranService {
	private List<RestoranModel> listRestoran;
	
	//Constructor
	public RestoranInMemoryService() {
		listRestoran = new ArrayList<>();
	}
	
	@Override
	public void addRestoran(RestoranModel restoran) {
		listRestoran.add(restoran);
	}
	
	@Override
	public List<RestoranModel> getRestoranList(){
		return listRestoran;
	}
	
	@Override
	public RestoranModel getRestoranByIdRestoran(String idRestoran) {
		RestoranModel model = null;
		for (RestoranModel i : listRestoran){
			if (i.getIdRestoran().equals(idRestoran)) {
				model = i;
			}
		}
		return model;
	}

	
}
