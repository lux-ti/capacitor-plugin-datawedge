import Foundation

@objc public class DataWedge: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
