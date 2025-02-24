package com.business.fitrack.data.network

import com.business.fitrack.data.network.dto.product.ProductDto
import com.business.fitrack.data.network.dto.search.ProductListDto


/**
 * Remote datasource interface.
 */
interface RemoteDatasource {

    /**
     * Retrieves the product having [code] as identifier.
     */
    suspend fun getProduct(code: String) : DataResult<ProductDto, Throwable>

    /**
     * Retrieves all the products that match [query].
     */
    suspend fun searchByQuery(query : String) : DataResult<ProductListDto, Throwable>
}