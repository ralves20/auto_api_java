#language: pt

Funcionalidade: Restricoes
  Como um usuario da aplicacao bancaria
  Jose deseja consultar as restricoes por CPF
  Para que ele consiga saber se ele deve ou nao prosseguir com determinadas transacoes

  Contexto:
    Dado que Jose acessa a aplicacao

  Esquema do Cenario: Consultar um CPF restrito
    Quando ele realiza uma consulta pelo CPF <cpf>
    Entao ele deve ver que a aplicacao informa corretamente que o CPF <cpf> possui restricao

    Exemplos:
      | cpf         |
      | 97093236014 |
      | 60094146012 |
      | 84809766080 |
      | 62648716050 |
      | 26276298085 |
      | 01317496094 |
      | 55856777050 |
      | 19626829001 |
      | 24094592008 |
      | 58063164083 |

  Esquema do Cenario: Consultar um CPF sem restricao
    Quando ele realiza uma consulta pelo CPF <cpf>
    Entao ele deve ver que o CPF <cpf> nao possui restricao
    Exemplos:
      | cpf         |
      | 82951906013 |
      | 14794539002 |
