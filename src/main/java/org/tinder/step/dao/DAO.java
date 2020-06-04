package org.tinder.step.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface DAO<T> {

    Optional<T> get(int id);

    List<T> getAll();

    boolean add(T object);

    boolean remove(T object);
}
