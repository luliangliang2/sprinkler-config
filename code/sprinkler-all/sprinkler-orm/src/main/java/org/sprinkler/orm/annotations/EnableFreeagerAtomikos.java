package org.sprinkler.orm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.sprinkler.orm.config.TransactionManagerConfig;
import org.sprinkler.orm.registrar.FreeagerAtomikosRegistrar;


@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import({ FreeagerAtomikosRegistrar.class, TransactionManagerConfig.class })
public @interface EnableFreeagerAtomikos {
    String[] dataSource();
}
