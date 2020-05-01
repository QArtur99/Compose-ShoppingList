package com.artf.shoppinglistcompose.testing

import com.artf.data.testing.OpenClass

@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class NoArgClass

@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class NoArgForTesting