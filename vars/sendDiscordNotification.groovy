import java.text.SimpleDateFormat

def call(String title, String message, String status, String webhookUrl) {
    // Buat payload untuk notifikasi Discord
    def color = status == "success" ? 3066993 : 15158332
    def timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date())
    
    def payload = [
        embeds: [
            [
                title: title,
                description: message,
                color: color,
                footer: [
                    text: "Status: ${status}",
                ],
                timestamp: timestamp
            ]
        ]
    ]

    // Kirim notifikasi ke Discord menggunakan curl
    def response = sh(script: "curl -H 'Content-Type: application/json' -d '${groovy.json.JsonOutput.toJson(payload)}' ${webhookUrl}", returnStatus: true)

    // Cek status 
    if (response != 0) {
        error "Failed to send Discord notification"
    }
}
