package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getUserModuleDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.userModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userModuleRepo extends JpaRepository<userModule, String> {
    @Query(value = "SELECT * FROM user_module", nativeQuery = true)
    public List<getUserModuleDTO> getModuleList();
}
