package br.com.projetoBase.dto;

import java.util.List;

public record RetornoLocal(long id, int capacidade, List<String> recursos, String localTipo, String LocalDetalhado ) {

}
