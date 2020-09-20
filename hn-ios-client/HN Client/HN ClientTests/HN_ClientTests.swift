//
//  HN_ClientTests.swift
//  HN ClientTests
//
//  Created by Marco Gomiero on 20/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

import XCTest
//import HNFoundation

@testable import HN_Client

class HN_ClientTests: XCTestCase {

    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() throws {
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        
        
        let news = NewsDTODecodable(author: "John", id: 13515, score: 35135, timestamp: 115135, title: "Title", type: "news", url: "www.google.com")
        
        XCTAssertEqual(news.author, "John")
    }

    func testPerformanceExample() throws {
        // This is an example of a performance test case.
        measure {
            // Put the code you want to measure the time of here.
        }
    }

}
