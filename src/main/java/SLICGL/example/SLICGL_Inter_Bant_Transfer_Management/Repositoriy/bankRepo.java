package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bankRepo extends JpaRepository<bank, String> {

}
