package sicr.model;

import support.GeraCpfCnpj;

import java.util.HashMap;
import java.util.Map;

public class SimulacaoInfo {

    static Map<String, Object> simulacaoValidaInfo = new HashMap<>();
    static GeraCpfCnpj geraCpfCnpj = new GeraCpfCnpj();
    public static String cpfRandom = geraCpfCnpj.cpf().replace(".", "");



    public static Map<String, Object> createValidSimulacaoMap(){
        simulacaoValidaInfo.put("nome", "Jose");
        simulacaoValidaInfo.put("cpf", cpfRandom);
        simulacaoValidaInfo.put("email", "emailvalidoteste@mmailinator.com");
        simulacaoValidaInfo.put("valor", 5000);
        simulacaoValidaInfo.put("parcelas", 5);
        simulacaoValidaInfo.put("seguro", true);

        return simulacaoValidaInfo;
    }
}
