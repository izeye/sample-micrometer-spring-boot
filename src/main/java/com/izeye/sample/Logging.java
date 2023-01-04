package com.izeye.sample;

import java.lang.annotation.*;

/**
 * Annotation to mark methods that you want to log.
 *
 * @author Johnny Lim
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logging {
}
