#language: pt
Funcionalidade: Simulacoes
  Como um usuario da aplicacao bancaria
  Jose deseja realizar simulacoes financeiras
  Para que ele possa prosseguir normalmente com suas transacoes bancarias apos validadas

  Contexto:
    Dado que Jose acessa a aplicacao

  Cenario: Criar uma simulacao
    Quando ele insere uma nova simulacao informando somente dados validos
    Entao ele deve ver que a simulacao eh inserida corretamente

  Esquema do Cenario: Criar uma simulacao sem informar campos obrigatorios
    Quando ele insere uma simulacao informando todos os dados validos porem sem preencher o campo <campo> que eh obrigatorio
    Entao um erro eh retornando impedindo que a simulacao seja criada devido ao campo faltante

    Exemplos:
      | campo   |
      | cpf     |
      | nome    |
      | email   |
      | valor   |
      | parcelas |
      | seguro  |

  Cenario: Criar uma simulacao com CPF invalido
    Quando ele tenta inserir uma nova simulacao informando somente dados validos porem com CPF invalido
    Entao um erro eh retornando impedindo que a simulacao seja criada

  Cenario: Criar uma simulacao com CPF com ja cadastrado na aplicacao
    Quando ele tenta inserir uma nova simulacao informando porem utilizando um CPF ja cadastrado
    Entao um erro eh de duplicidade retornando impedindo que a simulacao seja criada

  Cenario: Criar uma simulacao com numero de parcelas menor que o minimo permitido
    Quando ele tenta inserir uma nova simulacao informando um numero de parcelas menor que o minimo permitido
    Entao um erro eh retornando impedindo que a simulacao seja criada

  Cenario: Criar uma simulacao com numero de parcelas maior que o maximo permitido
    Quando ele tenta inserir uma nova simulacao informando um numero de parcelas maior que o maximo permitido
    Entao um erro eh retornando impedindo que a simulacao seja criada

  Esquema do Cenario: Criar uma simulacao com valor de parcelas menor que o minimo permitido
    Quando ele tenta inserir uma nova simulacao informando o valor <valor> para cada parcela que eh menor que o minimo permitido
    Entao um erro eh retornando impedindo que a simulacao seja criada

    Exemplos:
      | valor |
      | 999   |
      | 500   |
      | 1     |
      | 0     |

  Esquema do Cenario: Criar uma simulacao com valor de parcelas maior que o maximo permitido
    Quando ele tenta inserir uma nova simulacao informando o valor <valor> para cada parcela que eh maior que o maximo permitido
    Entao um erro eh retornando impedindo que a simulacao seja criada

    Exemplos:
      | valor  |
      | 40001  |
      | 100000 |
      | 500000 |

  Cenario: Alterar uma simulacao existente
    Quando ele realiza a alteracao de um CPF existente que possui simulacao
    Entao a alteracao eh realizada com sucesso

  Cenario: Tentar alterar simulacao de CPF que nao possui simulacao
    Quando ele realiza a alteracao de um CPF que nao possui simulacao
    Entao um erro de nao encontrado eh retornando impedindo que a alteracao da simulacao seja realizada

  Cenario: Remover simulacao por ID inexistente
    Quando ele tenta remover uma simulacao inexistente
    Entao a aplicacao retorna um erro durante a remocao da simulacao

  Cenario: Consultar todas as simulacoes quando ha registros para consultar
    Quando ele realiza a consulta por todas as simulacoes validas existentes
    Entao a aplicacao retorna todas as simulacoes existentes


