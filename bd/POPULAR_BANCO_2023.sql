-- Inserindo dados na tabela `Estado`
INSERT INTO `sysadocao`.`Estado` (`ID`, `Sigla`, `Nome`) VALUES
(1, 'SP', 'São Paulo'),
(2, 'RJ', 'Rio de Janeiro'),
(3, 'MG', 'Minas Gerais');

-- Inserindo dados na tabela `Cidade` usando os IDs dos estados
INSERT INTO `sysadocao`.`Cidade` (`Estado_id`, `Nome`) VALUES
(1, 'São Paulo'),
(1, 'Campinas'),
(2, 'Rio de Janeiro'),
(3, 'Belo Horizonte');

-- Inserindo dados na tabela `Bairro` usando os IDs das cidades
INSERT INTO `sysadocao`.`Bairro` (`ID_Cidade`, `Nome`) VALUES
(1, 'Centro'),
(1, 'Vila Madalena'),
(2, 'Centro'),
(2, 'Cambuí'),
(3, 'Centro'),
(3, 'Copacabana'),
(4, 'Centro'),
(4, 'Savassi');

-- Inserindo dados na tabela `Endereco` usando os IDs dos bairros
INSERT INTO `sysadocao`.`Endereco` (`ID_Bairro`, `Logradouro`, `Complemento`) VALUES
(1, 'Rua A', 'Ap 101'),
(2, 'Rua B', 'Casa 10'),
(3, 'Avenida X', 'Sala 200'),
(4, 'Rua Y', 'Ap 302'),
(5, 'Avenida Z', 'Casa 5'),
(6, 'Rua P', 'Loja 15'),
(7, 'Avenida Q', 'Ap 501'),
(8, 'Rua R', 'Casa 20');

-- Inserindo dados na tabela `Doacoes`
INSERT INTO `sysadocao`.`Doacoes` (`ID`, `Valor`, `Data`) VALUES
(1, 100.50, '2023-05-01'),
(2, 50.75, '2023-05-02'),
(3, 200.00, '2023-05-03');

-- Inserindo dados na tabela `cores`
INSERT INTO `sysadocao`.`cores` (`Nome`) VALUES
('Preto'),
('Branco'),
('Marrom'),
('Amarelo'),
('Cinza');

-- Inserindo dados na tabela `especies`
INSERT INTO `sysadocao`.`especies` (`Nome_Especie`) VALUES
('Cachorro'),
('Gato'),
('Coelho'),
('Hamster'),
('Pássaro');

-- Inserindo dados na tabela `racas` usando os IDs das espécies
INSERT INTO `sysadocao`.`racas` (`Nome`, `ID_Especies`) VALUES
('Golden Retriever', 1),
('Persa', 2),
('Holland Lop', 3),
('Sírio', 4),
('Canário', 5);

-- Inserindo dados na tabela `animais` usando os IDs das cores e raças
INSERT INTO `sysadocao`.`animais` (`Nome`, `Idade`, `Sexo`, `Descricao`, `ID_Cor`, `ID_Raca`, `Adotado`) VALUES
('Rex', 3, 'Macho', 'Cachorro brincalhão e amoroso', 1, 1, 0),
('Luna', 2, 'Fêmea', 'Gatinha tranquila e carinhosa', 2, 2, 0),
('Bolinha', 1, 'Macho', 'Coelhinho dócil e brincalhão', 3, 3, 0),
('Frajola', 1, 'Macho', 'Hamster curioso e ativo', 4, 4, 0),
('Piolho', 2, 'Fêmea', 'Canário cantor e colorido', 5, 5, 0);

-- Inserindo dados na tabela `pessoa` usando os IDs dos endereços e doações
INSERT INTO `sysadocao`.`pessoa` (`Nome`, `Numero_telefone`, `Email`, `ID_Endereco`, `Doacoes_ID`) VALUES
('João', '999999999', 'joao@example.com', 1, 1),
('Maria', '888888888', 'maria@example.com', 2, 2),
('Pedro', '777777777', 'pedro@example.com', 3, 3);

-- Inserindo dados na tabela `adocoes` usando os IDs dos animais e das pessoas
INSERT INTO `sysadocao`.`adocoes` (`ID_Animal`, `ID_Pessoa`, `Data_Adocao`) VALUES
(1, 1, '2023-05-01'),
(2, 2, '2023-05-02'),
(3, 3, '2023-05-03');
