package com.estacionamais.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorGlobalDeExcecoes {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException excecao) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroResposta(excecao.getMessage()));
    }

    @ExceptionHandler(AssinaturaJaAtivaException.class)
    public ResponseEntity<ErroResposta> tratarAssinaturaJaAtiva(AssinaturaJaAtivaException excecao) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErroResposta(excecao.getMessage()));
    }
}
