package pe.edu.upeu.sysventas.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.model.Marca;
import pe.edu.upeu.sysventas.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysventas.repository.MarcaRepository;
import pe.edu.upeu.sysventas.service.IMarcaService;

@RequiredArgsConstructor
public class MarcaServiceImp extends CrudGenericoServiceImp<Marca, Long> implements IMarcaService {
    private final MarcaRepository marcaRepository;

    @Override
    protected ICrudGenericoRepository<Marca, Long> getRepo() {
        return marcaRepository;
    }

}
