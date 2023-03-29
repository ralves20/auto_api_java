package sicr.stepdefinitions;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import sicr.model.HttpStatusCode;

import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;
import static sicr.model.SimulacaoInfo.cpfRandom;
import static sicr.model.SimulacaoInfo.createValidSimulacaoMap;
import static sicr.model.URLsInfo.SIMULACOES_PATH;

public class SimulacaoStepDefinitions {

    @Quando("ele insere uma nova simulacao informando somente dados validos")
    public void eleInsereUmaNovaSimulacaoInformandoSomenteDadosValidos() {
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("^ele insere uma simulacao informando todos os dados validos porem sem preencher o campo (.*) que eh obrigatorio")
    public void eleInsereUmaSimulacaoInformandoTodosOsDadosValidosPoremSemPreencherOCampoCampoQueEhObrigatorio(String campo) {
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        simulacaoValidaUsed.remove(campo);
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("ele tenta inserir uma nova simulacao informando somente dados validos porem com CPF invalido")
    public void eleTentaInserirUmaNovaSimulacaoInformandoSomenteDadosValidosPoremComCPFInvalido() {
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        simulacaoValidaUsed.remove("cpf");
        simulacaoValidaUsed.put("cpf", "CPF_INVALIDO");
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("ele tenta inserir uma nova simulacao informando porem utilizando um CPF ja cadastrado")
    public void eleTentaInserirUmaNovaSimulacaoInformandoSomenteDadosValidosPoremComCPFJaCadastrado() {
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        simulacaoValidaUsed.remove("cpf");
        simulacaoValidaUsed.put("cpf", "123456789");
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                ),
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("ele tenta inserir uma nova simulacao informando um numero de parcelas menor que o minimo permitido")
    public void eleTentaInserirUmaNovaSimulacaoInformandoUmNumeroDeParcelasMenorQueOMinimoPermitido() {
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        simulacaoValidaUsed.remove("parcelas");
        simulacaoValidaUsed.put("parcelas", 1);
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("ele tenta inserir uma nova simulacao informando um numero de parcelas maior que o maximo permitido")
    public void eleTentaInserirUmaNovaSimulacaoInformandoUmNumeroDeParcelasMaiorQueOMaximoPermitido() {
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        simulacaoValidaUsed.remove("parcelas");
        simulacaoValidaUsed.put("parcelas", 50000);
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("^ele tenta inserir uma nova simulacao informando o valor (.*) para cada parcela que eh menor que o minimo permitido")
    public void eleTentaInserirUmaNovaSimulacaoInformandoOValorValorParaCadaParcelaQueEhMenorQueOMinimoPermitido(String valor) {
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        simulacaoValidaUsed.remove("valor");
        simulacaoValidaUsed.put("valor", valor);
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("^ele tenta inserir uma nova simulacao informando o valor (.*) para cada parcela que eh maior que o maximo permitido")
    public void eleTentaInserirUmaNovaSimulacaoInformandoOValorValorParaCadaParcelaQueEhMaiorQueOMaximoPermitido(String valor) {
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        simulacaoValidaUsed.remove("valor");
        simulacaoValidaUsed.put("valor", valor);
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("ele realiza a alteracao de um CPF existente que possui simulacao")
    public void eleRealizaAAlteracaoDeUmCPFExistenteQuePossuiSimulacao() {
        String cpfToUpdate = cpfRandom;
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        theActorInTheSpotlight().attemptsTo(
                Post.to(SIMULACOES_PATH).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                ),
                Put.to(SIMULACOES_PATH+cpfToUpdate).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );
    }

    @Quando("ele realiza a alteracao de um CPF que nao possui simulacao")
    public void eleRealizaAAlteracaoDeUmCPFQueNaoPossuiSimulacao() {
        String cpfToUpdate = cpfRandom;
        Map<String, Object> simulacaoValidaUsed = createValidSimulacaoMap();
        theActorInTheSpotlight().attemptsTo(
                Put.to(SIMULACOES_PATH+cpfToUpdate).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json").body(simulacaoValidaUsed)
                )
        );

    }

    @Quando("ele tenta remover uma simulacao inexistente")
    public void eleTentaRemoverUmaSimulacaoInexistente() {
        String idInvalidoSimulacao = "ID_INVALIDO";
        theActorInTheSpotlight().attemptsTo(
                Delete.from(SIMULACOES_PATH+idInvalidoSimulacao).with(
                        requestSpecification -> requestSpecification.header("Content-Type", "application/json")
                )
        );
    }

    @Quando("ele realiza a consulta por todas as simulacoes validas existentes")
    public void eleRealizaAConsultaPorTodasAsSimulacoesValidasExistentes() {
        theActorInTheSpotlight().attemptsTo(
                Get.resource(SIMULACOES_PATH)
        );
    }

    @Entao("um erro eh retornando impedindo que a simulacao seja criada")
    public void umErroEhRetornandoImpedindoQueASimulacaoSejaCriada() {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.BAD_REQUEST))
        );
    }


    @Entao("a alteracao eh realizada com sucesso")
    public void aAlteracaoEhRealizadaComSucesso() {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.SUCCESS_REQUEST))
        );
    }

    @Entao("um erro eh retornando impedindo que a simulacao seja criada devido ao campo faltante")
    public void umErroEhRetornandoImpedindoQueASimulacaoSejaCriadaDevidoAoCampoFaltante() {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.BAD_REQUEST))
        );
    }

    @Entao("um erro eh de duplicidade retornando impedindo que a simulacao seja criada")
    public void umErroEhDeDuplicidadeRetornandoImpedindoQueASimulacaoSejaCriada() {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.BAD_REQUEST).body("message", equalTo("CPF duplicado")))
        );
    }

    @Entao("um erro de nao encontrado eh retornando impedindo que a alteracao da simulacao seja realizada")
    public void umErroDeNaoEncontradoEhRetornandoImpedindoQueAAlteracaoDaSimulacaoSejaRealizada() {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.NOT_FOUND))
        );
    }

    @Entao("a aplicacao retorna um erro durante a remocao da simulacao")
    public void aAplicacaoRetornaUmErroDuranteARemocaoDaSimulacao() {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.BAD_REQUEST))
        );
    }

    @Entao("a aplicacao retorna todas as simulacoes existentes")
    public void aAplicacaoRetornaTodasAsSimulacoesExistentes() {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.SUCCESS_REQUEST))
        );
    }

    @Entao("ele deve ver que a simulacao eh inserida corretamente")
    public void ele_deve_ver_que_a_simulacao_eh_inserida_corretamente() {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.SUCCESS_CREATION))
        );
    }

}
