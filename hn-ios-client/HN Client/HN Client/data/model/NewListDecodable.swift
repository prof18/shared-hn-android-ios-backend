//
//  NewListDecodable.swift
//  HN Client
//
//  Created by Marco Gomiero on 11/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

import HNFoundation

class NewsListDecodable: ListResponseDTO<NewsDTODecodable>, Decodable {
    
    enum CodingKeys: String, CodingKey {
        case items
    }
    
    public required init(from decoder: Decoder) throws {
        super.init()
        
        let container = try decoder.container(keyedBy: CodingKeys.self)
        items = try container.decode([NewsDTODecodable].self, forKey: .items) as [NewsDTODecodable]
        
        super.makeFrozen()
    }
}
