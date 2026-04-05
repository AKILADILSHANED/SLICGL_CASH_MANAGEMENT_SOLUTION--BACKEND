package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.subFunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubFunctionRepo extends JpaRepository<subFunction, String> {
    //List<subFunction> findAll();
}
