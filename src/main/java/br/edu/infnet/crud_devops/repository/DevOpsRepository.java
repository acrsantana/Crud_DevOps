package br.edu.infnet.crud_devops.repository;

import br.edu.infnet.crud_devops.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevOpsRepository extends JpaRepository<Usuario, String> {
}
