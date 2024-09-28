def call(String title, String message, String status, String webhookUrl) {

    def color = status == "success" ? "3066993" : "15158332" 
    def payload = [
        embeds: [
            [
                title: title,
                description: message,
                color: color,
                footer: [
                    text: "Status: ${status}",
                ],
                timestamp: new Date().format("yyyy-MM-dd'T'HH:mm:ss'Z", TimeZone.getTimeZone('UTC'))
            ]
        ]
    ]

    // Kirim notifikasi ke Discord menggunakan curl
    def response = sh(script: "curl -H 'Content-Type: application/json' -d '${groovy.json.JsonOutput.toJson(payload)}' ${webhookUrl}", returnStatus: true)

    if (response != 0) {
        error "Failed to send Discord notification"
    }
}
