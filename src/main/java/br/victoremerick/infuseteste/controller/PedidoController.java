package br.victoremerick.infuseteste.controller;

import br.victoremerick.infuseteste.model.dto.PedidoDTO;
import br.victoremerick.infuseteste.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping(value = "", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> receberPedidos(@RequestBody List<PedidoDTO> pedidos) {
        try {
            pedidoService.processarPedidos(pedidos);
            return new ResponseEntity<>("Pedidos processados com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<PedidoDTO>> buscarPedidos(
            @RequestParam(required = false) String numeroControle,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataCadastro) {

        List<PedidoDTO> pedidos = pedidoService.buscarPedidos(numeroControle, dataCadastro);
        return ResponseEntity.ok(pedidos);
    }
}
