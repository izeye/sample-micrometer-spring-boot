package com.izeye.sample;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspect extending {@link ObservedAspect} indirectly via an abstract class.
 *
 * @author Johnny Lim
 */
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AbstractClassExtendingObservedAspect extends AbstractObservedAspect {
    public AbstractClassExtendingObservedAspect(ObservationRegistry registry) {
        super(registry);
    }
}

/**
 * Abstract class just to avoid Spring AOP validation check.
 *
 * @author Johnny Lim
 */
abstract class AbstractObservedAspect extends ObservedAspect {

    AbstractObservedAspect(ObservationRegistry registry) {
        super(registry);
    }

}
