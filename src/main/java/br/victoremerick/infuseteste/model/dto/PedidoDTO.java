package br.victoremerick.infuseteste.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Pedido")
public class PedidoDTO {

    @XmlElement(name = "numeroControle")
    private String numeroControle;

    @XmlElement(name = "dataCadastro")
    private LocalDate dataCadastro;

    @XmlElement(name = "nome")
    private String nome;

    @XmlElement(name = "valor")
    private BigDecimal valor;

    @XmlElement(name = "quantidade")
    private Integer quantidade;

    @XmlElement(name = "codigoCliente")
    private Long codigoCliente;

    @XmlElement(name = "valorTotal")
    private BigDecimal valorTotal;
}
