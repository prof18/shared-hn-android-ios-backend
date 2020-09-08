//
//  ErrorView.swift
//  HN Client
//
//  Created by Marco Gomiero on 08/09/2020.
//  Copyright © 2020 Marco Gomiero. All rights reserved.
//

import SwiftUI

struct ErrorView: View {
    
    let reason: String
    let viewModel: MainViewModel
    
    init(reason: String, viewModel: MainViewModel) {
        self.reason = reason
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack {
            Text(self.reason)
            Spacer()
                .frame(height: 16)
            Button(action: {
                self.viewModel.loadData()
            }) {
                Text("Refresh")
            }
        }
    }
}

struct ErrorView_Previews: PreviewProvider {
    static var previews: some View {
        ErrorView(reason: "This is a random error", viewModel: MainViewModel())
    }
}
