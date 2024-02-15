package pe.gob.sunat.citas.dao;

import pe.gob.sunat.citas.bean.UsuarioBean;

public interface UsuarioDao {
	UsuarioBean autenticarUsuario(String dni, String password);
}
