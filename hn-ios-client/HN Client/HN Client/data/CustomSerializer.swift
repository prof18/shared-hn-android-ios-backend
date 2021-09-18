//
//  CustomSerializer.swift
//  HN Client
//
//  Created by Marco Gomiero on 05/01/21.
//  Copyright © 2021 Marco Gomiero. All rights reserved.
//
import Foundation
import Alamofire
import HNFoundation

struct CustomSerializer<T: BaseDTO>: ResponseSerializer {
    
    func serialize(request: URLRequest?, response: HTTPURLResponse?, data: Data?, error: Error?) throws -> T {
        
        guard error == nil else { throw error! }
        
        guard let data = data, !data.isEmpty else {
            guard emptyResponseAllowed(forRequest: request, response: response) else {
                throw AFError.responseSerializationFailed(reason: .inputDataNilOrZeroLength)
            }
            
            guard let emptyResponseType = T.self as? EmptyResponse.Type, let emptyValue = emptyResponseType.emptyValue() as? T else {
                throw AFError.responseSerializationFailed(reason: .invalidEmptyResponse(type: "\(T.self)"))
            }
            
            return emptyValue
        }
        
        do {
            let jsonString = try StringResponseSerializer().serialize(request: request,
                                                                      response: response,
                                                                      data: data,
                                                                      error: error)
            
            let deserializedObject = try T().deserialize(jsonString: jsonString) as! T
            deserializedObject.makeFrozen()
            return deserializedObject
            
        } catch {
            throw AFError.responseSerializationFailed(reason: .decodingFailed(error: error))
            
        }
    }
}
