//
//  News.swift
//  HN Client
//
//  Created by Marco Gomiero on 08/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

struct News: Hashable {
    var id: Int64
    let title: String
    let formattedDate: String
    let url: String
}
