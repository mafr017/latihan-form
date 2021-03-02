const PROXY_CONFIG = [
    {
        context: [
            "/"
        ],
        target: "http://localhost:8383",
        secure: false
    }
]
module.exports = PROXY_CONFIG;