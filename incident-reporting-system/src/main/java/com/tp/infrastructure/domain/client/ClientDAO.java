package com.tp.infrastructure.domain.client;

import java.util.List;
import java.util.UUID;

public interface ClientDAO {
  Client find(UUID id);

  List<Client> findAll();

  void save(UUID id, Client data);

  void update(UUID id, Client data);

  void delete(UUID id);
}
