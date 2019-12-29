package ddalivery.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import java.io.Serializable

interface BaseJPAQuerydslRepository<T, ID : Serializable?> : JpaRepository<T, ID>, QuerydslPredicateExecutor<T>