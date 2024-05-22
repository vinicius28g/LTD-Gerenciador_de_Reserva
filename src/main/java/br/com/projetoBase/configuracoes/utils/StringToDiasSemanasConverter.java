package br.com.projetoBase.configuracoes.utils;


import br.com.projetoBase.modelo.DiasSemanas;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class StringToDiasSemanasConverter implements Converter<String, Set<DiasSemanas>> {


    @Override
    public Set<DiasSemanas> convert(String source) {
        Set<DiasSemanas> diasSemanas = new HashSet<>();
        Arrays.stream(source.split(","))
                .map(String::trim)
                .map(DiasSemanas::valueOf)
                .forEach(diasSemanas::add);
        return diasSemanas;
    }
}
