//
//  NewDTODecodable.swift
//  HN Client
//
//  Created by Marco Gomiero on 11/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

import HNFoundation

class NewsDTODecodable: NewsDTO, Decodable {
    
    // Used for testing
    override init(author: String, id: Int64, score: Int32, timestamp: Int64, title: String, type: String, url: String) {
        super.init(author: author, id: id, score: score, timestamp: timestamp, title: title, type: type, url: url)
    }
    
    enum CodingKeys: String, CodingKey {
        case author
        case id
        case score
        case timestamp
        case title
        case type
        case url
    }
    
    public required init(from decoder: Decoder) throws {
        super.init()
        
        let container = try decoder.container(keyedBy: CodingKeys.self)
        author = try container.decode(String.self, forKey: .author)
        id = try container.decode(Int64.self, forKey: .id)
        score = try container.decode(Int32.self, forKey: .score)
        timestamp = try container.decode(Int64.self, forKey: .timestamp)
        title = try container.decode(String.self, forKey: .title)
        type = try container.decode(String.self, forKey: .type)
        url = try container.decode(String.self, forKey: .url)
        
        super.makeFrozen()
    }
}
