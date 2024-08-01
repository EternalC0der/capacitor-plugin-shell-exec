// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorCommunityShellExec",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "CapacitorCommunityShellExec",
            targets: ["ShellExecPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "ShellExecPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/ShellExecPlugin"),
        .testTarget(
            name: "ShellExecPluginTests",
            dependencies: ["ShellExecPlugin"],
            path: "ios/Tests/ShellExecPluginTests")
    ]
)