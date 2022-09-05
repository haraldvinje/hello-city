package com.example.hellocity.exceptions

import graphql.ErrorType
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component

@Component
class GraphQLExceptionHandler : DataFetcherExceptionResolverAdapter() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun resolveToSingleError(e: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (e) {
            is CityNotFoundException -> toGraphQLError(e)
            else -> super.resolveToSingleError(e, env)
        }
    }
    private fun toGraphQLError(e: Throwable): GraphQLError? {
        log.warn("Exception while handling request: ${e.message}", e)
        return GraphqlErrorBuilder.newError().message(e.message).errorType(ErrorType.DataFetchingException).build()
    }
}
