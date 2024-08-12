CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    numero_controle VARCHAR(50) NOT NULL UNIQUE,
    data_cadastro DATE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 1,
    codigo_cliente INTEGER NOT NULL,
    valor_total NUMERIC(12, 2) NOT NULL
);