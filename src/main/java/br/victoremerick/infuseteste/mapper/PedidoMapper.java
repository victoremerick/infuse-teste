package br.victoremerick.infuseteste.mapper;

import br.victoremerick.infuseteste.model.dto.PedidoDTO;
import br.victoremerick.infuseteste.model.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "valorTotal", source = "valorTotal")
    @Mapping(target = "valor", source = "valor")
    Pedido toEntity(PedidoDTO pedidoDTO);

    PedidoDTO toDTO(Pedido pedido);
}
