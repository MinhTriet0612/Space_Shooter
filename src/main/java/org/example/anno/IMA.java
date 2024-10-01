package org.example.anno;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface IMA {}  // Initializer Mutator Accessor