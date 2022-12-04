const DEBUG = false;

module.exports = {
    DEBUG,
    HOST: DEBUG ? "http://lavenes.ddns.net:3006" : "https://server.duan1.lavenes.com",
    DATABASE: "mongodb+srv://lavensoft:irUV9ikbAtcgdPQb@cluster0.51kmh.mongodb.net/duan1?retryWrites=true&w=majority"
}