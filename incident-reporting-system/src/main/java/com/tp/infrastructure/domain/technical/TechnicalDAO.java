package com.tp.infrastructure.domain.technical;

import java.util.List;
import java.util.UUID;

public interface TechnicalDAO {
  Technical find(UUID id);

  List<Technical> findAll();

  void save(UUID id, Technical data);

  void update(UUID id, Technical data);

  void delete(UUID id);
}
