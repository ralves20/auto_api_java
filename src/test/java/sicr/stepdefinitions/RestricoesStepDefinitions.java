package sicr.stepdefinitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import sicr.model.HttpStatusCode;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;
import static sicr.model.URLsInfo.BASE_URL;
import static sicr.model.URLsInfo.RESTRICOES_PATH;

public class RestricoesStepDefinitions {


    @Dado("que {actor} acessa a aplicacao")
    public void queJoseAcessaAAplicacao(Actor actor) {
        actor.whoCan(CallAnApi.at(BASE_URL));
    }

    @Quando("^el[e|a] realiza uma consulta pelo CPF (.*)")
    public void eleRealizaUmaConsultaPeloCPFCpf(String cpf) {
        theActorInTheSpotlight().attemptsTo(
                Get.resource(RESTRICOES_PATH+cpf)
        );
    }

    @Entao("^el[e|a] deve ver que a aplicacao informa corretamente que o CPF (.*) possui restricao")
    public void eleDeveVerQueAAplicacaoInformaCorretamenteQueOCPFCpfPossuiRestricao(String cpf) {
        String cpfExpectedMessage = "O CPF "+cpf+" tem problema";
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.SUCCESS_REQUEST)
                                .body("mensagem", equalTo(cpfExpectedMessage)))
        );
    }

    @Entao("^el[e|a] deve ver que o CPF (.*) nao possui restricao")
    public void ele_deve_ver_que_o_cpf_nao_possui_restricao(String cpf) {
        theActorInTheSpotlight().should(
                seeThatResponse("",
                        response -> response.statusCode(HttpStatusCode.NO_CONTENT))
        );
    }


}
