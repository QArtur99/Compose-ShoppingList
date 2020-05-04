package com.artf.shoppinglistcompose.testing

@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class NoArgClass

@NoArgClass
@Target(AnnotationTarget.CLASS)
annotation class NoArgForTesting