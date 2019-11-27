package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.RoleModel;
import apap.tutorial.gopud.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDB roleDB;

    @Override
    public List<RoleModel> findAll() {
        return roleDB.findAll();
    }
}
