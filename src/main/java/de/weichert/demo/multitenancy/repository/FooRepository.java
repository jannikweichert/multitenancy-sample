package de.weichert.demo.multitenancy.repository;

import de.weichert.demo.multitenancy.model.Foo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jannik on 02.07.15.
 */
@Repository
public interface FooRepository extends CrudRepository<Foo, Long>{
}
