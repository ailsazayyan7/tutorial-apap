package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.RestoranDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class RestoranServiceImpl implements RestoranService {
    @Autowired
    private RestoranDB restoranDB;

    @Override
    public void addRestoran(RestoranModel restoran){ restoranDB.save(restoran);}

    @Override
    public List<RestoranModel> getRestoranList() {
        return restoranDB.findAll();
    }

    @Override
    public Optional<RestoranModel> getRestoranByIdRestoran(Long idRestoran) {
        return restoranDB.findByIdRestoran(idRestoran);
    }

    @Override
    public RestoranModel changeRestoran(RestoranModel restoranModel) {
        //ngambil object restoran yang ingin diubah
        RestoranModel targetRestoran = restoranDB. findById(restoranModel.getIdRestoran()).get();

        try{
            targetRestoran.setNama((restoranModel).getNama());
            targetRestoran.setAlamat(restoranModel.getAlamat());
            targetRestoran.setNomorTelepon(restoranModel.getNomorTelepon());
            restoranDB.save(targetRestoran);
            return targetRestoran;
        }
        catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deleteRestoran(RestoranModel restoran) { restoranDB.delete(restoran); }

}
