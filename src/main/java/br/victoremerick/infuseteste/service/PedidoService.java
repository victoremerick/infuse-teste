package br.victoremerick.infuseteste.service;

import br.victoremerick.infuseteste.mapper.PedidoMapper;
import br.victoremerick.infuseteste.model.dto.PedidoDTO;
import br.victoremerick.infuseteste.model.entity.Pedido;
import br.victoremerick.infuseteste.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final PedidoMapper pedidoMapper;

    public void processarPedidos(List<PedidoDTO> pedidos) throws Exception {
        if (pedidos.size() > 10) {
            throw new Exception("O arquivo contém mais de 10 pedidos.");
        }

        for (PedidoDTO pedido : pedidos) {
            if (pedidoRepository.existsByNumeroControle(pedido.getNumeroControle())) {
                throw new Exception("Número de controle já cadastrado: " + pedido.getNumeroControle());
            }

            if (pedido.getDataCadastro() == null) {
                pedido.setDataCadastro(LocalDate.now());
            }

            if (pedido.getQuantidade() == null) {
                pedido.setQuantidade(1);
            }

            BigDecimal valorTotal = calcularValorTotal(pedido.getValor(), pedido.getQuantidade());
            pedido.setValorTotal(valorTotal);
            Pedido entity = pedidoMapper.toEntity(pedido);
            pedidoRepository.save(entity);
        }
    }

    public List<PedidoDTO> buscarPedidos(String numeroControle, LocalDate dataCadastro) {
        List<Pedido> pedidos = pedidoRepository.findByFilters(numeroControle, dataCadastro);
        return pedidos.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    private BigDecimal calcularValorTotal(BigDecimal valorUnitario, int quantidade) {
        BigDecimal valorTotal = valorUnitario.multiply(BigDecimal.valueOf(quantidade));
        if (quantidade >= 10) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.90)); // 10% de desconto
        } else if (quantidade > 5) {
            valorTotal = valorTotal.multiply(BigDecimal.valueOf(0.95)); // 5% de desconto
        }
        return valorTotal;
    }
}

