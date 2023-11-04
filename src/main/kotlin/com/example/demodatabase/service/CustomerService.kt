package com.example.demodatabase.service

import com.example.demodatabase.dto.Response
import com.example.demodatabase.entites.Customer
import com.example.demodatabase.models.*
import com.example.demodatabase.repositories.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    suspend fun saveCustomer(saveCustomerRequest: SaveCustomerRequest):Response<SaveCustomerResponse>{
        val responseData = customerRepository.saveCustomer(
            saveCustomerRequest.id,
            saveCustomerRequest.name,
            saveCustomerRequest.age
        )
        return Response(data = SaveCustomerResponse(responseData.id.orEmpty()))
    }

    suspend fun getCustomer(getCustomerRequest: GetCustomerRequest):Response<Customer>{
        val responseData = customerRepository.getCustomer(getCustomerRequest.id)
        return Response(data = responseData)
    }

    suspend fun updateCustomer(updateCustomerRequest: UpdateCustomerRequest):Response<UpdateCustomerResponse>{
        customerRepository.updateCustomer(
            updateCustomerRequest.id,
            updateCustomerRequest.name,
            updateCustomerRequest.age
        )
        return Response(data = UpdateCustomerResponse(updateCustomerRequest.id))
    }

    suspend fun deleteCustomer(deleteCustomerRequest: DeleteCustomerRequest):Response<DeleteCustomerResponse>{
        customerRepository.deleteCustomer(deleteCustomerRequest.id)
        return Response(data = DeleteCustomerResponse(deleteCustomerRequest.id))
    }

    suspend fun getAllCustomer():Response<List<Customer>>{
        val responseData = customerRepository.getAllCustomer()
        return Response(data = responseData)
    }
}