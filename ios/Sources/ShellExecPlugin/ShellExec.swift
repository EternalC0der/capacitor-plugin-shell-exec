import Foundation

@objc public class ShellExec: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
