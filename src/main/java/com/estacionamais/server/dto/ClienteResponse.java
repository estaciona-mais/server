package com.estacionamais.server.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record ClienteResponse(
                              Long id,
                              String nome,
                              OffsetDateTime criadoEm,
                              List<String> avisos) {}
